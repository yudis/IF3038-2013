package id.ac.itb.todolist.server;

import id.ac.itb.todolist.model.UpdateStatus;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;

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
                    
                    
                    
                } else if (msgType == MSG_UPDATE) {
                    int count = in.readInt();
                    System.out.println("--- MSG_UPDATE : " + count);
                    ArrayList<UpdateStatus> lUpdates = new ArrayList<>(count);
                    
                    for (int i=0; i<count; i++) {
                        lUpdates.add(new UpdateStatus(in.readInt(), in.readBoolean(), new Timestamp(in.readLong())));
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
