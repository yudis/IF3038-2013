function initButton() {
	document.getElementById('addtask').disabled = true;
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