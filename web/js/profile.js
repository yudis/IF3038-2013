/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function pass_validatingprof()
{
    var userid = document.registration.username.value;
    var userpass = document.registration.password.value;
    var usermail = document.registration.email.value;
    var confpass = document.registration.confirmpass.value;
					
    if((userpass != userid) && (userpass.length >= "8") && (userpass != usermail))
    {
        if(userpass != confpass)
        {
            document.getElementById("conficon").src="images/canceled.png";
        }
        document.getElementById("passicon").src="images/centang.png";
    }
    else
    {
        document.getElementById("passicon").src="images/canceled.png";
    }
    loginggprof();
						
}

			
function conf_validatingprof()
{
    var userpass = document.registration.password.value;
    var confpass = document.registration.confirmpass.value;
					
    if(confpass == userpass)
    {
        document.getElementById("conficon").src="images/centang.png";
    }
    else
    {
        document.getElementById("conficon").src="images/canceled.png";
    }
    loginggprof();
}
			
function nama_validatingprof()
{
    var name = document.registration.namaleng.value;
					
    if(name.match(/([a-zA-Z])+([ \t\r\n\v\f])+([a-zA-Z])/))
    {
        document.getElementById("nameicon").src="images/centang.png";
    }
    else
    {
        document.getElementById("nameicon").src="images/canceled.png";
    }
    loginggprof();
}
				
function date_validatingprof()
{
    var date = document.registration.tanggal.value;
					
    if(date.match(/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/))
    {
        document.getElementById("dateicon").src="images/centang.png";
							
    }
    else
    {
        document.getElementById("dateicon").src="images/canceled.png";
    }
    loginggprof();
}
			
function avatar_validatingprof()
{
    var ekstensi = document.registration.avatar.value;
					
    if((ekstensi.lastIndexOf(".jpg") != -1) || (ekstensi.lastIndexOf(".jpeg") != -1) )
    {
        document.getElementById("avaicon").src="images/centang.png";
    }
    else
    {
        document.getElementById("avaicon").src="images/canceled.png";
    }
    loginggprof();
}
				
function loginggprof()
{	
    var picon = document.getElementById("passicon").src;
    var cicon = document.getElementById("conficon").src;
    var nicon = document.getElementById("nameicon").src;
    var aicon = document.getElementById("avaicon").src;
    var dicon = document.getElementById("dateicon").src;
    var lokasi = "http://localhost:8080/IF3038-2013-TUBES3/images/centang.png";
					
    if ((picon == lokasi) || (cicon == lokasi) || (nicon == lokasi) || (aicon == lokasi) || (dicon == lokasi))
    {
        document.getElementById("submitedit").disabled = false;
    }
    else
    {
        document.getElementById("submitedit").disabled = true;
    }
}

function showDoneTask(){
    document.getElementById("donetask").innerHTML="";
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
            document.getElementById("donetask").innerHTML=xmlhttp.responseText;		
        }
    }
    xmlhttp.open("GET", "Task?aksi=lihat_done_task", true);
    xmlhttp.send();
}

function showUndoneTask(){
    document.getElementById("undonetask").innerHTML="";
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
            document.getElementById("undonetask").innerHTML=xmlhttp.responseText;		
        }
    }
    xmlhttp.open("GET", "Task?aksi=lihat_undone_task", true);
    xmlhttp.send();
}

function showTasks(){
    showDoneTask();
    showUndoneTask();
}