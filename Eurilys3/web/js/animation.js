// Fungsi Baru
function updateDashboard(){
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
   xmlhttp.open("GET","../src/getCategory.php",true);
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

function loginCheck(){
   var userName = document.getElementById("login_username").value;
   var userPass = document.getElementById("login_password").value;
   var checkBoxChecked = document.getElementById("remember_me_check").checked;
   if(checkBoxChecked){
      checked = "true";
   }
   else{
      checked = "false";
   }
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
         if(xmlhttp.responseText=="true"){
            window.location = "src/dashboard.jsp";
         }
         else{
            alert("Wrong username or password");
         }
      }
   }  
   xmlhttp.open("POST","ServletHandler?type=Login&username="+userName+"&password="+userPass+"&checked="+checked,true);
   xmlhttp.send();
}

function add_category() {
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
   xmlhttp.open("GET","../src/addCategory.php?category="+category+"&assignee="+assignee,true);
   xmlhttp.send();
}

var activeCategory = "";
function getCategoryURL(){
   window.location = "addTask.php?idcat=" + activeCategory;
}
function allCategory(){
   document.getElementById("add_task_link").style.display = 'none';
   document.getElementById("delete_category").style.display = 'none';
   activeCategory= "";
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
   xmlhttp.open("GET","../src/getAllTask.php",true);
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
   xmlhttp.open("GET","../src/getTaskList.php?idcat="+activeCategory,true);
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
            allCategory();
         else
            categoryActive(activeCategory);
      }
   }  
   xmlhttp.open("GET","../src/deleteCategory.php?category="+activeCategory,true);
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
            allCategory();
         else
            categoryActive(activeCategory);
      }
   }  
   xmlhttp.open("GET","../src/markAsFinished.php?idtask="+idtask,true);
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
            allCategory();
         else
            categoryActive(activeCategory);
      }
   }  
   xmlhttp.open("GET","../src/deleteTask.php?idtask="+idtask,true);
   xmlhttp.send();
}

function getAssignee(idtask){
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
         document.getElementById("assignee_rtd").innerHTML = xmlhttp.responseText;
      }
   }  
   xmlhttp.open("GET","../src/getAssignee.php?idtask="+idtask,true);
   xmlhttp.send();
}

function getTag(idtask){
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
         document.getElementById("tag_rtd").innerHTML = xmlhttp.responseText;
      }
   }  
   xmlhttp.open("GET","../src/getTag.php?idtask="+idtask,true);
   xmlhttp.send();
}

function edit_task() {
   document.getElementById("deadline_rtd").innerHTML = '<input id="deadline_input" type="date" name="deadline_td"></input>';
   document.getElementById("assignee_rtd").innerHTML = '<input id="assignee_input" type="text" name="assignee_td" autocomplete="on"></input>';
   document.getElementById("tag_rtd").innerHTML = '<input id="tag_input" type="text" name="tag_td"></input>';
   document.getElementById("edit_task_button").style.display = 'none';
   document.getElementById("save_button_td").style.display = 'block';
}

function save_edit_task(idtask) {
   var deadline = document.getElementById("deadline_input").value;
   var assignee = document.getElementById("assignee_input").value;
   var tag = document.getElementById("tag_input").value;
   document.getElementById("save_button_td").style.display = 'none';
   document.getElementById("edit_task_button").style.display = 'block';
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
         document.getElementById("deadline_rtd").innerHTML = deadline;
         getTag(idtask);
         getAssignee(idtask);
      }
   }  
   xmlhttp.open("GET","../src/editTask.php?idtask="+idtask+"&deadline="+deadline+"&assignee="+assignee+"&tag="+tag,true);
   xmlhttp.send();
}

