var tagsTugas = new Array();
var assigneesTugas = new Array();
var assigneeArr = new Array();
var assigneeIndex = 0;
var detilTugas;
var id;
var asTextbox;

function onload(id_tugas) {
    id = id_tugas;
    new nicEditor({iconsPath : './images/nicEditorIcons.gif'}).panelInstance('txtKomentar');
    requestUpdate(id_tugas, 2000, true, 0, 10, false);
}

function requestUpdate(id_tugas, timeout, updateAttachment, startc, countc, forceUpdate) {
    var url = './ajax/detilTugas.php?id_tugas=' + id_tugas + '&startc=' + startc + '&countc=' + countc;
    if (typeof detilTugas != 'undefined' && !forceUpdate) {
        url += '&update=' + detilTugas.responseTime;
    }

    ajax_get(url, function(xhr) {
        var json_obj = JSON.parse(xhr.responseText);
        if (json_obj.responseStatus == 200) {
            detilTugas = json_obj;
            updateContent(updateAttachment);
        }

        if (timeout > 0) {
            var delegate = "requestUpdate(" + id_tugas + ", " + timeout + ", " + false + ", " + startc + ", " + countc + ", " + false + ")";
            // alert(delegate);
            setTimeout(delegate, timeout);
        }
    });
}

function updateContent(updateAttachment) {
    if (detilTugas.responseStatus == 200) {
        var divNamaTugas = document.getElementById("namaTugas");
        var divStatusTugas = document.getElementById("statusTugas");
        var cbStatusTugas = document.getElementById("cbStatus");
        var divAttachmentTugas = document.getElementById("attachmentTugas");
        var divDeadlineTugas = document.getElementById("deadlineDisplayDiv");
        var txtDeadlineTugas = document.getElementById("deadline");
        var divAssigneesTugas = document.getElementById("assigneesList");
        var divKomentarTugas = document.getElementById("komentar");
        var divKomentarStatTugas = document.getElementById("komentarStatistic");
        var selKomentarPageTugas = document.getElementById("komentarPage");

        divNamaTugas.innerHTML = detilTugas.nama;
        if (detilTugas.status == 0)
        {
            cbStatusTugas.checked = false;
            divStatusTugas.innerHTML = '<strong>Belum selesai</status>';
        }
        else
        {
            cbStatusTugas.checked = true;
            divStatusTugas.innerHTML = '<strong>Selesai</status>';
        }

        if (updateAttachment) {
            divAttachmentTugas.innerHTML = '';
            detilTugas.attachment.forEach(function(entry) {
                if (entry.type == "image")
                {
                    divAttachmentTugas.innerHTML += '<div><img src="./attachment.php?file=' + encodeURIComponent(entry.filename) + '&nama=' + encodeURIComponent(entry.name) + '" alt="' + entry.name + '" /></div>';
                }
                else if (entry.type == "video")
                {
                    divAttachmentTugas.innerHTML += '<div><video width="320" height="240" controls><source src="./attachment.php?file=' + encodeURIComponent(entry.filename) + '&nama=' + encodeURIComponent(entry.name) + '" /><div><a href="./attachment.php?file=' + encodeURIComponent(entry.filename) + '&nama=' + encodeURIComponent(entry.name) + '" target="_blank">' + entry.name + '</a></div></video></div>';
                }
                else
                {
                    divAttachmentTugas.innerHTML += '<div><a href="./attachment.php?file=' + encodeURIComponent(entry.filename) + '&nama=' + encodeURIComponent(entry.name) + '" target="_blank">' + entry.name + '</a></div>'
                }
            });
        }

        divDeadlineTugas.innerHTML = detilTugas.tgl_deadline;
        txtDeadlineTugas.value = detilTugas.tgl_deadline;

        divAssigneesTugas.innerHTML = '';
        detilTugas.assignees.forEach(function(entry) {
            divAssigneesTugas.innerHTML += '<li><a href="./profile.php?u=' + entry.username + '">'  + entry.full_name +  ' (' + entry.username + ')</a> | <a class="red" href="#" onclick="removeAssignee(\'' + entry.username + '\'); return false;">&times;</a></li> ';
        });

        writeTags();

        var strKomentar = '';
        detilTugas.comments.comments.forEach(function(entry) {
            strKomentar += '<div class="item"><div class="title"><strong><img src="./images/avatars/' + entry.avatar + '" alt="' + entry.full_name + '" width="32" height="32" /> ' + entry.full_name + ' (<a href="./profile.php?u=' + entry.user + '">' + entry.user + '</a>)</strong> on ' + entry.time;
            if (entry.priviledge == 1) {
                strKomentar += ' (<a href="#" class="red" onclick="removeComment(' + entry.id + '); return false;">Remove</a>)';
            }
            strKomentar += '</div><div class="comment">' + entry.content + '</div></div>';
        });
        divKomentarTugas.innerHTML = strKomentar;

        divKomentarStatTugas.innerHTML = detilTugas.comments.comments.length + '/' + detilTugas.comments.total;
        selKomentarPageTugas.innerHTML = '';
        for (var i = 1, len = Math.ceil(detilTugas.comments.total / detilTugas.comments.count); i <= len; i++) {
            if (((i - 1) * detilTugas.comments.count) == detilTugas.comments.startindex) {
                selKomentarPageTugas.innerHTML += '<option value="' + i + '" selected>' + i + '</option>';
            } else {
                selKomentarPageTugas.innerHTML += '<option value="' + i + '">' + i + '</option>';
            }
        };
    } else if (detilTugas.responseStatus == 204) {
        // do nothing, already updated
    } else {
        alert(detilTugas.message);
    }
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
    
    assigneesTugas.push(newAssignee.value);
	assigneeArr[assigneeIndex]=newAssignee.value;
    assigneesList.innerHTML += "<li>" + newAssignee.value + "</li> ";
    newAssignee.value = "";
	assigneeIndex++;
    
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
    
    if (typeof asTextbox == 'undefined')
    {
        asTextbox = new AutoSuggestControl(document.getElementById("assignee"), new AssigneeSuggestions(id));
    }

    return false;
}

