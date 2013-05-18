package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Model.Task;

public class Controller {
	
	private Socket clientSocket;
	private static final byte LOGIN = 0;
	private static final byte LIST = 1;
	private static final byte UPDATE = 2;
	private static final byte LOGOUT = 9;
	private static final byte SUCCESS = 10;
	private static final byte FAILED = 11;
	private long sessionId;
	public List<Task> task_list = new ArrayList<Task>();
	
	public long getSessionId()
	{
		return sessionId;
	}
	
	public boolean connect(String ip, int port)
	{
		try
		{
			if (clientSocket != null && clientSocket.isConnected())
			{
				clientSocket.close();
			}
			clientSocket = new Socket(ip,port);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			clientSocket = null;
		}
		
		return (clientSocket != null);
	}
	
	public boolean login(String username, String password)
	{
		try
		{
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			
			out.write(LOGIN);
			
			byte[] sendbyte = username.getBytes();
			out.writeInt(sendbyte.length);
			out.write(sendbyte);
			
			sendbyte = password.getBytes();
			out.writeInt(sendbyte.length);
			out.write(sendbyte);
			byte response = in.readByte();
			if (response == SUCCESS)
			{
				System.out.println("RESPONSE :: SUCCESS");
				sessionId = in.readLong();
				System.out.println("SESSIONID :: " + sessionId);
				return true;
			}
			else if (response == FAILED)
			{
				System.out.println("RESPONSE :: FAILED");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void saveTask() {
		try {
			FileOutputStream file = new FileOutputStream("load.out");
			ObjectOutputStream fileout = new ObjectOutputStream(file);
			
			fileout.writeObject(task_list);
			fileout.flush();
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadTask() {
		try{
			FileInputStream file = new FileInputStream("load.out");
			ObjectInputStream fileout = new ObjectInputStream(file);
			
			task_list = (List<Task>)fileout.readObject();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void list()
	{
		try{
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			
			out.writeByte(LIST);
			out.writeLong(sessionId);
			
			out.writeInt(task_list.size());
			for (int i = 0; i < task_list.size(); i++)
			{
				out.writeChars(task_list.get(i).TaskToString());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
