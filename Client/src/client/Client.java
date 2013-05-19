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
        ac = new ArrayList<Content>();
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

    public ArrayList<Content> getAc() {
        return ac;
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
        int tugascount = in.readInt();
        System.out.println(tugascount);
        for (int idx = 0; idx < tugascount; idx++) {
            String nama = in.readUTF();
            System.out.println(nama);
            String pemilik = in.readUTF();
            System.out.println(pemilik);
            String kategori = in.readUTF();
            System.out.println(kategori);
            String date = in.readUTF();
            System.out.println(date);
            boolean status = in.readBoolean();
            System.out.println(status);

            String assignees = "";
            int aSize = in.readInt();
            System.out.println("Size assignees :" + aSize);
            if (aSize == 0) {
                assignees += "-";
            }
            for (int i = 0; i < aSize; i++) {
                assignees += in.readUTF();
                if (i != (aSize - 1)) {
                    assignees += ", ";
                }
            }
            System.out.println(assignees);
            String tags = "";

            int tSize = in.readInt();
            System.out.println("size tags : " + tSize);
            if (tSize == 0) {
                tags += "-";
            }
            for (int i = 0; i < tSize; i++) {
                tags += in.readUTF();
                if (i != (tSize - 1)) {
                    tags += ", ";
                }
            }
            System.out.println(tags);
            Content c = new Content(nama, pemilik, kategori, date, assignees, tags, status);
            ac.add(c);
        }
//            Just Printing
//        for (int i = 0; i < ac.size(); i++) {
//            System.out.println(ac.get(i));
//        }
    }

    public String getUsername() {
        return username;
    }
}
