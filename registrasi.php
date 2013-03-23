<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">

<?php
	session_start();
?>

<head>
	<title>To-Do - Register</title>
	<link rel="stylesheet" type="text/css" title="antartica" href="css/style2.css"/>
    <script type="text/javascript">
		document.onclick=check;
		
		var Ary=[];

		function check(e) {
			var target = (e && e.target) || (event && event.srcElement);
			while (target.parentNode){
			if (target.className.match('loginbox')||target.className.match('poplink')) return;
				target=target.parentNode;
			}
			var ary=zxcByClassName('loginbox')
			for (var z0=0;z0<ary.length;z0++){
				ary[z0].style.display='none';
			}
		}
				
		function zxcByClassName(nme,el,tag){
			if (typeof(el)=='string') el=document.getElementById(el);
				el=el||document;
				for (var tag=tag||'*',reg=new RegExp('\\b'+nme+'\\b'),els=el.getElementsByTagName(tag),ary=[],z0=0; z0<els.length;z0++){
					if(reg.test(els[z0].className)) ary.push(els[z0]);
				 }
				return ary;
			}

		function ulala() {
			var hza = document.getElementById('login');
			if (hza && hza.style){
				if (!hza.set){ hza.set=true;  Ary.push(hza); }
				hza.style.display = (hza.style.display == '')? 'none':'';
			}
		}
	</script>
    <script type="text/javascript" src="validator.js"></script>
	<script type="text/javascript" src="login.js"></script>
	<script type="text/javascript" src="datetimepicker_css.js"></script>        
</head>

<body>
	<header>
			<?php
				if(!isset($_SESSION['uname'])){
				echo"
					<div id='login' class='loginbox' style='display: none;'>
						Username <input type='text' class='labelboxlogin' id='username'></input>
						Password <input type='text' class='labelboxlogin' id='password'></input>
						<a><input type='button' id='buttonsignin' onclick='Login();'/></a>
					</div>";
				}
				else {
					echo "<a href ='index.php'><input type='button' id='buttonpost' /></a>";
				}
			?>
			
			<div id="sign">
			<?php
				if(!isset($_SESSION['uname'])){
					echo "
					<ul>
						<li><a href='index.php'><img src='images/todolistss.jpg'></a></li>
						<li>Hello, Stranger! Do you want to <a href='registrasi.php'>Register?</a>
						</li>
					</ul>";
				}
				else{
					echo "Hello there, <a href='halamanprofil.php'>".$_SESSION['uname']."</a>.! Welcome Home!  <a href='logout.php'>Logout</a>";
				}
			?>
			</div>
			
		</header>

           
	<div class="centeract">

	<div class="sesuatu">
<?php
//FUNCTIONS
require ('connect.php');

function validUsername(){
	$usname=$GLOBALS['uname'];
	if(preg_match("/^[a-zA-Z0-9_\.]{5,}$/", $usname)===0){
		return FALSE;
	}
	else{return TRUE;}
}

function availUsername(){
	$usname=$GLOBALS['uname'];
	$result = mysql_num_rows(mysql_query("SELECT * FROM user WHERE username='$usname'"));
	if($result > 0){
		return FALSE;
	}
	else{return TRUE;}
}

function validPass(){
	$pwd=$GLOBALS['pass'];
	if(preg_match("/^[a-zA-Z0-9]{8,}$/", $pwd)===0){
		return FALSE;
	}
	else{return TRUE;}
}

function availPass(){
	$mail=$GLOBALS['email'];
	$pwd=$GLOBALS['pass'];
	$usname=$GLOBALS['uname'];
	if($pwd==$usname || $pwd==$mail){
		return FALSE;
	}
	else{return TRUE;}
}

function validName(){
	$nama=$GLOBALS['name'];
	if(preg_match("/^[-a-zA-Z]+( +[-a-zA-Z]+)+$/", $nama)===0){
		return FALSE;
	}
	else{return TRUE;}
}

function validDOB(){
	$bd=$GLOBALS['dob'];
	if (preg_match ("/^([0-9]{4})-([0-9]{2})-([0-9]{2})$/", $bd, $parts)){
        if(checkdate($parts[2],$parts[3],$parts[1])) {return true;}
        else {return false;}
	}
	else {return false;}
}

function validEmail(){
	$mail=$GLOBALS['email'];
	if(preg_match("/^[-a-zA-Z0-9_.]+@[-a-zA-Z0-9]+(.[a-zA-Z]{2,})+$/", $mail)===0){
		return FALSE;
	}
	else{return TRUE;}
}

function availEmail(){
	$mail=$GLOBALS['email'];
	$result = mysql_num_rows(mysql_query("SELECT * FROM user WHERE email='$mail'"));
	if($result > 0){
		return FALSE;
	}
	else{return TRUE;}
}
//END FUNCTIONS

