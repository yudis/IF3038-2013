function search() {

var searchterm = encodeURI(document.getElementById('search_box').value);
var searchtype = encodeURI(document.getElementById('search_type').value);

http.open('get', 'php/search.php?searchterm='+searchterm+'&searchtype='+searchtype);
http.onreadystatechange = loginReply;
}