<%@page import="tubes3.SetAvatar"%>
<%
    String username = null;
    SetAvatar avtar = null;
    if (((HttpServletRequest) request).getSession().getAttribute("bananauser") == null)
        ((HttpServletResponse) response).sendRedirect("index.jsp");
    else {
        username = ((HttpServletRequest) request).getSession().getAttribute("bananauser").toString();
        avtar = new SetAvatar();
    }
%>

<!DOCTYPE html>
<html>	
	<head>
            <title> Banana Board - Home </title>
            <link rel="stylesheet" type="text/css" href="style.css">
            <script src="Dashboard.js" type="text/javascript" language="javascript"> </script>
            <script src="Raymond.js" type="text/javascript" language="javascript"> </script>
            <script src="datetimepicker_css.js" type="text/javascript" language="javascript"> </script>
	</head>
        <body onLoad ="initDashboard()">	
        <div id="content">	
            <div id="header">
                <div id="logo" >
                    <a href="home.jsp"><img src="image/logo.png"/></a>
                </div>
                <div id="menu">
                    <ul>
                        <li> <a href="home.jsp"> DASHBOARD </a> </li>
                        <li> <a href="profile.jsp"> PROFILE </a> </li>
                        <li> <a href="Logout.jsp"> LOGOUT </a> </li>
                    </ul>
                    <form method="post" action="searchresult.jsp" class="setSpan">
                        <a href="profile.jsp"><span><% if(username != null) out.print(username);%></span><img src="<% if(avtar != null) out.print(avtar.getAvatar(username));%>" id="profPic"/></a><!-- TODO -->
                        <select name="filter">
                            <option value="semua">Semua</option>
                            <option value="username">User Name</option>
                            <option value="judul">Judul Kategori</option>
                            <option value="task">Task</option>
                        </select>
                        <input name="keyword" id="keyword" class="box" type="text" onclick="this.value='';" onfocus="this.select()" onblur="this.value=!this.value?'Enter search query':this.value;" value="Enter search query" onKeyUp="searchSuggestKeyword()">
                        <input id="searchbutton" type="submit" value="">
                    </form>
                    <div id="layer"></div>
                </div>
            </div>
            <div id="isi">
                <div id="leftsidebar">
                    <ul id='categlist'>
                    </ul>
                    <img src="image/leftmenu.png"/>
                </div>

                <div id="rightsidebar">
                    <button id='addtask'>Tambah Tugas</button>
                    <ul id="kegiatan">
                    </ul>
                </div>

                <div id="footer" class="home">
                    <p>&copy; Copyright 2013. All rights reserved<br>
                    Chalkz Team<br>
                    Yulianti - Raymond - Devin</p>			
                </div>
            </div>
        </div>
        <a href='#' class='overlay' id='addcategory'></a>
        <div class="popup">
            <form action="javascript:addCategory()" method="POST">
                <ul>
                    <li>
                        <label>Nama Kategori</label>
                        :<input id="namakategori" type="text" name="namakategori" /><br/>
                    </li>
                    <li>
                        <label>Daftar Pengguna</label>
                        :<input type="text" name="daftarpengguna" id="daftarpengguna"/><br/>
                    </li>
                    <li>
                        <button type="submit" id="add"><b>Add</b></button>
                        <button id="cancel" onclick='location.href="#"'><b>Cancel</b></button>
                    </li>						
                </ul>
            </form>
        </div>
    </body>
</html>
