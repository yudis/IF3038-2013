/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tugas;

public class Client {

    private String serverName = "localhost";
    private int port = 6066;
    private String username;
    private boolean login = false;
    private long deltaTime;
    private static int command = 0;
    private List<Tugas> tasks;
    private boolean prevState = false;
    private boolean State = false;

    public Client() {
    }

    public int getPort() {
        return port;
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

    /**
     * @return the serverName
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @param serverName the serverName to set
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the isLogin
     */
    public boolean isLogin() {
        return login;
    }

    /**
     * @param isLogin the isLogin to set
     */
    public void setLogin(boolean login) {
        this.login = login;
    }

    /**
     * @return the deltaTime
     */
    public long getDeltaTime() {
        return deltaTime;
    }

    /**
     * @param deltaTime the deltaTime to set
     */
    public void setDeltaTime(long deltaTime) {
        this.deltaTime = deltaTime;
    }

    /**
     * @return the command
     */
    public int getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(int command) {
        this.command = command;
    }

    public void checkOnline() { 
        prevState = State;
        try {
            System.out.println("Connecting to " + getServerName() + " on port " + getPort());
            Socket socket;
            socket = new Socket(getServerName(), getPort());
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());
            socket.close();
            State = true;
        } catch (IOException ex) {
            State = false;
        }
            
    }
    
    public void Login(String user, String pass) {
        try {
            System.out.println(user + " " + pass);
            System.out.println("Connecting to " + getServerName() + " on port " + getPort());
            Socket socket = new Socket(getServerName(), getPort());
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());

            OutputStream outToServer = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("login#" + user + "#" + pass);

            InputStream inFromServer = socket.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            String response = in.readUTF();
            System.out.println(response);
            String split[] = response.split("#");
            if (Boolean.parseBoolean(split[0])) {
                setDeltaTime(Long.parseLong(split[1], 10) - System.currentTimeMillis());
                setUsername(user);
                setLogin(true);
            }
            socket.close();
            State = true;
        } catch (IOException ex) {
        }
    }

    public void update(int id, boolean stats) {
        String request = "update#" + id + "#" + stats + "#" + (System.currentTimeMillis() + getDeltaTime());
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(getUsername() + "_log.txt", true));
            out.write(request + "\n");
            out.close();
        } catch (IOException ex1) {
        }
        try {
            System.out.println("Connecting to " + getServerName() + " on port " + getPort());
            Socket socket = new Socket(getServerName(), getPort());
            socket.setSoTimeout(10000);
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());

            OutputStream outToServer = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF(request);

            socket.close();
        } catch (IOException ex) {
        }
    }

    public void sync() {
        if (!isPrevState() && isState()) {
            try {
                HashMap<Integer, String> logging = new HashMap<Integer, String>();
                BufferedReader br = new BufferedReader(new FileReader(getUsername() + "_log.txt"));
                String line = br.readLine();
                while (!(line == null)) {
                    String split[] = line.split("#");
                    logging.put(Integer.parseInt(split[1]), line);
                    line = br.readLine();
                }
                br.close();
                Iterator it = logging.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry) it.next();
                    try {
                        Socket socket = new Socket(getServerName(), getPort());
                        socket.setSoTimeout(10000);

                        OutputStream outToServer = socket.getOutputStream();
                        DataOutputStream out = new DataOutputStream(outToServer);

                        out.writeUTF(pairs.getValue().toString());

                        socket.close();

                    } catch (IOException ex) {
                    }
                    it.remove();
                }

            } catch (IOException ex) {
            }
        }

        if (State) {
            try {
                System.out.println("Connecting to " + getServerName() + " on port " + getPort());
                Socket socket = new Socket(getServerName(), getPort());
                socket.setSoTimeout(10000);
                System.out.println("Just connected to " + socket.getRemoteSocketAddress());

                OutputStream outToServer = socket.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);

                out.writeUTF("sync#" + getUsername());
                InputStream inFromServer = socket.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);

                int len = in.readInt();
                System.out.println("len :" + len);
                byte[] data = new byte[len];
                if (len > 0) {
                    in.readFully(data, 0, len);
                }
                setTasks((List<Tugas>) deserialize(data));
                for (int i = 0; i < getTasks().size(); i++) {
                    System.out.println(i);
                    System.out.println(getTasks().get(i).getId() + getTasks().get(i).getNama() + getTasks().get(i).getDeadline().toString() + getTasks().get(i).isStatus() + getTasks().get(i).getLast_mod() + getTasks().get(i).getKategori() + getTasks().get(i).getAssignee() + getTasks().get(i).getTag());
                }
                socket.close();
                setPrevState(isState());
                setState(true);
            } catch (ClassNotFoundException ex) {
            } catch (IOException ex) {
                setPrevState(isState());
                setState(false);
            }
        }
    }

    public void logout() {
        setLogin(false);
        setUsername("");
    }

//    @Override
//    public void run() {
//        while (true) {
//            
//            if (!isLogin()) {
//                System.out.print("command : ");
//                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
//                try {
//                    command = Integer.parseInt(br1.readLine());
//                } catch (IOException ex) {
//                    
//                }
//                if (getCommand() == 0) {
//                    Login("wilson", "12345678");
//                    
//                    sync();
//                }
//            } else {
//                try {
//                    System.out.print("command : ");
//                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//                    command = Integer.parseInt(br.readLine());
//                    System.out.println(command);
//                    switch (getCommand()) {
//                        case 1:
//                            System.out.print("id : ");
//                    BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
//                    int idt = Integer.parseInt(br2.readLine());
//                            update(idt, true);//update
//                            break;
//                        case 2:
//                            sync(); //synchronization
//                            break;
//                        case 3:
//                            logout();
//                            break;
//                        default : break;
//                    }
//                    setCommand(-1);
//                } catch (IOException ex) {
//                    
//                }
//            }
//        }
//    }
//    
//    public static void main(String[] args) throws IOException
//    {
//        Thread t = new Client();
//        t.start();
//    }
    /**
     * @return the tasks
     */
    public List<Tugas> getTasks() {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(List<Tugas> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the prevState
     */
    public boolean isPrevState() {
        return prevState;
    }

    /**
     * @param prevState the prevState to set
     */
    public void setPrevState(boolean prevState) {
        this.prevState = prevState;
    }

    /**
     * @return the State
     */
    public boolean isState() {
        return State;
    }

    /**
     * @param State the State to set
     */
    public void setState(boolean State) {
        this.State = State;
    }
}