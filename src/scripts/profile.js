

function saveProfile() {
    document.getElementById("nameEditDiv").style.display = "none";
	document.getElementById("birthEditDiv").style.display = "none";
	document.getElementById("passEditLabel").style.display = "none";
	document.getElementById("passEditDetail").style.display = "none";
	document.getElementById("repassEditLabel").style.display = "none";
	document.getElementById("repassEditDetail").style.display = "none";
	document.getElementById("changeAvatarLabel").style.display = "none";
	document.getElementById("changeAvatarDetail").style.display = "none";
    document.getElementById("doneButton").style.display = "none";
    document.getElementById("nameDisplayDiv").style.display = "block";
    document.getElementById("birthDisplayDiv").style.display = "block";
	document.getElementById("editButton").style.display = "block";
    
    var birthDisplay = document.getElementById("birthDisplayDiv");
    var birthTextBox = document.getElementById("deadline");
    birthDisplay.innerHTML = birthTextBox.value;
    
    saveTags();
    
    return false;
}

function editProfile() {
    document.getElementById("nameEditDiv").style.display = "block";
	document.getElementById("birthEditDiv").style.display = "block";
	document.getElementById("passEditLabel").style.display = "block";
	document.getElementById("passEditDetail").style.display = "block";
	document.getElementById("repassEditLabel").style.display = "block";
	document.getElementById("repassEditDetail").style.display = "block";	
	document.getElementById("changeAvatarLabel").style.display = "block";
	document.getElementById("changeAvatarDetail").style.display = "block";
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
    
    //changeRegister();
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
    
    //changeRegister();
}

function validatePassword() {
    var pwd = document.getElementById("pwd");
    var regex = /^[\w\W]{8,}$/g;
    
    isValidPassword = true;
    if (!regex.test(pwd.value)) {
        isValidPassword = false;
    }
    
    // var uname = document.getElementById("uname");
    // var email = document.getElementById("email");
    // if (uname.value == pwd.value || email.value == pwd.value) {
        // isValidPassword = false;
    // }
    
    if (isValidPassword) {
        pwd.style.border = '2px #5fae53 solid';
    } else {
        pwd.style.border = '2px red solid';
    }
    
    //changeRegister();
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
    
    //changeRegister();
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
	
    //changeRegister();
}


	ajax_get("ajax/postprofile.php?f=1",function(xhr)
	{
			document.getElementById("profileContent").innerHTML=xhr.responseText;
	});
