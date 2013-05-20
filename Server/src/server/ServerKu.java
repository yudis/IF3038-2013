package server;

import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.Tugas;
import id.ac.itb.todolist.model.User;
import id.ac.itb.todolist.util.Message;
import java.net.*;
import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerKu extends Thread {

    private ServerSocket serverSocket;

    public ServerKu(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(1000);
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

    public MultiServerThread(Socket socket) throws SocketException {
        super("MultiServerThread");
        this.server = socket;
        //socket.setSoTimeout(1000);
    }

    @Override
    public void run() {
        System.out.println("Port: " + server.getPort());
        //while (true) {
        try {
            DataInputStream in = new DataInputStream(server.getInputStream());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());


            System.out.println("waiting for message");
            int jumlahmessage = 0;

            while (true) {

                byte resType = in.readByte();
                System.out.println("jumlah: " + ++jumlahmessage);
                System.out.println("resType: " + resType);
                if (resType == Message.MSG_LOGIN) {
                    String username = in.readUTF();
                    String password = in.readUTF();
                    System.out.println("username: " + username + "pass: " + password);
                    UserDao userdao = new UserDao();
                    User user = new User();
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
                        out.writeInt(ALT.size());
                        for (int idxTgs = 0; idxTgs < ALT.size(); idxTgs++) {
                            out.writeInt(ALT.get(idxTgs).getId());
                            out.writeUTF(ALT.get(idxTgs).getNama());
                            out.writeUTF(ALT.get(idxTgs).getPemilik().getUsername());
                            out.writeUTF(ALT.get(idxTgs).getKategori().getNama());
                            out.writeUTF((ALT.get(idxTgs).getTglDeadline()).toString());
                            out.writeBoolean(ALT.get(idxTgs).isStatus());
                            out.writeInt(ALT.get(idxTgs).getAssignees().size());
                            for (int iA = 0; iA < ALT.get(idxTgs).getAssignees().size(); iA++) {
                                out.writeUTF(((User) (((ArrayList) ALT.get(idxTgs).getAssignees()).get(iA))).getUsername());
                            }
                            out.writeInt(ALT.get(idxTgs).getTags().size());
                            for (int iT = 0; iT < ALT.get(idxTgs).getTags().size(); iT++) {
                                out.writeUTF((String) (((ArrayList) ALT.get(idxTgs).getTags()).get(iT)));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (resType == Message.MSG_UPDATE) {
                    try {
                        System.out.println("masuk update");
                        TugasDao td = new TugasDao();
                        int idTugas = in.readInt();
                        System.out.println("id" + idTugas);

                        boolean value = in.readBoolean();
                        System.out.println("value" + value);
                        if (td.setStatus(idTugas, value) != -1) {
                            out.writeByte(Message.MSG_SUCCESS);
                        } else {
                            out.writeByte(Message.MSG_FAILED);
                        }

                    } catch (Exception e) {
                        System.out.println("masuk exception");
                        out.writeByte(Message.MSG_FAILED);
                        e.printStackTrace();
                    }
                } else if (resType == Message.MSG_SYNC) {
                    System.out.println("masuk sync");
                    TugasDao td = new TugasDao();
                    int size = in.readInt();
                    for (int i = 0; i < size; i++) {
                        int id = in.readInt();
                        String timestamp = in.readUTF();
                        boolean status = in.readBoolean();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                        Date parsedDate;
                        try {
                            parsedDate = dateFormat.parse(timestamp);
                            Timestamp time = new java.sql.Timestamp(parsedDate.getTime());
                            System.out.println("waktu:" + td.getTimestamp(id).toString());
                            if (td.getTimestamp(id).before(time)) {
                                System.out.println("status diupdate");
                                td.setStatus(id, status);
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(MultiServerThread.class.getName()).log(Level.SEVERE, null, ex);
                        }

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
