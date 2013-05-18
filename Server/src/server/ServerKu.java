package server;

import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.Tugas;
import id.ac.itb.todolist.model.User;
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
            System.err.println("Could not listen on port:" + port);
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

    // Message
    private static final byte MSG_LOGIN = 1;
    private static final byte MSG_UPDATE = 2;
    private static final byte MSG_LIST = 3;
    private static final byte MSG_SUCCESS = 0;
    private static final byte MSG_FAILED = -1;
    private Socket server = null;

    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.server = socket;
    }

    @Override
    public void run() {
        System.out.println("Port: " + server.getPort());
        while (true) {
            try {
                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                byte resType = in.readByte();
                if (resType == MSG_LOGIN) {
                    String username = in.readUTF();
                    String password = in.readUTF();

                    UserDao userdao = new UserDao();
                    User user = new User();

                    user = userdao.getUserLogin(username, password);
                    if (user == null) {
                        out.writeByte(MSG_FAILED);
                    } else {
                        out.writeByte(MSG_SUCCESS);
                    }
                }
            } catch (IOException e) {
                System.out.println("Client from IP: " + server.getRemoteSocketAddress() + " disconnected.");
                break;
            }
        }
    }
}
