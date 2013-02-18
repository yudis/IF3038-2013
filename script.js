function validateEmail()
{
	var x=document.getElementById("form-email").value;
	var atpos=x.indexOf("@");
	var dotpos=x.lastIndexOf(".");
	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
	  {
		  document.getElementById("ValidEmail").innerHTML="Input email tidak valid";
		  return false;
	  }
	else
	 {
		 document.getElementById("ValidEmail").innerHTML="Input email valid";
		 return true;
	 }
}
function validateTaskName()
{
	var x=document.getElementById("txtbox").value;
	var i = 0;
	for(i = 0;i<x.length;i++){
		var y = x.charAt(i);
		
		if ((y.charCodeAt(0)>=48 && y.charCodeAt(0)<=57) || (y.charCodeAt(0)>=65 && y.charCodeAt(0)<=90)|| (y.charCodeAt(0)>=97 && y.charCodeAt(0)<=122)){
			document.getElementById("demo").innerHTML="Input valid";
			document.getElementById("buttonReg").disabled = false;
		}
		else
		{
			document.getElementById("demo").innerHTML="Taks name tidak boleh menggunakan karakter khusus";
			document.getElementById("buttonReg").disabled = true;
			alert("Not a valid task name");
	  			return false;
		}
		
	}
}

function validateExtentionName()
{
	var x=document.getElementById("txtbox").value;
	var i = 0;
	var y="";
	var dotpos=x.lastIndexOf(".");
	
	for(i = dotpos;i<=x.length;i++){
		y =y.concat(x.charAt(i));
		
	}
	if ((y==".jar")||(y==".avi")||(y==".mp4")||(y==".mp3")||(y==".jpg")||(y==".jpeg")||(y==".txt") ){
		
			document.getElementById("demo").innerHTML="Input valid";
			document.getElementById("buttonReg").disabled = false;
		}
		else
		{
			document.getElementById("demo").innerHTML="Input tidak valid";
			document.getElementById("buttonReg").disabled = true;
			alert("Not a valid extention name");
	  			return false;
		}
		
	
}

function validateUserName()
{
	var x=document.getElementById("form-username").value;
	var y = document.getElementById("form-password").value;
	if((x.length > 4) && (x!=y))
	{
		document.getElementById("ValidUserName").innerHTML="Input username valid";
		return true;
	}
	else
	{
		document.getElementById("ValidUserName").innerHTML="Username min. 5 karakter dan tidak boleh sama dengan password";
		return false;
	}
	
}
S
function validatePassword()
{
	var x=document.getElementById("form-username").value;
	var y = document.getElementById("form-password").value;
	var z = document.getElementById("form-email").value;
	if((y.length > 7) && (y!=x) && (y != z))
	{
		document.getElementById("ValidPassword").innerHTML="Input password valid";
		return true;
	}
	else
	{
		document.getElementById("ValidPassword").innerHTML="Password min. 8 karakter dan tidak boleh sama dengan email maupun username";
		return false;
	}
	
}

function confirmPassword()
{
	var y = document.getElementById("form-cpass").value;
	var x = document.getElementById("form-password").value;
	if(y == x)
	{
		document.getElementById("ValidCPass").innerHTML="Correct password";
		return true;
	}
	else
	{
		document.getElementById("ValidCPass").innerHTML="Incorrect confirm password";
		return false;
	}
	
}

function validateFullName()
{
	var x = document.getElementById("form-nama").value;
	var z="";
	var truefalse = 0;
	var i = 0;
	for(i = 0;i<=x.length;i++){
        z = x.charAt(i);
		if ((z == " ") && (x.charAt(i-1) != z) && (i != 0)){
			while (x.charAt(i+1) == z)
			{
				i++;
			}
			if((x.charAt(i+1) != z)&& (i+1 != x.length))
				{
					truefalse = 1;
				}
		}
	}
	
	if(truefalse != 0)
		{
			document.getElementById("ValidNama").innerHTML="Nama valid";
			return true;
		}
		else
		{
			document.getElementById("ValidNama").innerHTML="Nama lengkap minimal mengandung satu spasi di antara nama depan dan nama belakang";
			return false;
		}	
}

function validateAvatar()
{
	var x=document.getElementById("form-avatar").value;
	var i = 0;
	var y="";
	var dotpos=x.lastIndexOf(".");
	
	for(i = dotpos;i<=x.length;i++){
		y =y.concat(x.charAt(i));
		
	}
	if ((y==".jpg")||(y==".jpeg")){
		
			document.getElementById("ValidAvatar").innerHTML="Input avatar valid";
			return true;
		}
		else
		{
			document.getElementById("ValidAvatar").innerHTML="Input avatar harus .jpg atau .jpeg";
	  		return false;
		}
}

function validate()
{
	validateUserName();
	validatePassword();
	validateFullName();
	validateAvatar();
	confirmPassword();
	validateEmail();
	if(validateUserName() && validatePassword() && validateFullName() && validateAvatar() && confirmPassword() && validateEmail())
	{
		document.getElementById("buttonReg").disabled = false;
	}
	else
	{
		document.getElementById("buttonReg").disabled = true;
	}
}