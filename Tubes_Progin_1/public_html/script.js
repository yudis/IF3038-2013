/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function showList(){
document.getElementById("listtugas3").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="hidden";
document.getElementById("listtugas").style.visibility="visible";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="hidden";
document.getElementById("wanted").style.visibility="hidden";
self.focus();
}

function showList2(){
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="visible";
document.getElementById("listtugas3").style.visibility="hidden";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="hidden";
document.getElementById("wanted").style.visibility="hidden";
self.focus();
}

function showList3(){
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="hidden";
document.getElementById("listtugas3").style.visibility="visible";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="hidden";
document.getElementById("wanted").style.visibility="hidden";
self.focus();
}

function showRinci(){
document.getElementById("listtugas3").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="hidden";
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("rincitugas").style.visibility="visible";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="hidden";
document.getElementById("wanted").style.visibility="visible";
self.focus();
}

function showEdit(){
document.getElementById("listtugas3").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="hidden";
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="visible";
document.getElementById("buattugas").style.visibility="hidden";
document.getElementById("wanted").style.visibility="visible";
}
function showBuat(){
document.getElementById("listtugas3").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="hidden";
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="visible";
document.getElementById("wanted").style.visibility="visible";
}

function createTask() {
    var k = document.getElementById("listtugas");
    k.innerHTML = "<a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a onclick='showBuat()' class='addTask'></a>"
    showList();
}