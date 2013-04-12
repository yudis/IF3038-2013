/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chidx
 */
public class ConnectionManager {
    static Connection con;
    static String url;
    
    public static Connection getConnection(){
        try{
            url = "jdbc:mysql:" + "//localhost:27456/progin_405_13510074";
            Class.forName("com.mysql.jdbc.Driver");
            try{
                con = DriverManager.getConnection(url, "root", "");
            } catch (SQLException e){
                System.out.println("SQL Error");
            }
        } catch (ClassNotFoundException ex){
            System.out.println(ex);
        }
        return con;
    }
}
