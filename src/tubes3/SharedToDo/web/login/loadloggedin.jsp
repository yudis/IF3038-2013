<%
    String username = (request.getParameter("username") == "") ? "" : request.getParameter("username");
%>
<div id="navigation">
    <a href="../dashboard/"><img src="../images/logo.gif" /></a>
    <a href="../dashboard/">DASHBOARD</a>
    <a href="../profile/?u=<%=username%>&e=1">PROFILE</a>
    <a href="#" onclick="toggleSearch()">SEARCH</a>
    <a href="../index.jsp" onclick="logout()">LOGOUT</a>			
</div>
<div id="search" style="display: inline">
    <input id="searchterm" type="text" size="50%" />
    <select id="searchtype">
        <option value="semua" selected>Semua</option>
        <option value="username">Username saja</option>
        <option value="category">Category saja</option>
        <option value="task">Task saja</option>
    </select>
    <button id="searchbutton" onclick="search()">Search</button>
</div>