package id.ac.itb.todolist.server;

import java.util.Scanner;

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
