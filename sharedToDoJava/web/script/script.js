// JavaScript Document

/**************************HEADER**************************/

function toSearchResult(keyword,filter) {
	//alert(keyword + " " + filter);
	window.location = "searching.php?keyword=" + keyword + "&filter=" + filter;
}

function showHint(str) {
    //alert(str);
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
            //alert("sini " + xmlhttp.readyState + " & " + xmlhttp.status);
            if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200)){
//                alert("masuk");
                message = xmlhttp.responseText;
                document.getElementById("textHint").innerHTML = message;
                //alert(message);
            }
        }
//        var url = "http://localhost:8080/SharedToDoList/Suggestion?k=" + str;
        var url = "Suggestion?k=" + str;
        xmlhttp.open("GET",url,true);
        xmlhttp.send();
}

function keProfil() {
	window.location = 'Profil.php';
}

function showKategori(kategori) {
//	alert(kategori);
	
	var _xmlhttp;
	if (window.XMLHttpRequest) { //membuat objek XMLHttpRequest
		_xmlhttp = new XMLHttpRequest();
	} else {
		_xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	_xmlhttp.open("GET","changeKategori?k=" + kategori,true);
	_xmlhttp.send();
	
	_xmlhttp.onreadystatechange = function() {
		if ((_xmlhttp.readyState == 4) && (_xmlhttp.status == 200)) {
//                        alert("aman");
			var replacement = _xmlhttp.responseText;
//                        alert("aman");
//                        alert(replacement);
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
	
	_xmlhttp.open("GET","showAllKategori",true);
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
function updateProfile(newFullName, newBirthdate, newPassword, newPasswordAgain) {
//	alert(newFullName + " "+ newBirthdate + " " + newPassword + " " + newPasswordAgain);
	
	if (newPassword == newPasswordAgain) { //password match
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
		if (newPassword == "") {
			newPassword = "-333";
		}
		
		var __xmlhttp;
		if (window.XMLHttpRequest) {
			__xmlhttp = new XMLHttpRequest();
		} else {
			__xmlhttp = new ActiveXObject(Microsoft.XMLHTTP);
		}
		
		__xmlhttp.onreadystatechange = function() {
//			alert("readystatechange" + __xmlhttp.readyState + " " + __xmlhttp.status);
			if ((__xmlhttp.readyState == 4) && (__xmlhttp.status == 200)) {
				//ditampung xmlresponsenya
//                                alert("aman");
                                $reply = __xmlhttp.responseText;
//				alert("Response : " + reply);
                                var $update = $reply.split("*");
//                                alert(update[0] + "&" + update[1]);
				/*$xmlresponse = __xmlhttp.responseXML; //responsexml tidak bisa langsung diambil, akan mengakibatkan null
				alert("Profile telah berhasil diperbaharui");
				//mengolah respon dalam bentuk xml
				$responseElmt = $xmlresponse.getElementsByTagName("response");
				
				$fullname = $responseElmt[0].childNodes[0].childNodes[0].nodeValue;
				$birthdate = $responseElmt[0].childNodes[1].childNodes[0].nodeValue;*/
				
				document.getElementById("userFullName").innerHTML = "<p>" + $update[0] + "</p>";
				document.getElementById("userBirthdate").innerHTML = "<p>" + $update[1] + "</p>";
			}
		}
		
		__xmlhttp.open("POST","UserDataUpdate",true);
		__xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		//__xmlhttp.setRequestHeader("Content-length", $param.length);
		//__xmlhttp.setRequestHeader("Connection", "close");
		
		__xmlhttp.send("newFullName=" + newFullName + "&newBirthdate=" + newBirthdate + "&newPassword=" + newPassword);
	} else{
		alert("Peringatan! kedua password Anda tidak sesuai");
	}
}

/**************************DASHBOARD**************************/

var isTaskStatusClicked = false;
function changeTaskStatus(namaTask,id) {
//    alert(namaTask + " dan " + id);
	$status = "undefined";
        $kodeUbah = "";
	if (!isTaskStatusClicked) {
		isTaskStatusClicked = true;
		$status = "selesai";
                $kodeUbah = "0";
	} else {
		isTaskStatusClicked = false;
		$status = "belum";
                $kodeUbah = "1";
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
//			alert($responseUpdate);
                        
                        if ($responseUpdate == 1) {
                            if ($kodeUbah == "0") {
                                    document.getElementById(id).innerHTML = "<p>selesai</p>";
                            } else {
                                    document.getElementById(id).innerHTML = "<p>belum</p>";
                            }
                            alert("Success. Status telah berhasil diubah");
                        } else if ($responseUpdate == 0) {
                            alert("Warning. Anda tidak berhak edit tugas!");
                        }
		}
	}
	
	xmlhttp.open("GET","CommitStatus?stat=" + $status + "&task=" + namaTask,true);
	xmlhttp.send();
}

function toHalamanRincianTugas(namaTask) {
//	alert("pindah ke halaman " + namaTask);
	window.location = "halamanRincianTugas.jsp?task=" + namaTask;
}

function toHalamanPembuatanTugas(kat) {
	window.location = "createtask.php?kategori=" + kat;
	//alert("pindah ke halaman pembuatan tugas");
}
function deleteTask(namaTask) {
	//meminta username dan creator dari task yang hendak dihapus
//	alert(namaTask);
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject(Microsoft.XMLHTTP);
	}
	xmlhttp.onreadystatechange = function(){
		if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200)) {
			$response = xmlhttp.responseText;
//			alert($response);
			if ($response == 1) {
				alert("Success. Tugas telah berhasil dihapus");
				document.getElementById(namaTask+"space").innerHTML = "";
			} else if ($response == 0){
                            alert("Failed. Tugas tidak berhasil dihapus karena alasan teknis");
			} else if ($response == 2) {
                            alert("Warning. Anda tidak berhak untuk menghapus tugas ini.");
                        }
		}
	}
	xmlhttp.open("GET","GetDeletionInfo?task="+namaTask,true);
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
	xmlhttp.open("GET","InsertKategori?kat="+katName+"&userList="+userList,true);
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
	xmlhttp.open("GET","DeleteKategori?kat="+namaKategori,true);
	xmlhttp.send();
	//cek apakah user yang sedang aktif berhak untuk menghapus kategori
	
	//hapus semua table terkait : tabel kategori, editKategori, task
}

/**************************Halaman Rincian Tugas**************************/

function toUserProfile(userName) {
//    alert(userName);
    window.location = "profile.jsp?username=" + userName + "";
}