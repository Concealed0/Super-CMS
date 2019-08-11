package com.song.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.song.dao.AdminLogMapper;
import com.song.model.AdminLog;
import com.song.service.AdminLogService;

@Service("AdminLogService")
public class AdminLogServiceImpl implements AdminLogService{

	@Autowired
	AdminLogMapper adminlogmapper;
	
	@Override
	public int logInsert(AdminLog adminlog) {
		return adminlogmapper.insertSelective(adminlog);
	}
	
	@Override
	public int adminlogValidate(int group,String user, String ip,String app,String con) {
		AdminLog adminlog =new AdminLog();
		adminlog.setLogGroup(group);
		adminlog.setLogUser(user);
		adminlog.setLogIp(ip);
		adminlog.setLogApp(app);
		adminlog.setLogCon(con);   
		//直接在adminlogValidate方法中将时间设置到实体中，同时在实体中将date时间戳转换为了datetime，使用了DateTimeFormat
		Date date=new Date();
        adminlog.setLogTime(date);
        System.out.println(date);
		int log =logInsert(adminlog);
	    return log;
	}

	@Override
	@Cacheable(cacheNames="content",key="'adminlog'")
	public List<AdminLog> selectall() {
		// TODO Auto-generated method stub
		return adminlogmapper.selectlist();
	}

	

	
}

