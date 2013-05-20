package Controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Model.Task;

public class ConnectionHandler extends Thread {

	private Socket clientSocket;
	private static final byte LOGIN = 0;
	private static final byte SYNC = 1;
	private static final byte UPDATE = 2;
	private static final byte LIST = 3;
	private static final byte LOGOUT = 9;
	private static final byte SUCCESS = 10;
	private static final byte FAILED = 11;
	private static Random random = new Random();
	private HashMap<Long,String> session = new HashMap<Long, String>();
	public List<Task> task_list = new ArrayList<Task>();
	public List<Task> task_listServer = new ArrayList<Task>();
	
	public ConnectionHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		long sessionId = -1;
		try
		{
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			
			while(true)
			{
				System.out.println("DENGERIN");
				byte msg = in.readByte();
				System.out.println("MSG = "+msg);
				if (msg != LOGIN)
				{
					sessionId = in.readLong();
					
					synchronized(session){
						if (!session.containsKey(sessionId)){
							out.write(FAILED);
							clientSocket.close();
							return;
						}
					}
				}
				
				if (msg == LOGIN)
				{
					byte[] data = new byte[in.readInt()];
					in.readFully(data);
					String username = new String(data);
					
					data = new byte[in.readInt()];
					in.readFully(data);
					String password = new String(data);
					try{
						URL url = new URL("http://eurilys.ap01.aws.af.cm/user/login_check?login_username="+username+"&login_password="+password);
			            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			            httpConn.setRequestMethod("GET");
			            httpConn.setRequestProperty("Accept", "application/json");
		            
			            if (httpConn.getResponseCode() != 200) {
			            	throw new RuntimeException("Failed : HTTP error code : " + httpConn.getResponseCode());
			            }
		            
			            BufferedReader br = new BufferedReader(new InputStreamReader((httpConn.getInputStream())));
			            String output;
			            String outputObject = "";
			            while ((output = br.readLine()) != null) {
			                outputObject += output;
			            } 
			            httpConn.disconnect();
			            
			            String[] output_parts = outputObject.split(",");
			            String fullname = output_parts[0];
			            String avatar = output_parts[1]; 
			            
			            if ("failed".equals(outputObject)) {
			            	out.writeByte(FAILED);
			            } else {
			            	synchronized (session) {
	                            long i = random.nextLong();
	                            while (session.containsKey(i)) {
	                                i = random.nextLong();
	                            }
	                            session.put(i, username);
	                            out.writeByte(SUCCESS);
	                            out.writeLong(i);
	                            System.out.println("SESSIONID :: " + i);
					    	}
			            }
					} 
					catch (Exception e) 
		            {
		            	out.writeByte(FAILED);
		            }
				}
				else if (msg == SYNC)
				{
					int n = in.readInt();
					for (int i = 0; i < n; i++) {
						byte[] data = new byte[in.readInt()];
						in.readFully(data);
						String taskTemp = new String(data);
						JSONObject taskObject = new JSONObject(taskTemp.toString());
						task_list.add(new Task(taskObject));
                    }
					
					String username = session.get(sessionId);
					System.out.println("LIST USERNAME :: "+ username);
					URL taskURL = new URL("http://eurilys.ap01.aws.af.cm/task/all_task?username=" + username);
		            HttpURLConnection taskConn = (HttpURLConnection) taskURL.openConnection();
		            taskConn.setRequestMethod("GET");
		            taskConn.setRequestProperty("Accept", "application/json");
		            if (taskConn.getResponseCode() != 200) {
		                throw new RuntimeException("Failed : HTTP error code : " + taskConn.getResponseCode());
		            }
		            BufferedReader taskBr = new BufferedReader(new InputStreamReader((taskConn.getInputStream())));
		            String taskOutput;
		            String taskJSONObject = "";
		            while ((taskOutput = taskBr.readLine()) != null) {
		                taskJSONObject += taskOutput;
		            } 
		            taskConn.disconnect();
		            
		            JSONTokener taskTokener = new JSONTokener(taskJSONObject);
		            JSONArray taskroot = new JSONArray(taskTokener);
		        
		            for (int i=0; i<taskroot.length(); i++) {
		                JSONObject task = taskroot.getJSONObject(i);
		                JSONObject taskObject = new JSONObject();
		                taskObject.put("task_id", task.getString("task_id"));
	                    taskObject.put("task_name", task.getString("task_name"));
	                    taskObject.put("task_status", task.getString("task_status"));
	                    taskObject.put("task_deadline", task.getString("task_deadline"));
	                    taskObject.put("task_category", task.getString("task_category"));
	                    taskObject.put("task_creator", task.getString("task_creator"));
	                    taskObject.put("tag_list", task.getJSONArray("tag_list"));
	                    task_listServer.add(new Task(taskObject));
		            }
		            
		            for (int i = 0; i < task_list.size(); i++)
		            {
		            	for (int j=0; j < task_listServer.size(); j++){
		            		if (task_list.get(i).getId_task().equals(task_listServer.get(j).getId_task()))
		            		{
		            			if (task_list.get(i).getStatus().equals(task_listServer.get(j).getStatus()))
		            			{
		            				System.out.println("STATUSNYA SAMA");	
		            			}
		            			else
		            			{
		            				System.out.println("UPDATE STATUS ID = "+task_list.get(i).getId_task()+" status = "+task_list.get(i).getStatus());
		            			}
		            		}
		            	}
		            }
		            out.writeByte(SUCCESS);
				}
				else if (msg == LIST)
				{
						
						String username = session.get(sessionId);
						System.out.println("LIST TASK USERNAME :: "+ username);
						URL taskURL = new URL("http://eurilys.ap01.aws.af.cm/task/all_task?username=" + username);
			            HttpURLConnection taskConn = (HttpURLConnection) taskURL.openConnection();
			            taskConn.setRequestMethod("GET");
			            taskConn.setRequestProperty("Accept", "application/json");
			            try{
			            if (taskConn.getResponseCode() != 200) {
			            	System.out.println("MASUK DI EXC LIST2");
			            	throw new RuntimeException("Failed : HTTP error code : " + taskConn.getResponseCode());
			            }
		            
		                BufferedReader taskBr = new BufferedReader(new InputStreamReader((taskConn.getInputStream())));
			            String taskOutput;
			            String taskJSONObject = "";
			            while ((taskOutput = taskBr.readLine()) != null) {
			                taskJSONObject += taskOutput;
			            } 
			            taskConn.disconnect();
			            
			            JSONTokener taskTokener = new JSONTokener(taskJSONObject);
			            JSONArray taskroot = new JSONArray(taskTokener);
			            task_listServer.clear();
			            for (int i=0; i<taskroot.length(); i++) {
			                JSONObject task = taskroot.getJSONObject(i);
			                JSONObject taskObject = new JSONObject();
			                taskObject.put("task_id", task.getString("task_id"));
		                    taskObject.put("task_name", task.getString("task_name"));
		                    taskObject.put("task_status", task.getString("task_status"));
		                    taskObject.put("task_deadline", task.getString("task_deadline"));
		                    taskObject.put("task_category", task.getString("task_category"));
		                    taskObject.put("task_creator", task.getString("task_creator"));
		                    taskObject.put("tag_list", task.getJSONArray("tag_list"));
		                    task_listServer.add(new Task(taskObject));
			            }
			            System.out.println("mau kirim sukses di sini");
			            out.writeByte(SUCCESS);
			            System.out.println("kirim sukses di sini");
			            out.writeInt(task_listServer.size());
						for (int i = 0; i < task_listServer.size(); i++)
						{
							String taskTemp = task_listServer.get(i).TaskToString();
							byte[] sendbyte = taskTemp.getBytes();
							out.writeInt(sendbyte.length);
							out.write(sendbyte);
						}
					} 
					catch (Exception e) 
		            {
		            	System.out.println("MASUK DI EXC LIST");
		            	out.writeByte(FAILED);
		            }
				}
				else if (msg == UPDATE)
				{
					try{
						int n = in.readInt();
						for (int i = 0; i < n; i++) {
							byte[] recv = new byte[in.readInt()];
							in.readFully(recv);
							String data = new String(recv);
							String[] output_parts = data.split(",");
				            String taskID = output_parts[0];
				            String status = output_parts[1];
				            
				            //update status
				            if (status.equals("0"))
				            {
				            	URL url = new URL("http://eurilys.ap01.aws.af.cm/task/finish_task?task_id="+taskID);
	
				                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
				                httpConn.setRequestMethod("GET");
				                httpConn.setRequestProperty("Accept", "application/json");
			                
					            if (httpConn.getResponseCode() != 200) {
					            	throw new RuntimeException("Failed : HTTP error code : " + httpConn.getResponseCode());
					            }
				            
				                BufferedReader br = new BufferedReader(new InputStreamReader((httpConn.getInputStream())));
				                String output;
				                String outputObject = "";
				                while ((output = br.readLine()) != null) {
				                    outputObject += output;
				                } 
				                httpConn.disconnect();
				                
				                if (outputObject.equals("1"))
				                {
				                	System.out.println("UPDATE STATUS "+ taskID +" BERHASIL");
				                }
				                else
				                {
				                	System.out.println("UPDATE STATUS "+ taskID +" GAGAL");
				                }
				            }
				            else if (status.equals("1"))
				            {
				            	URL url = new URL("http://eurilys.ap01.aws.af.cm/task/unfinish_task?task_id="+taskID);
	
				                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
				                httpConn.setRequestMethod("GET");
				                httpConn.setRequestProperty("Accept", "application/json");
			                
					            if (httpConn.getResponseCode() != 200) {
					            	throw new RuntimeException("Failed : HTTP error code : " + httpConn.getResponseCode());
					            }
				            
				                BufferedReader br = new BufferedReader(new InputStreamReader((httpConn.getInputStream())));
				                String output;
				                String outputObject = "";
				                while ((output = br.readLine()) != null) {
				                    outputObject += output;
				                } 
				                httpConn.disconnect();
				                
				                if (outputObject.equals("1"))
				                {
				                	System.out.println("UPDATE STATUS "+ taskID +" BERHASIL");
				                }
				                else
				                {
				                	System.out.println("UPDATE STATUS "+ taskID +" GAGAL");
				                }
				            }
						}
						
						out.writeByte(SUCCESS);
					} 
	            	catch (Exception e) 
		            {
		            	out.writeByte(FAILED);
		            }
				}
				else if (msg == LOGOUT)
				{
					
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}

}
