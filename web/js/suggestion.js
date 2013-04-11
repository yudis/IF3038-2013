/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function autocomplete_search(jenis, str)
{
    if(str.length == 0)
    {
        document.getElementById("hasil_ac").innerHTML="";
        return;
    }
		
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
            document.getElementById("hasil_ac").innerHTML=xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", "Search?aksi=suggest&key="+jenis+"&value="+str, true);
    xmlhttp.send();
}