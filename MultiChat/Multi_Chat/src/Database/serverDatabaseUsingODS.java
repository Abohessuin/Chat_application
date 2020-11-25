package Database;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import Datatype.Account;

public class serverDatabaseUsingODS implements serverDatabase {

	private static HashMap<Socket, String>clientsIds;
	private static HashMap<String,Socket>clientsSockets;
	private static ArrayList<Account>clientsAccount;



	public serverDatabaseUsingODS() {
		this.clientsIds = new HashMap<>();
		this.clientsSockets = new HashMap<>();
		this.clientsAccount = new ArrayList<>();
	}


	public serverDatabaseUsingODS(HashMap<Socket, String> clientsIds, HashMap<String, Socket> clientsSockets,
			ArrayList<Account> clientsAccount) {
		super();
		this.clientsIds = clientsIds;
		this.clientsSockets = clientsSockets;
		this.clientsAccount = clientsAccount;
	}

	@Override
	public void setAccount(Account a) {
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
		this.clientsIds.put(s, id);
		this.clientsSockets.put(id,s);

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