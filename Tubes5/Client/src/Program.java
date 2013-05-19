import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Controller.Controller;
import Model.Task;

public class Program {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		System.out.println("-Connect- " + controller.connect("127.0.0.1", 9000));
		System.out.println("-Login- " + controller.login("sharonloh", "gilagila"));
		controller.loadTask();
		controller.sync();
	}

}
