
var http = new XMLHttpRequest();

var nocache = 0;
function login() {
// Optional: Show a waiting message in the layer with ID ajax_response
document.getElementById('login_response').innerHTML = "Loading..."
// Required: verify that all fileds is not empty. Use encodeURI() to solve some issues about character encoding.
var uname = encodeURI(document.getElementById('unameLogin').value);
var passw = encodeURI(document.getElementById('passwLogin').value);
// Set te random number to add to URL request
nocache = Math.random();
// Pass the login variables like URL variable
http.open('get', 'php/login.php?uname='+uname+'&passw='+passw+'&nocache = '+nocache);
http.onreadystatechange = loginReply;
http.send(null);
}
function loginReply() {
if(http.readyState == 4){
var response = http.responseText;
if(response == 0){
// if login fails
document.getElementById('login_response').innerHTML = 'Login failed! Verify user and password';
// else if login is ok show a message: "Welcome + the user name".
} else {
window.location = "src/dashboard.php"
}
}
}