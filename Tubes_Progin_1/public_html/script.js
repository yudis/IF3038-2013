/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function login () {
    var user = document.getElementsById("user").value;
    if  (true) {
        window.open("/dashboard.html", "_self");
        document.getElementById("logo").style.visibility="hidden";
    } else {
        document.getElementById("register").style.visibility="hidden";
    }
}

function showList(){
document.getElementById("listtugas").style.visibility="visible";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="hidden";
self.focus();
}

function showRinci(){
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("rincitugas").style.visibility="visible";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="hidden";
self.focus();
}

function showEdit(){
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="visible";
document.getElementById("buattugas").style.visibility="hidden";
}
function showBuat(){
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="visible";
}