function clearSuggestion(id){	$("id", id).innerHTML = "";}function getValues(type){	var value = $("id", type).value;			var xmlhttp = createAJAX();		xmlhttp.onreadystatechange = function()	{		if(xmlhttp.readyState == 4 && xmlhttp.status == 200)		{			$("id", type + "suggestion").innerHTML = xmlhttp.responseText;		}	}		var parameters = "type=" + type + "&value=" + value;	postAJAX(xmlhttp, "getvalues.php", parameters);}function submitNewCategory(){	var category = $("id", "category").value;	var user = $("id", "users").value;		/*	if (category == "" || user == "")	{		alert("Empty data detected.");		return;	}	*/	var xmlhttp = createAJAX();		xmlhttp.onreadystatechange = function()	{		if(xmlhttp.readyState == 4 && xmlhttp.status == 200)		{			alert(xmlhttp.responseText);			alert('Category successfully added!');			window.location = "../dashboard.php";		}	}		var parameters = "category=" + category + "&user=" + user;	postAJAX(xmlhttp, "addcatsubmit.php", parameters);}