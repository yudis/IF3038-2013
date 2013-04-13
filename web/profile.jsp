<%-- 
    Document   : profile
    Created on : Apr 13, 2013, 1:21:59 AM
    Author     : user
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
    } else {
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Profile</title>
        <link href="styles/profile.css" rel="stylesheet" type="text/css" />
        <link href="styles/header.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/profile.js"></script>
        <script type="text/javascript" src="js/popup.js"></script>
        <link rel="stylesheet" type="text/css" href="styles/profile2.css">
    </head>

    <body>
        <div id="container" >
            <%@include file="header.jsp" %>

            <%
                String nama = null;
                String email = null;
                String namaleng = null;
                String tanggal = null;
                String ava = null;

                try {
                    ResultSet rs = null;
                    Statement s = null;
                    Connection con = null;
                    String name = (String) session.getAttribute("username");
                    String connectionURL = "jdbc:mysql://localhost:3306/progin";
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(connectionURL, "progin", "progin");
                    s = con.createStatement();
                    rs = s.executeQuery("select* from accounts where username='" + name + "'");

                    if (rs.next()) {
                        nama = rs.getString("username");
                        namaleng = rs.getString("nama_lengkap");
                        tanggal = rs.getString("tgl_lahir");
                        email = rs.getString("email");
                        ava = rs.getString("avatar");
                    }

                    rs.close();
                    s.close();
                    con.close();

                } catch (Exception e) {
                    out.println("Unable to connect to database.");
                }

            %>

            <div id="profilearea">
                <div class="profilephoto">
                    <img  src=<% out.print("'");
                                    out.println(ava);
                                    out.print("'");%>/>
                </div>
                <div class="biodata">
                    <br>
                    Username  : <% out.println(nama);%><br><br>
                    Fullname  : <% out.println(namaleng);%><br><br>
                    Birthdate : <% out.println(tanggal);%><br><br>
                    Email	  : <% out.println(email);%><br>
                    <button type="button" id="editbutton" onclick="popup('popUpDiv')"></button>
                </div>
            </div>
            <div id="listarea">
                <div id="undonetasktitle">
                    Undone Task
                    <input type="button" onclick="showUndoneTask()" value="Show">
                </div>
                <div id="donetasktitle">
                    Done Task
                    <input type="button" onclick="showDoneTask()" value="Show">
                </div>
                <div id="undonetask">
                    
                </div>
                <div id="donetask">
                    
                </div>
            </div>
            <div id="blanket" style="display:none;"></div>
            <div id="popUpDiv" style="display: none;">
                <div id="newcategory">
                    <div id="closeedit">
                        <a href="" onclick="popup('popUpDiv')" >CLOSE</a>
                    </div>
                    <div id="edittitle">Edit Profile</div>
                    <div id="elcategory">
                        <form name="registration" action="editprofile" method="post" enctype="multipart/form-data">
                            <br>
                            <label>Nama Lengkap</label>
                            <input name="namaleng" placeholder="Nama Lengkap" onkeyup="nama_validatingprof()">
                            <img src="images/blank.png" alt="icon2" id="nameicon"  />
                            <br>
                            <label>Avatar</label>
                            <input type="file" name="avatar" onchange="avatar_validatingprof()" />
                            <img src="images/blank.png" alt="icon7" id="avaicon" />
                            <br>
                            <label>tanggal lahir</label>
                            <input type="text" placeholder="Tanggal Lahir" name="tanggal" id="date" onkeyup="date_validatingprof()" />
                            <img src="images/blank.png" alt="icon8" id="dateicon"  />
                            <br>
                            <label>Ubah Password</label>
                            <input name="password" type="password" placeholder="password" onkeyup="pass_validatingprof()"  />
                            <img src="images/blank.png" alt="icon3" id="passicon" />
                            <br>
                            <label>Confirm Password</label>
                            <input name="confirmpass" type="password" placeholder="confirm password" onkeyup="conf_validatingprof()"   />
                            <img src="images/blank.png" alt="icon4" id="conficon" />
                            <input class= "submitreg" disabled id="submitedit" name="submit" type="submit" value="Save Change">
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </body>
</html>
