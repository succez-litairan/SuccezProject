package com.succez.litr;

public class IntToHex {

	/**
	 * 函数intToHex对输入的整数n进行分类处理再调用posToHex
	 * @param n 一个整数
	 * @return String 一个字符串对象
	 */
	public static String intToHex(int n) {

		if (n == Integer.MIN_VALUE) {
			return "-80000000";
		} else {
			if (n == 0) {
				return "0";
			}
			char[] c = new char[9];
			int i;
			boolean isPos = n > 0;
			if (!isPos) {
				n = -n;
			}
			for (i = 0; i < 8; i++) {
				c[8 - i] = HEX_CHARS[n & 15];
				if ((n >>= 4) == 0)// 增加判定条件，当整数的高位全部为零时，直接跳出for循环减少程序的运行次数
					break;
			}
			if (!isPos) {
				c[8 - (++i)] = '-';
			}
			return String.valueOf(c, 8 - i, i + 1);
		}

	}

	private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

}
