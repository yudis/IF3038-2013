<?php
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<?php
include 'header.php';
?>
<html>
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="autocomplete.css" media="screen" />
        <script type="text/javascript" src="ajax.js"></script>
    </head>
    <form id="addTaskForm" method="post" action="addTaskDB.php" enctype="multipart/form-data">
        <div class="tugas" id="buattugas">
            <br/>
            Name: <div class="nama"><input type="text" id="namaTask" name="namaTask"></div><br/>
            Attachment: <div class="attachment"><input type="file" id="newAttachmentTask" name="attachfile[]"  multiple></div><br/>
            Deadline: <div class="deadline"><input id="newDeadlineTask" name="newDeadlineTask" type="date"></div><br/>
            Assignee: <div class="asignee"><input id="newAssigneeTask" name="newAssigneeTask" type="text" onkeyup="multiAutocomp(this, 'assignee.php', 'buattugas')" onfocusin="multiAutocompClearAll()"></div><br/>
            Tag: <div class="tag"> <input id="newTagTask" name="newTagTask" type="text"></div> <br/>

            <input type="submit" id="regbuttosn" name="submit" value="create">
            <br/>
            <!-- <a type="submit" onclick="createTask();" class="button">create</a><br/> !-->
        </div>
    </form>
    <script type="text/javascript" src="script.js"></script>
</html>