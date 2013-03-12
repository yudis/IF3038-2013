var num=1
img1 = new Image ()
img1.src = "images/image-1.jpg"
img2 = new Image ()
img2.src = "images/image-2.jpg"
img3 = new Image ()
img3.src = "images/image-3.jpg"

var isValidUname = false, isValidPassword = false, isValidRePassword = false,
isValidFullName = false, isValidEmail = false, isValidBday = false,
isValidPhoto = false;

function next()
{
    num = num + 1
    if (num == 4) {
        num = 1
    }
    document.getElementById("slider_picture").src=eval("img"+num+".src")
}

function back()
{
    num = num - 1
    if (num == 0) {
        num = 3
    }
    document.getElementById("slider_picture").src=eval("img"+num+".src")
}

function changeRegister() {
    var registerBtn = document.getElementById("register");
    if (isValidUname && isValidPassword && isValidRePassword && isValidFullName && isValidEmail && isValidBday && isValidPhoto) {
        registerBtn.disabled = false;
    } else {
        registerBtn.disabled = true;
    }
}

function validateUName() {
    var uname = document.getElementById("uname");
    var regex = /^[\w]{5,}$/g;
    
    isValidUname = true;
    if (!regex.test(uname.value)) {
        isValidUname = false;
    }
    
    var pwd = document.getElementById("pwd");
    if (uname.value == pwd.value) {
        isValidUname = false;
    }
    
    if (isValidUname) {
        uname.style.border = '2px #5fae53 solid';
    } else {
        uname.style.border = '2px red solid';
    }
    
    changeRegister();
}

function validatePassword() {
    var pwd = document.getElementById("pwd");
    var regex = /^[\w\W]{8,}$/g;
    
    isValidPassword = true;
    if (!regex.test(pwd.value)) {
        isValidPassword = false;
    }
    
    var uname = document.getElementById("uname");
    var email = document.getElementById("email");
    if (uname.value == pwd.value || email.value == pwd.value) {
        isValidPassword = false;
    }
    
    if (isValidPassword) {
        pwd.style.border = '2px #5fae53 solid';
    } else {
        pwd.style.border = '2px red solid';
    }
    
    changeRegister();
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
    
    changeRegister();
}


function validateFullName() {
    var name = document.getElementById("name");
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
    
    changeRegister();
}

function validateEmail() {
    var email = document.getElementById("email");
    var regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    
    isValidEmail = true;
    if (!regex.test(email.value)) {
        isValidEmail = false;
    }
    
    var pwd = document.getElementById("pwd");
    if (email.value == pwd.value) {
        isValidEmail = false;
    }
    
    if (isValidEmail) {
        email.style.border = '2px #5fae53 solid';
    } else {
        email.style.border = '2px red solid';
    }
    
    changeRegister();
}


function validateBday() {
    var bday = document.getElementById("bday");
    var regex = /^[0-9]{1,2}-[0-9]{1,2}-[0-9]{4,4}$/g;
    
    isValidBday = true;
    if (!regex.test(bday.value)) {
        isValidBday = false;
    }
    
    if (isValidBday) {
        if (bday.value.substr(bday.value.length - 4) < 1955) {
            isValidBday = false;
        }
    }
    
    if (isValidBday) {
        bday.style.border = '2px #5fae53 solid';
    } else {
        bday.style.border = '2px red solid';
    }
    
    changeRegister();
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
	
    changeRegister();
}

function bdayPicker() {
    NewCal('bday', 'ddmmyyyy');
    return false;
}

function validateLogin() 
{
	var xUsername=document.forms["login"]["uname"].value;
	var xPassword=document.forms["login"]["pwd"].value;
	if (xUsername == "admin" && xPassword == "adminpwd") {
		return true;
	} else {
		alert("Username and password combination doesn't match or couldn't be found");
		return false;
	}
}

function validateForm()
{
    var xUsername=document.forms["registration"]["uname"].value;
    if (xUsername.length < 5)
    {
        alert("User name must be filled out with at least 5 characters.");
        return false;
    }
    var xPassword=document.forms["registration"]["pwd"].value;
    if ((xPassword.length < 8) || (xPassword == xUsername))
    {
        alert("Password must be filled out with at least 8 characters and must be different with username.");
        return false;
    }
    var xPasswordConfirm=document.forms["registration"]["pwd_confirm"].value;
    if (xPasswordConfirm != xPassword)
    {
        alert("Your password confirmation doesn't match.");
        return false;
    }
    var xName=document.forms["registration"]["name"].value;
    var flag = 0;
    var i=0;
    while (i<xName.length && (flag < 3)) { 
        if ((xName.toString().charAt(i) != ' ') && (flag == 0))
            flag = 1;
        else if ((xName.toString().charAt(i) == ' ') && (flag == 1))
            flag = 2;
        else if ((xName.toString().charAt(i) != ' ') && (flag == 2))
            flag = 3;
        i++;
    }
    if ((flag < 3) || (xName.length < 3))
    {
        alert("Name must contain at least two words.");
        return false;
    }
    var xEmail=document.forms["registration"]["email"].value;
    var atpos=xEmail.indexOf("@");
    var dotpos=xEmail.lastIndexOf(".");
    if (xEmail == xPassword) {
        alert("Password and email must be different.");
        return false;
    }
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=xEmail.length)
    {
        alert("Not a valid e-mail address.");
        return false;
    }
    //var xDate=document.forms["registration"]["bday"].value.;
    //var n = xDate.split("-");
    //var date = parseInt(n[0]);
    //if (date < 1955) {
    //	alert(date);
    //	return false;
    //}
    var xAvatar=document.forms["registration"]["ava"].value;
    var str1=/\.jpg/g;
    var str2=/\.jpeg/g;
	
    var result=str1.test(xAvatar);
    if (result==1)
    {
        alert("Registration success!");
        return true;
    } else {
        result=str2.test(xAvatar);
        if (result==1) {
            alert("Registration success!");
            return true;
        } else {
            alert("Use jpg or jpeg file only!");
            return false;
        }
    }
}

function register() {
    window.location.replace("dashboard.html");
}