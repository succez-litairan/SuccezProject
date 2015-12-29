package WebServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	static final String WEB_ROOT = "C:/Users/John/SuccezProject/src/WebServer/webroot";

	boolean shutdown = false;

	public static void main(String[] args) throws IOException {
		WebServer webserver = new WebServer();
		webserver.await();
	}

	public void await() throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8001, 1,
					InetAddress.getByName("127.0.0.1"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("服务器已运行！");
		while (!shutdown) {
			Socket socket = serverSocket.accept();
			InputStream input = null;
			OutputStream output = null;
			
			input = socket.getInputStream();
			output = socket.getOutputStream();

			Request request = new Request(input);
			request.parse();

			Response response = new Response(output);
			response.sendResponse();
			
			socket.close();
		}
	}
}
