package client;

import java.net.*;
import java.io.*;

public class Client {
    public Socket client;
    
    // Message
    private static final byte MSG_LOGIN = 1;
    private static final byte MSG_UPDATE = 2;
    private static final byte MSG_LIST = 3;
    private static final byte MSG_SUCCESS = 0;
    private static final byte MSG_FAILED = -1;
    
    public Client() {
        client = null;
    }
    
    public boolean connect(String serverName, int port) throws InterruptedException {
        System.out.println("Connecting to " + serverName
                + " on port " + port);
        try {
//                    System.out.println("Just connected to " + client.getRemoteSocketAddress());
//                    OutputStream outToServer = client.getOutputStream();
//                    DataOutputStream out = new DataOutputStream(outToServer);
//                    while (true) {
//                        out.writeUTF("Hello from " + client.getLocalSocketAddress());
//                        InputStream inFromServer = client.getInputStream();
//                        DataInputStream in = new DataInputStream(inFromServer);
//                        System.out.println("Server says " + in.readUTF());
//
//                        Thread.sleep(1000);
//                    }
            client = new Socket(serverName, port);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean login(String username, String password) {
        try {
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeByte(MSG_LOGIN);
            out.writeUTF(username);
            out.writeUTF(password);
            
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            byte resType = in.readByte();
            if (resType == MSG_SUCCESS) {
                return true;
            }
        } catch (IOException io) {
            
        }
        return false;
    }
}
