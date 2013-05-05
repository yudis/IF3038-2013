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

function initDashboard() {
    document.getElementById("addtask").disabled = true;
    window.xmlhttp = getXmlHttpRequest();
    if(!window.xmlhttp)
        return;
    window.xmlhttp.open('POST', 'Dashboard', true);
    window.xmlhttp.onreadystatechange = writeDashboard;
    window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    window.xmlhttp.send();
}

function writeDashboard() {
    if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
        var timeoutID = setTimeout('initDashboard()', 60000);
        var response = window.xmlhttp.responseXML;
        var idcategory = response.getElementsByTagName('idkategori');
        var category = response.getElementsByTagName('kategori');
        var categlist = document.getElementById('categlist');
        categlist.innerHTML = '';
        var link = document.createElement('a');
        link.setAttribute('id', 'addcateg');
        link.setAttribute('href', '#addcategory');
        link.innerHTML = '<i>Add new category...</i>';
        categlist.appendChild(link);
        for(var i=0; i < category.length; i++) {
            var kategori = document.createElement('li');
            kategori.name = 'kategori';
            var kotak = document.createElement('div');
            kotak.className = 'kateg';
            kotak.innerHTML = '<a href=\'javascript:selectCategory("' + idcategory[i].firstChild.nodeValue + '")\'>' + category[i].firstChild.nodeValue + '</a>';
            kotak.innerHTML = kotak.innerHTML + '&nbsp;&nbsp;&nbsp;<a href=\'javascript:deleteCategory("' + idcategory[i].firstChild.nodeValue + '")\'><img class=\'deletecateg\' src=\'image/delete.png\' alt=\'Delete Category\'/></a>';
            kategori.appendChild(kotak);
            categlist.appendChild(kategori);
        }
        var id = response.getElementsByTagName('id');
        var nama = response.getElementsByTagName('nama');
        var deadline = response.getElementsByTagName('deadline');
        var status = response.getElementsByTagName('status');
        var tag = response.getElementsByTagName('tag');
        var canerase = response.getElementsByTagName('canerase');
        var kegiatan = document.getElementById('kegiatan');
        kegiatan.innerHTML = '';
        for(i=0; i < nama.length; i++) {
            var tugas = document.createElement('li');
            tugas.setAttribute('name', 'task');
            link = document.createElement('a');
            link.setAttribute('class', 'list');
            link.setAttribute('href', 'taskdetails.jsp?id=' + encodeURIComponent(id[i].firstChild.nodeValue));
            link.innerHTML = nama[i].firstChild.nodeValue;
            tugas.appendChild(link);
            var form = document.createElement('form');
            form.innerHTML = "<label for='deadline'>Deadline</label>: " + deadline[i].firstChild.nodeValue + "<br />";
            form.innerHTML = form.innerHTML + "<label for='tag'>Tag</label>: ";
            if(tag[i].firstChild != null)
                form.innerHTML = form.innerHTML + tag[i].firstChild.nodeValue;
            form.innerHTML += "<br />"
            if(canerase[i].firstChild.nodeValue == 'true') {
                if(status[i].firstChild.nodeValue == 0) {
                    form.innerHTML = form.innerHTML + "<label for='status'>Status</label>: <span id='task" + id[i].firstChild.nodeValue + "'>Belum selesai</span><br />";
                    form.innerHTML = form.innerHTML + "<input type='checkbox' value='1' onchange='changeStatus(\"" + id[i].firstChild.nodeValue + "\")'><i class='status'> Buat selesai?</i></input>";
                }
                else {
                    form.innerHTML = form.innerHTML + "<label for='status'>Status</label>: <span id='task" + id[i].firstChild.nodeValue + "'>Sudah selesai</span><br />";
                    form.innerHTML = form.innerHTML + "<input type='checkbox' checked='checked' onchange='changeStatus(\"" + id[i].firstChild.nodeValue + "\")'><i class='status'> Buat selesai?</i></input>";
                }
                tugas.appendChild(form);
                link = document.createElement('a');
                link.setAttribute('href', 'javascript:deleteTask("' + id[i].firstChild.nodeValue + '")');
                link.innerHTML = '<img src="image/deletetask.png" class="hapus" alt="Delete Task"/>';
                tugas.appendChild(link);
            }
            else {
                if(status[i].firstChild.nodeValue == 0) {
                    form.innerHTML = form.innerHTML + "<label for='status'>Status</label>: <span id='task" + id[i].firstChild.nodeValue + "'>Belum selesai</span><br />";
                    form.innerHTML = form.innerHTML + "<input type='checkbox' value='1' onchange='changeStatus(\"" + id[i].firstChild.nodeValue + "\")' disabled><i class='status'> Buat selesai?</i></input>";
                }
                else {
                    form.innerHTML = form.innerHTML + "<label for='status'>Status</label>: <span id='task" + id[i].firstChild.nodeValue + "'>Sudah selesai</span><br />";
                    form.innerHTML = form.innerHTML + "<input type='checkbox' checked='checked' onchange='changeStatus(\"" + id[i].firstChild.nodeValue + "\")' disabled><i class='status'> Buat selesai?</i></input>";
                }
                tugas.appendChild(form);
            }
            kegiatan.appendChild(tugas);
        }
    }
}

