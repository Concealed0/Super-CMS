/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.controller 
 * @author: dongsong   
 * @date: 2019年7月5日 下午9:35:32 
 */
package com.hbase.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hbase.model.Pingdu_1;
import com.hbase.service.Pingdu_1Service;
import com.test.junit.BaseTest;


/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: Pingdu_1Controller.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年7月5日 下午9:35:32 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月5日     dongsong           v1.0.0               修改原因
*/
@Controller
@RequestMapping("/HbaseCon")
public class Pingdu_1Controller extends  BaseTest {
	@Autowired
	private Pingdu_1Service pingdu_1Service;
	
	@ResponseBody
	@RequestMapping(value="/alldata",produces = "application/json;charset=UTF-8")
    public String alldata() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String timedata=dateFormat.format(date).toString();;
		List<Pingdu_1> pdList = pingdu_1Service.finaAll();
		List<Pingdu_1> resultList = new ArrayList<Pingdu_1>();
		for(Pingdu_1 pd1 : pdList) {	
			if(pd1.toString().contains(timedata)) {								
				resultList.add(pd1);	
			}	
		}
		System.out.println(pdList.size());	
		return JSON.toJSONString(resultList);
	}
	
	
	@Test
	public void Pingdu_1Sizer() {
		
		List<Pingdu_1> pdList = pingdu_1Service.finaAll();
		System.out.println(pdList.size());	
//		for(Pingdu_1 pd1 : pdList) {
//			System.out.println(pd1);	
//
////			if(pd1.toString().contains(new Current().todayDate())) {
////				
////				List<Pingdu_1> resultList = new ArrayList<Pingdu_1>();
////				
////				resultList.add(pd1);
////				System.out.println(resultList);	
////			}	
//		}
//		System.out.println(pdList.size());	

	}

}
