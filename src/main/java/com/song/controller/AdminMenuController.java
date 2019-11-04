/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.controller 
 * @author: dongsong   
 * @date: 2019年6月25日 上午9:08:17 
 */
package com.song.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.song.common.StrUtil;
import com.song.common.SuperCommon;
import com.song.model.AdminMenu;
import com.song.model.MenuTree;
import com.song.service.AdminMenuService;





/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: AdminMenuController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年6月25日 上午9:08:17 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月25日     dongsong           v1.0.0               修改原因
*/
@Controller
@RequestMapping("/AdminMenuCon")
public class AdminMenuController{
	// @Fields allmenu : TODO
    //public int a=0 ;
	
	SuperCommon superCom=new SuperCommon();
	
	@Autowired
	AdminMenuService adminmenuservice;
	
	
	@RequestMapping(value="/adminindex")
	public String powerindex() {
		return "Admin/admin_menu/index";
	}
	//获取后台图标
	@ResponseBody
	@RequestMapping(value="/icon",produces = "application/json;charset=UTF-8")
	public String icon() {
		return StrUtil.icon();
	}
	//对状态status=1的进行筛选菜单组进行筛选
	@ResponseBody
	@RequestMapping(value="/menupowerapi",produces = "application/json;charset=UTF-8")
	public String menupowerapi() {
		List<AdminMenu> menuall = adminmenuservice.supermenuall();
		List<AdminMenu> menuallapi =new ArrayList<AdminMenu>();
		//创建一个AdminMenu的对象作为多叉树的根的值
		AdminMenu root=new AdminMenu();
		root.setId(0);
		//创建跟节点
		MenuTree roottree =new MenuTree();
		roottree.setNodeEntity(root);
		//开始创建多叉树
		CreateTree(roottree,menuall);
		//遍历多叉树，roottree 为多叉树的根节点，这个函数将根节点遍历给全局变量allmenu对象数组中
		DFStraverse(roottree,menuallapi);
		if(menuallapi.size()==0) {
			return superCom.SuperJson(menuallapi,"超级权限中的菜单管理出错！");
		}
		System.out.println(JSON.toJSONString(menuallapi));
		return JSON.toJSONString(menuallapi);
	}
	//超级权限中的菜单管理选择所有的status组
	@ResponseBody
	@RequestMapping(value="/menupower",produces = "application/json;charset=UTF-8")
	public String menupower() {
		List<AdminMenu> menuall = adminmenuservice.superapimenuall();
		List<AdminMenu> menuallapi =new ArrayList<AdminMenu>();
		//创建一个AdminMenu的对象作为多叉树的根的值
		AdminMenu root=new AdminMenu();
		root.setId(0);
		//创建跟节点
		MenuTree roottree =new MenuTree();
		roottree.setNodeEntity(root);
		//开始创建多叉树
		CreateTree(roottree,menuall);
		//遍历多叉树
		roottree.traverse();
		System.out.println(roottree.getChildNodes().size());
		//遍历多叉树，roottree 为多叉树的根节点，这个函数将根节点遍历给全局变量allmenu对象数组中
		DFStraverse(roottree,menuallapi);
		if(menuallapi.size()==0) {
			return superCom.SuperJson(menuallapi,"超级权限中的菜单管理出错！");
		}
		System.out.println(JSON.toJSONString(menuallapi));
		return JSON.toJSONString(menuallapi);
	}
	
	/**   
	* @Function: AdminMenuController.java
	* @Description: 将数据库的目录进行递归处理，创建多叉树
	*
	* @param:   menutree
	* @return：   多叉树的实体
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: dongsong
	* @date: 2019年6月27日 下午2:01:32 
	*/
	public boolean CreateTree(MenuTree menutree,List<AdminMenu> menuall) {
		if(menutree==null)
			return false;
		//先获取同等级节点
		List<AdminMenu> rootMenu = new ArrayList<AdminMenu>();
		for (AdminMenu res : menuall) {
			if (menutree.getNodeEntity().getId().equals(res.getPid())) {// 父节点是0的，为根节点。
				rootMenu.add(res);
			}
		}
		// 根据Menu类的order排序 ,对同等级节点进行排序
		Collections.sort(rootMenu, order());
		//对rootmenu继续创建节点
		for(AdminMenu res :rootMenu) {
			System.out.println("menutree.getNodeEntity().getId()---------"+menutree.getNodeEntity().getId()+"--------res.getPid()------"+res.getPid());
			if(menutree.getNodeEntity().getId().equals(res.getPid())) {
				System.out.println(JSON.toJSONString(res));
                if(Pattern.matches("^\\d00$",Integer.toString(res.getId()))) {
                	res.setCname("&nbsp;"+res.getName());
                }else if(Pattern.matches("^\\d{2}0$",Integer.toString(res.getId()))) {
                	res.setCname("&nbsp;&nbsp;&nbsp; ├"+res.getName());
                }else {
                	res.setCname("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; |└"+res.getName());
                }
				MenuTree leaf=new MenuTree();
				leaf.setNodeEntity(res);				
				menutree.addChildNode(leaf);
				CreateTree(leaf,menuall);	
			}
		}
		return true;		
	}

