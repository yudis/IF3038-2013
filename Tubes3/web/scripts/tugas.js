/*
 * 
 */
var IDTugas;
var tagsTugas = new Array();
var assigneesTugas = new Array();
var changeMade = false;

function initializetugas()
{
	if (typeof(Storage) !== 'undefined')
	{
		if (localStorage.session)
		{
			onload();
			var innerhtml = "<a href='profile.jsp'><img src='avatar/"+localStorage.session+".jpg' alt='Profile page' width='50' height='50'><br/>Hi, "+localStorage.session+"!</a>";
			document.getElementById("dashboardlink").innerHTML = "<a href='dashboard.jsp'>Dashboard</a>";
			document.getElementById("profil").innerHTML = innerhtml;
		}
		else
		{
			window.location = "index.jsp";
		}
	}
}

function onload() {
    var id = getQueryParameter('id');
	IDTugas = decodeURIComponent(id);
	
	if (id !== null)
	{
		getTaskName(decodeURIComponent(id));
		getStatus(decodeURIComponent(id));
		setAttachment(decodeURIComponent(id));
		getDeadline(decodeURIComponent(id));
		document.getElementById('deadline').value = document.getElementById('deadlineDisplayDiv').innerHTML;
		setAssignee(decodeURIComponent(id));
		setTags(decodeURIComponent(id));
		generateComments(id);
		setCommentCount();
	}
}

/*function onload() {
    var name = getQueryParameter('name');
    if (name == null) {
        name = 'Tugas 1';
    }
    document.getElementById('namaTugas').innerHTML = '<b>' + decodeURIComponent(name) + '</b>';
    
    var deadline = getQueryParameter('deadline');
    if (deadline == null) {
        deadline = '2013-02-22';
    }
    document.getElementById('deadlineDisplayDiv').innerHTML = decodeURIComponent(deadline);
    
    var tags = getQueryParameter('tags');
    var temp = tags.split(",");
    tagsTugas.length = 0;
    temp.forEach(function(obj) {
        tagsTugas.push(obj.trim());
    });
    writeTags();
    
    assigneesTugas.push("Benny Wijaya");
    assigneesTugas.push("Florentina");
    writeAssignees();
}*/

function generateComments(id)
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	var username = localStorage.session;
	
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState===4 && xmlhttp.status===200)
		{
			document.getElementById("komentar").innerHTML = xmlhttp.responseText;
		}
	};
	xmlhttp.open("GET","getcomments?id="+id+"&user="+username,true);
	xmlhttp.send();
}

function setCommentCount()
{
	var xmlhttp2;
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
		if (xmlhttp2.readyState===4 && xmlhttp2.status===200)
		{
			document.getElementById("commentlabel").innerHTML += " (sebanyak " + xmlhttp2.responseText + " komentar)";
		}
	};
	xmlhttp2.open("GET","getcommentinfo?id="+IDTugas+"&type=1",true);
	xmlhttp2.send();
}

function getTaskName(id)
{
	var xmlhttp3;
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
		if (xmlhttp3.readyState===4 && xmlhttp3.status===200)
		{
			var taskname = xmlhttp3.responseText;
			document.getElementById('namaTugas').innerHTML = taskname;
		}
	};
	xmlhttp3.open("GET","gettaskinfotaskname?id="+id,true);
	xmlhttp3.send();
}

function getStatus(id)
{
	var xmlhttp4;
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
		if (xmlhttp4.readyState===4 && xmlhttp4.status===200)
		{
			var status = xmlhttp4.responseText;
			if (status === "1")
			{
				document.getElementById('checkstatus').checked = true;
			}
			else
			{
				document.getElementById('checkstatus').checked = false;
			}
		}
	};
	xmlhttp4.open("GET","gettaskinfostatus?id="+id,true);
	xmlhttp4.send();
}

function getDeadline(id)
{
	var xmlhttp5;
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
		if (xmlhttp5.readyState===4 && xmlhttp5.status===200)
		{
			var deadline = xmlhttp5.responseText;
			document.getElementById('deadlineDisplayDiv').innerHTML = deadline;
		}
	};
	xmlhttp5.open("GET","gettaskinfodeadline?id="+id,true);
	xmlhttp5.send();
}

