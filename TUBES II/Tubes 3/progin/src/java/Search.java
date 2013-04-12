/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Compaq
 */
public class Search extends HttpServlet {

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
    String htmlresponse;
    private DBConnector con;
    private ResultSet resultSet = null;
    private ResultSet resultSet2 = null;
    private ResultSet resultSet3 = null;
    private ResultSet resultSet4 = null;
    
    @Override
    public void init() {
        con = new DBConnector();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String realPath = this.getServletConfig().getServletContext().getRealPath("/");
        System.out.println("realpath: "+realPath);
        try {
            htmlresponse = DoSearch(request.getParameter("filtertype"), request.getParameter("searchquery"));
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(htmlresponse);
            out.close();
//            request.setAttribute("resultset", resultSet);
//            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/Dashboard.jsp");
//            requestDispatcher.forward(request,response);
        } catch (Exception ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private String DoSearch(String filter, String searchquery) throws Exception {
        con.Init();
        String query;
        System.out.println(searchquery);
        String out = "";
        switch (filter) {
            case "All": {
                if (!searchquery.equals("")) {
                    query = "SELECT * FROM profil WHERE Username='"+searchquery+"'";
                } else {
                    query = "SELECT * FROM profil";
                }
                resultSet = con.ExecuteQuery(query);
                out+= "<p>Users found</p>";
                out+= "<table class='usersearchtable'>\n";
                while (resultSet.next()) {
                    out+= "<tr>\n";
                    out+= "<td>"+resultSet.getString("Username") +"</td>\n";
                    out+= "<td>"+resultSet.getString("Fullname") +"</td>\n";
                    out+= "<td> <img src='"+resultSet.getString("Avatar")+"'></td>\n";
                    out+= "</tr>\n";
                }
                out+= "</table>\n";
                if (!searchquery.equals("")) {
                    query = "SELECT DISTINCT task.ID, task.IDCreator, task.Nama, task.Category, task.Status, task.Deadline " +
                            "FROM category INNER JOIN task LEFT JOIN tags " +
                            "ON category.Category=task.Category AND task.ID=tags.IDTask " +
                            "WHERE Tag='"+searchquery+"' OR Nama='"+searchquery+"' OR task.Category='"+searchquery+"'";
                } else {
                    query = "SELECT DISTINCT task.ID, task.IDCreator, task.Nama, task.Category, task.Status, task.Deadline " +
                            "FROM category INNER JOIN task LEFT JOIN tags " +
                            "ON category.Category=task.Category AND task.ID=tags.IDTask ";
                }
                resultSet = con.ExecuteQuery(query);
                out+= "<p>Task found</p>";
                out+= "<table class='tasksearchtable'>\n";
                out+= "<tr>\n";
                out+= "    <td>Nama Task</td>\n";
                out+= "    <td>Deadline</td>\n";
                out+= "    <td>Status</td>\n";
                out+= "    <td>Tags</td>\n";
                out+= "    <td> Check if done </td>\n";
                out+= "</tr>\n";
                while(resultSet.next())
                {
                    query = "SELECT Tag FROM tags WHERE IDTask='"+resultSet.getString("ID")+"'";
                    resultSet2 = con.ExecuteQuery(query);
                    String tags = "";
                    while (resultSet2.next()) {
                        tags += resultSet2.getString("Tag");
                    }
                    out+= "<tr>\n";
                    out+= "        <td>\n";
                    out+= "        <form name='hiddenidtask"+resultSet.getString("ID")+"' id='hiddenidtask"+resultSet.getString("ID")+"' action='rincian.jsp' method='post'>\n";
                    out+= "            <input type='hidden' name='idtask' value='"+resultSet.getString("ID")+"' />\n";
                    out+= "        </form>\n";
                    out+= "        <a href=\"javascript:document.forms['hiddenidtask"+resultSet.getString("ID")+"'].submit()\"  >"+resultSet.getString("Nama")+"</a>\n";
                    out+= "        </td>\n";
                    out+= "        <td>"+resultSet.getString("Deadline")+"</td>\n";
                    if (resultSet.getInt("Status") == 1) {
                    out+= "        <td> finished </td>\n";
                    }
                    else
                        out+= "        <td> not finished </td>\n";
                    out+= "        <td>"+tags+"</td>\n";
                    if (resultSet.getInt("Status") == 1) 
                        out+= "<td><input type='checkbox' checked /></td>\n";
                    else
                        out+= "<td><input type='checkbox' />\n</td>";
                    out+= "</tr>\n";
                }
                out+= "</table>\n";
                break;
            }
            case "Username": {
                if (!searchquery.equals("")) {
                    query = "SELECT * FROM profil WHERE Username='"+searchquery+"'";
                } else {
                    query = "SELECT * FROM profil";
                }
                resultSet = con.ExecuteQuery(query);
                out+= "<table class='usersearchtable'>\n";
                while (resultSet.next()) {
                    out+= "<tr>\n";
                    out+= "<td>"+resultSet.getString("Username") +"</td>\n";
                    out+= "<td>"+resultSet.getString("Fullname") +"</td>\n";
                    out+= "<td> <img src='"+resultSet.getString("Avatar")+"'></td>\n";
                    out+= "</tr>\n";
                }
                out+= "</table>\n";
                break;
            }
            case "Category": {
                if (!searchquery.equals("")) {
                    query = "SELECT * FROM category INNER JOIN task "+
                            "ON category.Category=task.Category "+
                            "WHERE category.Category='"+searchquery+"'";
                    resultSet = con.ExecuteQuery(query);
                    query = "alo";
                }
                else {
                    query = "SELECT * FROM category INNER JOIN task "+
                            "ON category.Category=task.Category";
                    resultSet = con.ExecuteQuery(query);
                }
                out+= "<table class='categorysearchtable'>\n";
                out+= "<tr>\n";
                out+= "     <td>Nama Task</td>\n";
                out+= "     <td>Deadline</td>\n";
                out+= "     <td>Status</td>\n";
                out+= "     <td>Tags</td>\n";
                out+= "     <td> Check if done </td>\n";
                out+= "     </tr>\n";
                while(resultSet.next())
                {
                    query = "SELECT Tag FROM tags WHERE IDTask='"+resultSet.getString("ID")+"'";
                    resultSet2 = con.ExecuteQuery(query);
                    String tags = "";
                    while (resultSet2.next()) {
                        tags += resultSet2.getString("Tag");
                    }
                    out+= "<tr>\n";
                    out+= "        <td>\n";
                    out+= "        <form name='hiddenidtask"+resultSet.getString("ID")+"' id='hiddenidtask"+resultSet.getString("ID")+"' action='rincian.jsp' method='post'>\n";
                    out+= "            <input type='hidden' name='idtask' value='"+resultSet.getString("ID")+"' />\n";
                    out+= "        </form>\n";
                    out+= "        <a href=\"javascript:document.forms['hiddenidtask"+resultSet.getString("ID")+"'].submit()\"  >"+resultSet.getString("Nama")+"</a>\n";
                    out+= "        </td>\n";
                    out+= "        <td>"+resultSet.getString("Deadline")+"</td>\n";
                    if (resultSet.getInt("Status") == 1) {
                    out+= "        <td> finished </td>\n";
                    }
                    else
                        out+= "        <td> not finished </td>\n";
                        out+= "        <td>"+tags+"</td>\n";
                    if (resultSet.getInt("Status") == 1) {
                        out+= "        <td><input type='checkbox' checked /></td>\n";
                    }
                    else
                        out+= "        <td><input type='checkbox' /></td>\n";
                    out+= "</tr>\n";
                }
                out+= "</table>\n";
                break;
            }
            case "Task": {
                if (!searchquery.equals("")) {
                    query = "SELECT DISTINCT task.ID, task.IDCreator, task.Nama, task.Category, task.Status, task.Deadline FROM task LEFT JOIN tags "+
                            "ON task.ID=tags.IDTask "+
                            "WHERE task.Nama='"+searchquery+"' OR tags.Tag='"+searchquery+"'";
                    resultSet = con.ExecuteQuery(query);
                }
                else {
                    query = "SELECT DISTINCT task.ID, task.IDCreator, task.Nama, task.Category, task.Status, task.Deadline FROM task LEFT JOIN tags "+
                            "ON task.ID=tags.IDTask ";
                    resultSet = con.ExecuteQuery(query);
                }

                out+= "<table class='tasksearchtable'>\n";
                out+= "<tr>\n";
                out+= "    <td>Nama Task</td>\n";
                out+= "    <td>Deadline</td>\n";
                out+= "    <td>Status</td>\n";
                out+= "    <td>Tags</td>\n";
                out+= "    <td> Check if done </td>\n";
                out+= "</tr>\n";
                while(resultSet.next())
                {
                    query = "SELECT Tag FROM tags WHERE IDTask='"+resultSet.getString("ID")+"'";
                    resultSet2 = con.ExecuteQuery(query);
                    String tags = "";
                    while (resultSet2.next()) {
                        tags += resultSet2.getString("Tag");
                    }
                    out+= "<tr>\n";
                    out+= "        <td>\n";
                    out+= "        <form name='hiddenidtask"+resultSet.getString("ID")+"' id='hiddenidtask"+resultSet.getString("ID")+"' action='rincian.jsp' method='post'>\n";
                    out+= "            <input type='hidden' name='idtask' value='"+resultSet.getString("ID")+"' />\n";
                    out+= "        </form>\n";
                    out+= "        <a href=\"javascript:document.forms['hiddenidtask"+resultSet.getString("ID")+"'].submit()\"  >"+resultSet.getString("Nama")+"</a>\n";
                    out+= "        </td>\n";
                    out+= "        <td>"+resultSet.getString("Deadline")+"</td>\n";
                    if (resultSet.getInt("Status") == 1) {
                    out+= "        <td> finished </td>\n";
                    }
                    else
                        out+= "        <td> not finished </td>\n";
                    out+= "        <td>"+tags+"</td>\n";
                    if (resultSet.getInt("Status") == 1) 
                        out+= "<td><input type='checkbox' checked /></td>\n";
                    else
                        out+= "<td><input type='checkbox' />\n</td>";
                    out+= "</tr>\n";
                }
                out+= "</table>\n";
                break;
            }
            default: break;
        }
        con.Close();
        return out;
    }
}
