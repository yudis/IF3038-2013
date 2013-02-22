function init() {
	document.getElementById('addtask').disabled = true;
	document.getElementById('sbTask').disabled = true;
	initDateOfTask();
	localStorage.state = "true";
}

function initDateOfTask(){
	initTaskYears();
	initTaskMonth();
	initTaskDay();
}

function initTaskYears(){
	var sel = document.getElementsByName('taskYear');
	var opt = null;
	for(j = 0; j < sel.length; j++) {
		for(i = 2000; i<=2099; i++) { 
			opt = document.createElement('option');
			opt.value = i;
			opt.innerHTML = i;
			sel[j].appendChild(opt);
		}
	}
}
function initTaskMonth(){
	var sel = document.getElementsByName('taskMonth');
	var opt = null;
	var bulan = ['Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni', 'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember'];
	for(j = 0; j < sel.length; j++) {
		for(i = 0; i<12; i++) { 
			opt = document.createElement('option');
			opt.value = bulan[i];
			opt.innerHTML = bulan[i];
			sel[j].appendChild(opt);
		}
	}
}
function initTaskDay(){
	var sel = document.getElementsByName('taskDay');
	var opt = null;
	for(j = 0; j < sel.length; j++) {
		for(i = 1; i<=31; i++) { 
			opt = document.createElement('option');
			opt.value = i;
			opt.innerHTML = i;
			sel[j].appendChild(opt);
		}
	}
}

function submitComment(id1,id2) {
	var comment = document.getElementById(id1).value;
	if(comment != "")
		document.getElementById(id2).innerHTML = comment;
	return false;
}

function onClickButton(id) {
	var temp = document.getElementsByName("category");
	for(var i = 0; i < temp.length; i++) {
		temp[i].setAttribute("class", "buttonnormal");
	}
	document.getElementById(id).setAttribute("class", "buttonpressed");
	switch(id) {
		case("allcategories"):
			document.getElementById("tasks").innerHTML = "<li><a href='#taskdetail1'><h2> Tugas Besar Pemrograman Internet </h2></a>"
			document.getElementById("tasks").innerHTML += "Tanggal Penyelesaian : 22 Februari 2013 <br />Tag : Tugas, Besar, Progin, IF3038</li>"
			document.getElementById("tasks").innerHTML += "<li><a href='#taskdetail2'><h2> Tugas Besar Inteligensia Buatan </h2></a>"
			document.getElementById("tasks").innerHTML += "Tanggal Penyelesaian : 22 Februari 2013 <br />Tag : Tugas, Besar, IB, AI, IF3052</li>"
			document.getElementById("tasks").innerHTML += "<li><a href='#taskdetail3'><h2> Penyerahan Proposal </h2></a>"
			document.getElementById("tasks").innerHTML += "Tanggal Penyelesaian : 28 Februari 2013 <br />Tag : PKM</li>"
			document.getElementById("tasks").innerHTML += "<li><a href='#taskdetail4'><h2> Periksa Kuis </h2></a>"
			document.getElementById("tasks").innerHTML += "Tanggal Penyelesaian : 7 Maret 2013 <br />Tag : TBO, Kuis</li>"
			document.getElementById("tasks").innerHTML += "<li><a href='#taskdetail5'><h2> Memasukkan Nilai </h2></a>"
			document.getElementById("tasks").innerHTML += "Tanggal Penyelesaian : 1 Januari 2013 <br />Tag : Probstat, Nilai</li>"
			document.getElementById('addtask').disabled = true;
			break;
		case("category1"):
			document.getElementById("tasks").innerHTML = "<li><a href='#taskdetail1'><h2> Tugas Besar Pemrograman Internet </h2></a>"
			document.getElementById("tasks").innerHTML += "Tanggal Penyelesaian : 22 Februari 2013 <br />Tag : Tugas, Besar, Progin, IF3038</li>"
			document.getElementById("tasks").innerHTML += "<li><a href='#taskdetail2'><h2> Tugas Besar Inteligensia Buatan </h2></a>"
			document.getElementById("tasks").innerHTML += "Tanggal Penyelesaian : 22 Februari 2013 <br />Tag : Tugas, Besar, IB, AI, IF3052</li>"
			document.getElementById('addtask').disabled = false;
			break;
		case("category2"):
			document.getElementById("tasks").innerHTML = "<li><a href='#taskdetail4'><h2> Periksa Kuis </h2></a>"
			document.getElementById("tasks").innerHTML += "Tanggal Penyelesaian : 7 Maret 2013 <br />Tag : TBO, Kuis</li>"
			document.getElementById("tasks").innerHTML += "<li><a href='#taskdetail5'><h2> Memasukkan Nilai </h2></a>"
			document.getElementById("tasks").innerHTML += "Tanggal Penyelesaian : 1 Januari 2013 <br />Tag : Probstat, Nilai</li>"
			document.getElementById('addtask').disabled = false;
			break;
		case("category3"):
			document.getElementById("tasks").innerHTML = "<li><a href='#taskdetail3'><h2> Penyerahan Proposal </h2></a>"
			document.getElementById("tasks").innerHTML += "Tanggal Penyelesaian : 28 Februari 2013 <br />Tag : PKM</li>"
			document.getElementById('addtask').disabled = false;
			break;
	}
}

