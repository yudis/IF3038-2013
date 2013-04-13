<%-- 
    Document   : resultPage
    Created on : Apr 12, 2013, 11:10:47 PM
    Author     : Nicholas Rio
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Utility.DBUtil"%>
<%
    Connection con = DBUtil.getConnection();
    String username = "";
    if(session.getAttribute("username")!=null){
        username = session.getAttribute("username").toString();
    }else{
        response.sendRedirect("index.jsp");
    }
%>

<!DOCTYPE html>
<!DOCTYPE html>
<html>

    <head>
        <title>BANG! - Dashboard</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS Files/resultpage.css" media="screen" />
        <jsp:include page="header.jsp" />
    </head>
    <body>
        <%
            String opt = request.getParameter("options");
            String box = request.getParameter("Search");
            String query = "";
            PreparedStatement ps;
            ResultSet rs;
        %>
        <div id = "wall">
            <div id="category_wall">
                <%
                    out.write("<div class='resulttitle'>Category Result</div>");        
                    if (opt.equalsIgnoreCase("c") || opt.equalsIgnoreCase("a")) {
                        query = "SELECT id_category,name FROM category WHERE name LIKE '%" + box + "%'";
                        ps = con.prepareStatement(query);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            out.write("<div class='resultelm'>");
                            out.write(rs.getString(1)+"<br/>");
                            out.write(rs.getString(2)+"<br/>");
                            out.write("</div>");
                        }
                    }
                %>
            </div>
            <div id="user_wall">
                <%
                    out.write("<div class='resulttitle'>User Result</div>");
                    if (opt.equalsIgnoreCase("u") || opt.equalsIgnoreCase("a")) {
                        query = "SELECT username,fullname,avatar FROM user WHERE username LIKE '%" + box + "%' OR fullname LIKE '%" + box + "%' OR email LIKE '%"
                                + box + "%' OR dob LIKE '%" + box + "%'";
                        ps = con.prepareStatement(query);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            out.write("<div class='resultelm'>");
                            out.write("<a href='profile.jsp?userprofile="+rs.getString(1)+"'>");
                            out.write(rs.getString(1)+"<br/>");
                            out.write("</a>");
                            out.write(rs.getString(2)+"<br/>");
                            out.write("<img src='" + rs.getString(3) + "'></img>"+"<br/>");
                            out.write("</div>");
                        }
                    }
                %>
            </div>
            <div id="task_wall">
                <%
                    out.write("<div class='resulttitle'>Task Result</div>");
                    query = "SELECT DISTINCT t.id_task, t.name, t.deadline, t.status FROM task AS t "
                            + "INNER JOIN ttrelation AS tt "
                            + "ON t.id_task = tt.id_task "
                            + "INNER JOIN tag AS ta "
                            + "ON tt.id_tag = ta.id_tag "
                            + "INNER JOIN utrelation AS u "
                            + "ON t.id_task = u.id_task "
                            + "WHERE (t.name LIKE '%" + box + "%' OR ta.name LIKE '%" + box + "%') AND u.username = '" + username + "'";
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        String id = rs.getString(1);
                        out.write("<div class='resultelm'>");
                        out.write("<a href='dashboard.jsp?seektask="+rs.getString(1)+"'>");
                        out.write(rs.getString(1)+"<br/>");
                        out.write("</a>");
                        out.write(rs.getString(2)+"<br/>");
                        out.write(rs.getString(3)+"<br/>");
                        if(rs.getString(4).equalsIgnoreCase("T")){
                            out.write("<input type='checkbox' name='task"+id+"' id='task"+id+"'onclick='changeTaskStatus("+id+",this.checked)' checked='checked'/>"+"<br/>");
                        }else{
                            out.write("<input type='checkbox' name='task"+id+"' id='task"+id+"'onclick='changeTaskStatus("+id+",this.checked)'/>"+"<br/>");
                        }
                        out.write("</div>");
                    }
                %>
            </div>
        </div>
    </body>
</html>
