
function updatetugas(id) {
	date = document.getElementById("iddeadline").value;
	asignee = document.getElementById("idasignee").value;
	tag = document.getElementById("idtag").value;
	if(window.XMLHttpRequest) {
		// untuk IE7, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	}
	else {
		//untuk IE jadul
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			document.getElementById('detail').innerHTML=xmlhttp.responseText;
		}
	}
	xmlhttp.open("GET", "ajax_edittask.php?id="+id+"&deadline="+date+"&assignee="+asignee+"&tag="+tag, true);
	xmlhttp.send();
}

function tambahkomen(id,iduser) {
	komen = document.getElementById("takomen").value;
	
	if(window.XMLHttpRequest) {
		// untuk IE7, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	}
	else {
		//untuk IE jadul
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			document.getElementById('komen').innerHTML+=xmlhttp.responseText;
		}
	}
	xmlhttp.open("GET", "ajax_tambahkomen.php?id="+id+"&komen="+komen+"&iduser="+iduser, true);
	xmlhttp.send();
}

function clearta () {
  document.getElementById("takomen").value = "";
}

