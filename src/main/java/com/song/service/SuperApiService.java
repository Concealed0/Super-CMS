/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.service 
 * @author: dongsong   
 * @date: 2019年6月27日 下午4:59:02 
 */
package com.song.service;

import java.util.List;

import com.song.model.AdminMenu;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: SuperAPIService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年6月27日 下午4:59:02 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月27日     dongsong           v1.0.0               修改原因
*/
public interface SuperApiService {

	//用于更新admin_menu表中的status字段值
	int updatamenuapi(int userId, int status);
	
	int deladminmenu(int id);
	
	int delusermenu(int id);
	
	//用于查找admin_menu表中的所有sort字段值
	List<AdminMenu> fintsortallapi(AdminMenu adminmenu);
	
	//用于更新admin_menu表中的sort字段值
	int updatamenusort(int userId, int sort);
}
