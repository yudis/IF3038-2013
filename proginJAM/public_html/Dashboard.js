if (!String.prototype.trim) {
    String.prototype.trim = function() {
        return this.replace(/^\s+|\s+$/g, '');
    };
}

function EditTaskDetail() {
    var oldAssignee = document.getElementById('assignee_info').innerHTML;
    var editAssignee = "<input type='text'\n\
id='edit_assignee'\n\
form='task_detail_form'\n\
value=" + oldAssignee + "/>";
    document.getElementById('assignee_info').innerHTML = editAssignee;

    var oldCommentary = document.getElementById('commentary').innerHTML;
    var editCommentary = "<textarea id='edit_commentary'\n\
rows='6'\n\
cols='40'\n\
form='task_detail_form'>\n\
" + oldCommentary.trim() + "\n\
</textarea>";
    document.getElementById('commentary').innerHTML = editCommentary;

    var oldTag = document.getElementById('tag_info').innerHTML;
    var editTag = "<input type='text'\n\
id='edit_tag'\n\
form='task_detail_form'\n\
value=" + oldTag + "/>";
    document.getElementById('tag_info').innerHTML = editTag;

    var oldDeadline = document.getElementById('deadline_info').innerHTML;
    var editDeadline = "<input  tyep='text' id='edit_deadline'\n\
value=" + oldDeadline + "\n\
required/>";
    document.getElementById('deadline_info').innerHTML = editDeadline;

    document.getElementById("edit_task_button").className = "hide";
    document.getElementById("done_edit_button").className = "show";

}

function DoneEditDetail() {
    var newAssignee = document.getElementById('edit_assignee').value;
    var setNewAssignee = "<label id='assignee_info'>" + newAssignee + "</label>";
    document.getElementById('edit_assignee').innerHTML = setNewAssignee;

    var newCommentary = document.getElementById('edit_commentary').value;
    var setNewCommentary = "<label id='commentary_info'>" + newCommentary + "</label>";
    document.getElementById('edit_commentary').innerHTML = setNewCommentary;

    var newTag = document.getElementById('edit_tag').value;
    var setNewTag = "<label id='tag_info'>" + newTag + "</label>";
    document.getElementById('edit_tag').innerHTML = setNewTag;

    var newDeadline = document.getElementById('edit_deadline').value;
    var setNewDeadline = "<label id='deadline_info'>" + newDeadline + "</label>";
    document.getElementById('edit_deadline').innerHTML = setNewDeadline;

    document.getElementById("edit_task_button").className = "show";
    document.getElementById("done_edit_button").className = "hide";
}

function ValidateFileType() {
    var id_value = document.getElementById('attachment').value;
    if (id_value !== '') {
        var image_valid_extensions = /(.jpg|.jpeg|.gif|.png)$/i;
        var video_valid_extensions = /(.avi|.flv|.mp4)$/i;

        var imageHint = document.getElementById('image_detected');
        var videoHint = document.getElementById('video_detected');
        var fileHint = document.getElementById('file_detected');
        if (image_valid_extensions.test(id_value)) {
            imageHint.style.display = "inline";
        } else if (video_valid_extensions.test(id_value)) {
            videoHint.style.display = "inline";
        } else {
            fileHint.style.display = "inline";
        }
    }
}

var ids = new Array('task_set_1', 'task_set_2', 'task_set_3', 'add_task_div');

function HideAllIDs() {
    for (var i = 0; i < ids.length; i++) {
        HideDiv(ids[i]);
    }
}

function HideDiv(id) {
    if (document.getElementById) {
        document.getElementById(id).style.display = 'none';
    } else {
        if (document.layers) {
            document.id.display = 'none';
        } else {
            document.all.id.style.display = 'none';
        }
    }
}

function showdiv(id) {
    if (document.getElementById) {
        document.getElementById(id).style.display = 'block';
    }else {
        if (document.layers) {
            document.id.display = 'block';
        } else {
            document.all.id.style.display = 'block';
        }
    }
}

function addNewCategory() {
    showdiv('the_new_category');
}

function addNewTask() {
    showdiv('new_task_1');
    showdiv('new_task_2');
    showdiv('new_task_3');
}