/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.ajax;

import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.model.Tugas;
import id.ac.itb.todolist.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Felix
 */
public class profilecategory extends HttpServlet {

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
        PrintWriter out= response.getWriter();
        TugasDao tugasdao = new TugasDao();
        HttpSession session = request.getSession();
        User loginedUser = (User) session.getAttribute("user");
        ArrayList<Tugas> result = new ArrayList<Tugas>();
        result = (ArrayList<Tugas>) tugasdao.getTugas(request.getParameter("id"));
     
//////////
        out.println("<h2> </h2>");
        out.println("<h1> Undone </h1>");
        out.println("<h2> </h2>");
        if (result != null)
        {
                int i = 0;
                while((i < result.size()) && !(result.get(i).isStatus()))
                {
                        int lastKN = result.get(i).getKategori().getId();

                        out.println("                                  <h2>" + result.get(i).getKategori().getNama() + "</h2>");
                        do {						
                                out.println("					<div class=\"tugas\">");
                                out.println("					<div><a href=\"tugas.jsp?id=" + result.get(i).getId() + "\">" + result.get(i).getNama() + "</a></div>");
                                out.println("						 <div>Submission: <strong>" + result.get(i).getTglDeadline() + "</strong></div>");
                                out.println("						 <div>");
                                out.println("							 Tags: ");
                                out.println("							 <ul class=\"tag\">");

                                for (int j = 0; j < ((ArrayList<String>) result.get(i).getTags()).size(); j++){
                                        out.println("							<li>" + ((ArrayList<String>) result.get(i).getTags()).get(j) + "</li>");
                                }
                                out.println("							  </ul>");
                                out.println("						 </div>");

                                if ((request.getParameter("id")).equals(loginedUser.getUsername())){
                                    if (!result.get(i).isStatus()){
                                            out.println("<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value," + result.get(i).getId() +")\" value=\"" + result.get(i).isStatus() + "\"></div>");
                                    } else {
                                            out.println("<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value," + result.get(i).getId() + ")\" value=\"" + result.get(i).isStatus() + "\" checked></div>");
                                    }          
                                } else {
                                    if (!result.get(i).isStatus()){
                                            out.println("<div>Status : Belum Selesai</div>");
                                    } else {
                                            out.println("<div>Status : Selesai</div>");
                                    }          
                                }
                                out.println("				</div>");
                                
                                i++;

                        } while((i < result.size()) && (lastKN == result.get(i).getKategori().getId()) && !(result.get(i).isStatus()));

                }
                out.println(" <h2> </h2>");
                out.println("<h1> Done </h1>");
                out.println("<h2> </h2>");
                while((i < result.size()) && (result.get(i).isStatus()))
                {
                        int lastKN = result.get(i).getKategori().getId();

                        out.println("                                  <h2>" + result.get(i).getKategori().getNama() + "</h2>");
                        do {						
                                out.println("					<div class=\"tugas\">");
                                out.println("					<div><a href=\"tugas.jsp?id=" + result.get(i).getId() + "\">" + result.get(i).getNama() + "</a></div>");
                                out.println("						 <div>Submission: <strong>" + result.get(i).getTglDeadline() + "</strong></div>");
                                out.println("						 <div>");
                                out.println("							 Tags: ");
                                out.println("							 <ul class=\"tag\">");

                                for (int j = 0; j < ((ArrayList<String>) result.get(i).getTags()).size(); j++){
                                        out.println("							<li>" + ((ArrayList<String>) result.get(i).getTags()).get(j) + "</li>");
                                }
                                out.println("							  </ul>");
                                out.println("						 </div>");
                                
                                if ((request.getParameter("id")).equals(loginedUser.getUsername())){
                                    if (!result.get(i).isStatus()){
                                            out.println("<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value," + result.get(i).getId() +")\" value=\"" + result.get(i).isStatus() + "\"></div>");
                                    } else {
                                            out.println("<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value," + result.get(i).getId() + ")\" value=\"" + result.get(i).isStatus() + "\" checked></div>");
                                    }          
                                } else {
                                    if (!result.get(i).isStatus()){
                                            out.println("<div>Status : Belum Selesai</div>");
                                    } else {
                                            out.println("<div>Status : Selesai</div>");
                                    }          
                                }

                                out.println("				</div>");
                                
                                i++;

                        } while((i < result.size()) && (lastKN == result.get(i).getKategori().getId()) && (result.get(i).isStatus()));

                }
        
        }        
//////////        
        
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
        //processRequest(request, response);
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
