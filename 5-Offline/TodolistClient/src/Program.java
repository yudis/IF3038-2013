
import id.ac.itb.todolist.client.Controller;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ^
 *
 * @author Edward Samuel
 */
public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Controller controller = new Controller("127.0.0.1", 9000);

        while (true) {
            try {
                System.out.println("Login with wIlson:lalalala@127.0.0.1:9000");
                System.out.println(controller.login("wIlson", "lalalala"));
                System.out.println(controller.list());
                System.out.println(controller.tgsList);
                sc.next();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Trying connecting...");
                while (!controller.connect()) {
                    System.err.println("Failed connecting");
                    sc.next();
                    System.out.println("Trying connecting...");
                }
            }
        }
    }
}
