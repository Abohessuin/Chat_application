package Forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Database.serverDatabase;
import Database.serverDatabaseUsingODS;
import Datatype.Account;
import Datatype.clientServerPacket;

public class ChatForm extends JFrame implements ActionListener,CaretListener {

	JTextField jtf;
	JButton Button;
	JPanel jpan;
	int k=0;
	String s1="";
	private Socket s;
	private  PrintWriter out;
	private ArrayList<String> Names;
	private ObjectOutputStream oos;
	private BufferedReader in;
	//private ArrayList<String> Name;
	//private static serverDatabase SD ;
	public ChatForm() throws IOException {

		MakeForm();
	}
	public ChatForm(Socket s,ObjectOutputStream oos, BufferedReader in) throws IOException {
		this.s=s;
		this.oos=oos;
		this.in=in;
		//this.SD=SD;
		// out = new PrintWriter(this.s.getOutputStream(),true);
		//MakeForm();
	}
	public ChatForm(Socket s,ArrayList<String> communicatedClients,ObjectOutputStream oos, BufferedReader in) throws IOException {
		this.s=s;
		this.Names=communicatedClients;
		this.oos=oos;
		this.in=in;
		//this.SD=SD;
		// out = new PrintWriter(this.s.getOutputStream(),true);
		MakeForm();
	}

	int getbounds() {
		return k;
	}
void setbounds(int k1) {
	k+=k1;
}
	public void MakeForm() throws IOException {
		Button = new JButton("Click");
		Button.setBounds(150,10,100,60);
		//Button.addActionListener(this);
		//  serverDatabase SD=this.SD;
		Socket s=this.s;
		ObjectOutputStream oos=this.oos;
		System.out.println("is oos null ;"+ oos);
		BufferedReader in=this.in;
		ArrayList<String> Names=this.Names;
		Button.addActionListener(new ActionListener(){  
			//	private serverDatabase SD=this.SD;
			//	private Socket s=this.s;
			public void actionPerformed(ActionEvent e){  
				s1 = jtf.getText();

				if(!s1.equals("")) {
					//int num = Integer.parseInt(s);
					JLabel jlabels = new JLabel();
					//settext(s1);
					jlabels = new JLabel("Label");
					//jlabels.setPreferredSize(new Dimension(250, 100));
					jlabels.setForeground(new Color(120, 90, 40));
					jlabels.setBackground(new Color(100, 20, 70));
					jlabels.setSize(1000,200);
					jlabels.setBounds(0, k, 400, 75);
					k+=10;
					jpan.add(jlabels);
					jlabels.setText(s1+"\n");
					//	 ObjectOutputStream oos=this.oos;
					try {
						//SendtoMyFriend(s1,oos);
						clientServerPacket CP= new clientServerPacket(2,s1,Names,"",jpan);
						System.out.println(oos + " " + CP + " " + Names);
						oos.writeObject(CP);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//out.print(dc);

					jpan.validate();
					jpan.repaint();


					//	System.out.println(k);


				}
			}  
		}); 
		jtf = new JTextField(100);
		jpan = new JPanel();
		// jtf.addCaretListener(this);

		setLayout(null);
		jtf.setBounds(50, 10, 50, 30);
		jpan.setBounds(60, 80, 220, 350);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jpan.setOpaque(true);
		jpan.setBackground(Color.CYAN);
		jpan.setLayout(null);
		add(Button);
		add(jtf);
		add(jpan);
		setTitle("Chatting");
		setSize(1000,500);
		setVisible(true);

		/*
			try {
				String k=in.readLine();

				addlabel(k);
			System.out.println(k);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 */
		//connectionChatForm con= new connectionChatForm(s,jpan,k);
	//	k+=10;
		//new Thread(con).start();
	}



	@Override
	public void caretUpdate(CaretEvent e) {
		// TODO Auto-generated method stub
		String s = jtf.getText();
		if(!s.equals("")) {
			int num = Integer.parseInt(s);
			JLabel jlabels[] = new JLabel[num];
			for(int i=0 ; i<jlabels.length ; i++) {
				jlabels[i] = new JLabel("Label"+i);
				jpan.add(jlabels[i]);
				jlabels[i].setText("mandy");
			}
			jpan.validate();
			jpan.repaint();
		}
	}


	String inputtext(){
		return s1;
	}

	void  settext(String f){
		s1=f;
	}


	public void addlabel(String d) {
		JLabel jlabels = new JLabel();

		jlabels = new JLabel("Label");
		//jlabels.setPreferredSize(new Dimension(250, 100));
		jlabels.setForeground(new Color(120, 90, 40));
		jlabels.setBackground(new Color(100, 20, 70));
		jlabels.setSize(1000,200);
		jlabels.setBounds(0, k, 75, 75);
		k+=10;
		jpan.add(jlabels);
		jlabels.setText(d+"\n");

		jpan.validate();
		jpan.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	









}

