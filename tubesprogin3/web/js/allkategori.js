/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getalkategori(){
    alert("fungsi getalkategori");
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
                       
                       var container = document.getElementById('category_item');
                       
                       container.innerHTML = '';
                       
                       var length = result.length;
                       alert("panjang"+length);
                       for(var i=0;i<length;i++){
                           container.innerHTML = container.innerHTML+'<li><a href="#" onclick="get_taskkategorijs("'+result[i]['cat_name']+'")" >' +result[i]['cat_name']+'</a>'+
                                                            '<img src="../img/no.png" id="del_cat" onclick="deletecat("'+result[i]['cat_name']+'")" class="task_done_button" alt="" />'+
                                                        '</li>';
                        
                                          
					
					
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
     xmlhttp.open("GET","../php/allkategori.jsp",true);
     xmlhttp.send();
    
    
    
}

