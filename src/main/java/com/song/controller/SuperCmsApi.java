/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.controller 
 * @author: dongsong   
 * @date: 2019年6月27日 下午4:51:30 
 */
package com.song.controller;




import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.song.common.StrUtil;
import com.song.common.SuperCommon;
import com.song.model.AdminMenu;
import com.song.model.AdminUser;
import com.song.model.Category;
import com.song.model.Content;
import com.song.service.AdminMenuService;
import com.song.service.AdminUserService;
import com.song.service.ArticleService;
import com.song.service.SuperApiService;
import com.song.service.impl.ArticleServiceImpl;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: SuperCmsApi.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年6月27日 下午4:51:30 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月27日     dongsong           v1.0.0               修改原因
*/
@Controller
@RequestMapping("/SuperApi")
public class SuperCmsApi {

	SuperCommon superCom=new SuperCommon();
	public Integer apiid=null;
	public String apiname;
	@Autowired
	SuperApiService superapi;
	@Autowired
	AdminUserService userservice;
	@Autowired
	AdminMenuService menuservice;

	@Autowired
	ArticleService artser;
	
	@ResponseBody
	@RequestMapping(value="/icon",produces = "application/json;charset=UTF-8")
	public String icon() {
		return StrUtil.icon();
	}
	
	//可以用于前台对status、sort的更改
	@ResponseBody
	@RequestMapping(value="/menustatus",produces = "application/json;charset=UTF-8")
	public String updatamenuapi(@RequestParam(defaultValue="table") String table,   //接受的数据表名称
			@RequestParam(defaultValue="id_name") String id_name,   //表中字段name值 
			@RequestParam(defaultValue="") Integer id_value,     //表中字段id值  
			@RequestParam(defaultValue="status") String field,    //要操作的字段      field_value  //修改的值
			@RequestParam(defaultValue="") String field_value,HttpServletRequest request, HttpSession session) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException {
		System.out.println("这是更改用户菜单的status状态"+table+"-------"+id_name+"-------"+id_value+"-------"+field+"-------"+field_value);
		int value;
		if (id_value==null){
			return superCom.ajaxReturn(0,"参数不足","");
        }
		if (field_value.equals("false")){
			value=0;
        }else
        	value=Integer.parseInt(field_value);
		switch(table) {
		case "le_admin_menu":
			if(field.equals("status")){
				int menu=superapi.updatamenuapi(id_value, value);
				if(menu==1) {
					return superCom.ajaxReturn(200,"操作成功","");
				}else
					return superCom.ajaxReturn(0,"操作失败","");
			}else if(field.equals("sort")) {
				//创建一个存放id的上级变量
				int upid;
				//根据需求生成随机数组
				if(Pattern.matches("^[1-9]{3}$",Integer.toString(id_value))){
					System.out.println("高级菜单下面建立一级菜单");	
					upid=(id_value/10)*10;                	         	
                }
				else if(Pattern.matches("^[1-9]{2}0$",Integer.toString(id_value))) {
					System.out.println("一级菜单下面建立二级菜单");
					upid=(id_value/100)*100;					       	
                }else if(Pattern.matches("^[1-9]{1}00$",Integer.toString(id_value))) {
					System.out.println("二级菜单下面建立三级菜单");	  	
					upid=(id_value/1000)*1000;
                }else {     
                	System.out.println("更改菜单排序失败");	
         			return superCom.ajaxReturn(0,"更改菜单排序失败","/SuperCMS/AdminMenuCon/adminindex");	
                }
				System.out.println("获取到的上级菜单"+upid);	  	
				//创建一个实体型，通过setPid值查找所有的实体
				AdminMenu menuid=new AdminMenu();
				menuid.setPid(upid);
				//需要先获取到数据库中同级所有字段值
				List<AdminMenu> findallbyid=superapi.fintsortallapi(menuid);	
				System.out.println("查看下获取到的list实体");	
				System.out.println(JSON.toJSONString(findallbyid));	
				//设置一个字符串，用于容纳id
				String allsort=new String();
				if(findallbyid.size()==0||findallbyid==null) {
					allsort="-1";
				}else {
					allsort=superCom.getFieldValueListByName(findallbyid,AdminMenu.class,"sort");
				}
				  	
				//将得到的字符串转换为数组对象
				String[] arraysort = allsort.split(","); // 用,分割
				System.out.println("查看下sort数组");	
				System.out.println(Arrays.toString(arraysort));	
				//判断下sort数组中是否有重复的
				int pdsort=1;
				for(int i=0;i<arraysort.length;i++){
					if(arraysort[i].equals(field_value)) {
						pdsort=0;
						break;
					}
				}
				if(pdsort!=0) {
					//插入排序的值，进行更新，sort值为id_value
					int sort=superapi.updatamenusort(id_value,Integer.parseInt(field_value));
					if(sort==1) {
						return superCom.ajaxReturn(200,"操作成功","");
					}else
						return superCom.ajaxReturn(0,"操作失败","");
				}else
         			return superCom.ajaxReturn(0,"排序输入菜单失败，输入排序重复","/SuperCMS/AdminMenuCon/adminindex");	
				
			}else
				return superCom.ajaxReturn(0,"更改状态时发生未知错误","");
				
	    case "le_admin_user":
			int user=userservice.updatapowerapi(id_value, value);
			if(user==1) {
				return superCom.ajaxReturn(200,"操作成功","");
			}else
				return superCom.ajaxReturn(0,"操作失败","");
	    case "le_content":
	    	int stat=artser.selectStatus(id_value, value);
			if(stat==1) {
				return superCom.ajaxReturn(200,"操作成功","");
			}else
				return superCom.ajaxReturn(0,"操作失败",""); 
		default :
			return superCom.ajaxReturn(0,"更改状态时发生未知错误","/SuperCMS/SuperApi/menustatus");
		}
	}
	//删除单个菜单
	@ResponseBody
	@RequestMapping(value="/menudel",produces = "application/json;charset=UTF-8")
	public String powerdel(@RequestParam(defaultValue="0") Integer id,
			@RequestParam(defaultValue="") String name,
			HttpServletRequest request, HttpSession session) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException {
		System.out.println("-------"+id+"-------"+name);
		if(id==null&&name==null) {
			return superCom.ajaxReturn(0,"参数不能为空","");
		}
		switch(name) {
		case "adminmenu":
			if(superapi.deladminmenu(id)==1)
				return superCom.ajaxReturn(200,"菜单删除成功","");
			else
				return superCom.ajaxReturn(0,"菜单删除失败","");	
		case "usermenu":
			if(superapi.delusermenu(id)==1)
				return superCom.ajaxReturn(200,"用户删除成功","");
			else
				return superCom.ajaxReturn(0,"用户删除失败","");			
		default :
			return superCom.ajaxReturn(0,"无法执行该操作","");		
		}
		
	}
	
