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
        <script type="text/javascript" src="ajax.js"></script>
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
                $category = "SELECT category.IDCategory,category.CategoryName FROM assignment,task,category WHERE assignment.Username=\"" . $_COOKIE['UserLogin'] . "\" AND assignment.IDTask=task.IDTask AND task.IDCategory=category.IDCategory
        UNION DISTINCT
        SELECT category.IDCategory,category.CategoryName FROM authority,category WHERE authority.Username=\"" . $_COOKIE['UserLogin'] . "\" AND authority.IDCategory=category.IDCategory
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

                        <a class="listTugas" onclick="showRinci();">
                            <br><b>TaskName : </b><br>
                            <?php
                                echo($data['TaskName']);
                            ?>
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
                                    ?>
                                    <br>
                                    <?php
                                }
                            }
                            ?>
                            <br><b>Status : </b><br><input type="checkbox" id="taskstat
                            <?php
                                echo($i);
                            ?> "
                            <?php
                            if ($data['Status'] == "done")
                            {?>
                            checked="checked"
                            <?php }
                            ?>
                            onclick="changeStatus(this,<?php echo($data['IDTask']);?>,<?php echo($i);?>);">
                            <span id="checkedvalue<?php echo($i);?>">
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



                    <datalist id="assignee">
                        <option value="Frilla" />
                        <option value="Stefan" />
                        <option value="Timo" />
                        <option value="Yosef" />
                        <option value="Hasby" />
                    </datalist>

                    <div class="tugas" id ="edittugas">
                        <form>
                            Name: Nama Task<br/>
                            Attachment: <div class="attachment"><input id="upload" type="file"></div><br/>
                            Deadline: <input type="date"><br/>
                            Assignee: <div class="assignee"><input type="text" list="assignee"></div><br/>
                            Tag: <div class="tag"> <input type="text"></div> <br/>
                            Comment: <br/>
                            <div class="komentar">Dangerous criminal. Proceed with caution.</div><br/>
                        </form> <br/>
                        <a onclick="showRinci()" class="button">save</a><br/>
                    </div>

                    <div id="wanted">
                        <img src="img/kertas2.png">
                    </div>


                    <div id='add'>
                        Category Name:<br/> <input type='text' id='cate'><br/>
                        User:<br/> <input type='text'><br/>
                        <input type="submit" onclick="addCat();" value="create">
                        <input type="submit" onclick="restore();" value="cancel">
                    </div>
                    </body>  

                    </body>
                    <script type="text/javascript" src="script.js"></script>
                    <?php
                } else { // jika koneksi database tidak berhasil
                    die('Database connection error');
                }
                ?>
                </html>
