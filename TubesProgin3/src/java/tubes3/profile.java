/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Yulianti Oenang
 */
@WebServlet(name = "profile", urlPatterns = {"/profile"})
@MultipartConfig
public class profile extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(profile.class.getCanonicalName());
    public String username, fullname, email, avatar;
    public String birthday;
    public List<String> tugasSelesai = new ArrayList<String>();
    public List<String> tugasBelumSelesai = new ArrayList<String>();
    public String password;
    //,tugasBelumSelesai;
    ResultSet rs;
    public profile(){}

    public profile(String username) throws SQLException {
        Tubes3Connection tu = new Tubes3Connection();
        Connection connection = tu.getConnection();this.username=username;

        String queryUser = "SELECT * FROM pengguna WHERE username ='" + this.username + "'";
        String queryTaskSelesai = "SELECT name FROM tugas WHERE username='" + this.username + "' and stat=1";
        String queryTaskBelumSelesai = "SELECT name FROM tugas WHERE username='" + this.username + "' and stat=0";
        rs = tu.coba(connection, queryUser);
        if (rs.next()) {
            fullname = rs.getString("fullname");
            email = rs.getString("email");
            avatar = rs.getString("avatar");
            birthday = rs.getString("birthday");
            password = rs.getString("password");
        } else {
            fullname = "Gak ada";
        }
        rs = tu.coba(connection, queryTaskSelesai);
        while (rs.next()) {
            tugasSelesai.add(rs.getString("name"));
        }
        rs = tu.coba(connection, queryTaskBelumSelesai);
        while (rs.next()) {
            tugasBelumSelesai.add(rs.getString("name"));
        }

    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet profile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet profile at " + request.getContextPath() + " lljkjl</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long serialVersionUID = 1L;
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "avatar";
    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    public HttpSession session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        String username="yuli";
        String queryUser = "SELECT * FROM pengguna WHERE username ='" + username + "'";
        Tubes3Connection tu = new Tubes3Connection();
        Connection connection = tu.getConnection();
        ResultSet rs = null;
        Statement pst;
        String query = "";
        String namalengkap="";
        String ultah="";
        String sandi="";
        String avatar2="";
        try {
            rs=tu.coba(connection,queryUser);
              if(rs.next())
                {
                      namalengkap = rs.getString("fullname");
                      avatar2 = rs.getString("avatar");
                      ultah = rs.getString("birthday");
                      sandi = rs.getString("password");
                }
        } catch (SQLException ex) {
            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        String full, birthday, password, avatar = null;
        full = request.getParameter("namalengkap");
        birthday = request.getParameter("birthday");
        String data[]=birthday.split("-");
        birthday=data[2]+"-"+data[1]+"-"+data[0];
        password = request.getParameter("password");
        //avatar=request.getParameter("avatar");
        final Part filePart = request.getPart("avatar");
        final String fileName = getFileName(filePart);
        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();


        if (namalengkap.equals(full) && ultah.equals(birthday) && sandi.equals(password) && (fileName == null || fileName.equals(""))) {
            session.setAttribute("flag", 1);
        } else {
            session.setAttribute("flag", 0);
            //$_SESSION['flag']=0;
            /*
            this.fullname = full;
            this.birthday = birthday;
            this.password = password;
             
             */
            if (fileName == null || fileName.equals("")) {
                query = "UPDATE pengguna SET fullname='" + full + "',birthday='" + birthday + "', password='" + password + "' WHERE username='" + username + "'";
            } else {
                try {
                    out = new FileOutputStream(getServletContext().getRealPath("/") + "avatar/" + fileName);
                    filecontent = filePart.getInputStream();

                    int read = 0;
                    final byte[] bytes = new byte[1024];

                    while ((read = filecontent.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                } catch (FileNotFoundException fne) {
                    writer.println("You either did not specify a file to upload or are "
                            + "trying to upload a file to a protected or nonexistent "
                            + "location.");
                    writer.println("<br/> ERROR: " + fne.getMessage());

                    LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                            new Object[]{fne.getMessage()});
                } finally {
                    if (out != null) {
                        out.close();
                    }
                    if (filecontent != null) {
                        filecontent.close();
                    }
                }
                avatar2="avatar/"+fileName;
                query = "UPDATE pengguna SET fullname=\'" + full + "\',birthday=\'" + birthday + "\', password=\'" + password + "\', avatar=\'" + avatar2 + "\' WHERE username=\'" + username + "\'";
            }
            try {
                pst = connection.createStatement();
                pst.execute(query);
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*
        RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
        rd.forward(request, response);
         * 
         *///request.getRequestDispatcher("profile.jsp").forward(request, response);
        response.sendRedirect("profile.jsp");

    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
