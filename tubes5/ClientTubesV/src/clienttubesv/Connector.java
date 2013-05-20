/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttubesv;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chritianto Handojo
 */
public class Connector {
    private Socket sock;
    private InputStream in;
    private OutputStream out;
    private String request,reply;
    
    Connector(String ipaddr, int port) {
        sock = null;
        try {
            sock = new Socket(ipaddr, port);
            in = sock.getInputStream();
            out = sock.getOutputStream();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setRequest(String s) {
        request = s;
    }
    
    void sendRequest() {
        try {
            byte [] sendBuff;
            sendBuff = request.getBytes();
            out.write(sendBuff,0,sendBuff.length);
        } catch (IOException ex) {
            System.err.println("IOException in SendRequest");
        }   
    }
    
    public String getReply() {
        reply = "";
        try {
            int dataSize = 0;
            while (dataSize == 0) {
                dataSize = in.available();
            }
            byte [] recvBuff = new byte[dataSize];
            in.read(recvBuff, 0, dataSize);
            reply = new String(recvBuff,0,dataSize);
        } catch (IOException ex) {
            System.err.println("IOException in getResponse");
        }
        return reply;
    }
}
