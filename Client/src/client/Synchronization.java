/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.TreeMap;
import org.json.simple.JSONValue;

/**
 *
 * @author ASUS
 */
public class Synchronization implements Runnable{
    Socket socket;
    ObjectOutputStream out;
    boolean active;
    
    public Synchronization(Socket socket) {
        this.socket = socket;
        active = true;
        InitOut(socket);
    }
    
    @Override
    public void run() {
        while(active){
            try{
                Thread.sleep(10000);
                TreeMap<String,Object> msg = new TreeMap<String, Object>();
                msg.put("code",21);
                out.writeObject(JSONValue.toJSONString(msg));
                out.flush();
            }catch(Exception ex){
                ex.printStackTrace();
                active = false;
            }
        }
    }

    private void InitOut(Socket socket){
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            out = null;
        }
    }
}