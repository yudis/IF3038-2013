/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.client;

import id.ac.itb.todolist.model.UpdateStatus;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edward Samuel
 */
public class Controller {
    private static final byte MSG_UPDATE = 1;
    private static final byte MSG_SUCCESS = 127;
    private static final byte MSG_FAILED = -1;
    
    private Socket sockClient;
    private HashMap<Integer, UpdateStatus> lUpdates = new HashMap<>();
    
    private int sessionId;
    
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
            out.writeInt(sessionId);
            out.writeInt(lUpdates.size());
            
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
