package App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import Database.serverDatabase;
import Database.serverDatabaseUsingODS;
import Forms.ChatForm;
import Forms.RegisterForm;

public class Client {

	 public static void main(String [] args) throws UnknownHostException, IOException{
		 String hostname = "localhost";
		 int port = 6666;
	
		
			
		 Socket socket = new Socket(hostname, port);
		 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		 BufferedReader in = new  BufferedReader(new InputStreamReader(socket.getInputStream()));
		 RegisterForm RF = new RegisterForm(socket,oos,in);
		ChatForm CF= new ChatForm(socket,oos,in); 
		 

		 
		 
		 
		
		 
 	}
}