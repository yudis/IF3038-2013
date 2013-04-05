function getXmlHttpRequest() {
	var xmlHttpObj;
	
	if(window.XMLHttpRequest)
		xmlHttpObj = new XMLHttpRequest();
	else {
		try {
			xmlHttpObj = new ActiveXObject("Msxm12.XMLHTTP");
		}
		catch(e) {
			try {
				xmlHttpObj = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e) {
				xmlHttpObj = false;
			}
		}
	}
	return xmlHttpObj;
}

function initDashboard(sessionID) {
	window.xmlhttp = getXmlHttpRequest();
	if(!window.xmlhttp)
		return;
	window.xmlhttp.open('POST', 'dashboard.php', true);
	window.xmlhttp.onreadystatechange = writeDashboard;
	window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	window.xmlhttp.send();
}

function writeDashboard() {
	if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
		var response = window.xmlhttp.responseXML;
		var idcategory = response.getElementsByTagName('idkategori');
		var category = response.getElementsByTagName('kategori');
		var categlist = document.getElementById('categlist');
		for(var i=0; i < category.length; i++) {
			var kategori = document.createElement('li');
			kategori.name = 'kategori';
			kategori.innerHTML = '<a href=\'javascript:selectCategory("' + idcategory[i].firstChild.nodeValue + '")\'>' + category[i].firstChild.nodeValue + '</a>';
			categlist.appendChild(kategori);
		}
		var id = response.getElementsByTagName('id');
		var nama = response.getElementsByTagName('nama');
		var deadline = response.getElementsByTagName('deadline');
		var status = response.getElementsByTagName('status');
		var tag = response.getElementsByTagName('tag');
		var canerase = response.getElementsByTagName('canerase');
		var kegiatan = document.getElementById('kegiatan');
		for(var i=0; i < nama.length; i++) {
			var tugas = document.createElement('li');
			tugas.setAttribute('name', 'task');
			var link = document.createElement('a');
			link.setAttribute('class', 'list');
			link.setAttribute('href', 'taskdetails.php?id=' + encodeURIComponent(id[i].firstChild.nodeValue));
			link.innerHTML = nama[i].firstChild.nodeValue;
			tugas.appendChild(link);
			var form = document.createElement('form');
			form.innerHTML = "<label for='deadline'>Deadline</label>: " + deadline[i].firstChild.nodeValue + "<br />";
			form.innerHTML = form.innerHTML + "<label for='tag'>Tag</label>: " + tag[i].firstChild.nodeValue + "<br />";
			if(status[i].firstChild.nodeValue == 0) {
				form.innerHTML = form.innerHTML + "<label for='status'>Status</label>: Belum selesai<br />";
				form.innerHTML = form.innerHTML + "<input type='checkbox' value='1'><i class='status'> Buat selesai?</i></input>";
			}
			else {
				form.innerHTML = form.innerHTML + "<label for='status'>Status</label>: Sudah selesai<br />";
				form.innerHTML = form.innerHTML + "<input type='checkbox' checked='checked' disabled><i class='status'> Buat selesai?</i></input>";
			}
			tugas.appendChild(form);
			if(canerase[i].firstChild.nodeValue == 'true') {
				link = document.createElement('a');
				link.setAttribute('href', 'javascript:deleteTask("' + id[i].firstChild.nodeValue + '")');
				link.innerHTML = '<div class="hapus"></div>';
				tugas.appendChild(link);
			}
			kegiatan.appendChild(tugas);
		}
	}
}

function deleteTask(id) {
	window.xmlhttp = getXmlHttpRequest();
	if(!window.xmlhttp)
		return;
	window.xmlhttp.open('POST', 'deletetask.php', true);
	var query = 'id=' + id;
	window.xmlhttp.onreadystatechange = function() {
		if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
			var response = window.xmlhttp.responseText;
			if(response == '1')
				alert('Tugas berhasil dihapus');
			else
				alert('Tugas gagal dihapus');
			window.location.replace('home.php');
		}
	};
	window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	window.xmlhttp.send(query);
}

function selectCategory(id) {
	document.getElementById('addtask').innerHTML = 'Add new task...';
	window.xmlhttp = getXmlHttpRequest();
	if(!window.xmlhttp)
		return;
	window.xmlhttp.open('POST', 'selectcategory.php', true);
	var query = 'id=' + id;
	window.xmlhttp.onreadystatechange = function() {
		if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
			var response = window.xmlhttp.responseXML;
			var kegiatan = document.getElementById('kegiatan');
			var tugas = document.getElementsByName('task');
			for(var i = 0; i < tugas.length; i++)
				kegiatan.removeChild(tugas[i]);
			var id = response.getElementsByTagName('id');
			var nama = response.getElementsByTagName('nama');
			var deadline = response.getElementsByTagName('deadline');
			var status = response.getElementsByTagName('status');
			var tag = response.getElementsByTagName('tag');
			var canerase = response.getElementsByTagName('canerase');
			for(var i=0; i < nama.length; i++) {
				var tugas = document.createElement('li');
				tugas.name = 'task';
				var link = document.createElement('a');
				link.setAttribute('class', 'list');
				link.setAttribute('href', 'taskdetails.php?id=' + encodeURIComponent(id[i].firstChild.nodeValue));
				link.innerHTML = nama[i].firstChild.nodeValue;
				tugas.appendChild(link);
				var form = document.createElement('form');
				form.innerHTML = "<label for='deadline'>Deadline</label>: " + deadline[i].firstChild.nodeValue + "<br />";
				form.innerHTML = form.innerHTML + "<label for='tag'>Tag</label>: " + tag[i].firstChild.nodeValue + "<br />";
				if(status[i].firstChild.nodeValue == 0) {
					form.innerHTML = form.innerHTML + "<label for='status'>Status</label>: Belum selesai<br />";
					form.innerHTML = form.innerHTML + "<input type='checkbox' value='1'><i class='status'> Buat selesai?</i></input>";
				}
				else {
					form.innerHTML = form.innerHTML + "<label for='status'>Status</label>: Sudah selesai<br />";
					form.innerHTML = form.innerHTML + "<input type='checkbox' checked='checked' disabled><i class='status'> Buat selesai?</i></input>";
				}
				tugas.appendChild(form);
				if(canerase[i].firstChild.nodeValue == 'true') {
					link = document.createElement('a');
					link.setAttribute('href', 'javascript:deleteTask("' + id[i].firstChild.nodeValue + '")');
					link.innerHTML = '<div class="hapus"></div>';
					tugas.appendChild(link);
				}
				kegiatan.appendChild(tugas);
			}
		}
	};
	window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	window.xmlhttp.send(query);
}

function addCategory() {
	window.xmlhttp = getXmlHttpRequest();
	if(!window.xmlhttp)
		return;
	window.xmlhttp.open('POST', 'addcategory.php', true);
	var query = 'name=' + encodeURIComponent(document.getElementById('namakategori').value) + '&users=' + encodeURIComponent(document.getElementById('daftarpengguna').value);
	window.xmlhttp.onreadystatechange = function() {
		if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
			var response = window.xmlhttp.responseText;
			if(response == '1')
				alert('Kategori berhasil ditambahkan');
			else
				alert('Kategori gagal ditambahkan');
		}
	};
	window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	window.xmlhttp.send(query);
	window.location.replace('home.php');
}	