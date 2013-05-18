package id.ac.itb.todolist.client;

import id.ac.itb.todolist.model.Tugas;
import id.ac.itb.todolist.model.UpdateStatus;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;

public class Controller {

    private static final byte MSG_LOGIN = 0;
    private static final byte MSG_UPDATE = 1;
    private static final byte MSG_LIST = 2;
    private static final byte MSG_SUCCESS = 127;
    private static final byte MSG_FAILED = -1;
    
    private String serverName;
    private int port;
    private Socket sockClient;
    private HashMap<Integer, UpdateStatus> lUpdates = new HashMap<>();
    private long sessionId;
    
    public List<Tugas> tgsList = new ArrayList<>();

    public Controller(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    public long getSessionId() {
        return sessionId;
    }

    public void saveObject() {
        try {
            FileOutputStream out = new FileOutputStream("updates.out");
            ObjectOutputStream oos = new ObjectOutputStream(out);

            oos.writeObject(lUpdates);
            oos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadObject() {
        try {
            FileInputStream in = new FileInputStream("updates.out");
            ObjectInputStream ois = new ObjectInputStream(in);

            System.out.println((HashMap<Integer, UpdateStatus>) ois.readObject());
        } catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }
    }

    public boolean connect() {
        try {
            if (sockClient != null && sockClient.isConnected()) {
                sockClient.close();
            }
            sockClient = new Socket(serverName, port);
        } catch (Exception e) {
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

            /////////////////////////////// Client recieves p & g and creates spec
            BigInteger clip = new BigInteger(in.readUTF());
            BigInteger clig = new BigInteger(in.readUTF());
            DHParameterSpec cliParamSpec = new DHParameterSpec(clip, clig);

            /////////////////////////////// Client generates keypair
            KeyPairGenerator cliKPG = KeyPairGenerator.getInstance("DH");
            cliKPG.initialize(cliParamSpec);
            KeyPair cliKP = cliKPG.generateKeyPair();

            byte[] cliPub = cliKP.getPublic().getEncoded();

            /////////////////////////////// PKI exchange and convert back
            out.writeInt(cliPub.length);
            out.write(cliPub);
            byte[] servPub = new byte[in.readInt()];
            in.readFully(servPub);
            DHPublicKey cliServPub = (DHPublicKey) KeyFactory.getInstance("DH").generatePublic(new X509EncodedKeySpec(servPub));

            /////////////////////////////// Client gens secret
            KeyAgreement cliKA = KeyAgreement.getInstance("DH");
            cliKA.init(cliKP.getPrivate(), cliParamSpec);
            cliKA.doPhase(cliServPub, true);
            byte[] cliSecret = cliKA.generateSecret("AES").getEncoded();

            ////////////////////////////// Client encrypt using AES
            Cipher cClient = Cipher.getInstance("AES");
            SecretKeySpec kClient = new SecretKeySpec(cliSecret, 0, 16, "AES");
            cClient.init(Cipher.ENCRYPT_MODE, kClient);

            byte[] dataToSend = username.getBytes();
            byte[] encryptedData = cClient.doFinal(dataToSend);
            out.writeInt(encryptedData.length);
            out.write(encryptedData);

            dataToSend = password.getBytes();
            encryptedData = cClient.doFinal(dataToSend);
            out.writeInt(encryptedData.length);
            out.write(encryptedData);

            byte responseType = in.readByte();
            if (responseType == MSG_SUCCESS) {
                sessionId = in.readLong();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean list() {
        if (sockClient == null || !sockClient.isConnected()) return false;
        
        try {
            DataOutputStream out = new DataOutputStream(sockClient.getOutputStream());
            DataInputStream in = new DataInputStream(sockClient.getInputStream());

            out.writeByte(MSG_LIST);
            out.writeLong(sessionId);

            out.writeInt(tgsList.size());
            for (int i = 0; i < tgsList.size(); i++) {
                out.writeInt(tgsList.get(i).getId());
                out.writeLong(tgsList.get(i).getLastMod().getTime());
            }

            int status;
            do {
                status = in.readInt();
                Tugas tugas = new Tugas();

                if (status == 3) { // Add
                    tugas.readIn(in);
                    System.out.println("Add: " + tugas);
                    tgsList.add(tugas);
                } else if (status == 0) { // Delete
                    int idDel = in.readInt();
                    System.out.println("Del: " + idDel);
                    int j;
                    for (j = 0; j < tgsList.size(); j++) {
                        if (tgsList.get(j).getId() == idDel) {
                            break;
                        }
                    }
                    tgsList.remove(j);
                } else if (status == 1) { // Update
                    tugas.readIn(in);
                    System.out.println("Upd: " + tugas);
                    int j;
                    for (j = 0; j < tgsList.size(); j++) {
                        if (tgsList.get(j).getId() == tugas.getId()) {
                            break;
                        }
                    }
                    tgsList.remove(j);
                    tgsList.add(tugas);
                } else if (status == 2) {
                    System.out.println("Eqs: " + in.readInt());
                } else if (status == -1) {
                    System.out.println("ANEEEEH : " + status);
                }
            } while (status != -1);
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateStatus(int idTugas, boolean status) {
        if (lUpdates.containsKey(idTugas)) {
            lUpdates.remove(idTugas);
        } else {
            lUpdates.put(idTugas, new UpdateStatus(idTugas, status));
        }

        if (!lUpdates.isEmpty()) {
            updateToServer();
        }
    }

    private boolean updateToServer() {
        if (sockClient == null || !sockClient.isConnected()) return false;
        
        try {
            DataOutputStream out = new DataOutputStream(sockClient.getOutputStream());
            DataInputStream in = new DataInputStream(sockClient.getInputStream());

            out.writeByte(MSG_UPDATE);
            out.writeLong(sessionId);
            out.writeInt(lUpdates.size());

            Iterator<UpdateStatus> iter = lUpdates.values().iterator();
            while (iter.hasNext()) {
                UpdateStatus us = iter.next();
                us.writeOut(out);
                System.out.println(us);
            }
            out.flush();

            int response = in.readByte();
            if (response == MSG_SUCCESS) {
                lUpdates.clear();
                return true;
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
}
