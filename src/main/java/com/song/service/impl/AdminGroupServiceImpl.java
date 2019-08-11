package com.song.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.dao.AdminGroupMapper;
import com.song.model.AdminGroup;
import com.song.service.AdminGroupService;

@Service("AdminGroupService")
public class AdminGroupServiceImpl implements AdminGroupService{

	@Autowired
	AdminGroupMapper admingroupmapper;
	
	
	@Override
	public int groupselect(AdminGroup admingroup)
	{
		return admingroupmapper.selectUser(admingroup);
	}
	
	@Override
	public int groupValidate(String user)
	{
		AdminGroup group=new AdminGroup();
		group.setName(user);
		return groupselect(group);
	}
	
	
}
