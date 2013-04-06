<!DOCTYPE html>

<html>

    <head>
        <script type="text/javascript">
    /* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function get_alltask(){
     var xmlhttp;     
     
    if(window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
        
    }
    else
    {// code for IE6, IE5
         xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    
     xmlhttp.onreadystatechange=function()
      {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
                   var response = xmlhttp.responseText;
                    if (response !==""){
                          //  alert(response);
                           var result = eval('('+xmlhttp.responseText+')');                      
                            alert (result);
                            
                            var container=  document.getElementById("container");
container.innerHTML = container.innerHTML + 
    "<div>"+result[0]["NAMA_TASK"]+"</div>";                            
                        //window.location.href= "../src/taskdetail_file.php?id="+result.last_idx;               
                    }else{
                            
                    }
           
          
          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
     xmlhttp.open("GET","../php/fungsiget.php",true);
     xmlhttp.send();
     
    
}    
    </script>
        
    </head>
    
    <body>

        <?php
        //$kategori = $_GET["t"];
        $con = mysqli_connect("localhost", "progin", "progin", "progin");
        if (mysqli_connect_errno($con)) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
        $sql = "SELECT * FROM tugas WHERE (KATEGORI_TASK='PROGIN')";
        if (!mysqli_query($con, $sql)) {
            die('Error: ' . mysqli_error());
        }
        $result = mysqli_query($con, $sql);

        $res = array();
        $i = 0;
        // echo "<html><body>";


        while ($row = mysqli_fetch_array($result)) {
            //echo "row ".$i;
            // print_r($row);
            array_push($res, $row);
            //echo "<br/>";
            $i++;
        }
        //print_r($res[0]['ID_TASK']);
        //echo"</body></html>";  
        ?>

        <div id="container">
        <?php
        foreach ($res as $row) {
            ?>
            <div class="task_view" id="curtask3">
                <img src="../img/done.png" id="finish_3" onclick="javascript:finishTask(3)" class="task_done_button" alt=""/>
                <div id="task_name_ltd_3" class="left dynamic_content_left"><?php echo $row['NAMA_TASK'] ?></div>
                <div id="task_name_rtd_3" class="left dynamic_content_right"> <a href="taskdetail.php?id=<?php echo $row['ID_TASK'] ?>"> Imagine Cup </a> </div>
                <br><br>
                <div id="deadline_ltd_3" class="left dynamic_content_left">Deadline</div>
                <div id="deadline_rtd_3" class="left dynamic_content_right"><?php echo $row['TANGGAL_DEADLINE'] ?></div>
                <br><br>
                <div id="tag_ltd_3" class="left dynamic_content_left">Tag</div>
                <div id="tag_rtd_3" class="left dynamic_content_right">HTML 5, CSS 3</div>
                <br>
                <div class="task_view_category"> Lomba </div>
                <br>
            </div>
    <?php
}
?>
</div>
        <div onclick="get_alltask()">Click me</div>

    </body>


    <!-- ini nanti jadiin footer -->
</html>
