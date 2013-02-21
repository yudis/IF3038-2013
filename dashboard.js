/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var total_category = 3;
var idxselected = -1;

function ShowAllTask(elmt) {
    idxselected = -1;
    for (var i = 0; i < total_category; i++) {
        var current = document.getElementById('main-C' + i);
        current.style.display = 'block';

        var kcurrent = document.getElementById('C' + i);
    }
    return false;
}

function ShowTaskinCategory(elmt) {
    idxselected = elmt.id.substring(1);

    for (var i = 0; i < total_category; i++) {
        var current = document.getElementById('main-C' + i);
        current.style.display = 'none';

        var kcurrent = document.getElementById('C' + i);
    }
    document.getElementById('main-' + elmt.id).style.display = 'block';

    return false;
}

function NewKategori() {
    var element = document.getElementById('txtNewKategori');
    if (element.value !== "") {
        var newitemK = '<li><a id="C' + total_category + '" href="#" onclick="showAddTaskButton(); CancelDisplayDetail(); cancelAddTask(); return ShowTaskinCategory(this)">' + element.value + '</a></li>';
        var currentK = document.getElementById('category');
        currentK.innerHTML += newitemK;

        var newitemC = '<section id="main-C' + total_category + '"><h2>' + element.value + '</h2></section>';
        var currentC = document.getElementById('task');
        currentC.innerHTML += newitemC;

        total_category++;
        return false;
    }
    return false;
}

function DisplayDetail() {
    var toHide = document.getElementById('task');
    toHide.style.display = "none";
    var toShow = document.getElementById('details');
    var toShow2 = document.getElementById('details2');
    var toShow3 = document.getElementById('details3');
    var x = Math.floor(Math.random() * 3 + 1);
//    if(x===1){
    toShow.style.display = "block";
//    }else if(x===2){
//        toShow2.style.display = "block";
//    }else if(x===3){
//        toShow3.style.display = "block";
//    }
    document.getElementById('addTask').style.display = "none";
}

function CancelDisplayDetail() {
    var toHide = document.getElementById('task');
    toHide.style.display = "block";
    var toShow = document.getElementById('details');
    toShow.style.display = "none";
    document.getElementById('addTask').style.display = "block";
}

function showPopUp() {
    var popup = document.getElementById('popup');
    popup.style.display = "block";
}

function hidePopUp() {
    var popup = document.getElementById('popup');
    popup.style.display = "none";
}

function editTask() {
    var popEditTask = document.getElementById('editTask');
    popEditTask.style.display = "block";
    var toHide = document.getElementById('details');
    toHide.style.display = "none";
    document.getElementById('addTask').style.display = "none";
}

function cancelEditTask() {
    var popEditTask = document.getElementById('editTask');
    popEditTask.style.display = "none";
    var toHide = document.getElementById('details');
    toHide.style.display = "block";
}

function addTask() {
    if (idxselected === -1) {
        alert("select category first");
    } else {
        var popAddTask = document.getElementById('createTask');
        popAddTask.style.display = "block";
        var toHide = document.getElementById('task');
        toHide.style.display = "none";
        var toHideDetails = document.getElementById('details');
        toHideDetails.style.display = "none";
        document.getElementById('addTask').style.display = "none";
    }
}

function cancelAddTask() {
    var popAddTask = document.getElementById('createTask');
    popAddTask.style.display = "none";
    var toHide = document.getElementById('task');
    toHide.style.display = "block";
    document.getElementById('addTask').style.display = "block";
}

function submitAddTask() {
    var taskname = document.getElementById('taskname').value;
    var deadline = document.getElementById('deadline').value;
    var tag = document.getElementById('tag').value;
    if ((taskname !== "") && (deadline !== "")) {
        if (taskname.length < 25) {
            var newTask = '<div class="todo"><div><a href="#" class="rincian" onclick="DisplayDetail()">' + taskname + '</a></div><div>Deadline: <strong>' + deadline + '</strong></div><div>Tags:' + tag + '</div></div>';
            var currentTask = document.getElementById('main-C' + idxselected);
            currentTask.innerHTML += newTask;
            document.getElementById('createTask').style.display = "none";
            document.getElementById('addTask').style.display = "block";
            document.getElementById('task').style.display = "block";
        } else {
            alert("Taskname length must be < 25");
        }
    }else{
        alert("Taskname and deadline must be defined.");
    }
    return false;
}

var comment = document.getElementById('formcomment');
comment.onsubmit = function() {
//    alert("Tes");
    var com = document.getElementById('comment').value;
    var curComments = document.getElementById('comments');
    var newComments = '<li>' + com + '</li>';
    curComments.innerHTML += newComments;
    return false;
};

function showAddTaskButton() {
    document.getElementById('addTask').style.display = "block";
    return false;
}
function hideAddTaskButton() {
    document.getElementById('addTask').style.display = "none";
    return false;
}

function submitEditTask() {
    var editAss = document.getElementById('editAssignee').value;
    var editTag = document.getElementById('editTag').value;
    var editDeadline = document.getElementById('editDeadline').value;
    if (editAss === "" && editTag === "" && editDeadline === "") {
        return false;
    } else {
        DisplayDetail();
        return false;
    }
}