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
<<<<<<< HEAD
        
        <div id ="listtugas" class="list">
            <a class="listTugas" onclick="showRinci();"><img src="img/tugas.png"/><br/>
            NAMA TUGAS<br/>
            DEADLINE<br/></a> 
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a onclick='showBuat()' class='addTask'></a>
        </div>
        <div id ="listtugas2" class="list">
            <a class="listTugas" onclick="showRinci();"></a> 
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a onclick='showBuat()' class='addTask'></a>
=======



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
>>>>>>> a9daf28b44d42e718ee14f38bdbd005b216d01d7
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
</html>
