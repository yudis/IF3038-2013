function check_value(namatask,kategori){
 
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
                        // alert (result['last_idx']);
                        if(result[0]['checkbox']==0){
                            changevalue(1,namatask,kategori);
                        }else{
                            changevalue(0,namatask.kategori);
                            
                        }
                        
                       // window.location.href= "../src/getnewtaskdetail.php?id="+result.last_idx;               
                    }else{
                            
                    }
           

          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
      xmlhttp.open("GET","../php/getcheckboxvalue.php?t="+namatask,true);
      xmlhttp.send();
      
}
function changevalue(value,namatask,kategori){
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
                         
                        // var result = eval('('+xmlhttp.responseText+')');
                        // alert (result['last_idx']);
                        get_taskkategorijs(kategori);
                       // window.location.href= "../src/getnewtaskdetail.php?id="+result.last_idx;               
                    }else{
                            
                    }
           

          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
      xmlhttp.open("GET","../php/changestatusncheckbox.php?t="+value+"&nama="+namatask,true);
      xmlhttp.send();   
    
}

function edit_task() {
	document.getElementById("deadline_rtd").innerHTML = '<input type="text" name="deadline_td"></input>';
	document.getElementById("assignee_rtd").innerHTML = '<input type="text" name="assignee_td" autocomplete="on"></input>';
	document.getElementById("tag_rtd").innerHTML = '<input type="text" name="tag_td"></input>';
	document.getElementById("edit_task_button").style.display = 'none';
	document.getElementById("save_button_td").style.display = 'block';
}

function save_edit_task() {
	document.getElementById("deadline_rtd").innerHTML = '21/2/2012';
	document.getElementById("assignee_rtd").innerHTML = 'Sharon';
	document.getElementById("tag_rtd").innerHTML = 'HTML 5, CSS 3';
	document.getElementById("save_button_td").style.display = 'none';
	document.getElementById("edit_task_button").style.display = 'block';
}

function tampil_edit_task(namatask){
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
                        alert("taskdetail"+response);
                        var result = eval('('+xmlhttp.responseText+')');
                      
                         alert(result);
                            
                      window.location.href= "../src/taskdetail_file.php?ct="+result[0]['cat_task_name']+"&tm="+result[0]['task_name']+"&ts="+result[0]['task_status']+"&td="+result[0]['task_deadline']+"&ttm="+result[0]['task_tag_multivalue']+"&c="+result[0]['checkbox'];               
                    }else{
                            
                    }
           
          
          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
     xmlhttp.open("GET","../php/gettaskdetail.php?t="+namatask,true);
     xmlhttp.send();    
    
    
}