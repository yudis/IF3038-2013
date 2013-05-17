package client;

import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        boolean restart = true;
        while (restart) {
            restart = false;
            try {

                System.out.println("Connecting to " + serverName
                        + " on port " + port);
                try (Socket client = new Socket(serverName, port)) {
                    System.out.println("Just connected to " + client.getRemoteSocketAddress());
                    OutputStream outToServer = client.getOutputStream();
                    DataOutputStream out = new DataOutputStream(outToServer);
                    while (true) {
                        out.writeUTF("Hello from " + client.getLocalSocketAddress());
                        InputStream inFromServer = client.getInputStream();
                        DataInputStream in = new DataInputStream(inFromServer);
                        System.out.println("Server says " + in.readUTF());

                        Thread.sleep(1000);
                    }
                } catch (SocketException se) {
                    System.out.println("SE  : reconnect");
                    restart = true;
                    Thread.sleep(1000);
                } catch (SocketTimeoutException ste){
                    System.out.println("STE : reconnect");
                    restart = true;
                    Thread.sleep(1000);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void connect() {
    }
}