var regValid = 0;
function regCheck() {
   var username = document.getElementById("reg_username").value;
   var password = document.getElementById("reg_password").value;
   var confirm = document.getElementById("reg_confirm").value;
   var name = document.getElementById("reg_name").value;
   var email = document.getElementById("reg_email").value;
   var birthdate = document.getElementById("reg_birthdate").value;
   var avatar = document.getElementById("avatar_upload").value;
   var usernameValid;
   var passwordValid = 0;
   var confirmValid = 0;
   var nameValid = 0;
   var emailValid = 0;
   var birthdateValid = 0;
   var avatarValid = 0;
   //alert(username + " " + password+ " " + confirm + " " +email + " " + birthdate + " " +avatar);
	
   //check 
	
   if ((username.length > 4) && (username != password) && (username.indexOf(" ") == -1)){
      //AJAX
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
            if(xmlhttp.responseText=="true"){
               usernameValid = 0;
               document.getElementById("username_validation").src = "img/no.png";
            }
            else if (xmlhttp.responseText=="false") {
               usernameValid = 1;
               document.getElementById("username_validation").src = "img/yes.png";
            }
         }
      }  
      xmlhttp.open("GET","ServletHandler?type=CheckUsername&username="+username,false);
      xmlhttp.send();
   }
   else {
      usernameValid = 0;
      if (username.length == 0)
         document.getElementById("username_validation").src = "img/none.png";
      else
         document.getElementById("username_validation").src = "img/no.png";
   }
   document.getElementById("username_validation").style.display = "block";
	
   //check password
   if ((password.length > 7) && (password != username) && (password != email)) {
      document.getElementById("password_validation").src = "img/yes.png";
      passwordValid = 1;
   }
   else {
      passwordValid = 0;
      if (password.length == 0)
         document.getElementById("password_validation").src = "img/none.png";
      else
         document.getElementById("password_validation").src = "img/no.png";
   }
   document.getElementById("password_validation").style.display = "block";

   //check confirm
   if (confirm == password && password != "" && password.length > 7) {
      document.getElementById("confirm_validation").src = "img/yes.png";
      confirmValid = 1;
   }
   else{
      confirmValid = 0;
      if (confirm.length == 0)
         document.getElementById("confirm_validation").src = "img/none.png";
      else
         document.getElementById("confirm_validation").src = "img/no.png";
   }
   document.getElementById("confirm_validation").style.display = "block";
	
   //check name
   if (name.indexOf(' ') != -1) {
      var names = name.split(" ");
      if (names.length > 1){
         if (names[1] != ""){
            document.getElementById("name_validation").src = "img/yes.png";
            nameValid = 1;
         }
         else{
            document.getElementById("name_validation").src = "img/no.png";
            nameValid = 0;
         }
      }
      else{
         document.getElementById("name_validation").src = "img/no.png";
         nameValid = 0;
      }
   }
   else {
      nameValid = 0;
      if (name.length == 0)
         document.getElementById("name_validation").src = "img/none.png";
      else
         document.getElementById("name_validation").src = "img/no.png";
   }
   document.getElementById("name_validation").style.display = "block";
	
   //check email
   var emailRegEx = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/i;
   if ((email==password) || (email.search(emailRegEx) == -1)) {
      emailValid = 0;
      if (email.length == 0)
         document.getElementById("email_validation").src = "img/none.png";
      else
         document.getElementById("email_validation").src = "img/no.png";
   }
   else {
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
         if(xmlhttp.responseText=="true"){
            emailValid= 0; 
            document.getElementById("email_validation").src = "img/no.png";	
         }
         else if (xmlhttp.responseText=="false"){
            emailValid = 1;
            document.getElementById("email_validation").src = "img/yes.png";
         }
      }  
      xmlhttp.open("GET","ServletHandler?type=CheckEmail&email="+email,false);
      xmlhttp.send();
   }
   document.getElementById("email_validation").style.display = "block";
	
   //check birthday
   if (birthdate != "") {
      if (birthdate.indexOf("-") != -1)
         var dates = birthdate.split("-");
      else
         var dates = birthdate.split("/");
      var birth = new Date(dates[0], dates[1]-1, dates[2]);
      var today = new Date();
      if (birth.getTime() > today.getTime()){
         document.getElementById("birthdate_validation").src = "img/no.png";
         birthdateValid = 0;
      }
      else{
         document.getElementById("birthdate_validation").src = "img/yes.png";
         birthdateValid = 1;
      }
   }
   else {
      document.getElementById("birthdate_validation").src = "img/none.png";
      regValid = 0;
   }
   document.getElementById("birthdate_validation").style.display = "block";
	
   //check avatar
   var extension = avatar.split('.');
   if ( (extension[1] == "jpg") || (extension[1] == "jpeg") ) {
      document.getElementById("avatar_validation").src = "img/yes.png";
      avatarValid = 1;
   }
   else {
      avatarValid = 0;
      if (avatar.length == 0)
         document.getElementById("avatar_validation").src = "img/none.png";
      else
         document.getElementById("avatar_validation").src = "img/no.png";
   }
   document.getElementById("avatar_validation").style.display = "block";
   if (usernameValid == 1 && passwordValid == 1 && confirmValid == 1 && 
      nameValid == 1 && emailValid == 1 && birthdateValid == 1 && avatarValid == 1){
      
      regValid = 1;
      document.getElementById("signup_button_submit").className = "link_blue_big right top10 bold";
   }
   else{
      document.getElementById("signup_button_submit").className = "link_blue_big_disabled right top10 bold";
      regValid = 0;
   }
}

function signup() {
   if (regValid == 1) {
      document.getElementById("signup_form").submit();
   }
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

function addTaskCheck(){

}
function checkTaskName() {
   var taskName = document.getElementById('task_name_input').value;
   var isAnySpecialCharacter = 0;
   var iChars = "~=-_^&.\\|*|,\":<>[]{}`\';()@&$#%";
   for (var i = 0; i < taskName.length; i++) {
      if (iChars.indexOf(taskName.charAt(i)) != -1){
         isAnySpecialCharacter = 1; 
         break;
      }
   }
	
   document.getElementById('taskname_validation').style.display = "block";
   if (taskName.length == 0){
      document.getElementById('taskname_validation').src = "../img/none.png";
   }
   else if ((taskName.length > 25) || (isAnySpecialCharacter == 1)) {
      //tidak valid
      document.getElementById('taskname_validation').src = "../img/no.png";
   }
   else {
      //valid
      document.getElementById('taskname_validation').src = "../img/yes.png";
   }
}

function checkTaskAttachment() {
   var attachmentName = document.getElementById('attachment_upload').value;
   document.getElementById('task_attachment_validation').style.display = "block";
   if (attachmentName.indexOf(".") != -1) {
      //valid
      document.getElementById('task_attachment_validation').src = "../img/yes.png";
   }
   else {
      //not valid
      document.getElementById('task_attachment_validation').src = "../img/none.png";
   }
}

function checkDeadline(){
   var deadline = document.getElementById("deadline_input").value;
   if (deadline != "") {
      if (deadline.indexOf("-") != -1)
         var dates = deadline.split("-");
      else
         var dates = deadline.split("/");
      var dead = new Date(dates[0], dates[1]-1, dates[2]);
      var today = new Date();
      if (dead.getTime() < today.getTime()-86400000){
         document.getElementById("deadline_validation").src = "../img/no.png";
      }
      else{
         document.getElementById("deadline_validation").src = "../img/yes.png";
      }
   }
   else {
      document.getElementById("deadline_validation").src = "../img/none.png";
   }
   document.getElementById("deadline_validation").style.display = "block";
}