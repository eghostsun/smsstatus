package com.slf.engine.services.impl;

import com.slf.engine.bo.LsDxhk;
import com.slf.engine.services.IBusService;

public class BusServiceImpl implements IBusService {	
	
//	/**
//	 * 获取短信状态
//	 * @return
//	 */
//	public LsDxhk getRecvStatus()
//	{
//		LsSms  lsSms = null;
//		synchronized (SysContext.QUERY_ORDER_LIST) {
//			if(SysContext.QUERY_ORDER_LIST.size() > 0)
//			{
//				lsSms = SysContext.QUERY_ORDER_LIST.get(0);
//				SysContext.QUERY_ORDER_LIST.remove(lsSms);
//			}
//		}
//		return lsSms;
//	}
//	
//	/**
//	 * 获取失败要通知的短信
//	 * @return
//	 */
//	public LsSms  getFailLsSms()
//	{
//		LsSms  lsSms = null;
//		synchronized (SysContext.QUERY_FAIL_ORDER_MAP) {
//			if(SysContext.QUERY_FAIL_ORDER_MAP.size() > 0)
//			{				
//				Set<Long> keys = SysContext.QUERY_FAIL_ORDER_MAP.keySet();			
//				if(keys.iterator().hasNext())
//				{
//					Long key = keys.iterator().next();
//					lsSms = SysContext.QUERY_FAIL_ORDER_MAP.get(key);
//					SysContext.QUERY_FAIL_ORDER_MAP.remove(key);
//				}
//			}
//		}
//		return lsSms;		
//	}	
}