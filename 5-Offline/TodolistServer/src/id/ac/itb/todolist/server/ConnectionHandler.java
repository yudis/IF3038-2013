package id.ac.itb.todolist.server;

import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.model.UpdateStatus;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import id.ac.itb.todolist.model.User;
import id.ac.itb.todolist.dao.UserDao;
import java.util.HashMap;
import java.util.Random;

public class ConnectionHandler extends Thread {

    private static final byte MSG_LOGIN = 0;
    private static final byte MSG_UPDATE = 1;
    private static final byte MSG_SUCCESS = 127;
    private static final byte MSG_FAILED = -1;
    private static Random random = new Random();
    private HashMap<Long, String> session = new HashMap<>();
    private Socket sockClient;

    public ConnectionHandler(Socket sockClient) {
        this.sockClient = sockClient;
    }

    @Override
    public void run() {
        long sessionId = -1;
        try {
            DataOutputStream out = new DataOutputStream(sockClient.getOutputStream());
            DataInputStream in = new DataInputStream(sockClient.getInputStream());

            while (true) {

                /**
                 * *
                 * +-------------------+---------------------+------------------
                 * | MSG_TYPE (1 byte) | SESSION_ID (1 long) | ...
                 * +-------------------+---------------------+------------------
                 *
                 * SESSION_ID ada pada semua message, kecuali MSG_LOGIN
                 */
                
                byte msgType = in.readByte();
                if (msgType != MSG_LOGIN) {
                    sessionId = in.readLong();

                    synchronized (session) {
                        if (!session.containsKey(sessionId)) {
                            out.writeByte(MSG_FAILED);
                            sockClient.close();
                            return;
                        }
                    }
                }

                if (msgType == MSG_LOGIN) {
                    String username = in.readUTF();
                    String password = in.readUTF();

                    UserDao userDao = new UserDao();
                    User user = userDao.getUserLogin(username, password);
                    if (user != null) {
                        synchronized (session) {
                            long i = random.nextLong();
                            while (session.containsKey(i)) {
                                i = random.nextLong();
                            }
                            session.put(i, username);
                            out.writeByte(MSG_SUCCESS);
                            out.writeLong(i);
                            System.out.println(session.get(i));
                        }
                    } else {
                        out.writeByte(MSG_FAILED);
                    }
                } else if (msgType == MSG_UPDATE) {
                    int count = in.readInt();

                    TugasDao tugasDao = new TugasDao();
                    for (int i = 0; i < count; i++) {
                        UpdateStatus us = new UpdateStatus(in.readInt(), in.readBoolean(), new Timestamp(in.readLong()));
                        tugasDao.setStatus(us.getIdTugas(), us.getStatus(), us.getTimestamp());
                    }

                    out.writeByte(MSG_SUCCESS);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            synchronized (session) {
                session.remove(sessionId);
            }
        }
    }
}
