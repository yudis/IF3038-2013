package service;

import com.sun.mail.iap.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "user_service")
public class user_service {

    @WebMethod(operationName = "login_check")
    public String login_check(
    @WebParam(name = "login_username") String login_username, 
    @WebParam(name = "login_password") String login_password)  {
        String response = "false";
        if (login_username != null) {
            System.out.println("login_username : " + login_username);
        }
        else {
            System.out.println("username NULL");
        }
        if (login_password != null) {
            System.out.println("login_password : " + login_password);
        }
        else {
            System.out.println("password NULL");
        }
        
        try {
            dbConnection dbConnector = new dbConnection();
            Connection conn = dbConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT username, full_name FROM user WHERE username=? AND password=?;");
            stmt.setString(1, login_username);
            stmt.setString(2, login_password);
            ResultSet rs = stmt.executeQuery();
            rs.beforeFirst();
            String fullname = "";
            while (rs.next()) {
                fullname = rs.getString(2);
            }
            if (!fullname.equals("")) { //berhasil login
                response = fullname;
            }             
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return response;
        }
    }
}
