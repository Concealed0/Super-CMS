package com.song.dao;

import com.song.model.AdminGroup;

public interface AdminGroupMapper {

	 int deleteByPrimaryKey(Integer groupId);

	    int insert(AdminGroup record);

	    int insertSelective(AdminGroup record);

	    AdminGroup selectByPrimaryKey(Integer groupId);

	    int updateByPrimaryKeySelective(AdminGroup record);

	    int updateByPrimaryKey(AdminGroup record);
	    
	    
	    int selectUser(AdminGroup admingroup);
	    
	    String selectmenupurview(int groupid);
	    
}
