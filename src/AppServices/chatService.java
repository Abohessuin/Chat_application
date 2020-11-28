package AppServices;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import Database.serverDatabase;
import Datatype.Account;

public class chatService implements serverServices {
	private serverDatabase SD;
	private Socket socket;
	private ArrayList<String>username;
	private HashMap<String, ArrayList<Socket>>result;
	public chatService(serverDatabase sD, Socket socket, ArrayList<String> username) {
		super();
		SD = sD;
		this.socket = socket;
		this.username = username;
	}



	@Override
	public void performService() {
		if(checkUsersInContact()) {
			ArrayList<Socket>userSockets=new ArrayList<>();
			for(int i=0;i<username.size();++i) {
				userSockets.add(SD.getSocket(username.get(i)));
			}
			this.result.put("Done", userSockets);

		}else {
			this.result.put("Chech someone not in contact", null);
		}

	}



	@Override
	public Object returnResToServer() {
		return this.result;
	}




	boolean checkUsersInContact() {
		String un=SD.getId(this.socket);
		Account account=SD.getAccount(un);
		for(int i=0;i<username.size();++i) {
			boolean check=false;
			for(int j=0;j<account.getContacts().size();++j) {
				if(username.get(i).equals(account.getContacts().get(j))){
					check=true;
					break;
				}
			}
			if(!check) {
				return false;
			}
		}

		return true;
	}



	public serverDatabase getSD() {
		return SD;
	}
	
	public void setSD(serverDatabase sD) {
		SD = sD;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public ArrayList<String> getUsername() {
		return username;
	}
	
	public void setUsername(ArrayList<String> username) {
		this.username = username;
	}
	
	public HashMap<String, ArrayList<Socket>> getResult() {
		return result;
	}
	
	public void setResult(HashMap<String, ArrayList<Socket>> result) {
		this.result = result;
	}
}
