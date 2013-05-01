function editProfileMode(){
   document.getElementById("edit_profile_button").style.display = "none";
   document.getElementById("save_profile_button").style.display = "block";
   document.getElementById("change_avatar").style.display = "block";
   document.getElementById("change_password").style.display = "block";
   document.getElementById("confirm_password").style.display = "block";
   var name = document.getElementById("name_edit").innerHTML;
   document.getElementById("name_edit").innerHTML = "<input name='name' type='text' value='" + name +  "'>";
   
   var birthdate = document.getElementById("birthdate_edit").innerHTML;
   document.getElementById("birthdate_edit").innerHTML = "<input name='birthdate' type='date' value='" + birthdate +  "'>";
}

function getProfileContent(username){
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
         var info = xmlhttp.responseText.split("|");
         document.getElementById("avatar_container").src = "../" + info[5];
         document.getElementById("profile_avatar_username").innerHTML = info[0];
         document.getElementById("username_edit").innerHTML = info[0];
         document.getElementById("name_edit").innerHTML = info[2];
         document.getElementById("email_edit").innerHTML = info[3];
         document.getElementById("birthdate_edit").innerHTML = info[4];
         document.getElementById("username_submit").value = info[0];
         document.getElementById("email_submit").value = info[3];
      }
   }  
   xmlhttp.open("GET","../ServletHandler?type=GetProfileInfo&username="+username,true);
   xmlhttp.send();
}

function getProfileTask(username){
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
         var task = xmlhttp.responseText.split("|@|");
         document.getElementById("currentTask").innerHTML = task[0];
         document.getElementById("finishedTask").innerHTML = task[1];
      }
   }  
   xmlhttp.open("GET","../ServletHandler?type=GetProfileTask&username="+username,true);
   xmlhttp.send();						
}

function checkViewMode(username, view){
   if (username != view){
      document.getElementById("edit_profile_button").style.display = "none";
   }
}
