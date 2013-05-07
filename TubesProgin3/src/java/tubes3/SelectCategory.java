/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Devin
 */
@WebServlet(name = "SelectCategory", urlPatterns = {"/SelectCategory"})
public class SelectCategory extends HttpServlet {

    private Tubes3Connection db;
    private Connection connection;

    public SelectCategory(){
        db = new Tubes3Connection();
        connection = db.getConnection();
    }
    
    public String writeTask(String id, String username) {
        String result= "";
        try {
            ResultSet qresult = db.coba(connection, "SELECT IDTask, name, deadline, stat, tag FROM tugas WHERE IDKategori='" + id + "'");
            while(qresult.next()) {
                result = result + "<id>" + qresult.getInt("IDTask") + "</id>";
                result = result + "<nama>" + qresult.getString("name") + "</nama>";
                result = result + "<deadline>" + qresult.getString("deadline") + "</deadline>";
                result = result + "<status>" + qresult.getInt("stat") + "</status>";
                result = result + "<tag>" + qresult.getString("tag") + "</tag>";
                ResultSet canerase = db.coba(connection, "SELECT COUNT(*) FROM penugasan WHERE username='" + username + "' AND IDTask='" + qresult.getInt("IDTask") + "'");
                canerase.first();
                if(canerase.getInt("COUNT(*)") > 0)
                    result = result + "<canerase>true</canerase>";
                else
                    result = result + "<canerase>false</canerase>";
            }
        }
        catch (Exception ex) {
            System.out.println("Exception is ;" + ex);
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String result;
        response.setContentType("text/xml");
        out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        out.write("<root>");
        if(!(result = writeTask(request.getParameter("id"), (String)request.getSession().getAttribute("bananauser"))).equals(""))
            out.write(result);
        out.write("</root>");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}

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
