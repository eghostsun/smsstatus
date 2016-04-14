package com.slf.engine.common;

import com.slf.common.util.ReadProperties;



public class SysContants {

	public static final String CONFIG_FILE_NAME = "conf/config.properties";
	
	public static final int STATUS_THREADNUM = Integer.parseInt(ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("status.threadnum"));
	
	public static final int BACK_THREADNUM = Integer.parseInt(ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("back.threadnum"));
	
	
	public static final String DEFAULT_CHARSET = "utf-8";
	
	public static final String ORACLE_TABLEUSER = ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("ORACLE.TABLEUSER");
	
	public static final int MID_GETNUM = Integer.parseInt(ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("mid.getnum"));
	public static final String TDBH = ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("tdbh");
	public static final String MID_URL = ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("mid.url");
	public static final String MID_NAME = ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("mid.name");
	public static final String MID_PWD= ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("mid.pwd");
	public static final String MID_KEY = ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("mid.key");
	public static final String HTTPSQS_URL = ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("httpsqs.url");
	public static final String HTTPSQS_STATUS = ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("httpsqs.status");
	public static final String HTTPSQS_FAIL = ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("httpsqs.fail");
	public static final String HTTPSQS_AUTH = ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("httpsqs.auth");
	
	public static final int NOTICE_MAXNUM = Integer.parseInt(ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("notice.maxnum"));
	public static final int NOTICE_TIMEOUT = Integer.parseInt(ReadProperties.getProp(CONFIG_FILE_NAME).getProperty("notice.timeout"));
}
