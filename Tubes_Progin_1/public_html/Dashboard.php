<?php
require_once("db.php");
session_start();

$_SESSION['category'] = 0;
?>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <head>
        <title>BANG! - Dashboard</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="autocomplete.css"/>
        <script type="text/javascript" src="script.js"></script>
        <script src="multiautocomplete.js"></script>
        <script src='comment.js'></script>
    </head>
    <body>
        <?php
        include_once './header.php';
        ?>
        <div id="panel">
            <img src="img/bg.jpg" id="panelbg"/>
        </div>
        <div id="category">
            <?php
            $query = "SELECT name, category.id_category AS categoryID FROM category JOIN ucrelation WHERE category.id_category = ucrelation.id_category AND username = '" . $_SESSION['username'] . "'";
            $result = ProginDB::getInstance()->query($query);
            echo "<div class='kategori' id='categoryAll' onclick='showTaskList(0);'>All</div>";
            while ($row = mysqli_fetch_array($result)) {
                echo "<div class='kategori' id='category" . $row['categoryID'] . "' onclick='showTaskList(" . $row['categoryID'] . ");'>" . $row['name'] . "</div>";

                $queryCategCreator = "SELECT categ_creator FROM category WHERE id_category='" . $row['categoryID'] . "';";
                $resultCategCreator = ProginDB::getInstance()->query($queryCategCreator);
                while ($rowCategCreator = mysqli_fetch_array($resultCategCreator)) {
                    if ($_SESSION['username'] === $rowCategCreator['categ_creator']) {
                        echo "<div class='kategori' id='removeCategory" . $row['categoryID'] . "' onclick='removeCategory(" . $row['categoryID'] . ");'>REMOVE " . $row['name'] . "</div>";
                    }
                }
            }
            ?>
        </div>

        <div id="addCat">
            <a onclick="addCategory();">+ category</a>
        </div>

        <div id="listwall" class="list">
            <?php
            $query = "SELECT task.id_task AS taskID, task.name AS taskName, deadline, status FROM utrelation JOIN task WHERE username = '" . $_SESSION['username'] . "' AND utrelation.id_task = task.id_task;";
            $result = ProginDB::getInstance()->query($query);

            while ($row = mysqli_fetch_array($result)) {
                echo "<div class='listTugas' id='task" . $row['taskID'] . "'>";
                echo "<a id='task" . $row['taskID'] . "' onclick='showRinci(" . $row['taskID'] . ");'><b>" . $row['taskName'] . "</b></a>";
                echo "<div>" . $row['deadline'] . "</div>";

                $queryTag = "SELECT tag.name AS tagName FROM utrelation JOIN tag JOIN ttrelation WHERE username='" . $_SESSION['username'] . "' AND tag.id_tag = ttrelation.id_tag AND utrelation.id_task = ttrelation.id_task AND utrelation.id_task='" . $row['taskID'] . "';";
                $resultTag = ProginDB::getInstance()->query($queryTag);

                echo "<div style='font-size:10pt;'>Tag: <i>";
                while ($rowTag = mysqli_fetch_array($resultTag)) {
                    echo $rowTag['tagName'] . ", ";
                }
                echo "</i></div>";

                echo "<div id='statusTask" . $row['taskID'] . "'>";
                //echo "<div>" . $row['status'] . "</div>";
                echo "<div>";
                if ($row['status'] == "T") {
                    echo "<input type='checkbox' id='checkboxTask" . $row['taskID'] . "' onclick='changeTaskStatus(" . $row['taskID'] . ", this.checked);' checked>";
                } else if ($row['status' == "F"]) {
                    echo "<input type='checkbox' id='checkboxTask" . $row['taskID'] . "' onclick='changeTaskStatus(" . $row['taskID'] . ", this.checked);'>";
                }
                echo "Done</div>";
                echo "</div>";

                $queryOwner = "SELECT creator FROM task WHERE id_task='" . $row['taskID'] . "';";
                $resultOwner = ProginDB::getInstance()->query($queryOwner);
                while ($rowOwner = mysqli_fetch_array($resultOwner, MYSQLI_ASSOC)) {
                    if ($_SESSION['username'] === $rowOwner['creator']) {
                        echo "<div class='removeTask'><input type='submit' id='removeTaskBtn" . $row['taskID'] . "' onclick='removeTask(" . $_SESSION['category'] . "," . $row['taskID'] . ");' value='Remove Task'/></div>";
                    }
                }
                echo "</div>";
            }
            ?>
        </div>

        <div class="tugas" id="rincitugas"></div>

        <div class="tugas" id ="edittugas"></div>

        <div class="tugas" id="buattugas">
            <br/>
            <form method='POST' onsubmit='addTask()' action='AddTask.php' enctype="multipart/form-data">
                Nama task: <div class="nama"><input type="text" name="newTaskName" required
                                                    pattern='[A-Za-z0-9]{1,25}'></div><br/>
                Attachment: <div class="attachment"><input type="file" name='newTaskAttach[]' multiple></div><br/>
                Deadline: <div class="deadline"><input type="date" name='newTaskDeadline' required></div><br/>
                Assignee: <div class="asignee"><input type="text" name='newTaskAssignee' id='newTaskAssignee'
                                                      onkeyup="multiAutocomp(this, 'GetAllUsernames.php');"
                                                      onfocusin="multiAutocompClearAll()"></div><br/>
                Tag: <div class="tag"> <input type="text" name='newTaskTag' id='newTaskTag'
                                              onkeyup="multiAutocomp(this, 'GetAllTags.php');"
                                              onfocusin="multiAutocompClearAll()"></div> <br/>
                <br><input type='submit' value='Create Task'><br/>
            </form>
            <!--<br/>-->
            <!--<a onclick="createTask();" class="button">create</a><br/>-->
        </div>

        <div id="add">
            <form method='POST' onsubmit='addCat()' action='dashboard.php'>
                Category Name: <input type='text' id='newCategoryName' required><br/>
                Authorized Users: <input type='text' id ="authUsers"
                                         onkeyup="multiAutocomp(this, 'GetAllUsernames.php');"
                                         onfocusin="multiAutocompClearAll()"><br/>
                <input type="submit" value="Create Category">
            </form>
            <input type="submit" onclick="restore();" value="Cancel">
        </div>
        <?php
        ProginDB::getInstance()->display_avatar($_SESSION['username']);
        if (isset($_GET['seektask'])) {
            echo "<script type = 'text/javascript'>";
            echo "showRinci(" . $_GET['seektask'] . ");";
            echo "</script>";
        }
        ?>
    </body>
</html>
