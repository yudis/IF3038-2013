/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gilang
 */
@WebServlet(name="toRincianTugas", urlPatterns={"/toRincianTugas"})
public class toRincianTugas extends HttpServlet{
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet taskdetail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet taskdetail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
            PrintWriter out = response.getWriter();
        try{
            String param = request.getParameter("value1");
            request.setAttribute("namatask", param);
             out.println("<html>");
             out.println("<head>");
             out.println("<script type='text/javascript'>");
             out.println("function redirect(){");
             out.println("window.location = 'rinciantugas.jsp';");
             out.println("}");
             out.println("redirect();");
             out.println("</script>");
             out.println("</head>");
             out.println("<body>");
             out.println("</body>");
             out.println("</html>");
            
        }
        catch(Exception e){
             out.println("Masuk ke Exception toRincianTugas");
        }
        
}
}