var before;
var after;


function saveProfile() {
	var str = "";
	if (document.getElementById("oldpass").value != ""){
		if (document.getElementById("Oldpwd").value != calcMD5(document.getElementById("oldpass").value)){
			alert("Old Password different from Current Password");
			return false;
		} else if (document.getElementById("pwd").value == ""){
			alert("New Password is blank");
			return false;
		} else if (document.getElementById("pwd_confirm").value == ""){
			alert("rePassword is blank");
			return false;
		} else if (document.getElementById("pwd_confirm").value != document.getElementById("pwd").value){
			alert("New Password is different from rePassword");
			return false;
		}
	}
	
	after = new Array();
	after[0] = document.getElementById("fname").value;
	after[1] = document.getElementById("Bday").value;
	after[2] = document.getElementById("pwd_confirm").value;
	
	
	if (after[0] == before[0]){
		str += "- Full name did not changed \n";
	}
	if (after[1] == before[1]){
		str += "- Birthday did not changed \n";
	}
	if (after[2] == before[2]){
		str += "- Password did not changed \n";
	}

	if (str != ""){
		alert(str);
	}
	
    document.getElementById("nameEditDiv").style.display = "none";
	document.getElementById("birthEditDiv").style.display = "none";
	document.getElementById("passEditLabel").style.display = "none";
	document.getElementById("passEditDetail").style.display = "none";
	document.getElementById("repassEditDetail").style.display = "none";
	document.getElementById("changeAvatarLabel").style.display = "none";
	document.getElementById("changeAvatarDetail").style.display = "none";
	document.getElementById("oldpassEditDetail").style.display = "none";
	document.getElementById("oldpassEditLabel").style.display = "none";
    document.getElementById("doneButton").style.display = "none";
    document.getElementById("nameDisplayDiv").style.display = "block";
    document.getElementById("birthDisplayDiv").style.display = "block";
	document.getElementById("editButton").style.display = "block";
    var birthDisplay = document.getElementById("birthDay");
    var birthTextBox = document.getElementById("Bday");
    birthDisplay.innerHTML = birthTextBox.value;
    var nameDisplay = document.getElementById("Fullnama");
    var nameTextBox = document.getElementById("fname");
    nameDisplay.innerHTML = nameTextBox.value;
	
	// document.getElementById("oldpass").value = "";
	// document.getElementById("pwd").value = "";
	// document.getElementById("pwd_confirm").value = "";
}

function editProfile() {
	before = new Array();
	before[0] = document.getElementById("Fullnama").innerHTML;
	before[1] = document.getElementById("birthDay").innerHTML;
	before[2] = document.getElementById("oldpass").value;

    document.getElementById("nameEditDiv").style.display = "block";
	document.getElementById("birthEditDiv").style.display = "block";
	document.getElementById("passEditLabel").style.display = "block";
	document.getElementById("passEditDetail").style.display = "block";
	document.getElementById("repassEditDetail").style.display = "block";	
	document.getElementById("changeAvatarLabel").style.display = "block";
	document.getElementById("changeAvatarDetail").style.display = "block";
	document.getElementById("oldpassEditDetail").style.display = "block";
	document.getElementById("oldpassEditLabel").style.display = "block";
    document.getElementById("doneButton").style.display = "block";
    document.getElementById("nameDisplayDiv").style.display = "none";
	document.getElementById("birthDisplayDiv").style.display = "none";
    document.getElementById("editButton").style.display = "none";
    
    return false;
}

function validateFullName() {
    var name = document.getElementById("fname");
    var regex = /^[\w]+ [\w ]+$/g;
    
    isValidFullName = true;
    if (!regex.test(name.value)) {
        isValidFullName = false;
    }
    
    if (isValidFullName) {
        name.style.border = '2px #5fae53 solid';
    } else {
        name.style.border = '2px red solid';
    }
    
}

function validateBday() {
    var bday = document.getElementById("bday");
    var regex = /^[0-9]{4,4}-[0-9]{1,2}-[0-9]{1,2}$/g;
    
    isValidBday = true;
    if (!regex.test(bday.value)) {
        isValidBday = false;
    }
    
    if (isValidBday) {
        if (bday.value.substr(0, 4) < 1955) {
            isValidBday = false;
        }
    }
    
    if (isValidBday) {
        bday.style.border = '2px #5fae53 solid';
    } else {
        bday.style.border = '2px red solid';
    }
    
}

function validatePassword() {
    var pwd = document.getElementById("pwd");
    var regex = /^[\w\W]{8,}$/g;
    
    isValidPassword = true;
    if (!regex.test(pwd.value)) {
        isValidPassword = false;
    }
    
    var uname = document.getElementById("usernameDisplayDiv");
    var email = document.getElementById("emailValue");
    if (uname.innerHTML == pwd.value || email.innerHTML == pwd.value) {
        isValidPassword = false;
    }
    
    if (isValidPassword) {
        pwd.style.border = '2px #5fae53 solid';
    } else {
        pwd.style.border = '2px red solid';
    }
    
}

function validateOldPassword() {
    var oldpass = document.getElementById("oldpass");
    var regex = /^[\w\W]{8,}$/g;
    
    isValidPassword = true;
    if (!regex.test(oldpass.value)) {
        isValidPassword = false;
    }
    
    var uname = document.getElementById("usernameDisplayDiv");
    var email = document.getElementById("emailValue");
    if (uname.innerHTML == oldpass.value || email.innerHTML == oldpass.value) {
        isValidPassword = false;
    }
    
    if (isValidPassword) {
        oldpass.style.border = '2px #5fae53 solid';
    } else {
        oldpass.style.border = '2px red solid';
    }
    
}

function validateRePassword() {
    var pwd = document.getElementById("pwd");
    var pwd_confirm = document.getElementById("pwd_confirm");
    
    isValidRePassword= true;
    if (pwd.value != pwd_confirm.value) {
        isValidRePassword = false;
    }
    
    if (isValidRePassword) {
        pwd_confirm.style.border = '2px #5fae53 solid';
    } else {
        pwd_confirm.style.border = '2px red solid';
    }
    
}




function validateAvatar() {
    var xAvatar = document.getElementById("ava");
    var str1=/\.jpg/g;
    var str2=/\.jpeg/g;
    
    isValidPhoto = true;

    if (!str1.test(xAvatar.value) && !str2.test(xAvatar.value)) {
        isValidPhoto = false;
    }
    
    if (isValidPhoto) {
        xAvatar.style.border = '2px #5fae53 solid';
    } else {
        xAvatar.style.border = '2px red solid';
    }
	
}
	
	ajax_get("ajax/progilTugas.php?username=" + getQueryParameter("username"),function(xhr)
	{
			document.getElementById("tasklist").innerHTML=xhr.responseText;
	});	
