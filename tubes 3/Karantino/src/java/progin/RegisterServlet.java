package progin;

import progin.RegisterDAO;
import progin.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import progin.UserBean;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
         try
        {
            String day = request.getParameter("tanggal");
            String yr = request.getParameter("tahun");
            String mon = request.getParameter("bulan");
            
            int d = Integer.parseInt(day);
            int m = Integer.parseInt(mon);
            int y = Integer.parseInt(yr);
            
            java.sql.Date birthdate = new java.sql.Date(y-1900, m, d-1);
            
            UserBean user = new UserBean();
            RegisterDAO reg = new RegisterDAO();
            
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setNamalengkap(request.getParameter("namalengkap"));
            user.setAvatar("testdir");
            user.setEmail(request.getParameter("email"));
            user.setDate(birthdate);
            
            user = reg.register(user);

            if (user.isValid())
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", user);
                response.sendRedirect("profil.jsp"); //logged in page
            }
            else
            {
                response.sendRedirect("invalidRegister.jsp");
            }
        }catch(Throwable e){
            System.out.println(e);
        }
    }
}
