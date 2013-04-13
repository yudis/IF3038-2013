/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function user_validating()
{
    var userid = document.registration.username.value;
    var userpass = document.registration.password.value;
    var email=document.getElementById("email");
					
    if((userid.length >= "5") && (userid != userpass))
        {
            document.getElementById("usericon").src="images/centang.png";
        }
    else
        {
            document.getElementById("usericon").src="images/canceled.png";
        }
							
    var xmlhttp;
    if (window.XMLHttpRequest)
        {
            xmlhttp=new XMLHttpRequest();
        }
            else
        {
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
						
        xmlhttp.onreadystatechange = function()
            {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    if (xmlhttp.responseText.search("true") != -1)
                    {
                        logingg();
                    }
                    else
                    {
                        alert("Username telah dipakai ");
                        document.getElementById("usericon").src="images/canceled.png";
                    }
                }
            }
        xmlhttp.open("GET","validasiRegist?username="+userid+"&email="+email,true);
        xmlhttp.send();
}

function pass_validating()
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
        logingg();
    }
    else
    {
        document.getElementById("passicon").src="images/canceled.png";
    }
						
}

function conf_validating()
{
    var userpass = document.registration.password.value;
    var confpass = document.registration.confirmpass.value;
					
    if(confpass == userpass)
    {
        document.getElementById("conficon").src="images/centang.png";
        logingg();
    }
    else
    {
        document.getElementById("conficon").src="images/canceled.png";
    }
}

function nama_validating()
{
    var name = document.registration.namaleng.value;
					
    if(name.match(/([a-zA-Z])+([ \t\r\n\v\f])+([a-zA-Z])/))
    {
        document.getElementById("nameicon").src="images/centang.png";
        logingg();
    }
    else
    {
        document.getElementById("nameicon").src="images/canceled.png";
    }
}

function email_validating()
{
    var emails = document.registration.email.value;
					
    if(emails.match(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i))
    {
        document.getElementById("emailicon").src="images/centang.png";
    }
    else
    {
        document.getElementById("emailicon").src="images/canceled.png";
    }
					
    
    if (window.XMLHttpRequest)
    {
        xmlhttp=new XMLHttpRequest();
    }
    else
    {
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
						
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            if (xmlhttp.responseText.search("true") != -1)
            {
                logingg();
            }
            else
            {
                alert("Email telah dipakai ");
                document.getElementById("emailicon").src="images/canceled.png";
            }
        }
    }
    xmlhttp.open("GET","validasiRegist?username=''&email="+emails,true);
    xmlhttp.send();
}

function logine()
{
    var a = document.getElementById("userid");
    var b = document.getElementById("passid");
    var flag = false;
		
    if (window.XMLHttpRequest)
    {
        //code buat IE7 dan browser lainnya
        xmlhttp = new XMLHttpRequest();
    }
    else
    {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
						
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            if (xmlhttp.responseText.search("true") != -1)
            {
                alert("Login berhasil ");
                window.location="dashboard.jsp";
                if (typeof(Storage) != "undefined")
                {
                    localStorage.setItem('username',a.value);
                }
                else
                {
                    alert("Sorry, your browser does not support web storage "); 
                }
                flag = true;
            }
            else
            {
                alert("Login gagal ");
            }
        }
    }
    xmlhttp.open("POST","login2",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send("userid="+a.value+"&passid="+b.value);
						
    return flag;
}

function isAlreadyLogin()
{
    if (typeof(Storage) != "undefined")
    {
        if (window.XMLHttpRequest)
        {
            xmlhttp = new XMLHttpRequest();
        }
        else
        {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
							
        if (localStorage.getItem('username'))
        {
									
            xmlhttp.onreadystatechange = function()
            {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    if (xmlhttp.responseText = "true")
                    {
                       alert("Already Login");
                       window.location =("dashboard.jsp");
                    }
                    else
                    {
                        alert("Error detected !");
                    }
                }
            }
            xmlhttp.open("POST","getLocal",true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send("locals="+localStorage.getItem('username'));
        }
        else
        {
            alert("Belum Login");
        }
    }
    else
    {
        alert("Sorry, your browser does not support web storage.. ");
    }
}

function logingg()
{	
    var uicon = document.getElementById("usericon").src;
    var picon = document.getElementById("passicon").src;
    var cicon = document.getElementById("conficon").src;
    var nicon = document.getElementById("nameicon").src;
    var eicon = document.getElementById("emailicon").src;
    var aicon = document.getElementById("avaicon").src;
    var dicon = document.getElementById("dateicon").src;
    var lokasi = "http://localhost:8080/IF3038-2013-TUBES3/images/centang.png";
					
    if ((uicon == lokasi) && (picon == lokasi) && (cicon == lokasi) && (nicon == lokasi) && (eicon == lokasi) && (aicon == lokasi) && (dicon == lokasi))
    {
        document.getElementById("submitb").disabled = false;
    }
}

function date_validating()
{
    document.getElementById("dateicon").src="images/centang.png";
    logingg();
}

function avatar_validating()
{
    var ekstensi = document.registration.avatar.value;
					
    if((ekstensi.lastIndexOf(".jpg") != -1) || (ekstensi.lastIndexOf(".jpeg") != -1) )
    {
        document.getElementById("avaicon").src="images/centang.png";
        logingg();
    }
    else
    {
        document.getElementById("avaicon").src="images/canceled.png";
    }
}




