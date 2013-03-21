/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var t = "0";
	
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

function createTask() {
    var regex = /^[a-zA-Z0-9]{5,25}$/;
    
    if ((regex.test(document.getElementById("namaTask").value))){
        var k = document.getElementById("listtugas");
        k.innerHTML = "<a class='listTugas' onclick='showRinci();'><a class='listTugas' onclick='showRinci();'></a> <a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a 	class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a onclick='showBuat()' class='addTask'></a>";
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

var clickable = false;

function Redirect(){
    window.location = "index.php";
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
        window.location = "Dashboard.php";
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

function selectedkategori(str){
	t = str;
	showTask(t);
}

function showKategori(){
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
			
			if (xmlhttp.responseText != "")
			{
				var result = "";
				var string = xmlhttp.responseText.split("<br>");
				for (var s=1; s < string.length; s++)
				{
					result += "<div class=\"kategori\" onclick=\"selectedkategori(this.innerHTML);\">"+string[s]+"</div>";
				}
				
				document.getElementById("category").innerHTML=result;
			}
			else
			{
				document.getElementById("category").innerHTML="";
			}
		  }
	  }
	xmlhttp.open("GET","allkategori.php",true);
	xmlhttp.send();
}

function showTask(str){
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp2=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp2=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp2.onreadystatechange=function()
	  {
		  if (xmlhttp2.readyState==4 && xmlhttp2.status==200)
		  {
			  if (xmlhttp2.responseText != "")
				{
					if (str=="0")
					{
						var result = "";
						var string1 = xmlhttp2.responseText.split("<br>");
						for (var s=1;s<string1.length;s++)
						{
							var string2 = string1[s].split(",");
							result += "<div class=\"task\">";
							result += "<a href=\"#\">"+string2[0]+"</a>";
							result += "<span><br>deadline : "+string2[1]+"</span>";
							
							if (string2.length > 4)
							{
								result += "<span><br>tag : ";
								for (var i=5; i< string2.length; i++)
								{
									result += string2[i]+",";
								}
							}
							
							if (string2[2] == "0"){
								result += "<br><input type=\"checkbox\" name=\"done\" value=\"done\"> done";
							} else if (string2[2] == "1")
							{
								result += "<br><input type=\"checkbox\" name=\"done\" value=\"done\" checked> done";
							}
							
							if (string2[4] == "yes"){
								result += "<button onclick=\"deletetask("+string2[0]+")\">delete</button>";
							}
							
							result += "</div>";
						}
						
						document.getElementById("listtugas").innerHTML=result;
					}
					else
					{
						var result = "";
						result += "<div><button onclick=\"deletekategori("+str+")\">delete kategori</button></div>";
						result += "<div class=\"addtask\"><a href=\"#\">+ task</a></div>";
						var string1 = xmlhttp2.responseText.split("<br>");
						for (var s=1;s<string1.length;s++)
						{
							var string2 = string1[s].split(",");
							result += "<div class=\"task\">";
							result += "<a href=\"#\">"+string2[0]+"</a>";
							result += "<span><br>deadline : "+string2[1]+"</span>";
							
							if (string2.length > 4)
							{
								result += "<span><br>tag : ";
								for (var i=5; i< string2.length; i++)
								{
									result += string2[i]+",";
								}
							}
							
							if (string2[2] == "0"){
								result += "<br><input type=\"checkbox\" name=\"done\" value=\"done\"> done";
							} else if (string2[2] == "1")
							{
								result += "<br><input type=\"checkbox\" name=\"done\" value=\"done\" checked> done";
							}
							
							if (string2[4] == "yes"){
								"<button onclick=\"deletetask("+string2[0]+")\">delete</button>"
							}
							
							result += "</div>";
						}

						document.getElementById("listtugas").innerHTML=result;
					}
				}
			  else{
				document.getElementById("listtugas").innerHTML="";
			  }
		  }
	  }
	xmlhttp2.open("GET","showTask.php?q="+str,true);
	xmlhttp2.send();
}

function deletekategori(str)
{

}

function deletetask(str)
{

}

function update(){
	showTask(t);
	showKategori();
}

// setInterval(function(){update();},5000)
function Loginaja(){
	//Variable for authentication
	var username = document.getElementById("logusername").value;
	var password = document.getElementById("logpassword").value;
	var xmlhttp;
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
			if(xmlhttp.responseText==0){
				alert("Your username and/or password is wrong!");
			}else{
				window.location = "Dashboard.php";
			}
		}
	}
	xmlhttp.open("GET","authentication.php?usr="+username+"&psw="+password,true);
	xmlhttp.send();
}

function restore() {
   document.body.removeChild(document.getElementById("overlay"));
   document.getElementById('add').style.display='none';
   document.getElementById('overlay').style.display='none';
}

function editProfile() {
   var overlay = document.createElement("div");
   overlay.setAttribute("id","overlay");
   overlay.setAttribute("class", "overlay");
   document.body.appendChild(overlay);
   
   document.getElementById('edit').style.display='block';
}

function profileRestore() {
   document.body.removeChild(document.getElementById("overlay"));
   document.getElementById('edit').style.display='none';
   // document.getElementById('overlay').style.display='none';
}