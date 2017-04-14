package chat;

import java.io.*;
import java.io.IOException;
import java.net.*;

public class server {
	private ServerSocket TCPserver = null;
	private DatagramSocket UDPserver = null;;
	private Socket connection = null;

	public server() {
		try {
			TCPserver = new ServerSocket(15555);
			System.out.println("Port open on ip: " + InetAddress.getLocalHost());
			UDPserver = new DatagramSocket(15555);
			while (true) {
				connection = TCPserver.accept();
				System.out.println("Connected to client");
				tcpThread tcp = new tcpThread(connection);
				tcp.run();
			}
		} catch (BindException be) {
			be.printStackTrace();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void runServer() {
		
	}

	class tcpThread implements Runnable {
		Socket s;
		BufferedReader br;;
		PrintWriter pw;
		String message;

		public tcpThread(Socket s) {
			this.s = s;
		}

		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

				message = br.readLine();
				pw.println("Here is your message: " + message);
				pw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		new server();
		System.out.println("Server up");
	}
}
