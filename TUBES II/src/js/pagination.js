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

function loadComment(currpage,idtask)
{
    alert(idtask);
//loadXMLDocGet('php/pagination.php?page='+currpage+
//    '&t='+idtask,function()
//  { 
//  if (xmlhttp.readyState==4 && xmlhttp.status==200)
//    {
//    console.log("response get");
//    console.log(xmlhttp.responseText);
//    document.getElementById("commentpage").innerHTML=xmlhttp.responseText;
//    }
//    
//  });
return false;
}