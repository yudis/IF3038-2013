/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function add_category(kategori,arrofassignee){

}

function set_assignee(element){
    var xmlhttp;
   var asignee_name = element.options[element.selectedIndex].text;
    alert("nama user"+asignee_name);
   var category =document.getElementById("add_category_name").value;
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
                      // var result = eval('('+xmlhttp.responseText+')');
                       
                       
                         
                         
                            
                      //  window.location.href= "../src/taskdetail_file.php?t="+PROGIN;               
                    }else{
                            
                    }
           
          
          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
     xmlhttp.open("GET","../php/setauthorized.php?t="+category+"&na="+asignee_name,true);
     xmlhttp.send();
    
}