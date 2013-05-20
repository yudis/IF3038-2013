package id.ac.itb.todolist.server;

import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.model.UpdateStatus;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
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

    // Message Code
    private static final byte MSG_LOGIN = 0x00;
    private static final byte MSG_LOGOUT = 0x01;
    private static final byte MSG_UPDATE = 0x10;
    private static final byte MSG_LIST = 0x20;
    private static final byte MSG_SUCCESS = 0x7f;
    private static final byte MSG_FAILED = -1;
    // List Status Code
    private static final byte LIST_STATUS_NEW = 0x00;
    private static final byte LIST_STATUS_EQL = 0x01;
    private static final byte LIST_STATUS_DEL = 0x02;
    private static final byte LIST_STATUS_UPD = 0x03;
    private static final byte LIST_STATUS_END = -1;
    // Attributes
    private static Random random = new Random();
    static final HashMap<Long, String> session = new HashMap<>();
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

                System.out.println("--- MSG_TYPE = " + msgType + " ---");

                if (msgType != MSG_LOGIN) {
                    sessionId = in.readLong();

                    synchronized (session) {
                        if (!session.containsKey(sessionId)) {
                            System.out.println("Session tidak sesuai");
                            out.writeByte(MSG_FAILED);
                            
                            // Tutup Koneksi jika SESSION_ID tidak terdaftar
                            sockClient.close();
                            return;
                        }
                    }
                }

                if (msgType == MSG_LOGIN) {
                    System.out.println(sockClient + " : MSG_LOGIN");
                    System.out.println("------------------------------------------");
                    /////////////////////////////// Server send p and g value                    
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
                        }
                    } else {
                        out.writeByte(MSG_FAILED);
                    }
                } else if (msgType == MSG_LOGOUT) {
                    System.out.println(sockClient + " : MSG_LOGOUT");

                    synchronized (session) {
                        session.remove(sessionId);
                    }

                    out.writeByte(MSG_SUCCESS);
                    
                    // Tutup Koneksi jika logout
                    sockClient.close();
                    break;
                } else if (msgType == MSG_UPDATE) {
                    System.out.println(sockClient + " : MSG_UPDATE");

                    int count = in.readInt();
                    TugasDao tugasDao = new TugasDao();
                    for (int i = 0; i < count; i++) {
                        UpdateStatus us = new UpdateStatus(in.readInt(), in.readBoolean(), new Timestamp(in.readLong()));
                        tugasDao.setStatus(us.getIdTugas(), us.getStatus(), us.getTimestamp());
                        System.out.println("Id = " + us.getIdTugas() + " Status = " + us.getStatus() + " TimeStamp = " +us.getTimestamp());
                        System.out.println("------------------------------------------");
                    }
                     
                    out.writeByte(MSG_SUCCESS);
                } else if (msgType == MSG_LIST) {
                    System.out.println(sockClient + " : MSG_LIST");

                    int jmlTugas = in.readInt();
                    TugasDao tgsDao = new TugasDao();
                    HashMap<Integer, Long> hmlist = new HashMap<>();
                    int id;
                    long timestamp;
                    for (int i = 0; i < jmlTugas; i++) {
                        id = in.readInt();
                        timestamp = in.readLong();
                        hmlist.put(id, timestamp);
                    }

                    HashMap<Integer, Long> hmlistquery = tgsDao.getAllTugasbyUser((String) session.get(sessionId));
                    System.out.println("Tugas dari client = " + hmlist);
                                        
                    out.writeByte(MSG_SUCCESS);

                    Iterator<Map.Entry<Integer, Long>> it = hmlist.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<Integer, Long> pairs = it.next();
                        if (hmlistquery.containsKey(pairs.getKey())) {
                            System.out.print("ID: " + pairs.getKey() + " - ReadTimeStamp: " + pairs.getValue() + " - QueryTimeStamp: " + hmlistquery.get(pairs.getKey()) + " EQ: " + (hmlistquery.get(pairs.getKey()).equals(pairs.getValue())));
                            if (pairs.getValue().equals(hmlistquery.get(pairs.getKey()))) {
                                out.writeByte(LIST_STATUS_EQL);
                                out.writeInt(pairs.getKey());
                                System.out.println(" | TimeStamp Sama");
                                hmlistquery.remove(pairs.getKey());
                            } else {
                                out.writeByte(LIST_STATUS_UPD);
                                tgsDao.getTugas(pairs.getKey(), true, true, true).writeOut(out);
                                System.out.println(" | Update dari client");
                                hmlistquery.remove(pairs.getKey());
                            }
                        } else {
                            out.writeByte(LIST_STATUS_DEL);
                            out.writeInt(pairs.getKey());
                            System.out.println(" | Tugas tidak ada di database");
                            hmlistquery.remove(pairs.getKey());
                        }
                    }

                    Iterator<Map.Entry<Integer, Long>> it2 = hmlistquery.entrySet().iterator();
                    while (it2.hasNext()) {
                        Map.Entry<Integer, Long> pairs = it2.next();
                        out.writeByte(LIST_STATUS_NEW);
                        System.out.print("ID: " + pairs.getKey() + " - ReadTimeStamp: " + pairs.getValue() + " - QueryTimeStamp: " + hmlistquery.get(pairs.getKey()) + " EQ: " + (hmlistquery.get(pairs.getKey()).equals(pairs.getValue())));
                        System.out.println(" | Tugas baru dari server");
                        tgsDao.getTugas(pairs.getKey(), true, true, true).writeOut(out);
                    }
                    System.out.println("List dikirim ke client = " + hmlistquery);
                    System.out.println("------------------------------------------");
                    out.writeByte(LIST_STATUS_END);
                }
                
                out.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            synchronized (session) {
                session.remove(sessionId);
            }
        }
    }

    public static String getSessionsString() {
        StringBuilder sb = new StringBuilder();
        synchronized (session) {
            sb.append("Active Session: ").append(session.size());
            for (Map.Entry<Long, String> val : session.entrySet()) {
                System.out.printf("%10d - %s", val.getKey(), val.getValue());
            }
        }
        return sb.toString();
    }
}
