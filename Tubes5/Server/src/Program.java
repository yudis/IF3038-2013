import Controller.Controller;


public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
			Controller controller = new Controller();
			controller.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
