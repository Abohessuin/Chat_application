

package Forms;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.serverDatabase;
import Database.serverDatabaseUsingODS;
import Datatype.clientServerPacket;

public class HomeForm  extends JFrame implements ActionListener,  FormHandling {
	JPanel panel;
	JButton ADDC, CO,CG,CP,chats,contacts,DmPublic,FriendReq;
	//private static serverDatabase SD ;
    private Socket s;
	private ObjectOutputStream oos;
	private BufferedReader in;
	private String d;
   // private ArrayList <String> Dms= new ArrayList <>();
	public HomeForm(Socket s,ObjectOutputStream oos, BufferedReader in,String d) {
		//this.SD=SD;
		this.s=s;
		 this.oos=oos;
		 this.in=in;
		 this.d=d;
		MakeForm();
	}
	public void MakeForm() {
		// Username Label

		ADDC = new JButton("Add to Contact");
		CO = new JButton("Chat with One");
		CG = new JButton("Chat with Group");
		CP = new JButton("Chat with Public");
		chats = new JButton("Chats");
		contacts = new JButton("My Contacts");
		DmPublic = new JButton("Public Chats");
		FriendReq = new JButton("Freinds Requests");
		ADDC.setBounds(200,100, 500, 500);
		CO.setBounds(60, 40, 500, 500);
		CG.setBounds(200,100, 500, 500);
		CP.setBounds(60, 40, 500, 500);
		chats.setBounds(60, 40, 500, 500);
		contacts.setBounds(60, 40, 500, 500);
		DmPublic.setBounds(60, 40, 500, 500);
		FriendReq.setBounds(60, 40, 500, 500);
		
		panel = new JPanel();
		panel.add(ADDC);
		panel.add(CO);
		panel.add(CG);
		panel.add(CP);
		panel.add(chats);
		panel.add(contacts);
		panel.add(DmPublic);
		panel.add(FriendReq);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Socket s=this.s;
		ObjectOutputStream oos=this.oos;
		 BufferedReader in=this.in;
		 
		 
		 FriendReq.addActionListener(new ActionListener(){  
				
				public void actionPerformed(ActionEvent e){  
					//new ChatWithGroup(s,oos,in);
					clientServerPacket CP= new clientServerPacket(7);
					try {
						oos.writeObject(CP);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ArrayList <String> Strings = new ArrayList<>();
					try {
						String k=in.readLine();
						
						  String[] arrOfStr = k.split(":", -2); 
						  
						  System.out.println(arrOfStr);
						  
						  for (String a : arrOfStr) { 
					            Strings.add(a) ;
						  }
						  
						  System.out.println(Strings);
						  
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					new contactslist(Strings);
				}  
			});  
		 
		 
		 
		 
		DmPublic.addActionListener(new ActionListener(){  
				
				public void actionPerformed(ActionEvent e){  
					/*
				try {
					chatsform cf=new chatsform(s,oos,in);
					connectionchatsform con= new connectionchatsform(s,cf,cf.getbounds());
						new Thread(con).start();
						cf.setbounds(10);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				*/
				}  
			});  
		 
		 chats.addActionListener(new ActionListener(){  
				
				public void actionPerformed(ActionEvent e){  
					
				try {
					chatsform cf=new chatsform(s,oos,in);
					connectionchatsform con= new connectionchatsform(s,cf,cf.getbounds());
						new Thread(con).start();
						cf.setbounds(10);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
				}  
			});  
		CO.addActionListener(new ActionListener(){  
			
			public void actionPerformed(ActionEvent e){  
				
			new ChatOneForm(s,oos,in);
			
			}  
		});  
		ADDC.addActionListener(new ActionListener(){  
			
			public void actionPerformed(ActionEvent e){  
				
				new AddContactsForm(s,oos,in);
				}  
		}); 
		CP.addActionListener(new ActionListener(){ 
		
			public void actionPerformed(ActionEvent e){  
				try {
					ChatsPForm cf=new ChatsPForm(s,oos,in);
					connectionpchats con= new connectionpchats(s,cf,cf.getbounds());
						new Thread(con).start();
						cf.setbounds(15);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			
				}  
		}); 
		CG.addActionListener(new ActionListener(){  
		
			public void actionPerformed(ActionEvent e){  
		
			new ChatWithGroup(s,oos,in);
			}  
		});  
		contacts.addActionListener(new ActionListener(){  
			
				public void actionPerformed(ActionEvent e){  
				
				//new ChatWithGroup(s,oos,in);
					clientServerPacket CP= new clientServerPacket(6);
					try {
						oos.writeObject(CP);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ArrayList <String> Strings = new ArrayList<>();
					try {
						String k=in.readLine();
						
						  String[] arrOfStr = k.split(":", -2); 
						  
						  for (String a : arrOfStr) { 
					            Strings.add(a) ;
						  }
						  
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					 new contactslist(Strings);
				}  
			});  
		add(panel, BorderLayout.CENTER);
		setTitle("Home Page of "+d);
		setSize(500,200);
		setVisible(true);
		
	}
	

	public static void main(String[] args) {
		//serverDatabase SD = new serverDatabaseUsingODS();
	//	new HomeForm(SD,s);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}


}