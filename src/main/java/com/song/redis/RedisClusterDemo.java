/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.redis 
 * @author: dongsong   
 * @date: 2019年7月18日 上午10:56:25 
 */
package com.song.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.song.common.SuperCommon;
import com.song.dao.AdminLogMapper;
import com.song.dao.AdminMenuMapper;
import com.song.model.AdminLog;
import com.song.model.AdminMenu;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: RedisClusterDemo.java
* @Description: Redis集群测试
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年7月18日 上午10:56:25 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月18日     dongsong           v1.0.0               修改原因
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:mybatis-spring.xml", "classpath:rediscluster.xml" })
public class RedisClusterDemo {

	@Autowired
	private RedisTemplate<String, String> redisTemplate; 
	
	
	@Autowired
	AdminMenuMapper menumapper;
	@Autowired
	AdminLogMapper adminlogmapper;
	@Test
	public void test(){
	    System.out.println(redisTemplate);
	}
	@Test
	public void aaa() {
		System.out.println("#######sdfgadfa");
	}
	
	
	/**
     * key-value 存储
     */
    @Test
    public void test01(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        //存入key-value
        operations.set("hello","1211", 600, TimeUnit.SECONDS);
        operations.set("age","12", 600, TimeUnit.SECONDS);
        //根据key取出Value
        Object age = operations.get("age");
        System.out.println("age:"+age);
        //追加
        operations.append("age","30");
        //获得并修改
        operations.getAndSet("age","40");
        Object age1 = operations.get("age");
        System.out.println("修改后："+age1);
    }
    @Test
    public void test02(){
    	List<AdminLog> all=adminlogmapper.selectlist();
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String logstring=JSON.toJSONString(all);
        //存入key-value
        operations.set("log",logstring);
        //根据key取出Value
        Object log = operations.get("log");
        System.out.println(log);
        //使用redisTemplate将键值删除
        //redisTemplate.delete("log");
    }
    
    @Test
    public void test03(){
    	List<AdminMenu> adminmenuall=menumapper.supermenuall();
        ValueOperations<String, String> operations = redisTemplate.opsForValue(); 
        String menustring=JSON.toJSONString(adminmenuall);
        //String menuutf8=SuperCommon.toUTF8(menustring);
        //存入key-value
        operations.set("menuall",menustring, 600, TimeUnit.SECONDS);
        //根据key取出Value
        Object menu = operations.get("menuall");
        System.out.println(menu);
    }
    
    @Test
    public void demo3() {
        //从左边插入一个集合
        List<String> list = new ArrayList<String>();
        list.add("鬼泣5");
        list.add("荒野大镖客2");
        list.add("仙剑奇侠传7");
        redisTemplate.opsForList().leftPushAll("game:list", list);
    }
    
    //存储list对象
    @Test
    public void demo4() {
        //从左边插入一个集合
    	//List<AdminMenu> adminmenuall=menumapper.supermenuall();
        //存储
        //redisTemplate.opsForList().rightPushAll("oowwoo", adminmenuall);
    }

}
