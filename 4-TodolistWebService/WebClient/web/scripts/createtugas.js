function showAssignee()
{
    ajax_get("./ajax/AssigneeList", function(xhr) {
        document.getElementById("user").innerHTML = xhr.responseText;
    });
}

function createT()
{
    var qry = 'namakategori=' + encodeURIComponent(document.forms["formTugas1"]["namakategori"].value) + '&namatask=' + encodeURIComponent(document.forms["formTugas1"]["namatask"].value) + '&deadline=' + encodeURIComponent(document.forms["formTugas1"]["deadline"].value) + '&assigneeI=' + encodeURIComponent(document.forms["formTugas1"]["assigneeI"].value) + '&tag=' + encodeURIComponent(document.forms["formTugas1"]["tag"].value);
    ajax_post("./ajax/Create", qry, function(xhr) {

    });
}

function addAssignees() {
    var newAssignee = document.getElementById("assignee");
    var assigneesList = document.getElementById("assigneesList");

    if (newAssignee.value == "") {
        alert("Nama harus dimasukkan");
        return false;
    }

    document.getElementById("assigneeI").value += newAssignee.value + ",";
    assigneesList.innerHTML += "<li>" + newAssignee.value + "</li>";
    newAssignee.value = "";
}
