function fileallowed(){
//	alert("masuk");
	var file = document.getElementById('avatar');
   var ext = file.value.match(/\.([^\.]+)$/)[1];
	
    switch(ext)
    {
        case 'jpg':
        case 'jpeg':
		case 'png':
		case 'ogg':
            break;
        default:
		 file.value='';
            alert('Tipe file tidak diizinkan.\nSilahkan ulangi masukan');
           
    }
}

function removeComment(idcomment){

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
	  if(xmlhttp.readyState == 4){
	  document.getElementById(idcomment).innerHTML="";
	  document.getElementById("a").innerHTML="Komentar("+xmlhttp.responseText+")";
			}
	  }
  var queryString = "?idcomment="+idcomment;
xmlhttp.open("GET","taskdetailscontroller.php"+queryString,true);

xmlhttp.send('');
}

function addcomment(username){
var xmlhttp;
alert("masuk");
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
	  if(xmlhttp.readyState == 4){
		alert(xmlhttp.responseText);
		}
	  }
	  
var queryString = "comment="+document.getElementById('commentfield').value+"&usernamecur="+username;
xmlhttp.open("POST", "taskdetailscontroller.php", true);
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.send(queryString);
}

function editTask(jumlahAssignee)
{
		
	document.getElementById("edit").innerHTML="<b>Save</b>";
	document.getElementById("edit").setAttribute('onclick','save('+jumlahAssignee+')');
	document.getElementById("tanggal").setAttribute('onclick', '');
	//document.getElementById("deadline").value="";
	var i=1;
	for(i;i<=jumlahAssignee;i++) {
	document.getElementById("r"+i).setAttribute("style","visibility:visible; position:relative; left:3px;");
	document.getElementById("inputtag").setAttribute("style","visibility:visible;position:absolute; top:15px; left:154px;");
	
	}
	document.getElementById("assignee").innerHTML+="<br><input id=\"asignee\" type=\"text\" placeholder=\"assignee\" onkeyup='searchSuggest()'></input>";
}
function save(jumlahA){
	document.getElementById("edit").innerHTML="<b>Edit</b>";
	document.getElementById("edit").setAttribute('onclick','editTask('+jumlahA+')');
	document.getElementById("tanggal").setAttribute('onclick', 'return false');
	document.getElementById("inputtag").setAttribute("style","visibility:hidden;position:absolute; top:15px; left:154px;");
	var assignee=document.getElementById("asignee").value;
	var i=1;
	document.getElementById("assignee").innerHTML="";
	//document.getElementById("anggota").innerHTML+="<div id=\""+assignee+"\"><a  href=\"profile.php?username="+assignee+"\">"+assignee+"</a><a id=\"r"+(jumlahA++)+"\" href=\"#\" style=\"visibility:hidden\" onclick=\"removeA('"+assignee+"')\">(remove)</a><br></div>";
	for(i;i<=jumlahA;i++) {
	document.getElementById("r"+i).setAttribute("style","visibility:hidden; position:relative; left:3px;");
	}
	var tag=document.getElementById("inputtag").value;
	var deadline=document.getElementById("deadline").value;

var n=deadline.split("-"); 
deadline=n[2]+"-"+n[1]+"-"+n[0];
alert(assignee)	;
	document.getElementById("inputtag").value="";
	if(tag=="" && deadline=="" && assignee=="")
	{}
	else
	{
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
		  
		  if(xmlhttp.readyState == 4){
			  if(tag=="")
			  {}
			  else
			  {
				document.getElementById("data").innerHTML=tag;
				}	
			}

		  }
		var queryString = "tag="+tag+"&deadline="+deadline+"&assignee="+assignee;
		xmlhttp.open("POST", "taskdetailscontroller.php", true)
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
		xmlhttp.send(queryString);
	}
}
function removeA(username)
{
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
  
  if(xmlhttp.readyState == 4){
	document.getElementById(username).innerHTML="";
	}

  }
var queryString = "username="+username;
xmlhttp.open("POST", "taskdetailscontroller.php", true)
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
xmlhttp.send(queryString);
}