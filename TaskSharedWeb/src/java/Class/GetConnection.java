/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class GetConnection {

    Connection conn = null;
    String userName;
    String password;
    String serverName;

    public GetConnection(String userName, String password, String serverName) {
        this.userName = userName;
        this.password = password;
        this.serverName = serverName;
    }
    
    public Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://"+serverName, userName, password);
        return conn;
    }
}
