package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Todo {

	// socket2an
	private Socket server = null;
    private OutputStream dos = null;
    private InputStream dis = null;
    
    // form login
	private JFrame frmToDoList;
	private JTextPane usernameTextPane;
	private JTextPane passwordTextPane;

	
	// form
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Todo window = new Todo();
					window.frmToDoList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Todo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmToDoList = new JFrame();
		frmToDoList.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		frmToDoList.setTitle("To Do List");
		frmToDoList.getContentPane().setBackground(new Color(255, 239, 213));
		frmToDoList.setBounds(100, 100, 450, 320);
		frmToDoList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Coba text = new Coba();
				loginButtonActionPerformed(arg0);
			}
		});
		
		usernameTextPane = new JTextPane();
		passwordTextPane = new JTextPane();
		
		JLabel lblLogin = new JLabel("Selamat Datang di To Do List!");
		lblLogin.setFont(new Font("Calibri Light", Font.BOLD, 15));
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri Light", Font.BOLD, 15));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri Light", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(frmToDoList.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(87, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(usernameTextPane, Alignment.LEADING)
								.addComponent(passwordTextPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)))
					.addGap(80))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addComponent(lblLogin)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(usernameTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(btnNewButton)
					.addGap(71))
		);
		frmToDoList.getContentPane().setLayout(groupLayout);
	}
	
	// event handlers
	private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
		/*
		String username = usernameTextPane.getText();
        String password = passwordTextPane.getText();
        System.out.println(username + " " + password);
        */
		boolean success = doHandshake();
		if (success)
		{
			success = doLogin();
			if (success)
	        {
				// go to other form
	        	//frmToDoList.setVisible(false);
	        }
		}
    }//GEN-LAST:event_loginButtonActionPerformed


	// methods for socket
    public boolean doHandshake()
    {
        if (server == null)
        {
            try 
            {
                // TODO add your handling code here:
                server = new Socket("127.0.0.1", 2000);
                JOptionPane.showMessageDialog(frmToDoList, "Connecting to server.");
            } catch (UnknownHostException ex) {
                Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
             
        try {
            
            System.out.println("Mencoba handshake");
            if (server == null)
            {
            	JOptionPane.showMessageDialog(frmToDoList, "Failed connect to server.");	
            	return false;
            }
            
            dos = server.getOutputStream();
            if (dos != null)
            {
	            dos.write("handshake".getBytes());
	            dos.flush();
	            
	            dis = server.getInputStream();
	            byte[] b = new byte[1024];
	            int r = dis.read(b);
	            if (r != -1)
	            {
	                if (new String(b).trim().equals("success"))
	                {
	                    JOptionPane.showMessageDialog(frmToDoList, "Success connect to server.");
	                    return true;
	                }   
	            }
            }
            else
            {
            	JOptionPane.showMessageDialog(frmToDoList, "Failed connect to server.");	
            }
        } catch (IOException ex) {
            Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean doLogin()
    {
    	int result = 0;
        try {
            String username = usernameTextPane.getText();
            String password = passwordTextPane.getText();
            System.out.println("Masuk username");
            
            dos = server.getOutputStream();
            dos.write(("login/" + username + "/" + password).getBytes());
            dos.flush();
            System.out.println("berhasil kirim message");
            
            byte[] b = new byte[1024];
            dis = server.getInputStream();
            int r = dis.read(b);
            if (r != -1)
            {
            	result = Integer.parseInt(new String(b).trim());
            	
                JOptionPane.showMessageDialog(frmToDoList, (result == 1) ? "Success login." : "Username or password incorrect.");
            }
        } catch (IOException ex) {
            Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result == 1;
    }
}
