<%-- 
    Document   : search
    Created on : Apr 11, 2013, 6:30:02 PM
    Author     : Frilla
--%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <title>BANG! - Search Result</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <script type="text/javascript" src="script.js"></script>
    </head>
    
    <body>
        <%
            String query = request.getParameter("searchquery");
            String type = request.getParameter("tipe");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
        %> 
        
        
        <div id="panel">
            search result for: <br/>
            <i><% out.print(query); %></i>
        </div>
        
        <div id="results">
            <%
                if (type.compareTo("All") == 0 || type.compareTo("Category") == 0) {
                    Statement  sCategory = conn.createStatement();
                    ResultSet rCategory = sCategory.executeQuery("SELECT * FROM category WHERE CategoryName LIKE '%" + query + "%'");
                    out.print ("<div id='rCategory'><strong>Category</strong><br/>-------------------------------------------------------------------------------------------------------------------<br/>");
                    if (!rCategory.wasNull()) {
                        while (rCategory.next()) {
                            out.print (rCategory.getString("CategoryName"));
                            out.print ("<br/>");
                        }
                    } else {
                        out.print ("No Result");
                    }
                    out.print ("</div>");
                } 
                if (type.compareTo("All") == 0 || type.compareTo("Task") == 0) {
                    Statement sTask = conn.createStatement();
                    ResultSet rTask = sTask.executeQuery("SELECT * FROM task, tag, tasktag WHERE TaskName LIKE '%" + query + "%' AND task.IDTask = tasktag.IDTask AND tag.IDTag = tasktag.IDTag");
                    out.print ("<div id='rTask'><strong>Task</strong><br/>-------------------------------------------------------------------------------------------------------------------<br/>");
                    if (!rTask.wasNull()) {
                        while (rTask.next()) {
                            String resTaskID = rTask.getString("IDTask");
                            String resTaskName = rTask.getString("TaskName");
                            String resTaskStatus = rTask.getString("Status");
                            String resTaskDeadline = rTask.getString("Deadline");
                            String resTaskTag = rTask.getString("TagName");
            %>
                <p class='searchUser'>
                    <a title="Go to Profile" href="RinciTugas.jsp?IDTask=<% out.print(resTaskID); %>">    
                        <strong><% out.print(resTaskName); %></strong><br/>
                            <% out.print(resTaskDeadline); %> <br/>
                            <% out.print(resTaskStatus); %><br/>
                            Tag: 
                            <%
                                out.print(resTaskTag);
                            %>
                    </a>
                </p>
            <%
                        }
                    } else {
                        out.print ("No Result");
                    }
                    out.print ("</div>");
                }
            %>
            
            <% if (type.compareTo("All") == 0 || type.compareTo("Category") == 0) {
                    Statement sUser = conn.createStatement();
                    ResultSet rUser = sUser.executeQuery("SELECT * FROM user WHERE Username LIKE '%" + query + "%'");
                    out.print ("<div id='rUser'><strong>User</strong><br/>-------------------------------------------------------------------------------------------------------------------<br/>");
                    if (!rUser.wasNull()) {
                        while (rUser.next()) {
                            String resUser = rUser.getString("Username");
                            String resName = rUser.getString("Fullname");
                            String resAva = rUser.getString("Avatar"); 
            %> 
            <p class='searchUser'>
                    <a title="Go to Profile" href="profile.jsp?user=<% out.print(resUser); %>">
                        <img src="<% out.print(resAva); %>" alt="avatar" class="searchAva"/>
                        <div class="detailUser">
                            <strong><% out.print(resUser); %></strong><br/>
                            <% out.print(resName); %>
                        </div>
                        <br/>
                    </a>
                </p>
            <br/>
            <%
                        }
                    } else {
                        out.print ("No Result");
                    }
                    out.print ("</div>");
                }
            %>
            
            <br/>
        </div>
        
    </body>
    
</html>
