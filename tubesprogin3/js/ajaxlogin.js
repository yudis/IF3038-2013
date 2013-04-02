
var http = new XMLHttpRequest();

var nocache = 0;
function login() {
document.getElementById('login_response').innerHTML = "Loading..."
var uname = encodeURI(document.getElementById('unameLogin').value);
var passw = encodeURI(document.getElementById('passwLogin').value);
nocache = Math.random();
http.open('get', 'php/login.php?uname='+uname+'&passw='+passw+'&nocache = '+nocache);
http.onreadystatechange = loginReply;
http.send(null);
}
function loginReply() {
if(http.readyState == 4){
var response = http.responseText;
if(response == 0){
document.getElementById('login_response').innerHTML = 'Login gagal. Silahkan ulangi memasukkan username dan password Anda.';
} else {
window.location = "src/dashboard.php"
}
}
}