package com.succez.litr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
		f2b.file2Buf(f);
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
		f2b.file2Buf(f);
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
		f2b.file2Buf(f);
	}

	/**
	 * 验证输入的文件对象不存在时程序能否正常抛出异常
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test(expected = FileNotFoundException.class)
	public void testFileNotFoundException() throws IllegalArgumentException,
			IOException {
		File f = new File("c:/test.zip");
		f2b.file2Buf(f);
	}

	/**
	 * 验证读取各个长度的文件，输出的结果是否正确
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void test() throws IllegalArgumentException, IOException {
		test(0);
		test(1);
		test(1024);
		test(1024 * 4 - 1);
		test(1024 * 4);
		test(1024 * 4 + 1);
	}

	/**
	 * 验证读取给定长度的文件，输出结果是否正确
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	private void test(int length) throws IllegalArgumentException, IOException {

		File f = new File("C:/abc.txt");
		byte[] expected = new byte[length];
		for (int i = 0; i < expected.length; i++) {
			expected[i] = (byte) ('A' + i % 26);
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			fos.write(expected);
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		byte[] actuals = f2b.file2Buf(f);
		Assert.assertArrayEquals(expected, actuals);
	}

}
