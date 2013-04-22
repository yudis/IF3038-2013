<%-- 
    Document   : header
    Created on : Apr 10, 2013, 6:38:39 PM
    Author     : sthobis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<nav>
    <a href="dashboard.jsp"><div class="logo"><img alt="Home" src="images/logo.png" /></div></a>
    <ul>                
    <li><div id = "dashboardlink"><a href="dashboard.jsp">Dashboard</a></div></li><li><div id="profil"></div></li><li><div><a href="index.jsp" onclick="signout()">Logout</a></div></li>
    </ul>
    <div class="search">
                        <div id="searchwrapper">
                            <form action="search.jsp" method="POST">
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
