function search(){
   var search = document.getElementById("search_box").value;
   var option = document.getElementById("search_box_filter").value;
   
   var username, category, task;
   username = category = task = "1";
   if (option == 2){ category = task = "0"; }
   else if (option == 3){ username = task = "0"; }
   else if (option == 4){ category = task = "0"; }

   window.location = "search.jsp?search=" + search + "&username=" + username + "&category=" + category + "&task=" + task;
}

function currentHeader(){
   var current = window.location.toString();
   if (current.indexOf("dashboard") != -1){
      document.getElementById("dashboard_header").className += " current_header_menu";
   }
   else if (current.indexOf("profile") != -1){
      document.getElementById("profile_header").className += " current_header_menu";
   }
}

function autoCompleteHeader(str){
   
   var option = document.getElementById("search_box_filter").value;
   var suggestion = document.getElementById("suggestion_header");
   
   var username, category, task;
   username = category = task = "1";
   if (option == 2){ category = task = "0"; }
   else if (option == 3){ username = task = "0"; }
   else if (option == 4){ category = task = "0"; }
   
   str+="&username=" + username + "&category=" + category + "&task=" + task;
   
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