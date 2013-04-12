// Fungsi Baru
function updateNavbar(username){
   var xmlhttp;
   if (window.XMLHttpRequest)
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
         document.getElementById("category_item").innerHTML = xmlhttp.responseText;
      }
   }  
   xmlhttp.open("GET","../ServletHandler?type=GetCategory&username="+username,true);
   xmlhttp.send();
}

// Fungsi Baru

function toggle_visibility(id) {
   var e = document.getElementById(id);
   if(e.style.display == 'block')
      e.style.display = 'none';
   else
      e.style.display = 'block';
}

function addCategory(username) {
   var category = document.getElementById("add_category_name").value;
   var assignee = document.getElementById("add_category_asignee_name").value;
   var xmlhttp;
   if (window.XMLHttpRequest)
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
         document.getElementById("add_category_name").value = "";
         document.getElementById("add_category_asignee_name").value ="";
      }
   }  
   xmlhttp.open("POST","../ServletHandler?type=AddCategory&username="+username+"&category="+category+"&assignee="+assignee,true);
   xmlhttp.send();
}

var activeCategory = "";
function getCategoryURL(){
   window.location = "addTask.jsp?idcat=" + activeCategory;
}

var user ="";
function allCategory(username){
   document.getElementById("add_task_link").style.display = 'none';
   document.getElementById("delete_category").style.display = 'none';
   activeCategory= "";
   user = username;
   var xmlhttp;
   if (window.XMLHttpRequest)
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
         document.getElementById("dynamic_content").innerHTML = xmlhttp.responseText;
      }
   }  
   xmlhttp.open("GET","../ServletHandler?type=GetAllTask&username="+username,true);
   xmlhttp.send();
}

function categoryActive(cat){
   document.getElementById("add_task_link").style.display = 'block';
   document.getElementById("delete_category").style.display = 'block';
   activeCategory = cat;
   updateTaskList();
}

function updateTaskList(){
   var xmlhttp;
   if (window.XMLHttpRequest)
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
         document.getElementById("dynamic_content").innerHTML = xmlhttp.responseText;
      }
   }  
   xmlhttp.open("GET","../ServletHandler?type=GetTaskList&idcat="+activeCategory,true);
   xmlhttp.send();
}

function deleteCategory(){
   var xmlhttp;
   if (window.XMLHttpRequest)
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
         if (activeCategory == "")
            allCategory(user);
         else
            categoryActive(activeCategory);
      }
   }  
   xmlhttp.open("POST","../ServletHandler?type=DeleteCategory&idcat="+activeCategory,true);
   xmlhttp.send();
}

function addTask(){
   document.getElementById("addtask_form").submit();
}

function markAsFinished(idtask){
   var xmlhttp;
   if (window.XMLHttpRequest)
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
         if (activeCategory == "")
            allCategory(user);
         else
            categoryActive(activeCategory);
      }
   }  
   xmlhttp.open("POST","../ServletHandler?type=MarkAsFinished&idtask="+idtask,true);
   xmlhttp.send();
}

function deleteTask(idtask){
   var xmlhttp;
   if (window.XMLHttpRequest)
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
         if (activeCategory == "")
            allCategory(user);
         else
            categoryActive(activeCategory);
      }
   }  
   xmlhttp.open("POST","../ServletHandler?type=DeleteTask&idtask="+idtask,true);
   xmlhttp.send();
}

function finishTask(i) {
   if (i == 1) {
      document.getElementById("curtask1").style.opacity = "0";
      document.getElementById("curtask2").style.top = "20px";
      document.getElementById("curtask3").style.top = "200px";
      document.getElementById("curtask4").style.top = "380px";
      document.getElementById("curtask5").style.top = "560px";
   }
		
   else {
      if (i == 2) {
         document.getElementById("curtask2").style.opacity = "0";
         document.getElementById("curtask3").style.top = "200px";
         document.getElementById("curtask4").style.top = "380px";
         document.getElementById("curtask5").style.top = "560px";
      }
      else {
         if (i==3) {
            document.getElementById("curtask3").style.opacity = "0";
            document.getElementById("curtask4").style.top = "380px";
            document.getElementById("curtask5").style.top = "560px";
         }
         else if (i == 4) {
            document.getElementById("curtask4").style.opacity = "0";
            document.getElementById("curtask5").style.top = "560px";
         }
         else if (i == 5){
            document.getElementById("curtask5").style.opacity = "0";
         }
      }
   }
}