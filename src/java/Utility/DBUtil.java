package Utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
                Properties prop = new Properties();
                InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("/db.properties");
                prop.load(inputStream);
                
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.getMessage();
            } catch (SQLException e) {
                e.getMessage();
            } catch (FileNotFoundException e) {
                e.getMessage();
            } catch (IOException e) {
                e.getMessage();
            } finally {
                return connection;
            }
        }
    }
}
