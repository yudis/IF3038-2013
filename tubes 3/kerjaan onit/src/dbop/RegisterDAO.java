package dbop;

import java.text.*;
import java.util.*;
import java.sql.*;

import util.ConnectionManager;
import model.UserBean;

public class RegisterDAO {
    private Connection Con;
    static ResultSet rs = null;
    
 public RegisterDAO()
 {
    Con = ConnectionManager.getConnection();
 }

 public UserBean register(UserBean bean)
    {
        //preparing some objects for connection 

        
        try
        {
            //connect to DB
            //currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = Con.prepareStatement("insert into user (username, password, namalengkap, tanggallahir, email, avatar) values (?, ?, ?, ?, ?, ?)");
   
            ps.setString(1, bean.getUsername());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getNamalengkap());
            ps.setDate(4, bean.getDate());
            ps.setString(5, bean.getEmail());
            ps.setString(6, bean.getAvatar());
            ps.executeUpdate();
            
            bean.setValid(true);
        
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
