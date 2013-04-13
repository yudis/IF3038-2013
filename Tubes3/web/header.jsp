<%@page import="java.io.PrintWriter"%>
<% 
if (session.getAttribute("username") == null) {
    response.sendRedirect("index.jsp");
}
String username = (String)session.getAttribute("username");
String ava = (String)session.getAttribute("ava");
%>
<header>
    <a href="dashboard.php" title="Home"><img id="logo-small" src="img/Logo_Small2.png" alt="" /></a>
    <div id="dashboard"><a title="Go to Dashboard" href="dashboard.jsp">Dashboard</a></div>

    <div id="profile">
        <%out.print("<a title=\"Go to Profile\" href=\"profile.jsp?user="+username+"\"</a>"+username);%>
    </div>
    <div id="logout"><a title="Log out from here" href="logout">Log Out</a></div>
    <form id="search" method="post" action="search.jsp">
        <input type="text" name="searchquery" id="searchquery">
        <select id="tipe" name="tipe">
            <option value="All"> All </option>   
            <option value="Category"> Category </option>
            <option value="Task"> Task </option>
            <option value="Username"> Username </option>
        </select>
        <input type="submit" value="Search">
    </form>
    <% out.print("<img id=\"smallava\" src=\""+ava+"\"/>");%>
</header>