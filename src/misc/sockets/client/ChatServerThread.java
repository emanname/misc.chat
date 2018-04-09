package misc.sockets.client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import misc.sockets.main.ChatServer;

public class ChatServerThread extends Thread{
	private Socket socket =null;
	private ChatServer chatServer = null;
	private int clientID = -1 ;
	private DataInputStream streamIn= null;

public ChatServerThread(ChatServer _chatServer, Socket _socket) {
	chatServer = _chatServer;
	socket = _socket;
	clientID=socket.getPort();
}

public void run() {
	System.out.println("Server thread "+ clientID + " is running.");
	
	while (true) {
		try {
			System.out.println(streamIn.readUTF());
			
		}catch(IOException ioex) {
			System.out.println(ioex);
		}
	}
	
}


public void open() throws IOException {
	
	streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));	
}

public void close() throws IOException {
	
	if(socket != null ) { socket.close();}
	if(streamIn != null) {streamIn.close();}
}

	
	
	
}
