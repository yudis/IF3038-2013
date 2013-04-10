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
        xmlhttp.open("GET","validasiRegist.php?username="+userid+"&email="+email,true);
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
                alert("Email telah dipakai ");
                document.getElementById("emailicon").src="images/canceled.png";
            }
        }
    }
    xmlhttp.open("GET","validasiRegist.php?username=''&email="+emails,true);
    xmlhttp.send();
}
