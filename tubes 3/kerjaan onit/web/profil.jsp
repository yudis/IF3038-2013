   <%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="model.UserBean"
   %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Profil - TargET</title>
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<script src="js/script.js" type="text/javascript" ></script>
</head>
<body>
	<header>
		<div id="header">
			<div id="topbar">
				<div id="topbar_logo">
					<a href="dashboard.html"><img id="logo" src="img/logo.png"></a>
				</div>
				<div id="topbar_logo2">
					<img id="logo2" src="img/namalogo.png">
				</div>
				<div id="topbar_border"></div>
				<div id="topbar_dashboard">
					<nav>
						<ul>
							<li> <a href="dashboard.html"> Dashboard </a> </li>
							<li> <a class="active" href="#">Profil</a> </li>
							<li> <a href="index.html">Logout</a> </li>
						</ul>
					</nav>
				</div>
				<div id="topbar_search">  
					<input type="search"  placeholder="search"/>
				</div>
			</div>
		</div>
	</header>
	<section>
		<div id="content2" class="wrap">
			<div id="foto_profil">
				<img id="foto" src="img/fotoprofil.jpg">
				<div id="user_info">user info</div>
				<div id="biodata1" class="user">
					<div>Username</div>
					<div>Nama Lengkap</div>
					<div>Tanggal Lahir</div>
					<div>Email</div>
					<div></div>
				</div>
				<div id="biodata2" class="user">
					<div>
                                            <% 
                                                UserBean currentUser = ((UserBean)session.getAttribute("currentSessionUser"));
                                            %>
                                            : <%= currentUser.getUsername() %>
                                        </div>
					<div>: <%= currentUser.getNamalengkap() %></div>
					<div>: <%= currentUser.getDate() %></div>
					<div>: <%= currentUser.getEmail() %></div>
                                        <div id="pedit">
                                        <input type="button" id="pedit_button" value="Edit Profile" onclick="pdisplay('biodata3')">
					</div>
                                        <div></div>
				</div>
				<div id="biodata3">					
					<form id="isi" class="editbio" action="EditServlet">
						
						<div>
							<input id = "edname" type="text" name="edname" placeholder="Edit Nama Lengkap" pattern="^.+ .+$" required>
						</div>
						<div>
							<select id="tahun" name="tahun">
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
							</select><br>		
						</div>
						<div>
							<input id="edmail" type="text" name="edmail" placeholder="Edit Email" pattern="^.+@.+\...+$" required>
						</div>
						<p>
							<input id = "edpass" type="password" name="edpass" onchange="isChanged()" placeholder="Edit Password" pattern="^.{8,}$" required>
						</p>
						<p>
							<input id = "edcpass" type="password" name="edcpass" placeholder="Confirm Password" pattern="^.{8,}$" required>
						</p>	
						<p id = "donepedit">
							<input type="submit" id="done_pedit" name="done_pedit" value="Done Edit">
                                                        <input type="button" id="cancel_button" value="Cancel" onclick="display('biodata2')">
						</p>		
						<p id="editresponse"></p>
					</form>
				</div>						
			</div>
			<div id="list">
				<div id="listTugas">
					List Task
					<div>
						<ul>
							<li>Tugas 3 IMK</li>
							<li>Tubes 1 Kriptografi </li>
						</ul>
					</div>
				</div>
				<div id="doneTugas">
					Completed Tasks
					<div>
						<ul>
							<li>Tubes 1 Intelegensi Buatan</li>
							<li>Tubes 1 Pemrograman Internet</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>
	<footer id="footer_wrap">
		<hr>TargET &#169 2013
	</footer>
</body>
</html>