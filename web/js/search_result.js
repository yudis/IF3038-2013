/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function showResult(key, value){
    document.getElementById("hasil").innerHTML="";
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
            document.getElementById("hasil").innerHTML=xmlhttp.responseText;		
        }
    }
    xmlhttp.open("GET", "Search?aksi=cari&key="+key+"&value="+value, true);
    xmlhttp.send();
}

var angka = 0;
var tambahan = window.screen.availHeight - 200;
window.onscroll = function(ev) {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight + tambahan) {
        angka++;
        document.getElementById("judul_search").innerHTML = angka;
        tambahan+=window.screen.availHeight - 200;
        alert(tambahan);
    }
    else{
        
    }
}

