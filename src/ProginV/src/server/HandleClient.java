/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.JSON.JSONArray;
import server.JSON.JSONObject;
import server.JSON.JSONValue;

/**
 *
 * @author M Reza MP
 */
public class HandleClient extends Thread {
    Socket client;
    ServerForm frame;
    InputStream dis = null;
    OutputStream dos = null;
    String apiaddress = "http://tubes4asdasd.aws.af.cm/api.php/";
    //String apiaddress = "http://localhost/GitHub/IF3038-2013/src/server/api.php/";
    
    ArrayList<String> clients = new ArrayList<>();
    
    public HandleClient(ServerForm f, Socket s) {
        this.frame = f;
        this.client = s;
    }

    @Override
    public void run() {
        try {
            frame.getLogTextArea().append("Connection received from: " + client.getInetAddress().getHostName() + "\n");
            while (true)
            {
                dis = client.getInputStream();
                byte[] b = new byte[1024];
                int r;
                r = dis.read(b);

                if (r != -1) {
                    String arg = new String(b).trim();
                    System.out.println("message: " + arg);
                    if (arg.contains("handshake"))
                    {
                        doHandshake(arg);
                    }
                    else if (arg.contains("login")) 
                    {
                        doLogin(arg);
                    }
                    if (arg.contains("logout"))
                    {
                        doLogout(arg);
                    }
                    else if (arg.contains("getTask"))
                    {
                        doGetTask(arg);
                    }
                    else if (arg.contains("update"))
                    {
                        doUpdate(arg);
                    }
                }
                
                sleep(1000);
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String bytesToHex(byte[] b) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder buf = new StringBuilder();
        for (int j = 0; j < b.length; j++) {
            buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
            buf.append(hexDigit[b[j] & 0x0f]);
        }
        return buf.toString();
    }

    private String readDataFromWebService(String url) {
        String output = "";

        try {
            URL urlObj = new URL(url.replace(" ", "%20"));
            URLConnection tc = urlObj.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream(), "UTF-8"))) {
                String line;
                while ((line = in.readLine()) != null) {

                    output += line + "\n";
                }
            }
        } catch (MalformedURLException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }

        System.out.println("output: " + output);
        return output.trim();
    }

    public void doHandshake(String arg)
    {
        try {
            if (clients.size() == 1)
            {
                dos = client.getOutputStream();
                dos.write("full".getBytes());
                dos.flush();
            }
            
            dos = client.getOutputStream();
            dos.write("success".getBytes());
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void doLogin(String arg) {
        try {

            MessageDigest md = MessageDigest.getInstance("SHA1");
            String[] temp = arg.split("/");
            String username = temp[1];
            String password = bytesToHex(md.digest(temp[2].getBytes()));

            System.out.println("username: " + username);
            System.out.println("password: " + password);

            String url = apiaddress + "checklogin/" + username + "/" + password;
            String output = readDataFromWebService(url);
            System.out.println(output);
            
            if (Integer.parseInt(output) == 1)
            {
                clients.add(username);
            }
                    
            String result = (output.contains("Connection error:")) ? "Cannot access database" : ((Integer.parseInt(output) == 1) ? "Success login" : "Username or password incorrect");

            frame.getLogTextArea().append(arg + "\n");
            frame.getLogTextArea().append(result + "\n");
            
            dos = client.getOutputStream();

            dos.write(result.getBytes());
            dos.flush();

        } catch (NoSuchAlgorithmException | IOException ex) {
            Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void doLogout(String arg)
    {
        try {
            int id = Integer.parseInt(arg.split("/")[1]);
            clients.remove((Object)id);
            
            frame.getLogTextArea().append("client " + id + " has been log out\n");
            dos = client.getOutputStream();
            dos.write("success".getBytes());
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String[][] json_parse(String data)
    { 
        Object json = JSONValue.parse(data);
        JSONArray jsonArray = (JSONArray) json;
        System.out.println(jsonArray.size());
        String[][] result = new String[jsonArray.size()][4];
        for (int i = 0; i < jsonArray.size(); ++i)
        {
            JSONObject tugas = (JSONObject) jsonArray.get(i);
            System.out.println(tugas.get("nama_tugas"));
            JSONArray cat = (JSONArray) tugas.get("cat");
            JSONObject nama_kategori = (JSONObject) cat.get(0);
            System.out.println(nama_kategori.get("nama_kategori"));
            
            result[i][0] = tugas.get("nama_tugas").toString();
            result[i][1] = tugas.get("deadline").toString();
            result[i][2] = nama_kategori.get("nama_kategori").toString();
            result[i][3] = tugas.get("status").toString();
            for(int j = 0; j < 4; ++j)
            {
                System.out.println(result[i][j]);
            }
        }
        
        return result;
    }
    
    public void doGetTask(String arg)
    {
        try {
            frame.getLogTextArea().append(arg + "\n");
            
            dos = client.getOutputStream();
            String url = apiaddress + arg;
            String output = readDataFromWebService(url);
            String[][] data = json_parse(output);
            frame.getLogTextArea().append("data tugas: " + output + "\n");
            String coba = "";
            coba += data.length + "_" + "4_";
            for (int i = 0; i < data.length; ++i)
            {
                for (int j = 0; j < 4; ++j)
                {
                    coba += data[i][j] + ";";
                }
                
                coba+= "<";
            }
            
            dos.write(coba.getBytes());
            dos.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void doUpdate(String arg)
    {
        try {
            frame.getLogTextArea().append(arg + "\n");
            
            dos = client.getOutputStream();
            String url = apiaddress + arg;
            String output = readDataFromWebService(url);
            System.out.println(output);
            
            //String url = apiaddress + "getTask";
            //String output = readDataFromWebService(url);
            frame.getLogTextArea().append("updates: " + output + "\n");
            dos.write(("cool").getBytes());
            dos.flush();
            /*
            String url = apiaddress + "getTask";
            String output = readDataFromWebService(url);
            frame.getLogTextArea().append("data tugas: " + output + "\n");
            dos.write(output.getBytes());
            dos.flush();
            */
        } catch (IOException ex) {
            Logger.getLogger(HandleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}