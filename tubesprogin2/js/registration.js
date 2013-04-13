function registerakun() {

var uname = encodeURI(document.getElementById('reg_username').value);
var passw = encodeURI(document.getElementById('reg_password').value);
var fullname = encodeURI(document.getElementById('reg_name').value);
var email = encodeURI(document.getElementById('reg_email').value);
var birthdate = encodeURI(document.getElementById('reg_birthdate').value);

nocache = Math.random();
http.open('get', 'php/registration.php?uname='+uname+'&passw='+passw+'&fullname='+fullname+'&email='+email+'&birthdate='+birthdate+'&nocache = '+nocache);
http.onreadystatechange = loginReply;
http.send(null);
}