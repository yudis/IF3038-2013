package server;

import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.Tugas;
import id.ac.itb.todolist.model.User;
import id.ac.itb.todolist.util.Message;
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
            System.out.println("Listening on" + serverSocket.getLocalSocketAddress());

            new MultiServerThread(serverSocket.accept()).start();
        }
        serverSocket.close();
    }
}

class MultiServerThread extends Thread {

    // Message

    private Socket server = null;

    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.server = socket;
    }

    @Override
    public void run() {
        System.out.println("Port: " + server.getPort());
        //while (true) {
        try {
            DataInputStream in;
            in = new DataInputStream(server.getInputStream());


            System.out.println("waiting for message");
            while (true) {

                byte resType = in.readByte();

                System.out.println("resType: " + resType);
                if (resType == Message.MSG_LOGIN) {
                    String username = in.readUTF();
                    String password = in.readUTF();
                    System.out.println("username: " + username + "pass: " + password);
                    UserDao userdao = new UserDao();
                    User user = new User();
                    DataOutputStream out = new DataOutputStream(server.getOutputStream());
                    user = userdao.getUserLogin(username, password);
                    // System.out.println("USer: "+user.getTglLahir());
                    if (user == null) {
                        out.writeByte(Message.MSG_FAILED);
                        System.out.println("failed");
                    } else {
                        out.writeByte(Message.MSG_SUCCESS);
                        System.out.println("suck");

                    }
                } else if (resType == Message.MSG_LIST) {
                    try {
                        TugasDao TD = new TugasDao();
                        ArrayList<Tugas> ALT = (ArrayList<Tugas>) TD.getTugas(in.readUTF());
                        DataOutputStream out = new DataOutputStream(server.getOutputStream());
                        out.writeInt(ALT.size());
                        for (int idxTgs = 0; idxTgs < ALT.size(); idxTgs++) {
                            out.writeUTF(ALT.get(idxTgs).getNama());
                            out.writeUTF(ALT.get(idxTgs).getPemilik().getUsername());
                            out.writeUTF(ALT.get(idxTgs).getKategori().getNama());
                            out.writeUTF((ALT.get(idxTgs).getTglDeadline()).toString());
                            out.writeBoolean(ALT.get(idxTgs).isStatus());
                            out.writeInt(ALT.get(idxTgs).getAssignees().size());
                            for (int iA = 0; iA < ALT.get(idxTgs).getAssignees().size(); iA++) {
                                out.writeUTF(((User) (((ArrayList) ALT.get(idxTgs).getAssignees()).get(iA))).getUsername());
                            }
                            for (int iT = 0; iT < ALT.get(idxTgs).getTags().size(); iT++) {
                                out.writeUTF((String) (((ArrayList) ALT.get(idxTgs).getTags()).get(iT)));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Client from IP: " + server.getRemoteSocketAddress() + " disconnected.");
            e.printStackTrace();
            System.out.println("closed: " + server.isClosed());
            System.out.println("closed: " + server.isConnected());

        }
        // }
    }
}
