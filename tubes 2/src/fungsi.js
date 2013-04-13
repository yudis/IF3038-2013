var username = document.getElementById("username");
var password = document.getElementById("password");
var copassword = document.getElementById("copassword");
var namalengkap = document.getElementById("namalengkap");
var email = document.getElementById("email");
var valid1 = document.getElementById("valid1");
var valid2 = document.getElementById("valid2");
var valid3 = document.getElementById("valid3");
var valid4 = document.getElementById("valid4");
var valid5 = document.getElementById("valid5");
var valid6 = document.getElementById("valid6");
var inputfile = document.getElementById("inputfile");
var submit = document.getElementById("submit");
var submitlogin = document.getElementById("submitlogin");
var idlogin = document.getElementById("idlogin");
var passlogin = document.getElementById("passlogin");
var form = document.getElementById("form");
var tahun = document.getElementById("tahun");
var bulan = document.getElementById("bulan");
var tanggal = document.getElementById("tanggal");
var daftar = document.getElementById("daftar");

//callback function AJAX
var xmlhttp;
var url;
function loadXMLDoc(url,cfunc)
{
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.open("GET",url,true);
xmlhttp.onreadystatechange=cfunc;
xmlhttp.send();
}

	 function cekLogin()
	{	
		document.getElementById('loginresponse').innerHTML = "LOADING";
		var x = document.getElementById('idlogin').value;
		var y = document.getElementById('passlogin').value;
		loadXMLDoc("login.php?idlogin="+x+"&passlogin="+y, function()
		{		
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
			{
				if (xmlhttp.responseText == 0)
				{
					document.getElementById('loginresponse').innerHTML = 'LOGIN FAILED';
				}
				else 
				{
					document.getElementById('loginresponse').innerHTML = 'WELCOME';
		
					window.location = "createSession.php?t="+x;
				}
			}
		});
	}
	
	function cekRegister()
	{
		document.getElementById('loginresponse').innerHTML = "LOADING";
		
		var x = document.getElementById('username').value;
		var e = document.getElementById('email').value;
		var i = document.getElementById('copassword').value;
		var j = document.getElementById('namalengkap').value;
		//var k = document.getElementById('inputfile').value;
		var y  = document.getElementById('tahun').value;
		var m = document.getElementById('bulan').value;
		var d = document.getElementById('tanggal').value;
		
		loadXMLDoc("register.php?username="+x+"&email="+e+"&copassword="+i+"&namalengkap="
						   +j+"&tahun="+y+"&bulan="+m+"&tanggal="+d
		,function()
		{
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
			{
				if (xmlhttp.responseText == 0)
				{
					document.getElementById('regresponse').innerHTML = 'username sudah terpakai';
				}
				else if (xmlhttp.responseText == 1)
				{
					document.getElementById('regresponse').innerHTML = 'email sudah terpakai';
				}
				else if (xmlhttp.responseText == 2)
				{
					document.getElementById('regresponse').innerHTML = 'WELCOME';
					window.location = "createSession.php?t="+x;
				}
			}
		});
	}
	
	/*daftar.onsubmit = function(){
		if (submit.disabled == "")
		{
			alert("register berhasil");
			window.location = "index.html"
		}
		
		return false;
	}*/

	username.onkeyup = function()
	{
		if (username.checkValidity()){
			valid1.src = "img/benar.png";
		}
		else
		{
			valid1.src = "img/salah.png";
		}
		
		if (username.value == password.value)
		{
			valid1.src = "img/salah.png";
		}
		
		cekvalid();
	}

	password.onkeyup = function()
	{
		if (password.checkValidity()){
			valid2.src = "img/benar.png";
		}
		else
		{
			valid2.src = "img/salah.png";
		}
		
		if (username.value == password.value)
		{
			valid2.src = "img/salah.png";
		}
		else if (password.value == email.value)
			{
				valid2.src = "img/salah.png";
			}
			
		cekvalid();
	}
	
	copassword.onkeyup = function()
	{
		if (copassword.checkValidity() && (password.value == copassword.value)){
			valid3.src = "img/benar.png";
		}
		else
		{
			valid3.src = "img/salah.png";
		}
		
		cekvalid();
	}
	
	namalengkap.onkeyup = function()
	{
		if (namalengkap.checkValidity()){
			valid4.src = "img/benar.png";
		}
		else
		{
			valid4.src = "img/salah.png";
		}
		
		cekvalid();
	}
	
	email.onkeyup = function()
	{
		if (email.checkValidity()){
			valid5.src = "img/benar.png";
		}
		else
		{
			valid5.src = "img/salah.png";
		}
		
		if (password.value == email.value)
			{
				valid5.src = "img/salah.png";
			}
		
		cekvalid();
	}
	
	function cekvalid(){
		if (username.checkValidity() && (username.value != password.value) && (password.checkValidity()) && (password.value != email.value)
		&& (password.value == copassword.value)  && (namalengkap.checkValidity()) && (email.checkValidity()) && (inputfile.value.match("^.+\.(jpe?g|JPE?G)$")) && (tahun.value != "") && (bulan.value !="") && (tanggal.value != ""))
		{
			submit.disabled="";
		}
		else
		{
			submit.disabled="disabled";
		}
	}
	
	function cekulta()
	{
		if (tahun.value != "" && bulan.value !="" && tanggal.value != "")
		{
			valid7.src = "img/benar.png";
		}
		else
		{
			valid7.src = "img/salah.png";
		}
	}
	
	function check_image_jpg()
	{
		var extensi = inputfile.value.match("^.+\.(jpe?g|JPE?G)$");
		if(extensi)
			valid6.src = "img/benar.png";
		else
			valid6.src = "img/salah.png";
			
		cekvalid();
	}

var crossFadeDuration = 3;
var Pic = new Array();
Pic[0] = 'img/ilustrate1.JPG'
Pic[1] = 'img/ilustrate2.JPG'
Pic[2] = 'img/ilustrate3.JPG'
Pic[3] = 'img/ilustrate4.JPG'

var t;
var j = 0;
var p = Pic.length;
var preLoad = new Array();
for (i = 0; i < p; i++) {
preLoad[i] = new Image();
preLoad[i].src = Pic[i];
}
function SlideShow() {
	if (document.all) {
	document.images.SlideShow.style.filter="blendTrans(duration=2)";
	document.images.SlideShow.style.filter="blendTrans(duration=crossFadeDuration)";
	document.images.SlideShow.filters.blendTrans.Apply();
	}
	document.images.SlideShow.src = preLoad[j].src;
	if (document.all) {
	document.images.SlideShow.filters.blendTrans.Play();
	}
	j = j + 1;
	if (j > (p - 1)) j = 0;
	t = setTimeout('SlideShow()', 2000);
}