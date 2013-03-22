/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function newtaskdetail(idlast){
     var xmlhttp;
    if(window.XMLHttpRequest)
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
                 // $('#dynamic_content-of-div').empty();
                   var response = xmlhttp.responseText;
                    if (response !==""){
                          alert(response);
                          var result = eval('('+xmlhttp.responseText+')');
                          
                         
                            
                      window.location.href= "../src/taskdetail_file.php?ct="+result[0]['cat_task_name']+"&tm="+result[0]['task_name']+"&ts="+result[0]['task_status']+"&td="+result[0]['task_deadline']+"&ttm="+result[0]['task_tag_multivalue']+"&c="+result[0]['checkbox'];               
                    }else{
                            
                    }
           
          
          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
     xmlhttp.open("GET","../php/getnewtaskdetail.php?t="+idlast,true);
     xmlhttp.send();
    
}

function add_task(){
    alert("fungsi kepanggil");
            var task_name = document.getElementById("task_name_input").value;
            var attachment = document.getElementById("attachment_upload").value;
            var deadline = document.getElementById("deadline_input").value;
            var assignee = document.getElementById("assignee_input").value;
            var tag =  document.getElementById("tag_input").value;
    alert("task_name"+task_name);
    var xmlhttp;
    if(window.XMLHttpRequest)
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

                    var response = xmlhttp.responseText;
                    if (response !==""){
                          //  alert(response);
                         
                         var result = eval('('+xmlhttp.responseText+')');
                         alert (result['last_idx']);
                        newtaskdetail(result.lastidx);    
                       // window.location.href= "../src/getnewtaskdetail.php?id="+result.last_idx;               
                    }else{
                            
                    }
           

          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
      xmlhttp.open("GET","../php/tambahtask.php?t="+task_name+"&a="+attachment+"&d="+deadline+"&as="+assignee+"&tag="+tag,true);
      xmlhttp.send();
      
  //alert("fungsi kepanggil abis send");
   
}