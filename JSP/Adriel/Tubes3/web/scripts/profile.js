var isValidPassword = true;
var isValidRePassword = true;
var isValidFullName = true; 
var isValidEmail = true; 
var isValidBirthday = true;

function initialize()
{
	if (typeof(Storage) !== 'undefined')
	{
                localStorage.session = "Adriel";
		if (localStorage.session)
		{
			document.getElementById("linkusername").innerHTML = localStorage.session;
			document.getElementById("linkusername").href = "profile.jsp";
			showFullName(localStorage.session);
			showEmail(localStorage.session);
			showDate(localStorage.session);
			showPassword(localStorage.session);
			showRePassword(localStorage.session);
                        loadActivities();
			
			document.getElementById("cppname").value = localStorage.session;
			document.getElementById("pp").src = "avatar/" + localStorage.session + ".jpg";
			//document.getElementById("dashboardlink").innerHTML = "<a href='dashboard.jsp?uname="+localStorage.session+"&cat=all'>Dashboard</a>";
			var innerhtml = "<a href='profile.jsp'><img src='avatar/"+localStorage.session+".jpg' alt='Profile page' width='50' height='50'><br/>Hi, "+localStorage.session+"!</a>";
	
			//document.getElementById("profil").innerHTML = innerhtml;
		}
		else
		{
			//window.location = "index.jsp";
		}
	}
}

function loadActivities()
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
	
	username = localStorage.session;
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState===4 && xmlhttp.status===200)
		{
			document.getElementById("activitylist").innerHTML = xmlhttp.responseText;
		}
	};
	xmlhttp.open("GET","getactivity?user="+username,true);
	xmlhttp.send();
}

function showFullName(str)
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
		if (xmlhttp.readyState===4 && xmlhttp.status===200)
		{
			document.getElementById("nameDisplayDiv").innerHTML=xmlhttp.responseText;
			document.getElementById("fullname").value = document.getElementById("nameDisplayDiv").innerHTML;
		}
	};
	xmlhttp.open("GET","getprofilename?q="+str,true);
	xmlhttp.send();
}

function showEmail(str)
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
		if (xmlhttp.readyState===4 && xmlhttp.status===200)
		{
			document.getElementById("emailDisplayDiv").innerHTML=xmlhttp.responseText;
			document.getElementById("email").value = document.getElementById("emailDisplayDiv").innerHTML;
		}
	};
	xmlhttp.open("GET","getprofileemail?q="+str,true);
	xmlhttp.send();
}

function showDate(str)
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
		if (xmlhttp.readyState===4 && xmlhttp.status===200)
		{
			document.getElementById("dateDisplayDiv").innerHTML=xmlhttp.responseText;
			document.getElementById("date").value = document.getElementById("dateDisplayDiv").innerHTML;
		}
	};
	xmlhttp.open("GET","getprofilebirthday?q="+str,true);
	xmlhttp.send();
}

function showPassword(str)
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
		if (xmlhttp.readyState===4 && xmlhttp.status===200)
		{
			document.getElementById("passwordDisplayDiv").innerHTML=xmlhttp.responseText;
			document.getElementById("password").value = document.getElementById("passwordDisplayDiv").innerHTML;
                        document.getElementById("repasswordDisplayDiv").innerHTML=xmlhttp.responseText;
			document.getElementById("repassword").value = xmlhttp.responseText;
		}
	};
	xmlhttp.open("GET","getprofilepassword?q="+str,true);
	xmlhttp.send();
}

function showRePassword(str)
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
		if (xmlhttp.readyState===4 && xmlhttp.status===200)
		{
			document.getElementById("repasswordDisplayDiv").innerHTML=xmlhttp.responseText;
			document.getElementById("repassword").value = document.getElementById("repasswordDisplayDiv").innerHTML;
		}
	};
	xmlhttp.open("GET","getprofilepassword?q="+str,true);
	xmlhttp.send();
}

function getPassword(str)
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
		if (xmlhttp.readyState===4 && xmlhttp.status===200)
		{
			$r=xmlhttp.responseText;
			return $r;
		}
	};
	xmlhttp.open("GET","getprofile?type=password&q="+str,true);
	xmlhttp.send();
}

function updateFullName(str, val)
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
		if (xmlhttp.readyState===4 && xmlhttp.status===200)
		{
		}
	};
	
	xmlhttp.open("GET","updateprofile?type=fullname&q="+str+"&val="+val,true);
	xmlhttp.send();
}

function updateEmail(str, val)
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

	xmlhttp.open("GET","updateprofile?type=email&q="+str+"&val="+val,true);
	xmlhttp.send();
}

function updateDate(str, val)
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

	xmlhttp.open("GET","updateprofile?type=date&q="+str+"&val="+val,true);
	xmlhttp.send();
}

function updatePassword(str, val)
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

	xmlhttp.open("GET","updateprofile?type=password&q="+str+"&val="+val,true);
	xmlhttp.send();
}

function changeDoneButton() {
    var doneButton = document.getElementById("doneProfileButton");
    if (isValidPassword && isValidFullName && isValidEmail && isValidBirthday) {
        doneButton.enabled = true;
    } else {
        doneButton.enabled = false;
    }
}

function validateFullName() {
    var fullname = document.getElementById("fullname");
    var regex = /^[\w]+ [\w ]+$/g;
    
    isValidFullName = true;
    if (!regex.test(fullname.value)) {
        isValidFullName = false;
    }
    
    if (isValidFullName) {
        fullname.style.border = '2px #5fae53 solid';
    } else {
        fullname.style.border = '2px red solid';
    }
    
    changeDoneButton();
}

