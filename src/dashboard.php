<html>

      <title> Next | Dashboard </title>
<?php include 'header.php'; ?>
          
<!----------------------------------------- body ----------------------------------------->                           
    <body onLoad="showUserLogin() ">
      <div class="main">
      <div id="addcat" onClick="popup('popUpDiv')">
      </div>
      <a href="buattask.php">
      <div id="addtask" style="visibility: hidden";>
      </div>
      </a>
      <div id="category">
      <div id="listcat" onClick="taskawal(6); showTask(); " onMouseOver="removeTask(); hideAddTask() ">
      <div id="tulcat">
                    ALL
                    </div>
      </div>
      <div id="listcat" onClick="addTask(1); showTask(); " onMouseOver="removeTask(); hideAddTask() ">
      <div id="tulcat">
                    KAP
                    </div>
      </div>
      <div id="listcat" onClick="addTask(3); showTask(); " onMouseOver="removeTask(); hideAddTask()">
      <div id="tulcat">
                    MSDI
                    </div>
      </div>
      <div id="listcat" onClick="addTask(2); showTask();" onMouseOver="removeTask(); hideAddTask()">
      <div id="tulcat">
                    PROGIN
                    </div>
      </div>
      </div>
            
      <div id="task">
      <a href="rinciantugas.php">
      <div id="div1">
                    
      </div>
      </a>
      </div>
            
      <div id="blanket" style="display:none;"></div>
      <div id="popUpDiv" style="display: none;">
      <div id="newcategory">
      <div id="closecat">
        <a href="#" onClick="popup('popUpDiv')" >CLOSE</a>
      </div>
      <div id="cattitle">ADD CATEGORY</div>
      <div id="elcategory">
        <form action="dashboard.php">
            <label>category name</label>
            <input name="catname" placeholder="category name">
            <label>assignee</label>
            <input name="catass" placeholder="assignee"></br></br>
            <input class= "submitreg" name="submit" type="submit" value="Submit">
        </form>
                    </div>
                </div>
            </div>
        </div>
        
<!----------------------------------------- footer ----------------------------------------->                              
        <div class="footer">
            Copyright © Ahmad Faiz - Fandi Pradana - Sigit Aji
        </div>
        
    </body>
</html>