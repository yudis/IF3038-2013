package progin;

import java.text.*;
import java.util.*;
import java.sql.*;

//import util.ConnectionManager;
//import model.UserBean;

public class UserDAO {
    static Connection currentCon = null;
    static ResultSet rs = null;
    static PreparedStatement ps = null;
    static ResultSet rsReg = null;
    
    public static UserBean login(UserBean bean)
    {
        //preparing some objects for connection 
        Statement stmt = null;
        
        String username = bean.getUsername();
        String password  =  bean.getPassword();
        
        String searchQuery = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "'";
        // "System.out.println" prints in the console; Normally used to trace the process
        System.out.println("Your user name is " + username);          
        System.out.println("Your password is " + password);
        System.out.println("Query: "+searchQuery);
        
        try
        {
            //connect to DB
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();
            
            // if user does not exist set the isValid variable to false

            if (!more)
            {
                System.out.println("Sorry, you are not a registered user! Please sign up first");
                bean.setValid(false);
            }
            //if user exists set the isValid variable to true
            else if (more)
            {
                 String userName = rs.getString("username");
                 String passWord = rs.getString("password");               
                 String namaLengkap = rs.getString("namalengkap");
                 String eMail = rs.getString("email");
                 String aVatar = rs.getString("avatar");
                 java.sql.Date dOb = rs.getDate("tanggallahir");
	    
                 System.out.println("Welcome " + username);
                 bean.setUsername(userName);
                 bean.setPassword(passWord);
                 bean.setNamalengkap(namaLengkap);
                 bean.setEmail(eMail);
                 bean.setAvatar(aVatar);
                 bean.setDate(dOb);
                 bean.setValid(true);
            }           
        }catch (Exception e){
            System.out.println("Log In failed: An Exception has occurred!" + e);
        }
        
        //some exception handling
        finally 
        {
            if (rs!=null)
            {
                try
                {
                    rs.close();
                }catch (Exception e){
                    rs = null;
                }
            }
            
            if (stmt!=null)
            {
                try
                {
                    stmt.close();
                }catch (Exception e){}
                 stmt = null;         
            }
            
            if (currentCon != null)
            {
                try
                {
                    currentCon.close();
                }catch(Exception e){}
                    currentCon = null; 
            }
        }
        return bean;
    }
    
    /*public static UserBean register(UserBean bean)
    {
             
        try
        {
            //connect to DB
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement("INSERT INTO user VALUES(?,?,?,?,?,?)");
   
            ps.setString(1, bean.getUsername());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getNamalengkap());
            ps.setString(4, bean.getDate().toString());
            ps.setString(5, bean.getEmail());
            ps.setString(6, bean.getAvatar());
            ps.executeUpdate();
            
            bean.setValid(true);     
        }catch(Exception e){
            System.out.println("Log In failed: An Exception has occurred!" + e);
        }
        
        //some exception handling      
            if (currentCon != null)
            {
                try
                {
                    currentCon.close();
                }catch(Exception ex){}
                    currentCon = null; 
            }
        
        return bean;       
    }*/
}
