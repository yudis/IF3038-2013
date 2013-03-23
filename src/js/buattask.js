function name_valid() {
	var namaid = document.buatugas.username.value;
	if((namaid.length > "25" ) || (namaid.match(/[!"#$%&'90*+,.:;<=>?@\^_`{|}~]/))){
		document.getElementById("namaicon").src="pict/canceled.png";
	} else {
		document.getElementById("namaicon").src="pict/centang.png";
	}
}
	
function atta_validating() {
	var attaid = document.buatugas.attach.value;				
	if(
	   (attaid.lastIndexOf(".jpg") != -1) ||
	   (attaid.lastIndexOf(".jpeg") != -1) ||
	   (attaid.lastIndexOf(".mp3") != -1) ||
	   (attaid.lastIndexOf(".mp4") != -1) ||
	   (attaid.lastIndexOf(".avi") != -1) ||
	   (attaid.lastIndexOf(".mkv") != -1)
	  ) {
			document.getElementById("attaicon").src="pict/centang.png";
		} else {
			document.getElementById("attaicon").src="pict/canceled.png";
		}
}
	
function dead_validating() {
	document.getElementById("deadicon").src="pict/centang.png"
	}

function asi_validating() {
	var asign = document.buatugas.namasign.value;
	if(asign.match(/([a-zA-Z])+([ \t\r\n\v\f])+([a-zA-Z])/)) {
			document.getElementById("asicon").src="pict/centang.png";
		} else {
			document.getElementById("asicon").src="pict/canceled.png";
		}
}

function tag_validating() {
	var tagid = document.buatugas.tag.value;
	if(tagid.match(/([ \t\r\n\v\f])/)){
		document.getElementById("tagicon").src="pict/canceled.png";
	} else {
		document.getElementById("tagicon").src="pict/centang.png";
	}
}

<!--AJAX AUTOCOMPLETE ASSIGNEE-->
function showResult(str, div) {
	if(str.length==0) {
		document.getElementById(div).innerHTML="";
		document.getElementById(div).style.border="0px";
		return;
	}
	var str_arr = str.split(", ");
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
			document.getElementById(div).innerHTML=xmlhttp.responseText;
			document.getElementById(div).style.border="1px solid #A5ACB2";
			document.getElementById(div).style.width="250px";
		}
	}
	xmlhttp.open("GET", "autocomplete_assignee.php?q="+str_arr[str_arr.length -1], true);
	xmlhttp.send();
}
function sort_and_unique( my_array ) {
	my_array.sort();
	for ( var i = 1; i < my_array.length; i++ ) {
		if ( my_array[i] === my_array[ i - 1 ] ) {
			my_array.splice( i--, 1 );
		}
	}
	return my_array;
};
function autocomplete_diklik(str) {
	var string_awal = document.getElementById("idasignee").value;
	var str_arr = string_awal.split(", ");
	str_arr[str_arr.length-1]=str;
	str_arr = sort_and_unique(str_arr);
	document.getElementById("idasignee").value = str_arr.join(", ")+", ";
	document.getElementById("idasignee").focus();
	document.getElementById("hasil_autocomplete").innerHTML="";
}