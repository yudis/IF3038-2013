<%-- 
    Document   : halamanRincianTugas
    Created on : Apr 13, 2013, 10:22:20 PM
    Author     : Sonny Theo Thumbur
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detil Tugas</title>
        <link rel="stylesheet" type="text/css" href="style/style.css"></link>
        <script LANGUAGE="Javascript" src="script/script.js"></script>
    </head>
    <body>
        <%
        //            ambil variabel session
        String curUser = "";
            if (session.getAttribute("username") == null) {
                response.sendRedirect("index.jsp");
            } else {
                curUser = (String) session.getAttribute("username");
            }
        %>
        <form action="Suggestion" method="get">
    	<div id="header">
            <a href="dashboard.jsp"><img id="logo" src="res/logo1.png" alt="to-do list"></img></a>
            <a id="dashboardLink" href="dashboard.jsp">dashboard</a>
            <input id="searchForm" type="text" name="keyword" onkeyup="showHint(this.value)" placeholder="search"></input>
            <select id="filter" name="filter">
                <!--<option selected>Select Filter ...</option>-->
                <option>all</option>
                <option>user</option>
                <option>category</option>
                <option>task</option>
            </select>
            <input id="submitForm" type="submit" name="search" value="search" onclick="toSearchResult(searchForm.value,filter.value)">
	    <a href="profile.jsp"><img id="profile" src="server/<%= curUser %>.png" onclick="keProfil()"/></a>
	    <a id="logout" href="logout.php">Log Out</a>
	    <div class="suggest">Suggestion : <span id="textHint"></span></div>
        </div>
	</form>
        
        <div id="spasi">
        </div>
        
        <%

            
//            String taskToShow = (String) request.getAttribute("task");
            String taskToShow = request.getParameter("task");
            String idStatus = taskToShow + "status";        
//            koneksi database
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();

            Connection con = null;
            ResultSet rs = null;
            Statement stmt = null;

            String url = "jdbc:mysql://localhost:3306/progin_405_13510027?user=progin&password=progin";
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String sql = "SELECT * FROM task WHERE namaTask='" + taskToShow + "'";
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                String status = rs.getString("status");
                String deadline = rs.getString("deadline");
        %>
        <div id="taskdetailcontainer">
            <div class="taskElmtLeft">
                <p><strong><%= taskToShow %></strong></p>
            </div>
            <div class="taskElmtRight">
            </div>
            <div class="taskElmtLeft">
                <p>Status Tugas : </p>
            </div>
            <div id="<%= idStatus %>" class="taskElmtRight">
                <p><%= status %></p>
            </div>
            <div class="taskElmtLeft">
            </div>
            <div class="taskElmtRight">
                <button class="ubahStatusTask" onclick="changeTaskStatus('<%= taskToShow %>','<%= idStatus %>')">Ubah Status</button>
            </div>
            <div class="taskElmtLeft">
                <p><em>Attachment : </em></p>
            </div>
            <div id="attachmentbox" class="taskElmtRight">
            <%
                Statement stmtAtt = con.createStatement();
                String sqlAtt = "SELECT attachment FROM attach WHERE namaTask='" + taskToShow + "'";
                ResultSet rsAtt = stmtAtt.executeQuery(sqlAtt);
                while (rsAtt.next()) {
                    String attachment = rsAtt.getString("attachment");
                    String type = attachment.substring(attachment.length()-3);
                    if ((type.equals("mp4")) || (type.equals("FLV"))) {
            %>
                            <video width="500" height="400" controls>
                                <source src="server/<%= attachment %>" type="video/mp4">
                            </video>
            <%
                    } else if (type.equals("png")) {
            %>
                            <img src="server/<%= attachment %>" alt="user attachment" height="100" width="100"/>
            <%
                    }
            %>
            <p><%= attachment %></p>
            <%
                }
            %>
            </div>
            <div class="taskElmtLeft">
                <p>Deadline : </p>
            </div>
            <div class="taskElmtRight">
                <p><%= deadline %></p>
            </div>
            <div class="taskElmtLeft">
                <p>Assignee : </p>
           </div>
            <div class="taskElmtRight">
            </div>
            <%
                Statement stmtAss = con.createStatement();
                String sqlAss = "SELECT asigneeName FROM tasktoasignee WHERE namaTask='" + taskToShow + "'";
                ResultSet rsAss = stmtAss.executeQuery(sqlAss);
                while (rsAss.next()) {
                    String assName = rsAss.getString("asigneeName");
            %>
                    <div class="taskElmtLeft">
                        <img src="server/<%= assName %>.png" alt="user attachment" height="50" width="50" align="right"/>
                    </div>
                    <div id="<%= assName %>" class="taskElmtRight" onclick="toUserProfile(this.id)">
                        <p><%= assName %></p>
                    </div>
            <%
                }
            %>
            
        </div>
            <div class="taskElmtLeft">
                <p>Komentar :</p>
            </div>
            <div class="taskElmtRight">
            </div>
            
            <%
//                seleksi komentar
                Statement stmtKomentar = con.createStatement();
                String sqlKomentar = "SELECT komentator, isikomentar, timestamp FROM komentar WHERE namaTask='" + taskToShow + "'";
                ResultSet rsKomentar = stmtKomentar.executeQuery(sqlKomentar);
                while (rsKomentar.next()) {
//                    Statement stmtKomentator = con.createStatement();
//                    String sqlKomentator = "SELECT avatar FROM user WHERE username='" + rsKomentar.getString("komentator") + "'";
//                    ResultSet rsKomentator = stmtKomentator.executeQuery(sqlKomentator);
            %>
            <div class="taskElmtLeft">
                <img src="server/<%= rsKomentar.getString("komentator") %>.png" alt="UserKomentator" height="50" width="50" align="right"/>
            </div>
            <div class="taskElmtRight">                
                <p><%= rsKomentar.getString("komentator") %></p>
            </div>
            <div class="taskElmtLeft">
                <p><%= rsKomentar.getString("timestamp") %></p>
            </div>
            <div class="taskElmtRight">
                <p><%= rsKomentar.getString("isikomentar") %></p>
            </div>
            <div class="taskElmtLeft">
            </div>
            <div id="DeleteComment" class="taskElmtRight">
                <button class="DeleteComment">Delete</button>
            </div>
        <%
                }
           }
            
//            seleksi Tag
            Statement stmtTag = con.createStatement();
            String sqlTag = "SELECT tag FROM tagging WHERE namaTask='" + taskToShow + "'";
            ResultSet rsTag = stmtTag.executeQuery(sqlTag);
            
            ArrayList<String> tagList = new ArrayList<String>();
            String tagTotal = "";
            while (rsTag.next()) {
                if (!tagList.contains(rsTag.getString("tag"))) {
                    tagList.add(rsTag.getString("tag"));
                    tagTotal += rsTag.getString("tag") + " | ";
                }
            }
        %>
        <div class="taskElmtLeft">
            <p>Tag :</p>
        </div>
        <div class="taskElmtRight">
            <p><%= tagTotal %></p>
        </div>
        <div class="taskElmtLeft">
        </div>
        <div class="taskElmtRight">
            <textarea rows="15" cols="80" placeholder="Masukan komentar kamu disini ..."></textarea>
        </div>
        <div class="taskElmtLeft">
        </div>
        <div id="CommentSubmitBtn" class="taskElmtRight">
            <button>Kirim Komentar!</button>
        </div>
        <br/><br/><br/>
    </body>
</html>
