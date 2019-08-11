package com.song.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.song.common.SuperCommon;
import com.song.dao.AdminGroupMapper;
import com.song.dao.AdminMenuMapper;
import com.song.model.AdminMenu;
import com.song.model.AdminUser;
import com.song.service.AdminMenuService;
import com.song.service.AdminUserService;

@Controller
@RequestMapping("/MenuCon")
public class MenuController {

	SuperCommon supercom = new SuperCommon();
	@Autowired
	AdminMenuMapper adminmenumapper;
	@Autowired
	AdminGroupMapper admingroupmapper;
	@Autowired
	AdminMenuService adminmenuservice;
	@Autowired
	AdminUserService adminuserservice;

	//使用jsp返回数据进行页面渲染
	@RequestMapping(value="menuhome1",method=RequestMethod.GET)
	public String menuhome1(HttpSession session,Model model) {
		System.out.println("右上角登陆名称");
		List<AdminUser> adminuser=adminuserservice.selectUsername("admin");
		System.out.println(JSON.toJSONString(adminuser));
		model.addAttribute("adminuser", adminuser);
		return "Admin/index/showmodel";
	}
	@RequestMapping(value="menuhome2",produces = "application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String menuhome2(HttpSession session,Model model) {
		System.out.println("使用json dddddddddd右上角登陆名称");
		List<AdminUser> adminuser=adminuserservice.selectUsername("admin");
		//model.addAttribute("username", adminuser);
		System.out.println(JSON.toJSONString(adminuser));	
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> data1 = new HashMap<String, Object>();
		data.put("list", adminuser);
		data1.put("data", data);
		data1.put("status", 200);
		return JSON.toJSONString(data1);
	}
	
	
	
	@RequestMapping(value="/menuhome")
	public String menuhome(HttpSession session) {
		return "Admin/index/index";
	}
	
