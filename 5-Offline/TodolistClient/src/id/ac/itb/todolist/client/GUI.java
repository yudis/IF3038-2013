package id.ac.itb.todolist.client;


import id.ac.itb.todolist.model.User;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class GUI extends javax.swing.JFrame {

    private Controller control;
    private boolean haslogin;
    private javax.swing.Timer timer;
    private int taskSelected;
    private static final int MAX_TRY_CONNECT = 3;
    private static final String STATE_FILENAME = "client.state";

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        haslogin = false;

        String ip;
        int port;

        try {
            Properties prop = new Properties();
            InputStream inputStream = new FileInputStream("client.properties");
            prop.load(inputStream);
            ip = prop.getProperty("ip");
            port = Integer.parseInt(prop.getProperty("port"));
        } catch (IOException e) {
            e.printStackTrace();
            ip = "127.0.0.1";
            port = 9000;
        }
        
        System.out.println(ip + ":" + port);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (haslogin) {
                    if (JOptionPane.showConfirmDialog(GUI.this, "Apakah Anda ingin menyimpan keadaan saat ini?", "Keluar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                        try {
                            control.saveState(STATE_FILENAME);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        File fState = new File(STATE_FILENAME);
                        fState.delete();
                    }
                }
                GUI.this.dispose();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }
        });

        tasklist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tasklist.getModel().getSize() > 0) {
                    showSelection(tasklist.getSelectedIndex());
                    tasklist.repaint();
                }
            }
        });

        timer = new javax.swing.Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (!control.logUpdate.isEmpty()) {
                        if (!control.updateToServer()) {
                            loggedOut();
                            return;
                        }
                    }

                    if (control.list()) {
                        int idx = tasklist.getSelectedIndex();
                        showSelection(idx == -1 ? control.listTugas.size() > 0 ? 0 : -1 : idx);
                    } else {
                        loggedOut();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    connect();
                }
            }
        });
        timer.setInitialDelay(0);

        File fState = new File(STATE_FILENAME);
        if (fState.exists() && JOptionPane.showConfirmDialog(GUI.this, "Apakah Anda ingin membuka keadaan saat dari sesi sebelumnya?", "Buka Keadaan", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
            try {
                control = Controller.loadState(STATE_FILENAME);
                fState.delete();
                System.out.println("-------------- STATE_FILENAME : " + STATE_FILENAME);
                System.out.println(control);
                haslogin = true;
                username.setText("");
                pass.setText("");
                userlabel.setVisible(false);
                passlabel.setVisible(false);
                username.setVisible(false);
                pass.setVisible(false);
                login.setText("Log keluar");
                taskname.setVisible(true);
                deadline.setVisible(true);
                assignee.setVisible(true);
                tag.setVisible(true);
                status.setVisible(true);
                kategori.setVisible(true);

                int idx = tasklist.getSelectedIndex();
                showSelection(idx == -1 ? control.listTugas.size() > 0 ? 0 : -1 : idx);

                timer.start();
            } catch (IOException ex) {
                ex.printStackTrace();
                control = new Controller(ip, port);
            }
        } else {
            control = new Controller(ip, port);
        }
    }

    private void connect() {
        if (control.connect()) {
            connectioninfo.setText("Status: Terhubung ke server");
            connectioninfo.setForeground(Color.BLUE);
        } else {
            connectioninfo.setText("Status: Terputus dari server");
            connectioninfo.setForeground(Color.red);
        }
    }

    private void loggedOut() {
        userlabel.setVisible(true);
        passlabel.setVisible(true);
        username.setVisible(true);
        pass.setVisible(true);
        login.setText("Log masuk");
        tasklist.setModel(new DefaultComboBoxModel());
        taskname.setVisible(false);
        deadline.setVisible(false);
        assignee.setVisible(false);
        tag.setVisible(false);
        status.setVisible(false);
        kategori.setVisible(false);
        haslogin = false;
        timer.stop();
    }

    private void showSelection(int idx) {
        try {
            Vector<String> tasktable = new Vector<>();
            for (int i = 0, len = control.listTugas.size(); i < len; i++) {
                tasktable.add(control.listTugas.get(i).getNama());
            }
            tasklist.setModel(new DefaultComboBoxModel(tasktable));
            if (idx > -1) {
                taskSelected = control.listTugas.get(idx).getId();

                taskname.setText(control.listTugas.get(idx).getNama());
                deadline.setText("Tenggat waktu : " + control.listTugas.get(idx).getTglDeadline());

                String assignees = "";
                ArrayList<User> users = new ArrayList(control.listTugas.get(idx).getAssignees());
                for (int i = 0; i < users.size(); i++) {
                    assignees += users.get(i).getUsername();
                    if (i < users.size() - 1) {
                        assignees += ", ";
                    }
                }
                assignee.setText("Pengerja : " + assignees);

                String tags = "";
                Object[] temp = control.listTugas.get(idx).getTags().toArray();
                for (int i = 0; i < temp.length; i++) {
                    tags += temp[i];
                    if (i < temp.length - 1) {
                        tags += ", ";
                    }
                }
                tag.setText("Tag : " + tags);

                if (control.listTugas.get(idx).isStatus()) {
                    status.setText("Status : Sudah selesai");
                } else {
                    status.setText("Status : Belum selesai");
                }

                kategori.setText("Kategori : " + control.listTugas.get(idx).getKategori().getNama());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userlabel = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        passlabel = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();
        tasklabel = new javax.swing.JLabel();
        changestatus = new javax.swing.JButton();
        connectioninfo = new javax.swing.JLabel();
        tasklist = new javax.swing.JComboBox();
        taskname = new javax.swing.JLabel();
        deadline = new javax.swing.JLabel();
        assignee = new javax.swing.JLabel();
        tag = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        kategori = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Todolist!");
        setMinimumSize(new java.awt.Dimension(600, 450));

        userlabel.setText("Nama pengguna");

        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        passlabel.setText("Sandi-lewat");

        login.setText("Log masuk");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        tasklabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tasklabel.setText("DAFTAR TUGAS");

        changestatus.setText("Ubah Status Tugas");
        changestatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changestatusActionPerformed(evt);
            }
        });

        connectioninfo.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        connectioninfo.setText("Status: Terputus dari server");

        tasklist.setMaximumRowCount(100);

        taskname.setVisible(false);
        taskname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        deadline.setVisible(false);
        deadline.setText("Tenggat waktu : ");

        assignee.setVisible(false);
        assignee.setText("Pengerja : ");

        tag.setVisible(false);
        tag.setText("Tag : ");

        status.setVisible(false);
        status.setText("Status : ");

        kategori.setVisible(false);
        kategori.setText("Kategori : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userlabel)
                            .addComponent(passlabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(username)
                            .addComponent(pass)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tasklabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tasklist, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(login)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                        .addComponent(connectioninfo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(changestatus)
                            .addComponent(taskname)
                            .addComponent(deadline)
                            .addComponent(assignee)
                            .addComponent(tag)
                            .addComponent(status)
                            .addComponent(kategori))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userlabel)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passlabel)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(login)
                    .addComponent(connectioninfo))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tasklabel)
                    .addComponent(tasklist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deadline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assignee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tag)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kategori)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(changestatus)
                .addContainerGap())
        );

        username.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        if (haslogin) {
            try {
                control.logout();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            loggedOut();
        } else if (!username.getText().equals("") && pass.getPassword().length > 0) {
            for (int i = 0; i < MAX_TRY_CONNECT; i++) {
                try {
                    if (control.login(username.getText(), new String(pass.getPassword()))) {
                        JOptionPane.showMessageDialog(this, "Berhasil log masuk", "Log Masuk", JOptionPane.INFORMATION_MESSAGE);
                        haslogin = true;
                        username.setText("");
                        pass.setText("");
                        userlabel.setVisible(false);
                        passlabel.setVisible(false);
                        username.setVisible(false);
                        pass.setVisible(false);
                        login.setText("Log keluar");
                        taskname.setVisible(true);
                        deadline.setVisible(true);
                        assignee.setVisible(true);
                        tag.setVisible(true);
                        status.setVisible(true);
                        kategori.setVisible(true);
                        timer.start();
                    } else {
                        JOptionPane.showMessageDialog(this, "Nama Pengguna dan sandi-lewat tidak cocok", "Log Masuk", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    if (i < MAX_TRY_CONNECT - 1) {
                        connect();
                    } else {
                        JOptionPane.showMessageDialog(this, "Gagal membuat koneksi ke server", "Log Masuk", JOptionPane.ERROR_MESSAGE);
                        loggedOut();
                    }
                }
            }
        }
    }//GEN-LAST:event_loginActionPerformed

    private void changestatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changestatusActionPerformed
        try {
            if (haslogin) {
                if (status.getText().equals("Status : Sudah selesai")) {
                    status.setText("Status : Belum selesai");
                    control.updateStatus(taskSelected, false);
                } else {
                    status.setText("Status : Sudah selesai");
                    control.updateStatus(taskSelected, true);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Logger a = new Logger();
            System.out.println("ascacsaaaaaaaaaaaaaaaaaaaaaaaaaacqwq" + control.logUpdate.size());
            a.log(control.logUpdate);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_changestatusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel assignee;
    private javax.swing.JButton changestatus;
    private javax.swing.JLabel connectioninfo;
    private javax.swing.JLabel deadline;
    private javax.swing.JLabel kategori;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel passlabel;
    private javax.swing.JLabel status;
    private javax.swing.JLabel tag;
    private javax.swing.JLabel tasklabel;
    private javax.swing.JComboBox tasklist;
    private javax.swing.JLabel taskname;
    private javax.swing.JLabel userlabel;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
