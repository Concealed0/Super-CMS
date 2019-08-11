/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.test.junit 
 * @author: dongsong   
 * @date: 2019年6月16日 上午9:23:10 
 */
package com.test.junit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 
/**
 * Created by GanBaby on 2018/10/26
 *  配置spring和junit整合，junit启动时加载springIOC容器
 */
 
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration("classpath:mybatis-spring.xml")
public class BaseTest {
 
}
