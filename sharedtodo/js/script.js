// JavaScript Document

/**************************HEADER**************************/

function showHint(str) {
        var xmlhttp;
        if (str.length == 0) {
            document.getElementById("textHint").innerHTML = "";
            return;
        }
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        } else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //ie5 dan ie6
        }
        
        xmlhttp.onreadystatechange = function() {
            if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200)){
                document.getElementById("textHint").innerHTML = xmlhttp.responseText;
            }
        }
        xmlhttp.open("GET","gethint.php?key=" + str, true);
        xmlhttp.send();
}

function keProfil() {
	window.location = 'Profil.php';
}

function active_1() {
	var elmt_1 = document.getElementById("dynamic_1");
	elmt_1.style.visibility = "visible";
	
	var elmt_2 = document.getElementById("dynamic_2");
	elmt_2.style.visibility = "hidden";
	
	var elmt_3 = document.getElementById("dynamic_3");
	elmt_3.style.visibility = "hidden";
	
	var elmt_semuaTugas = document.getElementById("dynamic_semuaTugas");
	elmt_semuaTugas.style.visibility = "hidden";
	
	var highlight_1 = document.getElementById("static_1");
	highlight_1.style.background = "#DCDCDC";
	
	var highlight_2 = document.getElementById("static_2");
	highlight_2.style.background = "#0F3";
	
	var highlight_3 = document.getElementById("static_3");
	highlight_3.style.background = "#0F3";
	
	var highlight_semuaTugas = document.getElementById("semuaTugas");
	highlight_semuaTugas.style.background = "#0F3";
}

function active_2() {
	var elmt_1 = document.getElementById("dynamic_1");
	elmt_1.style.visibility = "hidden";
	
	var elmt_2 = document.getElementById("dynamic_2");
	elmt_2.style.visibility = "visible";
	
	var elmt_3 = document.getElementById("dynamic_3");
	elmt_3.style.visibility = "hidden";
	
	var elmt_semuaTugas = document.getElementById("dynamic_semuaTugas");
	elmt_semuaTugas.style.visibility = "hidden";
	
	var highlight_1 = document.getElementById("static_1");
	highlight_1.style.background = "#0F3";
	
	var highlight_2 = document.getElementById("static_2");
	highlight_2.style.background = "#DCDCDC";
	
	var highlight_3 = document.getElementById("static_3");
	highlight_3.style.background = "#0F3";

	var highlight_semuaTugas = document.getElementById("semuaTugas");
	highlight_semuaTugas.style.background = "#0F3";
}

function active_3() {
	var elmt_1 = document.getElementById("dynamic_1");
	elmt_1.style.visibility = "hidden";
	
	var elmt_2 = document.getElementById("dynamic_2");
	elmt_2.style.visibility = "hidden";
	
	var elmt_3 = document.getElementById("dynamic_3");
	elmt_3.style.visibility = "visible";
	
	var elmt_semuaTugas = document.getElementById("dynamic_semuaTugas");
	elmt_semuaTugas.style.visibility = "hidden";
	
	var highlight_1 = document.getElementById("static_1");
	highlight_1.style.background = "#0F3";
	
	var highlight_2 = document.getElementById("static_2");
	highlight_2.style.background = "#0F3";
	
	var highlight_3 = document.getElementById("static_3");
	highlight_3.style.background = "#DCDCDC";
	
	var highlight_semuaTugas = document.getElementById("semuaTugas");
	highlight_semuaTugas.style.background = "#0F3";
}

function active_semuaTugas(){
	var elmt_1 = document.getElementById("dynamic_1");
	elmt_1.style.visibility = "hidden";
	
	var elmt_2 = document.getElementById("dynamic_2");
	elmt_2.style.visibility = "hidden";
	
	var elmt_3 = document.getElementById("dynamic_3");
	elmt_3.style.visibility = "hidden";
	
	var elmt_semuaTugas = document.getElementById("dynamic_semuaTugas");
	elmt_semuaTugas.style.visibility = "visible";
	
	var highlight_1 = document.getElementById("static_1");
	highlight_1.style.background = "#0F3";
	
	var highlight_2 = document.getElementById("static_2");
	highlight_2.style.background = "#0F3";
	
	var highlight_3 = document.getElementById("static_3");
	highlight_3.style.background = "#0F3";
	
	var highlight_semuaTugas = document.getElementById("semuaTugas");
	highlight_semuaTugas.style.background = "#DCDCDC";
}

function showPopUp() {
	var popUpArea = document.getElementById("popUp");
	popUpArea.style.visibility = "visible";
}

/**************************PROFIL**************************/

function showEditForm() {
	document.getElementById("editForm").style.visibility = "visible";
}
function hideEditForm() {
	document.getElementById("editForm").style.visibility = "hidden";
}
function changeFullName(newName) {
	document.getElementById("userFullName").innerHTML = "newName";
}

/**************************DASHBOARD**************************/

var isTaskStatusClicked = false;
function changeTaskStatus(id) {
	if (!isTaskStatusClicked) {
		isTaskStatusClicked = true;
		document.getElementById(id).innerHTML = "<p>selesai</p>";
	} else {
		isTaskStatusClicked = false;
		document.getElementById(id).innerHTML = "<p>belum selesai</p>";
	}
}

function toHalamanRincianTugas(namaHlm) {
	alert("pindah ke halaman " + namaHlm);
}

function toHalamanPembuatanTugas() {
	alert("pindah ke halaman pembuatan tugas");
}