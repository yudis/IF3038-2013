<%@page import="progin.UserBean"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection" %>


<%@ page language="java" 
    contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
%>
<!DOCTYPE html>
<html>
    <!--<?php
	include('config.php');
	session_start();
	if (isset($_REQUEST['username']) && isset($_REQUEST['namatugas'])){
		$namauser = stripslashes($_REQUEST['username']);
		$namatugas = stripslashes($_REQUEST['namatugas']);
		$_SESSION['namatugas'] = $namatugas; 
		$_SESSION['namauser'] = $namauser;
	} else {
		$namauser = $_SESSION['namauser'];
		$namatugas = $_SESSION['namatugas'];
	}
?>  -->
	<head>
		<link href="style2.css" rel="stylesheet" type="text/css"></link>
		<script src="script.js" type="text/javascript" ></script>
		<link rel="stylesheet" href="style.css" type="text/css">
		<title>Rincian Tugas<% out.print("Namatugas");%></title>
                <%
                    Connection connection = null;
                    try{
                        String url = "jdbc:mysql:" + "//localhost:3306/progin_405_13510074";
                        Class.forName("com.mysql.jdbc.Driver");
                        connection = DriverManager.getConnection(url, "root", "");
                        Statement s = connection.createStatement();
                        if (s != null)
                            pageContext.setAttribute("statement", s);
                    } catch (ClassNotFoundException e1) {
                        // JDBC driver class not found, print error message to the console
                        e1.printStackTrace();
                    } catch (Exception e3){
                        e3.printStackTrace();
                    }
                %>
        </head>
	<body>
		<header>
			<div id="header">
				<div id="topbar">
					<div id="topbar_logo">
						<a href="dashboard"><img id="logo" src="img/logo.png"></a>
					</div>
					<div id="topbar_logo2">
						<img id="logo2" src="img/namalogo.png">
					</div>
					<div id="topbar_border"></div>
					<div id="topbar_dashboard">
						<nav>
							<ul>
								<li> <a class="active" href="dashboard.jsp"> Dashboard </a> </li>
								<li> <a href="profil.jsp">Profil</a> </li>
								<li> <a href="index.jsp">Logout</a> </li>
							</ul>
						</nav>
					</div>
					<div id="topbar_search">  
						<input type="search" results="5" placeholder="search"/>
					</div>
                                          <div id="showUser">
                                            <% 
                                                UserBean user1 = ((UserBean)session.getAttribute("currentSessionUser"));
                                            %>
                                            User: <%= user1.getUsername() %>
                                        </div>
				</div>
			</div>
		</header>
                <div id="container">
                        <div id="edited">
                                <div id="rincian">
                                        <pab>Nama task</pab>
                                        <pab>Attachment</pab>
                                        <pab>Deadline</pab>
                                        <pab>Assignee</pab>
                                        <pab>Tag</pab>
                                </div>
                                <div id="isi">
                                        <pae id="namatugas"><%
                                        String namatask = (String) request.getSession(false).getAttribute("namatask");
                                        out.print(namatask);
                                        //String user = "dummy";
                                        String user = (String) request.getSession(false).getAttribute("user");
                                        request.getSession(false).setAttribute("user", user);
                                        %></pae><br>
                                        <pae id="fileattach"><% out.print("Attachment"); %></pae><br>
                                        <pae id="tanggaldeadline"><%
                                        try {
                                            Statement s1 = (Statement) pageContext.getAttribute("statement");
                                            namatask = (String) request.getSession(false).getAttribute("namatask");
                                            String sql = "SELECT deadline FROM tugas WHERE username='" +user+ "' AND namatugas='" +namatask+ "'";
                                            ResultSet rs = s1.executeQuery(sql);
                                            while (rs.next()){
                                                out.print(rs.getDate(1));
                                            }
                                            //  rs.close();
                                            //  s1.close();
                                        } catch (SQLException e2) {
                                            // Exception when executing java.sql related commands, print error message to the console
                                            e2.printStackTrace();
                                        }
                                        %></pae><br>
                                        <pae id="listassign"><%
                                            try {
                                                Statement s1 = (Statement) pageContext.getAttribute("statement");
                                                // String user = (String) request.getSession(false).getAttribute("user");
                                                String sql = "SELECT asignee FROM asigner WHERE username='" +user+ "' AND namatugas='" +namatask+ "'";
                                                ResultSet rs = s1.executeQuery(sql);
                                                if (rs.isLast()){
                                                    out.print(rs.getString(1)) ;
                                                } else {
                                                    while (rs.next()){
                                                        out.print(rs.getString(1));
                                                        if (!rs.isLast())
                                                            out.print(", ");
                                                    }
                                                }
                                                //   rs.close();
                                                //   s1.close();
                                            } catch (SQLException e2){
                                                e2.printStackTrace();
                                            }
                                        %></pae><br>
                                        <pae id="listtag"><%
                                            try {
                                                Statement s1 = (Statement) pageContext.getAttribute("statement");
                                                // String user = (String) request.getSession(false).getAttribute("user");
                                                String sql = "SELECT tag FROM tag WHERE username='" +user+ "' AND namatugas='" +namatask+ "'";
                                                ResultSet rs = s1.executeQuery(sql);
                                                if (rs.isLast()){
                                                    out.print(rs.getString(1)) ;
                                                } else {
                                                    while (rs.next()){
                                                        out.print(rs.getString(1));
                                                        if (!rs.isLast())
                                                            out.print(", ");
                                                    }
                                                }
                                                //   rs.close();
                                                //   s1.close();
                                            } catch (SQLException e2){
                                                e2.printStackTrace();
                                            }
                                        %></pae><br>
                                </div>
                        </div>
                        <form name="edittugas" action="taskdetail" method="POST">        
                            <div id="editing">
                                <div id="rincian">
                                        <pab>Nama task</pab>
                                        <pab>Attachment</pab>
                                        <pab>Deadline</pab>
                                        <pab>Assignee</pab>
                                        <pab>Tag</pab>
                                </div>
                                <div id="isi">
                                        <pae><% out.print(namatask);%></pae><br>
                                        <pae>Attachment</pae><br>
                                        <pae><select id="tahun" name="tahun">
                                                <option value = "2013">2013</option>
                                                <option value = "2014">2014</option>
                                                <option value = "2015">2015</option>
                                                <option value = "2016">2016</option>
                                                <option value = "2017">2017</option>
                                                <option value = "2018">2018</option>
                                                <option value = "2019">2019</option>
                                                <option value = "2020">2020</option>
                                                <option value = "2021">2021</option>
                                                <option value = "2022">2022</option>
                                                </select>
                                                <select id="bulan" name="bulan">
                                                <option value = "1">1</option>
                                                <option value = "2">2</option>
                                                <option value = "3">3</option>
                                                <option value = "4">4</option>
                                                <option value = "5">5</option>
                                                <option value = "6">6</option>
                                                <option value = "7">7</option>
                                                <option value = "8">8</option>
                                                <option value = "9">9</option>
                                                <option value = "10">10</option>
                                                <option value = "11">11</option>
                                                <option value = "12">12</option>
                                                </select>
                                                <select id="tanggal" name="tanggal">
                                                <option value = "1">1</option>
                                                <option value = "2">2</option>
                                                <option value = "3">3</option>
                                                <option value = "4">4</option>
                                                <option value = "5">5</option>
                                                <option value = "6">6</option>
                                                <option value = "7">7</option>
                                                <option value = "8">8</option>
                                                <option value = "9">9</option>
                                                <option value = "10">10</option>
                                                <option value = "11">11</option>
                                                <option value = "12">12</option>
                                                <option value = "13">13</option>
                                                <option value = "14">14</option>
                                                <option value = "15">15</option>
                                                <option value = "16">16</option>
                                                <option value = "17">17</option>
                                                <option value = "18">18</option>
                                                <option value = "19">19</option>
                                                <option value = "20">20</option>
                                                <option value = "21">21</option>
                                                <option value = "22">22</option>
                                                <option value = "23">23</option>
                                                <option value = "24">24</option>
                                                <option value = "25">25</option>
                                                <option value = "26">26</option>
                                                <option value = "27">27</option>
                                                <option value = "28">28</option>
                                                <option value = "29">29</option>
                                                <option value = "30">30</option>
                                                <option value = "31">31</option>
                                                </select>				
                                                </pae><br>
                                        <pae><input type="text" id="asignee" name="asignee" size="25" value="<%
                                            try {
                                                Statement s1 = (Statement) pageContext.getAttribute("statement");
                                                // String user = (String) request.getSession(false).getAttribute("user");
                                                String sql = "SELECT asignee FROM asigner WHERE username='" +user+ "' AND namatugas='" +namatask+ "'";
                                                ResultSet rs = s1.executeQuery(sql);
                                                if (rs.isLast()){
                                                    out.print(rs.getString(1)) ;
                                                } else {
                                                    while (rs.next()){
                                                        out.print(rs.getString(1));
                                                        if (!rs.isLast())
                                                            out.print(", ");
                                                    }
                                                }
                                                //  rs.close();
                                                //  s1.close();
                                            } catch (SQLException e2){
                                                e2.printStackTrace();
                                            }
                                        %>"></pae><br>
                                        <pae><input type="text" id="tag" name="tag" size="25" value="<%
                                            try {
                                                Statement s1 = (Statement) pageContext.getAttribute("statement");
                                                // String user = (String) request.getSession(false).getAttribute("user");
                                                String sql = "SELECT tag FROM tag WHERE username='" +user+ "' AND namatugas='" +namatask+ "'";
                                                ResultSet rs = s1.executeQuery(sql);
                                                if (rs.isLast()){
                                                    out.print(rs.getString(1));
                                                } else {
                                                    while (rs.next()){
                                                        out.print(rs.getString(1));
                                                        if (!rs.isLast())
                                                            out.print(", ");
                                                    }
                                                }
                                                //  rs.close();
                                                //  s1.close();
                                            } catch (SQLException e2){
                                                e2.printStackTrace();
                                            }
                                        %>"></pae><br>
                                </div>
                            </div>
                            <div id="tombol_edit">
                                    <input type="button" id="edit_button" value="Edit Task" onclick="display('editing','done_edit')"><br>
                                    <!--<form name="Hapustask" action="deletetask" method="POST"> -->
                                   
                                    <!--</form>-->
                            </div>
                            <div id="done_edit">
                                    <input type="submit" id="done_button" value="Done Editing"><br>
                                    <pai><input type="checkbox" id="status" name="statuss" value="true" <% try {
                                                    Statement s1 = (Statement) pageContext.getAttribute("statement");
                                                    // String user = (String) request.getSession(false).getAttribute("user");
                                                    String sql = "SELECT status FROM tugas WHERE username='" +user+ "' AND namatugas='" +namatask+ "'";
                                                    ResultSet rs = s1.executeQuery(sql);
                                                    while (rs.next()){
                                                        if (rs.getInt(1) == 1)
                                                            out.print("checked");
                                                    }
                                                    //  rs.close();
                                                    //  s1.close();
                                                } catch (SQLException e2){
                                                    e2.printStackTrace();
                                                } %>>Done</input></pai>
                            </div>
                        </form>
                        <div id="comment">
                                <div id="komentar">
                                        <pab>Komentar</pab>
                                </div>
                                <div id="isi2">
                                        <b>:</b>
                                </div>
                                <div id="tuliskomen">
                                        <% try{
                                            Statement s1 = (Statement) pageContext.getAttribute("statement");
                                            // String user = (String) request.getSession(false).getAttribute("user");
                                            String sql = "SELECT penulis, komentar FROM comment WHERE username='" +user+ "' AND namatugas='" +namatask+ "'";
                                            ResultSet rs = s1.executeQuery(sql);
                                            while (rs.next()){
                                                out.print("<pag><b>");
                                                out.print(rs.getString(1));
                                                out.print("</pag></b><br>");
                                                out.print("<pah>");
                                                out.print(rs.getString(2));
                                                out.print("</pah><br>");
                                            }
                                        } catch (SQLException e2){
                                            e2.printStackTrace();
                                        }
                                        %>
                                        <form name="comment" action="addcomment" method="POST">
                                            <p>
                                                <textarea id="areatulis" name="areacomment" rows="4" cols="34" placeholder="Tulis komentar anda"></textarea>
                                            </p>
                                            <!--<input type="submit" id="submitdone" value="Tulis Komentar" onclick="addcomment('<% out.print(namatask);%>','<% out.print(user);%>')">-->
                                            <input type="submit" id="submitdone" value="Tulis Komentar">
                                        </form>
                                </div>
                                <div id="deleted">
                                    <form name="deletetugas" action="deletetask" method="POST" >
                                         <input type="submit" id="delete_button" name="deletetask" value="Delete">
                                    </form>
                                </div>
                        </div>
                </div>
                <%
                connection.close();
                %>
	</body>
</html>