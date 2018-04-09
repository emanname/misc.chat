package misc.sockets.main;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


import misc.sockets.client.ChatServerThread;

public class ChatServer implements Runnable {
	
	private ServerSocket server = null;	
	private Thread serverThread =null;
	private ChatServerThread client=null;

	//private DataInputStream streamIn = null;
	private InetAddress serverIP = null;
	
	
	private int serverPort = 1050;

public ChatServer(String ip,int port) {
		
		serverPort = port;
		
		try {
			System.out.println("Binding to port " + serverPort + ", please wait ...");
					
			serverIP = InetAddress.getByName(ip);
			server = new ServerSocket(serverPort,0,serverIP); // 0-default backlolg value
			
			System.out.println("Server started " + server);
			
			start();
			
		}catch (IOException ioex) {
			System.out.println(ioex.getMessage());
		}
}// END OF CONSTRUCTOR
	


public void run() {
	while(serverThread != null) {
		try {
			System.out.println("Wait for a client ...");
			addThread(server.accept());
						
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
		
}//END OF run()

private void addThread(Socket socket) {
	System.out.println("Client accepted: "+ socket);
	client = new ChatServerThread(this, socket);
	try{
		client.open();
		client.start();
	}catch(IOException ioex) {
		System.out.println(ioex.getMessage());
	}
	
	
	
}



private void start() {
	if(	serverThread == null) {
		serverThread = new Thread(this);
		serverThread.start();
	}
	
}

private void stop() {
	if(serverThread != null){
		serverThread.stop();
		serverThread=null;
	}
}


}
