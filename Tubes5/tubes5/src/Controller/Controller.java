package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import Model.Task;

public class Controller {
	
	private Socket clientSocket;
	private static final byte LOGIN = 0;
	private static final byte SYNC = 1;
	private static final byte UPDATE = 2;
	private static final byte LIST = 3;
	private static final byte LOGOUT = 9;
	private static final byte SUCCESS = 10;
	private static final byte FAILED = 11;
	private long sessionId;
	private String userID;
	public boolean isLogin = false;
	public List<Task> task_list = new ArrayList<Task>();
	public List<String> update_list = new ArrayList<String>();
	
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
				userID = username;
				isLogin = true;
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
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(userID+"load.out"));
			String s = "";
			s += task_list.size() + "\n";
			for (int i = 0; i < task_list.size(); i++)
	        {
	        	s += task_list.get(i).TaskToString()+ "\n";
	        }
			
			s += update_list.size() + "\n";
			for (int i = 0; i < update_list.size(); i++)
	        {
	        	s += update_list.get(i)+ "\n";
	        }
			
			writer.write(s);
			writer.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public void loadTask() {
		try{
			task_list.clear();
			BufferedReader reader = new BufferedReader(new FileReader(userID+"load.out"));
			String line = reader.readLine();
			int n = Integer.parseInt(line);
			if (n > 0)
			{
				task_list.clear();
				for (int i = 0; i < n; i++)
				{
					line = reader.readLine();
					JSONObject temp = new JSONObject(line);
					task_list.add(new Task(temp));
				}
			}
			
			line = reader.readLine();
			n = Integer.parseInt(line);
			if (n > 0)
			{
				update_list.clear();
				for (int i = 0; i < n; i++)
				{
					line = reader.readLine();
					update_list.add(line);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sync()
	{
		try{
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			
			out.writeByte(SYNC);
			out.writeLong(sessionId);
			
			out.writeInt(task_list.size());
			for (int i = 0; i < task_list.size(); i++)
			{
				String taskTemp = task_list.get(i).TaskToString();
				byte[] sendbyte = taskTemp.getBytes();
				out.writeInt(sendbyte.length);
				out.write(sendbyte);
			}
			
			byte response = in.readByte();
			if (response == SUCCESS)
			{
				System.out.println("BERHASIL SYNC");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void list(){
		try{
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			
			out.writeByte(LIST);
			out.writeLong(sessionId);
			System.out.println("tunggu response list");
			byte response = in.readByte();
			System.out.println("dapat response list" + response);
			if (response == SUCCESS)
			{
				task_list.clear();
				int n = in.readInt();
				for (int i = 0; i < n; i++) {
					byte[] data = new byte[in.readInt()];
					in.readFully(data);
					String taskTemp = new String(data);
					JSONObject taskObject = new JSONObject(taskTemp.toString());
					task_list.add(new Task(taskObject));
	            }
				saveTask();
			} 
			else if (response == FAILED)
			{
				System.out.println("list to server failed");
			}
		}
		catch (IOException e)
		{
			System.out.println("EXCEPTION");
			e.printStackTrace();
		}
	}
	
	public void update(){
		try{
			System.out.println("amsuk update");
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			
			out.writeByte(UPDATE);
			out.writeLong(sessionId);
			System.out.println("kirim pesan update");
			int n = update_list.size(); 
			if (n > 0)
			{
				out.writeInt(n);
				for (int i = 0; i < n; i++)
				{
					byte[] sendbyte = update_list.get(i).getBytes();
					out.writeInt(sendbyte.length);
					out.write(sendbyte);
				}
			}
			else out.writeInt(n);
			System.out.println("tunggu response update");
			byte response = in.readByte();
			if (response == SUCCESS)
			{
				list();
				update_list.clear();				
			}
			else if (response == FAILED)
			{
				System.out.println("update to server failed");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void ubahStatus(int i)
	{
		String s = task_list.get(i).getId_task() + "," + task_list.get(i).getStatus();
		//ubah juga task_list nya..
		update_list.add(s);
	}
	
	public void logout()
	{
		saveTask();
		System.out.println("logout");
		isLogin = false;
		userID = "";
		sessionId = -1;
	}
}
