package tubes3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tubes3Connection {

    String url;
    String user;
    String password;

    public Tubes3Connection() {
        this("jdbc:mysql://localhost:3306/progin_439_13510081", "progin", "progin");
    }

    public Tubes3Connection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Error 01 : " + e.getLocalizedMessage());
        } catch (SQLException e) {
            System.out.println("Error 02 : " + e.getMessage());
        } finally {
            return con;
        }
    }

    public ResultSet coba(Connection con, String query) throws SQLException {
        Statement pst = con.createStatement();
        return pst.executeQuery(query);
    }
    
    public int nonReturnQuery(Connection con, String query) throws SQLException {
        Statement pst = con.createStatement();
        return pst.executeUpdate(query);
    }
}
