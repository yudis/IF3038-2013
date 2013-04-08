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
xmlhttp.open("GET",'removeComment'+queryString,true);

xmlhttp.send('');
}

function addcomment(username){
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
		}
	  }
	  
var queryString = "comment="+document.getElementById('commentfield').value+"&usernamecur="+username;
xmlhttp.open("POST", 'addComment', true);
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.send(queryString);
}

function editTask(jumlahAssignee)
{
	document.getElementById("edit").innerHTML="<b>Save</b>";
	document.getElementById("edit").setAttribute('onclick','save('+jumlahAssignee+')');
	document.getElementById("tanggal").setAttribute('onclick', '');
	var i=0;
        
	for(i;i<jumlahAssignee;i++) {
            if(document.getElementById("r"+i)!=null)
                {
                    document.getElementById("r"+i).setAttribute("style","visibility:visible; position:relative; left:3px;");
                }
	}
        document.getElementById("inputtag").setAttribute("style","visibility:visible;position:absolute; top:15px; left:154px;");	
	document.getElementById("assignee").innerHTML+="<br><input id=\"asignee\" type=\"text\" placeholder=\"assignee\" onkeyup='searchSuggest()'></input>";
}
function save(jumlahA){
    
	document.getElementById("edit").innerHTML="<b>Edit</b>";
	document.getElementById("edit").setAttribute('onclick','editTask('+jumlahA+')');
	document.getElementById("tanggal").setAttribute('onclick', 'return false');
	document.getElementById("inputtag").setAttribute("style","visibility:hidden;position:absolute; top:15px; left:154px;");
        document.getElementById("asignee").setAttribute("style","visibility:hidden;");
	var assignee=document.getElementById("asignee").value;
        if(assignee!="")
            {
                var jumlah=document.getElementById("jumlahA").innerHTML;
                document.getElementById("jumlahA").innerHTML=jumlah;
            }
	var i=0;
	document.getElementById("asignee").innerHTML="";
	//document.getElementById("anggota").innerHTML+="<div id=\""+assignee+"\"><a  href=\"profile.php?username="+assignee+"\">"+assignee+"</a><a id=\"r"+(jumlahA++)+"\" href=\"#\" style=\"visibility:hidden\" onclick=\"removeA('"+assignee+"')\">(remove)</a><br></div>";
	for(i;i<jumlahA;i++) {
            if(document.getElementById("r"+i)!=null)
                {
                    document.getElementById("r"+i).setAttribute("style","visibility:hidden; position:relative; left:3px;");
                }
            
	}
	var tag=document.getElementById("inputtag").value;
	var deadline=document.getElementById("deadline").value;
        
        var n=deadline.split("-"); 
        deadline=n[2]+"-"+n[1]+"-"+n[0];
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
                      alert(xmlhttp.responseText);
                      if(deadline!="" && tag!="" && assignee!="")
                          {
                              var total=xmlhttp.responseText.split(",");
                              var cal=total[0].split("-");
                              cal=cal[2]+"-"+cal[1]+"-"+cal[0];
                              
                              document.getElementById("deadline").setAttribute('value', cal);
                              document.getElementById("data").innerHTML = total[1];
                              document.getElementById("anggota").innerHTML+="<div id=\""+total[2] +"\"><a  href=\"profile.jsp?username="+total[2]+"\">"+total[2]+"</a><a id=\"r"+jumlahA+"\" href=\"#\" style=\"visibility:hidden\" onclick=\"removeA('"+total[2]+"')\">(remove)</a><br></div>";                              
                          }
			  else if(deadline!="" && tag!="")
			  {
                              var total=xmlhttp.responseText.split(",");
                              var cal=total[0].split("-");
                              cal=cal[2]+"-"+cal[1]+"-"+cal[0];
                              document.getElementById("deadline").setAttribute('value', cal);
                              document.getElementById("data").innerHTML = total[1];
                          }
			  else if(deadline!="" && assignee!="")
			  {
                              var total=xmlhttp.responseText.split(",");
                              var cal=total[0].split("-");
                              cal=cal[2]+"-"+cal[1]+"-"+cal[0];
                              document.getElementById("deadline").setAttribute('value', cal);
                              document.getElementById("anggota").innerHTML+="<div id=\""+total[1] +"\"><a  href=\"profile.jsp?username="+total[1]+"\">"+total[1]+"</a><a id=\"r"+jumlahA+"\" href=\"#\" style=\"visibility:hidden\" onclick=\"removeA('"+total[1]+"')\">(remove)</a><br></div>";			  
                          }
                          else if(deadline!="")
                              {
                                  var total=xmlhttp.responseText;
                                  var cal=total.split("-");
                                  cal=cal[2]+"-"+cal[1]+"-"+cal[0];
                                  document.getElementById("deadline").setAttribute('value', cal);
                                  
                              }
                          else if(deadline=="" && tag!="" && assignee!="")
                          {
                             var total=xmlhttp.responseText.split(",");
                             document.getElementById("data").innerHTML = total[0];
                             document.getElementById("anggota").innerHTML+="<div id=\""+total[1] +"\"><a  href=\"profile.jsp?username="+total[1]+"\">"+total[1]+"</a><a id=\"r"+jumlahA+"\" href=\"#\" style=\"visibility:hidden\" onclick=\"removeA('"+total[1]+"')\">(remove)</a><br></div>";			  
                          }
                          else if(deadline=="" && tag!="" && assignee=="")
                              {
                                document.getElementById("data").innerHTML = xmlhttp.responseText;
                                  
                              }
                          else if(deadline=="" && tag=="" && assignee!="")
                              {
                                 document.getElementById("anggota").innerHTML+="<div id=\""+xmlhttp.responseText+"\"><a  href=\"profile.jsp?username="+xmlhttp.responseText+"\">"+xmlhttp.responseText+"</a><a id=\"r"+jumlahA+"\" href=\"#\" style=\"visibility:hidden\" onclick=\"removeA('"+xmlhttp.responseText+"')\">(remove)</a><br></div>";			     
                              }
                          else
                              {
                                  alert("error");
                              }
                              window.location.reload()
			}

		  }
		var queryString = "tag="+tag+"&deadline="+deadline+"&assignee="+assignee;
		xmlhttp.open("POST", 'edittask', true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
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
xmlhttp.open("POST", 'removeAssignee', true)
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
xmlhttp.send(queryString);
}