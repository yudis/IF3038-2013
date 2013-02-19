if(!String.prototype.trim) {
    String.prototype.trim = function() {
        return this.replace(/^\s+|\s+$/g,'');
    };
}

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
form='task_detail_form'>\n\
" + oldCommentary.trim() + "\n\
</textarea>";
    document.getElementById('commentary').innerHTML = editCommentary;
    
    var oldTag = document.getElementById('tag_info').innerHTML;
    var editTag = "<input type='text'\n\
id='tag_edit'\n\
form='task_detail_form'\n\
value=" + oldTag + "/>";
    document.getElementById('tag_info').innerHTML = editTag;
    
    var oldDeadline = document.getElementById('deadline').innerHTML;
    // Change the deadline
    
    var doneButton = "<input type='submit'\n\
id='done_edit_button'\n\
value='Done'\n\
name='edit_task_detail'\n\
onclick='DoneEditDetail()'/>";
    document.getElementById('edit_task_button').innerHTML = doneButton;
}

function DoneEditDetail() {
    
}

function ValidateFileType() {
    var id_value = document.getElementById('attachment').value;
    if (id_value !== '') {
        var image_valid_extensions = /(.jpg|.jpeg|.gif|.png)$/i;
        var video_valid_extensions = /(.avi|.flv|.mp4)$/i;
        var file_valid_extensions = /(.txt|.doc|.pdf|.docx|.xls|.xlsx|.ppt|.pptx)$/i;
        if (image_valid_extensions.test(id_value)) {
            
        } else if (video_valid_extensions.test(id_value)) {
            
        } else if (file_valid_extensions.test(id_value)) {
            
        } else {
            
        }
    }
}