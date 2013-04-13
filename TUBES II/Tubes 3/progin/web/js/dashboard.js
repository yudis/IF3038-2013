function showCatPrompt()
{   
	var cat=prompt("Daftar Pengguna","");   
	var cat=prompt("Nama Kategori","");   
    
    if (cat!=null)  {addCategory(cat);}
    else if (cat == "") {alert("enter a category");} 
}

function addCategory(x)
{
    var toAppend = document.getElementById("cats");
    var newCat = document.createElement("p");
    newCat.id = x;
    var catTask = document.createElement("p");
    catTask.id = x + "t";
    
    var tNewCat = document.createTextNode(x);

    newCat.appendChild(tNewCat);
    newCat.appendChild(catTask);
    toAppend.appendChild(newCat);   
    
}

function showCatTask(p)
{
    var x = document.getElementById(p);
    x.style.backgroundColor="#D94A38";
    x.innerHTML = "let's bang!";
}

function nShowCatTask(p)
{
    var x = document.getElementById(p);
    x.innerHTML = "don't bang!";
}//do nothing

//Tubes 2
//var xmlhttp;
//function loadXMLDocGet(url,cfunc)
//{
//if (window.XMLHttpRequest)
//  {// code for IE7+, Firefox, Chrome, Opera, Safari
//  xmlhttp=new XMLHttpRequest();
//  }
//else
//  {// code for IE6, IE5
//  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
//  }
//xmlhttp.onreadystatechange=cfunc;
//xmlhttp.open("GET",url,true);
//xmlhttp.send();
//}
//function searchByFilter()
//{
//loadXMLDocGet('php/search.php?searchquery='+document.getElementById('searchquery').value+
//    '&filtertype='+document.getElementById('filtertype').value,function()
//  { 
//  if (xmlhttp.readyState==4 && xmlhttp.status==200)
//    {
//    console.log("response get");
//    console.log(xmlhttp.responseText);
//    document.getElementById("contentdashboard").innerHTML=xmlhttp.responseText;
//    }
//    
//  });
//return false;
//}
