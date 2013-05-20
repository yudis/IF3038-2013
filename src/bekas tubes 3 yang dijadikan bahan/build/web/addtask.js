/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function checkNamaTask() {
    var isalpha = false;
    var uname=document.forms["AddTaskForm"]["addtask_namaTask"].value;
    for(var i=0;(i<uname.length && isalpha==false);i++){
        var char1 = uname.charAt(i);
        var cc = char1.charCodeAt(0);

        if(!((cc==32) || (cc>47 && cc<58) || (cc>64 && cc<91) || (cc>96 && cc<123)))
        {
             isalpha = true;
        }
    }
    if(isalpha==true){
        {document.getElementById('namaTaskWarning').innerHTML="*Nama Task tidak boleh alphanumeric";return false;}
    }
    else
    if(uname=="" || uname==null)
        {document.getElementById('namaTaskWarning').innerHTML="*Nama Task tidak boleh kosong";return false;}
    else
    if(uname.length>25)
        {document.getElementById('namaTaskWarning').innerHTML="*NamaTask harus <= 25 karakter";return false;}
    else
        {document.getElementById('namaTaskWarning').innerHTML="";return true;}
}

function checkValidation() {
    return (checkNamaTask());
}