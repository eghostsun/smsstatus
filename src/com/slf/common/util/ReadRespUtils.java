package com.slf.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.slf.engine.common.SysContants;


public class ReadRespUtils {
	private static final String DEFAULT_CHARASET = "utf-8";
	/**
	 * 读取xml结果
	 * @param xml
	 * @return
	 * @throws DocumentException 
	 */
	public static Map<String, Object> readXmlResult(String xml) throws DocumentException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		Iterator childIter = root.elementIterator();
		while(childIter.hasNext())
		{
			Element child = (Element)childIter.next();
			if(child.isTextOnly())
			{
				try {
					map.put(child.getName(), URLDecoder.decode(child.getTextTrim(),DEFAULT_CHARASET));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
				}
			}else{
				List<Map<String, Object>> childsList = new ArrayList<Map<String,Object>>();
				Iterator childIter2 = child.elementIterator();
				while(childIter2.hasNext())
				{
					Map<String, Object> childMap = new HashMap<String, Object>();
					Element child2 = (Element)childIter2.next(); //obj
					if(child.isTextOnly())
					{
						try {
							childMap.put(child2.getName(), URLDecoder.decode(child2.getTextTrim(),DEFAULT_CHARASET));
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
						}
						childsList.add(childMap);
					}else{
						Iterator childIter3 = child2.elementIterator();
						while(childIter3.hasNext())
						{
							Element child3 = (Element)childIter3.next(); //a|b
							if(child3.isTextOnly())
							{
								try {
									childMap.put(child3.getName(), URLDecoder.decode(child3.getTextTrim(),DEFAULT_CHARASET));
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
								}
							}
						}
					}
					childsList.add(childMap);
				}
				map.put(child.getName(), childsList);
			}
		}
		return map;
	}
	
	/**
	 * 读取json结果
	 * @param json
	 * @return
	 */
	public static Map<String, Object> readJsonResult(String json)
	{

		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject parent = JSONObject.fromObject(json);
		Iterator<String> keys = parent.keys();
		while(keys.hasNext())
		{
			String key = keys.next();
			Object obj = parent.get(key);
			if(obj instanceof JSONArray)
			{
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				JSONArray array = (JSONArray)obj;
				for(int i = 0; i < array.size(); i++)
				{
					Map<String, Object> child = new HashMap<String, Object>();
					JSONObject jobj = (JSONObject)array.get(i);
					Iterator<String> childKeys = jobj.keys();
					while(childKeys.hasNext())
					{
						String childKey = childKeys.next();
						try {
							child.put(childKey, URLDecoder.decode(String.valueOf(jobj.get(childKey)), DEFAULT_CHARASET));
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
						}
					}
					list.add(child);
				}
				resultMap.put(key, list);
			}else{
				try {
					resultMap.put(key, URLDecoder.decode(String.valueOf(obj), DEFAULT_CHARASET));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
				}
			}
		}
		return resultMap;
	}
	
	public static void main(String args[])
	{
		String xml = "{\"RETCODE\":\"1\",\"LIST\":[{\"A\":\"2\",\"B\":\"2\"},{\"A\":\"2\",\"B\":\"2\"}]}";
			System.out.println(readJsonResult(xml).toString());
	}
	
}
