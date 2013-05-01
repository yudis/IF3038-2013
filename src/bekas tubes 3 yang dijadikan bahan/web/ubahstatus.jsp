<%-- 
    Document   : ubahstatus
    Created on : Apr 12, 2013, 1:39:37 AM
    Author     : Arief
--%>

<%@ page language="java" import="database.MyDatabase,java.sql.ResultSet;" errorPage="" %>
<%
    MyDatabase ubahstatusDB = new MyDatabase();
    MyDatabase ubahstatusDB2 = new MyDatabase();
    String curstatus = "";
    String tQuery = "select * from task where namaTask = \"" + request.getParameter("namaTask") + "\"";
    ResultSet tResult = ubahstatusDB.selectDB(tQuery);
    while (tResult.next()) {
        curstatus = tResult.getString("status");
    }
    if (curstatus.equals("done")) {
        tQuery = "UPDATE  `sharedtodolist`.`task` SET  `status` =  'undone' WHERE  `task`.`namaTask` =  \"" + request.getParameter("namaTask") + "\"";
    } else {
        tQuery = "UPDATE  `sharedtodolist`.`task` SET  `status` =  'done' WHERE  `task`.`namaTask` =  \"" + request.getParameter("namaTask") + "\"";
    }
    out.println(tQuery);
    ubahstatusDB2.queryDB(tQuery);
    if (request.getParameter("target").toString().equals("dashboard")) {
        response.sendRedirect("dashboard.jsp");
    }
       else if(request.getParameter("target").toString().equals("search")) {
           response.sendRedirect("searchresult.jsp?query="+request.getParameter("query")+"&filter="+request.getParameter("filter")+"&page="+request.getParameter("page"));
       }
       else if(request.getParameter("target").toString().equals("viewtask")) {
           response.sendRedirect("viewtask.jsp?namaTask="+request.getParameter("namaTask"));
       }
%>
