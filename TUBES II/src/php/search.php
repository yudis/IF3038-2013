<?php
session_start(); 
include 'ChromePhp.php';
ChromePhp::log('hello world');
header('Content-type: text/html');
    header('Access-Control-Allow-Origin: *');

    $filtertype = $_GET["filtertype"];
    $searchquery = $_GET["searchquery"];
    $userid = $_SESSION['login'];

    $con = mysqli_connect(localhost,"progin","progin","progin") or die ('Cannot connect to database : ' . mysql_error());
    ChromePhp::log("filtertype: ".$filtertype);
    ChromePhp::log("searchquery: ".$searchquery);
    ChromePhp::log("userid: ".$userid);
    
    switch ($filtertype) {
        case "All":
            $result1 = mysqli_query($con, "SELECT * FROM profil WHERE Username='".$searchquery."'");
            $result2 = mysqli_query($con, "SELECT * FROM category INNER JOIN task INNER JOIN assignee 
                ON category.IDTask=task.ID AND task.ID=assignee.IDTask
                WHERE category.Category='".$searchquery."' AND assignee.IDUser='".$userid."'");
            $result3 = mysqli_query($con, "SELECT * FROM task LEFT JOIN tags INNER JOIN assignee 
                ON task.ID=tags.IDTask AND task.ID=assignee.IDTask
                WHERE (task.Nama='".$searchquery."' OR tags.Tag='".$searchquery."') AND assignee.IDUser='".$userid."'");
            
            echo "<p>Users found</p>";
            echo "<table>";
            while($row = mysqli_fetch_array($result1))
            {
                echo "<tr>
                        <td>".$row['Username']."</td>
                        <td>".$row['FullName']."</td>
                        <td> <img src='".$row['Avatar']."'></td>
                        ";
                echo "</tr>";
            }
            echo "</table>";
            
            echo "<p>Tasks found</p>";
            echo "<table>";
            while($row = mysqli_fetch_array($result2))
            {
                $result2 = mysqli_query($con, "SELECT Tag FROM tags WHERE IDTask='".$row['IDTask']."'");
                $tags = "";
                while ($row2 = mysqli_fetch_array($result2)) {
                    $tags = $tags.$row2['Tag'];
                }
                echo "<tr>
                        <td>".$row['Nama']."</td>
                        <td>".$row['Deadline']."</td>
                        <td>".$row['Status']."</td>
                        <td>".$tags."</td>";
                        if ($row['Status'] == 1) 
                            echo "<input type='checkbox' checked />";
                        else
                            echo "<input type='checkbox' />";
                echo "</tr>";
            }
            echo "</table>";
            
            echo "<table>";
            while($row = mysqli_fetch_array($result3))
            {
                $result2 = mysqli_query($con, "SELECT Tag FROM tags WHERE IDTask='".$row['IDTask']."'");
                $tags = "";
                while ($row2 = mysqli_fetch_array($result2)) {
                    $tags = $tags.$row2['Tag'];
                }
                echo "<tr>
                        <td>".$row['Nama']."</td>
                        <td>".$row['Deadline']."</td>
                        <td>".$row['Status']."</td>
                        <td>".$tags."</td>";
                        if ($row['Status'] == 1) 
                            echo "<input type='checkbox' checked />";
                        else
                            echo "<input type='checkbox' />";
                echo "</tr>";
            }
            echo "</table>";
            break;
        case "Username":
            
            if ($searchquery != "")
                $result = mysqli_query($con, "SELECT * FROM profil WHERE Username='".$searchquery."'");
            else
                $result = mysqli_query($con, "SELECT * FROM profil");
            echo "<table class='usersearchtable'>";
            while($row = mysqli_fetch_array($result))
            {
                echo "<tr>
                        <td>".$row['Username']."</td>
                        <td>".$row['FullName']."</td>
                        <td> <img src='".$row['Avatar']."'></td>
                        ";
                echo "</tr>";
            }
            echo "</table>";
            break;
        case "Category":
            if ($searchquery != "") {
            $result = mysqli_query($con, "SELECT * FROM category INNER JOIN task INNER JOIN assignee 
                ON category.IDTask=task.ID AND task.ID=assignee.IDTask
                WHERE category.Category='".$searchquery."' AND assignee.IDUser='".$userid."' OR task.IDCreator='".$userid."'");
            }
            else {
                $result = mysqli_query($con, "SELECT * FROM category INNER JOIN task INNER JOIN assignee 
                ON category.IDTask=task.ID AND task.ID=assignee.IDTask
                WHERE assignee.IDUser='".$userid."' OR task.IDCreator='".$userid."'");
            }
            echo "<table class='categorysearchtable'>";
            echo "<tr>
                <td>Nama Task</td>
                <td>Deadline</td>
                <td>Status</td>
                <td>Tags</td>
                <td> Check if done </td>
                </tr>
                ";
            while($row = mysqli_fetch_array($result))
            {
                $result2 = mysqli_query($con, "SELECT Tag FROM tags WHERE IDTask='".$row['IDTask']."'");
                $tags = "";
                while ($row2 = mysqli_fetch_array($result2)) {
                    $tags = $tags.$row2['Tag'];
                }
                echo "<tr>
                        <td>
                        <form name='hiddenidtask' id='hiddenidtask' action='rincian.php' method='post'>
                            <input type='hidden' name='idtask' value='".$row['ID']."' />
                        </form>
                        <a href=\"javascript:document.forms['hiddenidtask'].submit()\"  >".$row['Nama']."</a>
                        </td>
                        <td>".$row['Deadline']."</td>";
                        if ($row['Status'] == 1)
                            echo "<td> finished </td>";
                        else
                            echo "<td> not finished </td>";
                        echo "<td>".$tags."</td>";
                        if ($row['Status'] == 1) 
                            echo "<td><input type='checkbox' checked /></td>";
                        else
                            echo "<td><input type='checkbox' /></td>";
                echo "</tr>";
            }
            echo "</table>";
            break;
        case "Task":
            if ($searchquery != "") {
                $result = mysqli_query($con, "SELECT * FROM task LEFT JOIN tags INNER JOIN assignee 
                ON task.ID=tags.IDTask AND task.ID=assignee.IDTask
                WHERE (task.Nama='".$searchquery."' OR tags.Tag='".$searchquery."') AND 
                    (assignee.IDUser='".$userid."' OR task.IDCreator='".$userid."')");
            }
            else {
                $result = mysqli_query($con, "SELECT * FROM task LEFT JOIN tags INNER JOIN assignee 
                ON task.ID=tags.IDTask AND task.ID=assignee.IDTask
                WHERE (assignee.IDUser='".$userid."' OR task.IDCreator='".$userid."')");
            }
            
            echo "<table class='tasksearchtable'>";
            echo "<tr>
                <td>Nama Task</td>
                <td>Deadline</td>
                <td>Status</td>
                <td>Tags</td>
                <td> Check if done </td>
                </tr>
                ";
            while($row = mysqli_fetch_array($result))
            {
                $result2 = mysqli_query($con, "SELECT Tag FROM tags WHERE IDTask='".$row['IDTask']."'");
                $tags = "";
                while ($row2 = mysqli_fetch_array($result2)) {
                    $tags = $tags.$row2['Tag'];
                }
                echo "<tr>
                        <td>
                        <form name='hiddenidtask2' id='hiddenidtask2' action='rincian.php' method='post'>
                            <input type='hidden' name='idtask' value='".$row['ID']."' />
                        </form>
                        <a href='javascript:document.getElementById('hiddenidtask2').submit();' >".$row['Nama']."</a>
                        </td>
                        <td>".$row['Deadline']."</td>
                        <td>".$row['Status']."</td>
                        <td>".$tags."</td>";
                        if ($row['Status'] == 1) 
                            echo "<input type='checkbox' checked />";
                        else
                            echo "<input type='checkbox' />";
                echo "</tr>";
            }
            echo "</table>";
            break;
    }
    
    
   
    
?>