function submitCategory() {
	if(document.getElementById("txCatName").value != "") {
		document.getElementById("listtugas").innerHTML = document.getElementById("listtugas").innerHTML + "<li class='buttonnormal' name='category'>" + document.getElementById("txCatName").value + "</li>";
		window.location.href="Dashboard.html#";
        self.close();
	}
}

function editDate(code){
	switch(code) {
		case('1') :
			if (document.getElementById('editdate1').value != "OK"){
				document.getElementById('editdate1').value = "OK";
				document.getElementById("deadline1").innerHTML = "Tanggal Penyelesaian : <select name='taskDay' id='taskDay1'></select><select name='taskMonth' id='taskMonth1'></select><select name='taskYear' id='taskYear1'></select>";
				initDateOfTask();
			}
			else {
				var tanggal = document.getElementById('taskDay1').value + ' ' + document.getElementById('taskMonth1').value + ' ' + document.getElementById('taskYear1').value;
				document.getElementById('editdate1').value = "Ubah";
				document.getElementById("deadline1").innerHTML = "Tanggal Penyelesaian : " + tanggal;
			}
			break;
		case('2') :
			if (document.getElementById('editdate2').value != "OK"){
				document.getElementById('editdate2').value = "OK";
				document.getElementById("deadline2").innerHTML = "Tanggal Penyelesaian : <select name='taskDay' id='taskDay2'></select><select name='taskMonth' id='taskMonth2'></select><select name='taskYear' id='taskYear2'></select>";
				initDateOfTask();
			}
			else {
				var tanggal = document.getElementById('taskDay2').value + ' ' + document.getElementById('taskMonth2').value + ' ' + document.getElementById('taskYear2').value;
				document.getElementById('editdate2').value = "Ubah";
				document.getElementById("deadline2").innerHTML = "Tanggal Penyelesaian : " + tanggal;
			}
			break;
		case('3') :
			if (document.getElementById('editdate3').value != "OK"){
				document.getElementById('editdate3').value = "OK";
				document.getElementById("deadline3").innerHTML = "Tanggal Penyelesaian : <select name='taskDay' id='taskDay3'></select><select name='taskMonth' id='taskMonth3'></select><select name='taskYear' id='taskYear3'></select>";
				initDateOfTask();
			}
			else {
				var tanggal = document.getElementById('taskDay3').value + ' ' + document.getElementById('taskMonth3').value + ' ' + document.getElementById('taskYear3').value;
				document.getElementById('editdate3').value = "Ubah";
				document.getElementById("deadline3").innerHTML = "Tanggal Penyelesaian : " + tanggal;
			}
			break;
		case('4') :
			if (document.getElementById('editdate4').value != "OK"){
				document.getElementById('editdate4').value = "OK";
				document.getElementById("deadline4").innerHTML = "Tanggal Penyelesaian : <select name='taskDay' id='taskDay4'></select><select name='taskMonth' id='taskMonth4'></select><select name='taskYear' id='taskYear4'></select>";
				initDateOfTask();
			}
			else {
				var tanggal = document.getElementById('taskDay4').value + ' ' + document.getElementById('taskMonth4').value + ' ' + document.getElementById('taskYear4').value;
				document.getElementById('editdate4').value = "Ubah";
				document.getElementById("deadline4").innerHTML = "Tanggal Penyelesaian : " + tanggal;
			}
			break;
		case('5') :
			if (document.getElementById('editdate5').value != "OK"){
				document.getElementById('editdate5').value = "OK";
				document.getElementById("deadline5").innerHTML = "Tanggal Penyelesaian : <select name='taskDay' id='taskDay5'></select><select name='taskMonth' id='taskMonth5'></select><select name='taskYear' id='taskYear5'></select>";
				initDateOfTask();
			}
			else {
				var tanggal = document.getElementById('taskDay5').value + ' ' + document.getElementById('taskMonth5').value + ' ' + document.getElementById('taskYear5').value;
				document.getElementById('editdate5').value = "Ubah";
				document.getElementById("deadline5").innerHTML = "Tanggal Penyelesaian : " + tanggal;
			}
			break;
	}
}

