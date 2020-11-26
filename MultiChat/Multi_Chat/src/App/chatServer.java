package App;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import AppServices.addToClientContact;
import AppServices.chatService;
import AppServices.serverServices;
import Database.serverDatabase;
import Datatype.clientServerPacket;

public class chatServer {
	private serverDatabase SD;
	private Socket clientSocket;
	private serverServices service;
	private String clientMsg;
	private ArrayList<String>communicatedClients;
	private int serviceNum;
	private String userToAddTOClientCont;
    private Object serviceRespond;

	public chatServer(serverDatabase sD, Socket clientSocket) {
		super();
		SD = sD;
		this.clientSocket = clientSocket;
		
	}
	
	
	
	public void execute() throws ClassNotFoundException, IOException {
		getServiceNeeded();
		switch (this.serviceNum) {
		case 1: {
			this.service=new addToClientContact(this.userToAddTOClientCont,this.SD);
			this.service.performService();
			this.serviceRespond=this.service.returnResToServer();
			if((boolean) this.serviceRespond) {
				respondeToClient("Added to your contact u can contact him any time");
			}else {
				respondeToClient("he is not on app kindly invite him");
			}
			break;
		}case 2:{
			this.service= new chatService(SD, clientSocket, this.communicatedClients);
			this.service.performService();
			HashMap<String, ArrayList<Socket>>result=(HashMap<String, ArrayList<Socket>>) this.service.returnResToServer();
			this.serviceRespond= (String) result.keySet().toArray()[0];
			ArrayList<Socket> chatParteners = (ArrayList<Socket>) result.values().toArray()[0];
			chatParteners.add(clientSocket);
			respondeToClient("starting the chat now");
			//start the chat between them
			while(true) {
				this.clientMsg=(String) ListenTOClient();
				if(this.clientMsg.equals("end")) {
					break;
				}
				for(int i=0;i<chatParteners.size();++i) {
					if(!(this.clientSocket.equals(chatParteners.get(i)))){
						String MSG=this.SD.getId(chatParteners.get(i))+":"+this.clientMsg;
						respondeToClient(MSG);
					}
				}
				
			}
			
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.serviceNum);
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
	
   //Get service Client Needed from server
   public void getServiceNeeded() throws IOException, ClassNotFoundException {
	     ObjectInputStream ois;
		 ois = new ObjectInputStream(this.clientSocket.getInputStream());
		  clientServerPacket CSP= (clientServerPacket) ois.readObject();
		  this.serviceNum=CSP.getServiceNum();
		  this.communicatedClients=CSP.getCommunicateWith();
		  this.userToAddTOClientCont=CSP.getUserToAdd();
   }
   
   public void respondeToClient(Object Msg) throws IOException {
	   PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream() ,true);
	   out.println(Msg);
		
   }
   
   public Object ListenTOClient() throws IOException, ClassNotFoundException {
	   ObjectInputStream ois;
	   ois = new ObjectInputStream(this.clientSocket.getInputStream());
	   return ois.readObject();
   }



}
