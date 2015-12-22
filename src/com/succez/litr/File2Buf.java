package com.succez.litr;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class File2Buf {
	/**
	 * 函数file2buf用于读取一个文件的内容并将其转化为数组
	 * 
	 * @param f 一个文件对象
	 * @return byte[] 一个数组
	 * @throws IllegalArgumentException 在传入的file对象为空、传入的文件对象是一个目录或者读取的文件大于2G时会抛出此异常
	 * @throws IOException 当文件读取过程中出现错误时会抛出此异常
	 * @time 2015年12月15日09:57:42
	 */
	public byte[] file2Buf(File f) throws IOException, IllegalArgumentException {
		if (f == null) {
			throw new IllegalArgumentException("没有传入file对象");
		}
		if (f.isDirectory()) {
			throw new IllegalArgumentException("传入的file对象\""
					+ f.getAbsolutePath() + "\"为一个目录");
		}
		long length = f.length();
		if (length > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("读取的文件超过2G！");
		}
		byte[] bytearray;
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(f));
			int intLen = (int) length;
			bytearray = new byte[(int) length];
			int count = 0;
			int n;
			while (count < intLen && (n = bis.read(bytearray, count, Math.min(4096, intLen - count))) != -1) {
				count += n;
			}
			
//			while (count < intLen && (n = bis.read(bytearray, count, Math.min(4096, intLen - count))) != -1) {
//				count += n;
//			}

		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (Exception e) {
				}
			}
		}

		return bytearray;
	}
}
