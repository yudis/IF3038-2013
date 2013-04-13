/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function tampilinkomen(kategori,task){
     var xmlhttp;
    var komenbaru = document.getElementById("comment_textarea").value;
     
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
                   //alert("komen semuanya"+response);
                    if (response !==""){
                         // alert(response);
                       var result = eval('('+xmlhttp.responseText+')');
                       
                       var container = document.getElementById('comment_rtd');
                       container.innerHTML='';
                       container.innerHTML=container.innerHTML+'<ul >';
               
                       for(var i=0;i<result.length;i++){
                            container.innerHTML = container.innerHTML+'<li>'+result[i]['user_comment']+" : "+result[i]['comm_content']+'</li>';  
                       }
                       container.innerHTML=container.innerHTML+'</ul >';
                       
                         
                            
                      //  window.location.href= "../src/taskdetail_file.php?t="+PROGIN;               
                    }else{
                            
                    }
           
          
          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
     xmlhttp.open("GET","../php/tampilkomenforjs.jsp?namakategori="+kategori+"&namatask="+task,true);
     xmlhttp.send();
    
}
function add_komen(kategori,task){
    var xmlhttp;
    var komenbaru = document.getElementById("comment_textarea").value;
     
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
                    tampilinkomen(kategori,task);
                       
                         
                            
                      //  window.location.href= "../src/taskdetail_file.php?t="+PROGIN;               
                  
          
          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
     xmlhttp.open("GET","../php/tambahkomen.jsp?komen="+komenbaru+"&namakategori="+kategori+"&namatask="+task,true);
     xmlhttp.send();
}

 
                  