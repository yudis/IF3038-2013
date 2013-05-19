package client;

import Util.Message;
import clientgui.LoginForm;
import id.ac.itb.todolist.model.Content;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public Socket client;
    private String username;
    ArrayList<Content> ac;
    // Message

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

            out.writeByte(Message.MSG_LOGIN);
            out.writeUTF(username);
            out.writeUTF(password);

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            byte resType = in.readByte();
            if (resType == Message.MSG_SUCCESS) {
                this.username = username;
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
            return false;
        } else {
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
    //    }
    //    }
    public Socket getClient() {
        return client;
    }

    public void getList() throws IOException {
        
        OutputStream outToServer = LoginForm.getClient().getClient().getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
        InputStream inFromServer = LoginForm.getClient().getClient().getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        out.writeByte(Message.MSG_LIST);
        out.writeUTF(username);
        for (int idx = 0; idx < in.readInt(); idx++) {
            String nama = in.readUTF();
            String pemilik = in.readUTF();
            String kategori = in.readUTF();
            String date = in.readUTF();
            boolean status = in.readBoolean();
            String tags = "";
            for (int i = 0; i < in.readInt(); i++) {
                tags += in.readUTF();
                if (i != (in.readInt() - 1)) {
                    tags += ", ";
                }
            }
            String assignees = "";
            for (int i = 0; i < in.readInt(); i++) {
                assignees += in.readUTF();
                if (i != (in.readInt() - 1)) {
                    assignees += ", ";
                }
            }
            Content c = new Content(nama, pemilik, kategori, date, tags, assignees, status);
            ac.add(c);
        }    
    }

    public String getUsername() {
        return username;
    }
}
