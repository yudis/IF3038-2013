
import id.ac.itb.todolist.server.Controller;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edward Samuel
 */
public class Program {
    
    public static void main(String[] args) {
        try {
            Controller controller = new Controller(9000);
            controller.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
