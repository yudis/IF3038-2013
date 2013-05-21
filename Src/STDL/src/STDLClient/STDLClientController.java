/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package STDLClient;

import ElGamal.ElGamalGenerator;
import FileHelper.FileManager;
import Protocol.ArsMsgType;
import java.sql.Timestamp;

/**
 *
 * @author Arief
 */
public class STDLClientController {
    
    private STDLClientSender clientSender;
    private STDLClientGUI clientGUI;
    private boolean isConnected = false;
    private String currentUser = "";
    public ElGamalGenerator eGG = new ElGamalGenerator();
    public STDLClientController(){
        clientSender = new STDLClientSender(this);
        clientGUI = new STDLClientGUI(this);
        clientGUI.setVisible(true);
    }
    
    public void testRun(){
        clientSender.createConnection("localhost", 8088);
        boolean tmp = clientSender.login("arief", "arief12345");
        if(tmp == true){
            System.out.println("login success");
        }
        else {
            System.out.println("login failed");
        }
        System.out.println(clientSender.list());
        System.out.println(clientSender.detail("task 1"));
        System.out.println(clientSender.changeStatus("task 1", "undone", new Timestamp(new java.util.Date().getTime())));
        clientSender.disconnect();
    }
    
    public void connect(String serverAddress){
        boolean feedback = clientSender.createConnection(serverAddress, 8088);
        if(feedback) {
            clientGUI.setWarningLabel("Connection Succeed");
            clientGUI.setConnection(true);
            isConnected = true;
        } else {
            clientGUI.setWarningLabel("Connection Failed");
            clientGUI.setConnection(false);
            isConnected = false;
        }
    }
    
    public void disconnect(){
        clientSender.disconnect();
        isConnected = false;
        clientGUI.setWarningLabel("Disconnected From Server");
        clientGUI.setConnection(false);
        clientGUI.setQueue(false);
    }
    
    public void login(String username, String password){
        if (isConnected) {
            boolean feedback = clientSender.login(username, password);
            if (feedback) {
                clientGUI.setLogin(feedback);
                clientGUI.setWarningLabel("Log In Succeed");
                currentUser = username;
                //saving buat cache untuk offline mode
                FileManager.saveDocNew(username+".lid", username+"\r\n"+password);
                //listing
                list();
            } else {
                clientGUI.setLogin(feedback);
                clientGUI.setWarningLabel("Log In Failed");
            }
            String syncdata = FileManager.openDoc(currentUser + ".sqd");
            if (syncdata != null && !syncdata.equals("")) {
                clientGUI.setQueue(true);
            } else {
                clientGUI.setQueue(false);
            }
        } else {
            String userdata = FileManager.openDoc(username+".lid");
            if(!userdata.equals("") && userdata!=null){
                String[] splitted = userdata.split("\r\n");
                if(splitted[1].equals(password)){
                    clientGUI.setLogin(true);
                    currentUser = username;
                    clientGUI.setWarningLabel("Log In Succeed (offline)");
                    list();
                } else {
                    clientGUI.setLogin(false);
                    clientGUI.setWarningLabel("Log In Failed");
                }
            } else {
                clientGUI.setLogin(false);
                clientGUI.setWarningLabel("Log In Failed");
            }
        }
        
    }
    
    public void logout(){
        if(isConnected)
            clientSender.logout();
        clientGUI.setLogin(false);
        clientGUI.setWarningLabel("Log Out Succeed");
        clientGUI.setDetail("");
        clientGUI.setTaskList(new String[] {});
        clientGUI.setQueue(false);
    }
    
    public void list(){
        if(isConnected) {
            String feedback = clientSender.list();
            if (ArsMsgType.getCode(feedback.toCharArray()) != ArsMsgType.FAILED_CODE) {
                if (feedback.length() > 15) {
                    feedback = feedback.substring(feedback.indexOf("#") + 1);
                    String[] splitted = feedback.split("#");
                    clientGUI.setTaskList(splitted);
                    //caching untuk offline mode
                    String filecache = "";
                    for (int i = 0; i < splitted.length; i++) {
                        filecache = filecache+clientSender.detail(splitted[i])+"\r\n";
                    }
                    FileManager.saveDocNew(currentUser+".tld", filecache);
                }
            }
        } else {
            String userdata = FileManager.openDoc(currentUser+".tld");
            if(!userdata.equals("") && userdata!=null){
                String[] splitted = userdata.split("\r\n");
                String[] listing = new String[splitted.length];
                for (int i = 0; i < listing.length; i++) {
                    listing[i] = splitted[i].split("#")[1];
                }
                clientGUI.setTaskList(listing);
            }
        }
    }
    
    public void detail(String namaTask){
        if (isConnected) {
            String feedback = clientSender.detail(namaTask);
            if (ArsMsgType.getCode(feedback.toCharArray()) != ArsMsgType.FAILED_CODE) {
                clientGUI.setDetail(feedback);
            }
        } else {
            String userdata = FileManager.openDoc(currentUser+".tld");
            if(!userdata.equals("") && userdata!=null){
                String[] splitted = userdata.split("\r\n");
                for (int i = 0; i < splitted.length; i++) {
                    if(splitted[i].split("#")[1].equals(namaTask)){
                        clientGUI.setDetail(splitted[i]);
                        
                    }
                }
            }
        }
        String syncdata = FileManager.openDoc(currentUser + ".sqd");
        if (syncdata != null && !syncdata.equals("")) {
            boolean found = false;
            String[] splitted2 = syncdata.split("\r\n");
            for (int i = 0; i < splitted2.length; i++) {
                if (splitted2[i].split("#")[0].equals(namaTask)) {
                    clientGUI.setStatusLabel(splitted2[i].split("#")[1]);
                    found = true;
                }
            }
            if (found) {
                clientGUI.setSycedLabel("(not Yet Synchronized)");
            } else {
                clientGUI.setSycedLabel("");
            }
        }
    }
    
    public void changeStatus(String namaTask, String status){
        if (isConnected) {
            boolean feedback = clientSender.changeStatus(namaTask, status, new Timestamp(new java.util.Date().getTime()));
            if (feedback) {
                clientGUI.setWarningLabel("Change Status Succeed");
                clientGUI.setDetail(clientSender.detail(namaTask));
            } else {
                clientGUI.setWarningLabel("Change Status Failed");
            }
            list();
        } else {
            String syncQueue = namaTask+"#"+status+"#"+new Timestamp(new java.util.Date().getTime()).toString()+"\r\n";
            FileManager.saveDocAppend(currentUser+".sqd", syncQueue);
            detail(namaTask);
        }
    }
    
    public void sync(){
        String syncdata = FileManager.openDoc(currentUser + ".sqd");
        if (syncdata != null && !syncdata.equals("")) {
            String[] splitted = syncdata.split("\r\n");
            for (int i = 0; i < splitted.length; i++) {
                String[] splitted2 = splitted[i].split("#");
                String namaTask = splitted2[0];
                String status = splitted2[1];
                Timestamp tS = Timestamp.valueOf(splitted2[2]);
                boolean feedback = clientSender.changeStatus(namaTask, status, tS);
                if(feedback){
                    String log = "TaskName : "+namaTask+", status : "+status;
                    clientGUI.appendLog("sync succeed on "+log);
                }
                else{
                    String log = "TaskName : "+namaTask;
                    clientGUI.appendLog("sync failed on "+log+" timeStamp not Latest");
                }
            }
        }
        FileManager.saveDocNew(currentUser+".sqd", "");
        clientGUI.setQueue(false);
    }
    
    public static void main (String[] args) {
        STDLClientController scc = new STDLClientController();
    }
    
}
