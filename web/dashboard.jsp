<%-- 
    Document   : dashboard
    Created on : Apr 5, 2013, 11:06:49 AM
    Author     : LCF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Dashboard</title>
        <link href="styles/header.css" rel="stylesheet" type="text/css" />
        <link href="styles/dashboard.css" rel="stylesheet" type="text/css" />
        <script src="js/dashboard.js"></script>
    </head>

    <body onload="showKategori(2)">
        <div id="container">
            <%@include file="header.jsp" %>

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
            <div class="form_baris">
                <div class="form_kiri">
                    Nama Kategori: 
                </div>
                <div class="form_kanan">
                    <input type="text"/>
                </div>
            </div>
            <div id="fs">
                <fieldset>
                    <legend>Pengguna yang Bisa Mengubah</legend>
                    <div class="gambar_kecil"><img src="../images/ranger1.jpg" title="Ranger1" alt="Ranger1"/><input type="checkbox" name="user_berhak" value="ranger1"/></div>
                    <div class="gambar_kecil"><img src="../images/ranger2.jpg" title="Ranger2" alt="Ranger2"/><input type="checkbox" name="user_berhak" value="ranger2"/></div>
                    <div class="gambar_kecil"><img src="../images/ranger3.jpg" title="Ranger3" alt="Ranger3"/><input type="checkbox" name="user_berhak" value="ranger3"/></div>
                    <div class="gambar_kecil"><img src="../images/ranger4.jpg" title="Ranger4" alt="Ranger4"/><input type="checkbox" name="user_berhak" value="ranger4"/></div>
                    <div class="gambar_kecil"><img src="../images/ranger5.jpg" title="Ranger5" alt="Ranger5"/><input type="checkbox" name="user_berhak" value="ranger5"/></div>
                </fieldset>
            </div>
            <div class="form_baris">
                <input type="submit" name="submit" value="Buat Kategori" id="button_buat_kategori"/>
            </div>
            <a class="close" href="#close"></a>
        </div>
    </body>
</html>