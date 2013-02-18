function EditTaskDetail() {
    var oldAssignee = document.getElementById('assignee_info').innerHTML;
    var editAssignee = "<input type='text'\n\
id='assignee_edit'\n\
form='task_detail_form'\n\
value=" + oldAssignee + "/>";
    document.getElementById('assignee_info').innerHTML = editAssignee;

    var oldCommentary = document.getElementById('commentary').innerHTML;
    var editCommentary = "<textarea id='commentary_edit'\n\
rows='6'\n\
cols='40'\n\
form='task_detail_form'\n\
value=" + oldCommentary + "></textarea>";
    document.getElementById('commentary').innerHTML = editCommentary;
    
    var oldTag = document.getElementById('tag_info').innerHTML;
    var editTag = "<input type='text'\n\
id='tag_edit'\n\
form='task_detail_form'\n\
value=" + oldTag + "/>";
    document.getElementById('tag_info').innerHTML = editTag;
    
    var doneButton = "<input type='submit'\n\
id='done_edit_button'\n\
value='Done'\n\
name='edit_task_detail'\n\
onclick='DoneEditDetail()'/>";
    document.getElementById('edit_task_button').innerHTML = doneButton;
}

function DoneEditDetail() {
    
}

