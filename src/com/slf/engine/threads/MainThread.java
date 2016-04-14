package com.slf.engine.threads;


import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.slf.engine.base.BaseThread;
import com.slf.engine.common.SysContants;
import com.slf.engine.common.SysContext;

public class MainThread extends BaseThread {

    private static final Logger log             = Logger.getLogger(MainThread.class);


    public MainThread(){
        log.log(Priority.INFO, "Main Thread Start!");
        SysContext.IS_START = true;
    }

	@Override
	protected void doBusiness() {
		// TODO Auto-generated method stub
		synchronized (SysContext.DEAL_FAILBACK_THREAD_GROUP) {
			while(SysContants.BACK_THREADNUM > SysContext.DEAL_FAILBACK_THREAD_GROUP.activeCount())
			{
				log.log(Priority.INFO, "DEAL_FAILBACK THREADS NUM【" + SysContext.DEAL_FAILBACK_THREAD_GROUP.activeCount() + "】");
				DealFailBackThread dealFailBackThread = new DealFailBackThread(SysContext.DEAL_FAILBACK_THREAD_GROUP);
				dealFailBackThread.start();
			}
		}
    	
    	synchronized (SysContext.DEAL_STATUSBACK_THREAD_GROUP) {
			while(SysContants.STATUS_THREADNUM > SysContext.DEAL_STATUSBACK_THREAD_GROUP.activeCount())
			{
				log.log(Priority.INFO, "DEAL_STATUSBACK THREADS NUM【" + SysContext.DEAL_STATUSBACK_THREAD_GROUP.activeCount() + "】");
				DealStatusBackThread dealStatusBackThread = new DealStatusBackThread(SysContext.DEAL_STATUSBACK_THREAD_GROUP);
				dealStatusBackThread.start();
			}
		}
		
		try {
			Thread.sleep(10000); //10秒钟监控一次线程状态
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}
}
