/*
 * 
 */
var IDTugas;
var tagsTugas = new Array();
var assigneesTugas = new Array();
var changeMade = false;

function initialize()
{
	if (typeof(Storage) !== 'undefined')
	{
		if (localStorage.session)
		{
			onload();
		}
		else
		{
			window.location = "index.php";
		}
	}
}

function onload() {
    var id = getQueryParameter('id');
	IDTugas = decodeURIComponent(id);
	
	if (id != null)
	{
		getTaskName(decodeURIComponent(id));
		getStatus(decodeURIComponent(id))
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
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("komentar").innerHTML = xmlhttp.responseText;
		}
	}
	xmlhttp.open("GET","getcomments.php?id="+id+"&user="+username,true);
	xmlhttp.send();
}

function setCommentCount()
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
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("commentlabel").innerHTML += " (sebanyak " + xmlhttp.responseText + " komentar)";
		}
	}
	xmlhttp.open("GET","getcommentinfo.php?id="+IDTugas+"&type=1",true);
	xmlhttp.send();
}

function getTaskName(id)
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
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var taskname = xmlhttp.responseText;
			document.getElementById('namaTugas').innerHTML = taskname;
		}
	}
	xmlhttp.open("GET","gettaskinfo.php?type=taskname&id="+id,true);
	xmlhttp.send();
}

function getStatus(id)
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
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var status = xmlhttp.responseText;
			if (status == "1")
			{
				document.getElementById('checkstatus').checked = true;
			}
			else
			{
				document.getElementById('checkstatus').checked = false;
			}
		}
	}
	xmlhttp.open("GET","gettaskinfo.php?type=status&id="+id,true);
	xmlhttp.send();
}

function getDeadline(id)
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
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var deadline = xmlhttp.responseText;
			document.getElementById('deadlineDisplayDiv').innerHTML = deadline;
		}
	}
	xmlhttp.open("GET","gettaskinfo.php?type=deadline&id="+id,true);
	xmlhttp.send();
}

function setAttachment(id)
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
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var attachment = xmlhttp.responseText;
			var arrattach = attachment.split(" ");
			var links = document.getElementById("links");
			var imagenvideo = document.getElementById("imagenvideo");
			for (var i = 0; i < arrattach.length; i++)
			{
				var ext = getExtension(arrattach[i]);
				if (ext == "jpg" || ext == "jpeg" || ext == "png" || ext == "gif" || ext == "bmp")
				{
					imagenvideo.innerHTML = imagenvideo.innerHTML + "<img src='" + arrattach[i] + "' alt='image' height='300' width='300'>";
				}
				else if (ext == "mp4" || ext == "webm" || ext == "ogg")
				{
					imagenvideo.innerHTML = imagenvideo.innerHTML + "<video height='240' width='320' controls> \n <source src='" + arrattach[i] + "' type='video/mp4'> \n Your browser does not support video tag. \n </video>";
				}
				else
				{
					links.innerHTML = links.innerHTML + "<a href='" + arrattach[i] + "' target='_blank'>" + getFileName(arrattach[i]) + "</a></br>";
				}
			}
		}
	}
	xmlhttp.open("GET","gettaskinfo.php?type=attach&id="+id,true);
	xmlhttp.send();
}

function setAssignee(id)
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
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var assignee = xmlhttp.responseText;
			var arrayassignee = assignee.split(",");
			var assignbox = document.getElementById("assignee");
			for (var i = 0; i < arrayassignee.length; i++)
			{
				assignbox.value = arrayassignee[i];
				addAssignees();
			}
		}
		writeAssignees();
	}
	xmlhttp.open("GET","gettaskinfo.php?type=assignee&id="+id,true);
	xmlhttp.send();
}

function setTags(id)
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
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("tags").value = xmlhttp.responseText;
			saveTags();
		}
	}
	xmlhttp.open("GET","gettaskinfo.php?type=tag&id="+id,true);
	xmlhttp.send();
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
	var xmlhttp;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	var now = new Date();
	var username = localStorage.session;
	var id_komentar = "COM-" + username + now.toUTCString;
	var date = "" + now.getFullYear() + "-" + now.getMonth() + "-" + now.getDate() + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
	var comment = document.getElementById('txtKomentar').value;
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		}
	}
	xmlhttp.open("GET","addcomment.php?id="+id_komentar+"&user="+username+"&date="+date+"&tugas="+IDTugas+"&comment="+comment,true);
	xmlhttp.send();

    //var komentar_div = document.getElementById("komentar");
	
    //komentar_div.innerHTML += "<b>" + username + "</b> - " + now.toUTCString() + "<hr />" + document.getElementById('txtKomentar').value + "<br /><br />";
    
    return false;
}

function deletekomentar(comment_id)
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
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		}
	}
	xmlhttp.open("GET","deletecomment.php?id="+comment_id,true);
	xmlhttp.send();
	
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
    
    if (newAssignee.value == "") {
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
	if (document.getElementById("checkstatus").checked == true)
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
	var xmlhttp;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	var stringStatus;
	if (document.getElementById("tags").checked == true)
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
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		}
	}
	xmlhttp.open("GET","settaskinfo.php?id="+id+"&status="+stringStatus+"&deadline="+stringDate+"&assignee="+stringAssignee+"&tags="+stringTags,true);
	xmlhttp.send();
}

function saveTugas() {
	if (changeMade == false)
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
		//masukkan data ke dalam database
		//saveToDatabase();
		
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
