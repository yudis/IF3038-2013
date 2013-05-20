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

    private long lastUpdate;
    private Boolean running;

    Client() {
        this.running = true;
    }

    public static void main(String[] args) {

        Client c = new Client();
        String command;
        String request;
        String respond;

        try {
            BufferedReader inClient = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket("localhost", 1234);
            DataOutputStream outServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (c.running) {
                System.out.print(">> ");
                command = inClient.readLine();
                command = command.toLowerCase();
                switch (command) {
                    case ("login"): {
                        System.out.print("Enter username: ");
                        String username = inClient.readLine();
                        System.out.print("Enter password: ");
                        String password = inClient.readLine();

                        request = ("1," + username + "," + password);
                        outServer.writeBytes(request + '\n');
                        respond = inServer.readLine();

                        String[] responds = respond.split(",");
                        if (responds[0].equals("200")) {
                            //Synchronize...
                            try {
                                Scanner scanner = new Scanner(new FileReader("log.txt"));
                                String logs = "";
                                long last=0;
                                while (scanner.hasNextLine()) {
                                    String now = scanner.nextLine();
                                    String[] logparts = now.split(",");
                                    
                                    if (Long.parseLong(logparts[2])> c.lastUpdate){
                                        if (Long.parseLong(logparts[2])>last){
                                            last = Long.parseLong(logparts[2]);
                                        }
                                        logs+= ","+now;
                                    }
                                }
                                
                                c.lastUpdate = Long.parseLong(responds[1]);
                                System.out.println("Login successful, this client lastUpdate is " + c.lastUpdate);

                                request = ("4,"+ username+","+last+","+c.lastUpdate + logs);
                                System.out.println(request);
                                outServer.writeBytes(request + '\n');
                             
                            } catch (IOException e) {
                                //JOptionPane.showMessageDialog(null, "File tidah ditemukan", "Report", 0);
                            }
                            

                            //menerima string berisi task_id dan status yang terupdate
                            respond = inServer.readLine();
                            responds = respond.split(",");
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
                            }
                            System.out.println("Creating 'keep update' thread");
                            Runnable task = new MyRunnable();
                            Thread threadUpdate = new Thread(task);
                            threadUpdate.start();
                        } else if (responds[0].equals("400")) {
                            System.out.println("Login failed");
                        } else {
                            System.out.println("Unrecognized respond");
                        }
                        break;
                    }
                    case ("check"): {
                        System.out.print("Enter task_id: ");
                        String task_id = inClient.readLine();
                        request = ("2," + task_id);

                        outServer.writeBytes(request + '\n');
                        respond = inServer.readLine();
                        String[] responds = respond.split(",");
                        if (responds[0].equals("200")) {
                            c.lastUpdate = Long.parseLong(responds[1]);
                            System.out.println("Check successful, lastUpdate " + c.lastUpdate);
                        } else if (responds[0].equals("400")) {
                            System.out.println("Check failed");
                        } else {
                            System.out.println("Unrecognized respond");
                        }
                        break;
                    }
                    case ("uncheck"): {
                        System.out.print("Enter task_id: ");
                        String task_id = inClient.readLine();
                        request = ("3," + task_id);

                        outServer.writeBytes(request + '\n');
                        respond = inServer.readLine();
                        String[] responds = respond.split(",");
                        if (responds[0].equals("200")) {
                            c.lastUpdate = Long.parseLong(responds[1]);
                            System.out.println("Uncheck successful, lastUpdate " + c.lastUpdate);
                        } else if (responds[0].equals("400")) {
                            System.out.println("Uncheck failed");
                        } else {
                            System.out.println("Unrecognized respond");
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }

                //System.out.println("From Server: " + message);
            }
            clientSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
