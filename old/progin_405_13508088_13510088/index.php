<!DOCTYPE html>
<html lang="en">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>canDo</title>
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <link rel="stylesheet" type="text/css" media="only screen and (max-device-width: 320px; max-device-height:480)" href="small-style.css" />


        <script src="LoginPage.js"> </script>
    </head>
	<body></body>
	
    <body>
            <div class="body">
				<header><a href="index.html"><img src="images/Logo.png"></a></header>
				<div id="loginform">
						<!-- <form name="login" method="get" accept-charset="utf-8"> -->  
						<h3>Welcome Back!</h3>
						<label for="usermail">Email</label>
							<input type="email" name="usermail" id="usermail" placeholder="yourname@email.com" required>
							<br>
							<label for="password">Password</label>
							<input type="password" id="password" name="password" placeholder="password" required>  
							<br>
							<input type="submit" value="Login" id="loginbutton" onclick="validate()">
						</div>
				 
     			<div class="registerform">  
     				<h3>New here? Please Register</h3>
     				<h4>canDo is your task manager that makes everyday lives easier! List task, view task, dan even share task! Join our community now! </h2>
    				<form name="register"  method="post" accept-charset="utf-8" onsubmit="reg" action="coba.php"> 
						<div id="nama">
							Respon nama di sini.
						</div>
						<p>
				        <label for="usermail">Email</label>
				        <input type="email" name="usermail" placeholder="yourname@email.com" required>  
				        
				        <br>
				        <label for="password">Password</label>
				        <input type="password" name="password" placeholder="password" required>  
				        
				        <br>
				        <label for="Confirm password">Confirm Password</label>  
				        <input type="password" name="confpassword" placeholder="password" required>
				        
				        <br> 
				        <label for="Nama">Full Name</label>
				        <input type="nama" name="nama" placeholder="Nama anda" required> 
				        
				        <br>
				        <label for="Jenis Kelamin">Sex</label>
				        <input type="radio" name="sex" value="male" checked="">Male
				        <input type="radio" name="sex" value="female">Female
				        
				        <br>
				         <label for="Tanggal lahir">Birthday</label><br />
            <select name="Tanggal Lahir">
				<option value="1" selected="">1</option>
				<option value="2" >2</option>
				<option value="3">3</option>
				<option value="4" >4</option>
				<option value="5" >5</option>
				<option value="6" >6</option>
				<option value="7" >7</option>
				<option value="8" >8</option>
				<option value="9" >9</option>
				<option value="10" >10</option>
				<option value="11" >11</option>
				<option value="12" >12</option>
				<option value="13" >13</option>
				<option value="14" >14</option>
				<option value="15" >15</option>
				<option value="16" >16</option>
				<option value="17" >17</option>
				<option value="18" >18</option>
				<option value="19" >19</option>
				<option value="20" >20</option>
				<option value="21" >21</option>
				<option value="22" >22</option>
				<option value="23" >23</option>
				<option value="24" >24</option>
				<option value="25" >25</option>
				<option value="26" >26</option>
				<option value="27" >27</option>
				<option value="28" >28</option>
				<option value="29" >29</option>
				<option value="30" >30</option>
				<option value="31" >31</option>
			</select>
			<select name="Bulan Lahir">
				<option value="Januari" selected="">Januari</option>
				<option value="Februari" >Februari</option>
				<option value="Maret">Maret</option>
				<option value="April" >April</option>
				<option value="Mei" >Mei</option>
				<option value="Juni" >Juni</option>
				<option value="Juli" >Juli</option>
				<option value="Agustus" >Agustus</option>
				<option value="September" >September</option>
				<option value="Oktober" >Oktober</option>
				<option value="November" >November</option>
				<option value="Desember" >Desember</option>
			</select>
			<select name="Tahun Lahir">
				<option value="1989" selected="">1989</option>
				<option value="1990" >1990</option>
				<option value="1991" >1991</option>
				<option value="1992" >1992</option>
				<option value="1993" >1993</option>
				<option value="1994" >1994</option>
				<option value="1995" >1995</option>
				<option value="1996" >1996</option>
				<option value="1997" >1997</option>
				<option value="1998" >1998</option>
				<option value="1999" >1999</option>
				<option value="2000" >2000</option>
				<option value="2001" >2001</option>
				<option value="2002" >2002</option>
				<option value="2003" >2003</option>
				<option value="2004" >2004</option>
				<option value="2005" >2005</option>
				<option value="2006" >2006</option>
				<option value="2007" >2007</option>
				<option value="2008" >2008</option>
			</select>
						
						<br>
						
					 <label for="Profile Picture">Profile picture</label> 
					 <br /> 
					  <input type="file" placeholder="" required> 
						<br />
				        <input type="submit" value="Register" id="registerbutton" onclick="formValidation(this.form)"> 
						</p>
    				</form>  
    			</div> 
	</div>
		<footer>
            CanDo. Yes you can!
            <br>
            About us
		</footer>
                     
    </body>		


</html>
