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
public class AddContactsForm extends JFrame implements ActionListener , FormHandling {

	public JPanel panel,panel1;
	public JLabel user_label, password_label, message;
	public JTextField userName_text;
	public JTextField password_text;
	public JButton submit, cancel;
	//private static serverDatabase SD ;
	private Socket s;
	private ObjectOutputStream oos;
	private BufferedReader in;
	public AddContactsForm(Socket s,ObjectOutputStream oos, BufferedReader in) {
		this.s=s;
		 this.oos=oos;
		 this.in=in;
		//this.SD = SD;
		this.MakeForm();
	}

	public void MakeForm() {
		// Username Label
		user_label = new JLabel();
		user_label.setText("User Name :");
		userName_text = new JTextField();
		// Password Label

		// Submit
		submit = new JButton("Add");
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
			//private Socket s=this.s;
			public void actionPerformed(ActionEvent e){  
				//String Myname =SD.getId(s);
				//Account A=SD.getAccount(Myname);
				if(e.getSource()==submit) {
				String name =userName_text.getText();
				ArrayList<String> communicateWith = new ArrayList<>() ;
				communicateWith.add(name);
				clientServerPacket CP= new clientServerPacket(3,communicateWith);
			System.out.println("after add " + name);
			try {
				oos.writeObject(CP);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		
			
				
				try {
					String k=in.readLine();
					if(k.equals("Added")) {
						userName_text.setText("");
						message.setText(" Added to your Contact ");
					}
					else {
						message.setText(" can not find him ");
						userName_text.setText("");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
			
		}); 
		add(panel, BorderLayout.CENTER);
		setTitle("Add to your Contacts !");
		setSize(450,400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		//serverDatabase SD = new serverDatabaseUsingODS();
		//new AddContactsForm(SD,s);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {

}
}