/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package STDLServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arief
 */
public class STDLServerListener extends Thread {

    private STDLServerController serverController = null;
    private boolean listening = true;
    private Vector<STDLServerListenerThread> threadVector = new Vector<>();
    ServerSocket serverSocket = null;

    public STDLServerListener(STDLServerController sC) {
        serverController = sC;
    }

    @Override
    public void run() {
        try {

            serverSocket = new ServerSocket(8088);
            while (isListening()) {
                STDLServerListenerThread sSLT = new STDLServerListenerThread(serverSocket.accept(), this);
                sSLT.start();
                threadVector.add(sSLT);
            }

            serverSocket.close();

        } catch (IOException ex) {
            Logger.getLogger(STDLServerListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void disconnect() {
        try {
            for (int i = 0; i < threadVector.size(); i++) {
                threadVector.get(i).disconnect();
            }
            serverSocket.close();
            threadVector.setSize(0);
            this.join();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(STDLServerListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the listening
     */
    public boolean isListening() {
        return listening;
    }

    /**
     * @param listening the listening to set
     */
    public void setListening(boolean listening) {
        this.listening = listening;
    }

    /**
     * @return the serverController
     */
    public STDLServerController getServerController() {
        return serverController;
    }
}
