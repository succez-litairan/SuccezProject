package WebServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URLDecoder;

import org.apache.log4j.Logger;

/**
 * 用于解析socket中InputStream的原始信息，并根据其原始信息解析出用户请求的目录或文件
 * <pre>
 * Request request = new Request(InputStream input);
 * </pre>
 * @author 李泰然
 */
public class Request {

	private static Logger logger=Logger.getLogger(Request.class);
	/**
	 * 存储构建Request对象时所引入的InputStream对象
	 */
	private InputStream input = null;

	/**
	 * 存储InputStream中解析出的用户请求的路径
	 */
	private String uri = null;

	/**
	 * 存储Request请求的原始信息
	 */
	private StringBuilder req;

	/**
	 * 根据引入的Socket对象构建一个Request对象，并将引入的InputStream对象存储到Request对象的input中
	 * @param input InputStream对象
	 */
	Request(Socket socket) throws IOException {
		this.input = socket.getInputStream();
		parse();
	}

	/**
	 * 获取InputStream对象中HTTP请求的原始信息，并将结果交给parseUri()做进一步解析
	 * @throws IOException 当读取文件出错或流关闭出错时会抛出此异常
	 */
	private void parse() throws IOException {
		byte[] b = new byte[1024];
		req = new StringBuilder(4096);
		int i;

		i = input.read(b);
		for (int j = 0; j < i; j++) {
			req.append((char) b[j]);
		}

		logger.info("用户请求全部信息: " + req.toString());

		uri = URLDecoder.decode(parseURI(new String(b)), "UTF-8");

		logger.info("用户请求的路径：" + uri);
	}

	/**
	 * 截取一个字符串中前两个空格之间的子字符串
	 * <pre>
	 * parseUri("AJF JAKJ FDA K J KA") == "JAKJ"
	 * parseUri("AJFJAKJFDAKJKA") == null
	 * </pre>
	 * @param request String对象
	 * @return String对象
	 */
	private String parseURI(String request) {
		int index1, index2;
		index1 = request.indexOf(" ");
		if (index1 != -1) {
			index2 = request.indexOf(" ", index1 + 1);
			if (index2 > index1) {
				return request.substring(index1 + 1, index2);
			}
		}
		return "/";
	}

	/**
	 * 获取Request对象中uri
	 * @return String对象
	 */
	public String getUri() {
		return this.uri;
	}

	/**
	 * 获取Request中的原始信息
	 * @return String对象
	 */
	public String getReq() {
		return this.req.toString();
	}
}
