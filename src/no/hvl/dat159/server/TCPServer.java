package no.hvl.dat159.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import no.hvl.dat159.config.ServerConfig;
import no.hvl.dat159.crypto.*;

public class TCPServer {

	private ServerSocket ssocket;
	private Vignere v = new Vignere("NULE".toCharArray());
	
	public TCPServer(int port) throws IOException {
		ssocket = new ServerSocket(port);
	}
	
	public void socketlistener() {
		
		try {
			
			System.out.println("TCP Server listening to incoming connections from clients >>");
			Socket socket = ssocket.accept();
			
			BufferedReader inmsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataOutputStream outmsg = new DataOutputStream(socket.getOutputStream());
			
			String clientmsg = inmsg.readLine();
			System.out.println("Encrypted message recieved from the Client: "+clientmsg);
			System.out.println("Decrypted message in the server from the Client: "+v.decrypt(clientmsg));
			
			String response = "HTTP/1.1 200 OK \r\n\r\n"+ "Thanks TCPClient - I got your message";

			outmsg.write(response.getBytes());
			outmsg.write(clientmsg.getBytes());
			outmsg.flush();
			inmsg.close();
			outmsg.close();
			
			socket.close();
	
		}catch(IOException e) {
			//
		}
	}
	
	public static void main(String[] args) throws IOException {

		TCPServer tcpserver = new TCPServer(ServerConfig.PORT);
	
		// start the server and let it run forever
		while(true) {
			tcpserver.socketlistener();
		}

	}

}