function changeStatus(id) {
    window.xmlhttp = getXmlHttpRequest();
    if(!window.xmlhttp)
        return;
    window.xmlhttp.open('POST', 'ChangeTaskStatus', true);
    var query = 'id=' + id;
    window.xmlhttp.onreadystatechange = function() {
        if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
            var response = window.xmlhttp.responseXML;
            response = response.getElementsByTagName("status");
            response = response[0].firstChild.nodeValue;
            if(response == '1')
                document.getElementById("task" + id).innerHTML = "Belum selesai";
            else
                document.getElementById("task" + id).innerHTML = "Sudah selesai";
        }
    };
    window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    window.xmlhttp.send(query);
}

function deleteTask(id) {
    window.xmlhttp = getXmlHttpRequest();
    if(!window.xmlhttp)
        return;
    window.xmlhttp.open('POST', 'DeleteTask', true);
    var query = 'id=' + id;
    window.xmlhttp.onreadystatechange = function() {
        if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
            var response = window.xmlhttp.responseXML;
            response = response.getElementsByTagName("status");
            response = response[0].firstChild.nodeValue;
            if(response == '1')
                alert('Tugas berhasil dihapus');
            else
                alert('Tugas gagal dihapus');
            window.location.replace('home.jsp');
        }
    };
    window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    window.xmlhttp.send(query);
}

