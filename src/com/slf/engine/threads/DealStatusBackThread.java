package com.slf.engine.threads;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.slf.common.client.HttpSqsServiceImpl;
import com.slf.common.client.IHttpSqsService;
import com.slf.common.client.IMidService;
import com.slf.common.client.MidServiceImpl;
import com.slf.engine.base.BaseThread;
import com.slf.engine.base.IBaseDao;
import com.slf.engine.bo.LsDxhk;
import com.slf.engine.bo.LsMtsms;
import com.slf.engine.common.SysContants;
import com.slf.engine.threads.helper.ThreadHelper;
import com.slf.engine.utils.DateUtils;
import com.slf.engine.utils.Factory;

public class DealStatusBackThread extends BaseThread {

	private static final Logger log = Logger.getLogger(DealStatusBackThread.class);
	private Map<String, Object> myClient = new HashMap<String, Object>();
	private IHttpSqsService httpSqsService = null;
	private IMidService midService = null;
	public DealStatusBackThread(ThreadGroup group)
	{
		super(group,"DEAL_STATUSBACK_THREAD");
		log.log(Priority.INFO, "DEAL_STATUSBACK_THREAD IS RUN");
		if(httpSqsService == null)
		{
			httpSqsService = new HttpSqsServiceImpl();
		}
		if(midService == null)
		{
			midService = new MidServiceImpl();
		}
	}
	@Override
	protected void doBusiness() {
		// TODO Auto-generated method stub
		Map<String, String> obj = httpSqsService.getFromSqs(SysContants.HTTPSQS_STATUS);
		IBaseDao baseDao = (IBaseDao)Factory.getInstantBiz("baseDao");
		IBaseDao ttBaseDao = (IBaseDao)Factory.getInstantBiz("ttBaseDao");
		if(obj != null)
		{
			String fssj = obj.get("fssj");
			int fscs = obj.get("fscs") == null ? 0 : Integer.parseInt(obj.get("fscs"));
			if(fscs <= SysContants.NOTICE_MAXNUM)
			{
				if(fssj == null || System.currentTimeMillis() - Long.parseLong(fssj) >= SysContants.NOTICE_TIMEOUT * 1000)
				{
					log.log(Priority.INFO, "get status->" + obj.toString());
					long start = System.currentTimeMillis();
					LsDxhk lsDxhk = ThreadHelper.putMapToStatus(obj);
					LsMtsms lsMtsms = new LsMtsms();
					try {
						lsMtsms.setFhxx(lsDxhk.getYyslsh());
						lsMtsms.setFshm(lsDxhk.getFshm());
						lsMtsms = (LsMtsms)baseDao.getObject(lsMtsms, "getSmsInfo");
						if(lsMtsms != null)
						{
							lsDxhk.setDlid(lsMtsms.getDlid());
							lsDxhk.setTdbh(lsMtsms.getTdbh());
							lsDxhk.setDxid(String.valueOf(lsMtsms.getDxid()));
							lsDxhk.setDlflsh(lsMtsms.getDlflsh());
							lsDxhk.setPch(lsMtsms.getPch());
							lsDxhk.setZt("3");
							if(!midService.doBackStatus(myClient, lsDxhk))
							{
								obj.put("fssj", String.valueOf(System.currentTimeMillis()));
								obj.put("fscs", String.valueOf(fscs+1));
								httpSqsService.putIntoSqs(SysContants.HTTPSQS_STATUS, obj);
							}else{
								lsDxhk.setFhrq(DateUtils.strDate("yyyyMMdd"));
								lsDxhk.setFhsj(DateUtils.strDate("yyyyMMddHHmmss"));
								ttBaseDao.add(lsDxhk, "addDxhk");
							}
						}else{
							lsMtsms = new LsMtsms();
							lsMtsms.setFhxx(lsDxhk.getYyslsh());
							lsMtsms.setFshm(lsDxhk.getFshm());
							lsMtsms = (LsMtsms)baseDao.getObject(lsMtsms, "getSmsHis");
							
							if(lsMtsms != null)
							{
								lsDxhk.setDlid(lsMtsms.getDlid());
								lsDxhk.setTdbh(lsMtsms.getTdbh());
								lsDxhk.setDxid(String.valueOf(lsMtsms.getDxid()));
								lsDxhk.setDlflsh(lsMtsms.getDlflsh());
								lsDxhk.setPch(lsMtsms.getPch());
								lsDxhk.setZt("3");
								if(!midService.doBackStatus(myClient, lsDxhk))
								{
									obj.put("fssj", String.valueOf(System.currentTimeMillis()));
									obj.put("fscs", String.valueOf(fscs+1));
									httpSqsService.putIntoSqs(SysContants.HTTPSQS_STATUS, obj);
								}else{
									lsDxhk.setFhrq(DateUtils.strDate("yyyyMMdd"));
									lsDxhk.setFhsj(DateUtils.strDate("yyyyMMddHHmmss"));
									ttBaseDao.add(lsDxhk, "addDxhk");
								}
							}
						}
						log.log(Priority.INFO, "send finish->" + (System.currentTimeMillis() - start));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						log.log(Priority.ERROR, "query status error:" + e.getMessage());
						httpSqsService.putIntoSqs(SysContants.HTTPSQS_STATUS, obj);
					}
				}else{
					httpSqsService.putIntoSqs(SysContants.HTTPSQS_STATUS, obj);
				}
			}else{
				log.log(Priority.INFO, "status back out maxnum,break it->" + obj.toString());
			}
			
		}else{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
	}

}
