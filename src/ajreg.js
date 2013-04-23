function validateReg(){
  document.getElementById('error-username').style.height = "1px";
	document.getElementById('error-username').innerHTML = "";
	document.getElementByIda('error-password').style.height = "1px";
	document.getElementById('error-password').innerHTML = "";
	document.getElementById('error-cpass').style.height = "1px";
	document.getElementById('error-cpass').innerHTML = "";
	document.getElementById('error-email').style.height = "1px";
	document.getElementById('error-email').innerHTML = "";
	document.getElementById('error-nama').style.height = "1px";
	document.getElementById('error-nama').innerHTML = "";
	document.getElementById('error-tgl').style.height = "1px";
	document.getElementById('error-tgl').innerHTML = "";
	document.getElementById('error-avatar').style.height = "1px";
	document.getElementById('error-avatar').innerHTML = "";
	document.getElementById('error-sex').style.height = "1px";
	document.getElementById('error-sex').innerHTML = "";
	
	var uname = getValue("euname");
	var pass = getValue("epass");
	var cpass = getValue("ecpass");
	var email = getValue("eemail");
	var nama = getValue("enama");
	var ava = getValue("eava");
	var tgl = getValue("etgl");
	var sex = getValue("esex");
	
	if (uname!=""){
		document.getElementById('error-username').style.height = "30px";
		document.getElementById('error-username').innerHTML = uname;
	}
	if (pass!=""){
		document.getElementById('error-password').style.height = "30px";
		document.getElementById('error-password').innerHTML = pass;
	}
	if (cpass!=""){
		document.getElementById('error-cpass').style.height = "30px";
		document.getElementById('error-cpass').innerHTML = cpass;
	}
	if (email!=""){
		document.getElementById('error-email').style.height = "30px";
		document.getElementById('error-email').innerHTML = email;
	}
	if (nama!=""){
		document.getElementById('error-nama').style.height = "30px";
		document.getElementById('error-nama').innerHTML = nama;
	}
	if (tgl!=""){
		document.getElementById('error-tgl').style.height = "30px";
		document.getElementById('error-tgl').innerHTML = tgl;
	}
	if (ava!=""){
		document.getElementById('error-avatar').style.height = "30px";
		document.getElementById('error-avatar').innerHTML = ava;
	}
	if (sex!=""){
		document.getElementById('error-sex').style.height = "30px";
		document.getElementById('error-sex').innerHTML = sex;
	}
	
	document.getElementById('form-username').value = getValue("uname");
	document.getElementById('form-password').value = getValue("pass");
	document.getElementById('form-cpass').value = getValue("cpass");
	document.getElementById('form-email').value = getValue("email");
	document.getElementById('form-nama').value = getValue("nama");
	document.getElementById('form-tgl').value = getValue("tgl");
	document.getElementById('form-about').value = getValue("about");
	if (getValue("sex")=="female")
		document.getElementById('form-female').checked = true;
	else if (getValue("sex")=="male")
		document.getElementById('form-male').checked = true;
	//ava
}

function valLive(tipe,pform,perror)
  {
    var field = pform.value;
    var msg = document.getElementById(perror);
 
    var code = '';
    var message = '';
    xmlhttp=mintajax();
    xmlhttp.onreadystatechange=function()
    {
      if (xmlhttp.readyState== 4 && xmlhttp.status== 200)
      {
		var result = eval("("+xmlhttp.responseText+")");
        code = result['code'];
        message = result['result'];
        if(code <=0)
        {
          msg.style.height = "30px";
          msg.innerHTML = message;
        }else{
			msg.style.height = "1px";
			msg.innerHTML = "";
		}
		
      }
    }
    xmlhttp.open("GET","vallive.php?tipe="+tipe+"&field="+field,true);
    xmlhttp.send(null);
	//alert(tipe+"\n"+pform+"\n"+perror);
}

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
	bulan++;
	if (bulan < 10) hasil += "-0" + bulan;
	else hasil += "-" + bulan;
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
