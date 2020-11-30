package Forms;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;

import Database.serverDatabase;
import Database.serverDatabaseUsingODS;
import Datatype.clientServerPacket;
public class LoginForm extends JFrame implements ActionListener , FormHandling {

	public JPanel panel;
	public JLabel user_label, password_label, message;
	public JTextField userName_text;
	public JTextField password_text;
	public JButton submit, cancel;
	private Socket s;
	private ObjectOutputStream oos;
	private BufferedReader in;
	
	//private static  serverDatabase SD ;

	public LoginForm(Socket s,ObjectOutputStream oos, BufferedReader in) throws IOException {
		//this.SD = SD;
		this.s=s;
		 this.oos=oos;
		 this.in=in;
		this.MakeForm();
	}

	public void MakeForm() {
		// Username Label
		user_label = new JLabel();
		user_label.setText("User Name :");
		userName_text = new JTextField();
		// Password Label
		password_label = new JLabel();
		password_label.setText("Password :");
		password_text = new JTextField();
		// Submit
		submit = new JButton("SUBMIT");
		panel = new JPanel(new GridLayout(3, 1));
		panel.add(user_label);
		panel.add(userName_text);
		panel.add(password_label);
		panel.add(password_text);
		message = new JLabel();
		panel.add(message);
		panel.add(submit);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Adding the listeners to components..
		submit.addActionListener(this);
		//serverDatabase SD=this.SD;
		Socket s=this.s;
		ObjectOutputStream oos=this.oos;
		 BufferedReader in=this.in;
		submit.addActionListener(new ActionListener(){  
			
			
			public void actionPerformed(ActionEvent e){  
				String userName = userName_text.getText();
				String password = password_text.getText();
				// serverDatabase SD = new serverDatabaseUsingODS();
				clientServerPacket CP= new clientServerPacket(4,userName, password);
				try {
					oos.writeObject(CP);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					String k=in.readLine();
					if(k.equals("found")) {
						message.setText(" Hello " + userName + "");
						new HomeForm(s,oos,in,userName);
					}
					else if (k.equals("pass not found")) {
						message.setText(" Pass fail , 401 error ");
						userName_text.setText("");
						password_text.setText("");
					}
					else if (k.equals("username not found")) {
						message.setText(" username fail , 404 error ");
						userName_text.setText("");
						password_text.setText("");
					}
					else {
						message.setText(" Invalid user.. ");
						userName_text.setText("");
						password_text.setText("");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			}  
			

		
		});  
		
		
		add(panel, BorderLayout.CENTER);
		setTitle("Please Login Here !");
		setSize(450,350);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/*
	public static void main(String[] args) {
		serverDatabase SD = new serverDatabaseUsingODS();
		new LoginForm(SD);
	}
	*/
	

}