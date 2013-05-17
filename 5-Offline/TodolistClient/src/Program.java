
import id.ac.itb.todolist.client.Controller;
import java.util.Scanner;

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
//        System.out.println("--- " + controller.connect("192.168.1.51", 9000));
        System.out.println("--- " + controller.connect("127.0.0.1", 9000));
        System.out.println(controller.login("edwardsp", "lalalala"));
        System.out.println("SID: " + controller.getSessionId());
        
        Scanner sc = new Scanner(System.in);
        sc.next();
//        controller.updateStatus(1, true);
//        controller.updateStatus(2, true);
//        
//        controller.saveObject();
//        controller.loadObject();
//        
//        controller.updateStatus(3, true);
    }
}
