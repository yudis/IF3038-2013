<?php

header('Content-type: text/html');
    header('Access-Control-Allow-Origin: *');
    echo "test php response";

//    $filtertype = $_GET["filtertype"];
//    $searchquery = $_GET["searchquery"];
//    $userid = $_SESSION['login'];
//
//    $con = mysqli_connect(localhost,"root","","tubes2progin") or die ('Cannot connect to database : ' . mysql_error());
//    
//    switch ($filtertype) {
//        case "All":
//            $result1 = mysqli_query($con, "SELECT * FROM profil WHERE Username='".$searchquery."'");
//            $result2 = mysqli_query($con, "SELECT * FROM category INNER JOIN task INNER JOIN assignee 
//                ON category.IDTask=task.ID AND task.ID=assignee.IDTask
//                WHERE category.Category='".$searchquery."' AND assignee.IDUser='".$userid."'");
//            $result3 = mysqli_query($con, "SELECT * FROM task LEFT JOIN tags INNER JOIN assignee 
//                ON task.ID=tags.IDTask AND task.ID=assignee.IDTask
//                WHERE (task.Nama='".$searchquery."' OR tags.Tag='".$searchquery."') AND assignee.IDUser='".$userid."'");
//            
//            echo "<p>Users found</p>";
//            echo "<table>";
//            while($row = mysqli_fetch_array($result1))
//            {
//                echo "<tr>
//                        <td>".$row['Username']."</td>
//                        <td>".$row['FullName']."</td>
//                        <td> <img src='".$row['Avatar']."'></td>
//                        ";
//                echo "</tr>";
//            }
//            echo "</table>";
//            
//            echo "<p>Tasks found</p>";
//            echo "<table>";
//            while($row = mysqli_fetch_array($result2))
//            {
//                $result2 = mysqli_query($con, "SELECT Tag FROM tags WHERE IDTask='".$row['IDTask']."'");
//                $tags = "";
//                while ($row2 = mysqli_fetch_array($result2)) {
//                    $tags = $tags.$row2['Tag'];
//                }
//                echo "<tr>
//                        <td>".$row['Nama']."</td>
//                        <td>".$row['Deadline']."</td>
//                        <td>".$row['Status']."</td>
//                        <td>".$tags."</td>";
//                        if ($row['Status'] == 1) 
//                            echo "<input type='checkbox' checked></input>";
//                        else
//                            echo "<input type='checkbox'></input>";
//                echo "</tr>";
//            }
//            echo "</table>";
//            
//            echo "<table>";
//            while($row = mysqli_fetch_array($result3))
//            {
//                $result2 = mysqli_query($con, "SELECT Tag FROM tags WHERE IDTask='".$row['IDTask']."'");
//                $tags = "";
//                while ($row2 = mysqli_fetch_array($result2)) {
//                    $tags = $tags.$row2['Tag'];
//                }
//                echo "<tr>
//                        <td>".$row['Nama']."</td>
//                        <td>".$row['Deadline']."</td>
//                        <td>".$row['Status']."</td>
//                        <td>".$tags."</td>";
//                        if ($row['Status'] == 1) 
//                            echo "<input type='checkbox' checked></input>";
//                        else
//                            echo "<input type='checkbox'></input>";
//                echo "</tr>";
//            }
//            echo "</table>";
//            break;
//        case "Username":
//            $result = mysqli_query($con, "SELECT * FROM profil WHERE Username='".$searchquery."'");
//            echo "<table>";
//            while($row = mysqli_fetch_array($result))
//            {
//                echo "<tr>
//                        <td>".$row['Username']."</td>
//                        <td>".$row['FullName']."</td>
//                        <td> <img src='".$row['Avatar']."'></td>
//                        ";
//                echo "</tr>";
//            }
//            echo "</table>";
//            break;
//        case "Category":
//            $result = mysqli_query($con, "SELECT * FROM category INNER JOIN task INNER JOIN assignee 
//                ON category.IDTask=task.ID AND task.ID=assignee.IDTask
//                WHERE category.Category='".$searchquery."' AND assignee.IDUser='".$userid."'");
//            echo "<table>";
//            while($row = mysqli_fetch_array($result))
//            {
//                $result2 = mysqli_query($con, "SELECT Tag FROM tags WHERE IDTask='".$row['IDTask']."'");
//                $tags = "";
//                while ($row2 = mysqli_fetch_array($result2)) {
//                    $tags = $tags.$row2['Tag'];
//                }
//                echo "<tr>
//                        <td>".$row['Nama']."</td>
//                        <td>".$row['Deadline']."</td>
//                        <td>".$row['Status']."</td>
//                        <td>".$tags."</td>";
//                        if ($row['Status'] == 1) 
//                            echo "<input type='checkbox' checked></input>";
//                        else
//                            echo "<input type='checkbox'></input>";
//                echo "</tr>";
//            }
//            echo "</table>";
//            break;
//        case "Task":
//            $result = mysqli_query($con, "SELECT * FROM task LEFT JOIN tags INNER JOIN assignee 
//                ON task.ID=tags.IDTask AND task.ID=assignee.IDTask
//                WHERE (task.Nama='".$searchquery."' OR tags.Tag='".$searchquery."') AND assignee.IDUser='".$userid."'");
//            echo "<table>";
//            while($row = mysqli_fetch_array($result))
//            {
//                $result2 = mysqli_query($con, "SELECT Tag FROM tags WHERE IDTask='".$row['IDTask']."'");
//                $tags = "";
//                while ($row2 = mysqli_fetch_array($result2)) {
//                    $tags = $tags.$row2['Tag'];
//                }
//                echo "<tr>
//                        <td>".$row['Nama']."</td>
//                        <td>".$row['Deadline']."</td>
//                        <td>".$row['Status']."</td>
//                        <td>".$tags."</td>";
//                        if ($row['Status'] == 1) 
//                            echo "<input type='checkbox' checked></input>";
//                        else
//                            echo "<input type='checkbox'></input>";
//                echo "</tr>";
//            }
//            echo "</table>";
//            break;
//    }
    
    
   
    
?>
