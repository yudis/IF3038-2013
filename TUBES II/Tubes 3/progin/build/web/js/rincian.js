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

function loadRincian() {
    alert($idtask);
}

function onload() {
}

function loadTaskDetails()
{
    var parameters = "requesttype=load";
    loadXMLDocPost('Rincian',parameters,
        function()
        { 
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
            console.log("response get");
            console.log(xmlhttp.responseText);
            document.getElementById("rincian").innerHTML=xmlhttp.responseText;
            }
        });
    return false;
}

function loadComment(currpage, idtask)
{
var filtertype=encodeURIComponent(document.getElementById("filtertype").value);
var searchquery=encodeURIComponent(document.getElementById("searchquery").value);
var parameters = "requesttype=save"+"&searchquery="+searchquery;    
loadXMLDocGet('php/pagination.php?page='+currpage+
    '&t='+idtask,function()
  { 
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    console.log("response get");
    console.log(xmlhttp.responseText);
    document.getElementById("commentpage").innerHTML=xmlhttp.responseText;
    }
    
  });
return false;
}

function saveComment() {
    var param = "?idtask=" + document.getElementById("idtask").value +
                    "&tabletype=" + "komentar"  +
                    "&commentarea=" + document.getElementById("commentarea").value;
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

function saveTaskDetails() {
    var param = "requesttype=save" +
                "&tabletype=" + "taskdetails"  +
                "&namatask=" + encodeURIComponent(document.getElementById("namatask").value) +
                //"&attachment=" + document.getElementById("attachment").value +
                "&deadline=" + encodeURIComponent(document.getElementById("deadline").value) +
                "&listassignee=" + encodeURIComponent(document.getElementById("listassignee").value) +
                "&listtag=" + encodeURIComponent(document.getElementById("listtag").value);
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

