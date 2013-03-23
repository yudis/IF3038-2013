/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function tampil_profile(){
    alert("fungsi kepanggil");
        
 
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
                        var container = document.getElementById('dynamic_content');
                       container.innerHTML = '';
                      
                       
                        var length = result.length;
                    
                           container.innerHTML = container.innerHTML +
                           
                           '<div class="half_div">'+
					'<div id="upperprof">'+
						'<img src="../file/<?php echo $avatar?>" alt="">'+
						'<div id="namauser"><?php echo $fullname?></div>'+
					'</div>'+
					
					'<br/>'+
                                       'Username :<?php echo $username?>'+
                                        '<br>'+
					'Email : <?php echo $email?>'+
					'<br>'+
					'Birthdate : <?php echo $birthdate?>'+

				'</div>'+
				'<div class="half_div">'+
					'<div class="half_tall">'+
						'<div class="headsdeh">Current Tasks:</div>'+
						'<ul class="ul_none">'+
							'<li>Tubes Progin 1</li>'+
							'<li>Catatan Progin </li>'+
						'</ul>'+
					'</div>'+
					'<div class="half_tall">'+
						'<div class="headsdeh">Finished Tasks:</div>'+
						'<ul class="ul_none">'+
							'<li>Tugas Individu IMK</li>'+
							'<li>Tugas Keamanan Informasi </li>'+
							'<li>Tugas Besar AI </li>'+
						'</ul>'+
					'</div>'+
				'</div>';  
                            //onclick="check_value('+'<?php echo $eachtask['+'task_name'+']?>'+','+'<?php echo $eachtask['+'cat_task_name'+']?>'+')"
                        
                      
                       
                                
                    }else{
                            
                    }
           

          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
      xmlhttp.open("GET","../php/datapengguna.php",true);
      xmlhttp.send();
      
  //alert("fungsi kepanggil abis send");
   
}

