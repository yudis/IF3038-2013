<%-- 
    Document   : task_page
    Created on : Apr 6, 2013, 8:26:09 AM
    Author     : VAIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Task</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="javascript/task_page.js"></script>
        <script src="javascript/calendar.js"></script>
        <link href="css/calendar.css" rel="stylesheet">
    </head>
    <body onLoad="check_html5()">
        <div id="main-body-general">
                <!--Header-->
                <div id="header">
                        <?php
                                include("header.php");
                                require('../php/init_function.php');
                                $taskid = $_GET['taskid'];
                                $task = getTask($taskid);
                        ?>
                </div>

                <div id="nomargin"><hr id="border"></div>

                <!--Body-->
                <div id="task-page-body">
                        <div id="task-page-title">
                                <div>
                                        <h1><?php echo $task['taskname']?></h1>
                                        <i><div id="delete-task">
                <?php 
                                        $creator = $task['username'];
                                        if($creator == $_SESSION['userlistapp']){
                                                echo " <a href=\"../php/deletetask.php?taskid=".$taskid."\" onClick=\"confirmTask()\"><i>Delete This Task</i></a>";
                                        }
                                        ?>
                <br><br></div></i>
                                        Submit by <b><i><?php echo $task['username']?></i></b> at. <?php echo $task['createddate']?>
                                </div>
                                <br>
                                <div id="deadline_done">
                                        <div id="left-main-body">Deadline : <?php echo $task['deadline']?></div>
                <?php 
                                                $creator = $task['username'];
                                                if($creator == $_SESSION['userlistapp']){
                                                        <!--echo "<div id=\"right-main-body\"><a href=\"#\" onCLick=\"edit_deadline()\"><u>edit</u></a></div>";-->
                                                }
                                        ?>
                                </div>
                                <div id="deadline_edit">
                                        <div id="left-main-body"><div id="label">Deadline : </div>
                                                <div id="date_html">
                                                        <input id="datedeadlineinput" type="text" class="calendarSelectDate" name="textDeadlineDate" placeholder="YYYY-MM-DD"/><div id="calendarDiv"></div><br />
                                                        <input id="timedeadlineinput" type="text" name="textDeadlineTime" placeholder="HH:MM"/>
                                                </div>              
                                        </div>
                                        <div id="right-main-body"><a href="#" onCLick="finish_deadline(<?php echo $taskid ?>)"><u>done</u></a></div>
                                </div>
                                <br><br><br>
            <div>
                                        <div id="left-main-body4">Status : <?php echo $task['status']?></div> 
                                        <div id="right-main-body">
                    <?php 
                                                $creator = $task['username'];
                                                if($creator == $_SESSION['userlistapp']){
                                                        echo "<input name=\"changeStatus\" type=\"button\" value=\"Change Status\" onClick=\"setCompleteStatus($taskid)\">";
                                                }
                                        ?>                   
                </div>
                                </div>
                        </div>
                        <br><br><br><br>
                        <div id="attachment">
                                <div id="image-attachment">
                                        <div>
                                        <br>
                                        <br>
                        <?php 
                                                        $con = getConnection();
                                                        $query = "SELECT filename FROM attachment WHERE taskid = $taskid";
                                                        $result = mysqli_query($con,$query);
                                                        while($row = mysqli_fetch_array($result)){
                                                                $filename = $row['filename'];
                                                                if(typeFile($filename) == 0){
                                                                        echo "<img id=\"screenshots\" src=\"../attachment/$filename\" alt=\"$filename\" title=\"$filename\"\">";
                                                                }
                                                        }
                                                ?>
                                        </div>
                                </div>
            <div id="video-attachment">
                <?php 
                                                        $con = getConnection();
                                                        $query = "SELECT filename FROM attachment WHERE taskid = $taskid";
                                                        $result = mysqli_query($con,$query);
                                                        while($row = mysqli_fetch_array($result)){
                                                                $filename = $row['filename'];
                                                                if(typeFile($filename) == 1){
                                                                        echo "<video width=\"320\" height=\"240\" controls>";
                                                                        echo "<source src=\"../attachment/$filename\" type=\"video/mp4\">";
                                                                        echo "</video>";
                                                                }
                                                        }
                                        ?>
                                </div>
                                <div id="other-attachment">
                                        <p>External File:</p>
                                        <ul>
                        <?php 
                                                        $con = getConnection();
                                                        $query = "SELECT filename FROM attachment WHERE taskid = $taskid";
                                                        $result = mysqli_query($con,$query);
                                                        while($row = mysqli_fetch_array($result)){
                                                                $filename = $row['filename'];
                                                                if(typeFile($filename) == 2){
                                                                        echo "<li><a href=\"../attachment/$filename\">$filename</a></li>";
                                                                }
                                                        }
                                                ?>
                                        <ul>
                                </div>
                        </div>
                        <div>
                                <div id="task-nomargin" name="1"><div id="assignee_done">
                                        <div id="left-main-body2">Shared with : <i>
                        <?php 
                                                        $con = getConnection();
                                                        $query = "SELECT username FROM assignee WHERE taskid = $taskid";
                                                        $result = mysqli_query($con,$query);
                                                        while($row = mysqli_fetch_array($result)){
                                                                echo "<a href=\"profile.php?username=".$row['username']."\"><u>".$row['username']."</u></a> ";
                                                        }
                                                ?>
                </i></div>
                                        <div id="right-main-body">
                                                <?php 
                        $creator = $task['username'];
                        if($creator == $_SESSION['userlistapp']){
                                        <!--echo "<a href=\"#2\" onClick=\"edit_assignee()\"><u>edit</u></a>";-->
                        }
                    ?>
                </div>
                                </div></div>
                                <div id="assignee_edit" name="2">
                                        <div id="left-main-body">Shared with : <input id="task-assignee" type="text" name="textAssignee" list="assignee-task" onKeyUp="autoCompleteAsignee()" placeholder="tag1,tag2,tag3"/>
                                        <div id="shared-with"></div>
                                        </div>
                                        <div id="right-main-body"><a href="#1" onClick="finish_assignee(<?php echo $taskid ?>)"><u>done</u></a></div>
                                </div>
                                <br>
                                <br>
                                <div id="task-nomargin" name="3"><div id="tag_done">
                                        <div id="left-main-body3">Tag : <i>
                <?php 
                                                        $con3 = getConnection();
                                                        $result3 = mysqli_query($con3,"SELECT tagid FROM task_tag WHERE taskid = '".$taskid."'");
                                                        while($row3 = mysqli_fetch_array($result3))
                                                        {
                                                                $tagname = getTagname($row3['tagid']);
                                                                echo "<u>".$tagname."</u> ";
                                                        }
                                                ?>
                </i></div>
                                        <div id="right-main-body">
                <?php 
                                                $creator = $task['username'];
                                                if($creator == $_SESSION['userlistapp']){
                                <!--echo "<a href=\"#3\" onClick=\"edit_tag()\"><u>edit</u></a>";-->
                                                }
                                        ?>
                </div>
                                </div></div>
                                <div id="tag_edit" name="4">
                                        <div id="left-main-body">Tag : <input id="tag-edit" type="text" placeholder="tag1,tag2,tag3"/></div>
                                        <div id="right-main-body"><a href="#4" onClick="finish_tag(<?php echo $taskid ?>)"><u>done</u></a></div>
                                </div>
                        </div>
                </div>

                <div id="nomargin"><hr id="border"></div>

                <!--Comment-->
                <div id="task-comment">
                        <div id="user-comment">
                                <p id="nComment"><b><?php echo getNComment($taskid);?></b></p>
                                <div id="comment-list">
                                        <?php
                        $con = getConnection();
                                                $query = "SELECT * FROM comment WHERE taskid = $taskid";
                                                $result = mysqli_query($con,$query);
                                                while($row = mysqli_fetch_array($result)){
                                                        echo " <div id=\"comment\">";
                                                        echo " 	<div id=\"user-info\">";
                                                        echo " 		<div id=\"left-comment-body\">";
                                                        echo " 			<img src=\"../avatar/".getAvatar($row['username'])."\" width=\"50px\" height=\"50px\"/>";
                                                        echo " 		</div>";
                                                        echo " 		<div id=\"right-comment-body\">";
                                                        echo " 			<b id=\"komentator\">".$row['username']."</b>";
                                                        echo " 			<br>";
                                                        echo " 			<b id=\"post-date\">Post at ".$row['createdate']."</b>";
                                                        echo " 		</div>";
                                                        echo " 		<div id=\"delete-comment\">";
                                                                if($row['username'] == $_SESSION['userlistapp']){
                                                                        <!--echo "<a href=\"#\" onClick=\"deleteComment(".$row['commentid'].",$taskid)\"><i>Delete Comment</i></a>";-->
                                                                }
                                                        echo " 		</div>";
                                                        echo " 	</div>";
                                                        echo " 	<div id=\"comment-box\">";
                                                        echo " 		<p>";
                                                        echo $row['message'];
                                                        echo " 		</p>";
                                                        echo " 	</div>";
                                                        echo " </div>";
                                                }
                                        ?>

                                        <div id="new-comment"></div>
                                </div>
                        </div>
                        <div id="add-comment">
                                <p><b>Leave a comment</b></p>
                                <form>
                                        <textarea id="textarea-comment" rows="8" cols="92" placeholder="Comment about this task..."></textarea>
                                </form>
                                <div><button id="submit-comment" onClick="addComment(<?php echo $taskid ?>)">Submit</button>&nbsp;</div>
                        </div>
                </div>

        </div>
    </body>
</html>
