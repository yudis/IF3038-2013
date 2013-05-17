/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.client;

import id.ac.itb.todolist.model.UpdateStatus;
import id.ac.itb.todolist.model.Tugas;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edward Samuel
 */
public class Controller {
    private static final byte MSG_LOGIN = 0;
    private static final byte MSG_UPDATE = 1;
    private static final byte MSG_LIST = 2;
    private static final byte MSG_SUCCESS = 127;
    private static final byte MSG_FAILED = -1;
    
    private Socket sockClient;
    private HashMap<Integer, UpdateStatus> lUpdates = new HashMap<>();
    
    private long sessionId;
    private List<Tugas> tgsList;
    
    public long getSessionId() {
        return sessionId;
    }
    
    public boolean connect(String serverName, int port) {
        try {
            if (sockClient != null && sockClient.isConnected()) {
                sockClient.close();
            }
            sockClient = new Socket(serverName, port);
        } catch (IOException e) {
            e.printStackTrace();
            sockClient = null;
        }
        
        return (sockClient != null);
    }
    
     public boolean login(String username, String password) {
        try {
            DataOutputStream out = new DataOutputStream(sockClient.getOutputStream());
            DataInputStream in = new DataInputStream(sockClient.getInputStream());
            out.writeByte(MSG_LOGIN);
            out.writeUTF(username);
            out.writeUTF(password);
            if(in.readBoolean())
            {
                sessionId =  in.readLong();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return false;
    }
     
     public void list() {
        try {
            DataOutputStream out = new DataOutputStream(sockClient.getOutputStream());
            DataInputStream in = new DataInputStream(sockClient.getInputStream());
            out.writeByte(MSG_LIST);
            out.writeLong(sessionId);
            if (tgsList.size()!= 0)
            {
                out.writeInt(tgsList.size());
                for (int i=0;i< tgsList.size();i++)
                {
                    out.writeInt(tgsList.get(i).getId());
                    out.writeLong(tgsList.get(i).getLastMod().getTime());
                }
                
            }
            else
            {
                out.writeInt(0);
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
     
    public void updateStatus(int idTugas, boolean status) {
        if (lUpdates.containsKey(idTugas))
        {
            lUpdates.remove(idTugas);
        }
        else
        {
            lUpdates.put(idTugas, new UpdateStatus(idTugas, status));
        }
        
        if (!lUpdates.isEmpty()) {
            updateServer();
        }
    }
    
    private void updateServer()
    {
        try {
            DataOutputStream out = new DataOutputStream(sockClient.getOutputStream());
            
            out.writeByte(MSG_UPDATE);
            out.writeLong(sessionId);
            out.writeInt(lUpdates.size());
            
            System.out.println("size: " + lUpdates.size());
            
            Iterator<UpdateStatus> iter = lUpdates.values().iterator();
            while (iter.hasNext()) {
                UpdateStatus us = iter.next();
                us.writeOut(out);
                System.out.println(us);
            }
            out.flush();
            System.out.println("DONE");
            
            DataInputStream in = new DataInputStream(sockClient.getInputStream());
            int response = in.readByte();
            if (response == MSG_SUCCESS) {
                System.out.println("Berhasil update status ke server.");
                lUpdates.clear();
            } else {
                System.out.println("Gagal update status ke server.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
