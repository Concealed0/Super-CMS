/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.service 
 * @author: dongsong   
 * @date: 2019年6月16日 上午10:02:38 
 */
package com.song.service;

import java.util.List;

import com.song.model.AdminMenu;

public interface AdminMenuService {

	public String selectmenupurview(int groupId);
	
	
	public AdminMenu selectmenu(int id);
	
	
	public List<AdminMenu> selectpurview(String[] menulist);
	
	
	public List<Object> supermenuonepid(int pid);
	
	
	public List<AdminMenu> supermenutwopid(String[] menuonelist);
	
	public List<AdminMenu> supermenuall();
	
	public List<AdminMenu> supermenupurview(String[] menulist);
	
	public List<AdminMenu> superapimenuall();
	
	public List<AdminMenu> supermenuoneapi(int id);
	//通过adminmenu获取list实体值
	public List<AdminMenu> selectarrayId(AdminMenu adminmenu);

	//通过id找pid,然后将这一字段更新
	public int superupdata(AdminMenu adminmenu);
	
	//通过pid插入实体
	public int superinsert(AdminMenu adminmenu);
	
	//通过pid插入实体获取到sort的最大值
	public Integer superMaxsort(AdminMenu adminmenu);
}
