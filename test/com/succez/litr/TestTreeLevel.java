package com.succez.litr;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTreeLevel {

	Tree tree = new Tree();

	/**
	 * 此用例用于测试输入的字符串为null时，能否抛出异常
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testException() throws Exception {
		tree.createTree(null);
	}

	/**
	 * 此用例用于测试输入的字符串为""时，能否抛出异常
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testException2() throws Exception {
		tree.createTree("");
	}

	/**
	 * 此用例用于测试输入树的层数为0时，能否抛出异常
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testException3() throws Exception {
		tree.TreeLevel(tree.createTree("A##"), 0);
	}

	/**
	 * 此用例用于测试输入树的层数超过最大层数时，能否抛出异常
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testException4() throws Exception {

		tree.TreeLevel(tree.createTree("A##"), 2);
	}

	/**
	 * 此用例用于测试TreeLevel能否返回对应层数的节点
	 * @throws Exception
	 */
	@Test
	public void testTreeLevel() throws IllegalArgumentException {

		assertEquals("A", tree.TreeLevel(tree.createTree("A##"), 1));
		assertEquals("B", tree.TreeLevel(tree.createTree("AB###"), 2));
		assertEquals("BC", tree.TreeLevel(tree.createTree("AB##C##"), 2));
		assertEquals("DEG", tree.TreeLevel(tree.createTree("ABD##E##C#G##"), 3));
		assertEquals("C", tree.TreeLevel(tree.createTree("ABCD#####"), 3));
		assertEquals("D", tree.TreeLevel(tree.createTree("AB#CD####"), 4));
		assertEquals("BC", tree.TreeLevel(tree.createTree("AB#E##C##"), 2));
		assertEquals("BC", tree.TreeLevel(tree.createTree("ABE###C#D##"), 2));
		assertEquals("DEFG",
				tree.TreeLevel(tree.createTree("ABD##E##CF##G##"), 3));
	}

}
