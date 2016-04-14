package com.slf.engine.bo;

import com.slf.engine.base.BaseObj;


public class LsDxhk extends BaseObj {
	private Long dxhkid;
	private String tdbh;
	private String dxid;
	private String pch;
	private String zt;
	private String cljg;
	private String yyslsh;
	
	private String fsbz;
	private String dlflsh;
	private String fshm;
	private Long dlid;
	private String fhms;
	
	private String fhrq;
	private String fhsj;
	
	
	public String getFhrq() {
		return fhrq;
	}
	public void setFhrq(String fhrq) {
		this.fhrq = fhrq;
	}
	public String getFhsj() {
		return fhsj;
	}
	public void setFhsj(String fhsj) {
		this.fhsj = fhsj;
	}
	public String getFhms() {
		return fhms;
	}
	public void setFhms(String fhms) {
		this.fhms = fhms;
	}
	public String getFsbz() {
		return fsbz;
	}
	public void setFsbz(String fsbz) {
		this.fsbz = fsbz;
	}
	public String getDlflsh() {
		return dlflsh;
	}
	public void setDlflsh(String dlflsh) {
		this.dlflsh = dlflsh;
	}
	public String getFshm() {
		return fshm;
	}
	public void setFshm(String fshm) {
		this.fshm = fshm;
	}
	public Long getDlid() {
		return dlid;
	}
	public void setDlid(Long dlid) {
		this.dlid = dlid;
	}
	public Long getDxhkid() {
		return dxhkid;
	}
	public void setDxhkid(Long dxhkid) {
		this.dxhkid = dxhkid;
	}
	public String getTdbh() {
		return tdbh;
	}
	public void setTdbh(String tdbh) {
		this.tdbh = tdbh == null ? null : tdbh.trim();
	}
	public String getDxid() {
		return dxid;
	}
	public void setDxid(String dxid) {
		this.dxid = dxid;
	}
	public String getPch() {
		return pch;
	}
	public void setPch(String pch) {
		this.pch = pch== null ? null : pch.trim();
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt== null ? null : zt.trim();
	}
	public String getCljg() {
		return cljg;
	}
	public void setCljg(String cljg) {
		this.cljg = cljg== null ? null : cljg.trim();
	}
	public String getYyslsh() {
		return yyslsh;
	}
	public void setYyslsh(String yyslsh) {
		this.yyslsh = yyslsh == null ? null : yyslsh.trim();
	}
	
}