function validateEmail() {
    var email = document.getElementById("email");
    var regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    
    isValidEmail = true;
    if (!regex.test(email.value)) {
        isValidEmail = false;
    }
    
    var password = document.getElementById("password");
    if (email.value === password.value) {
        isValidEmail = false;
    }
    
    if (isValidEmail) {
        email.style.border = '2px #5fae53 solid';
    } else {
        email.style.border = '2px red solid';
    }
    
    changeDoneButton();
}

function validateBirthday() {
    var birthday = document.getElementById("date");
    var regex = /^(1|2)(0|9)[0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
    
    isValidBirthday = true;
    if (!regex.test(birthday.value)) {
        isValidBirthday = false;
    }
    
    if (isValidBirthday) {
        birthday.style.border = '2px #5fae53 solid';
    } else {
        birthday.style.border = '2px red solid';
    }
    
    changeDoneButton();
}

function validatePassword() {
    var password = document.getElementById("password");
    var regex = /^[\w\W]{8,}$/g;
    
    isValidPassword = true;
    if (!regex.test(password.value)) {
        isValidPassword = false;
    }
    
    var username = document.getElementById("linkusername").innerHTML;
    var email = document.getElementById("email");
    if (username === password.value || email.value === password.value) {
        isValidPassword = false;
    }
    
    if (isValidPassword) {
        password.style.border = '2px #5fae53 solid';
    } else {
        password.style.border = '2px red solid';
    }
    
    changeDoneButton();
}

function validateRePassword() {
    var password = document.getElementById("password");
    var repassword = document.getElementById("repassword");
    
    isValidRePassword = true;
    if (password.value !== repassword.value) {
        isValidRePassword = false;
    }
    
    if (isValidRePassword) {
        repassword.style.border = '2px #5fae53 solid';
    } else {
        repassword.style.border = '2px red solid';
    }
    
    changeRegister();
}

function checkChange()
{
	var flag = false;

	if (document.getElementById("nameDisplayDiv").innerHTML === document.getElementById("fullname").value)
	{
		if (document.getElementById("emailDisplayDiv").innerHTML === document.getElementById("email").value)
		{
			if (document.getElementById("dateDisplayDiv").innerHTML === document.getElementById("date").value)
			{
				if (document.getElementById("password").value === getPassword(localStorage.session))
				{
					if (document.getElementById("repassword").value === getPassword(localStorage.session))
					{
						
					}
					else
					{
						flag = true;
					}
				}
				else
				{
					flag = true;
				}
			}
			else
			{
				flag = true;
			}
		}
		else
		{
			flag = true;
		}
	}
	else
	{
		flag = true;
	}
	
	return flag;
}

function editProfil()
{
	document.getElementById("nameEditDiv").style.display = "inline";
	document.getElementById("emailEditDiv").style.display = "inline";
	document.getElementById("dateEditDiv").style.display = "inline";
	document.getElementById("passwordEditDiv").style.display = "inline";
	document.getElementById("repasswordEditDiv").style.display = "inline";
	document.getElementById("doneProfileButton").style.display = "inline";
	document.getElementById("changeProfilePictureButton").style.display = "inline";
	
	document.getElementById("nameDisplayDiv").style.display = "none";
	document.getElementById("emailDisplayDiv").style.display = "none";
	document.getElementById("dateDisplayDiv").style.display = "none";
	document.getElementById("editProfileButton").style.display = "none";
	
	return false;
}

function doneProfil()
{
	var doneediting = false;
	if (checkChange() === false)
	{
		var c = confirm("Are you sure that there is no change made?");
		if (c === true)
		{
			doneediting = true;
		}
	}
	else
	{
		//ubah tampilan profile dan database
		document.getElementById("nameDisplayDiv").innerHTML = document.getElementById("fullname").value;
		document.getElementById("emailDisplayDiv").innerHTML = document.getElementById("email").value;
		document.getElementById("dateDisplayDiv").innerHTML = document.getElementById("date").value;
		document.getElementById("passwordDisplayDiv").innerHTML = document.getElementById("password").value;
		document.getElementById("repasswordDisplayDiv").innerHTML = document.getElementById("repassword").value;
		updateFullName(localStorage.session, document.getElementById("fullname").value);
		updateEmail(localStorage.session, document.getElementById("email").value);
		updateDate(localStorage.session, document.getElementById("date").value);
		updatePassword(localStorage.session, document.getElementById("password").value);
		
		doneediting = true;
	}

	if (doneediting === true)
	{
		document.getElementById("nameEditDiv").style.display = "none";
		document.getElementById("emailEditDiv").style.display = "none";
		document.getElementById("dateEditDiv").style.display = "none";
		document.getElementById("passwordEditDiv").style.display = "none";
		document.getElementById("repasswordEditDiv").style.display = "none";
		document.getElementById("doneProfileButton").style.display = "none";
		document.getElementById("changeProfilePictureButton").style.display = "none";
		
		document.getElementById("nameDisplayDiv").style.display = "inline";
		document.getElementById("emailDisplayDiv").style.display = "inline";
		document.getElementById("dateDisplayDiv").style.display = "inline";
		document.getElementById("editProfileButton").style.display = "inline";
	}
	
	return false;
}

function changePP()
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
	
	var ppdest = "avatar/" + localStorage.session + ".jpg";
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState===4 && xmlhttp.status===200)
		{
			document.getElementById("pp").src = xmlhttp.responseText;
		}
	};
	
	xmlhttp.open("GET","changeprofilepicture?dest="+ppdest,true);
	xmlhttp.send();
}