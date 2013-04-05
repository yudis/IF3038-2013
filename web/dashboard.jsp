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

    </head>

    <body>
        <div id="container">
            <div id="header">
                <div class=logo id="logo">
                    <a href="dashboard.html"><img src="../images/logo.png" title="Home" alt="Home"/></a>
                </div>
                <div id="space">
                </div>
                <div id="search">
                    <input type="text" name="search" id="searchbox">
                    <button type="submit" id="searchbutton"></button>
                </div>
                <div class="menu" id="logout">
                    <a href="index.html">Logout</a>
                </div>
                <div class="menu" id="profile">
                    <a href="profile.html">Profile</a>
                </div>
                <div class="menu" id="home">
                    <a href="dashboard.html">Home</a>
                </div>
            </div>

            <div id="category">
                <div id="category_head">
                    Kategori
                </div>

                <div class="category_block" id="k1" onclick="showK1()">
                    <div class="category_pic">
                        <img src="../images/Book-icon.png" alt=""/>
                    </div>
                    <div class="category_name">
                        Kuliah
                    </div>
                </div>

                <div class="category_block" id="k2" onclick="showK2()">
                    <div class="category_pic">
                        <img src="../images/Book-icon.png" alt=""/>
                    </div>
                    <div class="category_name">
                        Eksperimen
                    </div>
                </div>

                <div class="category_block" id="k3" onclick="showK3()">
                    <div class="category_pic">
                        <img src="../images/menguasai-dunia.png" alt=""/>
                    </div>
                    <div class="category_name">
                        Menguasai Dunia
                    </div>
                </div>

                <div class="category_block" id="tambah_kategori" onclick="location.href='#category_form'">
                    <div class="category_pic">
                        <img src="../images/tambah.png" alt=""/>
                    </div>
                    <div class="category_name">
                        Tambah kategori...
                    </div>
                </div>
            </div>
            <div id="task">
                <div id="task_header">
                    Tasks
                </div>

                <div class="task_block" id="tambah_task" onclick="location.href='newtask.html'">
                    <p>Tambah Task...</p>
                </div>

                <div class="task_block" id="k11">
                    <div class="task_judul">
                        <a href="viewtask.html">Mengerjakan Tugas Progin</a>
                    </div>
                    <div class="task_deadline">
                        Deadline: 21 February 2013
                    </div>
                    <div class="task_tag">
                        Tags: kuliah
                    </div>
                </div>

                <div class="task_block" id="k12">
                    <div class="task_judul">
                        Tugas Intelegensia Buatan
                    </div>
                    <div class="task_deadline">
                        Deadline: 22 February 2013
                    </div>
                    <div class="task_tag">
                        Tags: kuliah, IB, tugas
                    </div>
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

