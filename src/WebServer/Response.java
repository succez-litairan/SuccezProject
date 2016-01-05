package WebServer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Response {

	/**
	 * 存储Socket对象中的OutputStream信息
	 */
	private PrintStream output;

	/**
	 * 根据引入的Socket对象构建一个Response对象
	 * @param socket Socket对象
	 * @throws IOException
	 */
	Response(Socket socket) throws IOException {
		this.output = new PrintStream(socket.getOutputStream());
	}

	/**
	 * 获取Response对象中的输出流
	 * @return PrintStream对象
	 */
	public PrintStream getOutput() {
		return this.output;
	}

}
