function register() {

var uname = encodeURI(document.getElementById('uname').value);
var pass = encodeURI(document.getElementById('pass').value);
var fullname = encodeURI(document.getElementById('name').value);
var email = encodeURI(document.getElementById('email').value);
var dob = encodeURI(document.getElementById('dob').value);

nocache = Math.random();
http.open('get', 'registerpage.php?uname='+uname+'&pass='+pass+'&fullname='+fullname+'&email='+email+'&dob='+dob);
http.onreadystatechange = loginReply;
http.send(null);
}