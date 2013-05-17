package id.ac.itb.todolist.server;

import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.model.UpdateStatus;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import id.ac.itb.todolist.model.User;
import id.ac.itb.todolist.dao.UserDao;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.SecretKeySpec;

public class ConnectionHandler extends Thread {

    private static final byte MSG_LOGIN = 0;
    private static final byte MSG_UPDATE = 1;
	private static final byte MSG_LIST = 2;
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
                    out.writeUTF(Controller.servp.toString());
                    out.writeUTF(Controller.servg.toString());

                    /////////////////////////////// PKI exchange and convert back
                    byte[] cliPub = new byte[in.readInt()];
                    in.readFully(cliPub);
                    out.writeInt(Controller.servPub.length);
                    out.write(Controller.servPub);
                    DHPublicKey servCliPub = (DHPublicKey) KeyFactory.getInstance("DH").generatePublic(new X509EncodedKeySpec(cliPub));

                    /////////////////////////////// Server generates secret
                    KeyAgreement servKA = KeyAgreement.getInstance("DH");
                    servKA.init(Controller.servKP.getPrivate(), Controller.servParamSpec);
                    servKA.doPhase(servCliPub, true);
                    byte[] servSecret = servKA.generateSecret("AES").getEncoded();

                    ////////////////////////////// Server decrypt using AES
                    Cipher cServer = Cipher.getInstance("AES");
                    SecretKeySpec kServer = new SecretKeySpec(servSecret, 0, 16, "AES");
                    cServer.init(Cipher.DECRYPT_MODE, kServer);
                    
                    byte[] encryptedData = new byte[in.readInt()];
                    in.readFully(encryptedData);
                    byte[] data = cServer.doFinal(encryptedData);
                    String username = new String(data);

                    encryptedData = new byte[in.readInt()];
                    in.readFully(encryptedData);
                    data = cServer.doFinal(encryptedData);
                    String password = new String(data);
                    
                    System.out.println(username);
                    System.out.println(password);
                    
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
                }else if (msgType == MSG_LIST){
                    int jmlTugas = in.readInt();
                    TugasDao tgsDao = new TugasDao();
                    HashMap hmlist = new HashMap();
                    int id;
                    long timestamp;
                    for (int i = 0; i< jmlTugas;i++)
                    {
                        id = in.readInt();
                        timestamp = in.readLong();
                        hmlist.put(id, timestamp);
                    }
                    
                    tgsDao.getAllTugasbyUser((String)hm.get(sessionId));
                    Iterator it = hmlist.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pairs = (Map.Entry)it.next();
                        System.out.println(pairs.getKey() + " = " + pairs.getValue());

                        it.remove(); // avoids a ConcurrentModificationException
                    }
                        
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
