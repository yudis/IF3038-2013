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