package progin;

import progin.EditDAO;
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

public class EditServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, java.io.IOException 
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
            
            HttpSession session = request.getSession(true);
            
            UserBean user = ((UserBean)session.getAttribute("currentSessionUser"));
            EditDAO edit = new EditDAO();
            
            user.setNamalengkap(request.getParameter("edname"));
            user.setPassword(request.getParameter("edpass"));
            user.setEmail(request.getParameter("edmail"));
            user.setDate(birthdate);

            user = edit.edit(user);

            if (user.isValid())
            {
                
                session.setAttribute("currentSessionUser", user);
                response.sendRedirect("profil.jsp"); //logged in page
            }
            else
            {
                response.sendRedirect("invalidLogin.jsp");
            }
        }catch(Throwable e){
            System.out.println(e);
        }
    }
}
