
/*Login -id : asg6506
Name : Akshat Sunil Gawankar
Code was referenced from http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
*/
package NewPack;
import java.io.*;
import java.net.*;
import javax.swing.JTextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.*; 
import java.sql.*;
public    class ServSoc extends JFrame {                     
	static ServerSocket serverSocket = null;
	 private static final long serialVersionUID = 1L;
	 private JTextField textField;
	 
	 public ServSoc()                          // Step 1 -> Invoke constructor to define Text Fields and buttons and set properties
	 {
		 getContentPane().setLayout(null);
		 textField = new JTextField();
		 textField.setBounds(184, 28, 247, 35);
		 textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		 getContentPane().add(textField);
		 textField.setColumns(10);
		 textField.setText("Server started Successfully");
		 
		 
	/*	 JLabel lblNewLabel = new JLabel("Client requested For");
		 lblNewLabel.setBounds(46, 109, 166, 41);
		 lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		 
		 getContentPane().add(lblNewLabel);
		 
		 JTextField field1 = new JTextField(30);
		 field1.setBounds(330, 107, 199, 48);
		 getContentPane().add(field1);*/
		
		 
		 JButton b=new JButton("Exit");  
		 b.setBounds(234, 99, 150, 75);
		 b.setFont(new Font("Tahoma", Font.BOLD, 16));
		    
		    getContentPane().add(b);
		    
		    
		    
		    b.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){  
		           System.exit(0);
		        }  
		    });  
		    
		 
		 
		 
		 
		
		 
	 }
	 
	 private static void createAndShowGUI() {          //Step 3 -> Creating frame and displaying it on screen
		 
		 JFrame frame = new ServSoc();
		 frame.pack();
		 frame.setSize( 700, 700 );

		 frame.setLocationRelativeTo(null);
		 frame.setVisible(true);
		 
		 
		 
	 }
	 
	 
    public static void main(String[] args) throws IOException
    
    {
    	
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
    	
    		 
    	
    		public void run() {
    	
    		 
    	
    		    createAndShowGUI();   // Step 2 -> Display the frame by calling this method above 
    	
    		  
    		}
    		
    	
    	});

        
        try {
            serverSocket = new ServerSocket(1219);           //Defining port number.
        } 
        catch (IOException e) 
        {
            
        }

        while(true){                                          //Handling multiple clients by creating Thread for each client
            Socket clientSocket = serverSocket.accept();      //Server accepting client connection
            System.out.println("Client successfully connected to server");
            MiniServer mini = new MiniServer(clientSocket);
            mini.start();                                    //Thread started
        }
        
    }
}


 class MiniServer extends Thread                           //Thread class
 
 {
 
    private Socket socket = null;
    
    public MiniServer(Socket socket) {                    //Constructor invoked
    	

        super("MiniServer");                             //Calling super constructor
        this.socket = socket;
        
    }

    public void run(){                                  //run method.This method is invoked when thread starts
    	
    	try {
            String str="";
            String str2=null;
     	   	
     	   DataInputStream din=new DataInputStream(socket.getInputStream());               
    		DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
    		
    		str=din.readUTF();                           //Reading what client sent to server and storing it in str.
    		JOptionPane.showMessageDialog(null, "Client Has sent "+str);
     	  
    		//Connection to database.
     	   Class.forName("com.mysql.jdbc.Driver");      
     	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thesaurus",
     	            "root", "akshat");
     	    
     	    
     	    
     	    
 		
 		 String query=(" SELECT syn FROM  synonym  WHERE word = ?  ");   //Query to select synonym of word from table
 		PreparedStatement statement=con.prepareStatement(query);         //Allow to send or retrieve data to and from database
 		
 		statement.setString(1,str);                                      //select column 1 of table from database
 		ResultSet rs=statement.executeQuery();                           //getting result from query and store in rs
 		
 		while(rs.next())
 		{
 		   str2=rs.getString(1);                                         //storing result in str2
 		  	
 		  
 		  dout.writeUTF(str2);
 		                     //Sending the result from server to client
 		 JOptionPane.showMessageDialog(null, "Sending result to client");
 		 
 		}        
 		
 		
 		rs.close();                                                     //close result set
 		dout.close();                                                   //close server socket
 		socket.close();                                                 //close connection with client
 		
 		
 		
 		}
catch(Exception e)
    	{
	
    	}
            
    	
   
    
    }

	
           

}



