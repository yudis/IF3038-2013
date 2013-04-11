
<%-- 
    Document   : profile
    Created on : Apr 3, 2013, 2:28:46 PM
    Author     : Yulianti Oenang
--%>

<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@page import="tubes3.profile"%>
<!DOCTYPE html>
<jsp:include page="/header.jsp" />
        <script src="editprofile.js" type="text/javascript" language="javascript"> </script>

<%
String user;boolean editable;
user=(String)((HttpServletRequest) request).getSession().getAttribute("bananauser");
  

profile p=new profile(user);
%>        
        
        
<!-- Foto profile -->
       <div id="isi">
			<div id="leftsidebaredit">
			</div>
			
			<div id="rightsidebar">
			<ul>
				<form class="prof" action="profile" method="post" enctype="multipart/form-data">
					<h1 align="left">Edit Profile</h1>
					<li>
						<label style="width:230px; display:inline-block">Nama Lengkap:</label>
						<input type="text" value="<%out.print(p.fullname);%>" onchange="checkNamaLengkap()" id="namalengkap" name="namalengkap"></input>
					</li>
                    <li>
						<label style="width:230px; display:inline-block">Tanggal Lahir:</label>
                                                <input type="text"id="tgllahir" value="<%String date[]=p.birthday.split("-"); out.print(date[2]+"-"+date[1]+"-"+date[0]);%>" name="birthday" /readonly></input>
                                                <a href="javascript:NewCssCal('tgllahir','ddmmyyyy')"><img src="image/cal.gif" alt="Pick a date"/></a>
					</li>
					<li>
						<label style="width:230px; display:inline-block">New Password:</label>
						<input type="password" value="<%out.print(p.password);%>" onchange="checkPassword('<%out.print(p.username);%>')" name="password" id="pass"></input>
					</li>
					<li>
						<label style="width:230px; display:inline-block">Confirmed New Password:</label>
						<input type="password" value="<%out.print(p.password);%>" name="confirmedpass" onchange="checkConfirmedPass()" id="passconfirmed"></input>
					</li>
					<li>
						<label style="width:230px; display:inline-block">Change Avatar:</label>
						<input type="file" name="avatar" id="avatar" accept="image/jpeg, image/jpg" onchange="fileallowed()"></input>
					</li>
					<li>
						<button class="reg" type="submit"><b> Ok</b></button>
					</li>
				</form>
				</ul>
			</div>
	</div>

        <div id="footer" class="home">
                <p>&copy Copyright 2013. All rights reserved<br>
                Chalkz Team<br>
                Yulianti - Raymond - Devin</p>			
        </div>
</div>
</body>
        
</html>
