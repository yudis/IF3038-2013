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

function removeComment(idcomment,jumcom){

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
	  if(xmlhttp.responseText=="berhasil")
              {
                  document.getElementById(idcomment).innerHTML="";
                  jumcom--;
                  document.getElementById("a").innerHTML="Komentar("+jumcom+")";     
              }
          
            }
	  }

var queryString = "idcomment="+idcomment;
xmlhttp.open("POST", 'removeComment', true);
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.send(queryString);
}

function addcomment(username,jum){
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
                var total=xmlhttp.responseText.split(",");
		document.getElementById("comment").innerHTML+="<div id=\""+i+"\"><div class=\"headerComment\"><div class=avatar style=\"float:left;\"><img src="+total[2]+" height=\"42\" width=\"42\"></div><div class=username style=\"float:left;\"><b>"+total[1]+"</b></div><div class=waktu><b>"+task.comment.get(i).waktu+"</b></div><div>";
                        
                        if(!(task.comment.get(i).username.equals("yuli")))
                        {}
                        else
                        {
                        out.print ("<a class=\"remove\" href=\"\" onClick=\"removeComment("+task.comment.get(i).id+","+task.comment.size()+");return false;\" >remove");

                        out.print ("</a>");
                        }
                        out.print ("</div>");
                        out.print ("</div>");
                        out.print ( "<li>"+task.comment.get(i).isi+"</li>");
                        out.print ("</div>");
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
	document.getElementById("tanggal").setAttribute('onclick', 'return false');
        document.getElementById("edit").setAttribute('onclick','editTask('+jumlahA+')');
	document.getElementById("inputtag").setAttribute("style","visibility:hidden;position:absolute; top:15px; left:154px;");
        document.getElementById("asignee").setAttribute("style","visibility:hidden;");
	var assignee=document.getElementById("asignee").value;
        if(assignee!="")
            {
                
                var jumlah=document.getElementById("jumlahA").innerHTML;
                jumlah++;
                document.getElementById("jumlahA").innerHTML=jumlah;
                document.getElementById("edit").setAttribute('onclick','editTask('+jumlah+')');
            }
	var i=0;
	document.getElementById("asignee").value="";
        document.getElementById("assignee").innerHTML="";
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
                          
                              //window.location.reload();
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
      if(xmlhttp.responseText=="sukses")
          {
              document.getElementById(username).innerHTML="";
          }
	
	}

  }
var queryString = "username="+username;
xmlhttp.open("POST", 'removeAssignee', true)
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
xmlhttp.send(queryString);
}