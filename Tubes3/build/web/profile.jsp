<%-- 
    Document   : profile
    Created on : Apr 11, 2013, 3:37:56 PM
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
        <title>BANG! - Profile</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <script type="text/javascript" src="script.js"></script>
        <script type="text/javascript">
            function editProfile() {
                alert("edit!");
                var overlay = document.createElement("div");
                overlay.setAttribute("id", "overlay");
                overlay.setAttribute("class", "overlay");
                document.body.appendChild(overlay);
                document.getElementById('edit').style.display = 'block';
            }
        </script>
    </head>
    <%      
            String userID = request.getParameter("user");
            String uname = (String)session.getAttribute("username");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
            
            //TASKDONE
            Statement taskDone = conn.createStatement();
            ResultSet rTaskDone = taskDone.executeQuery("SELECT * FROM assignment, task WHERE task.IDTask = assignment.IDTask AND assignment.Username='"+ userID +"' AND task.Status = 'done' ");
            
            //TASK
            Statement task = conn.createStatement();
            ResultSet rTask = task.executeQuery("SELECT * FROM assignment, task WHERE task.IDTask = assignment.IDTask AND assignment.Username='" + userID + "' AND task.Status = 'undone' ");
            
            //USER-------------------------------------
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM user WHERE username='"+userID+"'");
            while (rs.next()) {    
                String Fullname = rs.getString(2);
                String DOB = rs.getString(4);
                String email = rs.getString(5);
    %>
    <body>
            <a id='editP' onclick="editProfile();">edit profile</a>
            <div id="panel">
                <%
                    if (uname.compareTo(userID) == 0) {
                    }
                %>
            </div>

            <div id="donelist">
                <%
                    while (rTaskDone.next()) {
                        out.print ("<a title='Go to Task' href='RinciTugas.jsp?IDTask="+ rTaskDone.getString("IDTask") +"'>");
                        out.print(rTaskDone.getString("TaskName"));
                        out.print("</a>");
                    }
                %>
            </div>
        
            <div id="todolist">
                <%
                    while (rTask.next()) {
                        out.print ("<a title='Go to Task' href='RinciTugas.jsp?IDTask="+ rTask.getString("IDTask") +"'>");
                        out.print(rTask.getString("TaskName"));
                        out.print("</a>");
                    }
                %>
            </div>
        
            <div id="biodata">
                <img id="foto" src=<%out.print(ava);%>>
                <img id="badge" src="img/badge.png">
                <div id="biousername"><%out.print(userID);%></div>
                <div id="bioleft">
                    Full Name<br>
                    Date of Birth<br>
                    Email<br>
                </div>
                <div id="bioright">
                    <%out.print(Fullname);%> <br/>
                    <%out.print(DOB);%> <br/>
                    <%out.print(email);%> <br/>
                </div>
            </div>
                
                <% } %>
        <div id='edit'>
                <p class="title">edit profile</p>
               
                    <form id="regForm" method="get" action="editprofile" enctype="multipart/form-data">
                    Full name: <input type="text" id="regname" name="regname" pattern="^.+ .+$" ><img id="valid2" src=""><br/>
                    Birthdate: <input type="date" id="regdate" name="regdate" onchange="dateChange();"><img id="valid7" src=""><br/>
                    New Password:<br/><input type="password" id="regpassword1" name="regpassword1" pattern="^.{8,}$"><img id="valid3" src=""><br>
                    Confirm new password:<br/><input type="password" id="regpassword2" name="regpassword2" pattern="^.{8,}$"><img id="valid4" src=""><br>
                    Upload new avatar: <br/><input type="file" id="regfile" name="regfile" onchange="checkImage();"><img id="valid6" src=""><br/><br/>
                    <input type="submit" id ="savechanges" value="savechanges"> <br/>
                </form>
                <input type='submit' onclick="restoreP();" value="cancel">

            </div>

        </body>
        
</html>