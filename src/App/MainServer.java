package App;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Database.serverDatabase;

public class MainServer extends chatServer implements Runnable  {
 

 

@Override
	public void run() {
		try {
			execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

//private static serverDatabase sD;


	//private static ExecutorService pool = Executors.newFixedThreadPool(4);

	public static void main(String [] args)throws Exception {
		int port = 6666;
		System.out.println("Multi Threaded Server started...");
		ServerSocket serverSocket = new ServerSocket(port);
		while(true){
			Socket socket = serverSocket.accept();
			//socket.setSoTimeout(14000);
			System.out.println("socket accept : " +socket);
			MainServer server = new MainServer();
			server.setSocket(socket);
			//server.setSocket(socket);
			
			Thread  t = new Thread(server);
			t.start();
			//start a new server thread...
			//pool.execute(server);

			//System.out.println("Thread #"  + t.getId());
		}
		
	}
}
