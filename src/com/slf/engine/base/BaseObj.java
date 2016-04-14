package com.slf.engine.base;

import java.io.Serializable;

import com.slf.engine.common.SysContants;


public class BaseObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tableUser = SysContants.ORACLE_TABLEUSER;

	public String getTableUser() {
		return tableUser;
	}

	public void setTableUser(String tableUser) {
		this.tableUser = tableUser;
	}

}
