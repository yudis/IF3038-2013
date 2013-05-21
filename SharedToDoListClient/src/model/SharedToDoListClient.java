/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.LoginFrame;
import view.MainFrame;
/**
 *
 * @author FUJITSU
 */
public class SharedToDoListClient {
    
    private static ConnectionHandler connHandler;
    private static Socket socket;
    
    public static LoginFrame loginF;
    public static MainFrame mainF;
    
    private static final String SUCCESS_MESSAGE = "002";
    
    public static void main(String[] args) throws UnknownHostException, IOException {
//        create connection
        connHandler = new ConnectionHandler();
        socket = ConnectionHandler.getConnection();
        System.out.println("Koneksi berhasil");
        
//        menciptakan main frame
        mainF = new MainFrame(socket);
        mainF.setVisible(false);
        
//        membuka halaman login
        loginF = new LoginFrame(socket);
        loginF.setVisible(true);
        
        while (!mainF.isVisible()) {
            System.out.println("tunggu");
            try {
                Thread.sleep(400);
            } catch (InterruptedException ex) {
                Logger.getLogger(SharedToDoListClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
//        main frame telah diaktifkan
//        mengganti nama user aktif pada main frame
        mainF.activeUser.setText(loginF.uName);
        
//        membuat thread khusus untuk selalu mengecek kondisi koneksi
        Thread t = new Thread(new ConnectionChecker(socket, SharedToDoListClient.mainF));
        t.start();
        System.out.println("checker jalan");
        
//        memulai listen dari server
        String message;
        while (true) {
            System.out.println("listening mode to server");
            
            message = ConnectionHandler.listen();
            System.out.println("msg : " + message);
            
//            penanganan pesan dari server
            if (message.equals("file")) {
                listenFile();
//                mengirim pesan berhasil
                ConnectionHandler.sendString(SUCCESS_MESSAGE);
            }
        }
    }
    
    public static void listenFile() {
        try {
            int fileSize = 5000000; //max sekali kirim 5MB
            int bytesRead;
            int currentTot = 0;

            byte[] bytearray = new byte[fileSize];

            InputStream is;
            File fileSave;
            FileOutputStream fos;
            BufferedOutputStream bos;
            
//          bersiap menerima file dari server
            is = socket.getInputStream();
            File file = new File("dummy.txt");
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(bytearray, 0, bytearray.length);
            currentTot = bytesRead;
            
            do {
                //System.out.println(bytesRead + " hehehehe " + bytearray.length + " " + currentTot);
                if (is.available() != 0) bytesRead = is.read(bytearray, currentTot, bytearray.length-currentTot);
                else bytesRead = -1;
                if (bytesRead >= 0) currentTot += bytesRead;
                //System.out.println(bytesRead + "jreng");
            }while (bytesRead > -1);

            bos.write(bytearray, 0, currentTot);

            bos.flush();
        } catch (IOException ex) {
            Logger.getLogger(SharedToDoListClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
