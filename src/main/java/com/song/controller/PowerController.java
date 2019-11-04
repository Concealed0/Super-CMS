/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.controller 
 * @author: dongsong   
 * @date: 2019年6月19日 下午6:55:23 
 */
package com.song.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.song.common.SuperCommon;
import com.song.model.AdminLog;

import com.song.model.AdminUser;
import com.song.service.AdminLogService;
import com.song.service.AdminUserService;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: ProwerController.java
* @Description: 超级权限菜单，查看登陆日志
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年6月19日 下午6:55:23 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月19日     dongsong           v1.0.0               修改原因
*/
@Controller
@RequestMapping("/PowerCon")
public class PowerController {
	
	public int userid=0;
	
	SuperCommon superCom=new SuperCommon();
	@Autowired
	AdminLogService logservice;
	@Autowired
	AdminUserService userservice;
	
	
	
	
	@RequestMapping(value="/powerindex")
	public String powerindex() {
		return "Admin/power/index";
	}
	
	@RequestMapping(value="/powerusermenu")
	public String powerusermenu() {
		return "Admin/power/poweruser";
	}
	//账号编辑部分
	@RequestMapping(value="/poweruserinfo")
	public String poweruserinfo() {
		return "Admin/power/poweruserinfo";
	}
	
	
	@RequestMapping(value="/index",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String index() {
		List<AdminLog> adminlog=logservice.selectall();
		return superCom.SuperJson(adminlog,"超级权限中的日志部分");
	}
	
	
	@RequestMapping(value="/poweruser",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String poweruser() {
		List<AdminUser> adminuser =userservice.selectpowerAll();
		return superCom.SuperJson(adminuser,"超级权限中的账号管理部分");
	}
	//测试连表查询，user+group表中的所有值
	@RequestMapping(value="/powerusergroup",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String powerusergroup() {
		List<AdminUser> adminuser =userservice.selecttwo();
		return superCom.SuperJson(adminuser,"超级权限中的账号管理部分");
	}
	
	@ResponseBody
	@RequestMapping(value="/powerapi",produces = "application/json;charset=UTF-8")
	public String powerapi(@RequestParam(defaultValue="table") String table,
			@RequestParam(defaultValue="id_name") String id_name,
			@RequestParam(defaultValue="00") int id_value,
			@RequestParam(defaultValue="status") String field,
			@RequestParam(defaultValue="") String field_value,HttpServletRequest request, HttpSession session) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException {
		System.out.println(table+"-------"+id_name+"-------"+id_value+"-------"+field+"-------"+field_value);
		int status;
		if (id_value==00){
			return superCom.ajaxReturn(0,"参数不足","");
        }
		if (!field_value.equals("false")){
			status=1;
        }else
        	status = 0;		
		int api=userservice.updatapowerapi(id_value, status);
		if(api==1) {
			return superCom.ajaxReturn(200,"操作成功","");
		}else
			return superCom.ajaxReturn(0,"操作失败","");
	}
	@ResponseBody
	@RequestMapping(value="/powerdel",produces = "application/json;charset=UTF-8")
	public String powerdel(@RequestParam(defaultValue="10") int id,
			HttpServletRequest request, HttpSession session) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException {
		System.out.println("-------"+id+"-------");
		switch(id) {
		case 10:
			return superCom.ajaxReturn(0,"参数不能为空","");
		case 1:
			return superCom.ajaxReturn(0,"保留用户无法删除","");			
		default :
			int del=userservice.powerdel(id);
			if(del==1)
				return superCom.ajaxReturn(200,"用户删除成功","");
			else
				return superCom.ajaxReturn(0,"用户删除失败","");		
		}
		
	}
	//***
	@RequestMapping(value="/powerinfo")
	public String powerinfo(@RequestParam(defaultValue="") Integer user_id,
			HttpServletRequest request, HttpSession session) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException {
		System.out.println("-------"+user_id+"-------");
		if(user_id==null) {
			userid=0;
			return "Admin/power/poweruserinfo";
		}else {
			userid=user_id;
			return "Admin/power/poweruserinfo";
		}
	}
	//****
	@ResponseBody
	@RequestMapping(value="/powerinfodata",produces = "application/json;charset=UTF-8")
	public String powerinfodata() {
		System.out.println("DDDDDD"+userid+"D");
		if(userid==0) {
			return superCom.ajaxReturn(0,"超级权限编辑用户，获取用户id失败","");
		}else {
			List<AdminUser> adminuser =userservice.usergroup(userid);
			userid=0;
			return superCom.SuperJson(adminuser,"超级权限中的账号编辑部分");
		}	
	}
	//****
	@ResponseBody
	@RequestMapping(value="/powerinfoupdata",produces = "application/json;charset=UTF-8")
	public String powerinfoupdata(@RequestParam(defaultValue="") Integer group_id,
			@RequestParam(defaultValue="") String username,
			@RequestParam(defaultValue="") String nicename,
			@RequestParam(defaultValue="") String email,
			@RequestParam(defaultValue="") String le_tel,
			@RequestParam(defaultValue="") String password,
			@RequestParam(defaultValue="") String password2,
			@RequestParam(defaultValue="") Integer status,
			@RequestParam(defaultValue="") Integer user_id) {
		System.out.println(group_id+"-------"+username+"-------"+nicename+"-------"+email+"-------"+le_tel+"-------"+password+"-------"+password2+"-------"+status+"-------"+user_id);
		System.out.println("user_id不为空，进入判断阶段，use_id值为-------"+userid+"-------");
		
		if(user_id!=null) {
			//如果传过来的值都不为空
			if(group_id!=null||username!=null||nicename!=null||status!=null) {
				List<AdminUser> name=userservice.selectUsername(username);
				if(name.size()==0) {
					System.out.println("api获取的其他值有一个不为空，进入到编辑用户表单逻辑--------------");
					if (password!=null&&password2!=null){
						if(!password.equals(password2))
							return superCom.ajaxReturn(0,"两次密码不一致","");
						else {

							int res=userservice.powerupdata(group_id,username,nicename,email,le_tel,password,status,user_id);
					    	if(res!=1) {
					    		return superCom.ajaxReturn(0,"操作失败","");
					    	}
					    	return superCom.ajaxReturn(200,"操作成功","powerusermenu");
						}
				    }else {
				    	int res=userservice.powerupdata(group_id,username,nicename,email,le_tel,password,status,user_id);
				    	if(res!=1) {
				    		return superCom.ajaxReturn(0,"操作失败","");
				    	}
				    	return superCom.ajaxReturn(200,"操作成功","powerusermenu");
				    }
				}else {
					String allsort=superCom.getFieldValueListByName(name,AdminUser.class,"userId");
					if(Integer.parseInt(allsort)==user_id) {
						if (password!=null&&password2!=null){
							if(!password.equals(password2))
								return superCom.ajaxReturn(0,"两次密码不一致","");
							else {

								int res=userservice.powerupdata(group_id,username,nicename,email,le_tel,password,status,user_id);
						    	if(res!=1) {
						    		return superCom.ajaxReturn(0,"操作失败","");
						    	}
						    	return superCom.ajaxReturn(200,"操作成功","powerusermenu");
							}
					    }else {
					    	int res=userservice.powerupdata(group_id,username,nicename,email,le_tel,password,status,user_id);
					    	if(res!=1) {
					    		return superCom.ajaxReturn(0,"操作失败","");
					    	}
					    	return superCom.ajaxReturn(200,"操作成功","powerusermenu");
					    }
					}else
						return superCom.ajaxReturn(0,"操作失败，账号已被占用","");
				}
				
			}else
				return superCom.ajaxReturn(0,"操作失败，请将信息填写完整","");
		//userid为空	
		}else {
			//如果传过来的值都不为空
			if(group_id!=null||username!=null||nicename!=null||status!=null||password!=null||password2!=null) {
				List<AdminUser> name=userservice.selectUsername(username);
				if(name.size()==0) {
					System.out.println("api获取的其他值有一个不为空，进入到编辑用户表单逻辑--------------");
					if(!password.equals(password2))
						return superCom.ajaxReturn(0,"两次密码不一致","");
					else {
						int res=userservice.powerinsert(group_id,username,nicename,email,le_tel,password,status,user_id);
				    	if(res!=1) {
				    		return superCom.ajaxReturn(0,"操作失败","");
				    	}
				    	return superCom.ajaxReturn(200,"操作成功","powerusermenu");
					}		
				}else
					return superCom.ajaxReturn(0,"操作失败，账号已被占用","");
					    
			}else
				return superCom.ajaxReturn(0,"操作失败，请将信息填写完整","");
		}		
	}	
	
	
	//替换上面的方法
	@ResponseBody
	@RequestMapping(value="/powerinfoupdata1111",produces = "application/json;charset=UTF-8")
	public String powerinfoupdata111(@RequestParam(defaultValue="") Integer group_id,
			@RequestParam(defaultValue="") String username,
			@RequestParam(defaultValue="") String nicename,
			@RequestParam(defaultValue="") String email,
			@RequestParam(defaultValue="") String le_tel,
			@RequestParam(defaultValue="") String password,
			@RequestParam(defaultValue="") String password2,
			@RequestParam(defaultValue="") Integer status,
			@RequestParam(defaultValue="") Integer user_id,HttpServletRequest request, HttpSession session) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException {
		System.out.println(group_id+"-------"+username+"-------"+nicename+"-------"+email+"-------"+le_tel+"-------"+password+"-------"+password2+"-------"+status+"-------"+user_id);
		System.out.println("DDDDDD"+userid+"D");
		if(user_id!=null) {
			System.out.println("user_id不为空，进入判断阶段，use_id值为-------"+userid+"-------");
			if(group_id!=null||username!=null||nicename!=null||email!=null||le_tel!=null||password!=null||password2!=null||status!=null) {
				System.out.println("api获取的其他值有一个不为空，进入到编辑用户表单逻辑--------------");
				if (password!=null){
					if(password2!=null) {
						return superCom.ajaxReturn(0,"确认密码不能为空","");
					}
					if(!password.equals(password2))
						return superCom.ajaxReturn(0,"两次密码不一致","");
			    }else {
			    	int res=userservice.powerupdata(group_id,username,nicename,email,le_tel,password,status,user_id);
			    	if(res==1) {
			    		return superCom.ajaxReturn(200,"操作成功","Admin/power/poweruser");
			    	}
			    	else {
			    		return superCom.ajaxReturn(0,"操作失败","");
			    	}
			    }
			}else {
				System.out.println("api获取的其他值都为空，进入到返回超级权限的账号编辑ajax部分--------------");
				List<AdminUser> adminuser =userservice.usergroup(user_id);
				return superCom.SuperJson(adminuser,"超级权限中的账号编辑部分");
			}		
		}else {
			userid=0;
			return "redirect:/LoginCon/bug";
		}
		return "redirect:/LoginCon/bug";
	}	
}
