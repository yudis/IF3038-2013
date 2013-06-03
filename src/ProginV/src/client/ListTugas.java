/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Aisyah
 */
public class ListTugas extends JPanel implements Runnable {

    /**
     * Creates new form ListTugas
     */
    String username;
    private Socket server = null;
    private OutputStream dos = null;
    private InputStream dis = null;
    JFrame frame;
    JDialog dialog;
    JTable table;
    JButton update;
    JButton logout;
    Object[][] tasks;
    
    
    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {
        frame.setVisible(false);
        dialog.setVisible(true);
    }
    
    public void doLogout()
    {
        try {
            dos = server.getOutputStream();
            dos.write(("logout/" + username).getBytes());
            dos.flush();
            System.out.println("berhasil kirim message");

            byte[] b = new byte[1024];
            dis = server.getInputStream();
            int r = dis.read(b);
            System.out.println();
            if (r != -1) {
                JOptionPane.showMessageDialog(null, new String(b).trim());
                //result = Integer.parseInt(new String(b).trim());

                //JOptionPane.showMessageDialog(frmToDoList, (result == 1) ? "Success login." : "Username or password incorrect.");
            }
        } catch (IOException ex) {
            Logger.getLogger(ListTugas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateActionPerformed(java.awt.event.ActionEvent evt) {
        frame.setVisible(false);
        dialog.setVisible(true);
    }
    public ListTugas(String username, JDialog dia, Socket _server, OutputStream _dos, InputStream _dis) {
        super(new GridLayout(1, 0));
        this.username = username;
        dialog = dia;
        
        server = _server;
        dos = _dos;
        dis = _dis;
    }

    //kelas pembentuk tabel
    class Tabel extends AbstractTableModel {

        private String[] namakolom = {
            "Nama Tugas", "Tanggal Tugas", "Kategori", "Status"};
        private Object[][] data = {{"progin dewa",
                "2013-03-05", "progin", false},
            {"tubes1",
                "2013-03-23", "sister", false},};
        //private Object data = new Object [][];
        //ArrayList <Object> data = new ArrayList<>();

        public Tabel(Object[][] tasks)
        {
            super();
            data = tasks;
        }
        
        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return namakolom.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            if (col == 3) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            if (true) {
                System.out.println("Mengubah " + row + "," + col
                        + " menjadi " + value);
            }

            data[row][col] = value;
            updateData(data, row, getColumnCount());

            fireTableCellUpdated(row, col);

            if (true) {
                System.out.println("Data baru");
                printDebugData();
            }
        }

        public void updateData(Object[][] data, int currentRow, int col) {
            int result = 0;
            try {
                //String username = jTextField1.getText();
                //String password = jPasswordField1.getText();
                System.out.println("Masuk username");

                String coba = "update/";
                //coba += (Integer.toString(row) + "/");
                //coba += (Integer.toString(col) + "/");
                //dos.write(.getBytes());
                //dos.write(.getBytes());
                //dos.write(.getBytes());
                coba += ("" + data[currentRow][0] + "/");
                coba += ("" + (((boolean)(data[currentRow][3]) == true) ? 1 : 0) + "/");
                
                //dos.write(("login/" + username + "/" + password).getBytes());
                coba += username;
                savelog(coba);
                
                dos = server.getOutputStream();
                dos.write(coba.getBytes());
                dos.flush();
                System.out.println("berhasil kirim message");

                byte[] b = new byte[1024];
                dis = server.getInputStream();
                int r = dis.read(b);
                System.out.println(new String(b).trim());
                if (r != -1) {
                    JOptionPane.showMessageDialog(null, result);
                    //result = Integer.parseInt(new String(b).trim());

                    //JOptionPane.showMessageDialog(frmToDoList, (result == 1) ? "Success login." : "Username or password incorrect.");
                }
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        public void savelog(String text) {
            Writer writer = null;

            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("log.txt", true), "utf-8"));
                writer.write(text + "\n");
            } catch (IOException ex) {
                // report
            } finally {
                try {
                    writer.close();
                } catch (Exception ex) {
                }
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i = 0; i < numRows; i++) {
                System.out.print("    baris " + i + ":");
                for (int j = 0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }

    private void createAndShowGUI(Object[][] data) {
        //Create and set up the window.
        System.out.println("masuk tugas");
        frame = new JFrame("List Tugas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        //ListTugas newContentPane = new ListTugas();
        //newContentPane.setOpaque(true);
        
        table = new JTable(new ListTugas.Tabel(data));
        table.setPreferredScrollableViewportSize(new Dimension(300, 100));
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        add(scroll);
        
        update = new JButton("Synchronize");
        add(update);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        
        logout = new JButton("Logout");
        add(logout);
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        
        
        this.setOpaque(true);
        frame.setContentPane(this);

        //Display the window.
        frame.pack();
        frame.setVisible(true);




    }

    // public static void main(String[] args) {
    @Override
    public void run() {
        /*
         String url = "jdbc:mysql://localhost:3306/";
         String dbname = "progin";
         String driver = "com.mysql.jdbc.Driver";
         String username = "root";
         String password = "";

         try {
         Class.forName(driver).newInstance();
         try (Connection conn = DriverManager.getConnection(url + dbname, username, password)) {
         Statement st = conn.createStatement();
         ResultSet res = st.executeQuery("select * from tugas");
         while (res.next()) {
         String nama = res.getString("nama_tugas");
         String status = res.getString("status");
         System.out.println(nama);
         System.out.println(status);
         }
         }
         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
         }
         */
        doGetTask();
        createAndShowGUI(tasks);

    }

    public void doGetTask() {
        try {
            dos = server.getOutputStream();
            if (dos != null) {
                dos.write(("getTask/" + username).getBytes());
                dos.flush();

                dis = server.getInputStream();
                byte[] b = new byte[1024];
                int r = dis.read(b);
                if (r != -1) {
                    String data = new String(b).trim();
                    System.out.println("data: " + data);
                    insertData(data);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ListTugas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertData(String inputdata)
    {
        String[] c = inputdata.split("_");
        int row = Integer.parseInt(c[0]);
        int col = Integer.parseInt(c[1]);
        tasks = new Object[row][col];
        String[] r = c[2].split("<");
        for (int i = 0; i < row; ++i)
        {
            System.out.println(r[i]);
            String[] w = r[i].split(";");
            for (int j = 0; j < col; ++j)
            {
                if (j == 3)
                {
                    tasks[i][j] = (w[j].equals("1"));
                }
                else
                {
                    tasks[i][j] = w[j];
                }
            }
        }
    }
    // }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 204, 204));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
