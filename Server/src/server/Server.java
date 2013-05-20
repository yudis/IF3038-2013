/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Timotius
 */
public class Server implements Runnable{
    
    protected static int port = 1234;
    private static long lastUpdate;
    Socket connection;
    private int ID;
    private static int count = 0;
    private String request;
    private String respond;
    private String capitalizedMessage;
    private Database db;
    
    Server(Socket socket, int id, Database db) {
        this.connection = socket;
        this.ID = id;
        this.db = db;
        this.request = "";
        this.respond = "";
        this.lastUpdate = System.currentTimeMillis();
    }
        
    public static void main(String[] args) {
        
        //Starts server ...
        Database database = new Database();
        try{
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Server ready...");
            while (true) {
                Socket conn = socket.accept();
                Runnable runnable = new Server(conn, ++count, database);
                System.out.println("New connection with ID: "+count);
                Thread thread = new Thread(runnable);
                thread.start();
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void run() {
        
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            while (true){
                request = in.readLine();
                System.out.println("From Client: " + request);
                String[] parts = request.split(",");
                String part1 = parts[0];
                switch (part1){
                    case ("1"): //login
                    {   
                        String username = parts[1]; //kennyazrina
                        String password = parts[2]; //kennyazrina
                        String respond1 = db.Login(username, password); //200 atau 400
                        out.writeBytes(respond1 + '\n');
                        String[] responds1 = respond1.split(",");
                        if (responds1[0].equals("200")){
                            System.out.println("Login successful, creating 'keep update' thread");
                            /*Runnable task = new MyRunnable();
                            Thread threadUpdate = new Thread(task);
                            threadUpdate.start();*/
                        } else {
                            System.out.println("Login failed");
                        }
                        break;
                    }
                    case ("2"): //check
                    {
                        String task_id = parts[1];
                        respond = db.Check(task_id);
                        String[] responds = respond.split(",");
                        if (responds[0].equals("200")){
                            lastUpdate = Long.parseLong(responds[1].substring(0,responds[1].length()-1));
                            System.out.println("Check successful");
                        } else {
                            System.out.println("Check failed");
                        }
                        out.writeBytes(respond);
                        break;
                    }
                    case ("3"): //uncheck
                    {
                        String task_id = parts[1];
                        respond = db.Uncheck(task_id);
                        String[] responds = respond.split(",");
                        if (responds[0].equals("200")){
                            lastUpdate = Long.parseLong(responds[1].substring(0,responds[1].length()-1));
                            System.out.println("Check successful");
                        } else {
                            System.out.println("Check failed");
                        }
                        out.writeBytes(respond);
                        break;
                    }
                    case ("4"): //synchronize dari log (tugas-X yang timestamp nya dari log lebih besar dari timestamp tugas-X di database akan overwrite)
                    {
                        //Terima 4,kennyazrina,<last>,<user.last_update>,<deretan line yang punya timestamp > user.last_update>
                        String clientUsername = parts[1];
                        Long clientLastUpdate = Long.parseLong(parts[2]);
                        //sisanya logs terbagi jadi parts[4] untuk task_id, parts[5] task_status, parts[6] timestamp, dst
                        int i = 3;
                        while (i < parts.length){
                            String respond1 = db.Synchronize(parts[i],parts[i+1],Long.parseLong(parts[i+2]));
                            if (respond1.equals("200")){
                                System.out.println("task_id "+parts[i]+" in log is newer and overwrites old data");
                            } else {
                                System.out.println("task_id "+parts[i]+" in log is older than the one in the database");
                            }
                            //String respond2 = db.UpdateLastUpdate(clientUsername, Long.parseLong(parts[i+2]));
                            i += 3;
                        }
                        //next step: ambil timestamp terbesar dari semua tugas itu, jadikan user.last_update (sikronisasi selesai)
                        long max_timestamp = db.GetMaxTimestamp(clientUsername);
                        System.out.println("max_timestamp: "+max_timestamp);
                        String respond2 = db.UpdateLastUpdate(clientUsername, max_timestamp);
                        System.out.println("respond2: "+respond2);
                        out.writeBytes(respond2 + "," + max_timestamp + '\n');
                        String respond3 = db.GetUserTasks(clientUsername);
                        System.out.println("respond3: "+respond3);
                        out.writeBytes(respond3 + '\n');
//                        out.writeBytes(respond);    //bila 200 berhasil, bila 400 tak ada update
//                        String[] responds = respond.split(",");
//                        if (responds[0].equals("200")){
//                            System.out.println("Synchronizing...");
//                            String request2, respond2;
//                            respond2 = "400\n";
//                            request2 = in.readLine();
//                            clientLastUpdate = Long.parseLong(request2);
//                            respond2 = db.UpdateLastUpdate(clientUsername, clientLastUpdate);
//                            out.writeBytes(respond2);
//                        } else if (responds[0].equals("400")){
//                            System.out.println("No update...");
//                        } else {
//                            
//                        }
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
                Thread.sleep(1000);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
