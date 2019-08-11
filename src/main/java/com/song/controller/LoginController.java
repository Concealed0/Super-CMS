package com.song.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.song.common.StrUtil;
import com.song.common.SuperCommon;
import com.song.dao.AdminUserMapper;
import com.song.model.AdminUser;
import com.song.service.AdminLogService;
import com.song.service.AdminUserService;


@Controller
@RequestMapping("/LoginCon")
public class LoginController {
	
	//公共资源文件
		SuperCommon songcom=new SuperCommon();
		
		@Autowired
		AdminUserService adminuserService;
		
		@Autowired
		AdminLogService adminlogservice;

		
		@Autowired
		AdminUserMapper adminusermapper;
		
		
		/**   
		* @Function: Login.java
		* @Description: 后台登陆方法
		*
		* @param:描述1描述
		* @return：返回结果描述
		* @throws：异常描述
		*
		* @version: v1.0.0
		* @author: dongsong
		* @date: 2019年5月24日 上午10:54:25 
		*/
		@RequestMapping("/login")
	    public String AdminLogin(){       
	        return "Admin/login/index";
			//return "redirect:/userController/hello";
	    }
		
		//获取主页
		@RequestMapping("/supermenu")
	    public String supermenu(){       
	        return "Admin/index/index";
			//return "redirect:/userController/hello";
	    }
		
		@RequestMapping("/supermenuhome")
	    public String supermenuhome(){       
	        return "Admin/index/home";
			//return "redirect:/userController/hello";
	    }

