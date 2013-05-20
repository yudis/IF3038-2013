/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.*;
import java.net.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Timotius
 */
public class Client {

    private String username;
    private long lastUpdate;
    private Boolean running;
    private Socket clientSocket;

    Client() {
        this.running = true;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    public Boolean getRunning() {
        return running;
    }
    
    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    public static void main(String[] args) {

        Client c = new Client();
        String command;
        //String request;
        //String respond;

        try {
            BufferedReader inClient = new BufferedReader(new InputStreamReader(System.in));
            c.clientSocket = new Socket("localhost", 1234);
            DataOutputStream outServer = new DataOutputStream(c.clientSocket.getOutputStream());
            BufferedReader inServer = new BufferedReader(new InputStreamReader(c.clientSocket.getInputStream()));
            while (c.running) {
                System.out.print(">> ");
                command = inClient.readLine();
                command = command.toLowerCase();
                switch (command) {
                    case ("login"): {
                        System.out.print("Enter username: ");
                        String username = inClient.readLine();
                        c.setUsername(username);
                        System.out.print("Enter password: ");
                        String password = inClient.readLine();
                        
                        String request1 = ("1," + username + "," + password);   //kirim: 1,kennyazrina,kennyazrina
                        outServer.writeBytes(request1 + '\n');
                        String respond1 = inServer.readLine();                  //terima: 200 atau 400 + user.last_update
                        System.out.println("respond1: "+respond1);
                        
                        String[] responds1 = respond1.split(",");
                        if (responds1[0].equals("200")) {
                            c.lastUpdate = Long.parseLong(responds1[1]);
                            int servercount = Integer.parseInt(responds1[2]);
                            System.out.println("Login successful, " + username + " username.last_update is " + c.lastUpdate);
                            //Baca dari log
                            try {
                                Scanner scanner = new Scanner(new FileReader("log.txt"));
                                String logs = "";
                                long last = 0;
                                while (scanner.hasNextLine()) {
                                    String now = scanner.nextLine();    //format now: task_id, task_status, timestamp
                                    String[] logparts = now.split(",");
                                    
                                    if (Long.parseLong(logparts[2]) > c.lastUpdate){
                                        if (Long.parseLong(logparts[2]) > last){
                                            last = Long.parseLong(logparts[2]);
                                        }
                                            logs = logs + now + ",";    //kumpulkan semua line yang punya timestamp > user.last_update
                                    }
                                }
                                //di sini last bernilai 0 jika tidak ada timestamp log yg lebih baru (>) dari user.last_update 
                                //Kirim 4,kennyazrina,<last>,<user.last_update>,<deretan line yang punya timestamp > user.last_update>
                                String request2 = ("4," + username + "," + c.lastUpdate + "," + logs);
                                request2 = request2.substring(0, request2.length()-1); //hilangkan koma terakhir dari logs
                                System.out.println("request2: "+request2);
                                outServer.writeBytes(request2 + '\n');
                            } catch (IOException e) {
                                System.out.println("Baca dari log: "+e.getMessage());
                                System.exit(1);
                            }
                            //menerima lastupdate terbaru merdasar max_timestamp
                            String respond2 = inServer.readLine();
                            System.out.println("respond2: "+respond2);
                            String[] responds2 = respond2.split(",");
                            if (responds2[0].equals("200")){
                                c.lastUpdate = Long.parseLong(responds2[1]);
                            } else {
                                c.lastUpdate = Long.parseLong(responds2[1]);    //mestinya hasilnya 0
                            }
                            System.out.println("current client's lastupdate: "+c.lastUpdate);
                            
                            //menerima semua task milik user
                            String respond3 = inServer.readLine();
                            System.out.println("respond3: "+respond3);
                            
                            String[] responds3 = respond3.split(",");
                            ArrayList<Log> logs = new ArrayList<>();
                            int a = 0;
                            while (a < responds3.length){
                                Log log = new Log();
                                log.setId_task(responds3[a]);
                                log.setTaskname(responds3[a+1]);
                                log.setDeadline(responds3[a+2]);
                                String[] responds3Parsed = responds3[a+3].split(":");
                                for (int n = 0; n < responds3Parsed.length; n++){
                                    (log.getAssignee()).add(responds3Parsed[n]);
                                }
                                log.setStatus(responds3[a+4]);
                                log.setCategory(responds3[a+5]);
                                logs.add(log);
                                a = a + 6;
                            }
                            for (int i = 0; i < logs.size(); i++){
                                System.out.println("Log "+(i+1)+":");
                                System.out.println(":: task_id: "+logs.get(i).getId_task());
                                System.out.println(":: task_name: "+logs.get(i).getTaskname());
                                System.out.println(":: deadline: "+logs.get(i).getDeadline());
                                System.out.print(":: assignees: ");
                                for (int j = 0; j < logs.get(i).getAssignee().size(); j++){
                                    System.out.print(logs.get(i).getAssignee().get(j)+",");
                                }
                                System.out.println("\n:: status: "+logs.get(i).getStatus());
                                System.out.println(":: category: "+logs.get(i).getCategory());
                            }
                            /*String[] responds2 = respond2.split(",");
                            if (responds[0].equals("200")) {
                                System.out.println("Synchronizing...");
                                String[] responds2;
                                for (int i = 1; i < responds.length; i++) {
                                    responds2 = responds[i].split(":");
                                    System.out.println("task_id: " + responds2[0] + ", current status: " + responds2[1] + ", updated");
                                }

                                String request2, respond2;
                                long now = System.currentTimeMillis();
                                request2 = "" + now;
                                outServer.writeBytes(request2 + '\n');

                                respond2 = inServer.readLine();
                                if (respond2.equals("200")) {
                                    System.out.println("Update last update successful");
                                } else if (respond2.equals("400")) {
                                    System.out.println("Update last update failed");
                                } else {
                                    System.out.println("Unrecognized respond");
                                }
                            } else if (responds[0].equals("400")) {
                                System.out.println("No update...");
                            } else {
                                System.out.println("unrecognized respond");
                            }*/
                            System.out.println("Creating 'keep update' thread");
                            Runnable task = new MyRunnable(c, servercount);
                            Thread threadUpdate = new Thread(task);
                            threadUpdate.start();
                        } else {
                            System.out.println("Login failed");
                        }
                        break;
                    }
                    case ("check"): {
                        System.out.print("Enter task_id: ");
                        String task_id = inClient.readLine();
                        String request1 = ("2," + task_id + "," + c.getUsername());
                        outServer.writeBytes(request1 + '\n');
                        String respond1 = inServer.readLine();
                        
                        String[] responds = respond1.split(",");
                        if (responds[0].equals("200")) {
                            c.lastUpdate = Long.parseLong(responds[1]);
                            //write to log
                            FileWriter fw = new FileWriter("log.txt",true); 
                            fw.write(task_id + ",1," + c.lastUpdate + "\n"); 
                            fw.close();
                            System.out.println("Check successful, lastUpdate " + c.lastUpdate);
                        } else {
                            System.out.println("Check failed");
                        }
                        break;
                    }
                    case ("uncheck"): {
                        System.out.print("Enter task_id: ");
                        String task_id = inClient.readLine();
                        String request1 = ("3," + task_id + "," + c.getUsername());
                        outServer.writeBytes(request1 + '\n');
                        String respond1 = inServer.readLine();
                        
                        String[] responds = respond1.split(",");
                        if (responds[0].equals("200")) {
                            c.lastUpdate = Long.parseLong(responds[1]);
                            //write to log
                            FileWriter fw = new FileWriter("log.txt",true); 
                            fw.write(task_id + ",0," + c.lastUpdate + "\n"); 
                            fw.close();
                            System.out.println("Uncheck successful, lastUpdate " + c.lastUpdate);
                        } else {
                            System.out.println("Uncheck failed");
                        }
                        break;
                    }
                    case ("logout"):{
                        c.running = false;
                        System.out.println("exited");
                    }
                    default: {
                        break;
                    }
                }

                //System.out.println("From Server: " + message);
            }
            c.clientSocket.close();
        } catch (Exception e) {
            System.out.println("ClientSocket: " + e.getMessage());
        }
    }
}
