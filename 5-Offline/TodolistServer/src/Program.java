
import id.ac.itb.todolist.server.Controller;
import java.io.IOException;
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
        try {
            Controller controller = new Controller(9000);
            Thread thread = new Thread(controller);
            thread.start();
            
            Scanner sc = new Scanner(System.in);
            int pil;
            do {
                pil = sc.nextInt();
                if (pil == 1) {
                    controller.printSession();
                }
            } while (pil != 0);
            
            System.exit(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
