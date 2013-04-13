<%-- 
    Document   : search_result
    Created on : Apr 6, 2013, 8:26:18 AM
    Author     : VAIO
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Class.*"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Result</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
	<script type="text/javascript" src="javascript/search_result.js"></script>
    </head>
    <body>
        <%
        /*session management*/
        if(request.getSession().getAttribute("userlistapp")==null){
            response.sendRedirect("index.jsp");
        }
        %>
        <div id="main-body-general">
                <div id="header">
                <jsp:include page="header.jsp" />
                <%
                    Function func = new Function();
                    String usernameToShow = request.getParameter("username");
                    HashMap<String, String> userShow = func.GetUser(usernameToShow);
                %>
                </div>
                
                <div><hr id="border"></div>
                
                <!--Body-->
                <div id="search-result-body">
                    <%
                        int mode = Integer.parseInt(request.getParameter("modesearch"));
                        String text = request.getParameter("search_text");
                        
                        GetConnection getCon = new GetConnection();
                        Connection conn = getCon.getConnection();
                        Statement stt = conn.createStatement();
                        String query;
                        ResultSet rs;
                        switch(mode) {
                            case 1: 
                                out.print("<div id=\"task-search\"><div id=\"dashboard-title\"><i><b>USER<br /></b></i></div><br><div><hr id=\"border-search\"></div><br>");
                                query = "SELECT * FROM user WHERE username LIKE '%"+text+"%'";
                                rs = stt.executeQuery(query);
                                while(rs.next()){
                                    out.print("<div id=\"user-result\">"
                                            + " <div id=\"user-result-image\">"
                                            + "     <a href=\"profile.jsp?username="+rs.getString("username")+"\"><img alt=\"Avatar\" id=\"photo\" src=\"../avatar/"+rs.getString("avatar")+"\" width=\"100\" height=\"120\"/></a>"
                                            + " </div>"
                                            + " <div id=\"user-result-name\">"
                                            + "     <div id=\"dashboard-title\"><a href=\"profile.jsp?username="+rs.getString("username")+"\"><b>"+rs.getString("username")+"</b></a></div><br>"
                                            + "     Full Name : "+rs.getString("fullname")+"<br>"
                                            + "     Joined On : "+rs.getString("join")+""
                                            + " </div>"
                                            + "</div>");
                                }
                                out.print("<div><hr id=\"border-search\"></div><br>"
                                           + "<div id=\"dashboard-title\"><i><b>CATEGORY<br /></b></i></div><br>"
                                           + "<div><hr id=\"border-search\"></div><br>");
                                query = "SELECT * FROM category WHERE categoryname LIKE '%"+text+"%'";
                                rs = stt.executeQuery(query);
                                while(rs.next()){
                                    out.print("<div class=\"task-category-body\">"
                                            + " <br><div><div id=\"category-title\"><b>"+rs.getString("categoryname")+"</b></div></div>"
                                            + " <div class=\"kosong\">Created by : <i>"+rs.getString("username")+"</i>, at "+rs.getString("createddate")+"</div>"
                                            + " <ul>");
                                    Statement stt2 = conn.createStatement();
                                    String query2 = "SELECT * FROM task WHERE categoryid="+rs.getString("categoryid");
                                    ResultSet rs2 = stt2.executeQuery(query2);
                                    while(rs2.next()) {
                                        out.print("<li><a href = \"task_page.jsp?taskid="+rs2.getString("taskid")+"\">"+rs2.getString("taskname")+"</a></li>");
                                    }
                                    out.print(" </ul>"
                                            + "</div>");
                                }
                                out.print("<div><hr id=\"border-search\"></div><br>"
                                        + " <div id=\"dashboard-title\"><i><b>TASK<br /></b></i></div><br>"
                                        + " <div><hr id=\"border-search\"></div><br>");
                                query = "SELECT * FROM task WHERE taskname LIKE '%"+text+"%'";
                                rs = stt.executeQuery(query);
                                int i = 0;
                                while(rs.next()) {
                                    out.print("<div id=\"task-result\">"
                                            + " <ul>"
                                            + "     <li><a href = \"task_page.jsp?taskid="+rs.getString("taskid")+"\">"+rs.getString("taskname")+"</a><div class=\"task-tag\">submit by : <b><i>"+rs.getString("username")+"</i></b>, deadline : "+rs.getString("deadline")+", status : <b id=\"red-text\">"+rs.getString("status")+"</b></div>"
                                            + "     <br><div>"
                                            + "     <div class=\"task-tag\">Set as <a href=\"javascript:setCompleteStatus("+i+","+rs.getString("taskid")+")\">Change Status</a></div></div><br><br>"
                                            + "     <div id=\"task-tag\">"
                                            + "     Tag :<br>");
                                    Statement stt2 = conn.createStatement();
                                    String query2 = "SELECT * FROM task_tag WHERE taskid="+rs.getString("taskid");
                                    ResultSet rs2 = stt2.executeQuery(query2);
                                    while(rs2.next()) {
                                        HashMap<String, String> tag = func.GetTag(rs2.getString("tagid"));
                                        out.print("<u>"+tag.get("tagname")+"</u>");
                                    }
                                    out.print("     </div>"
                                            + "     </li><br><br>"
                                            + " </ul>"
                                            + "</div>");
                                    i++;
                                }
                                out.print("</div>");
                                break;
                            case 2:
                                out.print("<div class=\"task-category-body\">"
                                        + " <div id=\"dashboard-title\"><b>USER<br /></b><br /></div>"
                                        + " <div id=\"sort\">"
                                        + "     Sort by :"
                                        + "     <select name=\"Sort by\">"
                                        + "         <option value=\"Auto\">Auto</option>"
                                        + "         <option value=\"Name\">Name</option>"
                                        + "         <option value=\"Date\">Date</option>"
                                        + "     </select>&nbsp;"
                                        + " </div>");
                                int mode2 = Integer.parseInt(request.getParameter("filtering"));
                                query = "";
                                switch(mode2){
                                    case 1 :
                                        query = "SELECT * FROM user WHERE username LIKE '%"+text+"%'";
                                        break;
                                    case 2 :
                                        query = "SELECT * FROM user WHERE email LIKE '%"+text+"%'";
                                        break;
                                    case 3 :
                                        query = "SELECT * FROM user WHERE fullname LIKE '%"+text+"%'";
                                        break;
                                    case 4 :
                                        query = "SELECT * FROM user WHERE birthdate LIKE '%"+text+"%'";
                                        break;
                                }
                                rs = stt.executeQuery(query);
                                while (rs.next()) {
                                    out.print("<div id=\"user-result\">"
                                            + " <div id=\"user-result-image\">"
                                            + "     <a href=\"profile.jsp?username="+rs.getString("username")+"\"><img alt=\"Avatar\" id=\"photo\" src=\"../avatar/"+rs.getString("avatar")+"\" width=\"100\" height=\"120\"/></a>"
                                            + " </div>"
                                            + " <div id=\"user-result-name\">"
                                            + "     <div id=\"dashboard-title\"><a href=\"profile.jsp?username="+rs.getString("username")+"\"><b>"+rs.getString("username")+"</b></a></div><br>"
                                            + "     Full Name : "+rs.getString("fullname")+"<br>"
                                            + "     Joined On : "+rs.getString("join")+""
                                            + " </div>"
                                            + "</div>");
                                }
                                out.print("</div>");
                                break;
                            case 3:
                                out.print("<div class=\"task-category-body\">"
                                        + " <div id=\"dashboard-title\"><b>CATEGORY<br /></b><br /></div>"
                                        + " <div id=\"sort\">"
                                        + "     Sort by :"
                                        + "     <select name=\"Sort by\">"
                                        + "         <option value=\"Auto\">Auto</option>"
                                        + "         <option value=\"Name\">Name</option>"
                                        + "         <option value=\"Date\">Date</option>"
                                        + "     </select>&nbsp;"
                                        + " </div>");
                                query = "SELECT * FROM category WHERE categoryname LIKE '%"+text+"%'";
                                rs = stt.executeQuery(query);
                                while(rs.next()){
                                    out.print("<div class=\"task-category-body\">"
                                            + " <br><div><div id=\"category-title\"><b>"+rs.getString("categoryname")+"</b></div></div>"
                                            + " <div class=\"kosong\">Created by : <i>"+rs.getString("username")+"</i>, at "+rs.getString("createddate")+"</div>"
                                            + " <ul>");
                                    Statement stt2 = conn.createStatement();
                                    String query2 = "SELECT * FROM task WHERE categoryid="+rs.getString("categoryid");
                                    ResultSet rs2 = stt2.executeQuery(query2);
                                    while(rs2.next()) {
                                        out.print("<li><a href = \"task_page.jsp?taskid="+rs2.getString("taskid")+"\">"+rs2.getString("taskname")+"</a></li>");
                                    }
                                    out.print(" </ul>"
                                            + "</div>");
                                }
                                out.print("</div>");
                                break;
                            case 4:
                                out.print("<div id=\"task-search\">"
                                        + " <div id=\"dashboard-title\"><b>TASK<br /></b><br /></div>"
                                        + " <div id=\"sort\">"
                                        + "     Sort by :"
                                        + "     <select name=\"Sort by\">"
                                        + "         <option value=\"Auto\">Auto</option>"
                                        + "         <option value=\"Name\">Name</option>"
                                        + "         <option value=\"Date\">Date</option>"
                                        + "     </select>&nbsp;"
                                        + " </div>");
                                query = "SELECT * FROM task WHERE taskname LIKE '%"+text+"%'";
                                rs = stt.executeQuery(query);
                                int j = 0;
                                while(rs.next()) {
                                    out.print("<div id=\"task-result\">"
                                            + " <ul>"
                                            + "     <li><a href = \"task_page.jsp?taskid="+rs.getString("taskid")+"\">"+rs.getString("taskname")+"</a><div class=\"task-tag\">submit by : <b><i>"+rs.getString("username")+"</i></b>, deadline : "+rs.getString("deadline")+", status : <b id=\"red-text\">"+rs.getString("status")+"</b></div>"
                                            + "     <br><div>"
                                            + "     <div class=\"task-tag\">Set as <a href=\"javascript:setCompleteStatus("+j+","+rs.getString("taskid")+")\">Change Status</a></div></div><br><br>"
                                            + "     <div id=\"task-tag\">"
                                            + "     Tag :<br>");
                                    Statement stt2 = conn.createStatement();
                                    String query2 = "SELECT * FROM task_tag WHERE taskid="+rs.getString("taskid");
                                    ResultSet rs2 = stt2.executeQuery(query2);
                                    while(rs2.next()) {
                                        HashMap<String, String> tag = func.GetTag(rs2.getString("tagid"));
                                        out.print("<u>"+tag.get("tagname")+"</u>");
                                    }
                                    out.print("     </div>"
                                            + "     </li><br><br>"
                                            + " </ul>"
                                            + "</div>");
                                    j++;
                                }
                                out.print("</div>");
                                break;
                        }
                    %>
            </div>
        </div>
    </body>
</html>
