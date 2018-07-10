
/*Login -id : asg6506
Name : Akshat Sunil Gawankar
Code was referenced from https://www.youtube.com/watch?v=-GoqPrxM8TQ
*/

package NewPack;

import java.awt.EventQueue;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;

public class ClientSock 

{
	

 



	private JFrame frame;
	private static JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception
	
	{
	
		
		
	  
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientSock window = new ClientSock();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	}

	/**
	 * Create the application.
	 */
	public ClientSock() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 793, 341);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(473, 21, 209, 45);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Find");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSearch.setBackground(UIManager.getColor("Button.light"));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {             //Perform action when user clicks on search
				
				try
				{
					Socket s=new Socket("localhost", 1219);            //Create socket with Port number 
					System.out.println("Server connected succesfully");
					DataInputStream din=new DataInputStream(s.getInputStream());  //Lets application to read primitive data types
					DataOutputStream dout=new DataOutputStream(s.getOutputStream());//Lets application to write data
					String str=  textField.getText();          //Get the string entered in TextField and store it in str
					dout.writeUTF(str);                        //send the string to server
					
					String str2=din.readUTF();                //Read the string sent from the server
					textField_1.setText(str2);                //Set the string to textField_1
					dout.close();                             //close Data output stream
					s.close();                                 
				}
				
				catch(Exception e)
				{
			  	 System.out.println(e);
				}
			}
		});
		
		/*Create TextFields and set their properties */
		
		btnSearch.setBounds(289, 97, 147, 45);
		frame.getContentPane().add(btnSearch);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setBounds(473, 168, 209, 54);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter the word to find synonym");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setBounds(43, 21, 313, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("The Thesaurus is");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_1.setBounds(43, 177, 296, 45);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnNewButton.setBounds(298, 247, 138, 45);
		frame.getContentPane().add(btnNewButton);
	}
}
