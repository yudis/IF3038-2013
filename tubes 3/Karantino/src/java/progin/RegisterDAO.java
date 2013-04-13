package progin;

import java.text.*;
import java.util.*;
import java.sql.*;

//import util.ConnectionManager;
//import model.UserBean;

public class RegisterDAO {
    private Connection Con;
    static ResultSet rs = null;
    static ResultSet rse = null;
    
 public RegisterDAO()
 {
    Con = ConnectionManager.getConnection();
 }

 public UserBean register(UserBean bean)
    {
        //preparing some objects for connection 
        Statement stmt = null;
        Statement stmt2 = null;
        
        String username = bean.getUsername();
        String email = bean.getEmail();
        
        String usernameQuery = "SELECT * FROM user WHERE username='" + username + "'";
        String emailQuery = "SELECT * FROM user WHERE email='" + username + "'";
        
        try
        {
             stmt = Con.createStatement();
             stmt2 = Con.createStatement();
             rs = stmt.executeQuery(usernameQuery);
             rse = stmt2.executeQuery(emailQuery);
             boolean more = rs.next();
             boolean more2 = rse.next();
             
             if(more || more2){
                 bean.setValid(false);
             }
             else if (!more || !more2){

            PreparedStatement ps = Con.prepareStatement("insert into user (username, password, namalengkap, tanggallahir, email, avatar) values (?, ?, ?, ?, ?, ?)");
   
            ps.setString(1, bean.getUsername());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getNamalengkap());
            ps.setDate(4, bean.getDate());
            ps.setString(5, bean.getEmail());
            ps.setString(6, bean.getAvatar());
            ps.executeUpdate();
            
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
            if (rse!=null)
            {
               try
                {
                    rse.close();
                }catch (Exception e){
                    rse = null;
                }
            }
            

            if (Con != null)
            {
                try
                {
                    Con.close();
                }catch(Exception e){}
                    Con = null; 
            }
        }
        return bean;       
    }
}
