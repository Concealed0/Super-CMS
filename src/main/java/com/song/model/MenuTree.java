/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.song.dao 
 * @author: dongsong   
 * @date: 2019年6月26日 下午3:01:46 
 */
package com.song.model;


import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.song.model.AdminMenu;

/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: Node.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: dongsong
* @date: 2019年6月26日 下午3:01:46 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月26日     dongsong           v1.0.0               修改原因
*/
public class  MenuTree{

	private AdminMenu nodeEntity;
	private MenuTree parentNode;
	private List<MenuTree> childNodes;  

	public MenuTree(AdminMenu nodeEntity) { 
		this.getParentNode();  
        initChildList();  
	}
	
	
	public MenuTree () {
		initChildList();
	}

    /* 插入一个child节点到当前节点中 */  
	public void addChildNode(MenuTree childNode){
		initChildList();
        this.childNodes.add(childNode);
    }
	
	 public boolean isLeaf() {  
	        if (this.childNodes==null) {  
	            return true;  
	        } else {  
	            if (this.childNodes.isEmpty()) {  
	                return true;  
	            } else {  
	                return false;  
	            }  
	        }  
	    }  
	 public boolean isValidTree() {  
	        return true;  
	    }  
	 public void initChildList() {  
		 if ( this.childNodes==null){
	            this.childNodes = new ArrayList<MenuTree>();
	        }
	    }  
	
	 /* 返回当前节点的父辈节点集合 */  
	    public List<MenuTree> getElders() {  
	        List<MenuTree> elderList = new ArrayList<MenuTree>();  
	        MenuTree parentNode = this.getParentNode();  
	        if (parentNode == null ) {  
	            return elderList;  
	        } else {  
	            elderList.add(parentNode);  
	            elderList.addAll(parentNode.getElders());  
	            return elderList;  
	        }  
	    }  
	    /* 返回当前节点的孩子集合 */  
	    public List<MenuTree> getChildList() {  
	        return childNodes;  
	    }  
	    /* 动态的插入一个新的节点到当前树中 */  
	    public boolean insertJuniorNode(MenuTree treeNode) {  
	        AdminMenu juniorParentId = treeNode.getNodeEntity();  
	        if (this.nodeEntity == juniorParentId) {  
	            addChildNode(treeNode);  
	            return true;  
	        } else {  
	            List<MenuTree> childList = this.getChildList();  
	            int childNumber = childList.size();  
	            boolean insertFlag;  
	  
	            for (int i = 0; i < childNumber; i++) {  
	            	MenuTree childNode = childList.get(i);  
	                insertFlag = childNode.insertJuniorNode(treeNode);  
	                if (insertFlag == true)  
	                    return true;  
	            }  
	            return false;  
	        }  
	    }  
	    
	    /* 遍历一棵树，层次遍历 */  
	    public void traverse() {  
	        if (childNodes == null || childNodes.isEmpty())  
	            return;  
	        int childNumber = childNodes.size();  
	        for (int i = 0; i < childNumber; i++) {  
	        	MenuTree child = childNodes.get(i);
	        	System.out.println(JSON.toJSONString(child.nodeEntity));
	            child.traverse();  
	        }  
	    }  
	    
	public MenuTree getParentNode() {
        return parentNode;
    }
 
    public void setParentNode(MenuTree parentNode) {
        this.parentNode = parentNode;
    }
 
    public AdminMenu getNodeEntity() {
        return nodeEntity;
    }
 
    public void setNodeEntity(AdminMenu nodeEntity) {
        this.nodeEntity = nodeEntity;
    }
 
    public List<MenuTree> getChildNodes() {
        return childNodes;
    }
 
    public void setChildNodes(List<MenuTree> childNodes) {
        this.childNodes = childNodes;
    }


}