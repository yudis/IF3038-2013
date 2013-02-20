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
	alert("ROARRRRRRRRRRR");
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
		} else
		{
			alert("Use jpg or jpeg file only!");
			return false;
		}
	}
}