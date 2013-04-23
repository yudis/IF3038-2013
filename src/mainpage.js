function checkext(a) {
	if (a.length == 0) return false;
	else {
		var s = "";
		for (i = a.length - 1; i >= 0; i--) {
			if (a.charAt(i) == '.') {
				break;
			} else {
				s = a.charAt(i) + s;
			}
		}
		if (s.toLowerCase() == "jpeg" || s.toLowerCase() == "jpg") return true;
	}
}
function validate() {
	var bolehusername = false;
	var bolehpassword = false;
	var bolehcpass = false;
	var bolehnama = false;
	var bolehtgl = false;
	var bolehemail = false;
	var bolehavatar = false;
	var bolehsex = false;
	
	document.getElementById('error-username').style.height = "30px";
	if (document.getElementById('form-username').value.length < 5) {
		document.getElementById('error-username').innerHTML = "Username minimal 5 karakter";
	} else if (document.getElementById('form-username').value == document.getElementById('form-password').value) {
		document.getElementById('error-username').innerHTML = "Username tidak boleh sama dengan password";
	} else {
		document.getElementById('error-username').style.height = "1px";
		document.getElementById('error-username').innerHTML = "";
		bolehusername = true;
	}
	
	document.getElementById('error-password').style.height = "30px";
	if (document.getElementById('form-password').value.length < 8) {
		document.getElementById('error-password').innerHTML = "Password minimal 8 karakter";
	} else if (document.getElementById('form-password').value == document.getElementById('form-username').value) {
		document.getElementById('error-password').innerHTML = "Password tidak boleh sama dengan username";
	} else if (document.getElementById('form-password').value == document.getElementById('form-email').value) {
		document.getElementById('error-password').innerHTML = "Password tidak boleh sama dengan email";
	}  else {
		document.getElementById('error-password').style.height = "1px";
		document.getElementById('error-password').innerHTML = "";
		bolehpassword = true;
	}
	
	document.getElementById('error-cpass').style.height = "30px";
	if (document.getElementById('form-cpass').value != document.getElementById('form-password').value) {
		document.getElementById('error-cpass').innerHTML = "Confirm password harus sama dengan password";
	} else {
		document.getElementById('error-cpass').style.height = "1px";
		document.getElementById('error-cpass').innerHTML = "";
		bolehcpass = true;
	}
	
	document.getElementById('error-nama').style.height = "30px";
	if (document.getElementById('form-nama').value.indexOf(' ') < 1 || document.getElementById('form-nama').value.indexOf(' ')+1 >= document.getElementById('form-nama').value.length) {
		document.getElementById('error-nama').innerHTML = "Minimal mengandung satu spasi diantara 2 karakter";
	} else {
		document.getElementById('error-nama').style.height = "1px";
		document.getElementById('error-nama').innerHTML = "";
		bolehnama = true;
	}
	
	document.getElementById('error-tgl').style.height = "30px";
	var formattgl = /^\d{4}\-\d{2}\-\d{2}$/;
	if (!formattgl.test(document.getElementById('form-tgl').value)) {
		document.getElementById('error-tgl').innerHTML = "Format tanggal harus berupa YYYY-MM-DD";
	} else {
		document.getElementById('error-tgl').style.height = "1px";
		document.getElementById('error-tgl').innerHTML = "";
		bolehtgl = true;
	}
	
	document.getElementById('error-email').style.height = "30px";
	var formatemail = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,})$/;
	if (!formatemail.test(document.getElementById('form-email').value)) {
		document.getElementById('error-email').innerHTML = "Format email salah";
	} else {
		document.getElementById('error-email').style.height = "1px";
		document.getElementById('error-email').innerHTML = "";
		bolehemail = true;
	}
	
	document.getElementById('error-avatar').style.height = "30px";
	if (!checkext(document.getElementById('form-avatar').value)) {
		document.getElementById('error-avatar').innerHTML = "Tipe file harus ber ekstensi .jpg atau .jpeg";
	} else {
		document.getElementById('error-avatar').style.height = "1px";
		document.getElementById('error-avatar').innerHTML = "";
		bolehavatar = true;
	}
	
	document.getElementById('error-sex').style.height = "30px";
	if ((document.getElementById('form-male').checked) || (document.getElementById('form-female').checked)) {
		document.getElementById('error-sex').style.height = "1px";
		document.getElementById('error-sex').innerHTML = "";
		bolehsex = true;
	} else {
		document.getElementById('error-sex').innerHTML = "Harus pilih salah satu";
	}
	
	if (bolehusername && bolehpassword && bolehcpass && bolehnama && bolehtgl && bolehemail && bolehavatar && bolehsex) {
		document.getElementById("form-button").disabled = false;
	} else {
		document.getElementById("form-button").disabled = true;
	}
}
validate();