	//***

	@RequestMapping(value="/menuinfo")
	public String menuinfo(@RequestParam(defaultValue="") Integer id,
			@RequestParam(defaultValue="") String name) {
		System.out.println("这是跳转到更改的页面，使用的是'/SuperCMS/PowerCon/powerinfo?id=100?name=menu'-------"+id+"-------"+name);
		//如果id传来的不为空，将改变全局变量
		if(id!=null){
			apiid=id;
			apiname=name;
			switch(name) {
			case "usermenu":
				return "Admin/power/poweruserinfo";
			case "adminmenu":
				return "Admin/admin_menu/indexinfo";
			case "contentarticle":
				return "Admin/admin_article/indexinfo";
			default :
				String a="apiid:::"+apiid+"apiname::"+apiname;
				return superCom.ajaxReturn(0,"这是一个错误的跳转页面，错误的跳转信息为"+a,"");
			}
		}else {
			apiid=null;
			apiname=name;
			switch(name) {
			case "usermenu":
				return "Admin/power/poweruserinfo";
			case "adminmenu":
				return "Admin/admin_menu/indexinfo";
			case "contentarticle":
				return "Admin/admin_article/indexinfo";
			default :
				String a="apiid:::"+apiid+"apiname::"+apiname;
				return superCom.ajaxReturn(0,"这是一个错误的跳转页面，错误的跳转信息为"+a,"");
			}
		}
	}
	//****
	@ResponseBody
	@RequestMapping(value="/menuinfodata",produces = "application/json;charset=UTF-8")
	public String menuinfodata() {
		System.out.println("这是先获取的编辑的id与姓名-------"+apiid+"------"+apiname);
		//先判断id是否已经获取到
		if(apiid==null) {
			return superCom.ajaxReturn(0,"超级权限编辑用户，获取id失败","");
		}else {
			switch(apiname) {
			case "hello":
				apiid=null;
				apiname="hello";
				return superCom.ajaxReturn(0,"超级权限编辑用户，获取name失败","");
			case "usermenu":
				List<AdminUser> adminuser =userservice.usergroup(apiid);
				apiid=null;
				apiname="hello";
				return superCom.SuperJson(adminuser,"超级权限中的账号编辑部分");
			case "adminmenu":
				List<AdminMenu> adminmenu =menuservice.supermenuoneapi(apiid);
				apiid=null;
				apiname="hello";
				return superCom.SuperJson(adminmenu,"超级权限中的账号编辑部分");
			default :
				apiid=null;
				apiname="hello";
				System.out.println("判断完menuinfodata后其值-------"+apiid+"------"+apiname);
				return superCom.ajaxReturn(0,"超级权限编辑，获取menuid失败","");
			}
		}
		
	}
	
