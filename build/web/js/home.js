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
                        alert("Username telah digunakan ...");
                        document.getElementById("usericon").src="images/canceled.png";
                    }
                }
            }
        xmlhttp.open("GET","register?username="+userid+"&email="+email,true);
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
                alert("Email telah digunakan ...");
                document.getElementById("emailicon").src="images/canceled.png";
            }
        }
    }
    xmlhttp.open("GET","register?username=''&email="+emails,true);
    xmlhttp.send();
}

var ajaxRequest;  // The variable that makes Ajax possible!
    
function checkAjax(){
    
    try{
        // Opera 8.0+, Firefox, Safari
        ajaxRequest = new XMLHttpRequest();
    } catch (e){
        // Internet Explorer Browsers
        try{
            ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try{
                ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e){
                // Something went wrong
                alert("Your browser broke!\n" + "Error : " + e);
                return false;
            }
        }
    } 
}

function validateLogin(){
    var id = document.getElementById('login_username').value;
    var pass = document.getElementById('login_pass').value;
   
    checkAjax();
    
    if (id != "null" && pass != "null"){
        ajaxRequest.open("GET", "checkUser?id="+id+"&pass="+pass, false );
        
        ajaxRequest.onreadystatechange = function(){
        if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200){
            var loginResponse = ajaxRequest.responseText;
                if (loginResponse == "true"){                    
                    window.location = "dashboard.jsp";
                } else {
                    alert("Something wrong with your Username or Password");
                }
            }
        };
        ajaxRequest.send();
    }
}

function sedangLogin(){
    if (typeof(Storage) != "undefined"){
        checkAjax();
        if (localStorage.getItem('username')){
            ajaxRequest.onreadystatechange = function(){
                if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200){
                    if(ajaxRequest.responseText == "true"){
                        alert("Anda masih Login");
                        window.location = "dashboard.jsp";
                    } else {
                        alert("Error on Login ...");
                    }
                }
            }
            ajaxRequest.open("POST","getLocal",true);
            ajaxRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            ajaxRequest.send("locals="+localStorage.getItem('username'));
        } else {
                alert("Anda Belum Login");
        }
    } else {
        alert ("Your browser doesn't support storage");
    }
}

/* - yang belum validasi versi java 
 * - 
 * $("#msgbox").removeClass().addClass('myfailure').text('Failed Login, Please Try Again ...').fadeIn(1000);*/