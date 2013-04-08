
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
						<input type="text" value="<% profile p=new profile(); out.print(p.fullname);%>" onchange="checkNamaLengkap()" id="namalengkap" name="namalengkap"></input>
					</li>
                    <li>
						<label style="width:230px; display:inline-block">Tanggal Lahir:</label>
						<input type="date" value="<% out.print(p.birthday);%>" name="birthday"></input>
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
