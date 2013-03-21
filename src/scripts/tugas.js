/*
 * 
 */
var tagsTugas = new Array();
var assigneesTugas = new Array();
var assigneeArr="";
var detilTugas;

function onload(id_tugas) {
    ajax_get('./ajax/detiltugas.php?id_tugas=' + id_tugas, function(xhr) {
        detilTugas = JSON.parse(xhr.responseText);
        updateContent(true);
    })
}

function updateContent(updateAttachment) {
    if (detilTugas.responseStatus == 200) {
        var divNamaTugas = document.getElementById("namaTugas");
        var divStatusTugas = document.getElementById("statusTugas");
        var divAttachmentTugas = document.getElementById("attachmentTugas");
        var divDeadlineTugas = document.getElementById("deadlineDisplayDiv");
        var txtDeadlineTugas = document.getElementById("deadline");
        var divAssigneesTugas = document.getElementById("assigneesList");
        var divKomentarTugas = document.getElementById("komentar");

        divNamaTugas.innerHTML = detilTugas.nama;
        if (detilTugas.status == 0)
        {
            divStatusTugas.innerHTML = '<strong>Belum selesai</status>';
        }
        else
        {
            divStatusTugas.innerHTML = '<strong>Selesai</status>';
        }

        if (updateAttachment) {
            divAttachmentTugas.innerHTML = '';
            detilTugas.attachment.forEach(function(entry) {
                if (entry.type == "image")
                {
                    divAttachmentTugas.innerHTML += '<div><img src="./files/' + entry.filename + '" alt="' + entry.name + '" /></div>';
                }
                else if (entry.type == "video")
                {
                    divAttachmentTugas.innerHTML += '<div><video width="320" height="240" controls><source src="./files/' + entry.filename + '" /><div><a href="./files/' + entry.filename + '" target="_blank">' + entry.name + '</a></div></video></div>';
                }
                else
                {
                    divAttachmentTugas.innerHTML += '<div><a href="./files/' + entry.filename + '" target="_blank">' + entry.name + '</a></div>'
                }
            });
        }

        divDeadlineTugas.innerHTML = detilTugas.tgl_deadline;
        txtDeadlineTugas.value = detilTugas.tgl_deadline;

        divAssigneesTugas.innerHTML = '';
        detilTugas.assignees.forEach(function(entry) {
            divAssigneesTugas.innerHTML += '<li><a href="./profile.php?u=' + entry.username + '">'  + entry.full_name +  ' (' + entry.username + ')</a> | <a class="red" href="#" onclick="removeAssignee(' + entry.username + '); return false;">&times;</a></li> ';
        });

        writeTags();

        divKomentarTugas.innerHTML = '';
        detilTugas.comments.comments.forEach(function(entry) {
            divKomentarTugas.innerHTML += '<strong><img src="./images/avatars/' + entry.avatar + '" alt="' + entry.full_name + '" width="32" height="32" /> ' + entry.full_name + ' (<a href="./profile.php?u=' + entry.user + '">' + entry.user + '</a>)</strong> ' + entry.time; 
            divKomentarTugas.innerHTML += '<hr />' + entry.content + '<br /><br />';
        });
    } else if (detilTugas.responseStatus == 204) {
        // do nothing, already updated
    } else {
        alert(detilTugas.message);
    }
}

function addKomentar() {
    var now = new Date();
    var komentar_div = document.getElementById("komentar");
    komentar_div.innerHTML += "<b>WhoAmI</b> - " + now.toString("dd MMMM yyyy hh:mm") + "<hr />" + document.getElementById('txtKomentar').value + "<br /><br />";
    
    return false;
}

function writeTags() {
    var tagsList = document.getElementById("tagsList");
    var tags = document.getElementById("tags");
    tags.value = '';
    
    tagsList.innerHTML = '';
    if (detilTugas.tag.length > 0)
    { 
        tagsList.innerHTML += "<li>" + detilTugas.tag[0] + "</li> ";
        tags.value += detilTugas.tag[0];

        for (var i=1; i<detilTugas.tag.length; i++) {
            tagsList.innerHTML += "<li>" + detilTugas.tag[i] + "</li> ";
            tags.value += ', ' + detilTugas.tag[i];
        }
    }
}

function saveTags() {
    var tags = document.getElementById("tags");
    var temp = tags.value.split(",");
    detilTugas.tag.length = 0;
    temp.forEach(function(obj) {
        obj = obj.trim();
        if (obj != '') {
            detilTugas.tag.push(obj);
        }
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
        alert("Nama harus dimasukkan");
        return false;
    }
    document.getElementById("assigneeI").value+=newAssignee.value+",";
    assigneesList.innerHTML += "<li>" + newAssignee.value + "</li> ";
    newAssignee.value = "";
	return false;
}

function saveTugas() {
    document.getElementById("tagsEditDiv").style.display = "none";
    document.getElementById("assigneeEditDiv").style.display = "none";
    document.getElementById("deadlineEditDiv").style.display = "none";
    document.getElementById("doneButton").style.display = "none";
    document.getElementById("deadlineDisplayDiv").style.display = "block";
    document.getElementById("tagsDisplayDiv").style.display = "block";
    document.getElementById("editButton").style.display = "block";
    
    var deadlineDisplay = document.getElementById("deadlineDisplayDiv");
    var deadlineTextBox = document.getElementById("deadline");
    deadlineDisplay.innerHTML = deadlineTextBox.value;
    
    saveTags();
    
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
    
    return false;
}

