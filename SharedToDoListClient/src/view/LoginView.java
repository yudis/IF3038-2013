/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author FUJITSU
 */
public class LoginView {
    
    private String userName;
    private String userPsw;
    
    public void createGUI(){
        JFrame frame = new JFrame("SharedToDoList | Login");
        frame.setDefaultLookAndFeelDecorated(true);
        Dimension frameMinSize = new Dimension(400, 200);
        frame.setMinimumSize(frameMinSize);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setMaximumSize(new Dimension(10, 10));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel TopLabel = new JLabel("SharedToDoList");
        panel.add(TopLabel);
        
        JTextField userNameField = new JTextField();
        userNameField.setMaximumSize(new Dimension(300, 20));
        userNameField.setToolTipText("username");
        panel.add(userNameField);
        JPasswordField passField = new JPasswordField();
        passField.setMaximumSize(new Dimension(300, 20));
        passField.setToolTipText("password");
        panel.add(passField);
        
        JButton logInButton = new JButton("Log In"); 
        logInButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sending bingung");
            }
        });
        panel.add(logInButton);
        
        //menggabungkan semua ke frame
//        frame.add(panel);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
