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
import java.util.HashMap;
import java.util.Random;

import org.json.JSONObject;

public class ConnectionHandler extends Thread {

	private Socket clientSocket;
	private static final byte LOGIN = 0;
	private static final byte LIST = 1;
	private static final byte UPDATE = 2;
	private static final byte LOGOUT = 9;
	private static final byte SUCCESS = 10;
	private static final byte FAILED = 11;
	private static Random random = new Random();
	private HashMap<Long,String> session = new HashMap<Long, String>();
	
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
				byte msg = in.readByte();
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
				else if (msg == LIST)
				{
					int n = in.readInt();
					
				}
				else if (msg == UPDATE)
				{
					
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
