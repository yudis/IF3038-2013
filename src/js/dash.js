<!--AJAX AUTOCOMPLETE ASSIGNEE-->
		
			function showResult(str)
			{
				if(str.length==0)
				{
					document.getElementById("hasil_autocomplete").innerHTML="";
					document.getElementById("hasil_autocomplete").style.border="0px";
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
						document.getElementById("hasil_autocomplete").style.border="1px solid #A5ACB2";
						
						document.getElementById("hasil_autocomplete").style.width="250px";
					}
				}
				xmlhttp.open("GET", "autocomplete_assignee.php?q="+str_arr[str_arr.length -1], true);
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
				var string_awal = document.getElementById("input_assignee").value;
				var str_arr = string_awal.split(", ");
				/*
				if(string_awal.indexOf(", ") != -1)
				{
					document.getElementById("input_assignee").value += str;
					document.getElementById("input_assignee").value += ", ";
				}
				else
				{
				document.getElementById("input_assignee").value = str + ", ";
					
				}*/
				str_arr[str_arr.length-1]=str;
				str_arr = sort_and_unique(str_arr);
				document.getElementById("input_assignee").value = str_arr.join(", ")+", ";
				document.getElementById("input_assignee").focus();
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
		
	
		<!--JS UNTUK SHOW/HIDE TOMBOL DELETE KATEGORI-->
		
			var isShown = false;
			function show_del_cat()
			{
				var elements = document.getElementsByClassName("tombol_hapus");
				if(isShown)
				{
					document.getElementById("delcat").style.backgroundColor="#ececec";
					for(var i = 0; i < elements.length; i++)
					{
						elements[i].style.display = "none";
					}
					isShown = false;
				}
				else{
					document.getElementById("delcat").style.backgroundColor="#361a2c";
					for(var i = 0; i < elements.length; i++)
					{
						elements[i].style.display = "block";
					}
					isShown = true;
				}
			}
		
	
		<!--JS UNTUK SHOW/HIDE TOMBOL DELETE TASK-->
		
			var isShown = false;
			function show_del_task()
			{
				var elements = document.getElementsByClassName("tombol_hapus_task");
				if(isShown)
				{
					document.getElementById("deltask").style.backgroundColor="#ececec";
					for(var i = 0; i < elements.length; i++)
					{
						elements[i].style.display = "none";
					}
					isShown = false;
				}
				else{
					document.getElementById("deltask").style.backgroundColor="#361a2c";
					for(var i = 0; i < elements.length; i++)
					{
						elements[i].style.display = "block";
					}
					isShown = true;
				}
			}
		
	
		<!--AJAX UNTUK UBAH STATUS TUGAS-->
		
		
		function ubahStatus(idstat)
			{
				var str = document.getElementById(idstat).innerHTML;
				document.getElementById(idstat).innerHTML="";
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
				
				if(str == "Sudah Selesai")
				{
					xmlhttp.open("GET", "ubah_status_task.php?idtugas="+idstat.substring(4)+"&status=0", true);
					document.getElementById(idstat).className="tombol_tugas_off";
				}else
				{
					xmlhttp.open("GET", "ubah_status_task.php?idtugas="+idstat.substring(4)+"&status=1", true);
					document.getElementById(idstat).className="tombol_tugas_on";
				}
				xmlhttp.send();
			}