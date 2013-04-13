/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectDB;

/**
 *
 * @author Yusuf Ardi
 */
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;

public class ConnectDB {
    public static final String dbURL = "jdbc:mysql://localhost:3306/progin_405_13510100";
    public static final String dbUsername = "root";
    public static final String dbPassword = "";
    
    /*
     * query sql dengan hasil, contoh select
     */
    
    public static String[][] getHasilQuery(String query) throws ServletException, SQLException {
        Connection conn = null;
        Statement st = null;
        
        ResultSet rs = null;
        ResultSetMetaData metadata;
        
        int jumlah_kolom = 0;
        ArrayList<String[]> data = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        
            conn = DriverManager.getConnection(ConnectDB.dbURL, ConnectDB.dbUsername, ConnectDB.dbPassword);
            st = conn.createStatement();
            
            rs = st.executeQuery(query);
            metadata = rs.getMetaData();
            jumlah_kolom = metadata.getColumnCount();
            
            data = new ArrayList<String[]>();

            while (rs.next()) {
                String[] row = new String[jumlah_kolom];
                for (int i = 0; i < jumlah_kolom; i++) {
                    row[i] = rs.getString(i + 1);
                }
                data.add(row);
            }
        } catch (SQLException e) {
            throw new ServletException("Servlet Could not display records.", e);
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC Driver not found.", e);
        } finally {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (st != null) {
                st.close();
                st = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        }
        String[][] hasil = new String[data.size()][jumlah_kolom];
        for (int i = 0; i < data.size(); i++) {
            System.arraycopy(data.get(i), 0, hasil[i], 0, jumlah_kolom);
        }

        return hasil;
    }
    
    /*
     * Untuk query yang ga ada hasilnya. Seperti INSERT
     */
    public static void RunQuery(String query) throws ServletException, SQLException {
        Connection conn = null;
        Statement st = null;
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            // connection
            conn = DriverManager.getConnection(ConnectDB.dbURL, ConnectDB.dbUsername, ConnectDB.dbPassword);
            
            // statement
            st = conn.createStatement();
            st.executeUpdate(query);
            
        } catch (SQLException e) {
            throw new ServletException("Servlet Could not display records.", e);
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC Driver not found.", e);
        } finally {
            if (st != null) {
                st.close();
                st = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        }
        
    }
}
