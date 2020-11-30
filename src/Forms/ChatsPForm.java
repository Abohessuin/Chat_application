package Forms;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Datatype.clientServerPacket;

public class ChatsPForm extends JFrame implements ActionListener,CaretListener {

	JTextField jtf,jtf1;
	JButton Button;
	JPanel jpan;
	private int k1=0;
    String s1="";
    String s2="";
	private Socket s;
	private  PrintWriter out;
	private ArrayList<String> Names;
	private ObjectOutputStream oos;
	private BufferedReader in;
	private int size=0;
	private static ArrayList <String> Dms = new  ArrayList <>();
	public ArrayList<String>communicatedClients = new ArrayList<String>();
	//private ArrayList<String> Name;
	//private static serverDatabase SD ;
	public ChatsPForm(Socket s,ObjectOutputStream oos, BufferedReader in) throws IOException {
		this.s=s;
		 this.oos=oos;
		 this.in=in;
		// this.Dms=Dms;
		//this.SD=SD;
		// out = new PrintWriter(this.s.getOutputStream(),true);
		MakeForm();
	}

	
	public static void main(String[] args) throws IOException {
		//serverDatabase SD = new serverDatabaseUsingODS();
		//new chatsform(null,null,null);
	}
	int getbounds() {
		return k1;
	}
void setbounds(int k) {
	this.k1+=k;
}
	public void MakeForm() throws IOException {
		
		//Button.addActionListener(this);
    //  serverDatabase SD=this.SD;
		//if(this.Dms.size()!=getoldsize()) {
		 Socket s=this.s;
		 ObjectOutputStream oos=this.oos;
		 BufferedReader in=this.in;
		 
			jtf = new JTextField(100);
			jtf1 = new JTextField(100);
			jpan = new JPanel();
			// jtf.addCaretListener(this);
			Button = new JButton("Click");
			Button.setBounds(150,10,100,60);
	
			setLayout(null);
			jtf.setBounds(50, 10, 50, 30);
			jtf1.setBounds(50, 30, 50, 30);
			jpan.setBounds(60, 80, 220, 350);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jpan.setOpaque(true);
			jpan.setBackground(Color.CYAN);
			jpan.setLayout(null);
			//add(Button);
			add(jtf);
			add(jtf1);
			add(jpan);
			add(Button);
			setTitle("Chatting");
		    setSize(1000,500);
		    setVisible(true);

			Button.addActionListener(new ActionListener(){  
				//	private serverDatabase SD=this.SD;
				//	private Socket s=this.s;
					public void actionPerformed(ActionEvent e){  
						 s1 = jtf1.getText();
						 s2 = jtf.getText();
							if(!s1.equals("")&&!s2.equals("")) {
								//int num = Integer.parseInt(s);
								JLabel jlabels = new JLabel();
								//settext(s1);
									jlabels = new JLabel("Label");
									//jlabels.setPreferredSize(new Dimension(250, 100));
									jlabels.setForeground(new Color(120, 90, 40));
									jlabels.setBackground(new Color(100, 20, 70));
									jlabels.setSize(1000,200);
									jlabels.setBounds(0, getbounds(), 500, 75);
									//k1+=15;
									setbounds(75) ;
										
									
									jpan.add(jlabels);
									jlabels.setText(s2+"\n");
								//	 ObjectOutputStream oos=this.oos;
									try {
										//SendtoMyFriend(s1,oos);
										// ArrayList<String> Names=new ArrayList<>();
										 //Names.add(s1);
										
										  String[] arrOfStr = s1.split(":", -2); 

									        for (String a : arrOfStr) {
									         communicatedClients.add(a);
									        }
										clientServerPacket CP= new clientServerPacket(8,s2, communicatedClients,"",jpan);
										System.out.println("my fr " +  communicatedClients);
										oos.writeObject(CP);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									 //out.print(dc);
									
								jpan.validate();
								jpan.repaint();
								
							
							}
						}  
				}); 
			
		   
		}
			 
			  

		 
//	}
	public void addlabel1(String d) {
		JLabel jlabels = new JLabel();

		jlabels = new JLabel("Label");
		//jlabels.setPreferredSize(new Dimension(250, 100));
		jlabels.setForeground(new Color(120, 90, 40));
		jlabels.setBackground(new Color(100, 20, 70));
		jlabels.setSize(1000,200);
		jlabels.setBounds(0, getbounds(), 500, 75);
		setbounds(75) ;
		jpan.add(jlabels);
		jlabels.setText(d+"\n");

		jpan.validate();
		jpan.repaint();
	}

	
	@Override
	public void caretUpdate(CaretEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
