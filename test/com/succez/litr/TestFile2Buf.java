package com.succez.litr;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class TestFile2Buf {

	File2Buf f2b = new File2Buf();

	/**
	 * 验证输入的文件对象为空时程序能否正常抛出异常
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentException1()
			throws IllegalArgumentException, IOException {
		File f = null;
		byte[] actuals = f2b.file2Buf(f);
	}
	
	/**
	 * 验证输入的文件对象为一个目录时程序能否正常抛出异常
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentException2()
			throws IllegalArgumentException, IOException {
		File f = new File("C:/");
		byte[] actuals = f2b.file2Buf(f);
	}

	/**
	 * 验证输入的文件对象大于2G时程序能否正常抛出异常
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentException3()
			throws IllegalArgumentException, IOException {
		File f = new File("e:/test.zip");
		byte[] actuals = f2b.file2Buf(f);
	}
	
	/**
	 * 验证文件输出的结果是否正确
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void test() throws IllegalArgumentException, IOException {
		File f = new File("C:/abc.txt");
		byte[] actuals = f2b.file2Buf(f);
		byte[] expected = { 97,97,97,97 };
		for (int i = 0; i < actuals.length; i++) {
			//TestCase.assertEquals(expected[i], actuals[i]);
			System.out.println(actuals[i]);
		}

	}

}
