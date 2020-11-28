package Database;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.Iterator;

import Datatype.Account;

public class serverDatabaseUsingODS implements serverDatabase {

	private static HashMap<Socket, String>clientsIds;
	private static HashMap<String,Socket>clientsSockets;
	private static ArrayList<Account>clientsAccount;
	private static ArrayList<String>clientsidsarr;
	private static ArrayList<Socket>clientssocketssarr;
	
	private static serverDatabaseUsingODS singletonObj;

	
	 
	 
	 
	public serverDatabaseUsingODS() {
		this.clientsIds = new HashMap<>();
		this.clientsSockets = new HashMap<>();
		this.clientsAccount = new ArrayList<>();
		this.clientssocketssarr = new ArrayList<>();
		this.clientsidsarr = new ArrayList<>();
		
	}
  
	public static serverDatabaseUsingODS getInstance() {
		if (singletonObj == null) {
			singletonObj = new serverDatabaseUsingODS();
		}
		return singletonObj;
	}

	
	public void updataAcc(Account A) {
		for(int i=0 ; i<clientsAccount.size() ; i++) {
			if(A.GetUserName()==clientsAccount.get(i).GetUserName()) {
				clientsAccount.get(i).setContacts(A.getContacts());
			}
		}
	}

	@Override
	public void setAccount(Account a) {
		System.out.println("d : "+a);
		this.clientsAccount.add(a);

	}
	@Override
	public Socket getSocket(String id) {
		return this.clientsSockets.get(id);

	}
	@Override
	public String getId(Socket s) {

		return this.clientsIds.get(s);
	}
	@Override
	public Account getAccount(String id) {
		for(int i=0;i<this.clientsAccount.size();i++) {
			if(id.equals(this.clientsAccount.get(i).GetUserName())) {
				return this.clientsAccount.get(i);    		
			}


		}

		return null;
	}
	@Override
	public boolean isAccountFound(String S,String P) {
		for(int i=0;i<this.clientsAccount.size();i++) {
			System.out.println("make it : "+this.clientsAccount.get(i).GetUserName()+ " " + this.clientsAccount.get(i).GetName() );
			if(S.equals(this.clientsAccount.get(i).GetUserName()) && P.equals(this.clientsAccount.get(i).GetName()) ) {
				System.out.println("right");
				return true;    		
			}


		}
		return false;

	}
	public void printclients() {
		for(int i=0;i<this.clientsAccount.size();i++) {
			System.out.println(this.clientsAccount.get(i).GetUserName());
			


		}
		

	}
	public String GetClient(Socket s) {
		// Iterator hmIterator = clientsIds.entrySet().iterator(); 
		int it =0;
		for(int i=0;i<this.clientssocketssarr.size();i++) {
			//System.out.println(this.clientsidsarr.get(i));
			if(clientssocketssarr.get(i)==s) {
				it=i;
				break;
			}


		}
		return this.clientsidsarr.get(it);
		
		

	}


	@Override
	public boolean checkSocket(String id) {
		Socket s=this.clientsSockets.get(id);
		if(s!=null) {
			return true;
		}else {
			return false;
		}

	}


	@Override
	public boolean checkId(Socket socket) {
		String s=this.clientsIds.get(socket);
		if(s!=null) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public void setClientId(Socket s, String id) {
		//System.out.println(s);
		//System.out.println(id);
		//System.out.println(clientsIds.size());
		// clientsIds.keySet().toArray()[clientsIds.size()]=s;
		// clientsIds.keySet().toArray()[clientsIds.size()]=s;
		// clientsIds.values().toArray()[clientsIds.size()]=id;
		this.clientsIds.put(s, id);
		this.clientsSockets.put(id,s);
		this.clientssocketssarr.add(s);
		this.clientsidsarr.add(id);

	}


	@Override
	public void setNewAccount(Account a) {
		for(int i=0;i<this.clientsAccount.size();i++) {
			if(a.GetUserName().equals(this.clientsAccount.get(i).GetUserName())) {
				this.clientsAccount.remove(i);
				break;

			}

		}

		this.clientsAccount.add(a);
	}


}