function selectCategory(id) {
    document.getElementById('addtask').disabled = false;
    document.getElementById('addtask').onclick = function() {
        window.location.replace('maketask.jsp?idkategori=' + id);
    }
    window.xmlhttp = getXmlHttpRequest();
    if(!window.xmlhttp)
        return;
    window.xmlhttp.open('POST', 'SelectCategory', true);
    var query = 'id=' + id;
    window.xmlhttp.onreadystatechange = function() {
        if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
            var response = window.xmlhttp.responseXML;
            var kegiatan = document.getElementById('kegiatan');
            kegiatan.innerHTML = '';
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
                link.setAttribute('href', 'taskdetails.jsp?id=' + encodeURIComponent(id[i].firstChild.nodeValue));
                link.innerHTML = nama[i].firstChild.nodeValue;
                tugas.appendChild(link);
                var form = document.createElement('form');
                form.innerHTML = "<label for='deadline'>Deadline</label>: " + deadline[i].firstChild.nodeValue + "<br />";
                form.innerHTML = form.innerHTML + "<label for='tag'>Tag</label>: ";
                if(tag[i].firstChild != null)
                    form.innerHTML = form.innerHTML + tag[i].firstChild.nodeValue
                form.innerHTML += "<br />";
                if(canerase[i].firstChild.nodeValue == 'true') {
                    if(status[i].firstChild.nodeValue == 0) {
                        form.innerHTML = form.innerHTML + "<label for='status'>Status</label>: <span id='task" + id[i].firstChild.nodeValue + "'>Belum selesai</span><br />";
                        form.innerHTML = form.innerHTML + "<input type='checkbox' value='1' onchange='changeStatus(\"" + id[i].firstChild.nodeValue + "\")'><i class='status'> Buat selesai?</i></input>";
                    }
                    else {
                        form.innerHTML = form.innerHTML + "<label for='status'>Status</label>: <span id='task" + id[i].firstChild.nodeValue + "'>Sudah selesai</span><br />";
                        form.innerHTML = form.innerHTML + "<input type='checkbox' checked='checked' onchange='changeStatus(\"" + id[i].firstChild.nodeValue + "\")'><i class='status'> Buat selesai?</i></input>";
                    }
                    tugas.appendChild(form);
                    link = document.createElement('a');
                    link.setAttribute('href', 'javascript:deleteTask("' + id[i].firstChild.nodeValue + '")');
                    link.innerHTML = '<img src="image/deletetask.png" class="hapus" alt="Delete Task"/>';
                    tugas.appendChild(link);
                }
                else {
                    if(status[i].firstChild.nodeValue == 0) {
                        form.innerHTML = form.innerHTML + "<label for='status'>Status</label>: <span id='task" + id[i].firstChild.nodeValue + "'>Belum selesai</span><br />";
                        form.innerHTML = form.innerHTML + "<input type='checkbox' value='1' onchange='changeStatus(\"" + id[i].firstChild.nodeValue + "\")' disabled><i class='status'> Buat selesai?</i></input>";
                    }
                    else {
                        form.innerHTML = form.innerHTML + "<label for='status'>Status</label>: <span id='task" + id[i].firstChild.nodeValue + "'>Sudah selesai</span><br />";
                        form.innerHTML = form.innerHTML + "<input type='checkbox' checked='checked' onchange='changeStatus(\"" + id[i].firstChild.nodeValue + "\")' disabled><i class='status'> Buat selesai?</i></input>";
                    }
                    tugas.appendChild(form);
                }
                kegiatan.appendChild(tugas);
            }
        }
    };
    window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    window.xmlhttp.send(query);
}

function addCategory() {
    if(document.getElementById('namakategori').value != "" && document.getElementById('daftarpengguna').value != "") {
        window.xmlhttp = getXmlHttpRequest();
        if(!window.xmlhttp)
            return;
        window.xmlhttp.open('POST', 'AddCategory', true);
        var query = 'name=' + encodeURIComponent(document.getElementById('namakategori').value) + '&users=' + encodeURIComponent(document.getElementById('daftarpengguna').value);
        window.xmlhttp.onreadystatechange = function() {
            if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
                var response = window.xmlhttp.responseXML;
                response = response.getElementsByTagName("idkategori");
                response = response[0].firstChild.nodeValue;
                if(response.length > 2)
                    alert('Kategori gagal ditambahkan, kemungkinan karena nama pengguna belum ada');
                else {
                    alert('Kategori berhasil ditambahkan');
                    window.location.replace('home.jsp');
                }
            }
        };
        window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        window.xmlhttp.send(query);
    }
}

function deleteCategory(id) {
    window.xmlhttp = getXmlHttpRequest();
    if(!window.xmlhttp)
        return;
    window.xmlhttp.open('POST', 'DeleteCategory', true);
    var query = 'id=' + id;
    window.xmlhttp.onreadystatechange = function() {
        if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
            var response = window.xmlhttp.responseXML;
            response = response.getElementsByTagName("status");
            response = response[0].firstChild.nodeValue;
            if(response == '1') {
                alert('Kategori berhasil dihapus');
                window.location.replace('home.jsp');
            }
            else
                alert('Kategori gagal dihapus');
        }
    };
    window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    window.xmlhttp.send(query);
    
}