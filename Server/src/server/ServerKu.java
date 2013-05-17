package server;

import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.model.Tugas;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerKu extends Thread {

    private ServerSocket serverSocket;

    public ServerKu(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            System.out.println("Create new socket");
            serverSocket = new ServerSocket(port);
            System.out.println(serverSocket.getReceiveBufferSize());
        } catch (IOException e) {
            System.err.println("Could not listen on port:"+ port);
            System.exit(-1);
        }

        while (listening) {
            System.out.println("Listening");
            
            new MultiServerThread(serverSocket.accept()).start();
        }
        serverSocket.close();
    }
}

class MultiServerThread extends Thread {

    private Socket server = null;

    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.server = socket;
    }

    @Override
    public void run() {
        System.out.println("Port: " +server.getPort());
        while (true) {
            try {
                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
            } catch (IOException e) {
                System.out.println("Client from IP: " + server.getRemoteSocketAddress() + " disconnected." );
                break;
            }
        }
    }
}
