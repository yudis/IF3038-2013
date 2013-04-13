//Tubes 2
var xmlhttp;
function loadXMLDocPost(url,parameters,cfunc)
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
xmlhttp.open("POST",url,true);
xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
xmlhttp.send(parameters);
}

function addtask() {
    var param = "requesttype=save" +
                "&tabletype=" + "taskdetails"  +
                "&namatask=" + encodeURIComponent(document.getElementById("namatask").value) +
                //"&attachment=" + document.getElementById("attachment").value +
                "&deadline=" + encodeURIComponent(document.getElementById("deadline").value) +
                "&assignee=" + encodeURIComponent(document.getElementById("assignee").value) +
                "&tag=" + encodeURIComponent(document.getElementById("tag").value);
loadXMLDocPost('Rincian',param,function() { 
    console.log(xmlhttp.readyState);
    console.log(xmlhttp.status);
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
        alert("db get");
        console.log("response get");
        console.log(xmlhttp.responseText);
        }
});
return false;
}


/*function goToTaskDetails() {
    
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
}*/