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