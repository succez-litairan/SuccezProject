package com.succez.litr;

import junit.framework.TestCase;

import org.junit.Test;

public class TestIntToHex {

	/**
	 * 用于测试intTohex能否返回正确的16进制字符串
	 */
	@Test
	public void test() {
		TestCase.assertEquals("-1测试未通过", "-1", IntToHex.intToHex(-1));
		TestCase.assertEquals("0测试未通过", "0", IntToHex.intToHex(0));
		TestCase.assertEquals("5测试未通过", "5", IntToHex.intToHex(5));
		TestCase.assertEquals("Integer.MAX_VALUE测试未通过", "7FFFFFFF",
				IntToHex.intToHex(Integer.MAX_VALUE));
		TestCase.assertEquals("Integer.MIN_VALUE测试未通过", "-80000000",
				IntToHex.intToHex(Integer.MIN_VALUE));
		TestCase.assertEquals("0x10测试未通过", "10", IntToHex.intToHex(0x10));
		TestCase.assertEquals("-0x100测试未通过", "-100", IntToHex.intToHex(-0x100));
		TestCase.assertEquals("0x2000测试未通过", "2000", IntToHex.intToHex(0x2000));
		TestCase.assertEquals("-0x30000测试未通过", "-30000",
				IntToHex.intToHex(-0x30000));
		TestCase.assertEquals("0x400000测试未通过", "400000",
				IntToHex.intToHex(0x400000));
		TestCase.assertEquals("-0x5000000测试未通过", "-5000000",
				IntToHex.intToHex(-0x5000000));
		TestCase.assertEquals("0x23456789测试未通过", "23456789",
				IntToHex.intToHex(0x23456789));
		TestCase.assertEquals("0x1ABCDEF测试未通过", "1ABCDEF",
				IntToHex.intToHex(0x1ABCDEF));
	}

}
