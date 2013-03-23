<?php

require_once('config.php');
?>
<?php

session_start();

if (connectDB()) {
    if (isset($_GET['category'])) {
        $task = 'SELECT * FROM task WHERE IDCategory=\'' . $_GET['category'].'\''; 
        $crea = 'SELECT Creator FROM category WHERE IDCategory=\'' . $_GET['category'].'\' UNION DISTINCT SELECT Username FROM authority WHERE IDCategory=\'' . $_GET['category'].'\''; 
        $creator='';
        $results = mysql_query($crea);
        if ($results > 0) {
            while ($data = mysql_fetch_array($results)) {
                
                if ($data['Creator'] == $_COOKIE['UserLogin'])
                {$creator = $data['Creator'];}
                
            }
            
        }
        
        $result = mysql_query($task);
        $output = "";
              
        $i=0;
        if ($result > 0) {
            while ($data = mysql_fetch_array($result)) {
                
                $output = $output . "<a class=\"listTugas\">";
                if ($data['Creator'] == $_COOKIE['UserLogin']) {
                $output=$output."<img class=\"deltask\" src=\"img/delete.png\" onclick=\"deleteTaskYeys(".$data['IDTask'].");\">";
                    }
                    $output=$output."
                    <br><b>TaskName : </b><br><div class=\"showRin\" onclick=\" showRinciTugas(".$data['IDTask']."\">".$data['TaskName']."</div>
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
           
        }
        if ($creator == $_COOKIE['UserLogin']) {$output = $output . "<a onclick=\"showBuatTugas(".$_GET['category'].",'".$_COOKIE['UserLogin']."');\" class='addTask'></a>";}
        
        echo($output);
    }
} else {
    die('Database connection error');
}
?>
