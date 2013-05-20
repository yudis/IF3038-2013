/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.Normalizer;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author ASUS
 */
public class ServerHandler implements Runnable{
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    public boolean active;    
    LoginForm form;
    int id;
    
    public ServerHandler(Socket socket, LoginForm form,int id) {
        this.socket = socket;
        this.form = form;
        this.id = id;
        active = true;
    }
    
    @Override
    public void run() {
        try{
            in = new ObjectInputStream(socket.getInputStream());
        }catch(Exception ex){
            ex.printStackTrace();
            active = false;
        }
        
        while(active){
            try {
                Object msg = in.readObject();
                JSONObject obj= (JSONObject) JSONValue.parse(msg.toString());
                if (obj.get("code").toString().equals("10")){
                    id = Integer.parseInt(obj.get("id").toString());
                    form.SetTitleID(id);
                }else if (obj.get("code").toString().equals("127")){
                    form.SetLoginSukses(obj.get("message").toString());
                }else if (obj.get("code").toString().equals("128")){
                    form.SetMessageBox("Login Gagal");
                }else if (obj.get("code").toString().equals("22")){
                    form.RefreshTask(obj.get("message").toString());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                active = false;
                try {
                    socket.close();
                } catch (IOException ex1) {
                    ex1.printStackTrace();
                }
                form.SetEnableBtnReconnect(true);
            }
        }
    }
}