/*
 * 
 */
function onload() {
    var name = getQueryParameter('name');
    if (name == null) {
        name = 'Tugas 1';
    }
    document.getElementById('namaTugas').innerHTML = '<b>' + name + '</b>';
}

function addKomentar() {
    var now = new Date();
     //Edit: changed TT to tt
    var komentar_div = document.getElementById("komentar");
    komentar_div.innerHTML += "<b>WhoAmI</b> - " + now.toString("dd MMMM yyyy hh:mm") + "<hr />" + document.getElementById('txtKomentar').value + "<br /><br />";
    
    return false;
}