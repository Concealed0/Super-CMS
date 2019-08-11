package com.song.dao;

import java.util.List;

import com.song.model.AdminUser;

public interface AdminUserMapper {

	 int deleteByPrimaryKey(Integer userId);

	    int insert(AdminUser record);

	    int insertSelective(AdminUser record);

	    AdminUser selectByPrimaryKey(Integer userId);

	    int updateByPrimaryKeySelective(AdminUser record);

	    int updateByPrimaryKey(AdminUser record);
	    
	    
	    AdminUser userlist001();
	    
	    AdminUser select(AdminUser user);
	    
	    public int selectGroup(AdminUser user);
	    
	    
	    List<AdminUser> selectUser(String user);
	    
	    List<AdminUser> selectAll();
	    
	    int selectstatus(String name);
	    List<AdminUser> selectuserid(int userId);
	    

	    
	    
}
