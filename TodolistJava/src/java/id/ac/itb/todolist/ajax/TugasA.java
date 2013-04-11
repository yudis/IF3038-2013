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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "TugasA", urlPatterns = {"/ajax/TugasA"})
public class TugasA extends HttpServlet {

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
        HttpSession session = request.getSession();
        String q = request.getParameter("q");
        User currentUser = (User) session.getAttribute("user");
        ArrayList<Tugas> result;
        TugasDao tugasDao=new TugasDao();
        try {
            if("".equals(q) || "0".equals(q))
            {
                result = tugasDao.getAllTugas();
                for (int i = 0; i < result.size(); i++)
                {
                    if(result.get(i).getPemilik().getUsername() == null ? currentUser.getUsername() == null : result.get(i).getPemilik().getUsername().equals(currentUser.getUsername()))
                    {
                        out.println("<h2>"+result.get(i).getKategori().getNama()+"</h2>");
				out.println("<div class=\"tugas\">");
					out.println("<div><a href=\"tugas.jsp?id="+result.get(i).getId()+"\">"+result.get(i).getNama()+"</a></div>");
					out.println("<div>Deadline: <strong>"+result.get(i).getTglDeadline()+"</strong></div>");
					out.println("<div>");
						out.println("Tags: ");
						out.println("<ul class=\"tag\">");
							for (int n = 0; n < result.get(i).getTags().size(); n++)
							{
								out.println("<li>"+result.get(i).getTags().toArray()[n].toString()+"</li>");
							}
						out.println("</ul>");
					out.println("</div>");   
					if(!result.get(i).isStatus())
						{
							out.println("<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,"+result.get(i).getId()+")\" value=\""+0 +"\"></div>");
						}
					else if(result.get(i).isStatus())
						{
							out.println("<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,"+result.get(i).getId()+")\" value=\""+1+"\" checked></div>");
						}
					out.println("<button id='deleteTugas' onclick='setChosenT(\""+result.get(i).getId()+"\");deleteTask()'>Delete Tugas</button>");
				out.println("</div>");
                    }
                    else
                    {
                        ArrayList<User> assignees=new ArrayList<User>(result.get(i).getAssignees());
                        for (int x = 0; x < assignees.size(); x++)
                        {
                            if((result.get(i).getPemilik().getUsername() == null ? assignees.get(x).getUsername() != null : !result.get(i).getPemilik().getUsername().equals(assignees.get(x).getUsername())) && assignees.get(x).getUsername() == null ? currentUser.getUsername() == null : assignees.get(x).getUsername().equals(currentUser.getUsername()))
                            {
                                out.println("<h2>"+result.get(i).getKategori().getNama()+"</h2>");
				out.println("<div class=\"tugas\">");
					out.println("<div><a href=\"tugas.jsp?id="+result.get(i).getId()+"\">"+result.get(i).getNama()+"</a></div>");
					out.println("<div>Deadline: <strong>"+result.get(i).getTglDeadline()+"</strong></div>");
					out.println("<div>");
						out.println("Tags: ");
						out.println("<ul class=\"tag\">");
							for (int n = 0; n < result.get(i).getTags().size(); n++)
							{
								out.println("<li>"+result.get(i).getTags().toArray()[n].toString()+"</li>");
							}
						out.println("</ul>");
					out.println("</div>");   
					if(!result.get(i).isStatus())
						{
							out.println("<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,"+result.get(i).getId()+")\" value=\""+0+"\"></div>");
						}
					else if(result.get(i).isStatus())
						{
							out.println("<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,"+result.get(i).getId()+")\" value=\""+1+"\" checked></div>");
						}
				out.println("</div>");
                            }
                        }
                    }
                }
            }
            else
            {
                result = tugasDao.getAllTugas();
                for (int i = 0; i < result.size(); i++)
                {
                    if(result.get(i).getKategori().getId()==Integer.parseInt(q))
                    {
                        if(result.get(i).getPemilik().getUsername() == null ? currentUser.getUsername() == null : result.get(i).getPemilik().getUsername().equals(currentUser.getUsername()))
                        {
                            out.println("<h2>"+result.get(i).getKategori().getNama()+"</h2>");
                                    out.println("<div class=\"tugas\">");
                                            out.println("<div><a href=\"tugas.jsp?id="+result.get(i).getId()+"\">"+result.get(i).getNama()+"</a></div>");
                                            out.println("<div>Deadline: <strong>"+result.get(i).getTglDeadline()+"</strong></div>");
                                            out.println("<div>");
                                                    out.println("Tags: ");
                                                    out.println("<ul class=\"tag\">");
                                                            for (int n = 0; n < result.get(i).getTags().size(); n++)
                                                            {
                                                                    out.println("<li>"+result.get(i).getTags().toArray()[n].toString()+"</li>");
                                                            }
                                                    out.println("</ul>");
                                            out.println("</div>");   
                                            if(!result.get(i).isStatus())
                                                    {
                                                            out.println("<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,"+result.get(i).getId()+")\" value=\""+0+"\"></div>");
                                                    }
                                            else if(result.get(i).isStatus())
                                                    {
                                                            out.println("<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,"+result.get(i).getId()+")\" value=\""+1+"\" checked></div>");
                                                    }
                                            out.println("<button id='deleteTugas' onclick='setChosenT(\""+result.get(i).getId()+"\");deleteTask()'>Delete Tugas</button>");
                                    out.println("</div>");
                        }
                        else
                        {
                            ArrayList<User> assignees=new ArrayList<User>(result.get(i).getAssignees());
                            for (int x = 0; x < assignees.size(); x++)
                            {
                                if((result.get(i).getPemilik().getUsername() == null ? assignees.get(x).getUsername() != null : !result.get(i).getPemilik().getUsername().equals(assignees.get(x).getUsername())) && assignees.get(x).getUsername() == null ? currentUser.getUsername() == null : assignees.get(x).getUsername().equals(currentUser.getUsername()))
                                {
                                    out.println("<h2>"+result.get(i).getKategori().getNama()+"</h2>");
                                    out.println("<div class=\"tugas\">");
                                            out.println("<div><a href=\"tugas.jsp?id="+result.get(i).getId()+"\">"+result.get(i).getNama()+"</a></div>");
                                            out.println("<div>Deadline: <strong>"+result.get(i).getTglDeadline()+"</strong></div>");
                                            out.println("<div>");
                                                    out.println("Tags: ");
                                                    out.println("<ul class=\"tag\">");
                                                            for (int n = 0; n < result.get(i).getTags().size(); n++)
                                                            {
                                                                    out.println("<li>"+result.get(i).getTags().toArray()[n].toString()+"</li>");
                                                            }
                                                    out.println("</ul>");
                                            out.println("</div>");   
                                            if(!result.get(i).isStatus())
                                                    {
                                                            out.println("<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,"+result.get(i).getId()+")\" value=\""+0+"\"></div>");
                                                    }
                                            else if(result.get(i).isStatus())
                                                    {
                                                            out.println("<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,"+result.get(i).getId()+")\" value=\""+1+"\" checked></div>");
                                                    }
                                    out.println("</div>");
                                }
                            }
                        }
                    }
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
