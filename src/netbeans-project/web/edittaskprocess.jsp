<%-- 
    Document   : edittaskprocess
    Created on : Apr 13, 2013, 3:08:13 AM
    Author     : Arief
--%>

<%@ page language="java" import="database.MyDatabase,java.sql.ResultSet;" errorPage="" %>
<%
    String namaTask = request.getParameter("edittask_namaTask");
    String assigneeList = request.getParameter("edittask_assignee");
    String tagList = request.getParameter("edittask_tag");
    String deadline = request.getParameter("edittask_date");
    String pQuery = "UPDATE `sharedtodolist`.`task` SET `deadline`='" + deadline + "' where namaTask=\"" + namaTask + "\"";
    MyDatabase.getSingleton().queryDB(pQuery);
    String creatorName = "";
    pQuery = "select * from task where namaTask=\"" + namaTask + "\"";
    ResultSet resultSet = MyDatabase.getSingleton().selectDB(pQuery);
    while (resultSet.next()) {
        creatorName = resultSet.getString("creatorTaskName");
    }
    
    pQuery = "delete from tasktoasignee where namaTask=\"" + namaTask + "\"";
    MyDatabase.getSingleton().queryDB(pQuery);
    pQuery = "INSERT INTO  `sharedtodolist`.`tasktoasignee` (`namaTask` ,`asigneeName`)VALUES ('" + namaTask + "',  '" + creatorName + "');";
    MyDatabase.getSingleton().queryDB(pQuery);
    if (assigneeList != null && !assigneeList.equals("")) {
        String[] assignee = assigneeList.split(",");
        for (int i = 0; i < assignee.length; i++) {
            if (assignee[i] != null && !assignee[i].equals("") && !assignee[i].equals(creatorName)) {
                pQuery = "INSERT INTO  `sharedtodolist`.`tasktoasignee` (`namaTask` ,`asigneeName`)VALUES ('" + namaTask + "',  '" + assignee[i] + "');";
                MyDatabase.getSingleton().queryDB(pQuery);
            }
        }
    }
    
    pQuery = "delete from tagging where namaTask=\"" + namaTask + "\"";
    MyDatabase.getSingleton().queryDB(pQuery);
    if (tagList != null && !tagList.equals("")) {
        String[] tag = tagList.split(",");
        for (int i = 0; i < tag.length; i++) {
            if (tag[i] != null && !tag[i].equals("")) {
                pQuery = "INSERT INTO  `sharedtodolist`.`tagging` (`namaTask` ,`tag`)VALUES ('" + namaTask + "',  '" + tag[i] + "');";
                MyDatabase.getSingleton().queryDB(pQuery);
            }
        }
    }
    response.sendRedirect("viewtask.jsp?namaTask="+namaTask);
%>
