/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.redis 
 * @author: dongsong   
 * @date: 2019年7月18日 下午2:12:15 
 */
package com.song.redis;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: RedisClusterOneDemo.java
* @Description: 单节点测试
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年7月18日 下午2:12:15 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月18日     dongsong           v1.0.0               修改原因
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redisclusterone.xml")
public class RedisClusterOneDemo {

	@Autowired
	private RedisTemplate redisTemplate; 
	
	@Test
	public void test(){
	    System.out.println(redisTemplate);
	}
	@Test
	public void aaa() {
		System.out.println("sdfgadfa");
	}
	
	  /**
     * key-value 存储
     */
    @Test
    public void test01(){
        ValueOperations operations = redisTemplate.opsForValue();
        String key = "name";
        //存入key-value
        operations.set("name","zhangsan");
        //根据key取出Value
        Object name = operations.get("name");
        System.out.println("name:"+name);
        //追加
        operations.append("name","is man");
        //获得并修改
        operations.getAndSet("name","zhangsan-1");
        Object name1 = operations.get("name");
        System.out.println("修改后："+name1);

    }
    @Test
    public void test02(){
        String key = "name-1";
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);
        //添加value
        boundValueOperations.set("张三");
        //获取value值
        Object value = boundValueOperations.get();
        System.out.println("value:"+value);
        //设置过期时间
        boundValueOperations.set("张三-1",1000);
        //获得并修改
        boundValueOperations.getAndSet("张三-2");
        Object andSet = boundValueOperations.get();
        System.out.println("获得并修改："+andSet);
        //追加
        boundValueOperations.append("是一个好人！！");
        Object append = boundValueOperations.get();
        System.out.println("追加："+append);
    }


    /**
     * list集合
     */
    @Test
    public void test03(){
        ListOperations listOperations = redisTemplate.opsForList();
        String key = "springData";
        BoundListOperations boundListOperations = redisTemplate.boundListOps(key);

        //push键值对
        listOperations.leftPush(key,"aaa");
        listOperations.leftPush(key,"bbb");

        listOperations.index(1,1000);

        //pop值
        Object leftPop = listOperations.leftPop(key);
        System.out.println(leftPop);

        listOperations.set(key,100,"测试数据");


        ListOperations<String,Integer> listOperations1 = redisTemplate.opsForList();
        listOperations1.leftPush(key,222);
        listOperations1.leftPush(key,333);

    }

    /**
     * Set集合,类似于
     *
     *  Map<Object, Set<Object>> map = new HashMap<Object, Set<Object>>();
     *  Set<Object> set = new HashSet<Object>();
     */
    @Test
    public void test04(){
        SetOperations setOperations = redisTemplate.opsForSet();

        /**
         * 添加，类似于
         *
         *  Map<Object, Set<Object>> map = new HashMap<Object, Set<Object>>();
         *  Set<Object> set = new HashSet<Object>();
         *  map.put(key,set);
         *  Set<Object> set = map.get(key);
         *  set.add(value);
         */
        setOperations.add("springData","redis");
        //获取
        Set springData = setOperations.members("springData");

        //获取两个key的value(Set)交集
        setOperations.intersect("springData","SpringData1");

        //获取两个key的value(Set)补集
        setOperations.difference("springData","SpringData1");

        //获取两个key的value(Set)并集
        setOperations.union("springData","SpringData1");

        String key = "springData";
        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps(key);
        //和SetOperations操作类似
    }

    /**
     * ZSet集合 和Set类似
     */
    @Test
    public void test05(){
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

        /**
         * zSetOperations.add(key,value,score);
         * key  键
         * value    值
         * score    Set中的权重
         *  以后这个Set可以根据权重进行排序
         */
        zSetOperations.add("springData","redis",1);

        String key = "springData";
        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps(key);
        //和 ZSetOperations 操作类似
    }
    /**
     * Hash
     */
    @Test
    public void test06(){
        HashOperations hashOperations = redisTemplate.opsForHash();

        String key = "springData";
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(key);
    }
}