function editTag(code){
	switch(code) {
		case('1') :
			if (document.getElementById('tag1').value != "OK"){
				document.getElementById('tag1').value = "OK";
				document.getElementById("tanda1").innerHTML = "Tag : <input type='text' id='edittag1' />";
			}
			else {
				document.getElementById('tag1').value = "Ubah";
				document.getElementById("tanda1").innerHTML = "Tag : " + document.getElementById('edittag1').value;
			}
			break;
		case('2') :
			if (document.getElementById('tag2').value != "OK"){
				document.getElementById('tag2').value = "OK";
				document.getElementById("tanda2").innerHTML = "Tag : <input type='text' id='edittag2' />";
			}
			else {
				document.getElementById('tag2').value = "Ubah";
				document.getElementById("tanda2").innerHTML = "Tag : " + document.getElementById('edittag2').value;
			}
			break;
		case('3') :
			if (document.getElementById('tag3').value != "OK"){
				document.getElementById('tag3').value = "OK";
				document.getElementById("tanda3").innerHTML = "Tag : <input type='text' id='edittag3' />";
			}
			else {
				document.getElementById('tag3').value = "Ubah";
				document.getElementById("tanda3").innerHTML = "Tag : " + document.getElementById('edittag3').value;
			}
			break;
		case('4') :
			if (document.getElementById('tag4').value != "OK"){
				document.getElementById('tag4').value = "OK";
				document.getElementById("tanda4").innerHTML = "Tag : <input type='text' id='edittag4' />";
			}
			else {
				document.getElementById('tag4').value = "Ubah";
				document.getElementById("tanda4").innerHTML = "Tag : " + document.getElementById('edittag4').value;
			}
			break;
		case('5') :
			if (document.getElementById('tag5').value != "OK"){
				document.getElementById('tag5').value = "OK";
				document.getElementById("tanda5").innerHTML = "Tag : <input type='text' id='edittag5' />";
			}
			else {
				document.getElementById('tag5').value = "Ubah";
				document.getElementById("tanda5").innerHTML = "Tag : " + document.getElementById('edittag5').value;
			}
			break;
	}
}

function addAssignee(code){	
	if (document.getElementById("adassignee1").innerHTML != ""){
		if(document.getElementById("name1").value != "") {
			var temp = "<li>" + document.getElementById("name1").value + "</li>";
			document.getElementById("assignee1").innerHTML += temp;
		}
		document.getElementById("adassignee1").innerHTML = "";
		document.getElementById("add1").setAttribute("value","Tambah");
	}else if(document.getElementById("adassignee2").innerHTML != ""){
		if(document.getElementById("name2").value != "") {
			var temp = "<li>" + document.getElementById("name2").value + "</li>";
			document.getElementById("assignee2").innerHTML += temp;
		}
		document.getElementById("adassignee2").innerHTML = "";
		document.getElementById("add2").setAttribute("value","Tambah");
	}else if(document.getElementById("adassignee3").innerHTML != ""){
		if(document.getElementById("name3").value != "") {
			var temp = "<li>" + document.getElementById("name3").value + "</li>";
			document.getElementById("assignee3").innerHTML += temp;
		}
		document.getElementById("adassignee3").innerHTML = "";
		document.getElementById("add3").setAttribute("value","Tambah");
	}else if(document.getElementById("adassignee4").innerHTML != ""){
		if(document.getElementById("name4").value != "") {
			var temp = "<li>" + document.getElementById("name4").value + "</li>";
			document.getElementById("assignee4").innerHTML += temp;
		}
		document.getElementById("adassignee4").innerHTML = "";
		document.getElementById("add4").setAttribute("value","Tambah");
	}else if(document.getElementById("adassignee5").innerHTML != ""){
		if(document.getElementById("name5").value != "") {
			var temp = "<li>" + document.getElementById("name5").value + "</li>";
			document.getElementById("assignee5").innerHTML += temp;
		}
		document.getElementById("adassignee5").innerHTML = "";
		document.getElementById("add5").setAttribute("value","Tambah");
	}else if(code == '1'){
		document.getElementById("add1").setAttribute("value","OK");
		var form ="Assignee : <input type=\"text\" id='name1'>";
		document.getElementById("adassignee1").innerHTML = form;
	}else if(code == '2'){
		document.getElementById("add2").setAttribute("value","OK");
		var form ="Assignee : <input type=\"text\" id='name2'>";
		document.getElementById("adassignee2").innerHTML = form;
	}else if(code == '3'){
		document.getElementById("add3").setAttribute("value","OK");
		var form ="Assignee : <input type=\"text\" id='name3'>";
		document.getElementById("adassignee3").innerHTML = form;
	}else if(code == '4'){
		document.getElementById("add4").setAttribute("value","OK");
		var form ="Assignee : <input type=\"text\" id='name4'>";
		document.getElementById("adassignee4").innerHTML = form;
	}else if(code == '5'){
		document.getElementById("add5").setAttribute("value","OK");
		var form ="Assignee : <input type=\"text\" id='name5'>";
		document.getElementById("adassignee5").innerHTML = form;
	}
}