
import id.ac.itb.todolist.client.Controller;

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
        Controller controller = new Controller();
        System.out.println("--- " + controller.connect("127.0.0.1", 9000));
        controller.updateStatus(1, true);
        controller.updateStatus(2, true);
        controller.updateStatus(3, true);
    }
}