		/**   
		* @Function: Login.java
		* @Description: 使用string方法接收前台数据
		*
		* @param:username  账号
		*        password  密码
		*        userType  账号权限
		*        verifyCode  校验码
		* @return：返回结果描述
		* @throws：异常描述
		*
		* @version: v1.0.0
		* @author: dongsong
		* @date: 2019年5月24日 下午10:16:14 
		*/
		@ResponseBody
		@RequestMapping(value="/LeLogin")
		public String doLogin(@RequestParam(defaultValue="") String username,
				@RequestParam(defaultValue="") String password,
				@RequestParam(defaultValue="0") int userType,
				@RequestParam(defaultValue="") String verifyCode,HttpServletRequest request, HttpSession session) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException {
			
			int group_id=0;
	        //获取客户端登陆的ip地址
			String ip=songcom.getIpAddr(request);
			//获取客户端采用的浏览器方式
			String userAgent = request.getHeader("user-agent");
			
			//比较验证码，toUpperCase()的意思是将所有的英文字符转换为大写字母
			//StrUtil.CODE_ERROR+"松"+sessionVerifyCode+"松"+verifyCode;
			String sessionVerifyCode = (String) session.getAttribute(StrUtil.VERIFY_CODE);
			if (sessionVerifyCode == null || !sessionVerifyCode.equals(verifyCode)) {
				System.out.println("验证码错误，已将登陆信息保存至日志。");
				int le=adminlogservice.adminlogValidate(0,username,ip,userAgent,StrUtil.CODE_ERROR);
				System.out.println(le);
				return StrUtil.CODE_ERROR;
			}
			int status=adminuserService.selectStatus(username);
			if(status!=1)
				return StrUtil.RESULT_STATUS;
			Object user=adminuserService.loginValidate(username, password.toUpperCase());//获得验证后user对象
			System.out.println(JSON.toJSONString(user));
			if(user!=null) {
				group_id=(int) songcom.getFieldValueByName("groupId",user);
				//System.out.println(group_id);
				System.out.println(JSON.toJSONString(group_id));
				System.out.println("登陆成功，已将登陆信息保存至日志。");
				int le=adminlogservice.adminlogValidate(group_id,username,ip,userAgent,StrUtil.RESULT_TRUE);
				System.out.println(le);
				//将登陆用户名以及获取的用户权限保存在session中
				session.setAttribute("group_id", group_id);
				session.setAttribute("username", username);
				return JSON.toJSONString(user);
			}else {
				System.out.println("登录名或密码错误，已将登陆信息保存至日志。");
				adminlogservice.adminlogValidate(group_id,username,ip,userAgent,StrUtil.RESULT_FALSE);
				return StrUtil.RESULT_FALSE;
			}

				
		}
		
		
		/**   
		* @Function: Login.java
		* @Description: 后台验证码功能
		*
		* @param:描述1描述
		* @return：返回结果描述
		* @throws：异常描述
		*
		* @version: v1.0.0
		* @author: dongsong
		* @date: 2019年5月24日 上午11:05:37 
		*/
		@RequestMapping("/getVerifyCode")
		public void getVerifyCode(HttpServletResponse response,HttpSession session) {
			ByteArrayOutputStream output =new ByteArrayOutputStream();
			session.setAttribute("verifyCode",songcom.drawCodeImg(output));
			try {
				ServletOutputStream out =response.getOutputStream();
				output.writeTo(out);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping(value= "/logout", produces = "application/json;charset=UTF-8")
		@ResponseBody
		public String logout(HttpSession session) {
			session.setAttribute("group_id", null);
			session.setAttribute("username", null);
			return songcom.ajaxReturn(200, "退出系统成功！", "/SuperCMS/LoginCon/login");
		}
		
		
		
		/**   
		* @Function: Login.java
		* @Description: 该函数的功能描述
		*
		* @param:testJson1
		* @return：JSON数组返回
		* @throws：使用List标签页面跳转
		*
		* @param:testJson2
		* @return：JSON数组返回
		* @throws：使用HashMap方法进行JSON数组返回
		* 
		* @param:loginPage
		* @return：直接跳转到指定页面
		* @throws：使用ModelAndView直接跳转页面
		* 
		* @param:login
		* @return：直接跳转到指定页面
		* @throws：使用返回string函数直接跳转页面
		* 
		* @version: v1.0.0
		* @author: dongsong
		 * @throws IllegalAccessException 
		 * @throws IllegalArgumentException 
		* @date: 2019年5月24日 下午10:07:12 
		*/
		
		@RequestMapping("/testJson1")
		@ResponseBody
		public Object getJson1() throws IllegalArgumentException, IllegalAccessException {
		//	List<User> song = usermapper.userlist();
			AdminUser song= adminusermapper.selectByPrimaryKey(1);		
			for (Field f:song.getClass().getDeclaredFields()){   //遍历通过反射获取object的类中的属性名
		        f.setAccessible(true);    //设置改变属性为可访问
		        if(f.getName().equals("username")){
		            System.out.println("属性值"+f.get(song));
		        }
		       // System.out.println("属性值");
		    }
			return JSON.toJSONString(song);
		}
		
		@RequestMapping("/testJson2")
		@ResponseBody
		public Object getJson2() {
			HashMap<String,Object> song = new HashMap<String, Object>();
			song.put("ceshi", adminusermapper.selectByPrimaryKey(1));
			return song;
		}
		
		
		@RequestMapping("/loginPage")
		public ModelAndView toLoginPage() {
			return new ModelAndView("Admin/login/login");
		}
		
		@RequestMapping("/login1")
	    public String login(){
			return "redirect:/LoginCon/hello";
	        //return "admin/LElogin";
	    }
		
		@RequestMapping("/hello")
	    public String hello(){
	       
	        return "Admin/menu/index1";
	    }
		
		//bug页面404
		@RequestMapping("/bug")
	    public String bug(){
	       
	        return "404";
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/testJson4")
	@ResponseBody
	public Object getJson4() throws IllegalArgumentException, IllegalAccessException {
	//	List<User> song = usermapper.userlist();
	    AdminUser song= adminusermapper.selectByPrimaryKey(1);		
		return JSON.toJSONString(song);
	}
	
}
