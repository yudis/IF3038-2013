package client;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            client = new Socket(serverName, port);
            System.out.println("CONNECT");
            System.out.println("TIMEOUT : " + client.getSoTimeout());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
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
            System.out.println("aaaa");
        }
        return false;
    }
//
    public boolean checkInput(DataInputStream di) {
        if (client == null) {
            return false; }
        else {
            try {
                System.out.println("READDDD");
                di.read();
                return true;
            } catch (IOException io) {
                io.printStackTrace();
                return false;
            }
        }
    }
//    public DataInputStream getInputStream() {
//        if (client != null) {
//            InputStream inFromServer;
//            try {
//                inFromServer = client.getInputStream();
//                DataInputStream in = new DataInputStream(inFromServer);
//
//                return in;
//            } catch (IOException ex) {
//                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//        return null;
//    }
//
//    public Socket getClient() {
//        return client;
//    }
}
