/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function showList(){
document.getElementById("listtugas3").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="hidden";
document.getElementById("listtugas").style.visibility="visible";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="hidden";
document.getElementById("wanted").style.visibility="hidden";

}

function showList2(){
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="visible";
document.getElementById("listtugas3").style.visibility="hidden";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="hidden";
document.getElementById("wanted").style.visibility="hidden";
self.focus;
}

function showList3(){
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="hidden";
document.getElementById("listtugas3").style.visibility="visible";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="hidden";
document.getElementById("wanted").style.visibility="hidden";
self.focus();
}

function showRinci(){
document.getElementById("listtugas3").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="hidden";
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("rincitugas").style.visibility="visible";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="hidden";
document.getElementById("wanted").style.visibility="visible";
self.focus();
}

function showEdit(){
document.getElementById("listtugas3").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="hidden";
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="visible";
document.getElementById("buattugas").style.visibility="hidden";
document.getElementById("wanted").style.visibility="visible";
}
function showBuat(){
document.getElementById("listtugas3").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="hidden";
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="visible";
document.getElementById("wanted").style.visibility="visible";
}

var valid = false;

function createTask() {
    var regex = /^[a-zA-Z0-9]{5,25}$/;
    
    if ((regex.test(document.getElementById("namaTask").value))){
        var k = document.getElementById("listtugas");
        k.innerHTML = "<a class='listTugas' onclick='showRinci();'><a class='listTugas' onclick='showRinci();'></a> <a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a onclick='showBuat()' class='addTask'></a>";
        document.getElementById("namaTask").value = 0;
        showList();
    } else {
            alert("task name must be 5-25 long");
    }
}

function addCat() {
        var k = document.getElementById("category");
        var l = document.getElementById("cate").value;
        if(l !== "") {
			k.innerHTML += "<div class='kategori' onclick='showList2();'>"+l+"</div>";
			restore();
			showList();
        } else {
            alert("Input category name");
        }
}

function addCategory() {
   var overlay = document.createElement("div");
   overlay.setAttribute("id","overlay");
   overlay.setAttribute("class", "overlay");
   document.body.appendChild(overlay);
   
   document.getElementById('add').style.display='block';
}

function editProfile() {
   var overlay = document.createElement("div");
   overlay.setAttribute("id","overlay");
   overlay.setAttribute("class", "overlay");
   document.body.appendChild(overlay);
   
   document.getElementById('edit').style.display='block';
}

function restore() {
   document.body.removeChild(document.getElementById("overlay"));
   document.getElementById('add').style.display='none';
   document.getElementById('overlay').style.display='none';
}

function profileRestore() {
   document.body.removeChild(document.getElementById("overlay"));
   document.getElementById('edit').style.display='none';
   document.getElementById('overlay').style.display='none';
}

var clickable = false;

function Redirect(){
    window.location = "index.html";
}

function Login(){
    if (document.getElementById("logusername").value !== "admin"){
        alert("Wrong username!");
    } else if (document.getElementById("logpassword").value !== "admincool"){
        alert("Wrong password!");
    }else{
        window.location = "Dashboard.html";
        localStorage.username = document.getElementById("logusername").value;
        localStorage.name = "Billy The Kid";
        localStorage.date = "1968-09-3";
        localStorage.email = "coolKid@yahoo.com";
        document.getElementById("foto").src = "img/foto.png";
    }
}

function Register(){
    var atPos = document.getElementById("regemail").value.indexOf("@");
    var dotPos = document.getElementById("regemail").value.indexOf(".");
    if ((document.getElementById("regusername").value.length < 5) && (document.getElementById("regusername").value !== "")){
        alert("Username should be at least 5 characters long.");
    } else if ((document.getElementById("regpassword1").value.length < 8) && (document.getElementById("regpassword1").value !== "")){
        alert("Password should be at least 8 characters long.");
    } else if ((document.getElementById("regusername").value === document.getElementById("regpassword1").value) && (document.getElementById("regusername").value !== "") && (document.getElementById("regpassword1").value !== "")){
        alert("Username and password cannot be identical.");
    } else if ((document.getElementById("regpassword1").value !== document.getElementById("regpassword2").value) && (document.getElementById("regpassword2").value !== "")){
        alert("Confirmed password and password are not the same.");
    } else if ((document.getElementById("regname").value.indexOf(" ") < 0) && (document.getElementById("regname").value !== "")) {
        alert("Name should be constructed by two or more words separated by space.");
    } else if ((document.getElementById("regemail").value !== "") && (atPos < 1)){
            alert("There should be at least one character before '@' character.");
    } else if ((document.getElementById("regemail").value !== "") && (dotPos - atPos < 2)){
            alert("There should be at least one character between '@' and '.' character.");
    } else if ((document.getElementById("regemail").value !== "")&&(document.getElementById("regemail").value.length - dotPos < 3)){
            alert("There should be at least two characters after '.' character");
    } else if ((document.getElementById("regusername").value !== "")&&(document.getElementById("regpassword1").value !== "")&&
        (document.getElementById("regpassword2").value !== "")&&(document.getElementById("regname").value !== "")&&
        (document.getElementById("regemail").value !== "")){
            document.getElementById("regbutton").style.color = "black";
            document.getElementById("regbutton").style.fontWeight = "bold";
            clickable = true;
    } else {
        document.getElementById("regbutton").style.color = "#777777";
        document.getElementById("regbutton").style.fontWeight = "normal";
        clickable = false;
    }
}

function edit(){
	if ((document.getElementById("regname").value.indexOf(" ") < 0)) {
        //alert("Name should be constructed by two or more words separated by space.");
	} else if ((document.getElementById("regpassword1").value.length < 8)){
        alert("Password should be at least 8 characters long.");
    } else if ((document.getElementById("regpassword1").value !== document.getElementById("regpassword2").value)){
		alert("Confirmed password and password are not the same.");
	}
}

