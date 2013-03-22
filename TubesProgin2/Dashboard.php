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

        </div> 

        <div id ="listtugas" class="list">

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
</html>
