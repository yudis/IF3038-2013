package progin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import progin.UserBean;
import progin.UserDAO;
import progin.ConnectionManager;

public class LoginServlet extends HttpServlet {

public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, java.io.IOException 
{
    try
    {
        UserBean user = new UserBean();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        
        user = UserDAO.login(user);
        
        if (user.isValid())
        {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser", user);
            response.sendRedirect("dashboard.jsp"); //logged in page
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
