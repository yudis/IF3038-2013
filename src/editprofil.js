/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function checkPassword() {
    var uname=document.forms["EditForm"]["daftar_username"].value;
    var pass=document.forms["EditForm"]["daftar_password"].value;
    var email=document.forms["EditForm"]["daftar_email"].value;
    if(pass=="" || pass==null)
        {document.getElementById('passwordWarning').innerHTML="*Password Tidak Berubah";return true;}
    else
    if(pass==uname)
        {document.getElementById('passwordWarning').innerHTML="*Password tidak boleh sama dengan username";return false;}
    else
    if(pass.length<8)
        {document.getElementById('passwordWarning').innerHTML="*Password minimal 8 karakter";return false;}
    else
    if(pass==email)
        {document.getElementById('passwordWarning').innerHTML="*Password tidak sama dengan e-mail";return false;}
    else
        {document.getElementById('passwordWarning').innerHTML="";return true;}
}

function checkConfirmPassword() {
    var pass1=document.forms["EditForm"]["daftar_password"].value;
    var pass2=document.forms["EditForm"]["daftar_confirmpassword"].value;
    if(pass1!=pass2)
        {document.getElementById('confirmPasswordWarning').innerHTML="*Password tidak cocok";return false;}
    else
        {document.getElementById('confirmPasswordWarning').innerHTML="";return true;}
}

function checkNama() {
    var nama=document.forms["EditForm"]["daftar_namalengkap"].value;
    var spacepos=nama.indexOf(" ");
    if(nama=="" || nama==null)
        {document.getElementById('namaWarning').innerHTML="*Nama tidak berubah";return true;}
    else
        if(spacepos<0)
            {document.getElementById('namaWarning').innerHTML="*Nama minimal 2 kata";return false;}
        else
            {document.getElementById('namaWarning').innerHTML="";return true;}
}


function check_extension() {
      var filename=document.forms["EditForm"]["daftar_avatar"].value;
      var re = /\..+$/;
      var ext = filename.match(re);
      if(filename=="")
           {document.getElementById('avatarWarning').innerHTML="avatar tidak berubah";return true}
      else if(!(ext==".jpg" || ext==".jpeg"))
           {document.getElementById('avatarWarning').innerHTML="ekstensi file tidak diterima";return false;}
      else
           {document.getElementById('avatarWarning').innerHTML="";return true;}
}


function checkValidation() {
    return (checkPassword() && checkConfirmPassword() && checkNama() && check_extension());
}
