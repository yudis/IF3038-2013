/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function getaltask(kategori){
   // alert("fungsi getaltaskkepanggil");
   // alert("kategoriisinya"+kategori)
    var xmlhttp;
   // var kategori;
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
                     //  alert(response);
                       
                       var result = eval('('+xmlhttp.responseText+')');
                       var container = document.getElementById('dynamic_content');
                       container.innerHTML = '';
                       if(kategori!=""){
                            container.innerHTML = container.innerHTML+'<ul>'+'<li><div id="add_task_link"><a href="../src/addtask.jsp" >+new task</a></div></li>';
                       }else{
                           var uls =  document.createElement('ul');
                           container.appendChild(uls);
                       }
                       
                       
                     
                       var length = result.length;
                       //alert(result[0]['cat_task_name']);
                       
                       //alert("panjang"+length);
                       var activepage;
                       for(var i=0;i<length;i++){
                           if(kategori==""){
                               activepage='dashboard';
                           }else{
                               activepage='lala';
                            }
                           var status;
                            if(result[i]['task_status']==0){
                             //   alert("not finisih");
                                status = 'Not Finish';
                            }else{
                               // alert("Firnish");
                                status = 'Finish';
                            }
                           var lis = document.createElement('li');
                           lis.innerHTML =  '<br><br>'+

                                            '<img src="../img/done.png" id="finish_1" onclick="delete_task(\''+result[i]['task_name']+'\',\''+result[i]['cat_task_name']+ '\')" class="task_done_button" alt="" />'+
                                            '<div id="task_name_ltd" class="left dynamic_content_left">Task Name</div>'+
                                            '<div id="task_name_rtd" class="left dynamic_content_right">  <a href="#" onclick="tampil_edit_task(\''+result[i]['task_name']+ '\')">' +result[i]['task_name']+'</a> </div>'+
                                                   
                                                    
                                                    
                                            '<br>'+
                                            '<div id="deadline_ltd" class="left dynamic_content_left">Deadline</div>'+
                                            '<div id="deadline_rtd" class="left dynamic_content_right"></div>'+
                                            '<br>'+
                                            '<div id="tag_ltd" class="left dynamic_content_left">Tag</div>'+
                                            '<div id="tag_rtd" class="left dynamic_content_right">'+result[i]['task_tag_multivalue']+'</div>'+
                                            '<br>'+
                                            '<div id="tag_ltd" class="left dynamic_content_left">Status</div>'+
                                            '<div id="tag_rtd" class="left dynamic_content_right">'+status+'</div>'+
                                                    
                                                   
                                            
                                            '<br>'+
                                            '<div id="tag_ltd" class="left dynamic_content_left">Checkbox</div>'+
                                           
                                            '<div id="tag_rtd" class="left dynamic_content_right"><input type="checkbox" onclick="check_value(\''+result[i]['task_name']+'\',\''+result[i]['cat_task_name']+'\',\''+activepage+ '\')" name="checkboxtask">'+result[i]['checkbox']+'</div>'+
                                            '<br>'+
                                            '<div class="task_view_category">'+result[i]['cat_task_name']+'</div>'+
                                            '<br>';
                        	uls.appendChild(lis);
                       }
                          
                      // container.innerHTML = container.innerHTML+'aa</ul>';
                         
                            
                      //  window.location.href= "../src/taskdetail_file.php?t="+PROGIN;               
                    }else{
                         
                    }
           
          
          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
     xmlhttp.open("GET","../php/gettaskphp.jsp?namakategori="+kategori,true);
     xmlhttp.send();
    
    
    
}
