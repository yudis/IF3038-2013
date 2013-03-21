<?php
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<html>
    <div class="tugas" id="buattugas">
        <br/>
        Name: <div class="nama"><input type="text" id="namaTask"></div><br/>
        Attachment: <div class="attachment"><input type="file" id="newAttachmentTask" name="attachfile[]"  multiple></div><br/>
        Deadline: <div class="deadline"><input id="newDeadlineTask" type="date"></div><br/>
        Assignee: <div class="asignee"><input id="newAssigneeTask" type="text" onkeyup="multiAutocomp(this, 'assignee.php', 'buattugas')" onfocusin="multiAutocompClearAll()"></div><br/>
        Tag: <div class="tag"> <input id="newTagTask"type="text"></div> <br/>
        <br/>
        <a onclick="createTask();" class="button">create</a><br/>
    </div>
</html>