function toggleStatus() {
    var url = "./ajax/updateTugas.php?chstatus&id_tugas=" + detilTugas.id + "&status=" + (detilTugas.status == 0 ? 1 : 0);
    ajax_get(url, function(xhr) {
        var json_obj = JSON.parse(xhr.responseText);
        if (json_obj.responseStatus != 200) {
            alert(json_obj.message);
        } else {
            // do nothing
            // alert("Berhasil");
            requestUpdate(detilTugas.id, -1, false, detilTugas.comments.startindex, detilTugas.comments.count, true);
        }
    });
}

function removeAssignee(username) {
    ajax_get("./ajax/updateTugas.php?removea&id_tugas=" + detilTugas.id + "&username=" + username, function(xhr) {
        var json_obj = JSON.parse(xhr.responseText);
        if (json_obj.responseStatus != 200) {
            alert(json_obj.message);
        } else {
            // do nothing
            // alert("Berhasil");
            requestUpdate(detilTugas.id, -1, false, detilTugas.comments.startindex, detilTugas.comments.count, true);
        }
    });
}

function changeKomentarPage(sel) {
    var value = sel.options[sel.selectedIndex].value;
    requestUpdate(detilTugas.id, -1, false, (value - 1) * detilTugas.comments.count, detilTugas.comments.count, true);
}

function removeComment(id) {
    ajax_get("./ajax/updateTugas.php?removec&id_komentar=" + id, function(xhr) {
        var json_obj = JSON.parse(xhr.responseText);
        if (json_obj.responseStatus != 200) {
            alert(json_obj.message);
        } else {
            // do nothing
            // alert("Berhasil");
            requestUpdate(detilTugas.id, -1, false, detilTugas.comments.startindex, detilTugas.comments.count, true);
        }
    });
}

function addComment() {
    var txtKomentar = nicEditors.findEditor('txtKomentar');
    var qry = 'addc=1&id_tugas=' + encodeURIComponent(detilTugas.id) + '&content=' + encodeURIComponent(txtKomentar.getContent());
    ajax_post("./ajax/updateTugas.php", qry, function(xhr) {
        var json_obj = JSON.parse(xhr.responseText);
        if (json_obj.responseStatus != 200) {
            alert(json_obj.message);
        } else {
            // do nothing
            // alert("Berhasil");
            txtKomentar.setContent('');
            requestUpdate(detilTugas.id, -1, false, detilTugas.comments.startindex, detilTugas.comments.count, true);
        }
    });

    // var txtKomentar = document.getElementById("txtKomentar");
    // var qry = 'addc=1&id_tugas=' + encodeURIComponent(detilTugas.id) + '&content=' + encodeURIComponent(txtKomentar.value);
    // ajax_post("./ajax/updateTugas.php", qry, function(xhr) {
    //     var json_obj = JSON.parse(xhr.responseText);
    //     if (json_obj.responseStatus != 200) {
    //         alert(json_obj.message);
    //     } else {
    //         // do nothing
    //         // alert("Berhasil");
    //         txtKomentar.value = '';
    //         requestUpdate(detilTugas.id, -1, false, detilTugas.comments.startindex, detilTugas.comments.count);
    //     }
    // });
}