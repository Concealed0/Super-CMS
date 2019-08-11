package com.song.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class StrUtil {
	
	/**
	 * 为配合前端框架table的数据格式
	 */
	public static final String RETURN_JONS_PRE_STR = "{\"code\":0,\"msg\":\"\",\"count\":";
	public static final String RETURN_JONS_MID_STR = ",\"data\":";
	public static final String RETURN_JONS_END_STR = "}";
	
	
	/**
	 * 反馈到前端的
	 */
	public static final String RESULT_TRUE = "true";
	public static final String RESULT_FALSE = "false";
	public static final String CODE_ERROR = "code_error";
	public static final String RESULT_STATUS = "status_error";
	/**
	 * 用户类型
	 */
	public static final String USER = "user";
	public static final String ADMIN = "admin";
	public static final String TEACHER = "teacher";
	public static final String STUDENT = "student";
	
	public static final String VERIFY_CODE = "verifyCode";
	String icon= "#xe60d;','#xe60a;','#xe63a;','#xe60f;','#xe64b;','#xe64c;','#xe611;','#xe631;','#xe610;','#xe696;','#xe74d;','#xe632;','#xe64a;','#xe649;','#xe612;','#xe6bc;','#xe653;','#xe60b;','#xe613;','#xe62c;','#xe62f;','#xe63b;','#xe63c;','#xe63d;','#xe63e;','#xe640;','#xe642;','#xe668;','#xe651;','#xe64e;','#xe652;','#xe669;','#xe6a1;','#xe639;','#xe617;','#xe659;','#xe607;','#xe647;','#xe662;','#xe616;','#xe618;','#xe635;','#xe654;','#xe648;','#xe683;','#xe655;','#xe672;','#xe674;','#xe679;','#xe67a;','#xe681;','#xe682;','#xe689;','#xe68b;','#xe690;','#xe636;','#xe608;','#xe62b;','#xe645;','#xe609;','#xe600;','#xe601;','#xe602;','#xe603;','#xe604;','#xe605;','#xe606;','#xe60e;','#xe619;','#xe61a;','#xe61b;','#xe61c;','#xe61d;','#xe61e;','#xe61f;','#xe620;','#xe621;','#xe641;','#xe622;','#xe623;','#xe624;','#xe625;','#xe626;','#xe627;','#xe628;','#xe629;','#xe638;','#xe63f;','#xe643;','#xe644;','#xe646;','#xe69f;','#xe630;','#xe637;";
	String name="品牌','下载','邮箱','等级','退出','草稿箱','文章','商品','分类','all','统计','标签','日志','新增','品牌','文章','编辑','图片','分类','07','10','22','23','24','25','27','29','等级','38','上翻','39','评论','勾','谷歌','网站','日志','ios浏','箭头','51','箭头','箭头','分类','栏目','火狐','分类','网站','account','addfrien','eyeopen','form','person','phone','set','share','paynumbe','等级','icon1','回复(1','商城','icon-pas','首页','iocn-lef','iocn-rig','iocn-upw','iocn-dow','iocn-see','iocn-add','iocn-sha','悬浮-','分类sv','关闭sv','加svg','减','客服','评分','时间','收藏','售后','悬浮-','我的','照片','键盘','删除','收藏1','双下','提醒','消息1','通知','换一','限时','商品','留言','验证','手机1','密码','设置1','时钟1','团队','内容";
	//HashMap实现相同key存入数据后不被覆盖
	
	public static final String icon() {
		String icon="#xe60d;, #xe60a;, #xe63a;, #xe60f;, #xe64b;, #xe64c;, #xe611;, #xe631;, #xe610;, #xe696;, "
				+ "#xe74d;, #xe632;, #xe64a;, #xe649;, #xe612;, #xe6bc;, #xe653;, #xe60b;, #xe613;, #xe62c;, "
				+ "#xe62f;, #xe63b;, #xe63c;, #xe63d;, #xe63e;, #xe640;, #xe642;, #xe668;, #xe651;, #xe64e;,"
				+ " #xe652;, #xe669;, #xe6a1;, #xe639;, #xe617;, #xe659;, #xe607;, #xe647;, #xe662;, #xe616;,"
				+ " #xe618;, #xe635;, #xe654;, #xe648;, #xe683;, #xe655;, #xe672;, #xe674;, #xe679;, #xe67a;,"
				+ " #xe681;, #xe682;, #xe689;, #xe68b;, #xe690;, #xe636;, #xe608;, #xe62b;, #xe645;, #xe609;,"
				+ " #xe600;, #xe601;, #xe602;, #xe603;, #xe604;, #xe605;, #xe606;, #xe60e;, #xe619;, #xe61a;, "
				+ "#xe61b;, #xe61c;, #xe61d;, #xe61e;, #xe61f;, #xe620;, #xe621;, #xe641;, #xe622;, #xe623;, "
				+ "#xe624;, #xe625;, #xe626;, #xe627;, #xe628;, #xe629;, #xe62a;, #xe62d;, #xe62e;, #xe633;, "
				+ "#xe634;, #xe638;, #xe63f;, #xe643;, #xe644;, #xe646;, #xe69f;, #xe630;, #xe637;"; 	

		String name="品牌, 下载, 邮箱, 等级, 退出, 草稿箱, 文章, 商品, 分类, all, "
				+ "统计, 标签, 日志, 新增, 品牌, 文章, 编辑, 图片, 分类, 07,"
				+ " 10, 22, 23, 24, 25, 27, 29, 等级, 38, 上翻, "
				+ "39, 评论, 勾, 谷歌, 网站, 日志, ios浏, 箭头, 51, 箭头, "
				+ "箭头, 分类, 栏目, 火狐, 分类, 网站, account, addfrien, eyeopen, form, "
				+ "person, phone, set, share, paynumbe, 等级, icon1, 回复(1, 商城, icon-pas,"
				+ " 首页, iocn-lef, iocn-rig, iocn-upw, iocn-dow, iocn-see, iocn-add, iocn-sha, 悬浮-, 分类sv,"
				+ " 关闭sv, 加svg, 减, 客服, 评分, 时间, 收藏, 售后, 悬浮-, 我的, "
				+ "照片, 键盘, 删除, 收藏1, 双下, 提醒, 消息1, 通知, 换一, 限时, "
				+ "商品, 留言, 验证, 手机1, 密码, 设置1, 时钟1, 团队, 内容";
		
		
		String[] iconarray=icon.split(",");
		String[] namearray = name.split(","); // 用,分割
		System.out.println(Arrays.toString(iconarray)); // [0, 1, 2, 3, 4, 5]
		System.out.println(Arrays.toString(namearray)); // [0, 1, 2, 3, 4, 5]
		System.out.println(namearray.length); // [0, 1, 2, 3, 4, 5]
		System.out.println(iconarray.length); // [0, 1, 2, 3, 4, 5]
		
		Map<String, Object> Icon = new HashMap<String, Object>();

		List<IconModel> iconlist=new ArrayList<IconModel>();
		for(int i=0;i<iconarray.length;i++) {
			IconModel iconmodel =new IconModel();
			iconmodel.setIconfont(iconarray[i]);
			iconmodel.setName(namearray[i]);
			iconlist.add(iconmodel);			
		}

		Icon.put("iconfont",iconlist);
		
		return JSON.toJSONString(Icon);
		
	}
	
	
}