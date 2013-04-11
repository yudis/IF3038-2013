<%@ include file="header.jsp" %>
<%
    String kategori;
    if (request.getParameter("kategori") != null) {
        kategori = request.getParameter("kategori");
    } else {
        kategori = "1";
    }

%>
<div id="isi">
    <div id="leftsidebar">
        <b>CREATE NEW TASK</b>
        <img src="image/leftmenu.png"/>
    </div>

    <div id="rightsidebar">
        <div id="wrapper-left">
            <form class="task" name="MakeForm" method="post" onSubmit="checkSubmission(this, event)" enctype="multipart/form-data" action="AddFile">
                <h1>Fill Details</h1>
                <ul>
                    <li>
                        <label for="tugas" >Nama Tugas</label>
                        <input id="tugas" name="tugas" onkeyup="checkNamaTugas()" type="text" maxlength="25"/><img src="image/false.png" alt="False04" id="nlengkappic"/><br>
                    </li>
                    <li>
                        <label for="filebutton1">Attachment 1</label>
                        <input id="filebutton1" name="filebutton" type="file" onChange="checkFile('filebutton1')"/>
                    </li>	
                    <li>
                        <label for="filebutton2">Attachment 2</label>
                        <input id="filebutton2" name="filebutton" type="file" onChange="checkFile('filebutton2')"/>
                    </li>
                    <li>
                        <label for="filebutton3">Attachment 3</label>
                        <input id="filebutton3" name="filebutton" type="file" onChange="checkFile('filebutton3')"/>
                    </li>
                    <li id="tmptAssignee">
                        <label for="asignee">Assignee</label>
                        <input id="asignee1" name="asignee" type="text" onKeyUp="searchSuggest(this.id)"/>
                        <button id="btn" class="task" name="addButton" type="button" onclick="add()"><b>Add</b></button>
                    </li>
                    <li id="layer1">
                    </li>
                    <li>
                        <label for="tag">Tag</label>
                        <input id="tag" name="tag" type="text" size="20"/><br>
                        <span>*dipisahkan dengan ","</span>
                    </li>							
                    <li>
                        <label for="deadline">Deadline</label>
                        <input id="demo1" name="deadline" type="text" size="25"/>
                        <a href="javascript:NewCssCal('demo1','yyyymmdd')"><img src="image/cal.gif" alt="Pick a date"/></a>
                    </li>
                    <li>
                        <input type="text" name="kategori" class="hidden" value="<%= kategori%>"/>
                        <input type="text" name="user" class="hidden" value="RAYMOND"><!-- $_SESSION['bananauser'];%>"/> TODO!!-->
                        <button class="task" name="submitbutton" type="submit"><b>Submit</b></button>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
<div id="footer" class="home">
    <p>&copy; Copyright 2013. All rights reserved<br>
        Chalkz Team<br>
        Yulianti - Adriel - Amelia</p>			
</div>
</div>
</body>

</html>
