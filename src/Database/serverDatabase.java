package Database;
import java.awt.Component;
import java.net.Socket;
import java.util.ArrayList;

import Datatype.Account;

public interface serverDatabase {
	 public void setClientId (Socket s,String id);
	 public void setAccount(Account a );
     public Socket getSocket(String id);
     public String getId(Socket s);
     public Account getAccount(String id);
     public void setNewAccount(Account a); 
     public boolean checkSocket(String id);
     public boolean checkId(Socket socket);
	public boolean isAccountFound(String S, String P);
	public void printclients();
	public String GetClient(Socket s);
	public void updataAcc(Account A);
	void AddReqtoAcc(String s, String Requested);
	ArrayList<String> GetReqs(String s);
	boolean isUserFoundInReq(String s, String Requested);
	void RemoveReq(String s,String Requested);
	boolean isAccountFounduser(String S);
	//public ArrayList<String> getBannedwords();
	//public ArrayList<Socket> getClientssocketssarr();
	//public Component getClientssocketssarr1();
	//boolean isAccountFoundUserName(String S, String P);
	boolean isAccountFoundUserName(String S);
	boolean isAccountFoundpass(String P);
	
	
	
     
}
