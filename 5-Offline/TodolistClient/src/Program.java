
import id.ac.itb.todolist.client.Controller;
import java.io.IOException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Controller controller = new Controller("127.0.0.1", 9000);

        int pil = -1;
        do {
            System.out.print("Pilihan: ");
            pil = sc.nextInt();
            try {
                if (pil == 1) {
                    System.out.println("Login with wIlson:lalalala@127.0.0.1:9000");
                    System.out.println(controller.login("wIlson", "lalalala"));
                } else if (pil == 2) {
                    System.out.println("List");
                    System.out.println(controller.list());
                    System.out.println(controller.listTugas);
                } else if (pil == 3) {
                    System.out.println("Logout: " + controller.logout());
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Trying connecting...");
                while (!controller.connect()) {
                    System.err.println("Failed connecting");
                    sc.next();
                    System.out.println("Trying connecting...");
                }
            }
        } while (pil != 0);
    }
}
