package com.succez.litr;

public class IntToHex {

	/**
	 * 返回一个整数的十六进制字符串
	 * <pre>
	 *  intToHex(-100) == "-64"
	 *  intToHex(255) == "FF"
	 * </pre>
	 * @param n 一个整数,取回范围为-0x80000000~0x7FFFFFFF
	 * @return 字符串
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

	/**
	 * 字符数组HEX_CHARS用于存储字符'0'~'F'
	 */
	private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

}
