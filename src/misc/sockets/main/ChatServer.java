package misc.sockets.main;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

 


public class ChatServer {
	private Socket socket =null;
	private ServerSocket server = null;
	private DataInputStream streamIn = null;
	private InetAddress serverIP = null;
	private int serverPort = 1050;
	
	private Thread thread =null;
	
	

	
	public ChatServer(String ip,int port) {
		

		serverPort = port;
		
		
		try {
			System.out.println("Binding to port " + serverPort + ", please wait ...");
					
			serverIP = InetAddress.getByName(ip);
			server = new ServerSocket(serverPort,0,serverIP); // 0-default backlolg value
			
			System.out.println("Server started " + server);
			System.out.println("Waiting for client...");
			
			socket = server.accept();
			
			System.out.println("Client accepted"+ socket);
			open(); 
			boolean done = false;
			while (!done) {
				
				try {
					String line = streamIn.readUTF();
					System.out.println(line);
					done = line.equals(".bye");
					
				}catch(IOException ioex) {
					System.out.println(ioex);
				}
				
			}
			close();
			
			
		}catch (IOException ioex) {
			System.out.println(ioex);
		}
		
		
		
	}// END OF CONSTRUCTOR

	private void close() throws IOException {
		if(socket != null ) { socket.close();}
		if(streamIn != null) {streamIn.close();}
	}

	private void open() throws IOException {
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		
	}
	

}
