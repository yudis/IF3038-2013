<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>    
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="autocomplete.css" media="screen" />
    </head>
    <body>
        <header>
            <?php
            include 'header.php';
            ?>
        </header>

        <div id="category">
            <?php
            if (connectDB()) {
                $category = "SELECT category.IDCategory,category.CategoryName,category.Creator FROM assignment,task,category WHERE assignment.Username=\"" . $_COOKIE['UserLogin'] . "\" AND assignment.IDTask=task.IDTask AND task.IDCategory=category.IDCategory
        UNION DISTINCT
        SELECT category.IDCategory,category.CategoryName,category.Creator FROM authority,category WHERE authority.Username=\"" . $_COOKIE['UserLogin'] . "\" AND authority.IDCategory=category.IDCategory
        UNION DISTINCT
        SELECT IDCategory,CategoryName,category.Creator FROM category WHERE Creator=\"" . $_COOKIE['UserLogin'] . "\" 
        ORDER BY CategoryName";
                $result = mysql_query($category);
                if ($result > 0) {
                    while ($data = mysql_fetch_array($result)) {
                        ?>
                        <div class="kategori" onclick="showList(
                        <?php
                        echo($data['IDCategory']);
                        ?>
                                );"><a>

                                <?php
                                echo($data['CategoryName']);
                                ?>                                
                            </a></div>
                        <?php
                        if ($data['Creator'] == $_COOKIE['UserLogin']) {
                            ?>
                            <img class="delcategory" src="img/delete.png" onclick="delCate(<?php echo($data['IDCategory']); ?>);">
                            <?php
                        }
                    }
                }
                ?>
            </div> 

            <div id ="listtugas" class="list">

                <?php
                $alltask = "SELECT DISTINCT task.* FROM assignment,task,category,authority WHERE assignment.Username=\"" . $_COOKIE['UserLogin'] . "\" AND assignment.IDTask=task.IDTask AND task.IDCategory=category.IDCategory OR authority.Username=\"" . $_COOKIE['UserLogin'] . "\"";
                $result = mysql_query($alltask);
                $output = "";
                $i = 0;
                if ($result > 0) {
                    while ($data = mysql_fetch_array($result)) {
                        ?>

                        <a class="listTugas" >
                            <br><b>TaskName : </b><br>
                            <div class="showRin"onclick=" showRinciTugas(<?php echo($data['IDTask']); ?>);">
                                <?php
                                echo($data['TaskName']);
                                ?>
                            </div>
                            <br><b>Deadline : </b><br>
                            <?php
                            echo($data['Deadline'] );
                            ?>
                            <br><b>Tag : </b><br>
                            <?php
                            $tag = "SELECT tag.* FROM tag,tasktag WHERE tag.IDTag=tasktag.IDTag AND tasktag.IDTask=\"" . $data['IDTask'] . "\"";
                            $results = mysql_query($tag);
                            if ($results > 0) {
                                while ($datas = mysql_fetch_array($results)) {
                                    echo($datas['TagName']);
                                    ?>,
                                    <?php
                                }
                            }
                            ?>
                            <br><b>Status : </b><br><input type="checkbox" id="taskstat
                            <?php
                            echo($i);
                            ?> "
                                                           <?php
                                                           if ($data['Status'] == "done") {
                                                               ?>
                                                               checked="checked"
                                                           <?php }
                                                           ?>
                                                           onclick="changeStatus(this,<?php echo($data['IDTask']); ?>,<?php echo($i); ?>);">
                            <span id="checkedvalue<?php echo($i); ?>">
                                <?php
                                echo($data['Status']);
                                ?>
                            </span></a>
                        <?php
                        $i++;
                    }
                }
                ?>  

            </div>

            <div id="addCat">
                <a onclick="addCategory();">+ category</a>
            </div>

            <div id='add'>

                <form id="addCatForm" method="post" action="addCat.php" enctype="multipart/form-data">
                    <br/>
                    Category Name:<br/>
                    <input type="text" id="newcat" name="newcat" required ><br>
                    User:<br/>
                    <input id="newcatuser" name="newcatuser" type="text" onkeyup="multiAutocomp(this, 'catuser.php', 'add');" onfocusin="multiAutocompClearAll()" required>
                    <br>
                    <input type="submit" id="newcatbutton" name="submit" value="create">
                    <input type="submit" onclick="restore();" value="cancel">

                    <br/>
                </form>
            </div>
        </body>  

    </body>
    <script type="text/javascript" src="script.js"></script>
        <script type="text/javascript" src="ajax.js"></script>
    <?php
} else { // jika koneksi database tidak berhasil
    die('Database connection error');
}
?>
</html>
