/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package STDLClient;

import Protocol.ArsMsgType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arief
 */
public class STDLClientSender {

    private String feedback = null;
    private STDLClientController clientController = null;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public STDLClientSender(STDLClientController cC) {
        clientController = cC;
    }

    public boolean createConnection(String address, int port) {

        try {
            socket = new Socket(address, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return true;
        } catch (UnknownHostException ex) {
            //Logger.getLogger(STDLClientSender.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("host unreachable");
            return false;
        } catch (IOException ex) {
            //Logger.getLogger(STDLClientSender.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("host unreachable");
            return false;
        }

    }

    public boolean login(String userId, String pass) {
        sendMessage(ArsMsgType.MsgLogin(userId, pass));
        feedback = receiveMessage();
        if (ArsMsgType.getCode(feedback.toCharArray()) == ArsMsgType.SUCCESS_CODE) {
            return true;
        } else {
            return false;
        }
    }

    public String list() {
        String outp = null;
        sendMessage(ArsMsgType.MsgList());
        outp = receiveMessage();
        return outp;
    }

    public String detail(String namaTask) {
        String outp = null;
        sendMessage(ArsMsgType.MsgDetail(namaTask));
        outp = receiveMessage();
        return outp;
    }

    public boolean logout() {
        sendMessage(ArsMsgType.MsgLogout());
        feedback = receiveMessage();
        if (ArsMsgType.getCode(feedback.toCharArray()) == ArsMsgType.SUCCESS_CODE) {
            return true;
        } else {
            return false;
        }
    }

    public boolean changeStatus(String namaTask, String status, Timestamp tS) {
        sendMessage(ArsMsgType.MsgChangeStatus(namaTask, status, tS));
        feedback = receiveMessage();
        if (ArsMsgType.getCode(feedback.toCharArray()) == ArsMsgType.SUCCESS_CODE) {
            return true;
        } else {
            return false;
        }
    }

    public void sendMessage(char[] msg) {
        String toSend = clientController.eGG.encryptString(new String(msg));
        System.out.println(toSend);
        out.println(toSend);
        //out.println(msg);
    }

    public String receiveMessage() {
        try {
            return in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(STDLClientSender.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("connection reset");
            clientController.disconnect();
            return "";
        }
    }

    public void disconnect() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(STDLClientSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