//PROSES DIMULAI
echo "<div id='sesuatu2'>";
if(isset($_POST['submit'])){
	$uname=$_POST['uname'];
	$pass=$_POST['pass'];
	$cpass=$_POST['cpass'];
	$name=$_POST['name'];
	$dob=$_POST['dob'];
	$email=$_POST['email'];


//Melakukan validasi kesalahan
if(validUsername()==FALSE || validPass()==FALSE || validName()==FALSE || validDOB()==FALSE || validEmail()==FALSE || availUsername()==FALSE || availPass()==FALSE || availEmail()==FALSE){
	if (validUsername()==FALSE) {echo ">>Username is invalid! <br/><br/>";}
	if (availUsername()==FALSE) {echo ">>Username is already taken! <br/><br/>";}
	if (validPass()==FALSE) {echo ">>Password is invalid!<br/><br/>";}
	if (availPass()==FALSE) {echo ">>Password should be different with username or e-mail! <br/><br/>";}
	if ($pass!==$cpass) {echo ">>Password doesn't match<br/><br/>";}
	if (validName()==FALSE) {echo ">>Name should at least contain first name and last name separated by space!<br/><br/>";}
	if (validDOB()==FALSE) {echo ">>Date format should be YYYY-MM-DD and valid!<br/><br/>";}
	if (validEmail()==FALSE) {echo ">>E-mail address is invalid!<br/><br/>";}
	if (availEmail()==FALSE) {echo ">>E-mail address already registered!<br/><br/>";}
}
//Memasukkan data ke database
else{
	
	//MENGURUSI GAMBAR
	$fileSize = $_FILES['ava']['size']; //get the size
	$fileError = $_FILES['ava']['error']; //get the error when upload
	if($fileSize > 0 || $fileError == 0){ //check if the file is corrupt or error
		$move = move_uploaded_file($_FILES['ava']['tmp_name'], "avatar/".$uname.".jpg");
		$loc = "avatar/".$uname.".jpg";
	}
	else {echo "You haven't uploaded your avatar!";}
	
	//MEMASUKKAN DATA KE DB
	if (isset($loc)){
		$sql="insert into user (username,password,name,dob,email,avatar)";
		$sql .="values('$uname','$pass','$name','$dob','$email','$loc')";
		$result=mysql_query($sql,$connect) or die(mysql_error());
	}
	else{
		$sql="insert into user (username,password,name,dob,email)";
		$sql .="values('$uname','$pass','$name','$dob','$email')";
		$result=mysql_query($sql,$connect) or die(mysql_error());
	}
	
	$a="select iduser from user where username='$uname'";
	$b=mysql_query($a,$connect);
	$c=mysql_fetch_row($b);
	$iduser=$c[0];
	
	$mysql = mysql_query("SELECT * FROM user WHERE username = '$uname' AND password = '$pass'");
	$hq=mysql_fetch_array($mysql);
	$_SESSION['uname'] = $uname;
	$_SESSION['name'] = $name;
	$_SESSION['dob'] = $dob;
	$_SESSION['email'] = $email;
	$_SESSION['iduser'] = $iduser;
	if (isset($loc)){$_SESSION['avatar'] = $loc;}
	$_SESSION['jmltask'] = $jmltask;
	header("refresh: 2; dashboard.php");
	die ("<h1>You have registered sucessfully, ".$_SESSION['uname']." </h1><a href='index.php'>Go Home!</a>");
}
}
echo"</div>";
?>
		<FORM type="registerpage.php" method="post" enctype="multipart/form-data">
                    Username:<br/>
                    <input type="text" class="labelboxreg" id="uname" name="uname"></input><br></br>
                    Password:<br/>
                    <input type="password" class="labelboxreg" id="pass" name="pass"></input><br></br>
                    Confirm Password:<br/>
                    <input type="password" class="labelboxreg" id="cpass" name="cpass"></input><br></br>
                    Full Name:<br/>
                    <input type="text" class="labelboxreg" id="name" name="name"></input><br></br>
                    Date of Birth:<br/>
                    <input type="text" class="labelboxreg" id="dob" name="dob"></input>
                    <img src="images2/cal.gif" onclick="javascript:NewCssCal('dob')" style="cursor:pointer" alt="calendar"/>
                    <br></br>
                    Email:<br/>
                    <input type="text" class="labelboxreg" id="email" name="email"></input><br></br>
                    Avatar<br/>
                    <input type="file" id="ava" name="ava"></input><br></br>
                    <input type="checkbox" id="agree" name="agree" onclick="validAgree(this)"></input> I agree to the terms and services <br/><br/>
                    <input type="submit" value="Register" name="submit"></input><br/><br/>
            </div>    
		</form>
	</div>
</body>

</html>  