/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.hbase.service 
 * @author: dongsong   
 * @date: 2019年7月5日 下午9:38:33 
 */
package com.hbase.service;

import java.util.List;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Service;

import com.hbase.dao.Pingdu_1Dao;
import com.hbase.model.Pingdu_1;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: Pingdu_1Service.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年7月5日 下午9:38:33 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月5日     dongsong           v1.0.0               修改原因
*/
@Service
public class Pingdu_1Service implements Pingdu_1Dao{

	@Autowired
	private HbaseTemplate hbaseTemplate;

	private String tableName = "506db:pingdu_1";
	public static byte[] INFO = Bytes.toBytes("info");

	private byte[] id = Bytes.toBytes("id");
	private byte[] device_pid = Bytes.toBytes("device_pid");
	private byte[] property = Bytes.toBytes("property");
	private byte[] value = Bytes.toBytes("value");
	private byte[] datetime = Bytes.toBytes("datetime");
	
	/**
	 * 	获取表中所有数据
	 */
	public List<Pingdu_1> finaAll(){
		
		return hbaseTemplate.find(tableName,"info",new RowMapper<Pingdu_1>() {

			@Override
			public Pingdu_1 mapRow(Result result, int rowNum) throws Exception {				
				return new Pingdu_1(Bytes.toString(result.getValue(INFO, id)),
						Bytes.toString(result.getValue(INFO, device_pid)),
						Bytes.toString(result.getValue(INFO, property)),
						Bytes.toString(result.getValue(INFO, value)),
						Bytes.toString(result.getValue(INFO, datetime)));					
			}
			
		});
	}

}