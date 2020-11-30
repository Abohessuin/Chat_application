package Forms;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class contactslist extends JFrame {
     private JPanel contentPane;
	private ArrayList<String> Strings;

     
     
        public contactslist( ArrayList <String> Strings ){
            this.Strings=Strings;
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            contentPane.setLayout(new BorderLayout(0, 0));
            setContentPane(contentPane);    

            setSize(200, 600);
            setLayout(null);
            setLocationRelativeTo(null);
            setTitle("My Contacts");
            addWindowListener(new WindowAdapter(){
                public void windowClosed(WindowEvent evnt){
                    System.exit(0);
                }
            }); 
            setResizable(false); 
            setVisible(true);
            int k=0;
  for(int i=0 ; i<Strings.size() ; i++) {
            JLabel lblStanFunc = new JLabel(Strings.get(i));
            lblStanFunc.setBounds(50, k, 50, 30);
            k+=10;
            lblStanFunc.setVisible(true);
            add(lblStanFunc);
       }

        }
}