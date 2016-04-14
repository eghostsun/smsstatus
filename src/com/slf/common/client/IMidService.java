package com.slf.common.client;

import java.util.List;
import java.util.Map;

import com.slf.engine.bo.LsDxhk;
import com.slf.engine.bo.LsSms;



public interface IMidService {

	public Map<String, Object> doLogin(Map<String,Object> myClient);
	public List<Map<String, Object>> getSmsList(Map<String,Object> myClient,short fph);
	public boolean doBack(Map<String,Object> myClient,LsSms lsSms);
	public int getMaxNum(Map<String,Object> myClient);
	public boolean doBackStatus(Map<String, Object> myClient,LsDxhk lsDxhk);
}
