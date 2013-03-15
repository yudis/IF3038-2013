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

function addComment(taskid){
	getAjax();
	var comment = document.getElementById("textarea-comment").value;
	
	if(comment!=""){
		ajaxRequest.open("GET","../php/updatecomment.php?comment="+comment+"&taskid="+taskid,false);
	
		ajaxRequest.onreadystatechange = function()
		{
			alert(ajaxRequest.responseText);
			document.getElementById("test2").innerHTML =  ajaxRequest.responseText;
		}
		
		ajaxRequest.send();
	}
}
