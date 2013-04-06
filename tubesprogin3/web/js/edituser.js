/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function ubah_detail_user(usernamelama){
    alert("ubah detail user "+usernamelama);
    var username = document.getElementById("user_name_input").value;
    var avatar = document.getElementById("avatar_upload").value;
    var password = document.getElementById("password_input").value;
    var fullname = document.getElementById("fullname_input").value;
    var email = document.getElementById("email_input").value;
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
                   
                  alert(response);
           

          // document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
        }else
        {
            //alert('ga jalan woy ajaxnya'+xmlhttp.readyState+xmlhttp.status);
        }
      }
      xmlhttp.open("GET","../php/ubahprofile.jsp?umd="+usernamelama+"&un="+username+"&av="+avatar+"&p="+password+"&f="+fullname+"&e="+email,true);
      xmlhttp.send(); 
    
}