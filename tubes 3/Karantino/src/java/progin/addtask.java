/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chidx
 */
@WebServlet(name = "addtask", urlPatterns = {"/addtask"})
public class addtask extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addtask</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addtask at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String koma = ",";
        PrintWriter out = response.getWriter();
        String namatask, attach, asignee, tag, user, category, temp;
        int tanggal, bulan, tahun;   
        String url = "jdbc:mysql://localhost:3306/progin_405_13510074";
        Connection connection;
        try{
            user = "dummy";
            category = "dummy";
            namatask = request.getParameter("nama");
            HttpSession session = request.getSession(true);
            
            asignee = request.getParameter("asignee");
            tag = request.getParameter("tag");
            tanggal = Integer.parseInt(request.getParameter("tanggal"));
            bulan = Integer.parseInt(request.getParameter("bulan"));
            tahun = Integer.parseInt(request.getParameter("tahun"));
            if (namatask == null || tag == null || tanggal == 0 || bulan == 0 || tahun == 0 || asignee == null ){
                out.println("<html>");
                out.println("<head>");
                out.println("<script type='text/javascript'>");
                out.println("function redirect(){");
                //out.println("alert('Task failed');");
                out.println("window.location = 'newtask.jsp';");
                out.println("}");
                out.println("redirect();");
                out.println("</script>");
                out.println("</head>");
                out.println("<body>");
                out.println("</body>");
                out.println("</html>");
            } else {
                /*tanggal = request.getParameter("tanggal");
                bulan = request.getParameter("bulan");
                tahun = request.getParameter("tahun");
                tanggal = tahun + "-" + bulan + "-" + tanggal;
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");*/
                session.setAttribute("namatask", namatask);
                String kalender = ""+tahun+"-"+bulan+"-"+tanggal;
                Date myDate = java.sql.Date.valueOf(kalender);
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, "root", "");
                PreparedStatement pst = connection.prepareStatement("insert into tugas values (?,?,?,?,?)");
                pst.setString(1, user);
                pst.setString(2, namatask);
                pst.setDate(3, myDate);
                pst.setString(4, category);
                pst.setString(5, "0");
                int i1 = pst.executeUpdate();
                while (asignee.indexOf(koma) > 0){
                    temp = asignee.substring(0, asignee.indexOf(','));
                    asignee = asignee.substring(asignee.indexOf(',')+1);
                    pst = connection.prepareStatement("insert into asigner values (?,?,?)");
                    pst.setString(1, user);
                    pst.setString(2, namatask);
                    pst.setString(3, temp);
                    i1 = pst.executeUpdate();
                }
                pst = connection.prepareStatement("insert into asigner values (?,?,?)");
                pst.setString(1, user);
                pst.setString(2, namatask);
                pst.setString(3, asignee);
                int i2 = pst.executeUpdate();

                while (tag.indexOf(koma) > 0){
                    temp = tag.substring(0, tag.indexOf(','));
                    tag = tag.substring(tag.indexOf(',')+1);
                    pst = connection.prepareStatement("insert into tag values (?,?,?)");
                    pst.setString(1, user);
                    pst.setString(2, namatask);
                    pst.setString(3, temp);
                    i2 = pst.executeUpdate();
                }
                pst = connection.prepareStatement("insert into tag values (?,?,?)");
                pst.setString(1, user);
                pst.setString(2, namatask);
                pst.setString(3, tag);
                int i3 = pst.executeUpdate();
                if ((i1 != 0) && (i2 != 0) && (i3 != 0)){
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script type='text/javascript'>");
                    out.println("function redirect(){");
                    out.println("alert('Task added');");
                    out.println("window.location = 'rinciantugas.jsp';");
                    out.println("}");
                    out.println("redirect();");
                    out.println("</script>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("</body>");
                    out.println("</html>");
                    //out.println("success");
                } else {
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script type='text/javascript'>");
                    out.println("function redirect(){");
                    out.println("alert('Task failed to be added');");
                    out.println("window.location = 'newtask.jsp';");
                    out.println("}");
                    out.println("redirect();");
                    out.println("</script>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("</body>");
                    out.println("</html>");
                    //out.println("fail");
                }
                pst.close();
                connection.close();
            }
        } catch (Exception e){
           e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
