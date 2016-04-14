package com.slf.engine.bo;

import java.util.Date;

import com.slf.engine.base.BaseObj;



public class LsSms extends BaseObj {
	

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column LS_SMS.FSHM
     *
     * @ibatorgenerated Thu Feb 28 19:18:27 CST 2013
     */
    private String fshm;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column LS_SMS.TDBH
     *
     * @ibatorgenerated Thu Feb 28 19:18:27 CST 2013
     */
    private String tdbh;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column LS_SMS.DXID
     *
     * @ibatorgenerated Thu Feb 28 19:18:27 CST 2013
     */
    private Long dxid;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column LS_SMS.FSCS
     *
     * @ibatorgenerated Thu Feb 28 19:18:27 CST 2013
     */
    private int fscs = 0;
    
    private String fssj;

    public String getFssj() {
		return fssj;
	}

	public void setFssj(String fssj) {
		this.fssj = fssj;
	}

    private String zt;
    
    private String pch;
    
    private String cljg;
    
    private String yyslsh;
    
    private String dlflsh;    
    

	public String getDlflsh() {
		return dlflsh;
	}

	public void setDlflsh(String dlflsh) {
		this.dlflsh = dlflsh;
	}

	public String getYyslsh() {
		return yyslsh;
	}

	public void setYyslsh(String yyslsh) {
		this.yyslsh = yyslsh;
	}

	public String getCljg() {
		return cljg;
	}

	public void setCljg(String cljg) {
		this.cljg = cljg;
	}

	public String getPch() {
		return pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}


    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column LS_SMS.FSHM
     *
     * @return the value of LS_SMS.FSHM
     *
     * @ibatorgenerated Thu Feb 28 19:18:27 CST 2013
     */
    public String getFshm() {
        return fshm;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column LS_SMS.FSHM
     *
     * @param fshm the value for LS_SMS.FSHM
     *
     * @ibatorgenerated Thu Feb 28 19:18:27 CST 2013
     */
    public void setFshm(String fshm) {
        this.fshm = fshm == null ? null : fshm.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column LS_SMS.TDBH
     *
     * @return the value of LS_SMS.TDBH
     *
     * @ibatorgenerated Thu Feb 28 19:18:27 CST 2013
     */
    public String getTdbh() {
        return tdbh;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column LS_SMS.TDBH
     *
     * @param tdbh the value for LS_SMS.TDBH
     *
     * @ibatorgenerated Thu Feb 28 19:18:27 CST 2013
     */
    public void setTdbh(String tdbh) {
        this.tdbh = tdbh == null ? null : tdbh.trim();
    }


    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column LS_SMS.DXID
     *
     * @return the value of LS_SMS.DXID
     *
     * @ibatorgenerated Thu Feb 28 19:18:27 CST 2013
     */
    public Long getDxid() {
        return dxid;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column LS_SMS.DXID
     *
     * @param dxid the value for LS_SMS.DXID
     *
     * @ibatorgenerated Thu Feb 28 19:18:27 CST 2013
     */
    public void setDxid(Long dxid) {
        this.dxid = dxid;
    }


    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column LS_SMS.FSCS
     *
     * @return the value of LS_SMS.FSCS
     *
     * @ibatorgenerated Thu Feb 28 19:18:27 CST 2013
     */
    public int getFscs() {
        return fscs;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column LS_SMS.FSCS
     *
     * @param fscs the value for LS_SMS.FSCS
     *
     * @ibatorgenerated Thu Feb 28 19:18:27 CST 2013
     */
    public void setFscs(int fscs) {
        this.fscs = fscs;
    }

}