//Tubes 2

function loadRincian() {
    alert($idtask);
}

function onload() {
}

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

function saveComment() {
    var param = "?idtask=" + document.getElementById("idtask").value +
                    "&tabletype=" + "komentar"  +
                    "&commentarea=" + document.getElementById("commentarea").value;
alert(param);
loadXMLDocGet('php/db.php'+param,function() { 
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

