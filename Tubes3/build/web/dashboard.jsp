<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>    
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="autocomplete.css" media="screen" />
    </head>
    <body>
        
        <div id="category">
            <%
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(
                        "SELECT category.IDCategory,category.CategoryName,category.Creator FROM assignment,task,category "
                        + "WHERE assignment.Username='" + session.getAttribute("username")
                        + "' AND assignment.IDTask=task.IDTask AND task.IDCategory=category.IDCategory "
                        + "UNION DISTINCT SELECT category.IDCategory,category.CategoryName,category.Creator FROM authority,"
                        + "category WHERE authority.Username='" + session.getAttribute("username")
                        + "' AND authority.IDCategory=category.IDCategory UNION DISTINCT "
                        + "SELECT IDCategory,CategoryName,category.Creator FROM category WHERE "
                        + "Creator='" + session.getAttribute("username") + "' ORDER BY CategoryName");
                while (result.next()) {
            %>
            <div class="kategori" onclick="showTask(<%=result.getString(1)%>);">
                <a><%=result.getString(2)%></a>
            </div>
            <%
                if (result.getString(3).compareToIgnoreCase(session.getAttribute("username").toString()) == 0) {
            %>
            <img class="delcategory" src="img/delete.png" onclick="delCate(<%=result.getString(1)%>); ? > );">
            <%
                    }
                }
            %>
        </div> 

        <div id ="listtugas" class="list">
            <%
                result = statement.executeQuery(
                        "SELECT DISTINCT task.* FROM assignment,task,category,authority WHERE "
                        + "assignment.Username='" + session.getAttribute("username")
                        + "' AND assignment.IDTask=task.IDTask AND "
                        + "task.IDCategory=category.IDCategory OR authority.Username='"
                        + session.getAttribute("username") + "'");
                int i = 0;
                while (result.next()) {
            %>
            <a class="listTugas" >
                <%
                    if (result.getString(6).compareToIgnoreCase(session.getAttribute("username").toString()) == 0) {
                %>
                <img class="deltask" src="img/delete.png" onclick="deleteTaskYeys(<%=result.getString(1)%>);">
                <%
                    }%>
                <br><b>TaskName : </b><br>
                <div class="showRin" onclick=" showRinciTugas(<%=result.getString(1)%>);">
                    <%=result.getString(3)%>
                </div>
                <br><b>Deadline : </b><br>
                <%=result.getString(5)%>
                <br><b>Tag : </b><br>
                <% Statement statements = conn.createStatement();
                    ResultSet data = statements.executeQuery(
                        "SELECT tag.* FROM tag,tasktag WHERE tag.IDTag=tasktag.IDTag AND tasktag.IDTask='"+
                        result.getString(1)+ "'");
                        while (data.next()){%>
                            <%=data.getString(2)%> 
                        <% out.print("<br>");
                        }
                 %>
                <br><b>Status : </b><br> <% out.print("<input type='checkbox' id='taskstat" + i + "'");
                    if (result.getString(4).compareToIgnoreCase("done") == 0) {
                        out.print("checked='checked'");
                    }%>
                   onclick="changeStatus(this,<%=result.getString(4)%>,<% out.print(i); %>);">
                <% out.print ("<span id='checkedvalue"+i+"'>");%>
                    <%=result.getString(4)%>
                    <%out.print("</span>");%>
                    </a>
                    <%
                        i++;
                    }
                %>
    </div>

    <div id="addCat">
        <a onclick="addCategory();">+ category</a>
    </div>

    <div id='add'>

        <form id="addCatForm" method="post" action="controller?type=addCat" enctype="multipart/form-data">
            <br/>
            Category Name:<br/>
            <input type="text" id="newcat" name="newcat" required ><br>
            User:<br/>

            <br>
            <input type="submit" id="newcatbutton" name="submit" value="create">
            <input type="submit" onclick="restore();" value="cancel">

            <br/>
        </form>
    </div>
</body>  

<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="ajax.js"></script>

</html>
