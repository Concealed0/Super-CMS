/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.dao 
 * @author: dongsong   
 * @date: 2019年6月24日 下午6:50:59 
 */
package com.song.dao;

import java.util.List;

import com.song.model.AdminUser;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: UserGroupMapper.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年6月24日 下午6:50:59 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月24日     dongsong           v1.0.0               修改原因
*/
public interface UserGroupMapper {

	List<AdminUser> selusergroup();
	List<AdminUser> usergroup(int userId);
}
