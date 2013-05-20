<%-- 
    Document   : viewtask
    Created on : Apr 12, 2013, 4:57:12 PM
    Author     : Arief
--%>

<%@page import="org.json.JSONArray"%>
<%@page import="javax.print.attribute.standard.MediaSize.Other"%>
<%@page import="database.Checker"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="database.MyDatabase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="dashboard.css" media="screen">
        <link rel="stylesheet" href="loginpage.css" type="text/css" media="screen">
        <script>
            function timedRefresh(timeoutPeriod) {
                setTimeout("location.reload(true);",timeoutPeriod);
            }
        </script>
        <title>S.Y.N. View Task</title>
    </head>
    <jsp:include page="header.jsp"/>
    <body onload="javascript:timedRefresh(5000)">
        &nbsp
        <br>
        &nbsp
        <br>
        &nbsp
        <br>
        &nbsp
        <br>
        <h1>Task Detail</h1>
        <table id="Content_Table">
            <tr>
                <td width="500" valign="top">
                    <%
                        String nama = request.getParameter("namaTask");
                        String pQuery = "select * from task where namaTask=\"" + nama + "\"";
                        MyDatabase viewTaskDB = new MyDatabase();
                        JSONArray resultSet = viewTaskDB.selectDBREST(pQuery);
                        int i = 0;
                        while (!resultSet.isNull(i)) {
                            out.println("<h2>Nama Task : " + resultSet.getJSONObject(i).getString("namaTask") + "</h2><br>");
                            out.println("Deadline : " + resultSet.getJSONObject(i).getString("deadline") + "<br>");
                            out.println("Status Task : " + resultSet.getJSONObject(i).getString("status") + "<br>");
                            if (Checker.isAssignee(session.getAttribute("sUsername").toString(), resultSet.getJSONObject(i).getString("namaTask"))) {
                                out.println("<a href=\"ubahstatus.jsp?target=viewtask&namaTask=" + resultSet.getJSONObject(i).getString("namaTask") + "\">");
                                out.println("<input type=\"button\" value=\"Ubah Status\"></a><br>");
                            }
                            out.println("tag : ");
                            MyDatabase viewTaskDB2 = new MyDatabase();
                            String pQuery2 = "select distinct * from tagging where namaTask=\"" + nama + "\"";
                            JSONArray resultSet2 = viewTaskDB2.selectDBREST(pQuery2);
                            int j=0;
                            while (!resultSet2.isNull(j)) {
                                out.println(resultSet2.getJSONObject(j).getString("tag") + ", ");
                                j++;
                            }
                            out.println("<br>");
                            pQuery2 = "select distinct * from tasktoasignee where namaTask=\"" + nama + "\"";
                            resultSet2 = viewTaskDB2.selectDBREST(pQuery2);
                            out.println("assignee : ");
                            j=0;
                            while (!resultSet2.isNull(j)) {
                                out.println("<a href=\"profil.jsp?username=" + resultSet2.getJSONObject(j).getString("asigneeName") + "\">" + resultSet2.getJSONObject(j).getString("asigneeName") + "</a>, ");
                                j++;
                            }
                            out.println("<h3>Attachment : </h3>");
                            pQuery2 = "select * from attach where namaTask=\"" + nama + "\"";
                            resultSet2 = viewTaskDB2.selectDBREST(pQuery2);
                            j=0;
                            while (!resultSet2.isNull(j)) {
                                String filename = resultSet2.getJSONObject(j).getString("attachment");
                                int dotplace = filename.lastIndexOf(".");
                                String extension = filename.substring(dotplace + 1, filename.length());
                                if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("bmp") || extension.equals("gif")) {
                                    out.println("attachment type : picture<br>");
                                    out.println("<br><img src=\"" + filename + "\">");
                                } else if (extension.equals("mpg") || extension.equals("avi") || extension.equals("flv") || extension.equals("mp4") || extension.equals("mpeg") || extension.equals("ogg")) {
                                    out.println("attachment type : video<br>");
                                    out.println("<video class=\"isi\" width=\"320\" height=\"240\" controls>");
                                    out.println("<source src=\"" + filename + "\" type=\"video/" + extension + "\">");
                                    out.println("<source src=\"" + filename.substring(0, dotplace) + ".ogg\" type=\"video/ogg\">");
                                    out.println("Your browser does not support the video tag.");
                                    out.println("</video>");
                                } else {
                                    out.println("attachment type : other file<br>");
                                    out.println(filename + "<br>");
                                    out.println("<a href=\"" + filename + "\"target=\"_blank\">Download </a>");
                                }
                                out.println("<br>===============================<br><br>");
                                j++;
                            }
                            if (Checker.isAssignee(session.getAttribute("sUsername").toString(), nama)) {
                                out.println("<a href=\"edittask.jsp?&namaTask=" + request.getParameter("namaTask") + "\">");
                                out.println("<input type=\"button\" value=\"Edit Task\"></a><br>");
                            }
                            i++;
                        }
                    %>
                </td>
                <td width="500" align="right" valign="top">
                    <%
                        int hasil = 0;
                        pQuery = "select count(*) as hasil FROM `komentar` where namaTask=\"" + nama + "\"";
                        resultSet = viewTaskDB.selectDBREST(pQuery);
                        i=0;
                        while (!resultSet.isNull(i)) {
                            hasil = resultSet.getJSONObject(i).getInt("hasil");
                            out.println("<h2>Jumlah Komentar = " + hasil + "<br></h2>");
                            i++;
                        }
                        out.println("<h2>Komentar :<br>====================<br></h2>");
                        int pagenum;
                        if (request.getParameter("page") != null) {
                            pagenum = Integer.parseInt(request.getParameter("page"));
                        } else {
                            pagenum = 0;
                        }
                        if (pagenum > hasil / 10) {
                            pagenum = hasil / 10;
                        }
                        if (pagenum < (hasil / 10)) {
                    %>
                    <form action="viewtask.jsp" method="POST">
                        <input type="hidden" value="<%= nama%>" name="namaTask">
                        <input type="hidden" value="<%= pagenum + 1%>" name="page">
                        <input type="submit" value="NextPage >>">
                    </form>
                    <%
                        }
                        if (pagenum > 0) {
                    %>
                    <form action="viewtask.jsp" method="POST">
                        <input type="hidden" value="<%= nama%>" name="namaTask">
                        <input type="hidden" value="<%= pagenum - 1%>" name="page">
                        <input type="submit" value="<< PrevPage">
                    </form>
                    <%
                        }
                        out.println("<br><br>");
                        int currentcomment = 0;
                        pQuery = "select * from komentar where namaTask=\"" + nama + "\" ORDER BY komentar.timestamp DESC";
                        resultSet = viewTaskDB.selectDBREST(pQuery);
                        i=0;
                        while (!resultSet.isNull(i)) {
                            if (currentcomment >= (pagenum * 10) && currentcomment < ((pagenum + 1) * 10)) {
                                out.println(resultSet.getJSONObject(i).getString("komentator") + "<br>");
                                out.println("<img src=\"" + Checker.getAvatar(resultSet.getJSONObject(i).getString("komentator").toString()) + "\" width=\"50\" height=\"50\"><br>");
                                String pQuery2 = "select count(*) as hasil FROM `komentar` where komentator=\"" + resultSet.getJSONObject(i).getString("komentator") + "\" AND namaTask=\"" + nama + "\"";
                                MyDatabase viewTaskDB2 = new MyDatabase();
                                int j=0;
                                JSONArray resultSet2 = viewTaskDB2.selectDBREST(pQuery2);
                                while (!resultSet2.isNull(j)) {
                                    out.println(resultSet2.getJSONObject(j).getInt("hasil") + " Komentar<br>");
                                    j++;
                                }
                                out.println("on : " + resultSet.getJSONObject(i).getString("timestamp") + "<br>");
                                out.println("Komentar :<br>");
                                out.println(resultSet.getJSONObject(i).getString("isikomentar") + "<br>");
                                if (resultSet.getJSONObject(i).getString("komentator").equals(session.getAttribute("sUsername"))) {
                    %>
                    <form action="hapuskomentar.jsp">
                        <input type="hidden" name="idkomentar" value="<%= resultSet.getJSONObject(i).getInt("idKomentar") %>">
                        <input type="hidden" name="namaTask" value="<%= nama%>">
                        <input type="hidden" name="target" value="<%= pagenum%>">
                        <input type="submit" value="Hapus Komentar">
                    </form>
                    <%
                                };
                                out.println("<br><br>");
                            }
                            currentcomment++;
                            i++;
                        }
                        out.println("<h2>Jumlah Komentar = " + hasil + "<br></h2><h2><br>Tambah Komentar : <br></h2>");
                        if (pagenum < (hasil / 10)) {
                    %>
                    <form action="viewtask.jsp" method="POST">
                        <input type="hidden" value="<%= nama%>" name="namaTask">
                        <input type="hidden" value="<%= pagenum + 1%>" name="page">
                        <input type="submit" value="NextPage >>">
                    </form>
                    <%
                        }
                        if (pagenum > 0) {
                    %>
                    <form action="viewtask.jsp" method="POST">
                        <input type="hidden" value="<%= nama%>" name="namaTask">
                        <input type="hidden" value="<%= pagenum - 1%>" name="page">
                        <input type="submit" value="<< PrevPage">
                    </form>
                    <%
                        }
                    %>
                    <form action="submitkomentar.jsp" method="POST">
                        <input type="hidden" name="target" value="<%= hasil / 10%>">
                        <input type="hidden" name="namaTask" value="<%= nama%>">
                        <textarea cols="50" rows="5" name="isiKomentar"></textarea><br>
                        <input type="submit" value="Kirim !">
                        <input type="reset" value="Reset !">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
