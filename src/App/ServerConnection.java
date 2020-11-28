package App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import Forms.ChatForm;

public class ServerConnection implements Runnable {
	private Socket server ;
	private BufferedReader in ;
	private ChatForm b ;
	String h="";
	public ServerConnection(Socket Socket,ChatForm bb ) throws IOException {
		
		server = Socket;
		in = new  BufferedReader(new InputStreamReader(server.getInputStream()));
		this.b=bb;
		//out = new PrintWriter(server.getOutputStream() ,true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			b.MakeForm();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String k=null;
		System.out.println(5);
		try {
		while(true) {
			k=in.readLine();
			//b.Update(k);
		
			b.addlabel(k);
			//Seth(k);
			System.out.println("server says : "+ k);
		}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	void Seth(String h) {
		this.h=h;
	}
	String h() {
		return this.h;
	}
}
