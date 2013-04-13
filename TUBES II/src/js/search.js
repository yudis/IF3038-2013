//Tubes 2
var xmlhttp;
function loadXMLDocGet(url,cfunc)
{
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=cfunc;
xmlhttp.open("GET",url,true);
xmlhttp.send();
}
function searchByFilter()
{
loadXMLDocGet('php/search.php?searchquery='+document.getElementById('searchquery').value+
    '&filtertype='+document.getElementById('filtertype').value,function()
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