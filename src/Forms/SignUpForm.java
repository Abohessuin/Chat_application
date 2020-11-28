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
public class SignUpForm extends JFrame implements ActionListener , FormHandling {
	JPanel panel;
	JLabel user_label, password_label, message,name_label;
	JTextField userName_text;
	JTextField password_text;
	JTextField name_text;
	JButton submit, cancel;
	private Socket s;
//	private static serverDatabase SD ;
private ObjectOutputStream oos;
private BufferedReader in;




	public SignUpForm(Socket s2,ObjectOutputStream oos, BufferedReader in) throws IOException {
		// TODO Auto-generated constructor stub
		this.s=s2;
		 this.oos=oos;
		 this.in=in;
		//oos = new ObjectOutputStream(s.getOutputStream());
		
		this.MakeForm();
	}




	public void MakeForm() {
		// Username Label
		name_label = new JLabel();
		name_label.setText("Your Password :");
		name_text = new JTextField();
		user_label = new JLabel();
		user_label.setText("User Name :");
		userName_text = new JTextField();
		// Password Label
		password_label = new JLabel();
		password_label.setText("Ypur Name :");
		password_text = new JTextField();
		// Submit
		submit = new JButton("SUBMIT");
		panel = new JPanel(new GridLayout(5, 1));
		panel.add(name_label);
		panel.add(user_label);
		panel.add(name_text);
		panel.add(userName_text);
		panel.add(password_label);
		panel.add(password_text);
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
		

			// serverDatabase SD=this.SD;
		//	Socket s=this.s;
			public void actionPerformed(ActionEvent e){  
				if(e.getSource()==submit) {
				String password = name_text.getText();
				String userName = userName_text.getText();
				String Name = password_text.getText();
				ArrayList<String> C =new ArrayList<>();
				System.out.println("password : "+ password);
				System.out.println("password : "+ Name);
				clientServerPacket CP= new clientServerPacket(1,password,userName,Name,C);
			//	System.out.println("password : "+ password);
				try {
					oos.writeObject(CP);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				try {
					String k=in.readLine();
					if(k.equals("not found")) {
						message.setText(" U Regesterd as " + userName + "");
						//new HomeForm(s,oos,in,userName);
					}
					else {
						message.setText(" This UserName is already exist. ");
						userName_text.setText("");
						password_text.setText("");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
			}
		});  
		add(panel, BorderLayout.CENTER);
		setTitle("Sign Up");
		setSize(450,350);
		setVisible(true);
	}
	/*
	public static void main(String[] args) {
		serverDatabase SD = new serverDatabaseUsingODS();
		new SignUpForm(SD);
	}
	*/
	@Override
	public void actionPerformed(ActionEvent ae) {
		

	}
}