package id.ac.itb.todolist.controller;

import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.User;
import id.ac.itb.todolist.util.Helper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "Create", urlPatterns = {"/Create"})
@MultipartConfig
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
        
        System.out.println("assigneeI : " + request.getParameter("assigneeI"));
        
        if (session.getAttribute("user") != null) {
            UserDao userDao = new UserDao();
            User currentUser = (User) session.getAttribute("user");
            TugasDao tugas = new TugasDao();
            
            try {
                int newestId = tugas.addTugas(request.getParameter("namatask"), request.getParameter("deadline"), currentUser.getUsername(), Integer.parseInt(request.getParameter("namakategori")));

                
                String[] assigneeArr = request.getParameter("assigneeI").split("[ ,]+");
                for (int i = 0; i < assigneeArr.length; i++) {
                    if (!assigneeArr[i].equals(currentUser.getUsername())) {
                        System.out.println("- add: " + assigneeArr[i] + " -> " + tugas.addAssignee(newestId, assigneeArr[i]));
                    }
                }

                String uploadPath = getServletContext().getRealPath("./files");
                Iterator<Part> iter = request.getParts().iterator();

                while (iter.hasNext()) {
                    Part part = iter.next();
                    Collection<String> headers = part.getHeaderNames();
                    
                    if (part.getName().equals("attachments") && !"".equals(Helper.getFileName(part))) {
                        String temp1 = Helper.getFileName(part);
                        String[] temp = temp1.split("[ .]+");
                        String name = temp[1];
                        String extension = temp[temp.length - 1].toLowerCase();
                        String file_type;
                        
                        if ("jpeg".equals(extension) || "jpg".equals(extension) || "png".equals(extension) || "gif".equals(extension)) {
                            file_type = "image";
                        } else if ("ogg".equals(extension) || "webm".equals(extension) || "mp4".equals(extension)) {
                            file_type = "video";
                        } else {
                            file_type = "file";
                        }

                        Random random = new Random();
                        String fn = name + random.nextInt();
                        tugas.addAttachment(newestId, name, fn, file_type);
                        if (fn != null) {
                            Helper.writeFile(part.getInputStream(), uploadPath + File.separator + fn);
                        }
                    }
                }

                String[] tags = request.getParameter("tag").split("[ ,]+");
                for (int i = 0; i < tags.length; i++) {
                    if (!tags[i].equals("")) {
                        tugas.addTag(newestId, tags[i]);
                    }
                }

                response.sendRedirect("./tugas.jsp?id=" + newestId);
            } finally {
                out.close();
            }
        } else {
            response.sendRedirect("./index.jsp");
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