var getbulan = new Array(12);
getbulan[0] = 'Januari';
getbulan[1] = 'Februari';
getbulan[2] = 'Maret';
getbulan[3] = 'April';
getbulan[4] = 'Mei';
getbulan[5] = 'Juni';
getbulan[6] = 'Juli';
getbulan[7] = 'Agustus';
getbulan[8] = 'September';
getbulan[9] = 'Oktober';
getbulan[10] = 'November';
getbulan[11] = 'Desember';
function jmlhari(bulan,tahun) {
	return new Date(tahun,bulan,0).getDate();
}
function changemonth(tahun) {
	showcal(document.getElementById('lbulan').selectedIndex,tahun);
}
function tangkap(hari,bulan,tahun) {
	var hasil = tahun;
	if (bulan < 10) hasil += "-0" + (bulan + 1);
	else hasil += "-" + (bulan + 1);
	if (hari < 10) hasil +=  "-0" + hari;
	else hasil +=  "-" + hari;
	document.getElementById('calendar').innerHTML = "";
	document.getElementById('calendar').style.zIndex = "-100";
	document.getElementById('form-tgl').value = hasil;
	validate();
}
function closecal() {
	document.getElementById('calendar').innerHTML = "";
	document.getElementById('calendar').style.zIndex = "-100";
}
function showcal(bulan,tahun) {
	document.getElementById('calendar').style.zIndex = '100';
	var date = new Date();
	date.setFullYear(tahun);
	date.setMonth(bulan);
	date.setDate(1);
	//
	var nexttahun = tahun; if (tahun < 9999) nexttahun++;
	var prevtahun = tahun; if (tahun > 1000) prevtahun--;
	//
	var carl = "<div id=\"caljudul\"><select id=\"lbulan\" onchange=\"changemonth("+tahun+");\">";
	for (i = 0; i < 12; i++) {
		if (i == bulan) carl += "<option selected=\"selected\">"+getbulan[i]+"</option>";
		else carl += "<option>"+getbulan[i]+"</option>";
	}
	carl += "</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	carl += "<a href=\"javascript:showcal("+bulan+","+prevtahun+");void(0);\">&lt;&lt;</a>&nbsp;&nbsp;"+tahun+"&nbsp;&nbsp;<a href=\"javascript:showcal("+bulan+","+nexttahun+");void(0);\">&gt;&gt;</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:closecal();void(0);\">Close</a></div>";
	carl += "<div class=\"grid\">Min</div><div class=\"grid\">Sen</div><div class=\"grid\">Sel</div><div class=\"grid\">Rab</div><div class=\"grid\">Kam</div><div class=\"grid\">Jum</div><div class=\"grid\">Sab</div><div class=\"clear\"></div>";
	var iter = date.getDay();
	for (i = 0; i < iter; i++) {
		carl += "<div class=\"grid\"></div>";
	}
	var jh = jmlhari(bulan+1,tahun);
	for (i = 1; i <= jh; i++) {
		if (iter == 6) {
			carl += "<div class=\"grid\"><a href=\"javascript:tangkap("+i+","+bulan+","+tahun+");void(0);\">"+i+"</a></div><div class=\"clear\"></div>";
			iter = -1;
		} else {
			carl += "<div class=\"grid\"><a href=\"javascript:tangkap("+i+","+bulan+","+tahun+");void(0);\">"+i+"</a></div>";
		}
		iter++;
	}
	if (iter > 0) {
		for (i = iter; i < 7; i++) {
			carl += "<div class=\"grid\"></div>";
		}
	}
	document.getElementById('calendar').innerHTML = carl;
}