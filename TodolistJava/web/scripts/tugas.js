var detilTugas;
var id;

function onload(id_tugas) {
    id = id_tugas;
    new nicEditor({iconsPath : './images/nicEditorIcons.gif'}).panelInstance('txtKomentar');
    requestUpdate(id_tugas, 5000, true, 0, 10, false);
}

function requestUpdate(id_tugas, timeout, updateAttachment, startc, countc, forceUpdate) {
    var url = './ajax/detiltugas?id_tugas=' + id_tugas + '&startc=' + startc + '&countc=' + countc;
    if (typeof detilTugas != 'undefined' && !forceUpdate) {
        url += '&update=' + detilTugas.responseTime + '&priviledge=' + detilTugas.priviledge;
    }

    ajax_get(url, function(xhr) {
        var json_obj = json_parse(xhr.responseText);
        if (json_obj.responseStatus == 200) {
            if (typeof detilTugas != 'undefined' && detilTugas.priviledge != json_obj.priviledge)
            {
                window.location.reload(true);
            }
            else
            {
                detilTugas = json_obj;
                updateContent(updateAttachment);
            }
        } else if (json_obj.responseStatus == 304) {
            // do nothing, already updated
        } else if (json_obj.responseStatus == 205) {            
            // reset content
            window.location.reload(true);
        } else {
            alert(json_obj.message);
            window.location.reload(true);
        }

        if (timeout > 0) {
            var delegate = "requestUpdate(" + id_tugas + ", " + timeout + ", " + false + ", " + startc + ", " + countc + ", " + false + ")";
            // alert(delegate);
            setTimeout(delegate, timeout);
        }
    });
}

