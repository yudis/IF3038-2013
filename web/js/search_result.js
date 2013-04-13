/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var _key;
var _value;
var _more = 0;

function showResult(key, value){
    _key = key;
    _value = value;
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

function moreResult(key, value, more){
    _more = more + 10;

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
            document.getElementById("hasil").innerHTML+= responseText;
        }
    }
    xmlhttp.open("GET", "Search?aksi=more&key="+key+"&value="+value+"&limit1="+more-10+"&limit2="+more, true);
    xmlhttp.send();
}

var angka = 0;
var tambahan = window.screen.availHeight - 100;
var asd = false;
window.onscroll = scroll;
function scroll(){
    
        if(window.innerHeight + document.body.scrollTop  >= document.body.offsetHeight){
            
            asd = true;
            moreResult(_key, _value, _more);
            asd = false;
        }
    
    

}
