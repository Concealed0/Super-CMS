package com.song.dao;

import java.util.List;

import com.song.model.AdminMenu;

public interface AdminMenuMapper {

	  int deleteByPrimaryKey(Integer id);

	    int insert(AdminMenu record);

	    int insertSelective(AdminMenu record);

	    AdminMenu selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(AdminMenu record);

	    int updateByPrimaryKey(AdminMenu record);
	    
	    
	    AdminMenu selectmenuid(int id);
	    
	    
	    List<AdminMenu> selectmenupurview(String[] menu);
	    
	    //结果集只能返回一个
	    AdminMenu selectmenu();
	  //结果集返回所有
	    List<AdminMenu> selectmenulist();
	    
	    
	    //三级菜单获取
	    List<Object> supermenuonepid(int pid);
	    
	    List<AdminMenu> supermenutwopid(String[] onemenu);
	    
	    List<AdminMenu> supermenuall();
	    
	    List<AdminMenu> supermenupurview(String[] onemenu);
	    
	    int updateBymenuapi(AdminMenu record);
	    
	    List<AdminMenu> superapimenuall();
	    
	    //根据adminmenu 获取所有list值
	    List<AdminMenu> selectoneapi(AdminMenu record);
	    
	    //取出pid列的最大id值
	    Integer selectmaxsort(AdminMenu record);

}
