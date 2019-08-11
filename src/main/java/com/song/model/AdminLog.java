package com.song.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class AdminLog implements Serializable {

	 // @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

	private Integer logId;

	    private Integer logGroup;

	    private String logUser;

	    private String logIp;
	    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	    private Date logTime;

	    private String logApp;

	    private String logCon;

	    private Integer logStatus;

//	    private static final long serialVersionUID = 1L;

	    public Integer getLogId() {
	        return logId;
	    }

	    public void setLogId(Integer logId) {
	        this.logId = logId;
	    }

	    public Integer getLogGroup() {
	        return logGroup;
	    }

	    public void setLogGroup(Integer logGroup) {
	        this.logGroup = logGroup;
	    }

	    public String getLogUser() {
	        return logUser;
	    }

	    public void setLogUser(String logUser) {
	        this.logUser = logUser == null ? null : logUser.trim();
	    }

	    public String getLogIp() {
	        return logIp;
	    }

	    public void setLogIp(String logIp) {
	        this.logIp = logIp == null ? null : logIp.trim();
	    }

	    public Date getLogTime() {
	        return logTime;
	    }

	    public void setLogTime(Date logTime) {
	        this.logTime = logTime;
	    }

	    public String getLogApp() {
	        return logApp;
	    }

	    public void setLogApp(String logApp) {
	        this.logApp = logApp == null ? null : logApp.trim();
	    }

	    public String getLogCon() {
	        return logCon;
	    }

	    public void setLogCon(String logCon) {
	        this.logCon = logCon == null ? null : logCon.trim();
	    }

	    public Integer getLogStatus() {
	        return logStatus;
	    }

	    public void setLogStatus(Integer logStatus) {
	        this.logStatus = logStatus;
	    }
}
