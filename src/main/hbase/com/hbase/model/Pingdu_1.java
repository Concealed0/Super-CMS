/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.hbase.model 
 * @author: dongsong   
 * @date: 2019年7月5日 下午9:36:51 
 */
package com.hbase.model;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: Pingdu_1.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年7月5日 下午9:36:51 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月5日     dongsong           v1.0.0               修改原因
*/
public class Pingdu_1 {
	
	private String id;
	private String device_pid;
	private String property;
	private String value;
	private String datetime;
	
	public Pingdu_1(String id, String device_pid, String property, String value, String datetime) {
		
		this.id = id;
		this.device_pid = device_pid;
		this.property = property;
		this.value = value;
		this.datetime = datetime;
	}

	public String getId() {
		return id;
	}

	public String getDevice_pid() {
		return device_pid;
	}

	public String getProperty() {
		return property;
	}

	public String getValue() {
		return value;
	}

	public String getDatetime() {
		return datetime;
	}

	@Override
	public String toString() {
		return "Pingdu_1 [id=" + id + ", device_pid=" + device_pid + ", property=" + property + ", value=" + value
				+ ", datetime=" + datetime + "]";
	}

}