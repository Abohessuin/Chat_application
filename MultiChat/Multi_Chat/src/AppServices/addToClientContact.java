package AppServices;

import java.util.ArrayList;

import Database.serverDatabase;
import Datatype.Account;

public class addToClientContact implements serverServices {

	private static serverDatabase sd;
	private boolean result;
	private String username;

	public addToClientContact(String username,serverDatabase sd) {
		this.sd=sd;
		this.username = username;
	}


	@Override
	public void performService() {
		if(checkAboutUserSignUp()) {
			Account account=sd.getAccount(username);
			ArrayList<String> contact=account.getContacts();
			contact.add(username);
			Account acc=new Account(account.GetName(), account.GetUserName(), account.GetPassWord(), contact);
			sd.setNewAccount(acc);
			this.result=true;
		}else {
			this.result=false;
		}



	}

	public void setUsername(String username) {
		this.username = username;
	}


	boolean checkAboutUserSignUp() {
		Account ac=sd.getAccount(username);
		if(ac==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public Object returnResToServer() {

		return this.result;
	}

}