function Submit(){
    if (clickable){
        window.location = "Dashboard.html";
        localStorage.username = document.getElementById("regusername").value;
        localStorage.name = document.getElementById("regname").value;
        localStorage.date = document.getElementById("regdate").value;
        localStorage.email = document.getElementById("regemail").value;
        document.getElementById("foto").src = "img/foto_anonim.png";
    }
}

function auto_complete(str)
{
	document.getElementById("box").value = str;
	document.getElementById("hasilsearch").innerHTML="";
	document.getElementById("hasilsearch").style.visibility="none";
}

function showfilter(){
	document.getElementById("filter").style.height = "64px";
}

function hiddenfilter(){
	document.getElementById("filter").style.height = "0px";
}

function filter(str)
{
	document.getElementById("selectedKategori").value = str;
	document.getElementById("filter").style.height = "0px";
}

function showHint(str)
{
	if (str.length==0)
	  { 
	  document.getElementById("hasilsearch").innerHTML="";
	  document.getElementById("hasilsearch").style.visibility="hidden";
	  return;
	  }
	  
	var tipe = document.getElementById("selectedKategori").value;
	
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
		if (tipe == "task"){
			var string = xmlhttp.responseText.split("<br>");
			var result = "";
			var check = true;
			result = "<ul>";
			if (string.length > 1)
			{
				result += "<li class=\"judul\">task</li>";
				for (var s=1; s<string.length; s++)
				{
					if (document.getElementById("box").value.toLowerCase() == string[s].toLowerCase())
						check = false;
					result += "<li onclick=\"auto_complete(this.innerHTML);\">"+string[s]+"</li>";
				}
			}
			if (check)
			{
				result += "</ul>";
				document.getElementById("hasilsearch").innerHTML=result;
				document.getElementById("hasilsearch").style.visibility="visible";
			}
			else
			{
				document.getElementById("hasilsearch").innerHTML="";
				document.getElementById("hasilsearch").style.visibility="none";
			}
		} else if (tipe == "username"){
			var string = xmlhttp.responseText.split("<br>");
			var result = "";
			var check = true;
			result = "<ul>";
			if (string.length > 1)
			{
				result += "<li class=\"judul\">user</li>";
				for (var s=1; s<string.length; s++)
				{
					if (document.getElementById("box").value.toLowerCase() == string[s].toLowerCase())
						check = false;
					result += "<li onclick=\"auto_complete(this.innerHTML);\">"+string[s]+"</li>";
				}
			}
			if (check)
			{
				result += "</ul>";
				document.getElementById("hasilsearch").innerHTML=result;
				document.getElementById("hasilsearch").style.visibility="visible";
			}
			else
			{
				document.getElementById("hasilsearch").innerHTML="";
				document.getElementById("hasilsearch").style.visibility="none";
			}
		} else if (tipe == "category"){
			var string = xmlhttp.responseText.split("<br>");
			var result = "";
			var check = true;
			result = "<ul>";
			if (string.length > 1)
			{
				result += "<li class=\"judul\">kategori</li>";
				for (var s=1; s<string.length; s++)
				{
					if (document.getElementById("box").value.toLowerCase() == string[s].toLowerCase())
					check = false;
					result += "<li onclick=\"auto_complete(this.innerHTML);\">"+string[s]+"</li>";
				}
			}
			if (check)
			{
				result += "</ul>";
				document.getElementById("hasilsearch").innerHTML=result;
				document.getElementById("hasilsearch").style.visibility="visible";
			}
			else
			{
				document.getElementById("hasilsearch").innerHTML="";
				document.getElementById("hasilsearch").style.visibility="none";
			}
		} else if (tipe == "all result"){
			var string = xmlhttp.responseText.split(",");
			var result = "";
			var check = true;
			result = "<ul>";
			for (var s in string)
			{
				var string2 = string[s].split("<br>");
				if (string2.length > 1)
				{
					result+="<li class=\"judul\">"+string2[0]+"</li>";
					for(var s2=1; s2<string2.length ; s2++)
					{
						if (document.getElementById("box").value.toLowerCase() == string2[s2].toLowerCase())
							check = false;
						result += "<li onclick=\"auto_complete(this.innerHTML);\">"+string2[s2]+"</li>";
					}
				}
			}
			if (check)
				{
					result += "</ul>";
					document.getElementById("hasilsearch").innerHTML=result;
					document.getElementById("hasilsearch").style.visibility="visible";
				}
				else
				{
					document.getElementById("hasilsearch").innerHTML="";
					document.getElementById("hasilsearch").style.visibility="none";
				}
		}
		
		/*
		var string = xmlhttp.responseText.split("<br>");
		var result = "";
		var check = true;
		result = "<ul>";
		//result += "<li class=\"judul\">task</li>"
		for (var s in string)
		{
			if (document.getElementById("box").value.toLowerCase() == string[s].toLowerCase())
				check = false;
			result += "<li onclick=\"auto_complete(this.innerHTML);\">"+string[s]+"</li>";
		}
						
		if (check)
		{
			result += "</ul>";
			document.getElementById("hasilsearch").innerHTML=result;
			document.getElementById("hasilsearch").style.visibility="visible";
		}
		else
		{
			document.getElementById("hasilsearch").innerHTML="";
			document.getElementById("hasilsearch").style.visibility="none";
		}*/
		
	   }
	  }
	xmlhttp.open("GET","autosearch.php?q="+str+"&tipe="+tipe,true);
	xmlhttp.send();
}