/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.test.test 
 * @author: dongsong   
 * @date: 2019年6月28日 下午10:25:53 
 */
package com.test.test;

import java.util.Arrays;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: StringArray.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年6月28日 下午10:25:53 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月28日     dongsong           v1.0.0               修改原因
*/
public class StringArray {

	/**   
	* @Function: StringArray.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: dongsong
	* @date: 2019年6月28日 下午10:25:53 
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String icon= "#xe60d;','#xe60a;','#xe63a;','#xe60f;','#xe64b;','#xe64c;','#xe611;','#xe631;','#xe610;','#xe696;',"
				+ "'#xe74d;','#xe632;','#xe64a;','#xe649;','#xe612;','#xe6bc;','#xe653;','#xe60b;','#xe613;','#xe62c;',"
				+ "'#xe62f;','#xe63b;','#xe63c;','#xe63d;','#xe63e;','#xe640;','#xe642;','#xe668;','#xe651;','#xe64e;',"
				+ "'#xe652;','#xe669;','#xe6a1;','#xe639;','#xe617;','#xe659;','#xe607;','#xe647;','#xe662;','#xe616;',"
				+ "'#xe618;','#xe635;','#xe654;','#xe648;','#xe683;','#xe655;','#xe672;','#xe674;','#xe679;','#xe67a;',"
				+ "'#xe681;','#xe682;','#xe689;','#xe68b;','#xe690;','#xe636;','#xe608;','#xe62b;','#xe645;','#xe609;',"
				+ "'#xe600;','#xe601;','#xe602;','#xe603;','#xe604;','#xe605;','#xe606;','#xe60e;','#xe619;','#xe61a;',"
				+ "'#xe61b;','#xe61c;','#xe61d;','#xe61e;','#xe61f;','#xe620;','#xe621;','#xe641;','#xe622;','#xe623;',"
				+ "'#xe624;','#xe625;','#xe626;','#xe627;','#xe628;','#xe629;','#xe62a;','#xe62d;','#xe62e;','#xe633;',"
				+ "'#xe634;','#xe638;','#xe63f;','#xe643;','#xe644;','#xe646;','#xe69f;','#xe630;','#xe637;";
		
		String icona="#xe60d;, #xe60a;, #xe63a;, #xe60f;, #xe64b;, #xe64c;, #xe611;, #xe631;, #xe610;, #xe696;, "
				+ "#xe74d;, #xe632;, #xe64a;, #xe649;, #xe612;, #xe6bc;, #xe653;, #xe60b;, #xe613;, #xe62c;, "
				+ "#xe62f;, #xe63b;, #xe63c;, #xe63d;, #xe63e;, #xe640;, #xe642;, #xe668;, #xe651;, #xe64e;,"
				+ " #xe652;, #xe669;, #xe6a1;, #xe639;, #xe617;, #xe659;, #xe607;, #xe647;, #xe662;, #xe616;,"
				+ " #xe618;, #xe635;, #xe654;, #xe648;, #xe683;, #xe655;, #xe672;, #xe674;, #xe679;, #xe67a;,"
				+ " #xe681;, #xe682;, #xe689;, #xe68b;, #xe690;, #xe636;, #xe608;, #xe62b;, #xe645;, #xe609;,"
				+ " #xe600;, #xe601;, #xe602;, #xe603;, #xe604;, #xe605;, #xe606;, #xe60e;, #xe619;, #xe61a;, "
				+ "#xe61b;, #xe61c;, #xe61d;, #xe61e;, #xe61f;, #xe620;, #xe621;, #xe641;, #xe622;, #xe623;, "
				+ "#xe624;, #xe625;, #xe626;, #xe627;, #xe628;, #xe629;, #xe62a;, #xe62d;, #xe62e;, #xe633;, "
				+ "#xe634;, #xe638;, #xe63f;, #xe643;, #xe644;, #xe646;, #xe69f;, #xe630;, #xe637;"; 
		
		String name="品牌','下载','邮箱','等级','退出','草稿箱','文章','商品','分类','all',"
				+ "'统计','标签','日志','新增','品牌','文章','编辑','图片','分类','07',"
				+ "'10','22','23','24','25','27','29','等级','38','上翻',"
				+ "'39','评论','勾','谷歌','网站','日志','ios浏','箭头','51','箭头',"
				+ "'箭头','分类','栏目','火狐','分类','网站','account','addfrien','eyeopen','form',"
				+ "'person','phone','set','share','paynumbe','等级','icon1','回复(1','商城','icon-pas',"
				+ "'首页','iocn-lef','iocn-rig','iocn-upw','iocn-dow','iocn-see','iocn-add','iocn-sha','悬浮-','分类sv',"
				+ "'关闭sv','加svg','减','客服','评分','时间','收藏','售后','悬浮-','我的',"
				+ "'照片','键盘','删除','收藏1','双下','提醒','消息1','通知','换一','限时',"
				+ "'商品','留言','验证','手机1','密码','设置1','时钟1','团队','内容";
		
		String namea="品牌, 下载, 邮箱, 等级, 退出, 草稿箱, 文章, 商品, 分类, all, "
				+ "统计, 标签, 日志, 新增, 品牌, 文章, 编辑, 图片, 分类, 07,"
				+ " 10, 22, 23, 24, 25, 27, 29, 等级, 38, 上翻, "
				+ "39, 评论, 勾, 谷歌, 网站, 日志, ios浏, 箭头, 51, 箭头, "
				+ "箭头, 分类, 栏目, 火狐, 分类, 网站, account, addfrien, eyeopen, form, "
				+ "person, phone, set, share, paynumbe, 等级, icon1, 回复(1, 商城, icon-pas,"
				+ " 首页, iocn-lef, iocn-rig, iocn-upw, iocn-dow, iocn-see, iocn-add, iocn-sha, 悬浮-, 分类sv,"
				+ " 关闭sv, 加svg, 减, 客服, 评分, 时间, 收藏, 售后, 悬浮-, 我的, "
				+ "照片, 键盘, 删除, 收藏1, 双下, 提醒, 消息1, 通知, 换一, 限时, "
				+ "商品, 留言, 验证, 手机1, 密码, 设置1, 时钟1, 团队, 内容";
		
		String[] arricon = icon.split("','"); // 用,分割
		System.out.println(Arrays.toString(arricon)); // [0, 1, 2, 3, 4, 5]
		System.out.println(arricon.length); // [0, 1, 2, 3, 4, 5]	
		
		String[] arricona = icona.split("','"); // 用,分割
		System.out.println(Arrays.toString(arricona)); // [0, 1, 2, 3, 4, 5]
		System.out.println(arricona.length); // [0, 1, 2, 3, 4, 5]	

		String[] arrname = name.split("','"); // 用,分割
		System.out.println(Arrays.toString(arrname)); // [0, 1, 2, 3, 4, 5]
		System.out.println(arrname.length); // [0, 1, 2, 3, 4, 5]
		
		
	
		String[] arrnamea = namea.split(","); // 用,分割
		System.out.println(Arrays.toString(arrnamea)); // [0, 1, 2, 3, 4, 5]
		System.out.println(arrnamea.length); // [0, 1, 2, 3, 4, 5]

	
	}

}
