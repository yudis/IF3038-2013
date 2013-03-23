/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function get_alltask(){
    
    
}
function get_taskkategorijs(kategori){
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
                       var container = document.getElementById('dynamic_content');
                       container.innerHTML = '';
                       container.innerHTML = container.innerHTML+'<ul>'+'<li><div id="add_task_link"><a href="../src/addtask.php" >+new task</a></div></li>';
                       
                        var length = result.length;
                        for(var i= 0 ; i<length ; i++){
                            var status;
                            if(result[i]['task_status']==0){
                             //   alert("not finisih");
                                status = 'Not Finish';
                            }else{
                               // alert("Firnish");
                                status = 'Finish';
                            }
                            var task_name = result[i]['task_name'];
                           container.innerHTML = container.innerHTML +
                           
                            '<li>'+
                            
                            '<br><br>'+
                            '<img src="../img/done.png" id="finish_1" onclick="finishTask('+i+')" class="task_done_button" alt="" />'+
                            '<div id="task_name_ltd" class="left dynamic_content_left">Task Name</div>'+
                            '<div id="task_name_rtd" class="left dynamic_content_right"> <a href="#" onclick="tampil_edit_task('+result[i]['task_name']+')">'+result[i]['task_name']+'</a> </div>'+
                            
                            '<br>'+
                            '<div id="deadline_ltd" class="left dynamic_content_left">Deadline</div>'+
                            '<div id="deadline_rtd" class="left dynamic_content_right">'+result[i]['task_deadline']+'</div>'+
                            '<br>'+
                            '<div id="tag_ltd" class="left dynamic_content_left">Tag</div>'+
                            '<div id="tag_rtd" class="left dynamic_content_right"> '+result[i]['task_tag_multivalue']+'</div>'+
                            '<br>'+
                            '<div id="tag_ltd" class="left dynamic_content_left">Status</div>'+
                            '<div id="tag_rtd" class="left dynamic_content_right">'+status+'</div>'+
                            '<br>'+
                            '<br>'+
                            '<div id="tag_ltd" class="left dynamic_content_left">Checkbox</div>'+
                            '<div id="tag_rtd" class="left dynamic_content_right"><input type="checkbox" name="checkboxtask"> '+result[i]['checkbox']+'</div>'+
                            '<br>'+
                            '<div class="task_view_category">'+result[i]['cat_task_name'] +'</div>'+
			    '<br>'+
                           
                            
                            '</li>';  
                            //onclick="check_value('+'<?php echo $eachtask['+'task_name'+']?>'+','+'<?php echo $eachtask['+'cat_task_name'+']?>'+')"
                        }
                        container.innerHTML = container.innerHTML+'</ul>';
                       
                         
                            
                      //  window.location.href= "../src/taskdetail_file.php?t="+PROGIN;               
                    }else{
                            
                    }
           
          
          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
     xmlhttp.open("GET","../php/gettaskphp.php?t="+kategori,true);
     xmlhttp.send();
    
}


