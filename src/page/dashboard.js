//Dashboard javascript
var ajaxRequest;

function getAjax() //a function to get AJAX from browser
{
	try
	{
		ajaxRequest = new XMLHttpRequest();
	}
	catch (e)
	{
		try
		{
			ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e) 
		{
			try
			{
				ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e)
			{
				alert("Can't get AJAX, browser error");
				return false;
			}
		}
	}
}

function autoCompleteAsignee(){
	getAjax();

	var asignee = document.getElementById("asignee").value;
	
	if(asignee!=""){
		ajaxRequest.open("GET","../php/autocompleteasignee.php?asignee="+document.getElementById("asignee").value,false);

		ajaxRequest.onreadystatechange = function()
		{
			document.getElementById("testt").innerHTML =  ajaxRequest.responseText;
		}
		
		ajaxRequest.send();
	}
	
	//ajaxRequest.open("GET","php/checkavailid.php?idinput="+document.getElementById("username").value,false);
	
}
