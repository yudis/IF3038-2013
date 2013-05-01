var detilTugas;
var id;
var asTextbox;

function onload(id_tugas) {
    id = id_tugas;
    new nicEditor({iconsPath : './images/nicEditorIcons.gif'}).panelInstance('txtKomentar');
    requestUpdate(id_tugas, 5000, true, 0, 10, false);
}

function requestUpdate(id_tugas, timeout, updateAttachment, startc, countc, forceUpdate) {
    var url = './ajax/detilTugas.php?id_tugas=' + id_tugas + '&startc=' + startc + '&countc=' + countc;
    if (typeof detilTugas != 'undefined' && !forceUpdate) {
        url += '&update=' + detilTugas.responseTime + '&priviledge=' + detilTugas.priviledge;
    }

    ajax_get(url, function(xhr) {
        var json_obj = JSON.parse(xhr.responseText);
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

    var assigneesTemp = '';
    detilTugas.assignees.forEach(function(entry) {
        assigneesTemp += '<li><a href="./profile.php?u=' + entry.username + '">'  + entry.full_name +  ' (' + entry.username + ')</a>';
        if (detilTugas.priviledge) {
            assigneesTemp += ' | <a class="red" href="#" onclick="removeAssignee(\'' + entry.username + '\'); return false;">&times;</a>';
        }
        assigneesTemp += '</li> ';
    });
    divAssigneesTugas.innerHTML = assigneesTemp;

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
}

function writeTags() {
    var tagsList = document.getElementById("tagsList");   
    var tagsTemp = '';
    for (var i=0; i < detilTugas.tag.length; i++) {
        tagsTemp += '<li>' + detilTugas.tag[i];
        if (detilTugas.priviledge) {
            tagsTemp += ' | <a href="#" class="red" onclick="removeTag(\'' + detilTugas.tag[i] + '\'); return false;">&times;</a>';
        }
        tagsTemp += '</li> ';
    } 
    tagsList.innerHTML = tagsTemp;
}

function writeAssignees() {
    var assigneesList = document.getElementById("assigneesList");
    
    assigneesList.innerHTML = '';
    for (var i=0; i<assigneesTugas.length; i++) {
        assigneesList.innerHTML += "<li>" + assigneesTugas[i] + "</li> ";
    }
}

function addAssignee() {
    var newAssignee = document.getElementById("assignee");
    var assigneesList = document.getElementById("assigneesList");
    
    if (newAssignee.value == "") {
        alert("Nama harus dimasukkan");
        return false;
    }
    
    var url = "./ajax/updateTugas.php?adda&id_tugas=" + detilTugas.id + "&username=" + newAssignee.value;
    ajax_get(url, function(xhr) {
        var json_obj = JSON.parse(xhr.responseText);
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
        
        ajax_get("./ajax/updateTugas.php?updatedeadline&id_tugas=" + id + "&deadline=" + deadlineTextBox.value, function(xhr) {
            var json_obj = JSON.parse(xhr.responseText);
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

function tagsSave(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode == 188) { // Comma
        var txtTag = document.getElementById("tags");
        var url = "./ajax/updateTugas.php?addt&id_tugas=" + id + "&tag=" + encodeURIComponent(txtTag.value.replace(",", ""));
        txtTag.value = '';
        ajax_get(url, function (xhr) {
            var json_obj = JSON.parse(xhr.responseText);
            if (json_obj.responseStatus != 200) {
                alert(json_obj.message);
            }
        });
    } else {
        ajax_get("./ajax/updateTugas.php?suggestt&id_tugas=" + id + "&start=" + encodeURIComponent(document.getElementById("tags").value), function (xhr) {
            var json_obj = JSON.parse(xhr.responseText);
            if (json_obj.responseStatus == 200) {
                var divTagSug = document.getElementById('tagsSug');
                divTagSug.innerHTML = '';
                json_obj.suggestedTags.forEach(function(entry){
                    divTagSug.innerHTML += '<option value="' + entry.tag + '">' + entry.tag + '</option>';
                });
            }
        });
    }
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

function removeTag(tag) {
    ajax_get("./ajax/updateTugas.php?removet&id_tugas=" + detilTugas.id + "&tag=" + tag, function(xhr) {
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
            requestUpdate(detilTugas.id, -1, false, 0, detilTugas.comments.count, true);
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

function deleteTugas() {
    var r = window.confirm("Are you sure want to delete this task?");
    if (r) {
        ajax_get("./ajax/updateTugas.php?removetugas&id_tugas=" + id, function(xhr) {
            var json_obj = JSON.parse(xhr.responseText);
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