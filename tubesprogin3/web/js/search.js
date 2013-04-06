var http = new XMLHttpRequest();

function search() {

var searchterm = encodeURI(document.getElementById('search_box').value);
var searchtype = encodeURI(document.getElementById('search_type').value);

http.open('get', 'search.jsp?searchterm='+searchterm+'&searchtype='+searchtype);

}