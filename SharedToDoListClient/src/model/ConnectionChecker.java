/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MainFrame;

/**
 *
 * @author FUJITSU
 */
public class ConnectionChecker implements Runnable {
    
    Socket socket;
    DataOutputStream os;
    MainFrame mainF;
    
    public ConnectionChecker(Socket sock, MainFrame mf) {
        socket = sock;
        mainF = mf;
    }

    @Override
    public void run() {
        while (ConnectionHandler.isConnected && SharedToDoListClient.mainF.isVisible()) { //selama masih terhubung dengan server
            String msg = "ping";
            try {
                os = new DataOutputStream(SharedToDoListClient.socket.getOutputStream());
                os.writeUTF(msg);
                os.flush();
            } catch (IOException ex) {
//                Logger.getLogger(ConnectionChecker.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Exception! Koneksi terputus");
                ConnectionHandler.isConnected = false;
                
                //melakukan enable tombol connect
                mainF.connectBtn.setEnabled(true);
                mainF.connectBtn.setText("connect");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ConnectionChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
