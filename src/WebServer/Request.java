package WebServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

/**
 * 用于解析socket中InputStream的原始信息，并根据其原始信息解析出用户请求的目录或文件
 * <pre>
 * Request request = new Request(InputStream input);
 * </pre>
 * @author 李泰然
 */
public class Request {

	/**
	 * 存储构建Request对象时所引入的InputStream对象
	 */
	private InputStream input = null;

	/**
	 * 存储InputStream中解析出的用户请求的路径
	 */
	private String uri = null;

	/**
	 * 根据引入的InputStream对象构建一个Request对象，并将引入的InputStream对象存储到Request对象的input中
	 * @param input InputStream对象
	 */
	Request(InputStream input) {
		this.input = input;
	}

	/**
	 * 获取InputStream对象中HTTP请求的原始信息，并将结果交给parseUri()做进一步解析
	 * @throws IOException 当读取文件出错或流关闭出错时会抛出此异常
	 */
	public void parse() throws IOException {
		byte[] b = new byte[1024];
		StringBuilder req = new StringBuilder(1024);
		int i;

		i = input.read(b);
		for (int j = 0; j < i; j++) {
			req.append((char) b[j]);
		}

		System.out.print(req.toString());

		uri = URLDecoder.decode(parseURI(new String(b)), "UTF-8");

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
		return null;
	}

	/**
	 * 获取Request对象中uri
	 * @return String对象
	 */
	public String getUri() {
		return this.uri;
	}
}
