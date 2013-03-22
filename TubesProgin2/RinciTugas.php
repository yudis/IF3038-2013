<?php
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
?>
<html>
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="autocomplete.css" media="screen" />
        <script type="text/javascript" src="ajax.js"></script>
    </head>
    <body>
        <?php
        include 'header.php';
        ?>
        <div id="category">

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
    </body>
    <script type="text/javascript" src="script.js"></script>
 </html>