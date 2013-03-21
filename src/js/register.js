/*----- Bagian Register ----*/
var register_form = document.getElementById("register_form");

var register_username = document.getElementById("register_username");
var register_password = document.getElementById("register_password");
var register_confirm_password = document.getElementById("register_confirm_password");
var register_email = document.getElementById("register_email");
var register_avatar = document.getElementById("register_avatar");

// added client side verification
register_password.onkeyup = function()
{
	document.getElementById("register_confirm_password").pattern = this.value;
}

register_form.onsubmit = function()
{		
	if ((register_password.value==register_confirm_password.value) && (register_username.value!=register_password.value) &&
		(register_email.value!=register_password.value))
	{
		var status = false;
		var data = serialize(register_form);
		var request = new ajaxRequest();
		request.onreadystatechange = function()
		{
			if (request.readyState==4)
			{
				if (request.status==200 || window.location.href.indexOf("http")==-1)
				{
					var result = JSON.parse(request.responseText);
					console.log(result);
					if (result.status == "success")
					{
						status = true;
					}
					else
					{
						for (var temp in result.error)
						{
							alert(result.error[temp]);
						}
					}
				}
				else
				{
					alert("Mohon maaf, sedang ada kesalahan pada server.");
				}
			}
		}
		request.open("POST", "api/register_check", false);
		request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		request.send(data);
		
		return status;
	}
	else if (register_password.value!=register_confirm_password.value)
	{
		alert("Konfirmasi sandi harus sama dengan sandi");
		return false;
	}
	else if (register_email.value==register_password.value)
	{
		alert("Alamat email tidak boleh sama dengan sandi");
		return false;
	}
	else if (register_username.value==register_password.value)
	{
		alert("Username tidak boleh sama dengan sandi");
		return false;
	}	
}