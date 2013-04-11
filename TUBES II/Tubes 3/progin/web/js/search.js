//Tubes 2
var xmlhttp;
function loadXMLDocPost(url,cfunc)
{
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
var filtertype=encodeURIComponent(document.getElementById("filtertype").value);
var searchquery=encodeURIComponent(document.getElementById("searchquery").value);
var parameters = "filtertype="+filtertype+"&searchquery="+searchquery;
xmlhttp.onreadystatechange=cfunc;
xmlhttp.open("POST",url,true);
xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
xmlhttp.send(parameters);
}
function searchByFilter()
{
loadXMLDocPost('Search',function()
  { 
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    console.log("response get");
    console.log(xmlhttp.responseText);
    document.getElementById("contentdashboard").innerHTML=xmlhttp.responseText;
    }
    
  });
return false;
}

function goToTaskDetails() {
    
loadXMLDocGet('rincian.php',function()
  { 
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    console.log("response get");
    console.log(xmlhttp.responseText);
    document.getElementById("contentdashboard").innerHTML=xmlhttp.responseText;
    }
    
  });
return false;
}