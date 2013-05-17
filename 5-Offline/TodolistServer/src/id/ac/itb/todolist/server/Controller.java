/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    
    private ServerSocket sockServer;
    
    public Controller(int port) throws IOException {
        sockServer = new ServerSocket(port);
    }
    
    public void start()
    {
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
}
