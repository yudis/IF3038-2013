/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
<!--AJAX untuk show kategori-->
function showKategori(uid){
    document.getElementById("category").innerHTML="";
    if(window.XMLHttpRequest)
    {
        // untuk IE7, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {
        //untuk IE jadul
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
				
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("category").innerHTML=xmlhttp.responseText;		
        }
    }
    xmlhttp.open("GET", "Kategori?aksi=lihat&uid="+uid, true);
    xmlhttp.send();
}

<!--JS UNTUK SHOW/HIDE TOMBOL DELETE KATEGORI-->
		
var isShown = false;
function show_del_cat()
{
    var elements = document.getElementsByClassName("tombol_hapus_kategori");
    if(isShown)
    {
        for(var i = 0; i < elements.length; i++)
        {
            elements[i].style.display = "none";
        }
        isShown = false;
    }
    else{
        for(var i = 0; i < elements.length; i++)
        {
            elements[i].style.display = "block";
        }
        isShown = true;
    }
}

