var assignee = [];

function initialize() {
	if(typeof(Storage)!=="undefined") {
		if (localStorage.session) {
                        listKategori();
                        listTask("all");
			loadUser();
			loadThumbnail();
		}
		else {
			window.location = "index.php";
		}
	}
	else {
		alert("Local storage not supported");
	}
}

function EditStatus(bool,taskid) {
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp6=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp6=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp6.onreadystatechange=function()
	{
		if (xmlhttp6.readyState==4 && xmlhttp6.status==200)
		{
			window.location = "dashboard.jsp";
		}
	}
	
	xmlhttp6.open("GET","editstatustugas?t="+taskid+"&s="+bool,true);
	xmlhttp6.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp6.send();
}

function signout() {
	localStorage.clear();
}

function loadThumbnail() {
	var innerhtml = "<a href='profile.jsp'><img src='avatar/"+localStorage.session+".jpg' alt='Profile page' width='50' height='50'><br/>Hi, "+localStorage.session+"!</a>";
	
	document.getElementById("profil").innerHTML = innerhtml;
}

function deleteTask(taskid) {
	if (confirm('Are you sure you want to delete this task?')) {
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp5=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp5=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp5.onreadystatechange=function()
		{
			if (xmlhttp5.readyState==4 && xmlhttp5.status==200)
			{
				window.location = "dashboard.php?uname="+localStorage.session+"&cat=all";
			}
		}
		
		xmlhttp5.open("GET","deletetask?t="+taskid,true);
		xmlhttp5.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlhttp5.send();
	}
}

function loadUser() {
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp2=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp2=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp2.onreadystatechange=function()
	{
		if (xmlhttp2.readyState==4 && xmlhttp2.status==200)
		{
			document.getElementById("datalistuser").innerHTML = xmlhttp2.responseText;
		}
	}
	
	xmlhttp2.open("GET","loaduser",true);
	xmlhttp2.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp2.send();
}

function listKategori() {
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("sidebar").innerHTML = xmlhttp.responseText;
		}
	}
	
	xmlhttp.open("GET","listkategori?uname="+localStorage.session,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send();
}

function listTask(catid) {
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp4=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp4=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp4.onreadystatechange=function()
	{
		if (xmlhttp4.readyState==4 && xmlhttp4.status==200)
		{
			document.getElementById("listTugas").innerHTML = xmlhttp4.responseText;
		}
	}
	
	xmlhttp4.open("GET","listtask?uname="+localStorage.session+"&cat="+catid,true);
	xmlhttp4.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp4.send();
}

function NewKategori() {
    var element = document.getElementById('txtNewKategori');
    var listassignee = "";
	var i = 0;
	while (i < assignee.length) {
		listassignee = listassignee+assignee[i]+"~";
		i++;
	}
    if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp3=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp3=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp3.onreadystatechange=function()
	{
		if (xmlhttp3.readyState==4 && xmlhttp3.status==200)
		{
			window.location = "lol.html";
		}
	}
	
	xmlhttp3.open("GET","addkategori?cat="+element.value+"&n="+assignee.length+"&a="+listassignee,true);
	xmlhttp3.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp3.send();
    
    return false;
}

function NewTask() {
    if(typeof(Storage)!=="undefined") {
		if (localStorage.kategori) {
			window.location = "createtugas.jsp?uname="+localStorage.session+"&cat="+localStorage.kategori;
		}
		else {
			alert("Please select the categori first, by click on left sidebar.", "Todolist");
		}
	}
	else {
		alert("Local storage not supported");
	}
}

function DeleteCategory() {
	if(typeof(Storage)!=="undefined") {
		if (localStorage.kategori) {
			if (confirm('Are you sure you want to delete this category?')) {
				window.location = "deletecategory.php?uname="+localStorage.session+"&cat="+localStorage.kategori;
			}
		}
		else {
			alert("Please select the categori first, by click on left sidebar.", "Todolist");
		}
	}
	else {
		alert("Local storage not supported");
	}
}

function RemoveKategoriFilter(elmt) { 
    if(typeof(Storage)!=="undefined") {
		localStorage.kategori.clear();
	}
	else {
		alert("Local storage not supported");
	}
    return true;
}

function KategoriSelected(elmt) {
    if(typeof(Storage)!=="undefined") {
		localStorage.kategori = elmt.id;
                listTask(elmt.id);
	}
	else {
		alert("Local storage not supported");
	}
    return true;
}

function addAssignee() {
	var element = document.getElementById('assignee');
	var element2 = document.getElementById('assigneelist');
	var found = false;
	var i = 0;
	
	while (i<assignee.length && !found) {
		if (assignee[i]==element.value)
			found = true;
		i++;
	}
	if (!found) {
		assignee[assignee.length]=element.value;
		element2.innerHTML+="<li>"+element.value+"</li> ";
		found = false;
                element.value="";
	}
}