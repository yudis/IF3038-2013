<!DOCTYPE html>
<%--<?php
include_once("../php/loginchecker.php");
?>
<?php

if(isset($_SESSION['uname'])){
    $username = $_SESSION['uname'];
    print_r("session sekarang usernya".$username);
}
?>

<?php include '../php/datapengguna.php'?>--%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../php/datapengguna.jsp"%>
<%@include file="../php/getalltaskuser.jsp"%>
<%@include file="../php/getalltaskuserfinish.jsp"%>
<%String activeuser="ruth";%>

<html>
	
	<head>
		<link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/animation.js"> </script> 
                <script type="text/javascript" src="../js/edituser.js"> </script> 
                <script type="text/javascript" src="../js/editprofile.js"> </script> 
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<title> Eurilys </title>
	</head>

<%
    //String catchoosen = request.getParameter("kategori")
    ResultSet userdata = datapengguna(activeuser);
    ResultSet alltask = getalltaskuser(activeuser);
%>  ResultSet alltaskfinish = getalltaskuserfinish(activeuser);
	<body>
		<!-- Web Header -->
		<%@include file="../src/header.jsp"%>
		
		
		<!-- Web Content -->
                <%  while(userdata.next()){%>
                    <section>
			<div id="navbar">
				<div id="short_profile">
					<img id="profile_picture" src="../file/<%out.println(userdata.getString("avatar"));%>" alt="">
					<div id="profile_info">
						<%out.println(userdata.getString("full_name"));%>
						<br><br>
						<div class="link_tosca" id="edit_profile_button" onclick="edit_profile(<%out.println(userdata.getString("username"));%>)"> Edit Profile </div>
					</div> 
				</div>
				
			</div>
			<div id="dynamic_content">
				<div class="half_div">
					<div id="upperprof">
						<img src="../file/<?php echo $eachdata['avatar']?>" alt="">
						<div id="namauser"><%out.println(userdata.getString("full_name"));%></div>
					</div>
					
					<br/>
                                       Username :<%out.println(userdata.getString("username"));%>
                                        <br>
					Email : <%out.println(userdata.getString("email"));%>
					<br>
					Birthdate : 

				</div>
				<div class="half_div">
					<div class="half_tall">
						<div class="headsdeh">Current Tasks:</div>
						<ul class="ul_none">
							<li>Tubes Progin 1</li>
							<li>Catatan Progin </li>
						</ul>
					</div>
					<div class="half_tall">
						<div class="headsdeh">Finished Tasks:</div>
						<ul class="ul_none">
							<li>Tugas Individu IMK</li>
							<li>Tugas Keamanan Informasi </li>
							<li>Tugas Besar AI </li>
						</ul>
					</div>
				</div>
				
			</div>
		</section>
                <%}%>
                
               
		
		<!-- Footer Section -->
		<div class="thin_line"></div>
		<footer>
			<div id="footer_container"> 
				<br><br>
				About &nbsp;&nbsp;&nbsp; FAQ &nbsp;&nbsp;&nbsp; Feedback &nbsp;&nbsp;&nbsp; Terms &nbsp;&nbsp;&nbsp; Privay &nbsp;&nbsp;&nbsp; Copyright 
				<br>
				Eurilys 2013
			</div>
		</footer>
	</body>

<!-- ini nanti jadiin footer -->
</html>