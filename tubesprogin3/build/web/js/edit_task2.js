function edit_task2(tm){
       alert("fungsi getalkategori");
      // var namatask = document.getElementById().value;
    var xmlhttp;
    var kategori;
    if(window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
        
    }
    else
    {// code for IE6, IE5
         xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    window.location.href= "../src/edittask.jsp?t="+tm;    
    
}

function ubah_detail_task($tmd){
    var taskname = document.getElementById("task_name_input").value;
    var attachment = document.getElementById("attachment_upload").value;
    var deadline = document.getElementById("deadline_input").value;
    var assignee = document.getElementById("assignee_input").value;
    var tag = document.getElementById("tag_input").value;
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
                   
                  
           

          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
      xmlhttp.open("GET","../php/ubahtask.jsp?tmd="+$tmd+"&tn="+taskname+"&at="+attachment+"&d="+deadline+"&as="+assignee+"&tg="+tag,true);
      xmlhttp.send(); 
    
}