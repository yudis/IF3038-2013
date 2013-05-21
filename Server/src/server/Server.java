package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import model.Tugas;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private int port = 6066;
    private static DbOperation db;
    
    public Server() throws IOException {
        serverSocket = new ServerSocket(port);
    }
    
    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }

    public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for client on port "
                        + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just connected to "
                        + server.getRemoteSocketAddress());
                DataInputStream in =
                        new DataInputStream(server.getInputStream());
                
                DataOutputStream out =
                        new DataOutputStream(server.getOutputStream());
                
                String request = in.readUTF();
                String[] split = request.split("#");
                if (split[0].compareTo("login") == 0) {
                    if (db.checkLogin(split[1], split[2])) {
                        out.writeUTF("true#"+System.currentTimeMillis());
                    }
                    else {
                        out.writeUTF("false");
                    }
                }
                else if (split[0].compareTo("sync") == 0) {
                    List<Tugas> tasks = db.getTugas(split[1]);
                    out.flush();
                    out.writeInt(serialize(tasks).length);
                    out.flush();
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i);
                        System.out.println(tasks.get(i).getId() + tasks.get(i).getNama() + tasks.get(i).getDeadline().toString() + tasks.get(i).isStatus() + tasks.get(i).getLast_mod() + tasks.get(i).getKategori() + tasks.get(i).getAssignee() + tasks.get(i).getTag());
                    }
                    out.write(serialize(tasks),0,serialize(tasks).length);
                    System.out.print(serialize(tasks).length);
                }
                else if (split[0].compareTo("update") == 0) {
                    db.Update(Integer.parseInt(split[1]), Boolean.parseBoolean(split[2]), Long.parseLong(split[3], 10));
                    System.out.println("update");
                }
                
                server.close();
            } catch (IOException e) {
                
            }
        }
    }

    public static void main(String[] args) {
//        DbOperation db = new DbOperation();
//        List<Tugas> tasks = db.getTugas("felixt");
//        for(int i = 0; i < tasks.size(); i++)
//            System.out.println(tasks.get(i).getId()+tasks.get(i).getNama()+tasks.get(i).getKategori()+tasks.get(i).getAssignee()+tasks.get(i).getTag());
        db = new DbOperation();
        try {
            Thread t = new Server();
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}