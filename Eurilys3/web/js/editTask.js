function edit_task() {
   var assignee = document.getElementById("assignee_rtd").innerHTML.replace(/ /g, "");
   var deadline = document.getElementById("deadline_rtd").innerHTML.replace(/ /g, "");
   var test = assignee.split(",");
   assignee = "";
   for (var i = 0; i < test.length; i++){
      test[i] = test[i].substring(test[i].indexOf(">")+1, test[i].lastIndexOf("<"));
      assignee += test[i] + ", ";
   }
   assignee = assignee.substring(0, assignee.lastIndexOf(","));
   var tag = document.getElementById("tag_rtd").innerHTML.replace(/ /g, "");
   document.getElementById("deadline_rtd").innerHTML = '<input id="deadline_input" type="date" name="deadline_input" value="'+ deadline + '"></input>';
   document.getElementById("assignee_rtd").innerHTML = '<input id="assignee_input" type="text" name="assignee_input" value="'+ assignee +'"></input>';
   document.getElementById("tag_rtd").innerHTML = '<input id="tag_input" type="text" name="tag_input" value="'+ tag +'"></input>';
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
   xmlhttp.open("POST","../ServletHandler?type=EditTask&idtask="+idtask+"&deadline="+deadline+"&assignee="+assignee+"&tag="+tag,true);
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
   xmlhttp.open("GET","../ServletHandler?type=GetAssignee&idtask="+idtask,true);
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
   xmlhttp.open("GET","../ServletHandler?type=GetTag&idtask="+idtask,true);
   xmlhttp.send();
}

function addComment(idtask){
   var content = document.getElementById("comment_textarea").value;
   var time = Date.now();
   
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
         if (xmlhttp.responseText == "true"){
            document.getElementById("comment_textarea").value = "";
            getComment(idtask);
         }
      }
   }  
   xmlhttp.open("POST","../ServletHandler?type=AddComment&idtask="+idtask+"&time="+time+"&content="+content,true);
   xmlhttp.send();
}

var same;
function getComment(idtask){
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
         if (xmlhttp.responseText != same){
            same = xmlhttp.responseText;
            document.getElementById("comment_area").innerHTML = xmlhttp.responseText;
         }
      }
   }  
   xmlhttp.open("GET","../ServletHandler?type=GetComment&idtask="+idtask,true);
   xmlhttp.send();
}

function removeTextAreaWhiteSpace() { 
   var myTxtArea = document.getElementById('comment_textarea'); 
   myTxtArea.value = myTxtArea.value.replace(/^\s*|\s*$/g,''); 
} 