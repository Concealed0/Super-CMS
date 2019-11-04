package com.song.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.dao.AdminUserMapper;
import com.song.dao.UserGroupMapper;
import com.song.model.AdminUser;
import com.song.service.AdminUserService;

@Service("AdminUserService")
public class AdminUserServiceImpl implements AdminUserService{
	
	
	@Autowired
	private AdminUserMapper adminusermapper;
	@Autowired
	private UserGroupMapper usergroupmapper;
	
	@Override
	public AdminUser selectUser(AdminUser user) {
		return adminusermapper.select(user);
	}
	
	@Override
	
	public AdminUser loginValidate(String username, String password) {
		AdminUser user = new AdminUser();
		user.setUsername(username);
		user.setPassword(password);
		AdminUser usera = selectUser(user);
		//if(user != null) user.setUserType("admin");
		return usera;
	}

	@Override
	public int selectGroup(AdminUser user) {
		return adminusermapper.selectGroup(user);
	}

	@Override
	public int groupValidate(String username) {
		AdminUser user = new AdminUser();
		user.setUsername(username);
		return selectGroup(user);
	}

	@Override
	public List<AdminUser> selectUsername(String user) {
		// TODO Auto-generated method stub
		return adminusermapper.selectUser(user);
	}

	@Override
	public List<AdminUser> selectpowerAll() {
		// TODO Auto-generated method stub
		return adminusermapper.selectAll();
	}

	@Override
	public int updatapowerapi(int userId, int status) {
		// TODO Auto-generated method stub
		AdminUser adminuser=new AdminUser();
		adminuser.setUserId(userId);
		adminuser.setStatus(status);
		//adminusermapper.updateByPrimaryKeySelective(adminuser);
		return adminusermapper.updateByPrimaryKeySelective(adminuser);
	}

	@Override
	public int selectStatus(String username) {
		// TODO Auto-generated method stub
		return adminusermapper.selectstatus(username);
	}

	@Override
	public int powerdel(int id) {
		// TODO Auto-generated method stub
		return adminusermapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<AdminUser> poweruserid(int userid) {
		// TODO Auto-generated method stub
		return adminusermapper.selectuserid(userid);
	}

	@Override
	public List<AdminUser> selecttwo() {
		// TODO Auto-generated method stub
		return usergroupmapper.selusergroup();
	}

	@Override
	public List<AdminUser> usergroup(int userid) {
		// TODO Auto-generated method stub
		return usergroupmapper.usergroup(userid);
	}

	@Override
	public int powerupdata(Integer group_id,String username, String nicename, String email, String le_tel, String password,
			Integer status, Integer user_id) {
		// TODO Auto-generated method stub
		AdminUser adminuser=new AdminUser();
		adminuser.setGroupId(group_id);
		adminuser.setUsername(username);
		adminuser.setCeshi(nicename);
		adminuser.setEmail(email);
		adminuser.setTel(le_tel);
		adminuser.setPassword(password);
		adminuser.setStatus(status);
		adminuser.setUserId(user_id);
		Date time=new Date();
		adminuser.setLastLoginTime(time);
		return adminusermapper.updateByPrimaryKeySelective(adminuser);
	}

	@Override
	public int powerinsert(Integer group_id, String username, String nicename, String email, String le_tel,
			String password, Integer status, Integer user_id) {

		AdminUser adminuser=new AdminUser();
		adminuser.setGroupId(group_id);
		adminuser.setUsername(username);
		adminuser.setCeshi(nicename);
		adminuser.setEmail(email);
		adminuser.setTel(le_tel);
		adminuser.setPassword(password);
		adminuser.setStatus(status);
		Date time=new Date();
		adminuser.setLastLoginTime(time);
		return adminusermapper.insertSelective(adminuser);		
	}
}
