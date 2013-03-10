var islike = false;
var isdislike = false;
document.getElementById('jmllike').innerHTML = Math.floor(Math.random()*10);
function getbulan(a) {
	switch (a) {
		case 1: return "January"; break;
		case 2: return "February"; break;
		case 3: return "March"; break;
		case 4: return "April"; break;
		case 5: return "May"; break;
		case 6: return "June"; break;
		case 7: return "July"; break;
		case 8: return "August"; break;
		case 9: return "Sepetember"; break;
		case 10: return "October"; break;
		case 11: return "November"; break;
		case 12: return "December"; break;
		default: return "Some month"; break;
	}
}
function duadigit(a) {
	if (a < 10) return "0"+a;
	else return a;
}
function like() {
	var jml = document.getElementById('jmllike').innerHTML;
	if (!islike) {
		jml++;
		document.getElementById('blike').className = "islike";
		document.getElementById('bdislike').className = "notdislike";
		document.getElementById('bdislike').innerHTML = "";
		islike = true;
	}
	else {
		jml--;
		document.getElementById('blike').className = "like";
		document.getElementById('bdislike').className = "dislike";
		document.getElementById('bdislike').innerHTML = "<a href=\"javascript:dislike();void(0);\"></a>";
		islike = false;
	}
	document.getElementById('jmllike').innerHTML = jml;
}
function dislike() {
	var jml = document.getElementById('jmllike').innerHTML;
	if (!isdislike) {
		jml--;
		document.getElementById('bdislike').className = "isdislike";
		document.getElementById('blike').className = "notlike";
		document.getElementById('blike').innerHTML = "";
		isdislike = true;
	} else {
		jml++;
		document.getElementById('bdislike').className = "dislike";
		document.getElementById('blike').className = "like";
		document.getElementById('blike').innerHTML = "<a href=\"javascript:like();void(0);\"></a>";
		isdislike = false;
	}
	document.getElementById('jmllike').innerHTML = jml;
}
function commenting() {
	if (document.getElementById('form-nama').value.length == 0) {
		alert('Isi nama untuk ngomentar');
		document.getElementById('form-nama').focus();
	} else if (document.getElementById('form-komen').value.length == 0) {
		alert('Isi komentar untuk ngomentar');
		document.getElementById('form-komen').focus();
	} else {
		var date = new Date();
		var bulan = getbulan(date.getMonth());
		var tanggal = duadigit(date.getDate());
		var tahun = date.getFullYear();
		var jam = duadigit(date.getHours());
		var menit = duadigit(date.getMinutes());
		var lkomen = document.getElementById('lkomen').innerHTML;
		lkomen = "<div class=\"komen-nama\">" + document.getElementById('form-nama').value + "</div><div class=\"komen-tgl\">| " + bulan + " " + tanggal + ", " + tahun + " at " + jam + ":" + menit + "</div><div class=\"komen-isi\">" + document.getElementById('form-komen').value + "</div><div class=\"line-konten\"></div>" + lkomen;
		document.getElementById('lkomen').innerHTML = lkomen;
		var jmlkomen = document.getElementById('jmlkomen').innerHTML;
		jmlkomen++;
		document.getElementById('jmlkomen').innerHTML = jmlkomen;
		document.getElementById('form-nama').value = "";
		document.getElementById('form-komen').value = "";
	}
}