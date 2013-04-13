<%-- 
    Document   : searchresult
    Created on : Apr 12, 2013, 9:44:18 AM
    Author     : Arief
--%>

<%@page import="database.Checker"%>
<%@page import="database.MyDatabase"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="dashboard.css" media="screen">
        <link rel="stylesheet" href="loginpage.css" type="text/css" media="screen">
        <title>S.Y.N. Search Result</title>
    </head>
    <jsp:include page="header.jsp"/>
    <body>
        &nbsp
        <br>
        &nbsp
        <br>
        &nbsp
        <br>
        &nbsp
        <br>
        <%
            String query = request.getParameter("query");
            int filter = Integer.parseInt(request.getParameter("filter"));
            int pagenum;
            if (request.getParameter("page") != null) {
                pagenum = Integer.parseInt(request.getParameter("page"));
            } else {
                pagenum = 0;
            }

        %>
        <h1>Search Result</h1>
        <table id="Content_Table>
            <tr>
                <td width="1000">
                    <%
                        if (filter == 1 || filter == 0) {
                            String pQuery = "select * from user where username LIKE '%" + query + "%' OR email LIKE '%" + query + "%' OR tanggalLahir LIKE '%" + query + "%' or fullname LIKE '%" + query + "%'";
                            ResultSet resultSet = MyDatabase.getSingleton().selectDB(pQuery);
                            out.println("<h2>User search result</h2>");
                            while (resultSet.next()) {
                                out.println("Username : ");
                                out.println("<a href=\"profil.jsp?username=" + resultSet.getString("username") + "\">");
                                out.println(resultSet.getString("username").toString() + "</a><br>");
                                out.println("Nama Lengkap : " + resultSet.getString("fullname") + "<br>");
                                out.println("Tanggal Lahir : " + resultSet.getDate("tanggalLahir") + "<br>");
                                out.println("e-mail : " + resultSet.getString("email") + "<br>");
                                out.println("<img src = \"" + resultSet.getString("avatar") + "\" width=\"200\" height=\"100\">");
                                out.println("<br><br>");
                            }
                            out.println("==========================================<br>");
                        }
                        if (filter == 2 || filter == 0) {
                            String pQuery = "select * from kategori where namaKategori LIKE '%" + query + "%'";
                            ResultSet resultSet = MyDatabase.getSingleton().selectDB(pQuery);
                            out.println("<h2>Category search result</h2>");
                            while (resultSet.next()) {
                                out.println("Kategori : ");
                                out.println("<a href=\"dashboard.jsp?category=" + resultSet.getString("namaKategori").toString() + "\">");
                                out.println(resultSet.getString("namaKategori").toString() + "</a><br>");
                                out.println("Pembuat Kategori : " + resultSet.getString("creatorKategoriName") + "<br><br>");
                            }
                            out.println("==========================================<br>");
                        }
                        if (filter == 3 || filter == 0) {
                            String pQuery = "SELECT DISTINCT task.namaTask AS namaTask, status, deadline FROM task LEFT OUTER JOIN komentar ON task.namaTask = komentar.namaTask WHERE task.namaTask LIKE  '%"+query+"%' OR komentar.isikomentar LIKE  '%"+query+"%'";
                            MyDatabase searchDB = new MyDatabase();
                            ResultSet resultSet = searchDB.selectDB(pQuery);
                            out.println("<h2>Task Search Result</h2>");
                            while (resultSet.next()){
                                out.println("Nama Task : ");
                                out.println("<a href=\"viewtask.jsp?namaTask=" + resultSet.getString("namaTask").toString() + "\">");
                                out.println(resultSet.getString("namaTask").toString() + "</a><br>");
                                out.println("Tanggal Deadline : " + resultSet.getDate("deadline") + "<br>");
                                out.println("Status : " + resultSet.getString("status") + "<br>");
                                out.println("Tag : ");
                                String pQuery2 = "SELECT DISTINCT * from tagging where namaTask=\"" + resultSet.getString("namaTask") + "\"";
                                MyDatabase searchDB2 = new MyDatabase();
                                ResultSet resultSet2 = searchDB2.selectDB(pQuery2);
                                while(resultSet2.next()){
                                    out.println(resultSet2.getString("tag"));
                                }
                                out.println("<br>");
                                if(Checker.isAssignee(session.getAttribute("sUsername").toString(), resultSet.getString("namaTask"))){
                                    out.println("<a href=\"ubahstatus.jsp?target=search&namaTask="+resultSet.getString("namaTask")+"&query="+query+"&filter="+filter+"&page="+pagenum+"\">");
                                    out.println("<input type=\"button\" value=\"Ubah Status\"></a><br><br>");
                                }
                            }
                            out.println("==========================================<br>");
                        }
                    %>
                </td>
            </tr>
        </table>
    </body>
</html>
