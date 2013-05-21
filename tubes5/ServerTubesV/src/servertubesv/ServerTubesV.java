/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servertubesv;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chritianto Handojo
 */
public class ServerTubesV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket serversock = null;
        try {
            serversock = new ServerSocket(3400);
        } catch (IOException ex) {
            Logger.getLogger(ServerTubesV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while (true) {
            try {
                Thread t = new Thread (new Pengatur(serversock.accept()));
                t.start();
            } catch (IOException ex) {
                Logger.getLogger(ServerTubesV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
