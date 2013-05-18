package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.json.JSONObject;

public class DBUtil {
	public static Connection getConnection() {
        Connection connection = null;

        try {
            String VCAP_SERVICES = "{\"mysql-5.1\":[{\"name\":\"progin\",\"label\":\"mysql-5.1\",\"plan\":\"free\",\"tags\":[\"mysql\",\"mysql-5.1\",\"relational\",\"mysql-5.1\",\"mysql\"],\"credentials\":{\"name\":\"progin_405_13510056\",\"hostname\":\"localhost\",\"host\":\"localhost\",\"port\":3306,\"user\":\"progin\",\"username\":\"progin\",\"password\":\"progin\"}}]}";
            //String VCAP_SERVICES = java.lang.System.getenv("VCAP_SERVICES");
            JSONObject servicesJson = new JSONObject(VCAP_SERVICES);
            JSONObject mysqlConfig = servicesJson.getJSONArray("mysql-5.1").getJSONObject(0).getJSONObject("credentials");

            String driver = "com.mysql.jdbc.Driver";
            String username = mysqlConfig.getString("username");
            String password = mysqlConfig.getString("password");
            String hostname = mysqlConfig.getString("hostname");
            Integer port = mysqlConfig.getInt("port");
            String dbname = mysqlConfig.getString("name");

            String url = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname;

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
        
    }
}
