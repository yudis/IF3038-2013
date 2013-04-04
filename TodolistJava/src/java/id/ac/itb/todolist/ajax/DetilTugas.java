package id.ac.itb.todolist.ajax;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import id.ac.itb.todolist.model.Tugas;
import id.ac.itb.todolist.model.User;
import id.ac.itb.todolist.dao.TugasDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DetilTugas extends HttpServlet {

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
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        User currentUser = (User) session.getAttribute("user");
                        
        String updateStr = request.getParameter("update");
        long update = 0;
        if (updateStr != null) update = Long.parseLong(updateStr);
        
        String idTugasStr = request.getParameter("id_tugas");
        int idTugas = 0;
        if (idTugasStr != null) idTugas = Integer.parseInt(idTugasStr);
        
        String priviledgeStr = request.getParameter("priviledge");
       
        JsonObject jObject = null;
        
        if (currentUser != null && idTugasStr != null) {
            TugasDao tugasDao = new TugasDao();
            Tugas tugas = null;

            if (!tugasDao.isUpdated(idTugas, update)) {
                tugas = tugasDao.getTugas(idTugas, true, true, true);
                jObject = (JsonObject) tugas.toJsonElement();
            }
        } else {
            jObject = new JsonObject();
            jObject.addProperty("responseStatus", 400);
            jObject.addProperty("responseTime", System.currentTimeMillis() / 1000L);
            jObject.addProperty("message", "Bad request");
        }
        
        Gson gson = new Gson();
        out.print(jObject.toString());
        out.close();
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
