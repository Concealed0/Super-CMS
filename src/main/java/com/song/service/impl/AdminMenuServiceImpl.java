/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.service.impl 
 * @author: dongsong   
 * @date: 2019年6月16日 上午10:04:48 
 */
package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.song.dao.AdminGroupMapper;
import com.song.dao.AdminMenuMapper;

import com.song.model.AdminMenu;
import com.song.service.AdminMenuService;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: AdminMenuServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年6月16日 上午10:04:48 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月16日     dongsong           v1.0.0               修改原因
*/
@Service("AdminMenuService")
public class AdminMenuServiceImpl implements AdminMenuService{
	
	@Autowired
	AdminGroupMapper groupmapper;
	@Autowired
	AdminMenuMapper menumapper;
	
	@Override
	public String selectmenupurview(int groupid) {
			
		return groupmapper.selectmenupurview(groupid);	
	}

	@Override
	public AdminMenu selectmenu(int id) {
		// TODO Auto-generated method stub
		
		return menumapper.selectmenuid(id);
	}

	@Override
	public List<AdminMenu> selectpurview(String[] menulist) {
		// TODO Auto-generated method stub
		return menumapper.selectmenupurview(menulist);
	}

	@Override
	public List<Object> supermenuonepid(int pid) {
		// TODO Auto-generated method stub
		
		return menumapper.supermenuonepid(pid);
	}

	@Override
	public List<AdminMenu> supermenutwopid(String[] menuonelist) {
		// TODO Auto-generated method stub
		return menumapper.supermenutwopid(menuonelist);
	}

	@Override
	@Cacheable(cacheNames="content",key="'adminmenu'")
	public List<AdminMenu> supermenuall() {
		// TODO Auto-generated method stub
		return menumapper.supermenuall();
	}
	@Override
	public List<AdminMenu> superapimenuall() {
		// TODO Auto-generated method stub
		return menumapper.superapimenuall();
	}

	@Override
	public List<AdminMenu> supermenupurview(String[] menulist) {
		// TODO Auto-generated method stub
		return menumapper.supermenupurview(menulist);
	}

	@Override
	public List<AdminMenu> supermenuoneapi(int id) {
		// TODO Auto-generated method stub
		AdminMenu adminmenu =new AdminMenu();
		adminmenu.setId(id);
		return menumapper.selectoneapi(adminmenu);
	}

	@Override
	public List<AdminMenu> selectarrayId(AdminMenu adminmenu) {
		// TODO Auto-generated method stub
		
		return menumapper.selectoneapi(adminmenu);
	}

	@Override
	public int superupdata(AdminMenu adminmenu) {
		// TODO Auto-generated method stub
		return menumapper.updateByPrimaryKeySelective(adminmenu);
	}

	@Override
	public int superinsert(AdminMenu adminmenu) {
		// TODO Auto-generated method stub
		return menumapper.insertSelective(adminmenu);
	}

	@Override
	public Integer superMaxsort(AdminMenu adminmenu) {
		// TODO Auto-generated method stub
		return menumapper.selectmaxsort(adminmenu);
	}

	
}
