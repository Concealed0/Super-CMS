/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.test.junit 
 * @author: dongsong   
 * @date: 2019年6月16日 上午9:32:49 
 */
package com.test.junit;

import org.junit.Test;

import com.song.model.AdminMenu;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: Demo.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年6月16日 上午9:32:49 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月16日     dongsong           v1.0.0               修改原因
*/
public class Demo extends  BaseTest{

	@Test
	public void show() {
		AdminMenu admin =new AdminMenu();
		System.out.println(admin.getAct());
		System.out.println("11111111");
	}
}
