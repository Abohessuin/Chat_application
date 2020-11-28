package Datatype;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import javax.accessibility.AccessibleContext;
import javax.swing.JPanel;

import Database.serverDatabase;
import Database.serverDatabaseUsingODS;
import Forms.ChatForm;

public class clientServerPacket implements Serializable  {
	private int serviceNum;
	private String ClientMsg;
	private ArrayList<String>communicateWith;
	private String userToAdd;
	private Account A;
	private String username;
	private String pass;
//	private Socket socket;
	private String Name;
	
	
	
	
	public clientServerPacket(int serviceNum) {
		// TODO Auto-generated constructor stub
		super();
		
		this.serviceNum = serviceNum;
	
		
	}
	public clientServerPacket(int serviceNum,String Name,String UserName,String PassWord, ArrayList<String> C) {
		// TODO Auto-generated constructor stub
		super();
		this.username=UserName;
		this.Name=Name;
		this.A=A;
		this.serviceNum = serviceNum;
		this.pass=pass;
		this.Contacts=C;
		
	}
	public clientServerPacket(int serviceNum, String username, String pass) {
		// TODO Auto-generated constructor stub
		super();
		
		this.serviceNum = serviceNum;
		this.username=username;
		this.pass=pass;
		
	}
	public clientServerPacket(int serviceNum, ArrayList<String> communicateWith) {
		// TODO Auto-generated constructor stub
		super();
		
		this.serviceNum = serviceNum;
		//this.username=username;
		this.communicateWith = communicateWith;
		
	}
	public clientServerPacket(int serviceNum, String clientMsg, ArrayList<String> communicateWith, String userToAdd,JPanel j) {
		// TODO Auto-generated constructor stub
		super();
		this.A=A;
		this.serviceNum = serviceNum;
		this.ClientMsg = clientMsg;
		this.jpan=j;
		this.communicateWith = communicateWith;
		this.userToAdd = userToAdd;
		
	}
	
	
	
	
	
	
	
public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public ArrayList<String> getContacts() {
		return Contacts;
	}
	public void setContacts(ArrayList<String> contacts) {
		Contacts = contacts;
	}
private ArrayList<String> Contacts;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	

	
public Account getA() {
		return A;
	}
	public void setA(Account a) {
		A = a;
	}
	//	private  ChatForm CF ;
	private JPanel jpan;
	
	public JPanel getJpan() {
		return jpan;
	}
	public void setJpan(JPanel jpan) {
		this.jpan = jpan;
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
