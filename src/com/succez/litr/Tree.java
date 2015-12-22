package com.succez.litr;

/**
 * 类Tree用于构建Node类的二叉树
 * @author John
 * @date 2015年12月18日
 */
public class Tree {

	/**
	 * 遍历字符串时使用
	 */
	private int index = 0;
	
	/**
	 * treelevelString 用于保存每层中各个节点值
	 */
	private String treelevelString="";
	
	/**
	 * 此函数根据输入的字符串来创建树
	 * @param s 一个字符串
	 * @return Node Node对象
	 * @throws Exception 
	 */
	public Node createTree(String s) throws Exception {

		Node root = new Node();
		if (s == null || s == "") {
			throw new Exception("树不能为空！");
		}
		if (index < s.length()) {
			root.value = s.charAt(index);
			if (s.charAt(++index) != '#') {
				root.leftNode = createTree(s);
			} else {
				root.leftNode = null;
			}
			if (s.charAt(++index) != '#') {
				root.rightNode = createTree(s);
			} else {
				root.rightNode = null;
			}
		}
		return root;
	}
	
	/**给定树的根节点，返回树的层数
	 * @param root
	 * @return
	 */
	public int getTreeLevel(Node root)
	{
		if(root == null)
			return 0;
		int totalLevel = 1;
		int leftTotalLevel = 1;
		int rightTotalLevel = 1;
		if(root.leftNode != null)
		{
			leftTotalLevel += getTreeLevel(root.leftNode);
			
		}
		if(root.rightNode != null)
		{
			rightTotalLevel += getTreeLevel(root.rightNode);
		}
		totalLevel = leftTotalLevel > rightTotalLevel ? leftTotalLevel : rightTotalLevel;
		return totalLevel;
	}

	/**
	 * 此函数用于从左到右输出对应节点的值
	 * @param node
	 * @param len
	 * @return
	 * @throws Exception 
	 */
	public String TreeLevel(Node node, int len) throws Exception {
		if (len==0){
			throw new Exception("树没有0层！");
		}
		if(len>this.getTreeLevel(node)){
			throw new Exception("树没有第"+len+"层！");
		}
		if (len == 1) {
			treelevelString += node.value;
		} else {
			if (node.leftNode != null) {
				TreeLevel(node.leftNode, len - 1);
			}
			if (node.rightNode != null) {
				TreeLevel(node.rightNode, len - 1);
			}
		}
		return treelevelString;
	}

}
