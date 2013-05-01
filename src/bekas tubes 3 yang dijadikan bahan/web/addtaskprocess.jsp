<%-- 
    Document   : addtaskprocess
    Created on : Apr 12, 2013, 11:32:03 PM
    Author     : Arief
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="database.MyDatabase,java.sql.ResultSet;" errorPage="" %>
<%
    String category = request.getParameter("addtask_category");
    String username = session.getAttribute("sUsername").toString();
    String namaTask = request.getParameter("addtask_namaTask");
    String assigneeBlock = request.getParameter("addtask_assignee");
    String tagBlock = request.getParameter("addtask_tag");
    String tanggal = request.getParameter("addtask_deadline");
    String attachment1 = request.getParameter("addtask_attachment1");
    String attachment2 = request.getParameter("addtask_attachment2");
    String attachment3 = request.getParameter("addtask_attachment3");
    String attachment4 = request.getParameter("addtask_attachment4");
    String attachment5 = request.getParameter("addtask_attachment5");
    String pQuery = "INSERT INTO `sharedtodolist`.`task` (`namaTask`, `deadline`, `status`, `creatorTaskName`, `namaKategori`) VALUES ('" + namaTask + "', '" + tanggal + "', 'undone', '" + username + "', '" + category + "');";
    int ti = MyDatabase.getSingleton().queryDB(pQuery);
    if (ti == -1) {
        response.sendRedirect("dashboard.jsp?category=" + category);
    } else {
        pQuery = "INSERT INTO `sharedtodolist`.`tasktoasignee` (`namaTask`,`asigneeName`) VALUES ('" + namaTask + "','" + username + "')";
        MyDatabase.getSingleton().queryDB(pQuery);
        String[] assignee = assigneeBlock.split(",");
        for (int i = 0; i < assignee.length; i++) {
            if (assignee[i] != null && !assignee[i].equals("")) {
                pQuery = "INSERT INTO `sharedtodolist`.`tasktoasignee` (`namaTask`,`asigneeName`) VALUES ('" + namaTask + "','" + assignee[i] + "')";
                int ta = MyDatabase.getSingleton().queryDB(pQuery);
            }
        }
        String[] tag = tagBlock.split(",");
        for (int i = 0; i < tag.length; i++) {
            if (tag[i] != null && !tag[i].equals("")) {
                pQuery = "INSERT INTO `sharedtodolist`.`tagging` (`namaTask`,`tag`) VALUES ('" + namaTask + "','" + tag[i] + "')";
                int ta = MyDatabase.getSingleton().queryDB(pQuery);
            }
        }
        if (attachment1 != null && !attachment1.equals("")) {
            pQuery = "INSERT INTO `sharedtodolist`.`attach` (`namaTask`,`attachment`) VALUES ('" + namaTask + "','" + attachment1 + "')";
            int ta = MyDatabase.getSingleton().queryDB(pQuery);
        }
        if (attachment2 != null && !attachment2.equals("")) {
            pQuery = "INSERT INTO `sharedtodolist`.`attach` (`namaTask`,`attachment`) VALUES ('" + namaTask + "','" + attachment2 + "')";
            int ta = MyDatabase.getSingleton().queryDB(pQuery);
        }
        if (attachment3 != null && !attachment3.equals("")) {
            pQuery = "INSERT INTO `sharedtodolist`.`attach` (`namaTask`,`attachment`) VALUES ('" + namaTask + "','" + attachment3 + "')";
            int ta = MyDatabase.getSingleton().queryDB(pQuery);
        }
        if (attachment4 != null && !attachment4.equals("")) {
            pQuery = "INSERT INTO `sharedtodolist`.`attach` (`namaTask`,`attachment`) VALUES ('" + namaTask + "','" + attachment4 + "')";
            int ta = MyDatabase.getSingleton().queryDB(pQuery);
        }
        if (attachment5 != null && !attachment5.equals("")) {
            pQuery = "INSERT INTO `sharedtodolist`.`attach` (`namaTask`,`attachment`) VALUES ('" + namaTask + "','" + attachment5 + "')";
            int ta = MyDatabase.getSingleton().queryDB(pQuery);
        }
        response.sendRedirect("viewtask.jsp?namaTask="+namaTask);
    }
%>
