/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.redis 
 * @author: dongsong   
 * @date: 2019年7月18日 上午11:16:48 
 */
package com.song.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: RedisJunitDemo.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年7月18日 上午11:16:48 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月18日     dongsong           v1.0.0               修改原因
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:mybatis-spring.xml")
public class RedisJunitDemo {

	@Test
	public void junita() {
		System.out.println("sdfasf");
	}
}
