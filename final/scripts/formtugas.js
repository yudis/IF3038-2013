var assignee = [];

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var ck_taskname = /[a-zA-Z0-9 ]{1,25}$/;
function initializeCreateTugas() {
	if(typeof(Storage)!=="undefined") {
		if (localStorage.session) {
			var innerhtml = "<a href='profile.php'><img src='avatar/"+localStorage.session+".jpg' alt='Profile page' width='50' height='50'><br/>Hi, "+localStorage.session+"!</a>";
	
			document.getElementById("profil").innerHTML = innerhtml;
			document.getElementById("dashboardlink").innerHTML = "<a href='dashboard.php?uname="+localStorage.session+"&cat=all'>Dashboard</a>";
			loadUser();
		}
		else {
			window.location = "index.php";
		}
	}
	else {
		alert("Local storage not supported");
	}
}

function loadUser() {
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
			document.getElementById("datalistuser").innerHTML = xmlhttp2.responseText;
		}
	}
	
	xmlhttp2.open("GET","loaduser.php",true);
	xmlhttp2.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp2.send();
}

function validate(form) {
    var taskname = form.namatask.value;
    var attachment = form.attachment.value;
    var deadline = form.deadline.value;
    var assignee = form.assignee.value;
    var tag = form.tag.value;
    var errors = [];
    
    if(!ck_taskname.test(taskname)) {
        errors[errors.length] = "Nama tugas tidak valid.";
    }
    
    if (assignee=="") {
        errors[errors.length] = "Assignee tidak valid.";
    }
    
    if (tag=="") {
        errors[errors.length] = "Tag tidak valid.";
    }

    if (deadline=="") {
        errors[errors.length] = "Deadline belum diisi.";
    }
    if (errors.length > 0) {
        reportErrors(errors);
        return false;
    }
	
	alert("correct");
    
    return true;
}

function reportErrors(errors){
 var msg = "Please Enter Valide Data...\n";
 for (var i = 0; i<errors.length; i++) {
 var numError = i + 1;
  msg += "\n" + numError + ". " + errors[i];
}
 alert(msg);
}

function parseForm() {
	var element = document.getElementById('tag');
	var element2 = document.getElementById('assignee');
	
	element.value=element.value.replace(",","~");
	element.value=element.value.replace(" ","");
	element2.value=element.value.replace(",","~");
	element2.value=element.value.replace(" ","");
	
	alert("launched");
	
	return false;
}
