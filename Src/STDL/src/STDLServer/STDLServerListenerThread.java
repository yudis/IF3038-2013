/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package STDLServer;

import DBHelper.MyDatabase;
import Protocol.ArsMsgType;
import Protocol.ArsProtocol;
import com.mysql.jdbc.util.ServerController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arief
 */
public class STDLServerListenerThread extends Thread {

    String CurrentUser = null;
    private STDLServerListener serverListener = null;
    private Socket socket = null;
    private PrintWriter out;
    private BufferedReader in;
    boolean listening = true;

    public STDLServerListenerThread(Socket sock, STDLServerListener sL) {
        serverListener = sL;
        socket = sock;
    }

    @Override
    public void run() {
        try {

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            char[] inputLine = null;
            String readLine = null;
            String outProt = null, inProt = null;

            ArsProtocol ap = new ArsProtocol();

            do {
                readLine = in.readLine();
                if (readLine != null) {
                    //inputLine = readLine.toCharArray();
                    inputLine = serverListener.getServerController().eGG.DecryptString(readLine).toCharArray();
                    System.out.println("rD "+readLine);
                    System.out.println("inp "+inputLine);
                } else {
                    listening = false;
                    inProt = ap.processMsg(ArsMsgType.MsgLogout());
                }

                //System.out.print("SListenerThread Received : ");
                //System.out.println(inputLine);

                inProt = ap.processMsg(inputLine);
                //System.out.println("SListener Protocol Response : " + inProt);

                if (ArsMsgType.getCode(inProt.toCharArray()) == ArsMsgType.LOGIN_CODE) {
                    String[] splitted = inProt.split(ArsMsgType.Separator);
                    String uname = splitted[1];
                    String pass = splitted[2];

                    boolean authenticated = false;

                    String Query = "Select * from user where username=\"" + uname + "\"";
                    ResultSet RS = MyDatabase.getSingleton().selectDB(Query);
                    try {
                        while (RS.next()) {
                            if (RS.getString("password").equals(pass)) {
                                authenticated = true;
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(STDLServerListenerThread.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (authenticated && serverListener.getServerController().userConnect(uname)) {
                        outProt = ap.processMsg(ArsMsgType.MsgSuccess());
                        CurrentUser = uname;
                    } else {
                        outProt = ap.processMsg(ArsMsgType.MsgFailed());
                    }
                } else if (ArsMsgType.getCode(inProt.toCharArray()) == ArsMsgType.LIST_CODE) {

                    String query = "select * from task join tasktoasignee where task.namaTask=tasktoasignee.namaTask and asigneeName=\"" + CurrentUser + "\"";
                    outProt = ap.processMsg(ArsMsgType.ListAnswer(MyDatabase.getSingleton().selectDB(query)));

                } else if (ArsMsgType.getCode(inProt.toCharArray()) == ArsMsgType.DETAIL_CODE) {

                    String namaTask = inProt.split("#")[1];
                    String query = "select * from task where namaTask=\"" + namaTask + "\"";
                    outProt = ap.processMsg(ArsMsgType.DetailAnswer(MyDatabase.getSingleton().selectDB(query)));

                } else if (ArsMsgType.getCode(inProt.toCharArray()) == ArsMsgType.CHANGESTATUS_CODE) {

                    String[] splitted = inProt.split("#");
                    String namaTask = splitted[1];
                    String status = splitted[2];
                    Timestamp tS = Timestamp.valueOf(splitted[3]);

                    String query = "select * from task where namaTask=\"" + namaTask + "\"";
                    ResultSet RS = MyDatabase.getSingleton().selectDB(query);

                    boolean isCanChange = false;
                    try {
                        while (RS.next()) {
                            if (RS.getTimestamp("timestamp").before(tS)) {
                                isCanChange = true;
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(STDLServerListenerThread.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (isCanChange) {
                        String pQuery = "UPDATE task SET status = '" + status + "',timestamp = '" + tS.toString().substring(0, tS.toString().length() - 4) + "' WHERE namaTask = '" + namaTask + "'";
                        //System.out.println(pQuery);
                        int result = MyDatabase.getSingleton().queryDB(pQuery);
                        if (result > 0) {
                            outProt = new String(ArsMsgType.MsgSuccess());
                            String log = "User : "+CurrentUser+" updated on task : "+namaTask+" status : "+status;
                            serverListener.getServerController().logging(log);
                        } else {
                            outProt = new String(ArsMsgType.MsgFailed());
                        }
                        
                    } else {
                        outProt = new String(ArsMsgType.MsgFailed());
                    }

                } else if (ArsMsgType.getCode(inProt.toCharArray()) == ArsMsgType.LOGOUT_CODE) {

                    outProt = new String(ArsMsgType.MsgSuccess());
                    serverListener.getServerController().userDisconnect(CurrentUser);

                } else {
                    outProt = new String(ArsMsgType.MsgFailed());
                }
                out.println(outProt);

            } while (listening);
            disconnect();
            serverListener.getServerController().userDisconnect(CurrentUser);

        } catch (IOException ex) {
            disconnect();
            serverListener.getServerController().userDisconnect(CurrentUser);
            Logger.getLogger(STDLServerListenerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        listening = false;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(STDLServerListenerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