	//跳转到主页面
	@RequestMapping(value="userhome", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String userhome(HttpSession session) {
		System.out.println("右上角登陆名称");
		System.out.println(session.getAttribute("username"));
		List<AdminUser> adminuser=adminuserservice.selectUsername((String) session.getAttribute("username"));
		System.out.println("右上角登陆名称--------");
		System.out.println(JSON.toJSONString(adminuser));
		return JSON.toJSONString(adminuser);
	}
//首页导航三级菜单显示
	@RequestMapping(value = "/supermenu", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String supermenu(HttpSession session) {
		if (session.getAttribute("group_id") != null) {
			System.out.println("存入session的group获取到值为" + session.getAttribute("group_id"));
			System.out.println("------获取adminmenupurview权限的menu字符串----------");
			String str = adminmenuservice.selectmenupurview((int) session.getAttribute("group_id"));
			System.out.println(str);
			if (str != null && str.length()!= 0) {
				System.out.println("------将有逗号的字符串转换为数组----------");
				String[] menu = supercom.convertStrToArray(str);
				System.out.println("------使用数组获取部分menu----------");
				System.out.println(JSON.toJSONString(adminmenuservice.selectpurview(menu)));
				List<AdminMenu> menupurview = adminmenuservice.supermenupurview(menu);
				Map<String, Object> data = findTree(menupurview);
				return JSON.toJSONString(data);		
			}else {
				List<AdminMenu> menuall = adminmenuservice.supermenuall();
				Map<String, Object> dataall = findTree(menuall);
				return JSON.toJSONString(dataall);	
			}							
		} else {							
			System.out.println("------获取session失败----跳转到Admin/login/login登录页面------");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("error", "获取session失败");
			return JSON.toJSONString(data);
		}
	}


	// 获取三级菜单
	public Map<String, Object> findTree(List<AdminMenu> allMenu) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> data1 = new HashMap<String, Object>();
		try {// 查询所有菜单
				// List<AdminMenu> allMenu = adminmenuservice.supermenuall();
				// 根节点
			List<AdminMenu> rootMenu = new ArrayList<AdminMenu>();
			for (AdminMenu nav : allMenu) {
				if (nav.getPid().equals(0)) {// 父节点是0的，为根节点。
					rootMenu.add(nav);
				}
			}
			/* 根据Menu类的order排序 */
			Collections.sort(rootMenu, order());
			// 为根菜单设置子菜单，getClild是递归调用的
			for (AdminMenu nav : rootMenu) {
				/* 获取根节点下的所有子节点 使用getChild方法 */
				List<AdminMenu> childList = getChild(nav.getId(), allMenu);
				nav.setChildren(childList);// 给根节点设置子节点
			}
			/**
			 * 输出构建好的菜单数据。
			 *
			 */
			//data.put("success", "true");
			data.put("list", rootMenu);
			
			data1.put("data", data);
			data1.put("status", 200);
			return data1;
		} catch (Exception e) {
			data.put("success", "false");
			data.put("list", new ArrayList<AdminMenu>());
			return data;
		}
	}
	
	/*
	 * 排序,根据order排序
	 */
	public Comparator<AdminMenu> order() {
		Comparator<AdminMenu> comparator = new Comparator<AdminMenu>() {
			@Override
			public int compare(AdminMenu o1, AdminMenu o2) {
				if (o1.getSort() != o2.getSort()) {
					return o1.getSort() - o2.getSort();
				}
				return 0;
			}
		};
		return comparator;
	}
	/**
	 * 获取子节点
	 * 
	 * @param id      父节点id
	 * @param allMenu 所有菜单列表
	 * @return 每个根节点下，所有子菜单列表
	 */
	public List<AdminMenu> getChild(int id, List<AdminMenu> allMenu) {
		// 子菜单
		List<AdminMenu> childList = new ArrayList<AdminMenu>();
		for (AdminMenu nav : allMenu) {
			// 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
			// 相等说明：为该根节点的子节点。
			if (nav.getPid().equals(id)) {
				childList.add(nav);
			}
		}
		// 递归
		for (AdminMenu nav : childList) {
			nav.setChildren(getChild(nav.getId(), allMenu));
		}
		Collections.sort(childList, order());// 排序
		// 如果节点下没有子节点，返回一个空List（递归退出）
		if (childList.size() == 0) {
			return new ArrayList<AdminMenu>();
		}
		return childList;
	}

	
	
	
//	@RequestMapping("/menupurview")
//	@ResponseBody
//	public void menupurview() {
//		List<Object> menuone=adminmenuservice.supermenuonepid(0);
//		System.out.println(menuone);
//		System.out.println(menuone.size());
//		for(int i=0;i<menuone.size();i++){
//	          System.out.println(menuone.get(i));
//	          System.out.println(menuone.get(i).toString());
//	        }
//		supercom.getFieldValueList(menuone);
//		for(Object o:menuone) {
//			Object[] ceshi=supercom.getFiledValues(o);
//			//System.out.println(ceshi);
//			for(int i=0;i<ceshi.length;i++){
//		          System.out.println(ceshi[i].toString());
//		        }
//			String test=JSON.toJSONString(ceshi);
//			System.out.println("parseJsonObject()方法：json==" + test);
//		}	
//		System.out.println("----------一级标题---------");
//		System.out.println(JSON.toJSONString(menuone));
//		//int id=(int) supercom.getFieldValueByName("id", adminmenu);
//		String menuoneid=supercom.getFieldValueListByName(menuone,AdminMenu.class,"id");
//		System.out.println(menuoneid);		
//		System.out.println("------将有逗号的一级标题字符串转换为数组----------");
//		String[] menutwopid=supercom.convertStrToArray(menuoneid); 
//		List<AdminMenu> menutwo=adminmenuservice.supermenutwopid(menutwopid);
//		System.out.println("----------二级标题---------");
//		System.out.println(JSON.toJSONString(menutwo));
//		String menutwoid=supercom.getFieldValueListByName(menutwo,AdminMenu.class,"id");
//		System.out.println("------二级标题字符串----------");
//		System.out.println(menutwoid);
//		System.out.println("------将有逗号的二级标题字符串转换为数组----------");
//		String[] menuthreepid=supercom.convertStrToArray(menutwoid);
//		List<AdminMenu> menuthree=adminmenuservice.supermenutwopid(menuthreepid);
//		System.out.println("----------三级标题---------");
//		System.out.println(JSON.toJSONString(menuthree));
//		String menuthreeid=supercom.getFieldValueListByName(menuthree,AdminMenu.class,"id");
//		System.out.println("------三级标题字符串----------");
//		System.out.println(menuthreeid);
//		
//	}
	
}
