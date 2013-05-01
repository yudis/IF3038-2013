function autoComplete(str){
   // User Name
   if(document.getElementById("username_search").checked){
      str = str+"&username=1";	
   }
   else{
      str = str+"&username=0";
   }
   // Judul Kategori
   if(document.getElementById("category_search").checked){
      str = str+"&category=1";	
   }
   else{
      str = str+"&category=0";
   }
   // Task Tag
   if(document.getElementById("task_search").checked){
      str = str+"&task=1";	
   }
   else{
      str = str+"&task=0";
   }
   var suggestion = document.getElementById("suggestion");
   if (str.length <= 0){
      suggestion.innerHTML = "";
   }
   else{
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
            suggestion.innerHTML = xmlhttp.responseText;
         }
      }  
      xmlhttp.open("GET","../ServletHandler?type=AutoComplete&val="+str,true);
      xmlhttp.send();
   }
}

function getResult(){
   var str = document.getElementById("searchBox").value;
   if (str.length > 0){
      if(document.getElementById("username_search").checked){
         str = str+"&username=1";
      }	
      else {
         str = str+"&username=0";
      }
   
      if(document.getElementById("category_search").checked){
         str = str+"&category=1";
      }	
      else{
         str = str+"&category=0";
      }

      if(document.getElementById("task_search").checked){
         str = str+"&task=1";
      }
      else{
         str = str+"&task=0";
      }
      window.history.pushState(null,"","?search="+str);
   } 
   
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
   xmlhttp.open("GET","../ServletHandler?type=Search&" + window.location.search.substring(1),true);
   xmlhttp.send();
}

function onPopState(){
   if (window.location.search.length > 0){
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
      xmlhttp.open("GET","../ServletHandler?type=Search&" + window.location.search.substring(1),true);
      xmlhttp.send();
   }
   else{
      document.getElementById("dynamic_content").innerHTML = "";
   }
}

function markAsFinished2(idtask){
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
         getResult();
      }
   }  
   xmlhttp.open("POST","../ServletHandler?type=MarkAsFinished&idtask="+idtask,true);
   xmlhttp.send();
}


function deleteTask2(idtask){
   alert("hello");
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
         alert("fiuh");
         getResult();
      }
   }  
   xmlhttp.open("POST","../ServletHandler?type=DeleteTask&idtask="+idtask,true);
   xmlhttp.send();
}