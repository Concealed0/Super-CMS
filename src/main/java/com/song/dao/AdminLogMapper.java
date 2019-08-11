package com.song.dao;

import java.util.List;

import com.song.model.AdminLog;

public interface AdminLogMapper {
	int deleteByPrimaryKey(Integer logId);

    int insert(AdminLog record);

    int insertSelective(AdminLog record);

    AdminLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(AdminLog record);

    int updateByPrimaryKey(AdminLog record);
    
    
    List<AdminLog> selectlist();
}
