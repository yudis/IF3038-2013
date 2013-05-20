/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servertubesv;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chritianto Handojo
 */
public class Pengatur implements Runnable, Serializable{
    private Socket sock;
    private InputStream in;
    private OutputStream out;
    private String request,reply;
    private Connection conn;
    private Statement query;
    
    Pengatur (Socket s) throws IOException {
        sock = s;
        in = s.getInputStream();
        out = s.getOutputStream();
    }
    
    private void getRequest() {
        try {
            int dataSize = 0;
            while (dataSize == 0) {
                dataSize = in.available();
            }
            
            byte[] buff = new byte[dataSize];
            in.read(buff, 0, dataSize);
            request = new String(buff,0,dataSize);
        } catch (IOException ex) {
            Logger.getLogger(Pengatur.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println(request);
        }
        
    }
    
    private void process() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510003", "root", "");

            if (request.charAt(0) == '0') {
                //Tries to login
                String username = request.substring(3, 3+request.charAt(1));
                String Password = request.substring(3+request.charAt(1),3+request.charAt(1)+request.charAt(2));
                
                System.out.println(username+" "+Password);
                
                PreparedStatement ps = conn.prepareStatement("SELECT password FROM pengguna WHERE username = ?");
                ps.setString(1,username);
                ResultSet result = ps.executeQuery();
                boolean ok = false;
                
                while (result.next()) {
                    if (result.getString(1).equals(Password)) ok = true;
                }
                
                if (ok) {
                    reply = "LOGIN_SUCCESS";
                } else {
                    reply = "LOGIN_FAILED";
                }
            } else if (request.charAt(0) == '1') {
                //Takes the newest list of task
            } else if (request.charAt(0) == '2') {
                //Update status of a task
            } else {
                reply = "Unknown Request";
            }
        } catch (SQLException ex) {
            reply = "Failed to connect to database";
        } catch (ClassNotFoundException ex) {
            reply = "Failed to create class";
        }
    }
    
    private void sendReply() {
        try {
            byte[] buff = reply.getBytes();
            out.write(buff, 0, buff.length);
        } catch(IOException ex) {
            Logger.getLogger(Pengatur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        getRequest();
        process();
        sendReply();
    }
}
