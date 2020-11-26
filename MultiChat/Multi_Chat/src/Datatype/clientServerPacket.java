package Datatype;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class clientServerPacket implements Serializable  {
	private int serviceNum;
	private String ClientMsg;
	private ArrayList<String>communicateWith;
	private String userToAdd;
	public clientServerPacket(int serviceNum, String clientMsg, ArrayList<String> communicateWith, String userToAdd) {
		super();
		this.serviceNum = serviceNum;
		ClientMsg = clientMsg;
		this.communicateWith = communicateWith;
		this.userToAdd = userToAdd;
	}
	public int getServiceNum() {
		return serviceNum;
	}
	public void setServiceNum(int serviceNum) {
		this.serviceNum = serviceNum;
	}
	public String getClientMsg() {
		return ClientMsg;
	}
	public void setClientMsg(String clientMsg) {
		ClientMsg = clientMsg;
	}
	public ArrayList<String> getCommunicateWith() {
		return communicateWith;
	}
	public void setCommunicateWith(ArrayList<String> communicateWith) {
		this.communicateWith = communicateWith;
	}
	public String getUserToAdd() {
		return userToAdd;
	}
	public void setUserToAdd(String userToAdd) {
		this.userToAdd = userToAdd;
	}
	
	

}
