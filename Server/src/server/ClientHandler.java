/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.awt.image.SampleModel;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.TreeMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author ASUS
 */
public class ClientHandler implements Runnable{
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    boolean active;
    int id;
    Connection conn;
    
    public ClientHandler(Socket socket,int id) {
        this.socket = socket;
        active = true;
        this.id = id;
        initConnection();
    }
    
    private void initConnection(){
        try
        {
          Properties prop = new Properties();
          
          prop.load(this.getClass().getResourceAsStream("db.properties"));
          // create a java mysql database connection
          String driver = prop.getProperty("driver").toString();
          String host = prop.getProperty("host").toString();
          String dbname = prop.getProperty("dbname").toString();
          String username = prop.getProperty("username").toString();
          String password = prop.getProperty("password").toString();
          
          String myUrl = "jdbc:mysql://"+host+"/"+dbname;
          Class.forName(driver);
          conn = DriverManager.getConnection(myUrl, username, password);
        }
        catch (Exception e)
        {
          System.err.println("Got an exception! ");
          System.err.println(e.getMessage());
        }
    }
    
    public String GetCategoriName(String catId){
        try {
            String query = "SELECT categoryname FROM category WHERE categoryid= ?";
            PreparedStatement prest = conn.prepareStatement(query);
            prest.setString(1, catId);
            ResultSet rs = prest.executeQuery();
            rs.next();
            return rs.getString("categoryname").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }
    
    public String GetTagName(String tagId){
        try {
            String query = "SELECT tagname FROM tag WHERE tagid= ?";
            PreparedStatement prest = conn.prepareStatement(query);
            prest.setString(1, tagId);
            ResultSet rs = prest.executeQuery();
            rs.next();
            return rs.getString("tagname").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }
    
    public String GetListAssigneeName(String TaskId){
        try {
            String query = "SELECT username FROM assignee WHERE taskid= ?";
            PreparedStatement prest = conn.prepareStatement(query);
            prest.setString(1, TaskId);
            ResultSet rs = prest.executeQuery();
            ArrayList<String> list = new ArrayList<String>();
            while(rs.next()){
                list.add(rs.getString("username"));
            }
            return JSONValue.toJSONString(list);
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }
    
    public String GetListTag(String TaskId){
        try {
            String query = "SELECT tagid FROM task_tag WHERE taskid= ?";
            PreparedStatement prest = conn.prepareStatement(query);
            prest.setString(1, TaskId);
            ResultSet rs = prest.executeQuery();
            ArrayList<String> list = new ArrayList<String>();
            while(rs.next()){
                list.add(GetTagName(rs.getString("tagid")));
            }
            return JSONValue.toJSONString(list);
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }
    
    @Override
    public void run() {
        try{
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        }catch(Exception ex){
            ex.printStackTrace();
            active = false;
        }
        
        try {
            TreeMap<String,Object> msg = new TreeMap<String, Object>();
            msg.put("code", 10);
            msg.put("id", id);
            out.writeObject(JSONValue.toJSONString(msg));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            active = false;
        }
        while(active){
            try {
                Object msg = in.readObject();
                JSONObject obj= (JSONObject) JSONValue.parse(msg.toString());
                if(obj.get("code").toString().equals("1")){
                    String username = obj.get("username").toString();
                    String password = obj.get("password").toString();
                    
                    String query = "SELECT count(*) AS exist FROM user WHERE username= ? AND password = ?";
                    PreparedStatement prest = conn.prepareStatement(query);
                    prest.setString(1, username);
                    prest.setString(2, password);
                    ResultSet rs = prest.executeQuery();
                    rs.next();
                    if(rs.getString("exist").toString().equals("0")){
                        System.out.println("Login Gagal : "+username);
                        TreeMap<String,Object> msgOut = new TreeMap<String, Object>();
                        msgOut.put("code", 128);
                        out.writeObject(JSONValue.toJSONString(msg));
                        out.flush();
                    }else{
                        System.out.println("Login Sukses : "+username);
                        TreeMap<String, Object> msgOut = GetListOfTask(username,127);
                        out.writeObject(JSONValue.toJSONString(msgOut));
                        out.flush();
                    }
                }else if(obj.get("code").toString().equals("2")){
                    String taskIdWantToChange = obj.get("taskid").toString();
                    String dateUpdate = obj.get("dtmlastupdate").toString();
                    String query = "SELECT status FROM task Where taskid = ?";
                    PreparedStatement prest = conn.prepareStatement(query);
                    prest.setString(1, taskIdWantToChange);
                    ResultSet rs = prest.executeQuery();
                    rs.next();
                    if(rs.getString("status").toString().equals("COMPLETE")){
                       query = "UPDATE task SET status = 'UNCOMPLETE',dtmlastupdate = ? WHERE taskid = ?";
                       prest = conn.prepareStatement(query);
                       prest.setString(1, dateUpdate);
                       prest.setString(2, taskIdWantToChange);
                       prest.execute();
                    }else{
                       query = "UPDATE task SET status = 'COMPLETE',dtmlastupdate = ? WHERE taskid = ?";
                       prest = conn.prepareStatement(query);
                       prest.setString(1, dateUpdate);
                       prest.setString(2, taskIdWantToChange);
                       prest.execute();
                    }
                    String usernameString = obj.get("username").toString();
                    TreeMap<String, Object> msgOut = GetListOfTask(usernameString,22);
                    out.writeObject(JSONValue.toJSONString(msgOut));
                    out.flush();
                }else if(obj.get("code").toString().equals("21")){
                    String usernameString = obj.get("username").toString();
                    TreeMap<String, Object> msgOut = GetListOfTask(usernameString,22);
                    out.writeObject(JSONValue.toJSONString(msgOut));
                    out.flush();
                }else if(obj.get("code").toString().equals("50")){
                    String jsonlistString = obj.get("message").toString();
                    JSONArray arr = (JSONArray) JSONValue.parse(jsonlistString);
                    for(Object jobj : arr){
                        try {
                            JSONObject jobj2 = (JSONObject) JSONValue.parse(jobj.toString());
                            Date dateOri = GetDate(jobj2.get("taskid").toString());
                            
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date dateKW = dateFormat.parse(jobj2.get("timeupdate").toString());
                            
                            if(dateOri.compareTo(dateKW) < 0 ){
                                String query = "UPDATE task SET status = ?,dtmlastupdate = ? WHERE taskid = ?";
                                PreparedStatement prest = conn.prepareStatement(query);
                                prest.setString(1, jobj2.get("statusfinal").toString());
                                prest.setString(2, jobj2.get("timeupdate").toString());
                                prest.setString(3, jobj2.get("taskid").toString());
                                prest.execute();
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                active = false;
            }
        }
    }

    private TreeMap<String, Object> GetListOfTask(String username,int code) throws SQLException {
        String query;
        PreparedStatement prest;
        ResultSet rs;
        TreeMap<String, Object> msgOut = new TreeMap<String, Object>();
        msgOut.put("code", code);
        ArrayList<String> list = new ArrayList<String>();
        query = "SELECT * From task Where username = ? OR taskid IN (select taskid from assignee where username= ?)";
        prest = conn.prepareStatement(query);
        prest.setString(1, username);
        prest.setString(2, username);
        rs = prest.executeQuery();
        while (rs.next()) {
            TreeMap<String, String> task = new TreeMap<String, String>();
            task.put("taskid", rs.getString("taskid"));
            task.put("taskname", rs.getString("taskname"));
            task.put("username", rs.getString("username"));
            task.put("createddate", rs.getString("createddate"));
            task.put("deadline", rs.getString("deadline"));
            task.put("status", rs.getString("status"));
            String categoriname = GetCategoriName(rs.getString("categoryid"));
            task.put("categoryname", categoriname);

            String listAssignee = GetListAssigneeName(rs.getString("taskid").toString());
            task.put("listassignee", listAssignee);

            String listTag = GetListTag(rs.getString("taskid").toString());
            task.put("listtag", listTag);

            list.add(JSONValue.toJSONString(task));
        }
        msgOut.put("message", JSONValue.toJSONString(list));
        return msgOut;
    }

    private Date GetDate(String taskid) {
        try {
            String query = "SELECT dtmlastupdate FROM task WHERE taskid= ?";
            PreparedStatement prest = conn.prepareStatement(query);
            prest.setString(1, taskid);
            ResultSet rs = prest.executeQuery();
            rs.next();
            return rs.getDate("dtmlastupdate");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
