<html>

        <title> Next | ADD TASK </title>
        <link rel="stylesheet" href="../css/css.css">
        <link rel="stylesheet" href="../css/buattask.css">
        <link rel="stylesheet" href="../css/calendar.css">
        <script src="../js/calendar.js" > </script>
        <script src="buattaskController.js"></script>
        
	<?php include '../header/index.php'; ?>
    
    <body onLoad="prepare();showUserLogin();">
    
        
        <!-----------------------------BODY FILE-------------------------------->
        <div class="main">
            <div id="addtask">
            </div>
            <div>
                <form>
                    <input type="button" id="back" value="" onClick="location.href='../dashboard/'">
                </form>
            </div>
            
            <div id="formtask">
                <div id = "judulform">
                </div>
                <form id="formtask2">
                    <label>NAMA TUGAS</label>
                    <input name="task_name" type="text" placeholder="task_name" onKeyUp="name_valid(this.value)" />
                    <img src="../pict/blank.png" alt="icon1" id="namaicon"  />                    
                    
                    <label>DEADLINE</label>
                    <input type="textarea" name="year" id="yearbox" value="" onChange="dead_validating(this.parentNode)">-
                    <select name="month" id="month" onChange="dead_validating(this.parentNode)">
                    </select>-

                    <select name="day" id="day" onChange="dead_validating(this.parentNode)">
                    </select> Jam: 
                    
                    <select name="hour" id="hour" onChange="dead_validating(this.parentNode)">
                    </select>-

                    <select name="minute" id="minute" onChange="dead_validating(this.parentNode)">
                    </select>                        
					<img src="../pict/blank.png" alt="icon3" id="deadicon" />
                    
                    <label>ASSIGNEE</label>
                    <input type="textarea" name="assignee" placeholder="assignee"
                      title="Akhiri nama user dengan tanda /, jangan dipisah spasi"
                      onkeyup="auto_complete(this)" value="">
                    <img src="../pict/blank.png" alt="icon4" id="asicon" />
                      
                    <input id="autobox" disabled></input>
                    
                    <label>TAG</label>
                    <input type="textarea" name="catname" placeholder="tag" value="">
                    
                    <label>ATTACHMENT</label>
                    <div id="attach_upload">                            
                    </div>                            
					<img src="../pict/blank.png" alt="icon2" id="attaicon"  />                    
                    
                    <input type="file" name="file" id="file" onChange="validasi_file(this)">
                    <br>
                    <input class= "submitreg" name="submit" type="button" value="Submit"
                     onclick="add_tugas(this.form)">
                </form>
            </div>
        </div>
        
        <!---------------------------------FOOTER FILE------------------------------------>
        <div class="footer">
            Copyright © Ahmad Faiz - Fandi Pradana - Sigit Aji
        </div>
        
    </body>
</html>