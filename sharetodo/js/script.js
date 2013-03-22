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
        xmlhttp.open("GET","php/gethint.php?key=" + str, true);
        xmlhttp.send();
}

function keProfil() {
	window.location = 'Profil.php';
}

function showKategori(kategori) {
	//alert(kategori);
	
	var _xmlhttp;
	if (window.XMLHttpRequest) { //membuat objek XMLHttpRequest
		_xmlhttp = new XMLHttpRequest();
	} else {
		_xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	_xmlhttp.open("GET","php/changeKategori.php?k=" + kategori,true);
	_xmlhttp.send();
	
	_xmlhttp.onreadystatechange = function() {
		if ((_xmlhttp.readyState == 4) && (_xmlhttp.status == 200)) {
			var replacement = _xmlhttp.responseText;
			//var sementara = _xmlhttp.responseXML;
			//alert(sementara);
			document.getElementById("dynamicSpace").innerHTML = replacement;
		}
	}
}
function showAllKategori() {
	var _xmlhttp;
	if (window.XMLHttpRequest) { //membuat objek XMLHttpRequest
		_xmlhttp = new XMLHttpRequest();
	} else {
		_xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	_xmlhttp.open("GET","php/showAllKategori.php",true);
	_xmlhttp.send();
	
	_xmlhttp.onreadystatechange = function() {
		if ((_xmlhttp.readyState == 4) && (_xmlhttp.status == 200)) {
			var replacement = _xmlhttp.responseText;
			document.getElementById("dynamicSpace").innerHTML = replacement;
		}
	}
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
function updateProfile(newFullName, newBirthdate, newPassword, newPasswordAgain, fileUpload) {
	//alert(newFullName + " "+ newBirthdate + " " + newPassword + " " + newPasswordAgain + " " + fileUpload);
	
	if (newPassword == newPasswordAgain) {
		//menampilkan notifikasi saat field password tidak diubah
		if ((newPassword == "") && (newPasswordAgain == "")) {
			alert("Warning. Field password tidak berubah");
		}
		
		if (newFullName == "") {
			//alert("Warning. Anda belum mengisi nama");
			newFullName = "-111";
		}
		if (newBirthdate == "") {
			//alert("Warning. Anda belum isi tanggalLahir");
			newBirthdate = "-222";
			//alert(newBirthdate);
		}
		
		var __xmlhttp;
		if (window.XMLHttpRequest) {
			__xmlhttp = new XMLHttpRequest();
		} else {
			__xmlhttp = new ActiveXObject(Microsoft.XMLHTTP);
		}
		
		__xmlhttp.onreadystatechange = function() {
			//alert("readystatechange" + __xmlhttp.readyState + " " + __xmlhttp.status);
			if ((__xmlhttp.readyState == 4) && (__xmlhttp.status == 200)) {
				//ditampung xmlresponsenya
				//alert(__xmlhttp.responseText);
				$xmlresponse = __xmlhttp.responseXML; //responsexml tidak bisa langsung diambil, akan mengakibatkan null
				alert("Profile telah berhasil diperbaharui");
				//mengolah respon dalam bentuk xml
				$responseElmt = $xmlresponse.getElementsByTagName("response");
				
				$fullname = $responseElmt[0].childNodes[0].childNodes[0].nodeValue;
				$birthdate = $responseElmt[0].childNodes[1].childNodes[0].nodeValue;
				
				document.getElementById("userFullName").innerHTML = "<p>" + $fullname + "</p>";
				document.getElementById("userBirthdate").innerHTML = "<p>" + $birthdate + "</p>";
				//setTimeout(function() {
				//},10000);
			}
		}
		
		__xmlhttp.open("POST","php/updateProfile.php",true);
		__xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		//__xmlhttp.setRequestHeader("Content-length", $param.length);
		//__xmlhttp.setRequestHeader("Connection", "close");
		
		__xmlhttp.send("newFullName=" + newFullName + "&newBirthdate=" + newBirthdate + "&newPassword=" + newPassword + "&newFileName=" + fileUpload);
	} else{
		alert("Peringatan! kedua password Anda tidak sesuai");
	}
}

/**************************DASHBOARD**************************/

var isTaskStatusClicked = false;
function changeTaskStatus(namaTask,id) {
	//alert(namaTask);
	$status = "undefined";
	if (!isTaskStatusClicked) {
		isTaskStatusClicked = true;
		$status = "selesai";
		document.getElementById(id).innerHTML = "<p>selesai</p>";
	} else {
		isTaskStatusClicked = false;
		$status = "belum";
		document.getElementById(id).innerHTML = "<p>belum selesai</p>";
	}
	
	//melakukan update database mengenai status terbaru
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject(Microsoft.XMLHTTP);
	}
	
	xmlhttp.onreadystatechange = function(){
		if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200)) {
			$responseUpdate = xmlhttp.responseText;
			alert($responseUpdate);
		}
	}
	
	xmlhttp.open("GET","php/CommitStatus.php?stat=" + $status + "&task=" + namaTask,true);
	xmlhttp.send();
}

function toHalamanRincianTugas(namaHlm) {
	alert("pindah ke halaman " + namaHlm);
}

function toHalamanPembuatanTugas() {
	alert("pindah ke halaman pembuatan tugas");
}
function deleteTask(namaTask) {
	//meminta username dan creator dari task yang hendak dihapus
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject(Microsoft.XMLHTTP);
	}
	xmlhttp.onreadystatechange = function(){
		if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200)) {
			$response = xmlhttp.responseText;
			//alert(xmlhttp.responseText);
			if ($response == "1") {
				document.getElementById(namaTask+"space").innerHTML = "";
				alert("Tugas telah berhasil dihapus");
			} else {
				alert("Warning. Anda tidak berhak untuk menghapus tugas ini.");
			}
		}
	}
	xmlhttp.open("GET","php/getDeletionInfo.php?task="+namaTask,true);
	xmlhttp.send();
}
function closeKategoriForm(id) {
	document.getElementById(id).style.visibility = "hidden";
}
function addKategori(katName,userList) {
	//alert(katName + " " + userList);
	
	//memeriksa apakah semua user yang dimasukkan valid / ada di dalam database
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject(Microsoft.XMLHTTP);
	}
	xmlhttp.onreadystatechange = function(){
		if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200)) {
			$response = xmlhttp.responseText;
			alert($response);
			
			//menampilkan penambahan kolom kategori secara langsung
		}
	}
	xmlhttp.open("GET","php/insertKategori.php?kat="+katName+"&userList="+userList,true);
	xmlhttp.send();
}
function deleteKategori(namaKategori) {
	//alert("siakek" + namaKategori);
	
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject(Microsoft.XMLHTTP);
	}
	xmlhttp.onreadystatechange = function(){
		if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200)) {
			$response = xmlhttp.responseText;
			alert($response);
		}
	}
	xmlhttp.open("GET","php/deleteKategori.php?kat="+namaKategori,true);
	xmlhttp.send();
	//cek apakah user yang sedang aktif berhak untuk menghapus kategori
	
	//hapus semua table terkait : tabel kategori, editKategori, task
}