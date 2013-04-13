
function getprivileged(category,task,activeuser){
   // alert("fungsi getalkategori");
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
    
     xmlhttp.onreadystatechange=function()
      {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
                    //alert("nyampe result");
                 // $('#dynamic_content-of-div').empty();
                   var response = xmlhttp.responseText;
                    if (response !==""){
                         // alert(response);
                       var result = eval('('+xmlhttp.responseText+')');
                       return result[0]['aut_privileged'];
                      
                         
                            
                      //  window.location.href= "../src/taskdetail_file.php?t="+PROGIN;               
                    }else{
                       
                    }
           
          
          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
     xmlhttp.open("GET","../php/getprivileged.jsp?namakategori="+category+"&namatask="+task+"&namauser="+activeuser,true);
     xmlhttp.send();
    
    
    
}