	@ResponseBody
	@RequestMapping(value="/ceshi",produces = "application/json;charset=UTF-8")
	public String ceshi() {
		List<AdminMenu> menuall = adminmenuservice.superapimenuall();
		List<AdminMenu> menuallapi =new ArrayList<AdminMenu>();
		//创建一个AdminMenu的对象作为多叉树的根的值
		AdminMenu root=new AdminMenu();
		root.setId(0);
		//创建跟节点
		MenuTree roottree =new MenuTree();
		roottree.setNodeEntity(root);
		//开始创建多叉树
		CreateTreeceshi(roottree,menuall);
		//遍历多叉树
		roottree.traverse();
		System.out.println(roottree.getChildNodes().size());
		//遍历多叉树，roottree 为多叉树的根节点，这个函数将根节点遍历给全局变量allmenu对象数组中
		DFStraverse(roottree,menuallapi);
		if(menuallapi.size()==0) {
			return superCom.SuperJson(menuallapi,"超级权限中的菜单管理出错！");
		}
		System.out.println(JSON.toJSONString(menuallapi));
		return JSON.toJSONString(menuallapi);
	}
	/**   
	* @Function: AdminMenuController.java
	* @Description: 将数据库的目录进行递归处理，创建多叉树
	*
	* @param:   menutree
	* @return：   多叉树的实体
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: dongsong
	* @date: 2019年6月27日 下午2:01:32 
	*/
	public boolean CreateTreeceshi(MenuTree menutree,List<AdminMenu> menuall) {
		if(menutree==null)
			return false;
		List<AdminMenu> rootMenu = new ArrayList<AdminMenu>();
		for (AdminMenu res : menuall) {
			if (menutree.getNodeEntity().getId().equals(res.getPid())) {// 父节点是0的，为根节点。
				rootMenu.add(res);
			}
		}
		/* 根据Menu类的order排序 */
		Collections.sort(rootMenu, order());
		
		for(AdminMenu res :rootMenu) {
			System.out.println("menutree.getNodeEntity().getId()---------"+menutree.getNodeEntity().getId()+"--------res.getPid()------"+res.getPid());
			if(menutree.getNodeEntity().getId().equals(res.getPid())) {
				System.out.println(JSON.toJSONString(res));
                if(Pattern.matches("^\\d00$",Integer.toString(res.getId()))) {
                	res.setCname("&nbsp;"+res.getName());
                }else if(Pattern.matches("^\\d{2}0$",Integer.toString(res.getId()))) {
                	res.setCname("&nbsp;&nbsp;&nbsp; ├"+res.getName());
                }else {
                	res.setCname("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; |└"+res.getName());
                }
				MenuTree leaf=new MenuTree();
				leaf.setNodeEntity(res);				
				menutree.addChildNode(leaf);
				CreateTreeceshi(leaf,menuall);	
			}
		}
		return true;		
	}
	
	
	
	/* 遍历一棵树，层次遍历 */ 
	
    /**   
    * @Function: AdminMenuController.java
    * @Description: 多叉树递归深度优先先序遍历
    *
    * @version: v1.0.0
    * @author: dongsong
    * @date: 2019年6月27日 下午2:04:15 
    */
    public void DFStraverse(MenuTree tree,List<AdminMenu> menuapi) {  
        if (tree.getChildNodes() == null || tree.getChildNodes().isEmpty())  
            return;
        int childNumber = tree.getChildNodes().size();  
        for (int i = 0; i < childNumber; i++) {  
        	MenuTree child = tree.getChildNodes().get(i);
        	menuapi.add(child.getNodeEntity());
        	DFStraverse(child,menuapi);  
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
					
}

