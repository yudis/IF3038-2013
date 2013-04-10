/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.ajax;

import id.ac.itb.todolist.dao.TugasDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        List<List<String>> result = tugasdao.getTugas(request.getParameter("id"));
     
//////////
        out.println("<h2> </h2>");
        out.println("<h1> Undone </h1>");

        if (result != null)
        {
                int i = 0;
                while(true)
                {
                        String lastKN = result.get(i).get(1);

                        out.println("                                  <h2>" + result.get(i).get(3) + "</h2>");
                        do {						
                                out.println("					<div class=\"tugas\">");
                                out.println("					<div><a href=\"tugas.jsp?id=" + result.get(i).get(2) + "\">" + result.get(i).get(4) + "</a></div>");
                                out.println("						 <div>Submission: <strong>" + result.get(i).get(6) + "</strong></div>");
                                out.println("						 <div>");
                                out.println("							 Tags: ");
                                out.println("							 <ul class=\"tag\">");


                                String lastTN = result.get(i).get(2);

                                do {
                                        out.println("							<li>" + result.get(i).get(6) + "</li>");
                                        i++;
                                } while((i < result.size()) && (lastTN.equals(result.get(i).get(2))));
                                out.println("							  </ul>");
                                out.println("						 </div>");
                                out.println("				</div>");
                                
                                if (i >= result.size()){
                                    break;
                                }
                        } while((lastKN.equals(result.get(i).get(1))));
 
                        if ((i < result.size())){
                            if ("1".equals(result.get(i).get(0))){
                                break;
                            }
                        } else {
                            break;
                        }
                }
                out.println(" <h2> </h2>");
                out.println("<h1> Done </h1>");

                if (i < result.size()){

                        while(true)
                        {
                                String lastKN = result.get(i).get(1);

                                out.println("				<h2>" + result.get(i).get(3) + "</h2>");
                                do {						
                                        out.println("				<div class=\"tugas\">");
                                        out.println("					<div><a href=\"#\">" + result.get(i).get(4) + "</a></div>");
                                        out.println("						 <div>Submission: <strong>" + result.get(i).get(5) + "</strong></div>");
                                        out.println("						 <div>");
                                        out.println("							 Tags: ");
                                        out.println("								 <ul class=\"tag\">");								 

                                        String lastTN = result.get(i).get(2);
                                        do {
                                                out.println("							<li>" + result.get(i).get(6) + "</li>");
                                                i++;
                                        } while((i < result.size()) && (lastTN.equals(result.get(i).get(2))));
                                        out.println("							  </ul>");
                                        out.println("						 </div>");
                                        out.println("				</div>");
                                        if (i >= result.size()){
                                            break;
                                        }                                        
                                } while((lastKN.equals(result.get(i).get(1))));
                                if ((i < result.size())){
                                    if ("1".equals(result.get(i).get(0))){
                                        break;
                                    }
                                } else {
                                    break;
                                }
                        }
                } else {

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