function setAttachment(id)
{
	var xmlhttp6;
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
		if (xmlhttp6.readyState===4 && xmlhttp6.status===200)
		{
			var attachment = xmlhttp6.responseText;
			var arrattach = attachment.split(" ");
			var links = document.getElementById("links");
			var imagenvideo = document.getElementById("imagenvideo");
			for (var i = 0; i < arrattach.length; i++)
			{
				var ext = getExtension(arrattach[i]);
				if (ext === "jpg" || ext === "jpeg" || ext === "png" || ext === "gif" || ext === "bmp")
				{
					imagenvideo.innerHTML = imagenvideo.innerHTML + "<img src='" + arrattach[i] + "' alt='image' height='300' width='300'>";
				}
				else if (ext === "mp4" || ext === "webm" || ext === "ogg")
				{
					imagenvideo.innerHTML = imagenvideo.innerHTML + "<video height='240' width='320' controls> \n <source src='" + arrattach[i] + "' type='video/mp4'> \n Your browser does not support video tag. \n </video>";
				}
				else
				{
					links.innerHTML = links.innerHTML + "<a href='" + arrattach[i] + "' target='_blank'>" + getFileName(arrattach[i]) + "</a></br>";
				}
			}
		}
	};
	xmlhttp6.open("GET","gettaskinfoattach?id="+id,true);
	xmlhttp6.send();
}

function setAssignee(id)
{
	var xmlhttp7;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp7=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp7=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp7.onreadystatechange=function()
	{
		if (xmlhttp7.readyState===4 && xmlhttp7.status===200)
		{
			var assignee = xmlhttp7.responseText;
			var arrayassignee = assignee.split(",");
			var assignbox = document.getElementById("assignee");
			for (var i = 0; i < arrayassignee.length; i++)
			{
				assignbox.value = arrayassignee[i];
				addAssignees();
			}
		}
		writeAssignees();
	};
	xmlhttp7.open("GET","gettaskinfoassignee?id="+id,true);
	xmlhttp7.send();
}

function setTags(id)
{
	var xmlhttp8;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp8=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp8=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp8.onreadystatechange=function()
	{
		if (xmlhttp8.readyState===4 && xmlhttp8.status===200)
		{
			document.getElementById("tags").value = xmlhttp8.responseText;
			saveTags();
		}
	};
	xmlhttp8.open("GET","gettaskinfotag?id="+id,true);
	xmlhttp8.send();
}

function getExtension(link)
{
	var linked = link.split(".");
	var ext = linked[linked.length-1];
	return ext;
}

function getFileName(link)
{
	var linked = link.split("\\");
	var ext = linked[linked.length-1];
	return ext;
}

function addKomentar() {
	var xmlhttp9;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp9=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp9=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	var now = new Date();
	var username = localStorage.session;
	var id_komentar = "COM-" + username + now.toUTCString();
	var date = "" + now.getFullYear() + "-" + now.getMonth() + "-" + now.getDate() + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
	var comment = document.getElementById('txtKomentar').value;
        
	xmlhttp9.onreadystatechange=function()
	{
		if (xmlhttp9.readyState===4 && xmlhttp9.status===200)
		{
                    window.location = "tugas.jsp?id=" + IDTugas;
		}
	};
	xmlhttp9.open("GET","addcomment?id="+id_komentar+"&user="+username+"&date="+date+"&tugas="+IDTugas+"&comment="+comment,true);
	xmlhttp9.send();

    //var komentar_div = document.getElementById("komentar");
	
    //komentar_div.innerHTML += "<b>" + username + "</b> - " + now.toUTCString() + "<hr />" + document.getElementById('txtKomentar').value + "<br /><br />";
    
    return false;
}

function deletekomentar(comment_id)
{
	var xmlhttp10;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp10=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp10=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp10.onreadystatechange=function()
	{
		if (xmlhttp10.readyState===4 && xmlhttp10.status===200)
		{
		}
	};
	xmlhttp10.open("GET","deletecomment?id="+comment_id,true);
	xmlhttp10.send();
	
	return false;
}

/*
function addKomentar() {
    var now = new Date();
    var komentar_div = document.getElementById("komentar");
	var username = localStorage.session;
	var id_komentar = "COM-" + username + now.toUTCString;
	
    komentar_div.innerHTML += "<b>" + username + "</b> - " + now.toUTCString() + "<hr />" + document.getElementById('txtKomentar').value + "<br /><br />";
    
    return false;
}*/

function writeTags() {
    var tagsList = document.getElementById("tagsList");
    var tags = document.getElementById("tags");
    tags.value = '';
    
    tagsList.innerHTML = '';
    for (var i=0; i<tagsTugas.length; i++) {
        tagsList.innerHTML += "<li>" + tagsTugas[i] + "</li> ";
        
        if (i > 0) {
            tags.value += ', ';
        }
        tags.value += tagsTugas[i];
    }
}

