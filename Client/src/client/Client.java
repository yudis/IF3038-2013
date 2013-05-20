package client;

import Util.FileManager;
import Util.Log;
import Util.Message;
import clientgui.LoginForm;
import id.ac.itb.todolist.model.Content;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public Socket client;
    private String username;
    private String servername;
    private int port;
    private FileManager fm;
    ArrayList<Content> ac;
    private Thread dc;
    private boolean isDC;

    public void setDc(Thread dc) {
        this.dc = dc;
    }

    public void setIsDC(boolean isDC) {
        this.isDC = isDC;
    }

    public Thread getDc() {
        return dc;
    }

    public boolean getIsDC() {
        return isDC;
    }

    public Client() {
        client = null;
        fm = new FileManager();
        isDC = false;
        dc = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("badut thread : " + dc.isAlive());
                    while (!connect(servername, port)) {
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

    public boolean connect(String serverName, int port) throws InterruptedException {
        //System.out.println("Connecting to " + serverName
        //        + " on port " + port);
        this.servername = serverName;
        this.port = port;
        try {
            client = new Socket(serverName, port);
            client.setSoTimeout(5000);
            System.out.println("CONNECT");
            System.out.println("TIMEOUT : " + client.getSoTimeout());
            isDC = false;
            HashMap<Integer, Log> hm = fm.parseFile(username);
            if (hm.size() > 0) {
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);

                out.writeByte(Message.MSG_SYNC);

                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);

                Iterator it = hm.entrySet().iterator();
                out.writeInt(hm.size());
                while (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry) it.next();
                    out.writeInt((int) pairs.getKey());
                    out.writeUTF(((Log) pairs.getValue()).getTimestamp().toString());
                    out.writeBoolean(((Log) pairs.getValue()).isValue());
                    it.remove(); // avoids a ConcurrentModificationException
                }
            }
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
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
                fm.createFile(username);
                return true;
            }
        } catch (IOException io) {
            isDC = true;
            dc = new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println("badut thread : " + dc.isAlive());
                        while (!connect(servername, port)) {
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            dc.start();
        }
        return false;
    }

    public boolean updateStatus(int idtugas, boolean status) {

        System.out.println("di update status : isDC" + isDC);
        if (!isDC) {
            try {
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);

                out.writeByte(Message.MSG_UPDATE);
                out.writeInt(idtugas);
                out.writeBoolean(status);

                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);

                byte resType = in.readByte();
                if (resType == Message.MSG_SUCCESS) {
                    return true;
                }

                if (dc.isAlive()) {
                    System.out.println("-----------------");
                    System.out.println("STOPPPPPPPP");

                    System.out.println("-----------------");
                    dc.destroy();
                }
            } catch (IOException io) {
                isDC = true;
                System.out.println("dc di updatestatus " + isDC);
                dc = new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("badut thread : " + dc.isAlive());
                            while (!connect(servername, port)) {
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
                dc.start();
            }
            return false;

        } else {
            return true;

        }
    }

    //
    public FileManager getFm() {
        return fm;
    }

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

    public void getList() {
        ac = new ArrayList<Content>();
        try {
            System.out.println("masuklist");
            OutputStream outToServer = LoginForm.getClient().getClient().getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            InputStream inFromServer = LoginForm.getClient().getClient().getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            out.writeByte(Message.MSG_LIST);
            out.writeUTF(username);
            int tugascount = in.readInt();
            System.out.println(tugascount);
            for (int idx = 0; idx < tugascount; idx++) {
                int id = in.readInt();
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
                Content c = new Content(id, nama, pemilik, kategori, date, assignees, tags, status);
                ac.add(c);
            }
        } catch (IOException e) {
            isDC = true;
            dc = new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println("badut thread : " + dc.isAlive());
                        while (!connect(servername, port)) {
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            dc.start();
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
