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
import java.util.List;

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
    private static final int  warringLimit=2;
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
	//	System.out.println("num"+H);

		switch (H) {
		case 4: {
			 PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream() ,true);
			boolean Found =this.SD.isAccountFound(CSP.getUsername(),CSP.getPass());
		//	System.out.println(Found);
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
				//A.printacc();
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
			 
		
			// System.out.println(CSP.getUsername() + CSP.getPass() + CSP.getName());
			 
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
			if(!(A.getContacts().get(i).equals(A.GetUserName()))) {
				Contacts+=A.getContacts().get(i);
				Contacts+=":";
			}
		}
		
			 PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream() ,true);
			
			 
						out.println(Contacts);
					
			
			break;
		}
		case 7: {
			String U = this.SD.getId(this.clientSocket);
			//Account A= this.getSD().getAccount(U);
			ArrayList<String> Requested = this.SD.GetReqs(U);
			String Contacts="";
			for(int i=0 ; i<Requested.size() ; i++) {
					Contacts+=Requested.get(i);
					Contacts+=":";
			}
			
				 PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream() ,true);
				
				 
							out.println(Contacts);
		
				
			break;
		}
		case 8: {
			System.out.println("arrive");
			ArrayList<Socket> chatParteners = new ArrayList<>();
			chatParteners.add(clientSocket);
			for(int i=0 ; i<CSP.getCommunicateWith().size(); i++) {
			chatParteners.add(this.SD.getSocket(CSP.getCommunicateWith().get(i)));
			}
			String h="";
			for(int i=0;i<chatParteners.size();++i) {
				if(!(chatParteners.get(i).equals(this.clientSocket))) {
				h+=this.SD.GetClient(chatParteners.get(i));
				h+=",";
				}
			}
			System.out.println("sockets : "+serverDatabaseUsingODS.getClientssocketssarr());
			for(int i=0;i<serverDatabaseUsingODS.getClientssocketssarr().size(); ++i) {
				System.out.println("before : "+serverDatabaseUsingODS.getClientssocketssarr().get(i) +"   "+this.clientSocket);
				
				if(!(this.SD.GetClient(this.clientSocket).equals(this.SD.GetClient(serverDatabaseUsingODS.getClientssocketssarr().get(i))))){
					
					System.out.println("after : "+serverDatabaseUsingODS.getClientssocketssarr().get(i) +"   "+this.clientSocket);
					String MSG=this.SD.GetClient(this.clientSocket)+" to "+h +" : "+CSP.getClientMsg();
					respondeToClient(MSG,serverDatabaseUsingODS.getClientssocketssarr().get(i));
				}
			}
				
			break;
		}
		case 3: {
		
			 PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream() ,true);
			String Myname =this.SD.GetClient(this.clientSocket);
			
				Account A=SD.getAccount(Myname);
			if(this.SD.isAccountFounduser(CSP.getCommunicateWith().get(0))) {
				
				A.PushContact(CSP.getCommunicateWith().get(0));
				this.SD.updataAcc(A);
				
				if(this.SD.isUserFoundInReq(Myname,CSP.getCommunicateWith().get(0))) {
					this.SD.RemoveReq(Myname, CSP.getCommunicateWith().get(0));
				}
				
				Socket user = this.SD.getSocket((CSP.getCommunicateWith().get(0)));
				String name =this.SD.GetClient(user);
				this.SD.AddReqtoAcc(name, Myname);
				System.out.println(this.SD.GetReqs(name));
				out.println("Added");
			}
			else {
				out.println("he has no account");
			}
				
			break;
		}case 2:{
			
			ArrayList<String>pmsg=parseMsg(CSP.getClientMsg());
			if(checkwordsBanned(serverDatabaseUsingODS.getBannedwords(), pmsg)) {
				String username=this.SD.getId(this.clientSocket);
				Account user=this.SD.getAccount(username);
				user=edit_checkUserStatues(user);
				this.SD.setNewAccount(user);
				System.out.println("ban"+user+username);
				if(user.getStatues().equals("banned")) {
					System.out.println("ban here"+user+username);
					respondeToClient("you got banned due to unbehaviors words in msg", this.clientSocket);
				}else {
					respondeToClient("YOUr massage wont send and warrning for banned", this.clientSocket);
				}

			}
			
			else {
			
			System.out.println("herrre");
			System.out.println(this.SD +" "+ clientSocket + this.communicatedClients);
			
		
			ArrayList<Socket> chatParteners = new ArrayList<>();
			chatParteners.add(clientSocket);
			for(int i=0 ; i<CSP.getCommunicateWith().size(); i++) {
			chatParteners.add(this.SD.getSocket(CSP.getCommunicateWith().get(i)));
			}
		//	try {
				//respondeToClient("starting the chat now",clientSocket);
		//	} catch (IOException e) {
				
			//	e.printStackTrace();
		//	}
			if(CSP.getClientMsg().equals("exit")) {
				respondeToClient("out",this.clientSocket);
			}
			else {
			
			
				for(int i=0;i<chatParteners.size();++i) {
					if(!(this.clientSocket.equals(chatParteners.get(i)))){
						String MSG=this.SD.GetClient(this.clientSocket)+" : "+CSP.getClientMsg();
						respondeToClient(MSG,chatParteners.get(i));
					}
				}
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
	//   System.out.println("respond"+Msg);
	   out.println(Msg);
		
   }
   
   public Object ListenTOClient() throws IOException, ClassNotFoundException {
	   ObjectInputStream ois;
	   ois = new ObjectInputStream(this.clientSocket.getInputStream());
	   return ois.readObject();
   }



   boolean checkwordsBanned(ArrayList<String>bannedWords,ArrayList<String>msgWords) {
		boolean check=false;
		for(int i=0;i< msgWords.size();++i) {
			for(int j=0;j<bannedWords.size();++j) {
				if(msgWords.get(i).equals(bannedWords.get(j))) {
					return true;
				}
			}

		}

		return false;
	}



	public ArrayList<String> parseMsg(String s){
		String[] arrOfStr = s.split(" ", -2); 
		ArrayList<String>res=new ArrayList<>();
		for(int i=0;i<arrOfStr.length;++i) {
			res.add(arrOfStr[i]);
		}
		return res;
	}


	public boolean  checkUserBehavior(Account user){
		int numberOfUserWarring=user.getNumberOfWarning();
		if(numberOfUserWarring>this.warringLimit) {
			return true;
		}

		return false;
	}

	public Account edit_checkUserStatues(Account user) {
		int n=user.getNumberOfWarning()+1;
		if(n>this.warringLimit) {
			user.setNumberOfWarning(0);
			user.setStatues("banned");
		}else {
			user.setNumberOfWarning(n);
		}

		return user;
	}



}
