<?php

require_once('config.php');
?>
<?php

session_start();

if (connectDB()) {
    if (isset($_GET['category'])) {
        $task = 'SELECT * FROM task WHERE IDCategory=\'' . $_GET['category'].'\''; 
        $crea = 'SELECT Creator FROM task WHERE IDCategory=\'' . $_GET['category'].'\''; 
        $result = mysql_query($crea);
        if ($result > 0) {
            while ($data = mysql_fetch_array($result)) {
                $creator = $data['Creator'];
            }
            
        }
        $result = mysql_query($task);
        $output = "";
              
        $i=0;
        if ($result > 0) {
            while ($data = mysql_fetch_array($result)) {
                $output = $output . "<a class=\"listTugas\" onclick=\"showRinci();\">
                    <br><b>TaskName : </b><br>".$data['TaskName']."
                    <br><b>Deadline : </b><br>".$data['Deadline']."
                    <br><b>Tag : </b><br>";
                        $tag = "SELECT tag.* FROM tag,tasktag WHERE tag.IDTag=tasktag.IDTag AND tasktag.IDTask=\"" . $data['IDTask'] . "\"";
                        $results = mysql_query($tag);
                        if ($results > 0) {
                            while ($datas = mysql_fetch_array($results)) {
                                $output= $output.$datas['TagName']."<br>";
                            }
                        }
                    

                    $output=$output."<br><b>Status : </b><br><input type=\"checkbox\" id=\"taskstat".$i."\"";
                    if ($data['Status']=="done")
                         $output=$output."checked=\"checked\"";

                    $output=$output."onclick=\"changeStatus(this,".$data['IDTask'].",".$i.");\">".
                    "<span id=\"checkedvalue".$i."\">".
                    $data['Status']."</span></a>";
                    $i++;            
                    
            }
           if ($creator == $_COOKIE['UserLogin']) {$output = $output . "<a onclick='showBuat()' class='addTask'></a>";}
            
        }
        
        echo($output);
    }
} else {
    die('Database connection error');
}
?>
