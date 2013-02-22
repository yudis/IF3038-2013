/*
 * 
 */
var tagsTugas = new Array();
var assigneesTugas = new Array();

function onload() {
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
    var temp = tags.value.split(",");
    tagsTugas.length = 0;
    temp.forEach(function(obj) {
        tagsTugas.push(obj.trim());
    });
    writeTags();
    
    assigneesTugas.push("Benny Wijaya");
    assigneesTugas.push("Florentina");
    writeAssignees();
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
        alert("Nama harus dimasukkan");
        return false;
    }
    
    assigneesTugas.push(newAssignee.value);
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
