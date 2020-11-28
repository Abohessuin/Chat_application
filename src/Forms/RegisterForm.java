package Forms;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.serverDatabase;
import Database.serverDatabaseUsingODS;

public class RegisterForm  extends JFrame implements ActionListener,  FormHandling {
	JPanel panel;
	JButton login, signup;
	//private static serverDatabase SD ;
    private Socket s;
	private ObjectOutputStream oos;
	private BufferedReader in;

	public RegisterForm(Socket s, ObjectOutputStream oos, BufferedReader in) {
		//this.SD=SD;
		 this.oos=oos;
		 this.in=in;
		this.s=s;
		MakeForm();
	}
	public void MakeForm() {
		// Username Label

		login = new JButton("LOGIN");
		signup = new JButton("SIGNUP");
		login.setBounds(200,100, 500, 500);
		signup.setBounds(60, 40, 500, 500);
		panel = new JPanel();
		panel.add(login);
		panel.add(signup);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Adding the listeners to components..
	//	serverDatabase SD=this.SD;
		Socket s=this.s;
		 ObjectOutputStream oos=this.oos;
		 BufferedReader in=this.in;
		login.addActionListener(new ActionListener(){  
		//	private serverDatabase SD=this.SD;
			//private Socket s=this.s;
			public void actionPerformed(ActionEvent e){  
				try {
					new LoginForm(s,oos,in);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		});  
		//System.out.println("cons" + SD);
	
		signup.addActionListener(new ActionListener(){  
	
				public void actionPerformed(ActionEvent e){  
				//System.out.println(SD);
				try {
					new SignUpForm(s,oos,in);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}  
		});  
		add(panel, BorderLayout.CENTER);
		setTitle("Registeration Form");
		setSize(500,200);
		setVisible(true);
	}
	/*

	public static void main(String[] args) {
		serverDatabase SD = new serverDatabaseUsingODS();
		new RegisterForm(SD);
	}
	
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}


}
