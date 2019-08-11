/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.service.impl 
 * @author: dongsong   
 * @date: 2019年6月27日 下午5:00:11 
 */
package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.dao.AdminMenuMapper;
import com.song.dao.AdminUserMapper;
import com.song.model.AdminMenu;

import com.song.service.SuperApiService;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: SuperAPIServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年6月27日 下午5:00:11 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月27日     dongsong           v1.0.0               修改原因
*/
@Service("SuperApiService")
public class SuperApiServiceImpl implements SuperApiService {

	@Autowired
	AdminMenuMapper adminmenumapper;
	@Autowired
	AdminUserMapper adminusermapper;
	
	@Override
	public int updatamenuapi(int userId, int status) {
		// TODO Auto-generated method stub
		AdminMenu adminmenu=new AdminMenu();
		adminmenu.setId(userId);
		adminmenu.setStatus(status);
		//adminusermapper.updateByPrimaryKeySelective(adminuser);
		return adminmenumapper.updateByPrimaryKeySelective(adminmenu);
	}
	@Override
	public int deladminmenu(int id) {
		// TODO Auto-generated method stub
		return adminmenumapper.deleteByPrimaryKey(id);
	}
	@Override
	public int delusermenu(int id) {
		// TODO Auto-generated method stub
		return adminusermapper.deleteByPrimaryKey(id);
	}
	//通过id获取到所有list实体值
	@Override
	public List<AdminMenu> fintsortallapi(AdminMenu adminmenu) {
		// TODO Auto-generated method stub
		return adminmenumapper.selectoneapi(adminmenu);
	}
	@Override
	public int updatamenusort(int userId, int sort) {
		// TODO Auto-generated method stub
		AdminMenu adminmenu=new AdminMenu();
		adminmenu.setId(userId);
		adminmenu.setSort(sort);
		//adminusermapper.updateByPrimaryKeySelective(adminuser);
		return adminmenumapper.updateByPrimaryKeySelective(adminmenu);
	}
}
