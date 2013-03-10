function setActiveStyleSheet(title) {
	var i, a, main;
	for(i = 0; (a = document.getElementsByTagName("link")[i]); i++) {
		if(a.getAttribute("rel").indexOf("style") != -1 && a.getAttribute("title")) {
			a.disabled = true;
			if(a.getAttribute("title") == title) a.disabled = false;
		}
	}
}

function getActiveStyleSheet() {
  var i, a;
  for(i=0; (a = document.getElementsByTagName("link")[i]); i++) {
	if(a.getAttribute("rel").indexOf("style") != -1 && a.getAttribute("title") && !a.disabled) return a.getAttribute("title");
  }
  return null;
}

function changeStyleSheet() {
	if (getActiveStyleSheet() == 'style1') {
		setActiveStyleSheet('style2');
	} else {
		setActiveStyleSheet('style1');
	}
}

//Vincentius Martin
function loginMenu(){
	var elmt = document.getElementById('droplogin');
        if(elmt.style.visibility=="hidden"){
            elmt.style.visibility = "visible";
        }else{
            elmt.style.visibility = "hidden";
        }
}

function getDashboardFocus(taskid){
    var htmlcontentplain = "<div class=\"atas\"></div>\n\
                            <div class=\"tengah\"><img src =\"images/ProginBig.png\" alt=\"task\" />\n\
                                    <img src =\"images/DateBig.png\" alt=\"tasklv\" /></div>\n\
                            <div class=\"bawah\"></div>";                            
    var htmlcontent1 = "<div class=\"atas\"></div>\n\
                        <div class=\"tengah\"> \n\
                        <div class=\"judul\">\n\
                            Pemrograman Internet\n\
                        </div>\n\
                        <div class=\"isi\">\n\
                            <img src=\"images/ProginBig.png\" alt=\"gambar 1\" \"/>\n\
                        </div>\n\
                        </div>\n\
                        <div class=\"bawah\"></div>";
    var htmlcontent2 = "<div class=\"atas\"></div>\n\
                        <div class=\"tengah\"> \n\
                        <div class=\"judul\">\n\
                            Kencan Uhuy\n\
                        </div>\n\
                        <div class=\"isi\">\n\
                            <img src=\"images/DateBig.png\" alt=\"gambar 1\" \"/>\n\
                        </div>\n\
                        </div>\n\
                        <div class=\"bawah\"></div>";
    
    if(taskid=='task1'){
        document.getElementById('rincian').innerHTML = htmlcontent1;
        document.getElementById('newtask').disabled = false;
    }else if(taskid=='task2'){
        document.getElementById('rincian').innerHTML = htmlcontent2;
        document.getElementById('newtask').disabled = false;
    }else{
        document.getElementById('rincian').innerHTML = htmlcontentplain;
    }
}

function searchFocus(elmt){
    if(elmt.value==elmt.defaultValue){ elmt.value=""; elmt.style.color="#000"; }
}

function searchBox(elmt){
     if(elmt.value==""){ elmt.value=elmt.defaultValue; elmt.style.color="#888"; }
}

//Kevin
var login = false;
var getId = new Array(2);
getId[0] = "admin1";
getId[1] = "admin2";
getId[2] = "admin3";
var getPass = new Array(2);
getPass[0] = "adminku";
getPass[1] = "adminmu";
getPass[2] = "adminnya";

function validateLogin() {
	var i = 0;
	var x = document.getElementById("usernameku").value
	var y = document.getElementById("passwordku").value
	for (i = 0; i< 3;i++){
		if ((x == getId[i])&& ((y == getPass[i])))
		{
			login = true;
		}
	}
	if (login ==true)
	{
		window.location='profil.html';
	}
	else
	{
		window.location='mainpage.html';
	}
}

function validateLogout() {
	login = false;
	window.location='mainpage.html';
}

function editTask() {
	window.location='rinciantugas2.html';
}

function editTask2() {
	window.location='rinciantugas.html';
}

function validateTaskName()
{
	var x=document.getElementById("judul").value;
	var i = 0;
	var flag = true;
	if (x=="")
	{
		flag = false;
	}
	for(i = 0;i<x.length;i++){
		var y = x.charAt(i);
		
		if ((y.charCodeAt(0)>=48 && y.charCodeAt(0)<=57) || (y.charCodeAt(0)>=65 && y.charCodeAt(0)<=90)|| (y.charCodeAt(0)>=97 && y.charCodeAt(0)<=122)){
			
		}
		else
		{
	  		flag = false;
		}
		
	}
	if (flag == true){
			document.getElementById("judulvalidate").innerHTML="<font color='green'>Input valid</font>";
		}
		else
		{
			document.getElementById("judulvalidate").innerHTML="<font color='red'>Task name tidak boleh menggunakan karakter khusus</font>";
		}
	return flag;
}

function validateExtentionName()
{
	var x=document.getElementById("txtbox").value;
	var i = 0;
	var y="";
	var dotpos=x.lastIndexOf(".");
	
	for(i = dotpos;i<=x.length;i++){
		y =y.concat(x.charAt(i));
		
	}
	if ((y==".jar")||(y==".avi")||(y==".mp4")||(y==".mp3")||(y==".jpg")||(y==".jpeg")||(y==".txt") ){
		
			document.getElementById("demo").innerHTML="Input valid";
			document.getElementById("buttonReg").disabled = false;
		}
		else
		{
			document.getElementById("demo").innerHTML="Input tidak valid";
			document.getElementById("buttonReg").disabled = true;
			alert("Not a valid extention name");
	  			return false;
		}
}

function validatePostTask()
{
	if(validateTaskName())
	{
		document.getElementById("post1").disabled = false;
	}
	else
	{
		document.getElementById("post1").disabled = true;
	}
}