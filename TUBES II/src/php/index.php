<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script>
function registertake()
{
var newuser = document.getElementById("DID").value;
var newpass = document.getElementById("DP").value;
var newpassc = document.getElementById("DCP").value;
var email = document.getElementById("DMail").value;
var name = document.getElementById("DName").value;
var tgl = document.getElementById("Day").value;
var bln = document.getElementById("DMonth").value;
var thn = document.getElementById("DYear").value;
var ava = document.getElementById("DAvatar").value;

if(newuser==""||newpass==""||newpassc==""||email==""||name==""||tgl==""||bln==""||thn==""||ava==""){
alert ("semua field harus diisi !");
}else
if (newpass != newpassc){
alert ("password tidak sama");
}else {
register(newuser,newpass,email,name,tgl,bln,thn,ava);
}
}

function register(usr,pswd,eml,nm,dt,mt,yt,avt)
{
if (usr=="")
  {
  document.getElementById("txtHint").innerHTML="";
  return;
  } 
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("txtHint").innerHTML=xmlhttp.responseText;
	checkLogin(xmlhttp.responseText);
    }
  }
xmlhttp.open("GET","register.php?username=" + usr + "&password=" + pswd + "&email=" + eml + "&name=" + nm + "&date=" + dt + "&month=" + mt + "&year=" + yt + "&avatar=" + avt,true);
xmlhttp.send();
}

function take()
{
var user = document.getElementById("user").value;
var password = document.getElementById("password").value;

showUser(user,password);
}

function showUser(str,qtr)
{
if (str=="")
  {
  document.getElementById("txtHint").innerHTML="";
  return;
  } 
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("txtHint").innerHTML=xmlhttp.responseText;
	checkLogin(xmlhttp.responseText);
    }
  }
xmlhttp.open("GET","getuser.php?q=" + str + "&r=" + qtr,true);
xmlhttp.send();
}

function checkLogin(response)
{
 if (response == "Accepted") {
   window.location='dashboard.php';
 }
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width; initial-scale=1.0"/>
<title>So La Si Do</title>
<link href="css/utama.css" rel="stylesheet" type="text/css" ></link>
<link href="css/mediaqueries.css" rel="stylesheet" type ="text/css" ></link>
<link href='http://fonts.googleapis.com/css?family=Skranji' rel='stylesheet' type='text/css'></link>
<link href='http://fonts.googleapis.com/css?family=Merienda' rel='stylesheet' type='text/css'></link>

</head>
<body>
	<div class="header">
	So La Si Do<br/>
	Beat Your Task!
	</div>
	<img alt="" src="images/fitur.png" width="403" height="403" class="fitur"></img>
	<div class="login">
	<h4 class="judul">Login</h4>
	<form action="javascript:take()" name="login" >
        <p>Username: <input type="text" name="ID" id="user" ></input></p>
        <p>Password: <input type="password" name="Password" id="password" ></input> </p>
        <input type="submit" name="submitHome" value="login"></input> <br/>
    </form>
    </div>
	<div class="register">
	<h4 class="judul">Register</h4>
	<form action="javascript:registertake()" name="registrasi">
		<p>Username: <input  id="DID" name="DID" type="text"></input></p>
		<p>Password: <input id="DP" name="DP" type="password"></input></p>
		<p>Confirm Password: <input  id="DCP" name="DCP" type="password"></input></p>
		<p>Name: <input  id="DName" name="DName" type="text"></input></p>
		<p>Tanggal Lahir: <select id="Day" ><option value="01">01</option><option value="02">02</option></select><select id="DMonth"><option value="01">01</option><option value="02">02</option></select><select id="DYear"><option value="1956">1956</option><option value="1957">1957</option></select></p>
		<p>Alamat email: <input  id="DMail" name="DMail" type="text"></input></p>
		<p>Avatar : <input type="file" id="DAvatar" name="DAvatar"></input></p>
		<input id="signup" value="signup" type="submit" ></input>
	</form>
    </div>
	<div id="txtHint"><b>Person info will be listed here.</b></div>
</body>   

</html>


