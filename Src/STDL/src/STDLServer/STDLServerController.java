/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package STDLServer;

import DBHelper.MyDatabase;
import ElGamal.ElGamalGenerator;
import STDLClient.STDLClientController;
import STDLClient.STDLClientSender;
import com.mysql.jdbc.util.ServerController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arief
 */
public class STDLServerController {
    
    private STDLServerListener serverListener = null;
    private STDLServerGUI serverGUI = null;
    private boolean multiLogin = true;
    private int currentUser = 0;
    private Vector<String> userVector = new Vector<>();
    public ElGamalGenerator eGG = new ElGamalGenerator();
    
    public STDLServerController(){
        serverGUI = new STDLServerGUI(this);
        serverGUI.setVisible(true);
    }
    
    public void conStart(){
        serverListener = new STDLServerListener(this);
        serverListener.start();
        serverListener.setListening(true);
        serverGUI.setConnectionStatus(true);
        logging("Server Started on port 8088");
    }
    
    public void conStop(){
        serverListener.disconnect();
        serverListener.setListening(false);
        serverGUI.setConnectionStatus(false);
        logging("Server Stopped");
        currentUser = 0;
        serverGUI.setUserCountLabel(currentUser);
    }
    
    public void setMultiLogin(boolean state){
        multiLogin = state;
        serverGUI.setMultiLoginStatus(state);
    }
    
    public void logging(String log){
        serverGUI.appendLog(log);
    }
    
    public boolean userConnect(String uname){
        if(userVector.contains(uname) && !multiLogin)
            return false;
        else {
            userVector.add(uname);
            currentUser++;
            serverGUI.setUserCountLabel(currentUser);
            logging("user : "+uname+" connected");
            return true;
        }
    }
    
    public void userDisconnect(String uname){
        userVector.remove(uname);
        currentUser--;
        serverGUI.setUserCountLabel(currentUser);
        logging("user : "+uname+" disconnected");
    }
    
    public static void main (String[] args){
        STDLServerController sC = new STDLServerController();
    }
    
}