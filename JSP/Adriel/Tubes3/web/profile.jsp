<%-- 
    Document   : profile
    Created on : Apr 11, 2013, 2:54:36 PM
    Author     : Adriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <link rel="stylesheet" type="text/css" href="default.css" />
        <link rel="stylesheet" type="text/css" href="mediaqueries.css" />
		<link rel="stylesheet" type="text/css" href="styles/profile.css" />
		<script src="scripts/profile.js" type="application/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Todolist: User Profile</title>
    </head> 
    <body onload="initialize();">
        <div class="page">
            <header class="content">
               <%@include file="header.jsp" %>
            </header>
            <div class="content">
                <h1> User Profile </h1>
                <div class="profile">
                    <div class="profiledetail">
						<a id="linkusername" href="#"></a>
						<div>
							<span class="rincianLabel2">Full Name:</span>
							<span id="nameDisplayDiv"></span>
							<div id = "nameEditDiv">
								<input type="text" id="fullname" name="fullname" list="names" onkeyup="validateFullName();" />
                                <datalist id="names">
                                    <option value="Abraham Krisnanda Santoso">
                                    <option value="Edward Samuel Pasaribu">
                                    <option value="Stefanus Thobi Sinaga">
                                </datalist>
							</div>
						</div>
						<div>
							<span class="rincianLabel2">E-mail: </span>
							<span id="emailDisplayDiv"></span>
							<div id="emailEditDiv"><input type="text" id="email" name="email" onkeyup="validateEmail();"/></div>
						</div>
						<div>
							<span class="rincianLabel2">Born date: </span>
							<span id="dateDisplayDiv"></span>
							<div id="dateEditDiv"><input type="text" id="date" name="date" onkeyup="validateBirthday();"/></div>
						</div>
						<div>
							<span class="rincianLabel2">Password: </span>
							<span id="passwordDisplayDiv"></span>
							<div id="passwordEditDiv"><input type="password" id="password" name="password" onkeyup="validatePassword();"/></div>
						</div>
						<div>
							<span class="rincianLabel2">Re-type: </span>
							<span id="repasswordDisplayDiv"></span>
							<div id="repasswordEditDiv"><input type="password" id="repassword" name="repassword" onkeyup="validateRePassword();"/></div>
						</div>
						<div id="editProfileButton" class="edit_button_profile"><button type="button" onClick="return editProfil();">Edit</button> </div>
						<div id="doneProfileButton" class="edit_button_profile"><button type="button" onClick="return doneProfil();" >Done</button> </div>
						<div id="changeProfilePictureButton" class="edit_button_profile"> Change PP : 
                                                    <form action="" method="GET" enctype="multipart/form-data"><!--"changeprofilepicture"--> 
								<input type="file" id="cpp" name="cpp" accept="image/*"/>
								<input type="text" id="cppname" name="cppname"/>
								<input type="submit"/>
							</form>
						</div>
                    </div>
                    <div class="profilepict"><img id="pp" width="200" height="200" alt="Edo Thobi Bram"/></div>
                </div>
                <h1> Activity </h1>
				<div id="activitylist">
					<!--<h2>Kategori 1</h2>
					<div class="tugas">
						<div><a href="#">Tugas Pertama</a></div>
							 <div>Submission: <strong>22 April 2012 17:00 WIT</strong></div>
							 <div>
								 Tags: 
								 <ul class="tag">
									 <li>Tag</li>
									 <li>Tag</li>
									 <li>Tag</li>
									 <li>Tag</li>
								  </ul>
							 </div>
					</div>
					<div class="tugas">
						 <div><a href="#">Tugas Pertama</a></div>
							 <div>Submission: <strong>22 April 2012 17:00 WIT</strong></div>
							 <div>
								 Tags: 
								 <ul class="tag">
									 <li>Tag</li>
									 <li>Tag</li>
									 <li>Tag</li>
									 <li>Tag</li>
								  </ul>
							 </div>
				   </div>
				   
				   <h2>Kategori 4</h2>
					<div class="tugas">
						<div><a href="#">Tugas Pertama</a></div>
							 <div>Submission: <strong>22 April 2012 17:00 WIT</strong></div>
							 <div>
								 Tags: 
								 <ul class="tag">
									 <li>Tag</li>
									 <li>Tag</li>
									 <li>Tag</li>
									 <li>Tag</li>
								  </ul>
							 </div>
					</div>
					<div class="tugas">
						 <div><a href="#">Tugas Pertama</a></div>
							 <div>Submission: <strong>22 April 2012 17:00 WIT</strong></div>
							 <div>
								 Tags: 
								 <ul class="tag">
									 <li>Tag</li>
									 <li>Tag</li>
									 <li>Tag</li>
									 <li>Tag</li>
								  </ul>
							 </div>
				   </div>-->
				</div>
            </div>
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.<br />
                IF3094 - Pemrograman Internet.
            </footer>
        </div>
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
