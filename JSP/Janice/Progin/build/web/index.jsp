<%-- 
    Document   : index
    Created on : Apr 3, 2013, 9:55:32 PM
    Author     : TOSHIBA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Todolist</title>
        <link rel="stylesheet" type="text/css" href="default.css" />
        <link rel="stylesheet" type="text/css" href="mediaqueries.css" />
    </head>
    <body>
        <div id="blanket"></div>
        <div class="page">
            <header class="content">
                <nav>
                    <div class="logo"><a href="dashboard.html"><img alt="Home" src="images/logo.png" /></a></div>
                    <ul>
                        <li><div><a href="dashboard.html">Dashboard</a></div></li><li><div><a href="profile.html">Profile</a></div></li><li><div><a href="search.html">Search</a></div></li><li><div><a href="index.html">Logout</a></div></li>
                    </ul>
                    <div class="search">
                        <div id="searchwrapper">
                            <form action="index.jsp" method="POST">
                                <input type="text" id="search" class="searchbox" name="searchword_header" value="" placeholder="Search.." />
                                <button type="button"  name="sumbit" class="searchbox_submit" alt="search..." onClick="SearchmodeHeader()"/><img src="images/search.png" class="search_image"></button>
                                <button type="button" name="filter" class="filter_submit" onClick="showfilterbox()"><img src = "images/filterarrow.png" class="filter_image"></button>
                            </form>
                            
                        </div>
                        
                        <div id = "f_menu">
                        <form>
                            	<div id= "menuleft">
                                	<div id = "mainfilter"><input type = "radio" name="filter" value="all" id = "al" checked>All</input></div>
                                    <div id = "mainfilter"><input type = "radio" name="filter" value="username" id = "user">Username</input></div>
                                    <!--<div id = "subfilter"><input type = "checkbox" name="filter" value="user_otherfiltering" id = "other_user">Other Filtering</input></div>-->
                                    <div id = "mainfilter"><input type = "radio" name="filter" value="category" id="categ">Judul Kategori</input></div>
                                </div>
                                <div id = "verticalline"></div>
                                <div id = "menuright">
                                    <div id = "mainfilter"><input type = "radio" name="filter" value="judultask" id="taskname">Judul Task</input></div>
                                    <div id = "mainfilter"><input type = "radio" name="filter" value="tag" id="tag">Tag</input></div>
                                    <!--<div id = "subfilter"><input type = "checkbox" name="filter" value="judultask" id="other_task">Other Filtering</input></div>-->
                                </div>
                         </form>
                        </div>
                           
                    </div>
                </nav>
            </header>
            <div class="content">
                <div class="sidebar">
                    <ul id="FilterKategori" class="nav">
                    <form action="">
                   	 	<input type="radio" name="searchmode" value="all" id="all_rb" checked>All<br>
                        <input type="radio" name="searchmode" value="username" id="username_rb">Username<br>
                       	<input type="radio" name="searchmode" value="category" id="category_rb">Category<br>
                        <input type="radio" name="searchmode" value="taskname" id="taskname_rb">Taskname<br>
                       	<input type="radio" name="searchmode" value="tag" id="tag_rb">Tag<br>
					</form>
                    </ul>
                </div>
                <div id="listTugas" class="main">
                    <h1 class="inlineblock">Search </h1>
                    
                    <div id = "searchtextfield">
                    	<input type="text" id="search_textfield" class="searchbox_textfield" name="q" value="" placeholder="Search.." />
                        <button type="button"  name="sumbit" class="searchbox_submit_textfield" alt="search..." onClick="Searchmode()"/><img src="images/search.png" class="search_image_textfield"></button>
                    </div>
                    <section id="main-F0">
                     
                    </section>
                   
                </div>
               
            </div>
            
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.<br />
                IF3094 - Pemrograman Internet.
            </footer>
        </div>
       	<script src="scripts/helper.js" type="application/javascript"></script>
        <script src="scripts/popup.js" type="application/javascript"></script>
        <script src="scripts/search.js" type="application/javascript"></script>
        <%
            if ((request.getParameter("s")!=null)&&(request.getParameter("m")!=null)&&(request.getParameter("page")!=null)){
                String s = request.getParameter("s");
                String m = request.getParameter("m");   
                String pageparam = request.getParameter("page");%>
                <script type ="text/javascript">
                    Searchbypage('<%=s%>','<%=m%>','<%=pageparam%>');
                </script><%
            }else if ((request.getParameter("s")!=null)&&(request.getParameter("m")!=null)) {
                String s = request.getParameter("s");
                String m = request.getParameter("m");  %>
                <script type ="text/javascript">
                    Search("<%=s%>","<%=m%>");
                </script>
          <%  }
			
			
			
	%>
    </body> 
</html>

