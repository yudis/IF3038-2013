function showAssignee()
{
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
		document.getElementById("user").innerHTML=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET","ajax/assigneeList.php",true);
	xmlhttp.send();
}

function addAssignees() {
    var newAssignee = document.getElementById("assignee");
    var assigneesList = document.getElementById("assigneesList");
    
    if (newAssignee.value == "") {
        alert("Nama harus dimasukkan");
        return false;
    }
    
    document.getElementById("assigneeI").value+=newAssignee.value+",";
	assigneeList.innerHTML+= "<li>"+newAssignee.value+"</li>";
	newAssignee.value="";
    
}
