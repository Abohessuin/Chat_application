package Forms;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

import Database.serverDatabase;
import Database.serverDatabaseUsingODS;
import Datatype.Account;
import Datatype.clientServerPacket;
public class ChatOneForm extends JFrame implements ActionListener , FormHandling {

	public JPanel panel,panel1;
	public JLabel user_label, password_label, message;
	public JTextField userName_text;
	public JTextField password_text;
	public JButton submit, cancel;
	//public ArrayList<String>communicatedClients = new ArrayList<String>();
	//private static serverDatabase SD;
	 private Socket s;
	private ObjectOutputStream oos;
	private BufferedReader in;
	public ChatOneForm(Socket s,ObjectOutputStream oos, BufferedReader in) {
		//this.SD = SD;
		this.s=s;
		 this.oos=oos;
		 this.in=in;
		this.MakeForm();
	}

	public void MakeForm() {
		// Username Label
		user_label = new JLabel();
		user_label.setText("Chat With (UserName) :");
		userName_text = new JTextField();
		// Password Label

		// Submit
		submit = new JButton("Start Chat");
		panel = new JPanel(new GridLayout(3, 3));
		panel1 = new JPanel();
		panel.add(user_label);
		panel.add(userName_text);
	    
		message = new JLabel();
		panel.add(message);
		panel.add(submit);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Adding the listeners to components..
		//submit.addActionListener(this);
		//serverDatabase SD=this.SD;
	 Socket s=this.s;
	 ObjectOutputStream oos=this.oos;
	 BufferedReader in=this.in;
		submit.addActionListener(new ActionListener(){

			
		//	private serverDatabase SD=this.SD;
		//	private Socket s=this.s;
			
			public void actionPerformed(ActionEvent e){  
			
				
					clientServerPacket CP= new clientServerPacket(5,userName_text.getText(), "");
					try {
						oos.writeObject(CP);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					String k = null;
					try {
						k = in.readLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(k.equals("found")) {
						ArrayList<String>communicatedClients = new ArrayList<String>();
						communicatedClients.add(userName_text.getText());
						try {
							System.out.println(oos + "gg " );
							ChatForm cf =new ChatForm(s,communicatedClients,oos,in);
							connectionChatForm con= new connectionChatForm(s,cf,cf.getbounds());
							new Thread(con).start();
							cf.setbounds(10);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						userName_text.setText("");
						message.setText(" He is in your Contacts ");
					}
					else {
						message.setText(" He is Not in your Contacts ");
						userName_text.setText("");
					}
					
					
				
				}  
				
		}); 
		add(panel, BorderLayout.CENTER);
		setTitle("Chat With One");
		setSize(450,400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		//serverDatabase SD = new serverDatabaseUsingODS();
		// Socket s;
	//	 ObjectOutputStream oos;
	//		 BufferedReader in;
	//	new ChatOneForm(SD);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
	
	}
}