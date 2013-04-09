function toggle_visibility(id) {
	var e = document.getElementById(id);
	if(e.style.display == 'block')
		e.style.display = 'none';
	else
		e.style.display = 'block';
}

function add_category() {
	var t = "<li>";
	t += "<a href ='#' onclick='catchange(10)' id='newCategoryAdded'>";
	t += document.getElementById("add_category_name").value;
	t += "</a>";
	t += "</li>";
	document.getElementById("category_item").innerHTML += t;
}

var regValid = 0;
function regCheck() {
	var username = document.getElementById("reg_username").value;
	var password = document.getElementById("reg_password").value;
	var confirm = document.getElementById("reg_confirm").value;
	var name = document.getElementById("reg_name").value;
	var email = document.getElementById("reg_email").value;
	var birthdate = document.getElementById("reg_birthdate").value;
	var avatar = document.getElementById("avatar_upload").value;
	//alert(username + " " + password+ " " + confirm + " " +email + " " + birthdate + " " +avatar);
	
	//check username
	if ((username.length > 4) && (username != password)) {
		document.getElementById("username_validation").src = "img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("username_validation").src = "img/no.png";
		regValid = 0;
	}
	document.getElementById("username_validation").style.display = "block";
	
	//check password
	if ((password.length > 7) && (password != username) && (password != email)) {
		document.getElementById("password_validation").src = "img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("password_validation").src = "img/no.png";
		regValid = 0;
	}
	document.getElementById("password_validation").style.display = "block";

	//check confirm
	//alert("confirm = " + confirm);
	if ((confirm != password) || (confirm == '') || (confirm == null)) {
		document.getElementById("confirm_validation").src = "img/no.png";
		regValid = 0;
	}
	else
	if ( (confirm == password) && (password.length > 7) ) {
		document.getElementById("confirm_validation").src = "img/yes.png";
		regValid = 1;
	}
	document.getElementById("confirm_validation").style.display = "block";
	
	//check name
	if (name.indexOf(' ') >= 0) {
		document.getElementById("name_validation").src = "img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("name_validation").src = "img/no.png";
		regValid = 0;
	}
	document.getElementById("name_validation").style.display = "block";
	
	//check email
	var emailRegEx = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
	if ((email==password) || (email.search(emailRegEx) == -1)) {
		document.getElementById("email_validation").src = "img/no.png";
		regValid = 0;
	}
	else {
		document.getElementById("email_validation").src = "img/yes.png";
		regValid = 1;
	}
	document.getElementById("email_validation").style.display = "block";
	
	//check birthday
	if (birthdate != "") {
		document.getElementById("birthdate_validation").src = "img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("birthdate_validation").src = "img/no.png";
		regValid = 0;
	}
	document.getElementById("birthdate_validation").style.display = "block";
	
	//check avatar
	var extension = avatar.split('.');
	if ( (extension[1] == "jpg") || (extension[1] == "jpeg") ) {
		document.getElementById("avatar_validation").src = "img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("avatar_validation").src = "img/no.png";
		regValid = 0;
	}
	document.getElementById("avatar_validation").style.display = "block";
	
	//alert("regvalid = " + regValid);
	if (regValid == 1) {
		document.getElementById('signup_submit').removeAttribute("disabled");
		//alert("yey");
	}
	else {
		document.getElementById('signup_submit').disabled = "disabled";
		//alert("Fill your registration form correctly");
	}
}

function signup() {
	if (regValid == 1) {
		window.location.href = "src/Dashboard.php";
	}
	else {
		alert("Fill your registration form correctly");
	}
}

function checkTaskName() {
	var taskName = document.getElementById('task_name_input').value;
	var taskNameValid = 0;
	var iChars = "~=-_^&.\\|*|,\":<>[]{}`\';()@&$#%";
	for (var i = 0; i < taskName.length; i++) {
		if (iChars.indexOf(taskName.charAt(i)) != -1){
			taskNameValid = 1; 
			break;
		}
	}
	
	if ((taskName.length > 25) || (taskNameValid == 1) || (taskName == "")) {
		//tidak valid
		document.getElementById('taskname_validation').src = "../img/no.png";
	}
	else {
		//valid
		document.getElementById('taskname_validation').src = "../img/yes.png";
	}
	document.getElementById('taskname_validation').style.display = "block";
}

function checkTaskAttachment() {
	var attachmentName = document.getElementById('attachment_file').value;
	var dot = ".";
	if (attachmentName.indexOf(dot) != -1) {
		//valid
		document.getElementById('task_attachment_validation').src = "../img/yes.png";
	}
	else {
		//not valid
		document.getElementById('task_attachment_validation').src = "../img/no.png";
	}
	document.getElementById('task_attachment_validation').style.display = "block";
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function addCatName(){
	var first = getUrlVars()["cat_name"];
	document.getElementById("cat_name").setAttribute('value',first);
}

function showSearchHint(str) { 
    document.getElementById('txtHint').style.display = "block";
    if (str.length==0) { 
        document.getElementById("txtHint").innerHTML="";
        return;
    }
    
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp=new XMLHttpRequest();
    }
    else {// code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    xmlhttp.onreadystatechange=function() {
        if (xmlhttp.readyState==4 && xmlhttp.status==200) {
            document.getElementById("txtHint").innerHTML = xmlhttp.responseText;
        }
    } 
    var selectOption = document.getElementById("search_box_filter");
    var filter = selectOption.options[selectOption.selectedIndex].value;
    
    var url="search_autocomplete.jsp?hint="+str+"&filter="+filter;
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function searchResult(resultID, resultType) {
    document.getElementById('txtHint').style.display = "none";

    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange=function() {
        if (xmlhttp.readyState==4 && xmlhttp.status==200) {
            document.getElementById("dynamic_content").innerHTML = xmlhttp.responseText;
        }
    }
    
    var s = "";    
    if (resultType == "user") {
        s = "search_result.jsp?q="+resultID+"&type=user";	
        xmlhttp.open("GET", s, true);
        xmlhttp.send();
    }
    else
    if (resultType == "category") {
        s = "search_result.jsp?q="+resultID+"&type=category";
        xmlhttp.open("GET", s, true);
        xmlhttp.send();
    }
    else
    if (resultType == "task") {
        //s= "search_result.jsp?q="+resultID+"&type=task";
        window.location.href = "task_detail.jsp?task_id="+resultID;
    }
    
}

function viewTask(taskID) {
    window.location.href = "task_detail.jsp?task_id="+taskID;
}
/*
function searchUser(userID) {	
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else {// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange=function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			document.getElementById("dynamic_content").innerHTML = xmlhttp.responseText;
		}
	}
		
	xmlhttp.open("GET","search_result.jsp?q="+userID+"&type=user",true);
	xmlhttp.send();
} */