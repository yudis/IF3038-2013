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
import java.io.Serializable;
import java.math.BigInteger;
import java.net.Socket;
import java.net.SocketException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Controller implements Serializable {
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
    // Attribute
    private String serverName;
    private int port;
    private transient Socket sockClient;
    // Configuration
    public HashMap<Integer, UpdateStatus> logUpdate = new HashMap<>();
    public long sessionId;
    public List<Tugas> listTugas = new ArrayList<>();

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

    public void saveState(String filename) throws IOException {
        FileOutputStream out = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(out);

        oos.writeObject(this);
        oos.flush();
    }

    public static Controller loadState(String filename) throws IOException {
        FileInputStream in = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(in);
        
        try {
            return (Controller) ois.readObject();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean connect() {
        try {
            if (sockClient != null && sockClient.isConnected()) {
                sockClient.close();
            }
            sockClient = new Socket(serverName, port);
            sockClient.setSoTimeout(30000);
        } catch (Exception e) {
            e.printStackTrace();
            sockClient = null;
        }

        return (sockClient != null);
    }

    public boolean login(String username, String password) throws IOException {
        if (sockClient == null) {
            throw new SocketException("Socket is null");
        }

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
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean isConnect() {
        return ((sockClient != null && sockClient.isConnected()));
    }

    public boolean logout() throws IOException {
        if (sockClient == null) {
            throw new SocketException("Socket is null");
        }

        DataOutputStream out = new DataOutputStream(sockClient.getOutputStream());
        DataInputStream in = new DataInputStream(sockClient.getInputStream());

        out.writeByte(MSG_LOGOUT);
        out.writeLong(sessionId);
        out.flush();

        byte msgResponse = in.readByte();
        if (msgResponse == MSG_SUCCESS) {
            sessionId = -1;
            logUpdate.clear();
            listTugas.clear();

            return true;
        }
        return false;
    }

    public boolean list() throws IOException {
        if (sockClient == null) {
            throw new SocketException("Socket is null");
        }

        DataOutputStream out = new DataOutputStream(sockClient.getOutputStream());
        DataInputStream in = new DataInputStream(sockClient.getInputStream());

        out.writeByte(MSG_LIST);
        out.writeLong(sessionId);

        out.writeInt(listTugas.size());
        for (int i = 0; i < listTugas.size(); i++) {
            out.writeInt(listTugas.get(i).getId());
            out.writeLong(listTugas.get(i).getLastMod().getTime());
        }

        out.flush();

        if (in.readByte() == MSG_SUCCESS) {
            byte status;
            do {
                status = in.readByte();
                Tugas tugas = new Tugas();

                if (status == LIST_STATUS_NEW) { // Add
                    tugas.readIn(in);
                    listTugas.add(tugas);
                } else if (status == LIST_STATUS_DEL) { // Delete
                    int idDel = in.readInt();
                    int j;
                    for (j = 0; j < listTugas.size(); j++) {
                        if (listTugas.get(j).getId() == idDel) {
                            break;
                        }
                    }
                    listTugas.remove(j);
                } else if (status == LIST_STATUS_UPD) { // Update
                    tugas.readIn(in);
                    int j;
                    for (j = 0; j < listTugas.size(); j++) {
                        if (listTugas.get(j).getId() == tugas.getId()) {
                            break;
                        }
                    }
                    listTugas.remove(j);
                    listTugas.add(tugas);
                } else if (status == LIST_STATUS_EQL) {
                    in.readInt();
                }
            } while (status != LIST_STATUS_END);

            return true;
        }

        return false;
    }

    public void updateStatus(int idTugas, boolean status) throws IOException {
        UpdateStatus us = new UpdateStatus(idTugas, status);
        
        for (Iterator<Tugas> iter = listTugas.iterator(); iter.hasNext();) {
            Tugas t = iter.next();
            if (t.getId() == idTugas) {
                t.setLastMod(us.getTimestamp());
                t.setStatus(status);
                break;
            }
        }

        if (logUpdate.containsKey(idTugas)) {
            logUpdate.remove(idTugas);
        } else {
            logUpdate.put(idTugas, us);
        }

        if (!logUpdate.isEmpty()) {
            updateToServer();
        }
    }

    public boolean updateToServer() throws IOException {
        if (sockClient == null) {
            throw new SocketException("Socket is null");
        }

        DataOutputStream out = new DataOutputStream(sockClient.getOutputStream());
        DataInputStream in = new DataInputStream(sockClient.getInputStream());

        out.writeByte(MSG_UPDATE);
        out.writeLong(sessionId);
        out.writeInt(logUpdate.size());

        Iterator<UpdateStatus> iter = logUpdate.values().iterator();
        while (iter.hasNext()) {
            UpdateStatus us = iter.next();
            us.writeOut(out);
            System.out.println(us);
        }
        out.flush();

        int response = in.readByte();
        if (response == MSG_SUCCESS) {
            logUpdate.clear();
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Controller{" + "serverName=" + serverName + ", port=" + port + ", sockClient=" + sockClient + ", logUpdate=" + logUpdate + ", sessionId=" + sessionId + ", listTugas=" + listTugas + '}';
    }
}
