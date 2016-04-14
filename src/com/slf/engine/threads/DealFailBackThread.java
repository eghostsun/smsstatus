package com.slf.engine.threads;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import com.slf.common.client.HttpSqsServiceImpl;
import com.slf.common.client.IHttpSqsService;
import com.slf.common.client.IMidService;
import com.slf.common.client.MidServiceImpl;
import com.slf.engine.base.BaseThread;
import com.slf.engine.bo.LsSms;
import com.slf.engine.common.SysContants;
import com.slf.engine.threads.helper.ThreadHelper;

public class DealFailBackThread extends BaseThread{

	public static final Logger log = Logger.getLogger(DealFailBackThread.class);

	private IHttpSqsService httpSqsService = null;
	
	private IMidService midService = null;
	
	private Map<String, Object> myClient = new HashMap<String, Object>();
	
	public DealFailBackThread(ThreadGroup group) {
		// TODO Auto-generated constructor stub
		super(group, "GET_FAIL_BACK_THREAD");
		log.log(Priority.INFO, "GET_FAIL_BACK_THREAD IS RUN");
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
		Map<String, String> obj = httpSqsService.getFromSqs(SysContants.HTTPSQS_FAIL);
		if(obj != null)
		{
			int fscs = obj.get("fscs") == null ? 0 : Integer.parseInt(obj.get("fscs"));
			if(fscs <= SysContants.NOTICE_MAXNUM)
			{
				String fssj = obj.get("fssj");
				LsSms lsSms = ThreadHelper.putMapToFail(obj);
				if(fssj == null || System.currentTimeMillis() - Long.parseLong(fssj) >= SysContants.NOTICE_TIMEOUT * 1000)
				{
					log.log(Priority.INFO, "get fail notice->" + obj.toString());
					if(!midService.doBack(myClient, lsSms))
					{
						obj.put("fscs", String.valueOf(fscs+1));
						obj.put("fssj", String.valueOf(System.currentTimeMillis()));
						httpSqsService.putIntoSqs(SysContants.HTTPSQS_FAIL, obj);
					}
				}else{
					httpSqsService.putIntoSqs(SysContants.HTTPSQS_FAIL, obj);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
				}
			}else{
				log.log(Priority.INFO, "fail back out maxnum,break it->" + obj.toString());
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
