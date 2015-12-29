package WebServer;

import java.io.IOException;
import java.io.OutputStream;

public class Response {
	OutputStream output;
	Request request;

	Response(OutputStream output) {
		this.output = output;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendResponse() throws IOException {
		output.write("服务器已运行！".getBytes());
	}

}
