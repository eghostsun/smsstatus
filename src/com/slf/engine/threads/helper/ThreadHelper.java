package com.slf.engine.threads.helper;

import java.util.Map;

import com.slf.engine.bo.LsDxhk;
import com.slf.engine.bo.LsSms;

public class ThreadHelper {

	public static LsSms putMapToFail(Map<String, String> obj)
	{
		LsSms lsSms = new LsSms();
		lsSms.setTdbh(obj.get("tdbh"));
		lsSms.setPch(obj.get("pch"));
		String dxid = obj.get("dxid");
		lsSms.setDxid(dxid == null ? null : Long.valueOf(dxid));
		lsSms.setYyslsh(obj.get("yyslsh"));
		lsSms.setZt(obj.get("zt"));
		lsSms.setCljg(obj.get("cljg"));
		return lsSms;
	}
	
	public static LsDxhk putMapToStatus(Map<String, String> obj)
	{
		LsDxhk lsDxhk = new LsDxhk();
		lsDxhk.setCljg(obj.get("result"));
		lsDxhk.setZt(obj.get("code"));
		lsDxhk.setYyslsh(obj.get("yyslsh"));
		lsDxhk.setFshm(obj.get("fshm"));
		lsDxhk.setFhms(obj.get("fhms"));
		return lsDxhk;
	}
}
