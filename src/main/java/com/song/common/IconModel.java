/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.common 
 * @author: dongsong   
 * @date: 2019年6月28日 下午9:36:03 
 */
package com.song.common;

/**
 * Copyright: Copyright (c) 2019 LanRu-Caifu
 * 
 * @ClassName: IconModel.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: dongsong
 * @date: 2019年6月28日 下午9:36:03
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年6月28日
 *        dongsong v1.0.0 修改原因
 */
public class IconModel {

	private String iconfont;
	private String name;

	public String getIconfont() {
		return iconfont;
	}

	public void setIconfont(String iconfont) {
		this.iconfont = iconfont == null ? null : iconfont.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}
