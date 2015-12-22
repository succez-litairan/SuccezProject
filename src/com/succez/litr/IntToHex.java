package com.succez.litr;

public class IntToHex {

	/**
	 * 函数intToHex对输入的整数n进行分类处理再调用posToHex
	 * 
	 * @param n
	 *            一个整数
	 * @return String 一个字符串对象
	 */
	public static String intToHex(int n) {

		if (n == -0x8000000) {
			return "-80000000";
		} else if (n >= 0) {
			return posToHex(n);
		} else {
			return '-'+posToHex(-n);
		}

	}

	private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
			'B', 'C', 'D', 'E', 'F' };
	/**
	 * 函数posToHex用将一个正数转化成一个char数组
	 * 
	 * @param n
	 *            n必须为0或者正数
	 * @return char[]
	 */
	private static String posToHex(int n) {
		char[] c = new char[9];
		for (int i = 0; i < 9; i++) {
			c[8 - i] = HEX_CHARS[n & 15];
			if ((n >>= 4) == 0)// 增加判定条件，当整数的高位全部为零时，直接跳出for循环减少程序的运行次数
				break;
		}
		StringBuilder sb= new StringBuilder();
		for (int i = 0; i < c.length; i++) {
			if (c[i]!='\0') {
				sb.append(c[i]);
			}
		}
		return sb.toString();
	}
}
