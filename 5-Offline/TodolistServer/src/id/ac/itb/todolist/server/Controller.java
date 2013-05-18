package id.ac.itb.todolist.server;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.AlgorithmParameterGenerator;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.DHParameterSpec;

public class Controller extends Thread {

    private ServerSocket sockServer;
    
    static DHParameterSpec servParamSpec;
    static BigInteger servp;
    static BigInteger servg;
    static KeyPair servKP;
    static byte[] servPub;

    public Controller(int port) throws IOException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException {
        sockServer = new ServerSocket(port);

        AlgorithmParameterGenerator servAPG = AlgorithmParameterGenerator.getInstance("DH");
        servAPG.init(512);

        /////////////////////////////// Server generates spec
        servParamSpec = servAPG.generateParameters().getParameterSpec(DHParameterSpec.class);
        servp = servParamSpec.getP();
        servg = servParamSpec.getG();

        /////////////////////////////// Server generates keypair
        KeyPairGenerator servKPG = KeyPairGenerator.getInstance("DH");
        servKPG.initialize(servParamSpec);
        servKP = servKPG.generateKeyPair();
        servPub = servKP.getPublic().getEncoded();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket sockClient = sockServer.accept();

                Thread thread = new Thread(new ConnectionHandler(sockClient));
                thread.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
    
    public void printSession() {
        System.out.println(ConnectionHandler.getSessionsString());
    }
}
