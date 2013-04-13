package progin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import model.UserBean;
//import util.ConnectionManager;

public class EditDAO {
   private Connection Con;
    static ResultSet rs = null;
    
 public EditDAO()
 {
    Con = ConnectionManager.getConnection();
 }

 public UserBean edit(UserBean bean)
    { 
        try
        {
            //connect to DB
            //currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = Con.prepareStatement("update user set password=?, namalengkap=?, tanggallahir=?, email=?" +
                                   "where username=?");
   
            ps.setString(1, bean.getPassword());
            ps.setString(2, bean.getNamalengkap());
            ps.setDate(3, bean.getDate());
            ps.setString(4, bean.getEmail());
            ps.setString(5, bean.getUsername());
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
