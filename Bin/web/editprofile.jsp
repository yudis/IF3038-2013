<%-- 
    Document   : editprofile
    Created on : Apr 12, 2013, 8:30:05 AM
    Author     : Arief
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="database.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="loginpage.css" type="text/css" media="screen">
        <script src="editprofile.js"></script>
        <title>Edit Profile</title>
    </head>
    <jsp:include page="header.jsp"/>
    <body onload="checkValidation();">
        &nbsp
        <br>
        &nbsp
        <br>
        &nbsp
        <br>
        &nbsp
        <br>
        <h1>Edit Profile</h1>
        <div id="Front_Form">
            <form action="editprofileprocess.jsp" method="POST" id="EditForm"  onsubmit="return checkValidation();">
                <table width="400px">
                    <tr>
                        <td>
                            Password
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="password" name="daftar_password" id="daftar_password" onkeyup="checkValidation();">
                            <input type="hidden" name="daftar_username" id="daftar_username" value="<%= session.getAttribute("sUsername") %>">
                            <input type="hidden" name="daftar_username" id="daftar_email" value="<%= Checker.getEmail(session.getAttribute("sUsername").toString()) %>">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="passwordWarning"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Confirm Pasword
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="password" name="daftar_confirmpassword" id="daftar_confirmpassword" onkeyup="checkValidation();">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="confirmPasswordWarning"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Nama Lengkap
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="text" name="daftar_namalengkap" id="daftar_namalengkap" onkeyup="checkValidation();">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="namaWarning"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Tanggal Lahir
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="date" name="daftar_tanggallahir" id="daftar_tanggallahir">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="emailWarning"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Avatar
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="file" name="daftar_avatar" id="daftar_avatar" accept="image/jpeg" onchange="checkValidation();">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="avatarWarning"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Kirim">
                        </td>
                        <td>
                        </td>
                        <td>
                            <input type="reset" value="Batal" onclick="checkValidation();">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
