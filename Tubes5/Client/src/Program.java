import Controller.Controller;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Controller controller = new Controller();
		System.out.println("-Connect- " + controller.connect("127.0.0.1", 9000));
		System.out.println("-Login- " + controller.login("sharonloh", "gilagila"));
	}

}
