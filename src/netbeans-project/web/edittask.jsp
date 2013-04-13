<%-- 
    Document   : edittask
    Created on : Apr 13, 2013, 2:32:07 AM
    Author     : Arief
--%>

<%@page import="database.Checker"%>
<%@page import="java.sql.Date"%>
<%@page import="database.MyDatabase"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="dashboard.css" media="screen">
        <link rel="stylesheet" href="loginpage.css" type="text/css" media="screen">
        <script src="edittask.js"></script>
        <title>Edit Task</title>
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
            String namaTask = request.getParameter("namaTask");
            String pQuery = "select distinct * from tasktoasignee where namaTask =\"" + namaTask + "\"";
            ResultSet tResult = MyDatabase.getSingleton().selectDB(pQuery);
            String assigneeList = "";
            while (tResult.next()) {
                assigneeList = assigneeList + tResult.getString("asigneeName") + ",";
            }
            pQuery = "select distinct * from tagging where namaTask=\"" + namaTask + "\"";
            tResult = MyDatabase.getSingleton().selectDB(pQuery);
            String tagList = "";
            while (tResult.next()) {
                tagList = tagList + tResult.getString("tag") + ",";
            }
            pQuery = "select * from task where namaTask=\"" + namaTask + "\"";
            tResult = MyDatabase.getSingleton().selectDB(pQuery);
            Date deadline = new Date(2012, 4, 3);
            while (tResult.next()) {
                deadline = tResult.getDate("deadline");
            }
        %>
        <h1>Edit Task</h1>
        <%
            if (Checker.isCreator(session.getAttribute("sUsername").toString(), namaTask)) {
                out.println("<a href=\"deletetask.jsp?namaTask=" + namaTask + "\"><input type=\"button\" value=\"Delete Task\"></a>");
            }
        %>
        <form action="edittaskprocess.jsp" method="post" id="editTaskForm">
            <table id="Content_Table">
                <tr>
                    <td align="right" width="450">Assignee</td>
                    <td width="50">:</td>
                    <td align="left" width="500">
                        <input type="hidden" name="edittask_namaTask" value="<%= namaTask%>">
                        <input type="text" name="edittask_assignee" id="edittask_assignee" value="<%= assigneeList%>" onkeyup="checkValidation();">
                    </td>
                </tr>
                <tr>
                    <td colspan="3"><div id="assigneewarning"></div></td>
                </tr>
                <tr>
                    <td align="right">Tag</td>
                    <td>:</td>
                    <td align="left">
                        <input type="text" name="edittask_tag" id="edittask_tag" value="<%= tagList%>" onkeyup="checkValidation();">
                    </td>
                </tr>
                <tr>
                    <td colspan="3"><div id="tagwarning"></div></td>
                </tr>
                <tr>
                    <td align="right">Deadline</td>
                    <td>:</td>
                    <td align="left">
                        <input type="date" name="edittask_date" value="<%= deadline%>" required>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="submit" value="Ubah Data">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
