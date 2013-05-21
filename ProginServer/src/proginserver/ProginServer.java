/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proginserver;

import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sthobis
 */
public class ProginServer extends Thread{

    /**
     * @param args the command line arguments
     */
    private static int port=10000;
    private static Connection connection;
    private static String query = null;
    private static Statement statement;
   
    public static void init(){
        String url = "jdbc:mysql://localhost:3306/progin_405_13510029";
        String username = "progin";
        String password = "progin";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username , password);
        }
        catch (Exception e) {

            e.printStackTrace();
        }

    }
    
    public static void listTask(String username, String filename) 
    {
        try(PrintWriter writer = new PrintWriter(filename,"UTF-8");) {
            try {
                query="SELECT task_id from task_incharge where people_incharge_task = '"+username+"'";
                statement = connection.createStatement();
                ResultSet rs1 = statement.executeQuery(query);
                while (rs1.next()) {
                    query="SELECT task_id,task_name,deadline,status,task_category,timestamp FROM task WHERE task_id = '" + rs1.getString("task_id") + "'";
                    statement = connection.createStatement();
                    ResultSet rs2 = statement.executeQuery(query);
                    if (rs2.next()) {
                        query="SELECT people_incharge_task FROM task_incharge WHERE task_id = '" + rs1.getString("task_id") + "'";
                        statement = connection.createStatement();
                        ResultSet rs3 = statement.executeQuery(query);
                        String assignee = "";
                        while (rs3.next()) {
                            if (assignee == "")
                                assignee = rs3.getString("people_incharge_task");
                            else
                                assignee = assignee + ","+ rs3.getString("people_incharge_task");
                        }
                        query="SELECT tag_id FROM tasktag WHERE task_id = '" + rs1.getString("task_id") + "'";
                        statement = connection.createStatement();
                        ResultSet rs4 = statement.executeQuery(query);
                        String tag = "";
                        while (rs4.next()) {
                            query="SELECT tag_name FROM tag WHERE tag_id = '" + rs4.getString("tag_id") + "'";
                            statement = connection.createStatement();
                            ResultSet rs5 = statement.executeQuery(query);
                            if (rs5.next()){
                                if (tag == "")
                                    tag = rs5.getString("tag_name");
                                else
                                    tag = tag+","+rs5.getString("tag_name");
                            }
                        }
                        query="SELECT category_name FROM category WHERE category_id = '" + rs2.getString("task_category") + "'";
                        statement = connection.createStatement();
                        ResultSet rs6 = statement.executeQuery(query);
                        rs6.next();
                            writer.println(rs2.getString("task_id")+"_"+rs2.getString("task_name")+"_"+rs2.getString("deadline")
                                        +"_"+rs2.getString("status")+"_"+tag+"_"+rs6.getString("category_name")+"_"
                                        +rs2.getString("timestamp")+"_"+assignee);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProginServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.close();
        } catch(IOException ex) {

        } finally {

        }
    }
    
    public static void main(String [] args)
    {
//        init();
//        listTask("Thobi","testfile.txt");
        try {
            ServerSocket listener = new ServerSocket(port);
            Socket server;
            while (true) {
            server = listener.accept();
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            doComms conn_c= new doComms(server);
            Thread t = new Thread(conn_c);
            t.start();
            }
        } catch (IOException ioe) {
            System.out.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
        }
    }
}

class doComms implements Runnable {
    private Socket server;
    private static Connection connection;
    private static String query = null;
    private static Statement statement;

    doComms(Socket server) {
      this.server=server;
      init();
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try {
                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                String inputan = in.readUTF();
                if (inputan.compareTo("ping") != 0)
                    System.out.println(inputan);
                String hasil = parseCommand(inputan);
                if (hasil.startsWith("login")){
                    if (hasil.equalsIgnoreCase("login_success"))
                        out.writeUTF("login_success");
                    else
                        out.writeUTF("login_fail");
                   System.out.println("checkpoint");
                }
                else if (hasil.compareTo("list_success") == 0) {
                   out.writeUTF("file");
                   File myFile = new File("filename.txt");

                   int count;
                   byte[] buffer = new byte[1024];

                   BufferedInputStream ins = new BufferedInputStream(new FileInputStream(myFile));
                   while ((count = ins.read(buffer)) > 0) {
                        out.write(buffer, 0, count);
                        out.flush();
                   }
                   System.out.println("list selesai");
                } else if (hasil.compareTo("push_success_clientid_taskid") == 0) {
                    out.writeUTF("push_success_clientid_taskid");
                    }
            }catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                break;
            }catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
    
    public String parseCommand(String input) 
    {
        if (!input.isEmpty()) {
            if (input.startsWith("login")){
                try {
                    String[] splitInput = input.split("_");
                    byte[] userbyte;
                    byte[] passbyte;
                    userbyte = splitInput[1].getBytes();
                    for (int i = 0; i<splitInput[1].length();i++) {
                        userbyte[i] = (byte) (userbyte[i]-i);
                    }
                    passbyte = splitInput[2].getBytes();
                    for (int i = 0; i<splitInput[2].length();i++) {
                        passbyte[i] = (byte) (passbyte[i]-i);
                    }
                    String username = new String(userbyte);
                    String password = new String(passbyte);
                    System.out.println((splitInput[1].substring(0, splitInput[1].length()-1))+"_"+(splitInput[2].substring(0, splitInput[2].length()-1)));
                    query="SELECT username FROM user WHERE username = '"+username+"' AND password = '"+password+"'";
                    statement = connection.createStatement();
                    ResultSet rs1 = statement.executeQuery(query);
                    if (rs1.next()) {
                        System.out.println("success_login");
                        return ("login_success");
                    }
                    else {
                        System.out.println("fail_login");
                        return ("login_fail");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ProginServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (input.startsWith("list")) {
                String[] splitInput = input.split("_");
                System.out.println("Making task file for username : " + splitInput[1]);
                listTask(splitInput[1],"filename.txt");
                System.out.println("List task success for username : " + splitInput[1]);
                System.out.println("1 File terbuat");
                return ("list_success");
            } else if (input.startsWith("pull")) {
                String[] splitInput = input.split("_");
                return pull(splitInput[2], splitInput[3]);
            } else if (input.startsWith("push")) {
                String[] splitInput = input.split("_");
                push(splitInput[2],splitInput[3],splitInput[4]);
                return ("push_success_clientid_taskid");
            }
        }
        return ("Bad Operation Total.");
    }
    
    
    public static void init(){
        String url = "jdbc:mysql://localhost:3306/progin_405_13510029";
        String username = "progin";
        String password = "progin";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username , password);
        }
        catch (Exception e) {

            e.printStackTrace();
        }

    }
    
    public void listTask(String username, String filename) 
    {
        try(PrintWriter writer = new PrintWriter(filename,"UTF-8");) {
            try {
                query="SELECT task_id from task_incharge where people_incharge_task = '"+username+"'";
                statement = connection.createStatement();
                ResultSet rs1 = statement.executeQuery(query);
                while (rs1.next()) {
                    query="SELECT task_id,task_name,deadline,status,task_category,timestamp FROM task WHERE task_id = '" + rs1.getString("task_id") + "'";
                    statement = connection.createStatement();
                    ResultSet rs2 = statement.executeQuery(query);
                    if (rs2.next()) {
                        query="SELECT people_incharge_task FROM task_incharge WHERE task_id = '" + rs1.getString("task_id") + "'";
                        statement = connection.createStatement();
                        ResultSet rs3 = statement.executeQuery(query);
                        String assignee = "";
                        while (rs3.next()) {
                            if (assignee == "")
                                assignee = rs3.getString("people_incharge_task");
                            else
                                assignee = assignee + ","+ rs3.getString("people_incharge_task");
                        }
                        query="SELECT tag_id FROM tasktag WHERE task_id = '" + rs1.getString("task_id") + "'";
                        statement = connection.createStatement();
                        ResultSet rs4 = statement.executeQuery(query);
                        String tag = "";
                        while (rs4.next()) {
                            query="SELECT tag_name FROM tag WHERE tag_id = '" + rs4.getString("tag_id") + "'";
                            statement = connection.createStatement();
                            ResultSet rs5 = statement.executeQuery(query);
                            if (rs5.next()){
                                if (tag == "")
                                    tag = rs5.getString("tag_name");
                                else
                                    tag = tag+","+rs5.getString("tag_name");
                            }
                        }
                        query="SELECT category_name FROM category WHERE category_id = '" + rs2.getString("task_category") + "'";
                        statement = connection.createStatement();
                        ResultSet rs6 = statement.executeQuery(query);
                        rs6.next();
                            writer.println(rs2.getString("task_id")+"_"+rs2.getString("task_name")+"_"+rs2.getString("deadline")
                                        +"_"+rs2.getString("status")+"_"+tag+"_"+rs6.getString("category_name")+"_"
                                        +rs2.getString("timestamp")+"_"+assignee);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProginServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.close();
        } catch(IOException ex) {

        } finally {

        }
    }
    
    private static void push(String taskid, String status, String timestamp) {
        try {
            System.out.println("Updating task"+taskid+" with status "+status+" timestamp "+timestamp);
            query = "UPDATE task SET status=" + status + " WHERE task_id = '" + taskid + "'";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            query = "UPDATE task SET timestamp='" + timestamp + "' WHERE task_id = '" + taskid + "'";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Updating successfull");
        } catch (SQLException ex) {
            Logger.getLogger(ProginServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String pull(String taskid, String timestamp) {
        try {       
            query = "SELECT timestamp FROM task WHERE task_id = '" + taskid + "'";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                Date servertime = new Date(Integer.parseInt(rs.getString("timestamp").substring(0,4)), Integer.parseInt(rs.getString("timestamp").substring(5,7)), Integer.parseInt(rs.getString("timestamp").substring(8,10)));
                Date clienttime = new Date(Integer.parseInt(timestamp.substring(0,4)), Integer.parseInt(timestamp.substring(5,7)), Integer.parseInt(timestamp.substring(8,10)));
                if (servertime.after(clienttime)) {
                    query = "SELECT task_name,status FROM task WHERE task_id = '" + taskid + "'";
                    statement = connection.createStatement();
                    ResultSet rs2 = statement.executeQuery(query);
                    if (rs2.next()) {
                        return (taskid+"_"+rs2.getString("task_name")+"_"+rs2.getString("status")+"_"+rs.getString("timestamp"));
                    }
                } else if (servertime.before(clienttime)) {
                    return ("noupdate");
                } else { //bandingkan jam
                    String temp = rs.getString("timestamp");
                    if (Integer.parseInt(temp.substring(11,13)) > Integer.parseInt(timestamp.substring(11, 13))) {
                        query = "SELECT task_name,status FROM task WHERE task_id = '" + taskid + "'";
                        statement = connection.createStatement();
                        ResultSet rs2 = statement.executeQuery(query);
                        if (rs2.next()) {
                            return (taskid+"_"+rs2.getString("task_name")+"_"+rs2.getString("status")+"_"+rs.getString("timestamp"));
                        }
                    } else if (Integer.parseInt(temp.substring(11,13)) > Integer.parseInt(timestamp.substring(11, 13))) {
                        return ("noupdate");
                    } else { //bandingkan menit
                        if (Integer.parseInt(temp.substring(14,16)) > Integer.parseInt(timestamp.substring(14, 16))) {
                            query = "SELECT task_name,status FROM task WHERE task_id = '" + taskid + "'";
                            statement = connection.createStatement();
                            ResultSet rs2 = statement.executeQuery(query);
                            if (rs2.next()) {
                                return (taskid+"_"+rs2.getString("task_name")+"_"+rs2.getString("status")+"_"+rs.getString("timestamp"));
                            }
                        } else if (Integer.parseInt(temp.substring(14,16)) > Integer.parseInt(timestamp.substring(14, 16))) {
                            return ("noupdate");
                        } else { //bandingkan detik
                            if (Integer.parseInt(temp.substring(17,19)) > Integer.parseInt(timestamp.substring(17, 19))) {
                                query = "SELECT task_name,status FROM task WHERE task_id = '" + taskid + "'";
                                statement = connection.createStatement();
                                ResultSet rs2 = statement.executeQuery(query);
                                if (rs2.next()) {
                                    return (taskid+"_"+rs2.getString("task_name")+"_"+rs2.getString("status")+"_"+rs.getString("timestamp"));
                                }
                            } else {
                                return ("noupdate");
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProginServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Bad Operation";
    }
}
