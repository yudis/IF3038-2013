/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function autocompletename(){
    var xmlhttp;
    var keyhit = document.getElementById("add_category_assignee").value;
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
                        //alert(response);
                        var optionlist = ["Seatle","Las Vegas"];
                       var result = eval('('+xmlhttp.responseText+')');
                       
                       var length = result.length;
                       
                       var dl = document.getElementById('userlist');                       
                       dl.innerHTML="";
                       for(var i=0;i<length;i++){
                        var option = document.createElement('option');
                        option.value = result[i]['username'];
                        dl.appendChild(option);   
                       }
                       
                            
                      //  window.location.href= "../src/taskdetail_file.php?t="+PROGIN;               
                    }else{
                            
                    }
           
          
          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
     xmlhttp.open("GET","../php/namaautocomplete.jsp?key="+keyhit,true);
     xmlhttp.send();
}