package com.song.model;

import java.io.Serializable;
import java.util.Date;


import com.alibaba.fastjson.annotation.JSONField;

public class AdminUser implements Serializable{

	 // @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

	private Integer userId;

	    private Integer groupId;

	    private String username;

	    private String password;

	    private String email;

	    private String tel;

	    private Integer status;
	    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	    private Date lastLoginTime;

	    private String lastLoginIp;

	    private String ceshi;
	    
	    private AdminGroup admingroup;
	    
	    
	    

//	    private static final long serialVersionUID = 1L;

	    public AdminGroup getAdmingroup(){
	    	return admingroup;
	    }
	    public void setAdmingroup(AdminGroup admingroup){
	    	this.admingroup=admingroup;
	    }
	    
	    public Integer getUserId() {
	        return userId;
	    }

	    public void setUserId(Integer userId) {
	        this.userId = userId;
	    }

	    public Integer getGroupId() {
	        return groupId;
	    }

	    public void setGroupId(Integer groupId) {
	        this.groupId = groupId;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username == null ? null : username.trim();
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password == null ? null : password.trim();
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email == null ? null : email.trim();
	    }

	    public String getTel() {
	        return tel;
	    }

	    public void setTel(String tel) {
	        this.tel = tel == null ? null : tel.trim();
	    }

	    public Integer getStatus() {
	        return status;
	    }

	    public void setStatus(Integer status) {
	        this.status = status;
	    }

	    public Date getLastLoginTime() {
	        return lastLoginTime;
	    }

	    public void setLastLoginTime(Date lastLoginTime) {
	        this.lastLoginTime = lastLoginTime;
	    }

	    public String getLastLoginIp() {
	        return lastLoginIp;
	    }

	    public void setLastLoginIp(String lastLoginIp) {
	        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
	    }

	    public String getCeshi() {
	        return ceshi;
	    }

	    public void setCeshi(String ceshi) {
	        this.ceshi = ceshi == null ? null : ceshi.trim();
	    }
	    
}
