package id.ac.itb.todolist.client;

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
import java.util.HashMap;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    private static final byte MSG_UPDATE = 1;
	private static final byte MSG_LIST = 2;
    private static final byte MSG_SUCCESS = 127;
    private static final byte MSG_FAILED = -1;
    private static final byte MSG_LOGIN = 0;
    private Socket sockClient;
    private HashMap<Integer, UpdateStatus> lUpdates = new HashMap<>();
    private long sessionId;
	private List<Tugas> tgsList;
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
	
	public void list() {
        try {
            DataOutputStream out = new DataOutputStream(sockClient.getOutputStream());
            DataInputStream in = new DataInputStream(sockClient.getInputStream());
            out.writeByte(MSG_LIST);
            out.writeLong(sessionId);
            if (tgsList.size()!= 0)
            {
                out.writeInt(tgsList.size());
                for (int i=0;i< tgsList.size();i++)
                {
                    out.writeInt(tgsList.get(i).getId());
                    out.writeLong(tgsList.get(i).getLastMod().getTime());
                }
                
            }
            else
            {
                out.writeInt(0);
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	
    public void updateStatus(int idTugas, boolean status) {
        if (lUpdates.containsKey(idTugas)) {
            lUpdates.remove(idTugas);
        } else {
            lUpdates.put(idTugas, new UpdateStatus(idTugas, status));
        }

//        if (!lUpdates.isEmpty()) {
//            updateServer();
//        }
    }

    private void updateServer() {
        try {
            DataOutputStream out = new DataOutputStream(sockClient.getOutputStream());

            out.writeByte(MSG_UPDATE);
            out.writeLong(sessionId);
            out.writeInt(lUpdates.size());

            System.out.println("size: " + lUpdates.size());

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
