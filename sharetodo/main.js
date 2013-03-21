
var regex_email;
var regex_username;
var regex_password;
var regex_nama;
var regex_tanggal;

function kalender()
{
    new JsDatePick({
                   useMode:2,
                   target:"tgl_lahir",
                   dateFormat:"%Y-%m-%d",
                   yearsRange:[1954,2020],
                   cellColorScheme:"aqua"
                   });
};

window.onload = function(){
    new JsDatePick({
                   useMode:2,
                   target:"inputDeadline",
                   dateFormat:"%Y-%m-%d",
                   earsRange:[1954,2020],
                   cellColorScheme:"aqua"
                   });
};

function loadRegex()
{
    regex_email = /^[A-Za-z][A-Za-z0-9_\.]*@[A-Za-z0-9]+[A-Za-z0-9\.-]*\.[A-Za-z]{2,}$/;
    regex_username = /^[0-9a-zA-Z]{5,}$/;
    regex_password = /^.{8,}$/;
    regex_nama = /^\w{2,} \w{2,}$/;
    regex_tanggal = /^\d{4}-\d{2}-\d{2}$/;
}

function validate_email()
{
    var input = document.getElementById("email_signup");
    if(input.value == "")
    {
        document.getElementById("error_email").innerHTML = "Harus diisi";
        return false;
    }
    if(input.value.match(regex_email))
    {
        
        document.getElementById("error_email").innerHTML = "";
        return true;
    }
    else
    {
        document.getElementById("error_email").innerHTML = "Email tidak valid";
        return false;
    }
}

function validate_email_exist(str)
{
    var xmlhttp;
    if(str.length==0)
    {
        return;
    }
    if(window.XMLHttpRequest)
        xmlhttp = new XMLHttpRequest();
    else
        xmlhttp = new ActiveXObject("Microsodt.XMLHTTP");
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState==4 && xmlhttp.status==200)
            document.getElementById("error_email_exist").innerHTML = xmlhttp.responseText;
        
        if(xmlhttp.responseText == "")
            return true;
        else
            return false;
    }
    
    xmlhttp.open("GET", "register.php?p="+str, true);
    xmlhttp.send();
}


function validate_username()
{
    var input = document.getElementById("usrnm_signup");
    if(input.value == "")
    {
        document.getElementById("error_username").innerHTML = "Harus diisi";
        return false;
    }
    if(input.value.match(regex_username))
    {
        
        document.getElementById("error_username").innerHTML = "";
        return true;
    }
    else
    {
        document.getElementById("error_username").innerHTML = "Hanya alphanumeric dan minimal 5 karakter";
        return false;
    }
}

function validate_username_exist(str)
{
    var xmlhttp;
    if(str.length==0)
    {
        return;
    }
    if(window.XMLHttpRequest)
        xmlhttp = new XMLHttpRequest();
    else
        xmlhttp = new ActiveXObject("Microsodt.XMLHTTP");
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState==4 && xmlhttp.status==200)
            document.getElementById("error_username_exist").innerHTML = xmlhttp.responseText;
        
        if(xmlhttp.responseText == "")
            return true;
        else
            return false;
    }
    
    xmlhttp.open("GET", "register.php?q="+str, true);
    xmlhttp.send();
}

function validate_password()
{
    var input = document.getElementById("psswrd_signup");
    if(input.value == "")
    {
        document.getElementById("error_password").innerHTML = "Harus diisi";
        return false;
    }
    if(input.value.match(regex_password))
    {
        if(input.value == document.getElementById("usrnm_signup").value && input.value != "")
        {
            document.getElementById("error_password").innerHTML = "Tidak boleh sama dengan username";
            return false;
        }
        if(input.value == document.getElementById("email_signup").value && input.value != "")
        {
            document.getElementById("error_password").innerHTML = "Tidak boleh sama dengan email";
            return false;
        }
        document.getElementById("error_password").innerHTML = "";
        return true;
    }
    else
    {
        document.getElementById("error_password").innerHTML = "Minimal 8 karakter";
        return false;
    }
}

function confirm_password()
{
    var password = document.getElementById("psswrd_signup");
    var password_confirm = document.getElementById("cnfrm_psswrd_signup");
    
    if(password_confirm.value == "")
    {
        document.getElementById("error_confirm_password").innerHTML = "Harus diisi";
        return false;
    }
    
    if(password.value != password_confirm.value)
    {
        document.getElementById("error_confirm_password").innerHTML = "Password tidak sama";
        return false;
    }
    else
    {
        document.getElementById("error_confirm_password").innerHTML = "";
        return true;
    }
}

