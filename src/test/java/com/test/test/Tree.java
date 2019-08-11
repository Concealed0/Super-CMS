/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.test.test 
 * @author: dongsong   
 * @date: 2019年6月25日 下午5:12:43 
 */
package com.test.test;



	/**
	*实体类
	*/
	public class Tree {

	    Tree treeLeft;
	    Tree treeRight;//创建了树的两个分支，声明类型自己本身树，目的是每新建一个分支依旧为树的一个节点
	    int data;

	    Tree(int data){
	        this.data = data;//data在这里是树节点（或分支）的值
	        treeLeft = treeRight = null;
	    }
	}

