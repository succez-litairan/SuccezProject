package WebServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class WebServer {

	public static Logger logger = Logger.getLogger(WebServer.class);
	static String WEB_ROOT;
	static int Port;
	static String HOST;

	/**
	 * 构建一个与ServerSocket通信的线程
	 * @author 李泰然
	 */
	class ConnectionThread extends Thread {
		private Socket socket;
		@SuppressWarnings("unused")
		private int counter;

		public ConnectionThread(Socket socket, int counter) {
			this.socket = socket;
			this.counter = counter;
		}

		public void run() {
			try {
				Request request = new Request(socket);
				Response response = new Response(socket);

				RequestHandler requestHandler = new RequestHandler(request,
						response);
				requestHandler.run();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (Exception e) {
					}
				}
			}
		}
	}

	/**
	 * 初始化服务器并运行
	 * @throws IOException
	 */
	private void await() throws IOException {
		getConfig();
		logger.info("服务器主机号为 " + HOST);
		logger.info("端口号为 " + Port);
		logger.info("WEB_ROOT为" + WEB_ROOT);
		;
		ServerSocket server = null;
		try {
			server = new ServerSocket(Port);
			Socket socket = null;
			int i = 1;
			ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10,
					TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(5));
			while (true) {
				socket = server.accept();
				ConnectionThread connectionThread = new ConnectionThread(
						socket, i);
				executor.execute(connectionThread);
				i++;
			}
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * 获取服务器根目录以及端口号的配置信息
	 * @throws IOException 
	 * @throws IllegalArgumentException
	 */
	private static void getConfig() throws IllegalArgumentException,
			IOException {
		File ini = new File(
				System.getProperty("user.dir") + "\\src\\WebServer",
				"config.ini");
		Properties cfg = new Properties();
		cfg.load(new FileInputStream(ini));
		WEB_ROOT = cfg.getProperty("WEB_ROOT");
		if (WEB_ROOT.equals("") == true) {
			throw new IllegalArgumentException("路径未设置");
		}
		Port = Integer.parseInt(cfg.getProperty("PORT"));
		if (cfg.getProperty("PORT").equals("") == true) {
			throw new IllegalArgumentException("端口号未设置");
		}
		HOST = cfg.getProperty("HOST");
		if (HOST.equals("") == true) {
			throw new IllegalArgumentException("服务器地址未设置");
		}
	}

	public static void main(String[] args) throws Exception {
		WebServer webserver = new WebServer();
		webserver.await();
	}
}
