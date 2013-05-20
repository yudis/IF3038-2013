/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import view.LoginFrame;
import view.MainFrame;
/**
 *
 * @author FUJITSU
 */
public class SharedToDoListClient {
    
    private static ConnectionHandler connHandler;
    private static Socket socket;
    
    public static void main(String[] args) throws UnknownHostException, IOException {
//        LoginView loginV = new LoginView();
//        loginV.createGUI();
        
//        create connection
        connHandler = new ConnectionHandler();
        socket = ConnectionHandler.getConnection();
        System.out.println("Koneksi berhasil");
        
        //membuka halaman login
        LoginFrame loginF = new LoginFrame(socket);
        loginF.setVisible(true);
    }
}
