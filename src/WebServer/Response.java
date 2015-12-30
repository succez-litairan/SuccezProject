package WebServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
	
	/**
	 * 存储构造Response的OutputStream对象
	 */
	private OutputStream output;
	
	/**
	 * 存储Request对象
	 */
	private Request request;

	/**
	 * 根据引入的OutputStream对象构建一个Response对象
	 * @param output OutputStream对象
	 */
	Response(OutputStream output) {
		this.output = output;
	}

	/**
	 * 将引入的Request对象存储到request中
	 * @param request Request对象
	 */
	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * 根据用户请求的路径给浏览器返回一个页面或文件的预览内容
	 * @throws IOException 在文件流读写过程中出错会抛出此异常
	 */
	public void sendResponse() throws IOException {
		File file = null;
		if (request.getUri() == null) {
			file = new File(WebServer.WEB_ROOT);
		} else {
			file = new File(WebServer.WEB_ROOT, request.getUri());
		}
		if (file.isDirectory()) {
			viewDir(file);
		} else if (file.isFile()) {
			viewFile(file);
		} else {
			output.write("文件不存在！".getBytes());
		}
	}

	/**
	 * 读取TXT或JPG文件的内容，如果文件不是上述文件类型则提示不提供预览
	 * @param file
	 * @throws IOException 当文件流读取出错时会抛出此异常
	 */
	private void viewFile(File file) throws IOException {
		if (file.getName().endsWith("txt") || file.getName().endsWith("jpg")) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				int length = (int) file.length();
				byte[] b = new byte[length];
				int i, count = 0;
				while (count < length
						&& (i = fis.read(b, count,
								Math.min(4096, length - count))) != -1) {
					count += i;
				}
				output.write(b);
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (Exception e) {
					}
				}
			}
		} else {
			output.write("此类型的文件不提供预览功能！".getBytes());
		}
	}

	/**
	 * 读取一个文件夹下的所有子目录和文件
	 * @param file File对象
	 * @throws IOException 当文件读取出错时会抛出此异常
	 */
	private void viewDir(File file) throws IOException {
		File[] list = file.listFiles();
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head></head><body>");
		for (int i = 0; i < list.length; i++) {
			sb.append("<p><a href=\"" + request.getUri() + list[i].getName()
					+ "/\">");
			sb.append(list[i].getName());
			sb.append("</a><p>");
		}
		sb.append("</body>");
		output.write(sb.toString().getBytes());

	}

}
