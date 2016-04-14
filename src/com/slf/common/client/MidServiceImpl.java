package com.slf.common.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.utils.CryptUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.slf.common.util.KeyedDigestMD5;
import com.slf.common.util.ReadRespUtils;
import com.slf.engine.bo.LsDxhk;
import com.slf.engine.bo.LsSms;
import com.slf.engine.common.SysContants;



public class MidServiceImpl implements IMidService{

	private static final Logger log = Logger.getLogger(MidServiceImpl.class);
	
	
	public Map<String, Object> doLogin(Map<String, Object> myClient)
	{		
        HttpClient client = new HttpClient();
		StringBuffer url = new StringBuffer(SysContants.MID_URL);
		url.append("login?name=");
		url.append(SysContants.MID_NAME);
		url.append("&pwd=");
		String pwd = SysContants.MID_PWD.startsWith("{3DES}") ? CryptUtils.decrypt(SysContants.MID_PWD.replace("{3DES}", "")) : SysContants.MID_PWD;
		url.append(pwd);
		String sign = KeyedDigestMD5.getKeyedDigest(SysContants.MID_NAME + pwd + SysContants.MID_KEY, pwd);
		url.append("&sign=");
		url.append(sign);
//		log.log(Priority.INFO, "login->url:" + url);
		GetMethod get = new GetMethod(url.toString());
		try {
			get.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, SysContants.DEFAULT_CHARSET); 
			client.executeMethod(get);
			String body = get.getResponseBodyAsString();
			log.log(Priority.INFO, "rplogin->msg:" + body);
			if(get.getStatusCode() != HttpStatus.SC_OK)
			{
				log.log(Priority.ERROR, "login error-> status:" + get.getStatusCode());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					log.log(Priority.ERROR, e1);
				}
				return doLogin(myClient); 
			}			
			Map<String, Object> result = ReadRespUtils.readJsonResult(body);
			if("1".equals(result.get("RETCODE")))
			{
				myClient.put("token", result.get("TOKEN").toString());
				myClient.put("client", client);
				return myClient;
			}else{
				return null;
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			log.log(Priority.ERROR, "login error-> try agent wait 3s");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
			}
			return doLogin(myClient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.log(Priority.ERROR, "login error-> try agent wait 3s");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				log.log(Priority.ERROR, e1);
			}
			return doLogin(myClient);
		} finally {
			get.releaseConnection();
		}
	}
	
	
	public List<Map<String, Object>> getSmsList(Map<String, Object> myClient,short fph)
	{
		if(!myClient.containsKey("token"))
		{
			myClient = doLogin(myClient);
		}
		StringBuffer url = new StringBuffer(SysContants.MID_URL);
		url.append("getsms?token=");
		url.append(myClient.get("token").toString());
		url.append("&tdbh=");
		url.append( SysContants.TDBH);
		url.append("&fph=");
		url.append(fph);
		url.append("&num=");
		url.append(SysContants.MID_GETNUM);
		GetMethod get = new GetMethod(url.toString());
		get.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, SysContants.DEFAULT_CHARSET); 
		try {
//			log.log(Priority.INFO, "getsms->url:" + url);
			HttpClient client = (HttpClient) myClient.get("client");
			client.executeMethod(get);
			String body = get.getResponseBodyAsString();
			if(get.getStatusCode() != HttpStatus.SC_OK)
			{
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
				}
				return getSmsList(myClient,fph); 
			}				
			Map<String, Object> result = ReadRespUtils.readJsonResult(body);
			if("1".equals(result.get("RETCODE")))
			{
				log.log(Priority.INFO, "rpgetsms->msg:" + body);
				List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("MSGS");
				return list;
			}else if("3".equals(result.get("RETCODE"))){
				myClient = doLogin(myClient);
			}
		} catch (HttpException e) {
			log.log(Priority.ERROR, "getsms error:" + e.getMessage());
		} catch (IOException e) {
			log.log(Priority.ERROR, "getsms error:" + e.getMessage());
		} finally {
			get.releaseConnection();
		}
		return null;
	}
	
	public boolean doBack(Map<String, Object> myClient,LsSms lsSms)
	{
		if(!myClient.containsKey("token"))
		{
			myClient = doLogin(myClient);
		}
		StringBuffer url = new StringBuffer(SysContants.MID_URL);
		url.append("backsms?token=");
		url.append(myClient.get("token").toString());
		url.append("&tdbh=");
		url.append(lsSms.getTdbh());
		url.append("&dxid=");
		url.append(lsSms.getDxid());
		url.append("&pch=");
		url.append(lsSms.getPch());
		url.append("&zt=");
		url.append(lsSms.getZt());
		url.append("&cljg=");
		try {
			url.append(URLEncoder.encode(lsSms.getCljg() == null ? "" : lsSms.getCljg(),SysContants.DEFAULT_CHARSET));
		} catch (UnsupportedEncodingException e) {
			log.log(Priority.ERROR, e);
		}
		url.append("&fhxx=");
		url.append(lsSms.getYyslsh());		
		log.log(Priority.INFO, "backsms->url:" + url);
		GetMethod get = new GetMethod(url.toString());
		try {
			HttpClient client = (HttpClient) myClient.get("client");
			get.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, SysContants.DEFAULT_CHARSET); 
			client.executeMethod(get);
			String body = get.getResponseBodyAsString();
			log.log(Priority.INFO, "rpbacksms->msg:" + body);
			if(get.getStatusCode() != HttpStatus.SC_OK)
			{
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				return doBack(myClient,lsSms);
			}		
			Map<String, Object> result = ReadRespUtils.readJsonResult(body);
			if("1".equals(result.get("RETCODE")))
			{					
				return true;
			}else if("3".equals(result.get("RETCODE"))){
				myClient = doLogin(myClient);
				return doBack(myClient,lsSms);
			}else{
				log.log(Priority.ERROR, result.get("RETMSG"));
				return false;
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			log.log(Priority.ERROR, "getsms error:" + e.getMessage());
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.log(Priority.ERROR, "getsms error:" + e.getMessage());
			return false;
		} finally {
			get.releaseConnection();
		}
	}
	
	public boolean doBackStatus(Map<String, Object> myClient,LsDxhk lsDxhk)
	{
		if(!myClient.containsKey("token"))
		{
			myClient = doLogin(myClient);
		}
		StringBuffer url = new StringBuffer(SysContants.MID_URL);
		url.append("backsms?token=");
		url.append(myClient.get("token").toString());
		url.append("&tdbh=");
		url.append(lsDxhk.getTdbh());
		url.append("&dxid=");
		url.append(lsDxhk.getDxid());
		url.append("&pch=");
		url.append(lsDxhk.getPch());
		url.append("&zt=");
		url.append(lsDxhk.getZt());
		url.append("&cljg=");
		try {
			url.append(URLEncoder.encode(lsDxhk.getCljg() == null ? "" : lsDxhk.getCljg(),SysContants.DEFAULT_CHARSET));
		} catch (UnsupportedEncodingException e) {
			log.log(Priority.ERROR, e);
		}
		url.append("&fhxx=");
		url.append(lsDxhk.getYyslsh());
		url.append("&dlflsh=");
		url.append(lsDxhk.getDlflsh());
		url.append("&fshm=");
		url.append(lsDxhk.getFshm());
		url.append("&fhms=");
		url.append(lsDxhk.getFhms());
		url.append("&dlid=");
		url.append(lsDxhk.getDlid());
		log.log(Priority.INFO, "backsms->url:" + url);
		GetMethod get = new GetMethod(url.toString());
		try {
			HttpClient client = (HttpClient) myClient.get("client");
			get.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, SysContants.DEFAULT_CHARSET); 
			client.executeMethod(get);
			String body = get.getResponseBodyAsString();
			log.log(Priority.INFO, "rpbacksms->msg:" + body);
			if(get.getStatusCode() != HttpStatus.SC_OK)
			{
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				return doBackStatus(myClient,lsDxhk);
			}		
			Map<String, Object> result = ReadRespUtils.readJsonResult(body);
			if("1".equals(result.get("RETCODE")))
			{					
				return true;
			}else if("3".equals(result.get("RETCODE"))){
				myClient = doLogin(myClient);
				return doBackStatus(myClient,lsDxhk);
			}else{
				log.log(Priority.ERROR, result.get("RETMSG"));
				return false;
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			log.log(Priority.ERROR, "getsms error:" + e.getMessage());
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.log(Priority.ERROR, "getsms error:" + e.getMessage());
			return false;
		} finally {
			get.releaseConnection();
		}
	}
	
	
	public int getMaxNum(Map<String, Object> myClient)
	{
		if(!myClient.containsKey("token"))
		{
			myClient = doLogin(myClient);
		}
		StringBuffer url = new StringBuffer(SysContants.MID_URL);
		url.append("getmaxnum?token=");
		url.append(myClient.get("token").toString());
		url.append("&tdbh=");
		url.append(SysContants.TDBH);
//		log.log(Priority.INFO, "getmaxnum->url:" + url);
		GetMethod get = new GetMethod(url.toString());
		try {
			HttpClient client = (HttpClient) myClient.get("client");
			get.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, SysContants.DEFAULT_CHARSET); 
			client.executeMethod(get);
			String body = get.getResponseBodyAsString();
//			log.log(Priority.INFO, "rpgetmaxnum->msg:" + body);
			if(get.getStatusCode() != HttpStatus.SC_OK)
			{
				return 0;
			}			
			Map<String, Object> result = ReadRespUtils.readJsonResult(body);
			if("1".equals(result.get("RETCODE")))
			{
				return Integer.parseInt(result.get("NUM").toString());
			}else if("3".equals(result.get("RETCODE"))){
				myClient = doLogin(myClient);
				return getMaxNum(myClient);
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			log.log(Priority.ERROR, "getsms error:" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.log(Priority.ERROR, "getsms error:" + e.getMessage());
		} finally {
			get.releaseConnection();
		}
		return 0;
	}
	
	
	public static void main(String args[])
	{
		IMidService midService = new MidServiceImpl();
//		midService.doLogin();
//		midService.getMaxNum(client);
		LsSms lsSms = new LsSms();
		lsSms.setDxid(246l);
		lsSms.setPch("201303171326351406");
		lsSms.setTdbh(SysContants.TDBH);
		lsSms.setCljg("成功");
		lsSms.setZt("1");
		lsSms.setYyslsh("123");
//		midService.getSmsList((short) 0);
	}


	
}
