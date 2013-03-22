/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/* ---------------------------- */
/* XMLHTTPRequest Enable */
/* ---------------------------- */
function createObject() {
    var request_type;
    var browser = navigator.appName;
    if (browser == "Microsoft Internet Explorer") {
        request_type = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        request_type = new XMLHttpRequest();
    }
    return request_type;
}

function logins() {

    var http = createObject();

    http.onreadystatechange = function() {
        if (http.readyState == 4) {
            var response = http.responseText;
            //alert(response);
            if (response == 0) {
                alert("username dan password salah");
            } else {
                // alert(response+"login berhasil");
                window.location = "Dashboard.php";
            }
        }
    };

    var username = encodeURI(document.getElementById('logusername').value);
    var pass = encodeURI(document.getElementById('logpassword').value);
    http.open('get', 'login.php?username=' + username + '&pass=' + pass);
    http.send(null);
}

function showcat() {

    var cat = createObject();
    
    cat.onreadystatechange = function() {
        if (cat.readyState == 4)
        {
            var response = cat.responseText;
            if (cat.responseText != "")
            {
                document.getElementById("category").innerHTML="";
                document.getElementById("category").innerHTML=response;
            }
        }
        
    };
    cat.open('get','listcat.php');
    cat.send(null);

}

function showTask(a) {
 var task = createObject();
    
    task.onreadystatechange = function() {
        if (task.readyState == 4)
        {
            var response = task.responseText;
            if (task.responseText != "")
            {
                document.getElementById("listtugas").innerHTML="";
                document.getElementById("listtugas").innerHTML=response;
            }
        }
        
    };
    task.open('get','listtask.php?category=' + a);
    task.send(null);

}

function update() {
    showcat();
    showTask();
}

setInterval(update(), 5000);