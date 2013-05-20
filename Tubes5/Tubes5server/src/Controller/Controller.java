package Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {
	private ServerSocket serverSocket;
	
	public Controller() throws IOException
	{
		serverSocket = new ServerSocket(9000);
	}
	
	public void start()
	{
		while (true)
		{
			try
			{
				Socket clientSocket = serverSocket.accept();
				
				Thread thread = new Thread(new ConnectionHandler(clientSocket));
				thread.start();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
