/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author FUJITSU
 */
public class ConnectionHandler {
    
    private static Socket socket;
    private static DataInputStream is;
    private static DataOutputStream os;
    public static boolean isConnected;
    private ConnectionChecker connChecker;
    
    public static final String IP_ADDRESS = "127.0.0.1";
    public static final int PORT_NUM = 2345;
//    public final String IP_ADDRESS = "192.168.0.194";
    
    public ConnectionHandler() {
        isConnected = false;
    }
    
    public static Socket getConnection() throws UnknownHostException, IOException {
//        socket = new Socket("192.168.0.104", 10000);
        socket = new Socket(IP_ADDRESS, PORT_NUM);
        isConnected = true;
        
        return socket;
    }
    
    public static void sendString(String str) throws IOException {
        os = new DataOutputStream(socket.getOutputStream());
        is = new DataInputStream(socket.getInputStream());
        
//        if (socket.isClosed()) System.out.println("socket sudah ditutup");
//        else System.out.println("socket masih dibuka");
        
        if (socket!=null && os!=null && is!=null) {
            os.writeUTF(str);
            os.flush();
        }
    }
    
    public static String listen() throws IOException {
        String ack = "";
        ack = is.readUTF();
//        System.out.println(ack);
        
        return ack;
    }
    
    public static void listenFile() {
        
    }
}
