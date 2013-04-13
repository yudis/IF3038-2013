<%-- 
    Document   : dashboard
    Created on : Apr 5, 2013, 11:06:49 AM
    Author     : LCF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if(session.getAttribute("username")== null)
    {
        response.sendRedirect("index.jsp");
    }
    else
    {
     
    }
%>
<html>
    <head>
        <title>Dashboard</title>
        <link href="styles/header.css" rel="stylesheet" type="text/css" />
        <link href="styles/dashboard.css" rel="stylesheet" type="text/css" />
        <script src="js/dashboard.js"></script>
    </head>

    <body onload="showKategori(<%=session.getAttribute("id")%>)">
        <%@include file="header.jsp" %>
        <div id="container">
            

            <div id="category">
                <div id="category_head">
                    Kategori
                </div>
            </div>

            <div id="task">
                <div id="task_header">
                    Tasks
                </div>
            </div>
        </div>

        <!--Popup bikin kategori baru -->
        <a href="#x" class="overlay" id="category_form"></a>
        <div class="popup">
            <div>Tambah Kategori</div>
            <form action="Kategori" method="post">
                <div class="form_baris">
                    <div class="form_kiri">
                        Nama Kategori: 
                    </div>
                    <div class="form_kanan">
                        <input type="text" name="nama">
                    </div>
                </div>
                <div class="form_baris">
                    <div class="form_kiri">
                        Assignees: 
                    </div>
                    <div class="form_kanan">
                        <input type="text" name="assignees" onkeyup="showResult(this.value)" id="input_assignees">
                        <div id="hasil_autocomplete"> </div>
                    </div>
                </div>
                <div class="form_baris">
                    <input type="submit" name="submit" value="Buat Kategori" id="button_buat_kategori"/>
                </div>
                <a class="close" href="#close"></a>
            </form>
        </div>
    </body>
</html>