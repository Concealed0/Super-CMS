package com.song.service;

import com.song.model.AdminGroup;

public interface AdminGroupService {

	public int groupselect(AdminGroup admingroup);
	
	
	public int groupValidate(String user);
}