	//****
		@ResponseBody
		@RequestMapping(value="/menuinfoupdata",produces = "application/json;charset=UTF-8")
		public String powerinfoupdata(@RequestParam(defaultValue="") Integer pid,
				@RequestParam(defaultValue="") String name,
				@RequestParam(defaultValue="") String url,
				@RequestParam(defaultValue="") String iconfont,
				@RequestParam(defaultValue="") Integer status,
				@RequestParam(defaultValue="") Integer id) {
			System.out.println(pid+"-------"+name+"-------"+url+"-------"+iconfont+"-------"+status+"-------"+id);
			if(pid==null&&status==null) {
				return superCom.ajaxReturn(0,"更改菜单失败，所填值不允许为空！","/SuperCMS/AdminMenuCon/adminindex");	
			}
			if(status==2) {
				status=0;
			}
			AdminMenu adminmenu=new AdminMenu();
		    adminmenu.setName(name);
		    adminmenu.setUrl(url);
		    adminmenu.setIconfont(iconfont);
		    adminmenu.setStatus(status);
		    //如果id不为空，代表后台由编辑传入进来
			if(id!=null) {
			    adminmenu.setId(id);	
			    int aa=menuservice.superupdata(adminmenu);
				System.out.println(aa);
				return superCom.ajaxReturn(200,"更改菜单成功","/SuperCMS/AdminMenuCon/adminindex");						
			}
			//判断出传入id为0，证明传入数据为最新编辑数据
			else {	
				if(pid%10!=0) {
					System.out.println("00000000");
					return superCom.ajaxReturn(0,"更改菜单失败，只允许创建三级目录！","/SuperCMS/AdminMenuCon/adminindex");	
				}
				String[] differ=new String[9];
				AdminMenu allid=new AdminMenu(); 
				allid.setPid(pid);
				//获取相同pid 的所有id值
				List<AdminMenu> aid=menuservice.selectarrayId(allid);
				//通过pid获取到sort的最大值
				Integer Maxsort=menuservice.superMaxsort(allid);
				if(Maxsort!=null) {
					adminmenu.setSort(Maxsort+1);
				}else {
					adminmenu.setSort(1);
				}        			
				System.out.println("通过pid获取到sort的最大值"+Maxsort);

				//设置一个字符串，用于容纳id
				String aidd=new String();
				if(aid.size()==0||aid==null) {
					aidd="-1";
				}else {
					aidd=superCom.getFieldValueListByName(aid,AdminMenu.class,"id");
				}
				System.out.println(aidd);
				//将得到的字符串转换为数组对象
				String[] arrayid = aidd.split(","); // 用,分割
				
				String[] differid=new String[9];
				int newid=-1;
				//根据需求生成随机数组
				if(Pattern.matches("^0$",Integer.toString(pid))){
					System.out.println("高级菜单下面建立一级菜单");		          	
                	for(int i=100,j=0;j<9;i=i+100,j++) {
                		differid[j]=String.valueOf(i);
                	}               	
                }
				else if(Pattern.matches("^[1-9]{1}00$",Integer.toString(pid))) {
					System.out.println("一级菜单下面建立二级菜单");						
					//创建一些列整数数组
                	for(int i=pid+10,j=0;j<9;i=i+10,j++) {
                		differid[j]=String.valueOf(i);
                	}               	
                }else if(Pattern.matches("^[1-9]{2}0$",Integer.toString(pid))) {
					System.out.println("二级菜单下面建立三级菜单");	  	
					//创建一些列整数数组
                	for(int i=pid+1,j=0;j<9;i=i+1,j++) {
                		differid[j]=String.valueOf(i);
                	}
                }else {     
                	System.out.println("************");	
         			return superCom.ajaxReturn(0,"更改菜单失败","/SuperCMS/AdminMenuCon/adminindex");	
                }
				System.out.println("随机产生的数组值");
            	System.out.println(differid);
            	System.out.println(Arrays.toString(differid));

            	System.out.println("输出所有的id数组");
				System.out.println(arrayid);
            	System.out.println(Arrays.toString(arrayid));
            	
                //判断随机产生的数组与输出的id数组的不同值
            	differ=different(arrayid,differid);
            	System.out.println("字段的不相同值");
            	System.out.println(differ);
            	System.out.println(Arrays.toString(differ)); 
            	
            	//将第一个不同值给一个对象，这个不同值将是插入数据的id值
            	for(int i=0;i<differ.length;i++) {
            		if(differ[i]!=null) {
            			newid=Integer.parseInt(differ[i]);
            			break;
            		}
            	}
            	System.out.println("添加到数据库中的id值为"+newid);
            	if(newid>0) {
            		adminmenu.setPid(pid);	
            		adminmenu.setId(newid);
            		int aa=menuservice.superinsert(adminmenu);
     				System.out.println("将这个新建的表插入到数据库,返回值"+aa);
     				if(aa==1) {
     					return superCom.ajaxReturn(200,"更改菜单成功","/SuperCMS/AdminMenuCon/adminindex");
     				}else
     					return superCom.ajaxReturn(0,"更改菜单失败","/SuperCMS/AdminMenuCon/adminindex"); 	
            	}else {
     				return superCom.ajaxReturn(0,"更改菜单失败，同一级目录只允许建立十个！","/SuperCMS/AdminMenuCon/adminindex");	
            	}
				
			}
		}	
		
		
		@Autowired
		ArticleServiceImpl articleService;
		//获取到文章content-content_article编辑信息
		@ResponseBody
		@RequestMapping(value="artinfo",produces="application/json;charset=UTF-8")
		public String artinfo() {
			
			System.out.println("文章后台classId值------------------------"+apiid);
			//id="148";
			Integer contentId;
			if(apiid!=null) {
				contentId=Integer.valueOf(apiid);
			}else {
				return superCom.ajaxReturn(400, "传入后台id为空", "index");
			}
			
			//获取content_article与content一对一数据接口
			Content infocon=articleService.selectInfo(contentId);
			List<Category> infocate=articleService.selectByCate(null);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("infocon", infocon);
			data.put("infocate",infocate);
			return superCom.SuperJsonMap(data,"返回所有文章接口");
		}
		
		
		//比较两个数组的不同之处
		//b为随机的数组  a为子菜单数组
		public String[] different(String a[],String b[]) {
			String[] differ=new String[9];   
		    String x = null;
		    for(int i=0;i<b.length;i++) {
		    	for(int j=0;j<a.length;j++) {
		    		if(!b[i].equals(a[j])) {
		    			x=b[i];
		    		}		    			
		    		else {
		    			x=null;
		    			break;		
		    		}		    			    		
		    	}
		    	if(x!=null) {
			    	differ[i]=x;
		    	}		    	
		    }
		    return differ;
		}
}
