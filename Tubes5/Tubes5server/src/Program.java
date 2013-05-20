import Controller.Controller;


public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
                        System.out.println("server started");
			Controller controller = new Controller();
			controller.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