function validate_nama()
{
    var input = document.getElementById("nama_signup");
    if(input.value == "")
    {
        document.getElementById("error_nama").innerHTML = "Harus diisi";
        return false;
    }
    if(input.value.match(regex_nama))
    {
        document.getElementById("error_nama").innerHTML = "";
        return true;
    }
    else
    {
        document.getElementById("error_nama").innerHTML = "Nama tidak valid";
        return false;
    }
}

function disable_signup_button()
{
    if(!validate_username() || !validate_email() || !validate_nama() || !confirm_password() || !validate_password() || !validate_tanggal_lahir())
    {
        document.getElementById("signup_button").disabled=true;
    }
    else
    {
        document.getElementById("signup_button").disabled=false;
    }
}

function validate_tanggal_lahir()
{
    var input = document.getElementById("tgl_lahir");
    if(input.value == "")
    {
        document.getElementById("error_tanggal_lahir").innerHTML = "Harus diisi";
        return false;
    }
    if(input.value.match(regex_tanggal))
    {
        document.getElementById("error_tanggal_lahir").innerHTML = "";
        return true;
    }
    else
    {
        document.getElementById("error_tanggal_lahir").innerHTML = "Format seharusnya yyyy-mm-dd";
        return false;
    }
}

function validate()
{
    var input = document.getElementById("namaTugas");
    var letters = /^[0-9a-zA-Z ]{0,25}$/;
    
    if(input.value.match(letters))
    {
        //document.getElementById("error").innerHTML=inputtxt.length.;
        return true;
        
    }
    else
    {
        alert("Tidak boleh mengandung karakter khusus dan panjang maksimal 25");
        return false;
    }
}

//smooth scroll effect
function currentYPosition()
{
    if(self.pageYOffset) return pageYOffset;
    if(document.documentElement && document.documentElement.scrollTop)
        return document.documentElement.scrollTop;
    if(document.body.scrollTop)
        return document.body.scrollTop;
    
    return 0;
}

function elementYPosition(id)
{
    var elm = document.getElementById(id);
    var y = elm.offsetTop;
    var node = elm;
    while(node.offsetParent && node.offsetParent != document.body) {
        node = node.offsetParent;
        y += node.offsetTop;
    }
    return y;
}

function smoothScroll(id)
{
    var startY = currentYPosition();
    var stopY = elementYPosition(id);
    var distance = stopY > startY ? stopY - startY : startY - stopY;
    if(distance < 100) {
        scrollTo(0, stopY);
        return;
    }
    var speed = Math.round(distance/100);
    if(speed >= 20) speed = 20;
    var step = Math.round(distance/100);
    var leapY = stopY > startY ? startY + step : startY - step;
    var timer = 0;
    if(stopY > startY) {
        for(var i=startY; i < stopY; i+=step) {
            setTimeout("window.scrollTo(0, "+leapY+")", timer*speed);
            leapY += step; if (leapY > stopY) leapY = stopY; timer++;
        }
        return;
    }
    
    for(var i=startY; i>stopY; i-=step) {
        setTimeout("window.scrollTo(0, "+leapY+")", timer * speed);
        leapY -= step; if (leapY < stopY) leapY = stopY; timer++;
    }
}

function checkSignIn(usrnm, psswrd)
{
    //alert(psswrd);
    var xmlhttp;
    if(usrnm.length==0 || psswrd.length==0) //panjang username dan psw tidak boleh sama dengan nol
    {
        document.getElementById("error_signin").innerHTML = "Isi username dan password";
        return false;
    }
    if(window.XMLHttpRequest)
        xmlhttp = new XMLHttpRequest();
    else
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState==4 && xmlhttp.status==200) {
            document.getElementById("error_signin").innerHTML = xmlhttp.responseText;
            
            if(xmlhttp.responseText == "") {
                window.open("dashboard.php","_self");
                return true;
            }
            
            else {
                alert("username dan password Anda tidak valid");
                return false;
            }
        }
        
    }
    
    xmlhttp.open("GET", "login.php?u="+usrnm+"&p="+psswrd, true);
    xmlhttp.send();
}

function hideDivById(id)
{
    var elm = document.getElementById(id);
    elm.style.display = "none";
}

function showDivById(id)
{
    var elm = document.getElementById(id);
    elm.style.display = "block";
}

function enterButton(doc) {
//    alert("enter");
    if(window.event.keyCode==13)
        doc.click();
}

