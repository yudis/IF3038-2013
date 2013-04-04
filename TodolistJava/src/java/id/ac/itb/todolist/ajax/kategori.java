
package id.ac.itb.todolist.ajax;

import com.google.gson.JsonObject;
import id.ac.itb.todolist.dao.CategoryDao;
import id.ac.itb.todolist.model.Category;
import id.ac.itb.todolist.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class kategori extends HttpServlet {

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
        response.setContentType("application/json");
        
        PrintWriter out = response.getWriter();
        out.println("tes");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        try {
            CategoryDao category =new CategoryDao();
            ArrayList<Category> result = new ArrayList<Category>();
            ArrayList<Category> result2 = new ArrayList<Category>();
            result=category.getAllCategory();
            result2=category.getAssigneeCat(currentUser.getUsername());
            for (int i = 0; i < result.size(); i++){
                ArrayList<String> users= category.getUser(result.get(i).getId());
                for (int n = 0; n < users.size(); n++){
                    if(users.get(n) == null ? currentUser.getUsername() == null : users.get(n).equals(currentUser.getUsername()))
                    {
                        int id_kategori=result.get(i).getId();
                        out.println("<li><a name='"+id_kategori+"' href=\"\" onclick=\"loadtugas('"+id_kategori+"');setChosen('"+id_kategori+"'); return false;\" >"); 
                        out.println(result.get(i).getNama());
                        out.println("</a></li>");
                        break;
                    }
                }
            }
            for(int i=0;i < result2.size(); i++){
                int id_kategori=result2.get(i).getId();
		out.println("<li><a name='"+id_kategori+"' href=\"\" onclick=\"loadtugas('"+id_kategori+"');setChosen('"+id_kategori+"'); return false;\" >"); 
                out.println(result2.get(i).getNama());
                out.println("</a></li>");
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
