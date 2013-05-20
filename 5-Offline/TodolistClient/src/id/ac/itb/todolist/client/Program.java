package id.ac.itb.todolist.client;


import id.ac.itb.todolist.client.Controller;
import java.io.IOException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Controller controller = new Controller("127.0.0.1", 9000);

        // 1 Connect ke Server
        System.out.println("Trying connecting...");
        while (!controller.connect()) {
            System.err.println("Failed connecting");
            sc.next();
            System.out.println("Trying connecting...");
        }

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
                    System.out.println("Update status id=15 -> true");
                    try {
                        controller.updateStatus(15, true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else if (pil == 4) {
                    System.out.println("Update status id=15 -> false");
                    try {
                        controller.updateStatus(15, false);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else if (pil == 5) {
                    System.out.println("Update status id=19 -> true");
                    try {
                        controller.updateStatus(19, true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else if (pil == 6) {
                    System.out.println("Update status id=19 -> false");
                    try {
                        controller.updateStatus(19, false);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else if (pil == 7) {
                    controller.saveState("controller.out");
                } else if (pil == 8) {
                    System.out.println(controller.loadState("controller.out"));
                } else if (pil == 9) {
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
