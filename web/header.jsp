<%-- 
    Document   : header
    Created on : Apr 9, 2013, 11:51:34 AM
    Author     : LCF
--%>

<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/suggestion.js"> </script>
<div id="header">
    
    <%
			String namah = null;
                        String avah = null;
                        
                        try{
                            ResultSet rs = null;
                            Statement s = null;
                            Connection con = null;
                            String name = (String) session.getAttribute("username");
                            String connectionURL = "jdbc:mysql://localhost:3306/progin";
                            Class.forName("com.mysql.jdbc.Driver");
                            con = DriverManager.getConnection(connectionURL,"progin","progin");
                            s = con.createStatement();
                            rs =  s.executeQuery("select* from accounts where username='"+name+"'");
                            
                            if(rs.next())
                                {
                                    namah = rs.getString("username");
                                    avah = rs.getString("avatar");
                                }
                            
                            rs.close(); 
                            s.close(); 
                            con.close();
                            
                            }catch(Exception e)
                                {
                                    out.println("Unable to connect to database."); 
                                }
                        
		%>
    
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
        <a href="logout" onclick="localStorage.clear()" >Logout</a>
    </div>
    <div class="menu" id="profile">
        <a href="profile.jsp"><img  src=<% out.print("'");out.println(avah);out.print("'");%>/><% out.print("  ");out.println(namah); %> </a>
        
    </div>
    <div class="menu" id="home">
        <a href="dashboard.jsp">Home</a>
    </div>
</div>
