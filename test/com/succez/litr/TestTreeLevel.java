package com.succez.litr;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTreeLevel {
	
	Tree tree=new Tree();
	
	/**
	 * 此用例用于测试输入的字符串为null时，能否抛出异常
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testException() throws Exception{
		tree.createTree(null);
	}
	
	/**
	 * 此用例用于测试输入的字符串为""时，能否抛出异常
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testException2() throws Exception{
		tree.createTree("");
	}
	
	/**
	 * 此用例用于测试输入树的层数为0时，能否抛出异常
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testException3() throws Exception{
		
		tree.TreeLevel(tree.createTree("A##"), 0);
	}
	/**
	 * 此用例用于测试输入树的层数超过最大层数时，能否抛出异常
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testException4() throws Exception{
		
		tree.TreeLevel(tree.createTree("A##"), 2);
	}
	
	/**
	 * 此用例用于测试输入的字符串为"A##"时，能否返回树的第一层节点的值
	 * @throws Exception
	 */
	@Test
	public void testTreeLevel1() throws Exception{
		
		assertEquals("A", tree.TreeLevel(tree.createTree("A##"), 1));
	}
	
	/**
	 * 此用例用于测试输入的字符串为"AB###"时，能否返回树的第二层节点的值
	 * @throws Exception
	 */
	@Test
	public void testTreeLevel2() throws Exception{
		
		assertEquals("B", tree.TreeLevel(tree.createTree("AB###"), 2));
	}
	
	/**
	 * 此用例用于测试输入的字符串为"AB##C##"时，能否返回树的第二层节点的值
	 * @throws Exception
	 */
	@Test
	public void testTreeLevel3() throws Exception{
		
		assertEquals("BC", tree.TreeLevel(tree.createTree("AB##C##"), 2));
	}
	
	/**
	 * 此用例用于测试输入的字符串为"ABE###C#D##"时，能否返回树的第三层节点的值
	 * @throws Exception
	 */
	@Test
	public void testTreeLevel4() throws Exception{
		
		assertEquals("ED", tree.TreeLevel(tree.createTree("ABE###C#D##"), 3));
	}
}
