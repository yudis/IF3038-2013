<%-- 
    Document   : searchresult
    Created on : Apr 12, 2013, 9:44:18 AM
    Author     : Arief
--%>

<%@page import="org.json.JSONArray"%>
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
                            JSONArray resultSet = MyDatabase.getSingleton().selectDBREST(pQuery);
                            out.println("<h2>User search result</h2>");
                            int i=0;
                            while (!resultSet.isNull(i)) {
                                out.println("Username : ");
                                out.println("<a href=\"profil.jsp?username=" + resultSet.getJSONObject(i).getString("username") + "\">");
                                out.println(resultSet.getJSONObject(i).getString("username").toString() + "</a><br>");
                                out.println("Nama Lengkap : " + resultSet.getJSONObject(i).getString("fullname") + "<br>");
                                out.println("Tanggal Lahir : " + resultSet.getJSONObject(i).getString("tanggalLahir") + "<br>");
                                out.println("e-mail : " + resultSet.getJSONObject(i).getString("email") + "<br>");
                                out.println("<img src = \"" + resultSet.getJSONObject(i).getString("avatar") + "\" width=\"200\" height=\"100\">");
                                out.println("<br><br>");
                                i++;
                            }
                            out.println("==========================================<br>");
                        }
                        if (filter == 2 || filter == 0) {
                            String pQuery = "select * from kategori where namaKategori LIKE '%" + query + "%'";
                            JSONArray resultSet = MyDatabase.getSingleton().selectDBREST(pQuery);
                            out.println("<h2>Category search result</h2>");
                            int i=0;
                            while (!resultSet.isNull(i)) {
                                out.println("Kategori : ");
                                out.println("<a href=\"dashboard.jsp?category=" + resultSet.getJSONObject(i).getString("namaKategori").toString() + "\">");
                                out.println(resultSet.getJSONObject(i).getString("namaKategori").toString() + "</a><br>");
                                out.println("Pembuat Kategori : " + resultSet.getJSONObject(i).getString("creatorKategoriName") + "<br><br>");
                                i++;
                            }
                            out.println("==========================================<br>");
                        }
                        if (filter == 3 || filter == 0) {
                            String pQuery = "SELECT DISTINCT task.namaTask AS namaTask, status, deadline FROM task LEFT OUTER JOIN komentar ON task.namaTask = komentar.namaTask WHERE task.namaTask LIKE  '%"+query+"%' OR komentar.isikomentar LIKE  '%"+query+"%'";
                            MyDatabase searchDB = new MyDatabase();
                            JSONArray resultSet = searchDB.selectDBREST(pQuery);
                            out.println("<h2>Task Search Result</h2>");
                            int i=0;
                            while (!resultSet.isNull(i)){
                                out.println("Nama Task : ");
                                out.println("<a href=\"viewtask.jsp?namaTask=" + resultSet.getJSONObject(i).getString("namaTask").toString() + "\">");
                                out.println(resultSet.getJSONObject(i).getString("namaTask").toString() + "</a><br>");
                                out.println("Tanggal Deadline : " + resultSet.getJSONObject(i).getString("deadline") + "<br>");
                                out.println("Status : " + resultSet.getJSONObject(i).getString("status") + "<br>");
                                out.println("Tag : ");
                                String pQuery2 = "SELECT DISTINCT * from tagging where namaTask=\"" + resultSet.getJSONObject(i).getString("namaTask") + "\"";
                                MyDatabase searchDB2 = new MyDatabase();
                                JSONArray resultSet2 = searchDB2.selectDBREST(pQuery2);
                                int j=0;
                                while(!resultSet2.isNull(j)){
                                    out.println(resultSet2.getJSONObject(j).getString("tag"));
                                    j++;
                                }
                                out.println("<br>");
                                if(Checker.isAssignee(session.getAttribute("sUsername").toString(), resultSet.getJSONObject(i).getString("namaTask"))){
                                    out.println("<a href=\"ubahstatus.jsp?target=search&namaTask="+resultSet.getJSONObject(i).getString("namaTask")+"&query="+query+"&filter="+filter+"&page="+pagenum+"\">");
                                    out.println("<input type=\"button\" value=\"Ubah Status\"></a><br><br>");
                                }
                                i++;
                            }
                            out.println("==========================================<br>");
                        }
                    %>
                </td>
            </tr>
        </table>
    </body>
</html>
