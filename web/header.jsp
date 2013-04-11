<%-- 
    Document   : header
    Created on : Apr 9, 2013, 11:51:34 AM
    Author     : LCF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/suggestion.js"> </script>
<div id="header">
    <div class=logo id="logo">
        <a href="dashboard.jsp"><img src="images/logo.png" title="Home" alt="Home"/></a>
    </div>
    <div id="space">
    </div>
    <div id="search">
        <form name="cari" method="get" action="search_result.jsp">
            <section id="searchdropdown">
                <select name="key" id="opsisearch">
                    <option value="semua">Semua</option>
                    <option value="username">Username</option>
                    <option value="kategori">Kategori</option>
                    <option value="task">Task</option>
                </select>
            </section>
            <input type="hidden" name="aksi" value="cari">
            <input type="text" name="value" id="searchbox" autocomplete="off" onkeyup="autocomplete_search(document.getElementById('opsisearch').value, this.value)">
            <div id="hasil_ac"></div>
            <button type="submit" id="searchbutton"></button>
        </form>
    </div>
    <div class="menu" id="logout">
        <a href="index.jsp">Logout</a>
    </div>
    <div class="menu" id="profile">
        <a href="profile.jsp">Profile</a>
    </div>
    <div class="menu" id="home">
        <a href="dashboard.jsp">Home</a>
    </div>
</div>
