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
    
    http.onreadystatechange = function(){
        if(http.readyState == 4){ 
            var response = http.responseText;
            //alert(response);
            if(response==0){
                alert("username dan password salah");
            }else{
               // alert(response+"login berhasil");
                window.location = "Dashboard.php";
            }
        }
    };
    
    var username = encodeURI(document.getElementById('logusername').value);
    var pass = encodeURI(document.getElementById('logpassword').value);
    http.open('get','login.php?username='+username+'&pass='+pass);
    http.send(null);
}



