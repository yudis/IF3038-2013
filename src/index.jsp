<%-- 
    Document   : index
    Created on : Apr 11, 2013, 10:55:56 AM
    Author     : Arief
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("sUsername")!=null)
        response.sendRedirect("dashboard.jsp");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>S.Y.N Login Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="loginpage.css" type="text/css" media="screen">
        <script src="registrasi.js"></script>
    </head>
    <body>
        <div id="Front_Logo">
            <img src="images/syn.jpg" width="600" height="200">
        </div>
        <div id="Front_Form">
            <h2>Login Form :</h2>
            <form action="loginprocess.jsp" id="LoginForm" method="POST">
                <table width="350px">
                    <tr>
                        <td width="140px">
                            Username
                        </td>
                        <td width="20px">
                            :
                        </td>
                        <td width="140px">
                            <input type="text" name="username" id="username" required>
                        </td>
                    </tr>
                    <tr>
                        <td width="140px">
                            Password
                        </td>
                        <td width="20px">
                            :
                        </td>
                        <td width="140px">
                            <input type="password" name="password" id="password" required>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <input type="submit" value="Login">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <br>
            <%
                if(request.getParameter("error")!=null)
                    out.println("<h1>"+request.getParameter("error")+"</h1>");
            %>
        <br>
        <div id="Front_Form">
            <h2>Registration Form :</h2>
            <form action="registerprocess.jsp" method="POST" id="RegistrationForm"  onsubmit="return checkValidation();">
                <table width="400px">
                    <tr>
                        <td width="140px">
                            Username
                        </td>
                        <td width="20px">
                            :
                        </td>
                        <td width="190px">
                            <input type="text" name="daftar_username" id="daftar_username" onkeyup="checkUsername();checkPassword();" required>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="usernameWarning"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Password
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="password" name="daftar_password" id="daftar_password" onkeyup="checkPassword();"  required>
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
                            <input type="password" name="daftar_confirmpassword" id="daftar_confirmpassword" onkeyup="checkConfirmPassword();"  required>
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
                            <input type="text" name="daftar_namalengkap" id="daftar_namalengkap" onkeyup="checkNama();" required>
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
                            <input type="date" name="daftar_tanggallahir" id="daftar_tanggallahir" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            e-Mail
                        </td>
                        <td>
                            :
                        </td>
                        <td>
                            <input type="text" name="daftar_e-mail" id="daftar_email" onkeyup="checkEmail();checkPassword();" required>
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
                            <input type="file" name="daftar_avatar" id="daftar_avatar" accept="image/jpeg" onchange="check_extension();"  required>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div id="avatarWarning"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Daftar">
                        </td>
                        <td>
                        </td>
                        <td>
                            <input type="reset" value="Batal">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <script type="text/javascript">
        </script>
    </body>
</html>
