package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.json.*;

public class dbConnection {
    
    public dbConnection() {}
    
    public static Connection getConnection () throws SQLException, ClassNotFoundException {
//        String jsonMsg = "{\"mysql-5.1\":[{\"name\":\"mysql-4f700\",\"label\":\"mysql-5.1\",\"plan\":\"free\",\"tags\":[\"mysql\",\"mysql-5.1\",\"relational\"],\"credentials\":{\"name\":\"progin_471_13510008\",\"hostname\":\"localhost\",\"host\":\"localhost\",\"port\":3306,\"user\":\"root\",\"username\":\"root\",\"password\":\"\"}}]}";
        String jsonMsg = java.lang.System.getenv("VCAP_SERVICES");
        JSONTokener tokener = new JSONTokener(jsonMsg);
        JSONObject root = new JSONObject(tokener);

        JSONArray mysql51 = root.getJSONArray("mysql-5.1");            
        JSONObject credentials = mysql51.getJSONObject(0).getJSONObject("credentials");
        String db_username = credentials.getString("username");
        String db_password = credentials.getString("password");
        String db_hostname = credentials.getString("hostname");
        int db_port = credentials.getInt("port");
        String db_name = credentials.getString("name");    

        String connectionURL = "jdbc:mysql://"+db_hostname+":"+db_port+"/"+db_name;
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(connectionURL,db_username,db_password);
    }
}
