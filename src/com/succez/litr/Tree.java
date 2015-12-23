package com.succez.litr;

/**
 * 类Tree用于构建Node类的二叉树
 * @author 李泰然
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
	private String treelevelString = "";

	/**
	 * 此函数根据输入的字符串来创建树
	 * @param s 不为空和""的字符串，例如"ABC"
	 * @return Node对象
	 * @throws IllegalArgumentException 当输入的字符串为空或者""时会抛出此异常
	 */
	public Node createTree(String s) {
		Node root = new Node();
		if (s == null || s == "") {
			throw new IllegalArgumentException("树不能为空！");
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

	/**根据给定树的根节点，返回树的层数，树的层数至少为1
	 * @param root 树的根节点
	 * @return 返回树的层数
	 */
	public int getTreeLevel(Node root) {
		if (root == null)
			return 0;
		int totalLevel = 1;
		int leftTotalLevel = 1;
		int rightTotalLevel = 1;
		if (root.leftNode != null) {
			leftTotalLevel += getTreeLevel(root.leftNode);

		}
		if (root.rightNode != null) {
			rightTotalLevel += getTreeLevel(root.rightNode);
		}
		totalLevel = leftTotalLevel > rightTotalLevel ? leftTotalLevel
				: rightTotalLevel;
		return totalLevel;
	}

	/**
	 * 函数TreeLevel用于从左到右输出给定树的对应层数节点的值
	 * @param node Node对象
	 * @param len len为大于1的整数且不大于树最大层数的整数
	 * @return 返回一个对应节点的值组成的字符串
	 * @throws IllegalArgumentException 当输入的树的层数len为0或者超过树的最大层数是会抛出此异常 
	 */
	public String TreeLevel(Node node, int len) {
		if (len == 0) {
			throw new IllegalArgumentException("树没有0层！");
		}
		if (len > this.getTreeLevel(node)) {
			throw new IllegalArgumentException("树没有第" + len + "层！");
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
