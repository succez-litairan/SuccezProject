package WebServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class RequestHandler {

	private static Logger logger = Logger.getLogger(RequestHandler.class);
	private Request request;
	private Response response;

	private long startRange = 0;
	private long endRange = 0;

	/**
	 * 通过引入的Request和Response对象构建一个RequestHandler对象
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public RequestHandler(Request request, Response response)
			throws IOException {
		this.request = request;
		this.response = response;
	}

	/**
	 * 根据用户请求的路径给浏览器返回一个页面或文件的预览内容
	 * @throws IOException 
	 * @throws Exception 
	 */
	public void run() throws IOException {
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
			print("文件不存在！");
		}
	}

	/**
	 * 读取一个文件夹下的所有子目录和文件
	 * @param file File对象
	 * @throws IOException 当文件读取出错时会抛出此异常
	 */
	private void viewDir(File file) throws IOException {
		File[] files = file.listFiles();
		print("HTTP/1.1 200 OK");
		print("MIME_version:1.0");
		print("Content-Type:text/html");
		print("");

		print("<html><head><title>WebServer</title></head><body>");
		for (File f : files) {
			if (f.isDirectory()) {
				print("<br><a href=\"" + request.getUri() + f.getName() + "/\""
						+ ">" + f.getName() + "</a><br>");
			} else {
				print("<br><a href=\"" + request.getUri() + f.getName() + "\""
						+ " " + ">" + f.getName() + "</a>"
						+ "&nbsp&nbsp&nbsp<a href=\"" + request.getUri()
						+ f.getName() + "\"" + " download=\"" + f.getName()
						+ "\">下载</a><br>");
			}
		}
		print("</body></html>");

	}

	/**
	 * 读取TXT或JPG或PDF文件的内容，如果文件不是上述文件类型则提示不提供预览
	 * @param file
	 * @throws IOException 当文件流读取出错时会抛出此异常
	 */
	public void viewFile(File file) throws IOException {
		int indexOfRange = request.getReq().indexOf("Range");
		String fileType = Utils.getFileType(file);
		File fileTypeIni = new File(System.getProperty("user.dir")
				+ "\\src\\WebServer", "FileType.ini");
		Properties properties = new Properties();
		properties.load(new FileInputStream(fileTypeIni));
		String contentType = properties.getProperty(fileType);

		if (indexOfRange == -1) {
			downloadFile(file, contentType);
		} else {
			downloadPartFile(file, contentType);
		}
	}

	/**
	 * 下载整个文件
	 * @param file
	 * @throws IOException
	 */
	private void downloadFile(File file, String contentType) throws IOException {

		print("HTTP/1.1 200 OK");
		print("MIME_version:1.0");
		print("Content-Length:" + file.length());
		print("Content_Type:" + contentType + "charset = UTF-8");
		print("");
		response.getOutput().write(Utils.fileToBuf(file));

		logger.info("HTTP/1.1 200 OK");
		logger.info("MIME_version:1.0");
		logger.info("Content-Length:" + file.length());
		logger.info("Content_Type:" + contentType + "charset = UTF-8");

	}

	/**
	 * 断点续传时下载文件
	 * @param file
	 * @throws IOException
	 */
	private void downloadPartFile(File file, String contentType)
			throws IOException {
		getRange();
		print("HTTP/1.1 206 OK");
		print("MIME_version:1.0");
		print("Content-Range: bytes " + startRange + '-' + endRange + '/'
				+ file.length());
		print("Content-Length: " + (endRange - startRange + 1));
		print("Content_Type:" + contentType + "charset = UTF-8");
		print("");
		response.getOutput().write(Utils.fileToBuf(file), (int) startRange,
				(int) (endRange - startRange + 1));

		logger.info("HTTP/1.1 206 OK");
		logger.info("MIME_version:1.0");
		logger.info("Content-Range: bytes " + startRange + '-' + endRange + '/'
				+ file.length());
		logger.info("Content-Length: " + (endRange - startRange + 1));
		logger.info("Content_Type:" + contentType + "charset = UTF-8");

	}

	/**
	 * 断点续传时获取HTTP请求中的range
	 */
	private void getRange() {
		String req = request.getReq();
		int Bytes = req.indexOf("bytes");
		String startRange = req.substring(req.indexOf('=', Bytes) + 1,
				req.indexOf('-', Bytes));
		String endRange = req.substring(req.indexOf('-', Bytes) + 1,
				req.indexOf('\r', Bytes));
		this.startRange = Long.parseLong(startRange);
		this.endRange = Long.parseLong(endRange);
	}

	/**
	 * response.getOutput().println()的缩写
	 * @param s
	 */
	private void print(String s) {
		response.getOutput().println(s);
	}
}
