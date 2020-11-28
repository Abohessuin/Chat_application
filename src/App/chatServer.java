package App;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import AppServices.addToClientContact;
import AppServices.chatService;
import AppServices.serverServices;
import Database.serverDatabase;
import Database.serverDatabaseUsingODS;
import Datatype.Account;
import Datatype.clientServerPacket;
import Forms.HomeForm;


public class chatServer  {
	private static serverDatabase SD=serverDatabaseUsingODS.getInstance();
	private Socket clientSocket;
	private serverServices service;
	private String clientMsg;
	private ArrayList<String>communicatedClients;
	private int serviceNum;
	private String userToAddTOClientCont;
    private Object serviceRespond;
    private JPanel jpan;
    private Account A;
   // private ObjectInputStream ois;
//private ObjectInputStream ois;
 //private ObjectInputStream ois;
 //private PrintWriter out ;
int bound=0;



public void setSocket(Socket socket) throws IOException {
	this.clientSocket = socket; 
	
	 //ois = new ObjectInputStream(this.clientSocket.getInputStream());
}
	
	
	
	
	public void execute() throws IOException, ClassNotFoundException {
		ObjectInputStream ois;
		 ois = new ObjectInputStream(this.clientSocket.getInputStream());
	
		while(true) {
			// System.out.println(this.clientSocket);
			//System.out.println("hg");
		
	
			  clientServerPacket CSP=  (clientServerPacket) ois.readObject();
	
			
		
		int H =CSP.getServiceNum();
		System.out.println("num"+H);

		switch (H) {
		case 4: {
			 PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream() ,true);
			boolean Found =this.SD.isAccountFound(CSP.getUsername(),CSP.getPass());
			System.out.println(Found);
			if (Found) {
			
				this.SD.setClientId(this.clientSocket,CSP.getUsername());
				

					 out.println("found");
		
			
			} else {
				out.println("not found");
			}
			
			
			break;
		}
		case 5: {
			
			 PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream() ,true);
			 
			String Myname =SD.getId(this.clientSocket);
			Account A=SD.getAccount(Myname);
				A.printacc();
				if(A.isinmycontact(CSP.getUsername())) {
					 out.println("found");
				}
				else {
					out.println("not found");
				}
				
			break;
		}
		case 1: {
		
		Account A = new Account(CSP.getName(),CSP.getUsername(),CSP.getPass(),CSP.getContacts());
	
		
			 PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream() ,true);
			 
		
			 System.out.println(CSP.getUsername() + CSP.getPass() + CSP.getName());
			 
					if(!this.SD.isAccountFound(CSP.getUsername(), CSP.getName())) {
						this.SD.setAccount(A);
						out.println("not found");
					}
					else {
						out.println("found");
					}
			
			break;
		}
		case 6: {
			
		String U = this.SD.getId(this.clientSocket);
		Account A= this.getSD().getAccount(U);
		String Contacts="";
		for(int i=0 ; i<A.getContacts().size() ; i++) {
				Contacts+=A.getContacts().get(i);
				Contacts+=":";
		}
		
			 PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream() ,true);
			
			 
						out.println(Contacts);
					
			
			break;
		}
		case 3: {
		
			 PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream() ,true);
			String Myname =this.SD.GetClient(this.clientSocket);
			
				Account A=SD.getAccount(Myname);
			if(this.SD.isAccountFound(CSP.getCommunicateWith().get(0), "")) {
				A.PushContact(CSP.getCommunicateWith().get(0));
				out.println("Added");
			}
			else {
				out.println("he has no account");
			}
				
			break;
		}case 2:{
			
			
			System.out.println("herrre");
			System.out.println(this.SD +" "+ clientSocket + this.communicatedClients);
			
		
			ArrayList<Socket> chatParteners = new ArrayList<>();
			chatParteners.add(clientSocket);
			for(int i=0 ; i<CSP.getCommunicateWith().size(); i++) {
			chatParteners.add(this.SD.getSocket(CSP.getCommunicateWith().get(i)));
			}
			try {
				respondeToClient("starting the chat now",clientSocket);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
			
			
				for(int i=0;i<chatParteners.size();++i) {
					if(!(this.clientSocket.equals(chatParteners.get(i)))){
						String MSG=this.SD.GetClient(this.clientSocket)+" : "+CSP.getClientMsg();
						respondeToClient(MSG,chatParteners.get(i));
					}
				}
				
			}
	break;
			
			
		
		default:
			
			throw new IllegalArgumentException("Unexpected value: " + this.serviceNum);
		}
		
	}
		
	}


	public serverDatabase getSD() {
		return SD;
	}


	public void setSD(serverDatabase sD) {
		SD = sD;
	}


	public Socket getClientSocket() {
		return clientSocket;
	}


	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}


	public serverServices getService() {
		return service;
	}


	public void setService(serverServices service) {
		this.service = service;
	}
	

   public void getServiceNeeded() throws IOException, ClassNotFoundException {

	      
	
   }
   
   public void respondeToClient(Object Msg,Socket s) throws IOException {
	   PrintWriter out = new PrintWriter(s.getOutputStream() ,true);
	   System.out.println("respond"+Msg);
	   out.println(Msg);
		
   }
   
   public Object ListenTOClient() throws IOException, ClassNotFoundException {
	   ObjectInputStream ois;
	   ois = new ObjectInputStream(this.clientSocket.getInputStream());
	   return ois.readObject();
   }







}
