
package id.ac.itb.todolist.ajax;

import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Create", urlPatterns = {"/ajax/Create"})
public class Create extends HttpServlet {

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
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();
        User currentUser = (User) session.getAttribute("user");
        TugasDao tugas = new TugasDao();
        try {
            tugas.AddTask(request.getParameter("namatask"), Date.valueOf(request.getParameter("deadline")), currentUser.getUsername(), Integer.parseInt(request.getParameter("namakategori")));
            String[] assigneeArr= request.getParameter("assigneeI").split("[ ,]+");
            for (int i = 0; i < assigneeArr.length; i++)
            {
                if(!assigneeArr[i].equals(currentUser.getUsername()) && userDao.isAvailableUsername(assigneeArr[i]))
                {
                    tugas.addAssignee(tugas.getNewestId(),assigneeArr[i]);
                }
            }
            String[] tags= request.getParameter("tag").split("[ ,]+");
            for (int i = 0; i < tags.length; i++)
            {
                tugas.addTag(tugas.getNewestId(),tags[i]);
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
