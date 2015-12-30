package WebServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	
	/**
	 * 保存WebServer的根目录
	 */
	static final String WEB_ROOT = "C:/Users/John/SuccezProject/src/WebServer/webroot";

	/**
	 * 控制服务器的关闭
	 */
	boolean shutdown = false;

	
	public static void main(String[] args) throws IOException {
		WebServer webserver = new WebServer();
		webserver.await();
	}

	/**
	 * 创建一个服务器套接字并侦听8000端口的用户请求
	 * @throws IOException 
	 */
	public void await() throws IOException {
		ServerSocket serverSocket = new ServerSocket(8000, 1,
				InetAddress.getByName("127.0.0.1"));
		System.out.println("服务器已运行！");
		while (!shutdown) {
			Socket socket =serverSocket.accept();
			InputStream input = null;
			OutputStream output = null;
			
			input = socket.getInputStream();
			output = socket.getOutputStream();

			Request request = new Request(input);
			request.parse();

			Response response = new Response(output);
			response.setRequest(request);
			response.sendResponse();
			
			socket.close();
		}
		serverSocket.close();
	}
}
