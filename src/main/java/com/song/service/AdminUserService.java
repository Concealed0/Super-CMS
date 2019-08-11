package com.song.service;

import java.util.List;

import com.song.model.AdminUser;

public interface AdminUserService {

	public AdminUser selectUser(AdminUser user);
	
	public int selectGroup(AdminUser user);
	
	/**   
	* @Function: UserService.java
	* @Description: 将参数传递到数据库进行查询操作
	*
	* @param:username  password
	* @return：登陆账号         登陆密码
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: dongsong
	* @date: 2019年5月28日 下午1:12:49 
	*/
	public AdminUser loginValidate(String username, String password);
	
	
	public int groupValidate(String username);
	
	List<AdminUser> selectUsername(String user);
	
	List<AdminUser> selectpowerAll();
	
	int updatapowerapi(int userId, int status);
	
	int selectStatus(String username);
	
	int powerdel(int id);
	
	List<AdminUser> poweruserid(int userid);

	List<AdminUser> selecttwo();
	
	List<AdminUser> usergroup(int userid);
	//更新原先的表数据
	int powerupdata(Integer group_id,String username,String nicename,String email,String le_tel,String password,Integer status,Integer user_id);
	
	int powerinsert(Integer group_id,String username,String nicename,String email,String le_tel,String password,Integer status,Integer user_id);

}
