/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
<!--AJAX untuk show kategori-->
function showKategori(uid){
    document.getElementById("category").innerHTML="";
    if(window.XMLHttpRequest)
    {
        // untuk IE7, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {
        //untuk IE jadul
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
				
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("category").innerHTML=xmlhttp.responseText;		
        }
    }
    xmlhttp.open("GET", "Kategori?aksi=lihat&uid="+uid, true);
    xmlhttp.send();
}

<!--AJAX untuk show list task-->
function showListTask(uid, idKategori){
    document.getElementById("task").innerHTML="";
    if(window.XMLHttpRequest)
    {
        // untuk IE7, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {
        //untuk IE jadul
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
				
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("task").innerHTML=xmlhttp.responseText;		
        }
    }
    xmlhttp.open("GET", "Task?aksi=lihat_list_task&idKategori="+idKategori+"&uid="+uid, true);
    xmlhttp.send();
}

<!--JS UNTUK SHOW/HIDE TOMBOL DELETE KATEGORI-->
		
var isShown = false;
function show_del_cat()
{
    var elements = document.getElementsByClassName("tombol_hapus_kategori");
    if(isShown)
    {
        for(var i = 0; i < elements.length; i++)
        {
            elements[i].style.display = "none";
        }
        isShown = false;
    }
    else{
        for(var i = 0; i < elements.length; i++)
        {
            elements[i].style.display = "block";
        }
        isShown = true;
    }
}

function showResult(str)
{
    if(str.length==0)
    {
        document.getElementById("hasil_autocomplete").innerHTML="";
        
        return;
    }
				
    var str_arr = str.split(", ");
				
    if(window.XMLHttpRequest)
    {
        // untuk IE7, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {
        //untuk IE jadul
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
				
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById("hasil_autocomplete").innerHTML=xmlhttp.responseText;
           
        }
    }
    xmlhttp.open("GET", "Search?aksi=suggest&key=username&value="+str_arr[str_arr.length -1], true);
    
    xmlhttp.send();
}
		
	
		
function sort_and_unique( my_array ) {
    my_array.sort();
    for ( var i = 1; i < my_array.length; i++ ) {
        if ( my_array[i] === my_array[ i - 1 ] ) {
            my_array.splice( i--, 1 );
        }
    }
    return my_array;
};
		
	
<!--JS UNTUK AUTOCOMPLETE-->
		
function autocomplete_diklik(str)
{
    var string_awal = document.getElementById("input_assignees").value;
    var str_arr = string_awal.split(", ");

    str_arr[str_arr.length-1]=str;
    str_arr = sort_and_unique(str_arr);
    document.getElementById("input_assignees").value = str_arr.join(", ")+", ";
    document.getElementById("input_assignees").focus();
    document.getElementById("hasil_autocomplete").innerHTML="";
}
		
		
<!--AJAX UNTUK MENAMPILKAN TASKS-->
		
var prev_selected = 0;
function showTasks(uid, str)
{
    document.getElementById("task").innerHTML="";

    if(prev_selected == 0)
    {
        prev_selected = str;
    }
    document.getElementById("id"+prev_selected).style.border="none";
				
    if(window.XMLHttpRequest)
    {
        // untuk IE7, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {
        //untuk IE jadul
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
				
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
					
            var elements = document.getElementsByClassName("tulcat");
						
            document.getElementById("id"+str).style.border="solid #FF0000";
            document.getElementById("task").innerHTML=xmlhttp.responseText;
            document.getElementById("link_buattask").href="buattask.php?idkategori="+str;
            document.getElementById("addtask").style.display = "block";
            document.getElementById("deltask").style.display = "block";
						
            prev_selected = str;
        }
    }
    xmlhttp.open("GET", "show_list_task.php?idaccounts="+uid+"&id_kategori="+str, true);
    xmlhttp.send();
}

<!--JS UNTUK SHOW/HIDE TOMBOL DELETE TASK-->
		
var muncul = false;
function show_del_task()
{
    var elements = document.getElementsByClassName("tombol_hapus_task");
    if(muncul)
    {
        
        for(var i = 0; i < elements.length; i++)
        {
            elements[i].style.display = "none";
        }
        muncul = false;
    }
    else{
        
        for(var i = 0; i < elements.length; i++)
        {
            elements[i].style.display = "block";
        }
        muncul = true;
    }
}

function ubahStatus(idstat)
{
    var str = document.getElementById(idstat).innerHTML;
    
    if(window.XMLHttpRequest)
    {
        // untuk IE7, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {
        //untuk IE jadul
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
				
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById(idstat).innerHTML=xmlhttp.responseText;
            alert("status telah tugas diubah");
        }
    }
    
    
    if(str == "Selesai")
    {
        xmlhttp.open("GET", "Task?aksi=ubahstatus&taskid="+idstat.substring(4)+"&newstatus=0", true);
        document.getElementById(idstat).className="tombol_status_off";
    }else
    {
        xmlhttp.open("GET", "Task?aksi=ubahstatus&taskid="+idstat.substring(4)+"&newstatus=1", true);
        document.getElementById(idstat).className="tombol_status_on";
    }
    xmlhttp.send();
}