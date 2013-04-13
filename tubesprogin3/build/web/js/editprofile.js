/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function edit_profile(username){
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
    window.location.href= "../src/editprofile.jsp?t="+username;    
}