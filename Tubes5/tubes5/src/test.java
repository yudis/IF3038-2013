/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aji
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class test {
    
    private static JButton createSimpleButton(String text) {
        JButton button = new javax.swing.JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);

        return button;
    } 
    
   private static void createAndShowGui() {
      JPanel panel = new JPanel();
      panel.add(new JButton(new ShowtestAction()));
      JFrame frame = new JFrame("test");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(panel);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}

class ShowtestAction extends AbstractAction {
   private JPanel panel = new JPanel();

   public ShowtestAction() {
      super("Show Moving Dialog");
      panel.add(new JLabel("label"));
      panel.add(new JTextField("TextField", 10));
      panel.add(new JButton("Button"));
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      JFrame owner = (JFrame) SwingUtilities.getWindowAncestor((Component) e
            .getSource());
      final JDialog dialog = new JDialog(owner, "Dialog",
            ModalityType.APPLICATION_MODAL);
      dialog.getContentPane().add(panel);
      dialog.pack();
      dialog.setLocation(0, 0);

      int delay = 20;
      new Timer(delay , new ActionListener() {
         int x = 0;
         int y = 0;
         Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();

         @Override
         public void actionPerformed(ActionEvent e) {
            int maxX = scrn.width - dialog.getWidth();
            int maxY = scrn.height - dialog.getHeight();
            if (x < maxX  && y < maxY) {
               x++;
               y++;
               dialog.setLocation(x, y);
            } else {
               ((Timer)e.getSource()).stop();
            }
         }
      }).start();

      dialog.setVisible(true);

   }
}