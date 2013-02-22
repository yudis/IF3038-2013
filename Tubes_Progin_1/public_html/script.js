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

}

function showList2(){
document.getElementById("listtugas").style.visibility="hidden";
document.getElementById("listtugas2").style.visibility="visible";
document.getElementById("listtugas3").style.visibility="hidden";
document.getElementById("rincitugas").style.visibility="hidden";
document.getElementById("edittugas").style.visibility="hidden";
document.getElementById("buattugas").style.visibility="hidden";
document.getElementById("wanted").style.visibility="hidden";
self.focus;
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

var valid = false;

function createTask() {
    var regex = /^[a-zA-Z0-9]{5,25}$/;
    var fuData = document.getElementById('upload');
        var FileUploadPath = fuData.value;

        if (FileUploadPath == '') {
            // There is no file selected 
            valid = false;
        }
        else {
            var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
            if (Extension == "gif" || Extension == "png" || Extension == "bmp" || Extension == "jpeg") {
                valid = true; // Valid file type
                FileUploadPath == '';
            }
            else {
                valid = false; // Not valid file type
            }
        }

    
    if ((regex.test(document.getElementById("namaTask").value))&&(valid)){
        var k = document.getElementById("listtugas");
        k.innerHTML = "<a class='listTugas' onclick='showRinci();'><a class='listTugas' onclick='showRinci();'></a> <a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a onclick='showBuat()' class='addTask'></a>";
        document.getElementById("namaTask").value = 0;
        showList();
    } else {
        if((regex.test(document.getElementById("namaTask").value))&&!(valid)) {
            alert("please choose the correct attachment");
        } else {
            alert("Task's name must be between 5-25");
        }
    }
}

function addCat() {
        var k = document.getElementById("category");
        var l = document.getElementById("cate").value;
        if(l !== "") {
        k.innerHTML += "<div class='kategori' onclick='showList2();'>"+l+"</div>";
        restore();
        showList();
        } else {
            alert("Input category name");
        }
}

function addCategory() {
   var overlay = document.createElement("div");
   overlay.setAttribute("id","overlay");
   overlay.setAttribute("class", "overlay");
   document.body.appendChild(overlay);
   
   document.getElementById('add').style.display='block';
}


function restore() {
   document.body.removeChild(document.getElementById("overlay"));
   document.getElementById('add').style.display='none';
   document.getElementById('overlay').style.display='none';
}