/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var ck_taskname = /[a-zA-Z0-9 ]{1,25}$/;
function validate(form) {
    var taskname = form.namatask.value;
    var attachment = form.attachment.value;
    var deadline = form.deadline.value;
    var assignee = form.assignee.value;
    var tag = form.tag.value;
    var errors = [];
    
    if(!ck_taskname.test(taskname)) {
        errors[errors.length] = "Nama tugas tidak valid.";
    }
    
    if (assignee=="") {
        errors[errors.length] = "Assignee tidak valid.";
    }
    
    if (tag=="") {
        errors[errors.length] = "Tag tidak valid.";
    }

    if (deadline=="") {
        errors[errors.length] = "Deadline belum diisi.";
    }
    if (errors.length > 0) {
        reportErrors(errors);
        return false;
    }
    
    return true;
}

function reportErrors(errors){
 var msg = "Please Enter Valide Data...\n";
 for (var i = 0; i<errors.length; i++) {
 var numError = i + 1;
  msg += "\n" + numError + ". " + errors[i];
}
 alert(msg);
}

