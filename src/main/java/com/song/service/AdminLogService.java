package com.song.service;

import java.util.List;

import com.song.model.AdminLog;

public interface AdminLogService {

	/**   
	* @Function: AdminLog.java
	* @Description: 该函数的功能描述
	*
	* @param:user 
	* @return：登陆用户名，返回用户存储状态
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: dongsong
	* @date: 2019年6月11日 上午10:47:39 
	*/
	public int adminlogValidate(int group,String user, String ip,String app,String con);
	
	
	
	public int logInsert(AdminLog adminlog);
	
	
	public List<AdminLog> selectall();
}
