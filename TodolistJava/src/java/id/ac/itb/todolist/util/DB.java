package id.ac.itb.todolist.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class DB {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
//                Properties prop = new Properties();
//                InputStream inputStream = DB.class.getClassLoader().getResourceAsStream("/db.properties");
//                prop.load(inputStream);
//                String driver = prop.getProperty("driver");
//                String url = prop.getProperty("url");
//                String user = prop.getProperty("user");
//                String password = prop.getProperty("password");
                
                JSONObject service_json=new JSONObject(java.lang.System.getenv("VCAP_SERVICES"));
                
        
                
                JSONObject mysql_config=new JSONObject();
                try {
                    mysql_config = service_json.getJSONArray("mysql-5.1").getJSONObject(0).getJSONObject("credentials");
                } catch (JSONException ex) {
                    Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
                }

                String username = mysql_config.getString("username");
                String password = mysql_config.getString("password");
                String hostname = mysql_config.getString("hostname");
                int port = mysql_config.getInt("port");
                String db = mysql_config.getString("name");
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://"+hostname+":"+port+"/"+db, username, password);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }
            return connection;
        }
    }
}