package Utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.json.JSONObject;
/**
 * Class to connect to database.
 *
 * @author Jeremy Joseph Hanniel - 13510026
 */
public class DBUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                String VCAP_SERVICES = "{\"mysql-5.1\":[{\"name\":\"test\",\"label\":\"mysql-5.1\",\"plan\":\"free\",\"tags\":[\"mysql\",\"mysql-5.1\",\"relational\",\"mysql-5.1\",\"mysql\"],\"credentials\":{\"name\":\"test\",\"hostname\":\"localhost\",\"host\":\"localhost\",\"port\":3306,\"user\":\"progin\",\"username\":\"progin\",\"password\":\"progin\"}}]}";
                //String VCAP_SERVICES = java.lang.System.getenv("VCAP_SERVICES");
                System.out.println(VCAP_SERVICES);
                JSONObject servicesJson = new JSONObject(VCAP_SERVICES);
                System.out.println("aa");
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
                e.getMessage();
                e.printStackTrace();
            } catch (SQLException e) {
                e.getMessage();
                e.printStackTrace();
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
            } finally {
                return connection;
            }
        }
    }
}
