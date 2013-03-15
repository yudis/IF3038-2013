function initialize()
{
	if (typeof(Storage) !== 'undefined')
	{
		if (localstorage.session)
		{
			document.getElementById("linkusername").innerHTML = localstorage.session;
			document.getElementById("linkusername").href = "profile.html";
			showFullName(localstorage.session);
			showEmail(localstorage.session);
			showDate(localstorage.session);
			showPassword(localstorage.session);
		}
		else
		{
			window.location = "index.html";
		}
	}
}

function showFullName(str)
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
			document.getElementById("nameDisplayDiv").innerHTML=xmlhttp.responseText;
			document.getElementById("fullname").value = document.getElementById("nameDisplayDiv").innerHTML;
		}
	}
	xmlhttp.open("GET","getprofile.php?type=fullname&q="+str,true);
	xmlhttp.send();
}

function showEmail(str)
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
			document.getElementById("emailDisplayDiv").innerHTML=xmlhttp.responseText;
			document.getElementById("email").value = document.getElementById("emailDisplayDiv").innerHTML;
		}
	}
	xmlhttp.open("GET","getprofile.php?type=email&q="+str,true);
	xmlhttp.send();
}

function showDate(str)
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
			document.getElementById("dateDisplayDiv").innerHTML=xmlhttp.responseText;
			document.getElementById("date").value = document.getElementById("dateDisplayDiv").innerHTML;
		}
	}
	xmlhttp.open("GET","getprofile.php?type=date&q="+str,true);
	xmlhttp.send();
}

function showPassword(str)
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
			document.getElementById("password").value=xmlhttp.responseText;
		}
	}
	xmlhttp.open("GET","getprofile.php?type=password&q="+str,true);
	xmlhttp.send();
}

function getPassword(str)
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
			$r=xmlhttp.responseText;
			return $r;
		}
	}
	xmlhttp.open("GET","getprofile.php?type=password&q="+str,true);
	xmlhttp.send();
}

function updateFullName(str, val)
{
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.open("GET","updateprofile.php?type=fullname&q="+str+"&val="+val,true);
	xmlhttp.send();
}

function updateEmail(str, val)
{
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.open("GET","updateprofile.php?type=email&q="+str+"&val="+val,true);
	xmlhttp.send();
}

function updateDate(str, val)
{
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.open("GET","updateprofile.php?type=date&q="+str+"&val="+val,true);
	xmlhttp.send();
}

function updatePassword(str, val)
{
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.open("GET","updateprofile.php?type=password&q="+str+"&val="+val,true);
	xmlhttp.send();
}

function checkChange()
{
	var flag = false;

	if (document.getElementById("nameDisplayDiv").innerHTML == document.getElementById("fullname").value)
	{
		if (document.getElementById("emailDisplayDiv").innerHTML == document.getElementById("email").value)
		{
			if (document.getElementById("dateDisplayDiv").innerHTML == document.getElementById("date").value)
			{
				if (document.getElementById("password").value == getPassword(localstorage.session))
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
	
	return flag;
}

function editProfil()
{
	document.getElementById("nameEditDiv").style.display = "inline";
	document.getElementById("emailEditDiv").style.display = "inline";
	document.getElementById("dateEditDiv").style.display = "inline";
	document.getElementById("passwordEditDiv").style.display = "inline";
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
	if (checkChange() == false)
	{
		var c = confirm("Are you sure that there is no change made?");
		if (c == true)
		{
			//ubah tampilan profile dan database
			document.getElementById("nameDisplayDiv").innerHTML = document.getElementById("fullname").value;
			document.getElementById("emailDisplayDiv").innerHTML = document.getElementById("email").value;
			document.getElementById("dateDisplayDiv").innerHTML = document.getElementById("date").value;
			updateFullName(localstorage.session, document.getElementById("fullname").value);
			updateEmail(localstorage.session, document.getElementById("email").value);
			updateDate(localstorage.session, document.getElementById("date").value);
			updatePassword(localstorage.session, document.getElementById("password").value);
		}
		else
		{
		}
	}
	else
	{
		//ubah tampilan profile dan database
		document.getElementById("nameDisplayDiv").innerHTML = document.getElementById("fullname").value;
		document.getElementById("emailDisplayDiv").innerHTML = document.getElementById("email").value;
		document.getElementById("dateDisplayDiv").innerHTML = document.getElementById("date").value;
		updateFullName(localstorage.session, document.getElementById("fullname").value);
		updateEmail(localstorage.session, document.getElementById("email").value);
		updateDate(localstorage.session, document.getElementById("date").value);
		updatePassword(localstorage.session, document.getElementById("password").value);
	}

	document.getElementById("nameEditDiv").style.display = "none";
	document.getElementById("emailEditDiv").style.display = "none";
	document.getElementById("dateEditDiv").style.display = "none";
	document.getElementById("passwordEditDiv").style.display = "none";
	document.getElementById("doneProfileButton").style.display = "none";
	document.getElementById("changeProfilePictureButton").style.display = "none";
	
	document.getElementById("nameDisplayDiv").style.display = "inline";
	document.getElementById("emailDisplayDiv").style.display = "inline";
	document.getElementById("dateDisplayDiv").style.display = "inline";
	document.getElementById("editProfileButton").style.display = "inline";
	
	return false;
}