<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<?php
require_once('config.php');
session_start();
?>
<html>
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <script type="text/javascript" src="script.js"></script>
    </head>
    <body>

        <?php
        if (connectDB()) {
            ?>  

            <header>
                <a href="dashboard.php" title="Home"><img id="logo-small" src="img/Logo_Small2.png" alt="" /></a>
                <div id="dashboard"><a title="Go to Dashboard" href="dashboard.php">Dashboard</a></div>
                <div id="profile"><a title="Go to Profile" href="profile.php">My Profile</a></div>
                <div id="logout"><a title="Log out from here" href="index.php">Log Out</a></div>
                <form id="search">
                    <input type="text" name="Search" id="box">
                    <input type="submit" value="Search">
                </form>
            </header>

            <div id="category">
                <input type="hidden" name="hidden" id="hidden" value="">
                <?php
                $category = "SELECT category.IDCategory,category.CategoryName FROM assignment,task,category WHERE assignment.Username=\"admin\" AND assignment.IDTask=task.IDTask AND task.IDCategory=category.IDCategory
UNION DISTINCT
SELECT category.IDCategory,category.CategoryName FROM authority,category WHERE authority.Username=\"admin\" AND authority.IDCategory=category.IDCategory
ORDER BY CategoryName";
                $result = mysql_query($category);
                if ($result > 0) {
                    while ($data = mysql_fetch_array($result)) {
                        ?>
                        <div class="kategori" onclick="showList(
                                <?php
                                echo($data['CategoryName']);
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

                $task = "SELECT * FROM task WHERE IDCategory=1";
                $result = mysql_query($task);
                
                if ($result > 0) {  
                    while ($data = mysql_fetch_array($result)) {
                        ?>
                        <a class="listTugas" onclick="showRinci();"></a> 
                        <?php
                    }
                }
                ?>
                <a onclick='showBuat()' class='addTask'></a>
                
            </div>

            <div id="addCat">
                <a onclick="addCategory();">+ category</a>
            </div>

            <div class="tugas" id="rincitugas">
                Name: Nama Tugas <br/>
                Attachment: 
                <div class="attachment">
                    <a href="img/file.zip">file.zip</a><br/>
                    <a href="img/badge.png">picture.png</a><br/>
                </div><br/>
                Deadline: 17-12-2014<br/>
                Assignee: <a href="" class="asignee">Timo</a>, <a href="" class="asignee">Stefan</a>, <a href="" class="asignee">Frilla</a><br/>
                Tag: <a href="" class="tag">dangerous</a>, <a href="" class="tag">novice</a> <br/>
                <br/>Comment:<br/>
                <div class="komentar">Dangerous criminal. Proceed with caution.</div><br/>
                <form>
                    <textArea></textarea>
                                                <input type="button" name="submit" value="submit">
                                            </form>
                                            <br/><br/>
                                            <a onclick="showEdit();" class="button">edit</a><br/>
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
                                    
                                        <div class="tugas" id="buattugas">
                                            <br/>
                                            Name: <div class="nama"><input type="text" id="namaTask"></div><br/>
                                            Attachment: <div class="attachment"><input type="file"></div><br/>
                                            Deadline: <div class="deadline"><input type="date"></div><br/>
                                            Assignee: <div class="asignee"><input type="text"></div><br/>
                                            Tag: <div class="tag"> <input type="text"></div> <br/>
                                            <br/>
                                            <a onclick="createTask();" class="button">create</a><br/>
                                        </div>
                                    
                                    <div id='add'>
                                        Category Name:<br/> <input type='text' id='cate'><br/>
                                        User:<br/> <input type='text'><br/>
                                        <input type="submit" onclick="addCat();" value="create">
                                        <input type="submit" onclick="restore();" value="cancel">
                                    </div>
                                </body>
                                
        <?php
    } else { // jika koneksi database tidak berhasil
        die('Database connection error');
    }
    ?>
            
</html>