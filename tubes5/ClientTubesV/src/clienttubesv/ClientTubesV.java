/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttubesv;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chritianto Handojo
 */
public class ClientTubesV {
    private static int status;
    private static String username;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            java.util.logging.Logger.getLogger(ClientLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        status = 0;
        
        while (true) {
            if (status == 0) {
                //Load Frame Login
                final ClientLogin dialog = new ClientLogin(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        dialog.setVisible(true);
                    }
                });

                boolean login = false;
                
                while (!login) {
                    while (!dialog.isReady()) ;
                    Connector conn = new Connector("127.0.0.1",3400);
                    StringBuilder sb = new StringBuilder();
                    sb.append(0);
                    sb.append((char)dialog.getUsername().length());
                    sb.append((char)dialog.getPass().length());
                    sb.append(dialog.getUsername());
                    sb.append(dialog.getPass());
                    conn.setRequest(sb.toString());
                    conn.sendRequest();
                    String hasil = conn.getReply();

                    if (hasil.equalsIgnoreCase("LOGIN_SUCCESS")) {
                        username = dialog.getUsername();
                        login = true;
                    } else {
                        dialog.showWarning("Wrong username / password");
                    }
                    dialog.prepare();
                }
                
                dialog.setVisible(false);
                dialog.dispose();
                status = 1;
            } else if (status == 1) {
                //Load Frame TaskList
                final TaskList dialog = new TaskList();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        //synch first
                        System.exit(0);
                    }
                });
                
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        dialog.setVisible(true);
                    }
                });
                
                Synchronizer synch = new Synchronizer(username);
                Thread t = new Thread(synch);
                t.start();
                long last = 0;
                
                while (dialog.getStatus()) {
                    if (System.currentTimeMillis() - last > 4000) {
                        synch.process();
                        while (synch.busy()) ;                    
                        last = System.currentTimeMillis();
                    }
                }
                
                synch.process();
                
                dialog.setVisible(false);
                dialog.dispose();
                status = 0;
            } else {
                System.out.println("A Strange error occured. Returning to Login Page.");
                status = 0;
            }
        }
    }
}
