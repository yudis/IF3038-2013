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

	form.onsubmit = function(){
		if ((idlogin.value == "admin") && (passlogin.value == "admin"))
		{
			window.location = "dashboard.html";
		}
		return false;
	}

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
		&& (password.value == copassword.value)  && (namalengkap.checkValidity()) && (email.checkValidity()) && (inputfile.value.match("^.+\.(jpe?g|JPE?G)$")))
		{
			submit.disabled="";
		}
		else
		{
			submit.disabled="disabled";
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
	
	function login()
	{
		if ((idlogin.value == "admin") && (passlogin.value == "admin"))
		{
			window.location = "dashboard.html";
		}
	}
	
	function buildCal(m, y, cM, cH, cDW, cD, brdr){
	var mn=['January','February','March','April','May','June','July','August','September','October','November','December'];
	var dim=[31,0,31,30,31,30,31,31,30,31,30,31];

	var oD = new Date(y, m-1, 1); //DD replaced line to fix date bug when current day is 31st
	oD.od=oD.getDay()+1; //DD replaced line to fix date bug when current day is 31st

	var todaydate=new Date() //DD added
	var scanfortoday=(y==todaydate.getFullYear() && m==todaydate.getMonth()+1)? todaydate.getDate() : 0 //DD added

	dim[1]=(((oD.getFullYear()%100!=0)&&(oD.getFullYear()%4==0))||(oD.getFullYear()%400==0))?29:28;
	var t='<div class="'+cM+'"><table class="'+cM+'" cols="7" cellpadding="0" border="'+brdr+'" cellspacing="0"><tr align="center">';
	t+='<td colspan="7" align="center" class="'+cH+'">'+mn[m-1]+' - '+y+'</td></tr><tr align="center">';
	for(s=0;s<7;s++)t+='<td class="'+cDW+'">'+"SMTWTFS".substr(s,1)+'</td>';
	t+='</tr><tr align="center">';
	for(i=1;i<=42;i++){
	var x=((i-oD.od>=0)&&(i-oD.od<dim[m-1]))? i-oD.od+1 : '&nbsp;';
	if (x==scanfortoday) //DD added
	x='<span id="today">'+x+'</span>' //DD added
	t+='<td class="'+cD+'">'+x+'</td>';
	if(((i)%7==0)&&(i<36))t+='</tr><tr align="center">';
	}
	return t+='</tr></table></div>';
	}