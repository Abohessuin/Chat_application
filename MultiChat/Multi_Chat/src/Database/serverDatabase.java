package Database;
import java.net.Socket;

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
     
     
     
}
