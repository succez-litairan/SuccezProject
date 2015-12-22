package com.succez.litr;

import junit.framework.TestCase;

import org.junit.Test;

public class TestIntToHex {
	
	/**
	 * 用于测试intTohex能否返回正确的16进制字符串
	 */
	@Test
	public void test() {
		TestCase.assertEquals("-1测试未通过","-1",IntToHex.intToHex(-1));
		TestCase.assertEquals("测试未通过","0",IntToHex.intToHex(0));
		TestCase.assertEquals("测试未通过","7FFFFFFF",IntToHex.intToHex(Integer.MAX_VALUE));
		TestCase.assertEquals("测试未通过","-80000000",IntToHex.intToHex(Integer.MIN_VALUE));
		TestCase.assertEquals("测试未通过","10",IntToHex.intToHex(16));
		TestCase.assertEquals("测试未通过","-100",IntToHex.intToHex(-256));
		TestCase.assertEquals("测试未通过","2000",IntToHex.intToHex(8192));
		TestCase.assertEquals("测试未通过","-30000",IntToHex.intToHex(-196608));
		TestCase.assertEquals("测试未通过","400000",IntToHex.intToHex(4194304));
		TestCase.assertEquals("测试未通过","-5000000",IntToHex.intToHex(-83886080));
		TestCase.assertEquals("测试未通过","60000000",IntToHex.intToHex(1610612736));
	}

}
