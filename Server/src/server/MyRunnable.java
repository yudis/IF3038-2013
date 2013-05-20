/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import static server.Server.port;

/**
 *
 * @author Compaq
 */
public class MyRunnable implements Runnable {
    private Server server;
    private Database db;
    private boolean running;
    public MyRunnable(Server s, Database db){
        this.server = s;
        this.db = db;
        this.running = true;
    }
    public void run(){
        try{
            ServerSocket confirmSocket = new ServerSocket(5000 + server.getCount());
            Socket askSocket = confirmSocket.accept();
            DataOutputStream out = new DataOutputStream(askSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(askSocket.getInputStream()));
            while (running){
                String request1 = in.readLine();
                String[] requests1 = request1.split(",");
                if (requests1[0].equals("11")){ //ini sebenarnya udah pasti sih message yang dikirim pasti depannya 11
                    String clientUsername = requests1[1];
                    long clientLastupdate = Long.parseLong(requests1[2]);
                    System.out.println("myrunnable "+clientUsername+" :: clientLastupdate: "+clientLastupdate+", serverLastupdate: "+server.getLastUpdate());
                    if (clientLastupdate < server.getLastUpdate()){
                        //server sudah lebih update
                        
                        //ambil semua task_id dan task_status di db yang lebih update
                        String respond1 = db.FetchUpdates(clientUsername, clientLastupdate);
                        System.out.println("myrunnable :: respond1: "+respond1);
                        if (respond1.equals("")){   //walaupun clientlastupdate < serverlastupdate, bisa saja lastupdate server itu untuk task yang bukan punya user ini, jadi waktu di fetch tugas yang lebih besar dari client update tetap saja tidak ada
                            out.writeBytes("12\n"); //no updates yet
                        } else {
                            //ambil max_timestamp supaya client bisa update newest dia
                            long max_timestamp = db.GetMaxTimestamp(clientUsername);
                            System.out.println("myrunnable :: max_timestamp: "+max_timestamp);
                            out.writeBytes("13" + "," + max_timestamp + "," + respond1 + '\n');
                        }
                    } else {
                        out.writeBytes("12\n"); //no updates yet
                    }
                } else {    //client kirim pesan putus (5)
                    running = false;
                    confirmSocket.close();
                    server.setCount(server.getCount()-1);
                    System.out.println("one listener thread exited, count now: "+server.getCount());
                }
                Thread.sleep(1000);
            }
        } catch (Exception e){
            System.out.println("MyRunnable: "+e.getMessage());
        }
    }
}
