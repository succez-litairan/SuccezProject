package WebServer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Utils {
	/**
	 * 获取文件的文件类型
	 * @param file
	 * @return String
	 */
	public static String getFileType(File file) {
		String fileName = file.getName();
		if(fileName.indexOf('.')!=-1){
			return fileName.substring(fileName.indexOf('.'));
		}else {
			return "";
		}
			
	}
	
	/**
	 * 将文件读取到byte数组中
	 * @param file
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] fileToBuf(File file) throws IOException{
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			int intLen = (int) file.length();
			byte[] bytearray = new byte[intLen];
			int count = 0;
			int n;
			while (count < intLen
					&& (n = bis.read(bytearray, count,
							Math.min(4096, intLen - count))) != -1) {
				count += n;
			}
			return bytearray;
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (Exception e) {
				}
			}
		}
	}
}
