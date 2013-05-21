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
import java.util.HashMap;
import java.util.Map;
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
            //System.out.println(request);
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
                
                //System.out.println(username+" "+Password);
                
                PreparedStatement ps = conn.prepareStatement("SELECT password FROM pengguna WHERE username = ?");
                ps.setString(1,username);
                ResultSet result = ps.executeQuery();
                boolean ok = false;
                
                while (result.next()) {
                    if (result.getString(1).equals(Password)) ok = true;
                }
                ps.close();
                
                if (ok) {
                    reply = "LOGIN_SUCCESS";
                } else {
                    reply = "LOGIN_FAILED";
                }
            } else if (request.charAt(0) == '1') {
                //Takes the newest list of task
                PreparedStatement ps = conn.prepareStatement("SELECT id_tugas FROM mengerjakan WHERE username = ? ORDER BY id_tugas");
                ps.setString(1, request.substring(1));
                ResultSet result = ps.executeQuery();
                result.last();
                int[] nomor = new int[result.getRow()];
                result.beforeFirst();
                for (int i=0;result.next();++i) {
                    nomor[i] = result.getInt(1);
                }
                result.close();
                ps.close();
                
                //System.out.println("Jumlah tugas = "+nomor.length);
                
                ps = conn.prepareStatement("SELECT * FROM kategori");
                result = ps.executeQuery();
                Map<Integer,String> kategori = new HashMap<>();
                while (result.next()) {
                    kategori.put(result.getInt("id_kategori"), result.getString("nama_kategori"));
                }
                result.close();
                ps.close();
                
                StringBuilder sb = new StringBuilder();
                sb.append(nomor.length).append("\n");
                for (int i=0;i<nomor.length;++i) {
                    Statement query = conn.createStatement();
                    ResultSet r1 = query.executeQuery("SELECT * FROM tugas WHERE id_tugas = "+nomor[i]);
 
                    while (r1.next()) {
                        sb.append(r1.getInt("id_tugas")).append("\n");
                        sb.append(r1.getString("nama_tugas")).append("\n");
                        sb.append(kategori.get(r1.getInt("id_kategori"))).append("\n");
                        sb.append(r1.getInt("status")).append("\n");
                        sb.append(r1.getString("deadline")).append("\n");
                        sb.append(r1.getString("tag")).append("\n");
                    }
                    r1.close();
                    //System.out.println("Ambil data tugas selesai");
                    
                    ResultSet r2 = query.executeQuery("SELECT username FROM mengerjakan WHERE id_tugas = "+nomor[i]);
                    r2.last();
                    sb.append(r2.getRow()).append("\n");
                    r2.beforeFirst();
                    while (r2.next()) {
                        sb.append(r2.getString(1)).append("\n");
                    }
                    r2.close();
                    //System.out.println("Ambil Assignee selesai");
                }
                reply = sb.toString();
            } else if (request.charAt(0) == '2') {
                //Update status of a task
                String[] args = request.split(" ");
                //System.out.println(args[0]);
                //System.out.println(args[1]);
                //System.out.println(args[2]);
                //System.out.println(args[3]);
                
                PreparedStatement ps = conn.prepareStatement("SELECT timestamp FROM tugas WHERE id_tugas = ?");
                ps.setString(1, args[2]);
                ResultSet result = ps.executeQuery();
                boolean flag = false;
                while (result.next()) {
                    if (result.getLong(1) < Long.parseLong(args[1])) flag = true;
                }
                //System.out.println(result.isAfterLast());                
                result.close();
                ps.close();
                
                if (flag) {
                    //System.out.println("UPDATING STATUS");
                    ps = conn.prepareStatement("UPDATE tugas SET status = ?,timestamp = ? WHERE id_tugas = ?");
                    ps.setString(1,args[3]);
                    ps.setString(2,args[1]);
                    ps.setString(3,args[2]);
                    int dapat = ps.executeUpdate();
                    ps.close();
                    reply = Integer.toString(dapat);
                } else {
                    reply = "0";
                }
            } else {
                reply = "Unknown Request";
            }
            
            conn.close();
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
