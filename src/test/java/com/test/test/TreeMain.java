/**   
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.test.test 
 * @author: dongsong   
 * @date: 2019年6月25日 下午5:01:30 
 */
package com.test.test;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
*运行主程序
*/
public class TreeMain {

    /**
     * 二叉排序树；左边是小于等于，右边是大于根节点。
     * @param root
     * @param data
     * @return
     */
    public static Tree insert(Tree root, int data) {
        if (root == null) {
            return new Tree(data);//新建树节点
        }else {
            Tree cur;
            if (data <= root.data) {//小的放在左侧
                cur = insert(root.treeLeft,data);//递归一直到root为空时，调用第一个IF实现新建树节点
                root.treeLeft = cur;
            }else {//大的放在右侧
                cur = insert(root.treeRight,data);
                root.treeRight = cur;
            }
            return root;
        }
    }

    /**
     * 深度优先遍历分为，先序、中序和后序遍历
     */

    /**
     * 递归法实现先序遍历，并打印
     * @param root
     */
    public static void preOrder(Tree root){
        if(root == null) return;
        System.out.print(root.data + " ");
        preOrder(root.treeLeft);
        preOrder(root.treeRight);
    }

    /**
     * 递归法实现中序遍历，并打印
     * @param root
     */
    public static void inOrder(Tree root){
        if(root == null) return;
        inOrder(root.treeLeft);//用递归的方法一直找到树的最左侧
        System.out.print(root.data + " ");
        inOrder(root.treeRight);
    }

    /**
     * 递归方法实现后序遍历，并打印
     * @param root
     */
    public static void postOrder(Tree root){
        if(root == null) return;
        postOrder(root.treeLeft);
        postOrder(root.treeRight);
        System.out.print(root.data + " ");
    }

    /**
     * 非递归方法实现先序遍历，并打印
     * @param root
     */
    public static void preOrder2(Tree root){
        LinkedList<Tree> stack = new LinkedList<>();
        Tree currentRoot = null;
        stack.push(root);
        while (!stack.isEmpty()){
            currentRoot = stack.pop();
            System.out.print(currentRoot.data + " ");
            //栈是先入后出，需要先入栈右分支
            if (currentRoot.treeRight != null){
                stack.push(currentRoot.treeRight);
            }
            if (currentRoot.treeLeft != null){
                stack.push(currentRoot.treeLeft);
            }
        }
    }

    /**
     * 非递归方法实现中序遍历，并打印
     * @param root
     */
    public static void inOrder2(Tree root){
        LinkedList<Tree> stack = new LinkedList<>();
        Tree currentRoot = root;
        while (currentRoot !=null || !stack.isEmpty()){
            //遍历到二叉树的最左侧
            while (currentRoot != null){
                stack.push(currentRoot);
                currentRoot = currentRoot.treeLeft;
            }
            currentRoot = stack.pop();
            System.out.print(currentRoot.data + " ");
            currentRoot = currentRoot.treeRight;
        }
    }

    /**
     * 非递归方法实现后序遍历，并打印
     * @param root
     */
    public static void postOrder2(Tree root){
        LinkedList<Tree> stack = new LinkedList<>();
        Tree currentRoot = root;
        Tree rightRoot = null;
        while (currentRoot != null || !stack.isEmpty()){
            while (currentRoot != null){
                stack.push(currentRoot);
                currentRoot = currentRoot.treeLeft;
            }
            currentRoot = stack.pop();
            //当前节点没有右节点或上一个结点（已经输出的结点）是当前结点的右结点，则输出当前结点
            while (currentRoot.treeRight == null || currentRoot.treeRight == rightRoot){
                System.out.print(currentRoot.data + " ");
                rightRoot = currentRoot;
                if (stack.isEmpty()){
                    return;
                }
                currentRoot = stack.pop();
            }
            stack.push(currentRoot);//还有未遍历的右侧节点
            currentRoot = currentRoot.treeRight;
        }
    }

    /**
     * 对列法实现二叉树广度优先遍历，队列遵循先进先出的规则，适合本方法
     * @param root
     */
    public static void levelOrer(Tree root){
        Queue<Tree> queue = new LinkedList<Tree>();//新增队列

        if(root != null){
            queue.add(root);//将根节点加入队列
        }

        while (!queue.isEmpty()){
            Tree cur = queue.peek();//创建cur的目的是在while循环的时候逐层将树带入，如果直接用root,会导致只能输出一级树
            System.out.print(cur.data + " ");
            queue.remove();
            if (cur.treeLeft != null){
                queue.add(cur.treeLeft);//先将左分支加入队列，之后先输出
            }
            if(cur.treeRight != null){
                queue.add(cur.treeRight);
            }
        }

    }

    /**
     * main函数，输入输出，遍历
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字的个数：");
        int T = sc.nextInt();
        int[] t = new int[T];
        System.out.println("请输入数字，以空格分隔：");
        for (int i = 0; i < T; i++) {
            t[i] = sc.nextInt();
        }

        Tree root = null;
        for (int i = 0; i < T; i++) {
            root = insert(root,t[i]);
        }
        System.out.println("递归先序遍历：");
        preOrder(root);
        System.out.println();
        System.out.println("递归中序遍历：");
        inOrder(root);
        System.out.println();
        System.out.println("递归后序遍历：");
        postOrder(root);
        System.out.println();
        System.out.println("非递归先序遍历：");
        preOrder2(root);
        System.out.println();
        System.out.println("非递归中序遍历：");
        inOrder2(root);
        System.out.println();
        System.out.println("非递归后序遍历：");
        postOrder2(root);
        System.out.println();
        System.out.println("广度优先遍历：");
        levelOrer(root);

    }
}