
package id.ac.itb.todolist.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import id.ac.itb.todolist.dao.CategoryDao;
import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CreateKategori", urlPatterns = {"/ajax/CreateKategori"})
public class CreateKategori extends HttpServlet {

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
        User currentUser = (User) session.getAttribute("user");
        String q = request.getParameter("q");
        String[] Arr = request.getParameter("Arr").split("[ ,]+");
        try {
            CategoryDao category = new CategoryDao();
            UserDao user = new UserDao();
            category.NewKategori(q,currentUser.getUsername());
            for (int i = 0; i < Arr.length; i++)
            {
                if(!Arr[i].equals(currentUser.getUsername()) && user.isAvailableUsername(Arr[i]))
                {
                    category.addNewestCoordinator(Arr[i]);
                }
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
