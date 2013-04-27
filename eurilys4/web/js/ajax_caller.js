function ajax_login() {
    var username = document.getElementById("login_username");
    var password = document.getElementById("login_password");

    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp=new XMLHttpRequest();
    }
    else {// code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    xmlhttp.onreadystatechange=function() {
        if (xmlhttp.readyState==4 && xmlhttp.status==200) {
            if (xmlhttp.responseText == "failed") {
                alert('failed');
            }
            else alert("fullname : " + xmlhttp.responseText)
        }
    } 
    
    var url="user/login_check/"+username+"/"+password;
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}