function updateContent(updateAttachment) {
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
        divStatusTugas.innerHTML = 'Belum selesai';
    }
    else
    {
        cbStatusTugas.checked = true;
        divStatusTugas.innerHTML = 'Selesai';
    }

    if (updateAttachment) {
        divAttachmentTugas.innerHTML = '';
        detilTugas.attachments.forEach(function(entry) {
            if (entry.type == "image")
            {
                divAttachmentTugas.innerHTML += '<div><img src="./attachment?file=' + encodeURIComponent(entry.filename) + '&nama=' + encodeURIComponent(entry.name) + '" alt="' + entry.name + '" /></div>';
            }
            else if (entry.type == "video")
            {
                divAttachmentTugas.innerHTML += '<div><video width="320" height="240" controls><source src="./attachment?file=' + encodeURIComponent(entry.filename) + '&nama=' + encodeURIComponent(entry.name) + '" /><div><a href="./attachment?file=' + encodeURIComponent(entry.filename) + '&nama=' + encodeURIComponent(entry.name) + '" target="_blank">' + entry.name + '</a></div></video></div>';
            }
            else
            {
                divAttachmentTugas.innerHTML += '<div><a href="./attachment?file=' + encodeURIComponent(entry.filename) + '&nama=' + encodeURIComponent(entry.name) + '" target="_blank">' + entry.name + '</a></div>'
            }
        });
    }

    divDeadlineTugas.innerHTML = detilTugas.tglDeadline;
    txtDeadlineTugas.value = detilTugas.tglDeadline;

    var assigneesTemp = '<li><a href="./profile?u=' + detilTugas.pemilik.username + '">'  + detilTugas.pemilik.fullName +  ' (' + detilTugas.pemilik.username + ')</a></li> ';
    detilTugas.assignees.forEach(function(entry) {
        assigneesTemp += '<li><a href="./profile?u=' + entry.username + '">'  + entry.fullName +  ' (' + entry.username + ')</a>';
        if (detilTugas.priviledge == "creator" || detilTugas.priviledge == "assignee") {
            assigneesTemp += ' | <a class="red" href="#" onclick="removeAssignee(\'' + entry.username + '\'); return false;">&times;</a>';
        }
        assigneesTemp += '</li> ';
    });
    divAssigneesTugas.innerHTML = assigneesTemp;

    writeTags();

    var strKomentar = '';
    detilTugas.comments.comments.forEach(function(entry) {
        strKomentar += '<div class="item"><div class="title"><strong><img src="./images/avatars/' + entry.user.avatar + '" alt="' + entry.user.fullName + '" width="32" height="32" /> ' + entry.user.fullName + ' (<a href="./profile?u=' + entry.user.username + '">' + entry.user.username + '</a>)</strong> on ' + entry.time;
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
}

function writeTags() {
    var tagsList = document.getElementById("tagsList");   
    var tagsTemp = '';
    for (var i=0; i < detilTugas.tags.length; i++) {
        tagsTemp += '<li>' + detilTugas.tags[i];
        if (detilTugas.priviledge == "creator" || detilTugas.priviledge == "assignee") {
            tagsTemp += ' | <a href="#" class="red" onclick="removeTag(\'' + detilTugas.tags[i] + '\'); return false;">&times;</a>';
        }
        tagsTemp += '</li> ';
    } 
    tagsList.innerHTML = tagsTemp;
}

function addAssignee() {
    var newAssignee = document.getElementById("assignee");
    
    if (newAssignee.value == "") {
        alert("Nama harus dimasukkan");
        return false;
    }
    
    var url = "./ajax/updatetugas?adda=1&id_tugas=" + detilTugas.id + "&username=" + newAssignee.value;
    ajax_get(url, function(xhr) {
        var json_obj = json_parse(xhr.responseText);
        if (json_obj.responseStatus != 200) {
            alert(json_obj.message);
        } else {
            // do nothing
            // alert("Berhasil");
            newAssignee.value = '';
            requestUpdate(detilTugas.id, -1, false, detilTugas.comments.startindex, detilTugas.comments.count, true);
        }
    });
    
    return false;
}

function saveTugas() {
    document.getElementById("tagsEditDiv").style.visibility = "hidden";
    document.getElementById("assigneeEditDiv").style.display = "none";
    document.getElementById("deadlineEditDiv").style.display = "none";
    document.getElementById("doneButton").style.display = "none";
    document.getElementById("deadlineDisplayDiv").style.display = "block";
    //document.getElementById("tagsDisplayDiv").style.display = "block";
    document.getElementById("editButton").style.display = "inline-block";
    
    var deadlineDisplay = document.getElementById("deadlineDisplayDiv");
    var deadlineTextBox = document.getElementById("deadline");
    if (deadlineTextBox.value == '') {
        deadlineTextBox.value = detilTugas.tgl_deadline;
    } else {
        deadlineDisplay.innerHTML = 'Loading...';
        
        ajax_get("./ajax/updatetugas?updatedeadline=1&id_tugas=" + id + "&deadline=" + deadlineTextBox.value, function(xhr) {
            var json_obj = json_parse(xhr.responseText);
            if (json_obj.responseStatus != 200) {
                alert(json_obj.message);
            } else {
                // do nothing
                // alert("Berhasil");
                requestUpdate(detilTugas.id, -1, false, 0, detilTugas.comments.count, true);
            }
        });
    }
}

function editTugas() {
    document.getElementById("tagsEditDiv").style.visibility = "visible";
    document.getElementById("assigneeEditDiv").style.display = "block";
    document.getElementById("deadlineEditDiv").style.display = "block";
    document.getElementById("doneButton").style.display = "inline-block";
    document.getElementById("deadlineDisplayDiv").style.display = "none";
    //document.getElementById("tagsDisplayDiv").style.display = "none";
    document.getElementById("editButton").style.display = "none";

    return false;
}

function toggleStatus() {
    var url = "./ajax/updatetugas?chstatus=1&id_tugas=" + detilTugas.id + "&status=" + (detilTugas.status == 0 ? true : false);
    ajax_get(url, function(xhr) {
        var json_obj = json_parse(xhr.responseText);
        if (json_obj.responseStatus != 200) {
            alert(json_obj.message);
        } else {
            // do nothing
            // alert("Berhasil");
            requestUpdate(detilTugas.id, -1, false, detilTugas.comments.startindex, detilTugas.comments.count, true);
        }
    });
}

function tagsSave(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode == 188) { // Comma
        var txtTag = document.getElementById("tags");
        var url = "./ajax/updatetugas?addt=1&id_tugas=" + id + "&tag=" + encodeURIComponent(txtTag.value.replace(",", ""));
        txtTag.value = '';
        ajax_get(url, function (xhr) {
            var json_obj = json_parse(xhr.responseText);
            if (json_obj.responseStatus != 200) {
                alert(json_obj.message);
            }
            requestUpdate(detilTugas.id, -1, false, detilTugas.comments.startindex, detilTugas.comments.count, true);
        });
    } else {
        ajax_get("./ajax/updatetugas?suggestt=1&id_tugas=" + id + "&start=" + encodeURIComponent(document.getElementById("tags").value), function (xhr) {
            var json_obj = json_parse(xhr.responseText);
            if (json_obj.responseStatus == 200) {
                var divTagSug = document.getElementById('tagsSug');
                divTagSug.innerHTML = '';
                json_obj.suggestedTags.forEach(function(entry){
                    divTagSug.innerHTML += '<option value="' + entry.tags + '">' + entry.tags + '</option>';
                });
            }
        });
    }
}

