package com.slf.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.slf.engine.bo.LsSms;

public class BeanToMapUtil {

	
   public static LsSms conventMap(Map<String,String> map){
	   LsSms lsSms = new LsSms();
	   lsSms.setDxid(Long.valueOf(map.get("dxid")));
	   lsSms.setPch(map.get("pch"));
	   lsSms.setZt(map.get("zt"));
	   lsSms.setCljg(map.get("cljg"));
	   lsSms.setYyslsh(map.get("yyslsh"));	   
	   return lsSms;
   }
	
}
