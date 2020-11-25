package AppServices;

import java.util.ArrayList;

import Database.serverDatabase;
import Datatype.Account;

public class addToClientContact implements serverServices {

	private static serverDatabase sd;
	private String result;
	private String username;
  
	public addToClientContact(String username) {
		
		this.username = username;
	}

	@Override
	public void performService() {
		Account account=sd.getAccount(username);
		ArrayList<String> contact=account.getContacts();
		contact.add(username);
		Account acc=new Account(account.GetName(), account.GetUserName(), account.GetPassWord(), contact);
		sd.setNewAccount(acc);


	}

	public void setUsername(String username) {
		this.username = username;
	}



	@Override
	public Object returnResToServer() {

		return null;
	}

}
