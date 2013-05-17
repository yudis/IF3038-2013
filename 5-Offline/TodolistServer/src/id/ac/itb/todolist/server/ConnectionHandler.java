package id.ac.itb.todolist.server;

import id.ac.itb.todolist.model.UpdateStatus;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import id.ac.itb.todolist.model.User;
import id.ac.itb.todolist.dao.UserDao;
import java.util.HashMap;
import java.util.Random;

public class ConnectionHandler extends Thread {

    private static final byte MSG_LOGIN = 0;
    private static final byte MSG_UPDATE = 1;
    private static final byte MSG_SUCCESS = 127;
    private static final byte MSG_FAILED = -1;
    
    private Socket sockClient;

    public ConnectionHandler(Socket sockClient) {
        this.sockClient = sockClient;
    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(sockClient.getOutputStream());
            DataInputStream in = new DataInputStream(sockClient.getInputStream());
            
            while (true) {
                byte msgType = in.readByte();
                int sessionId = -1;
                
                if (msgType != MSG_LOGIN) {
                    sessionId = in.readInt();
                }
                
                if (msgType == MSG_LOGIN) {
                    UserDao userDao = new UserDao();
                    HashMap hm = new HashMap();
                    boolean mapped = false;
                    User user =  new User();
                    String username = in.readUTF();
                    String password = in.readUTF();
                    user = userDao.getUserLogin(username,password);
                    if (user != null)
                    {
                        Random rnd = new Random();
                        long i = rnd.nextLong();
                        while (!mapped){
                            i = rnd.nextInt();
                            if (!hm.containsKey(i) ){
                                hm.put(i, username);
                                mapped = true;
                            }
                            
                        }
                        out.writeBoolean(true);
                        out.writeLong(i);
                        System.out.println(hm.get(i)); 
                    }
                    else
                    {
                        out.writeBoolean(false);
                    } 
                } else if (msgType == MSG_UPDATE) {
                    int count = in.readInt();
                    System.out.println("--- MSG_UPDATE : " + count);
                    ArrayList<UpdateStatus> lUpdates = new ArrayList<>(count);
                    
                    for (int i=0; i<count; i++) {
                        lUpdates.add(new UpdateStatus(in.readInt(), in.readBoolean()));
                    }
                    
                    System.out.println(lUpdates);
                    
                    out.writeByte(MSG_SUCCESS);
                    
                    System.out.println("DONE");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
