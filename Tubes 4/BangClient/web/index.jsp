<%-- 
    Document   : index
    Created on : Apr 4, 2013, 10:02:39 AM
    Author     : Nicholas Rio
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.setMaxInactiveInterval(30*24*3600*1000);
    String username = "";
    if(session.getAttribute("username")!=null){
        username = session.getAttribute("username").toString();
        System.out.println("current session username : "+username);
        response.sendRedirect("dashboard.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css"/>
        <script type="text/javascript" src="Javascript Files/Login Page.js"></script>
        <title>BANG!</title>
    </head>
    <body>
        <img src="img/parchment.jpg" id="homepage-bg" alt="homepage-bg">
        <div id="homepage-header">
            <div id="text-logo">
                <img src="img/logo_small.png" alt="Logo" href="dashboard.html" title="Home"/>
            </div>	
            <div id="login">
                <form name="login" method="POST">
                    Username<input type="text" id="logusername" name="logusername" size="15"/> 
                    Password<input type="password" id="logpassword" name="logpassword" size="10"/> 
                    <input type="button" value="Login" onclick="logindeh();"/>
                </form>
            </div>
        </div>
        <div id="homepage-content">            
            <div id="register">
                <img src="img/register.png" id="reg-bg" alt="reg-bg"/>
                <big><b>Sign yourself up now!</b></big><br>
                <form name="regform" method="POST" action="BangServlet" enctype="multipart/form-data">
                    Username<input type="text" id="regusername" name="regusername" onchange="regFill();"/><br>
                        <small id="errusernamea">Username should be at least 5 characters long</br></small>
                        <small id="errusernameb">Username and password cannot be identical</br></small>
                        <small id="errusernamec">This username has already been used</br></small>
                    Name<input type="text" id="regname" name="regname" onkeyup="regFill();"/><br>
                        <small id="errname">Name should be constructed by two or more words separated by space</br></small>
                    Birth<input type="date" id="regdate" name="regdate"/><br>
                    Password<input type="password" id="regpassword1" name="regpassword1" onkeyup="regFill();"/><br>
                        <small id="errpassword1a">Password should be at least 8 characters long</br></small>
                        <small id="errpassword1b">Username and password cannot be identical</br></small>
                    Confirm Password<input type="password" id="regpassword2" name="regpassword2" onkeyup="regFill();"/><br>
                        <small id="errpassword2">Confirmed password and password are not the same</br></small>
                    Email<input type="text" id="regemail" name="regemail" onkeyup="regFill();"/><br>
                        <small id="erremaila">There should be at least one character before '@' character</br></small>
                        <small id="erremailb">There should be at least one character between '@' and '.' character</br></small>
                        <small id="erremailc">There should be at least two characters after '.' character</br></small>
                        <small id="erremaild">This email has already been used</br></small>
                    Avatar<input type="file" id="regfile" name="regfile" accept="image/jpg, image/jpeg" onchange="regFill();" multiple/><br>
                    <input id="regbutton" type="submit" value="Register" disabled/>
                </form>
            </div>    
        </div>
    </body>
</html>
