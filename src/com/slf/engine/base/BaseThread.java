package com.slf.engine.base;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.slf.engine.common.SysContext;

public abstract class BaseThread extends Thread {

	private static final Logger log = Logger.getLogger(BaseThread.class);
	
	public BaseThread(ThreadGroup group, String string) {
		super(group, string);
	}
	
	public BaseThread()
	{}
	public void run()
	{
		while(true)
		{
			if(SysContext.IS_START)
			{
				doBusiness();
			}else{
				try {
					log.log(Priority.INFO, "system is close wait open->3秒钟后重试");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
			}
		}
	}
	protected abstract void doBusiness(); //业务操作

}
