/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function checkTag() {
    var tag=document.forms["editTaskForm"]["edittask_tag"].value;
    if(tag=="" || tag==null)
        {document.getElementById('tagwarning').innerHTML="*Warning! Seluruh task akan dihapus";}
    else
        {document.getElementById('tagwarning').innerHTML="";}
}

function checkAssignee() {
    var tag=document.forms["editTaskForm"]["edittask_assignee"].value;
    if(tag=="" || tag==null)
        {document.getElementById('assigneewarning').innerHTML="*Warning! Seluruh assignee akan dihapus, kecuali task creator";}
    else
        {document.getElementById('assigneewarning').innerHTML="";}
}



function checkValidation() {
    checkTag();
    checkAssignee();
}