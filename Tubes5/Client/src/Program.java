import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		Scanner sc = new Scanner(System.in);
		Controller controller = new Controller();
		System.out.println("-Connect- " + controller.connect("127.0.0.1", 9000));
		System.out.println("-Login- " + controller.login("sharonloh", "gilagila"));
		controller.loadTask();
		if (controller.update_list.size() == 0)
		{
			System.out.println("daftar update kosong");
		}
		controller.update();
		//sc.next();
		//controller.list();
		sc.next();
		/*
		controller.ubahStatus();
		for (int i = 0; i < controller.update_list.size(); i++)
		{
			System.out.println("daftar update = " + controller.update_list.get(i));
		}
		controller.update();
		*/
		//controller.list();
		controller.logout();
		/*
		controller.ubahStatus();
		//controller.saveTask();
		for (int i = 0; i < controller.update_list.size(); i++)
		{
			System.out.println("daftar update = " + controller.update_list.get(i));
		}
		controller.update();
		controller.list();*/
	}

}
