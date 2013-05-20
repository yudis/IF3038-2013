/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import java.sql.*;
/**
 *
 * @author Aisyah
 */
public class ConnectSql {
    public static void main (String[] args){
        String url ="jdbc:mysql://localhost:3306/";
        String dbname = "progin";
        String driver="com.mysql.jdbc.Driver";
        String username= "root";
        String password="";
        
        try{
        Class.forName(driver).newInstance();
        Connection conn = DriverManager.getConnection(url+dbname,username,password);
        
        conn.close();
        }
        catch (Exception e)
        {
        e.printStackTrace();
        }
        
        }
    
    
    }
    
    

