<?php
require_once("db.php");
session_start();
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
        <script type="text/javascript" src="script.js"></script>
    </head>
    <body>

        <div id="dashboard-header">
            <img id="text-logo" src="img/logo_small.png" alt="logo" href="dashboard.html" title="Home"/>
            <a title="Go to Dashboard" href="dashboard.php" id="dashboard">Dashboard</a>
            <a title="Go to Profile" href="profile.php" id="profile"><?php echo ($_SESSION['username']); ?></a>
            <img src="
            <?php
            $result1 = ProginDB::getInstance()->query("SELECT * FROM user WHERE username = '" . $_SESSION['username'] . "'");
            $row = mysqli_fetch_array($result1, MYSQLI_ASSOC);
            echo $row['avatar'];
            ?>" id="avatarsmall"
                 />
            <a title="Log out from here" href="logout.php" id="logout">Logout</a>
            <form id="search">
                <input type="text" name="Search" id="box">
                <input type="submit" value="Search">
            </form>
        </div>
        <div id="category">
            <?php
            $query = "SELECT name, category.id_category AS categoryID FROM category JOIN ucrelation WHERE category.id_category = ucrelation.id_category AND username = '" . $_SESSION['username'] . "'";
            $result = ProginDB::getInstance()->query($query);
            while ($row = mysqli_fetch_array($result)) {
                echo "<div class='kategori' id='category" . $row['categoryID'] . "' onclick='showTaskList(" . $row['categoryID'] . ");'>" . $row['name'] . "</div>";
            }
            ?>
        </div>
        
        <div id="addCat">
            <a onclick="addCategory();">+ category</a>
        </div>

        <div id="listtugas"class="list">
            <?php
            $query = "SELECT task.id_task AS taskID, task.name AS taskName, deadline, status FROM utrelation JOIN task WHERE username = '" . $_SESSION['username'] . "' AND utrelation.id_task = task.id_task;";
            $result = ProginDB::getInstance()->query($query);

            while ($row = mysqli_fetch_array($result)) {
                echo "<div class='listTugas' id='task" . $row['taskID'] . "'>";
                echo "<a id='task" . $row['taskID'] . "' onclick='showRinci('" . $row['taskID'] . "');'>" . $row['taskName'] . "</a>";
                echo "<div>" . $row['deadline'] . "</div>";

                $queryTag = "SELECT tag.name AS tagName FROM utrelation JOIN tag JOIN ttrelation WHERE username='" . $_SESSION['username'] . "' AND tag.id_tag = ttrelation.id_tag AND utrelation.id_task = ttrelation.id_task AND utrelation.id_task='" . $row['taskID'] . "';";
                $resultTag = ProginDB::getInstance()->query($queryTag);

                echo "<div>";
                while ($rowTag = mysqli_fetch_array($resultTag)) {
                    echo $rowTag['tagName'] . ", ";
                }
                echo "</div>";

                echo "<div id='statusTask" . $row['taskID'] . "'>";
                echo "<div>" . $row['status'] . "</div>";
                echo "<div>";
                if ($row['status'] == "T") {
                    echo "<input type='checkbox' id='checkboxTask" . $row['taskID'] . "' onclick='changeTaskStatus(" . $row['taskID'] . ", this.checked);' checked>";
                } else if ($row['status' == "F"]) {
                    echo "<input type='checkbox' id='checkboxTask" . $row['taskID'] . "' onclick='changeTaskStatus(" . $row['taskID'] . ", this.checked);'>";
                }
                echo "Done</div>";
                echo "</div>";

                $queryOwner = "SELECT maker_username FROM task WHERE id_task='" . $row['taskID'] . "';";
                $resultOwner = ProginDB::getInstance()->query($queryOwner);
                while ($rowOwner = mysqli_fetch_array($resultOwner)) {
                    if ($_SESSION['username'] === $rowOwner['maker_username']) {
                        echo "<div class='removeTask'><input type='submit' id='removeTaskBtn" . $row['taskID'] . "; onclick='removeTask('" . $row['taskID'] . "');' value='Remove Task'/></div>";
                    }
                }

                echo "</div>";
            }
            ?>
        </div>

        <div class="tugas" id="rincitugas">
            <?php
            
            ?>
<!--            Nama task: Nama Tugas <br/>
            Attachment: 
            <div class="attachment">
                file.zip<br/>
                picture.jpg<br/>
                video.mp4<br/>
            </div><br/>
            Deadline: 17-12-2014<br/>
            Assignee: <a href="" class="asignee">Timo</a>, <a href="" class="asignee">Stefan</a>, <a href="" class="asignee">Frilla</a><br/>
            Tag: <a href="" class="tag">dangerous</a>, <a href="" class="tag">novice</a> <br/>
            <br/>Komentar:<br/>
            <div class="komentar">lalalsalkdl sajdlkjaslkd sjadlkj sjadlkj jaskjkj jsk. jsad kjasd ajsdlj jksjd as jksjd owjijld.</div><br/>
            <form>
                <textArea></textarea>
                    <input type="button" name="submit" value="submit">
                </form>
                <br/><br/>
                <a onclick="showEdit();" class="button">edit</a><br/>-->
            </div>
        
            <div class="tugas" id ="edittugas">
                <form>
                    Nama task: Nama Task<br/>
                    Attachment: <div class="attachment"><input type="file"></div><br/>
                    Deadline: <div class="deadline"><input type="date"></div><br/>
                    Assignee: <div class="asignee"><input type="text"></div><br/>
                    Tag: <div class="tag"> <input type="text"></div> <br/>
                    Komentar: <br/>
                <div class="komentar">lalalsalkdl sajdlkjaslkd sjadlkj sjadlkj jaskjkj jsk. jsad kjasd ajsdlj jksjd as jksjd owjijld.</div><br/>
                </form> <br/>
                <a onclick="showRinci()" class="button">save</a><br/>
            </div>
        
			<div id="wanted">
			<img src="img/kertas2.png">
			</div>
        
            <div class="tugas" id="buattugas">
                <br/>
                Nama task: <div class="nama"><input type="text" id="namaTask"></div><br/>
                Attachment: <div class="attachment"><input type="file"></div><br/>
                Deadline: <div class="deadline"><input type="date"></div><br/>
                Assignee: <div class="asignee"><input type="text"></div><br/>
                Tag: <div class="tag"> <input type="text"></div> <br/>
                <br/>
                <a onclick="createTask();" class="button">create</a><br/>
            </div>
        
        <div id="add">
            Category Name:<br/> <input type='text' id='newCategoryName'><br/>
            Authorized Users:<br/> <input type='text' id="authUsers"><br/>
            <input type="submit" onclick="addCat();" value="create">
            <input type="submit" onclick="restore();" value="cancel">
        </div>
        <?php
        ProginDB::getInstance()->display_avatar($_SESSION['username']);
        ?>
    </body>
</html>