function suggestAssignees() {
    ajax_get("./ajax/updatetugas?suggesta=1&id_tugas=" + id + "&start=" + encodeURIComponent(document.getElementById("assignee").value), function (xhr) {
        var json_obj = json_parse(xhr.responseText);
        if (json_obj.responseStatus == 200) {
            var divTagSug = document.getElementById('assigneesSug');
            divTagSug.innerHTML = '';
            json_obj.suggestedAssignees.forEach(function(entry){
                divTagSug.innerHTML += '<option value="' + entry + '">' + entry + '</option>';
            });
        }
    });
}

function removeAssignee(username) {
    ajax_get("./ajax/updatetugas?removea=1&id_tugas=" + detilTugas.id + "&username=" + username, function(xhr) {
        var json_obj = json_parse(xhr.responseText);
        if (json_obj.responseStatus != 200) {
            alert(json_obj.message);
        } else {
            // do nothing
            // alert("Berhasil");
            requestUpdate(detilTugas.id, -1, false, detilTugas.comments.startindex, detilTugas.comments.count, true);
        }
    });
}

function removeTag(tag) {
    ajax_get("./ajax/updatetugas?removet=1&id_tugas=" + detilTugas.id + "&tag=" + tag, function(xhr) {
        var json_obj = json_parse(xhr.responseText);
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
    ajax_get("./ajax/updatetugas?removec=1&id_komentar=" + id, function(xhr) {
        var json_obj = json_parse(xhr.responseText);
        if (json_obj.responseStatus != 200) {
            alert(json_obj.message);
        } else {
            // do nothing
            // alert("Berhasil");
            requestUpdate(detilTugas.id, -1, false, 0, detilTugas.comments.count, true);
        }
    });
}

function addComment() {
    var txtKomentar = nicEditors.findEditor('txtKomentar');
    var qry = 'addc=1&id_tugas=' + encodeURIComponent(detilTugas.id) + '&content=' + encodeURIComponent(txtKomentar.getContent());
    ajax_post("./ajax/updatetugas", qry, function(xhr) {
        var json_obj = json_parse(xhr.responseText);
        if (json_obj.responseStatus != 200) {
            alert(json_obj.message);
        } else {
            // do nothing
            // alert("Berhasil");
            txtKomentar.setContent('');
            requestUpdate(detilTugas.id, -1, false, detilTugas.comments.startindex, detilTugas.comments.count, true);
        }
    });
}

function deleteTugas() {
    var r = window.confirm("Are you sure want to delete this task?");
    if (r) {
        ajax_get("./ajax/updatetugas?removetugas=1&id_tugas=" + id, function(xhr) {
            var json_obj = json_parse(xhr.responseText);
            if (json_obj.responseStatus != 200) {
                alert(json_obj.message);
            } else {
                // do nothing
                // alert("Berhasil");
                requestUpdate(detilTugas.id, -1, false, 0, detilTugas.comments.count, true);
            }
        });
    }
}