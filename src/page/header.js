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

function checkHeaderValidation(){
	getAjax();
	var content = document.getElementById("search_text").value;
	var elmt = document.getElementById("modesearch").value;
	var suggestion;
	var suggestionarray;
	
	if(content!=""){
		ajaxRequest.open("GET","../php/headervalidation.php?content="+content+"&idx="+elmt,false);
		ajaxRequest.onreadystatechange = function()
		{
			suggestion = ajaxRequest.responseText;
			suggestion = suggestion.substr(0,suggestion.length-1);
			suggestionarray = suggestion.split("|");
			
			var x;
			x="<datalist id=\"searching-auto\">";
			for (var i = 0; i < suggestionarray.length; i++) {
				x += "<option value=\""+suggestionarray[i]+"\">";
			}
			x += "</datalist>";
			document.getElementById("list-search").innerHTML=x;
		}
		
		ajaxRequest.send();
	}
}
