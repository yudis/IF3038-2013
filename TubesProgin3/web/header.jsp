<!DOCTYPE html>
<html>	
    <head>
        <title> Banana Board - Home </title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <script src="Dashboard.js" type="text/javascript" language="javascript"> </script>
        <script src="Raymond.js" type="text/javascript" language="javascript"> </script>
        <script src="datetimepicker_css.js" type="text/javascript" language="javascript"> </script>

    </head>
    <body>	
        <div id="content">	
            <div id="header">
                <div id="logo">
                    <img src="image/logo.png"/>
                </div>
                <div id="menu">
                    <ul>
                        <li> <a href="home.jsp"> DASHBOARD </a> </li>
                        <li> <a href="profile.jsp"> PROFILE </a> </li>
                        <li> <a href="logout.jsp"> LOGOUT </a> </li>
                    </ul>
                    <form method="post" action="searchresult.jsp">

                        <img src="image/avatar.jpg" id="profPic"></img>
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