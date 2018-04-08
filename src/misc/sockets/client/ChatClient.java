package misc.sockets.client;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;



public class ChatClient {
	private Socket socket 				=null;
	private DataInputStream console 	=null;
	private DataOutputStream streamOut 	=null;

public ChatClient(String serverName, int serverPort) {
	
	
	System.out.println("Starting client... ");
		
	try {
		socket = new Socket(serverName,serverPort);
		System.out.println("Connected: " + socket);
		start();
		
	}catch(UnknownHostException uhex) {
		System.out.println("UnknownHostException " + uhex.getMessage());
		
	}catch(IOException ioex) {
		System.out.println("IOException " + ioex.getMessage());
	}
	
	String line = "";
	while (!line.equals(".bye!") ) {
		try {
			
			line =console.readLine();
			streamOut.writeUTF(line);
			streamOut.flush();
			
		}catch(IOException ioex){
			System.out.print(ioex.getMessage());
		}catch(Exception ex) {
			System.out.print(ex.getMessage());
		}
		
		
	}// END OF WHILE
	stop();
	
	
	
}// END OF CONSTRUCTOR 


public void stop() {
	try {
		if(console != null) {console.close();}
		if(streamOut != null) {streamOut.close();}
		if(socket != null) {socket.close();}
		
	}catch(Exception ex) {
		System.out.print(ex.getMessage());
	}
	
}


public void start() throws IOException {
	console = new DataInputStream(System.in);
	streamOut = new DataOutputStream(socket.getOutputStream());
	
}



public static void main(String[] args) {

	ChatClient instChatClient = new ChatClient("192.168.1.107", 1050 );
	
	}//END OF MAIN

}