function saveTags() {
    var tags = document.getElementById("tags");
    var temp = tags.value.split(",");
    tagsTugas.length = 0;
    temp.forEach(function(obj) {
        tagsTugas.push(obj.trim());
    });
    writeTags();
    
    return false;
}

function writeAssignees() {
    var assigneesList = document.getElementById("assigneesList");
    
    assigneesList.innerHTML = '';
    for (var i=0; i<assigneesTugas.length; i++) {
        assigneesList.innerHTML += "<li>" + assigneesTugas[i] + "</li> ";
    }
}

function addAssignees() {
    var newAssignee = document.getElementById("assignee");
    var assigneesList = document.getElementById("assigneesList");
    
    if (newAssignee.value === "") {
        return false;
    }
    
    assigneesTugas.push(newAssignee.value);
    assigneesList.innerHTML += "<li>" + newAssignee.value + "</li> ";
    newAssignee.value = "";
    
	changeMadeTrue();
	
    return false;
}

function changeCheckBox()
{
	if (document.getElementById("checkstatus").checked === true)
	{
		document.getElementById("statustext").innerHTML = "Selesai";
	}
	else
	{
		document.getElementById("statustext").innerHTML = "Belum Selesai";
	}
	changeMadeTrue();
}

function changeMadeTrue()
{
	changeMade = true;
}

function saveToDatabase()
{
	var xmlhttp11;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp11=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp11=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	var stringStatus;
	if (document.getElementById("tags").checked === true)
	{
		stringStatus = "1";
	}
	else
	{
		stringStatus = "0";
	}
	var stringDate = document.getElementById("deadline").value;
	var stringAssignee = "";
	for (var i = 0; i < assigneesTugas.length; i++)
	{
		stringAssignee += assigneeTugas[i];
		if (i+1 < assigneeTugas.length)
		{
			stringAssignee += ",";
		}
	}
	var stringTags = document.getElementById("tags").value;
	
	xmlhttp11.onreadystatechange=function()
	{
		if (xmlhttp11.readyState===4 && xmlhttp11.status===200)
		{
		}
	};
	xmlhttp11.open("GET","settaskinfo?id="+id+"&status="+stringStatus+"&deadline="+stringDate+"&assignee="+stringAssignee+"&tags="+stringTags,true);
	xmlhttp11.send();
}

function saveTugas() {
	if (changeMade === false)
	{
		document.getElementById("tagsEditDiv").style.display = "none";
		document.getElementById("assigneeEditDiv").style.display = "none";
		document.getElementById("deadlineEditDiv").style.display = "none";
		document.getElementById("doneButton").style.display = "none";
		document.getElementById("deadlineDisplayDiv").style.display = "block";
		document.getElementById("tagsDisplayDiv").style.display = "block";
		document.getElementById("editButton").style.display = "block";
		document.getElementById("checkstatus").disabled = true;
		
		var deadlineDisplay = document.getElementById("deadlineDisplayDiv");
		var deadlineTextBox = document.getElementById("deadline");
		deadlineDisplay.innerHTML = deadlineTextBox.value;
		
		saveTags();
		
		changeMade = false;
    }
	else
	{
		
		
		document.getElementById("tagsEditDiv").style.display = "none";
		document.getElementById("assigneeEditDiv").style.display = "none";
		document.getElementById("deadlineEditDiv").style.display = "none";
		document.getElementById("doneButton").style.display = "none";
		document.getElementById("deadlineDisplayDiv").style.display = "block";
		document.getElementById("tagsDisplayDiv").style.display = "block";
		document.getElementById("editButton").style.display = "block";
		document.getElementById("checkstatus").disabled = true;
		
		var deadlineDisplay = document.getElementById("deadlineDisplayDiv");
		var deadlineTextBox = document.getElementById("deadline");
		deadlineDisplay.innerHTML = deadlineTextBox.value;
		//masukkan data ke dalam database
		saveToDatabase();
                alert("tesssssss");
		saveTags();
		
		changeMade = false;
	}
	
    return false;
}

function editTugas() {
    document.getElementById("tagsEditDiv").style.display = "block";
    document.getElementById("assigneeEditDiv").style.display = "block";
    document.getElementById("deadlineEditDiv").style.display = "block";
    document.getElementById("doneButton").style.display = "block";
    document.getElementById("deadlineDisplayDiv").style.display = "none";
    document.getElementById("tagsDisplayDiv").style.display = "none";
    document.getElementById("editButton").style.display = "none";
    document.getElementById("checkstatus").disabled = false;
	
    return false;
}
