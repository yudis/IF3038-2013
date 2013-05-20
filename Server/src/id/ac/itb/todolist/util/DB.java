package id.ac.itb.todolist.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

public class DB {

    public static Connection getConnection() {
        Connection connection = null;
        
            try {
                String VCAP_SERVICES = "{\"mysql-5.1\":[{\"name\":\"ranger_db\",\"label\":\"mysql-5.1\",\"plan\":\"free\",\"tags\":[\"mysql\",\"mysql-5.1\",\"relational\",\"mysql-5.1\",\"mysql\"],\"credentials\":{\"name\":\"progin_471_13510039\",\"hostname\":\"localhost\",\"host\":\"localhost\",\"port\":3306,\"user\":\"progin\",\"username\":\"progin\",\"password\":\"progin\"}}]}";
                //String VCAP_SERVICES = java.lang.System.getenv("VCAP_SERVICES");
                
                JSONObject service_json = new JSONObject(VCAP_SERVICES);
                JSONObject mysql_config = service_json.getJSONArray("mysql-5.1").getJSONObject(0).getJSONObject("credentials");

                String username = mysql_config.getString("username");
                String password = mysql_config.getString("password");
                String hostname = mysql_config.getString("hostname");
                int port = mysql_config.getInt("port");
                String db = mysql_config.getString("name");
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + db, username, password);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
