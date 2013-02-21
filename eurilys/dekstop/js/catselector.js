function catchange(catnum){
		if (catnum == 0) {
			document.getElementById("curtask1").style.opacity = "1";
			document.getElementById("curtask1").style.top = "20px";
			document.getElementById("curtask2").style.opacity = "1";
			document.getElementById("curtask2").style.top = "200px";
			document.getElementById("curtask3").style.opacity = "1";
			document.getElementById("curtask3").style.top = "380px";
			document.getElementById("curtask4").style.opacity = "1";
			document.getElementById("curtask4").style.top = "560px";
			document.getElementById("curtask5").style.opacity = "1";
			document.getElementById("curtask5").style.top = "740px";
			document.getElementById("kuliah").style.color = "#6FBEC6";
			document.getElementById("proyek").style.color = "#6FBEC6";
			document.getElementById("tugas").style.color = "#6FBEC6";
			document.getElementById("lomba").style.color = "#6FBEC6";
		}
		
		else {if (catnum == 1){
				document.getElementById("curtask3").style.opacity = "0";
				document.getElementById("curtask4").style.opacity = "0";
				document.getElementById("curtask5").style.opacity = "0";
				document.getElementById("curtask1").style.opacity = "1";
				document.getElementById("curtask1").style.top = "20px";
				document.getElementById("curtask2").style.opacity = "1";
				document.getElementById("curtask2").style.top = "200px";
				document.getElementById("kuliah").style.color = "#ff5645";
				document.getElementById("proyek").style.color = "#6FBEC6";
				document.getElementById("tugas").style.color = "#6FBEC6";
				document.getElementById("lomba").style.color = "#6FBEC6";
			}
		else {
			if (catnum==2) {
				document.getElementById("curtask1").style.opacity = "0";
				document.getElementById("curtask2").style.opacity = "0";
				document.getElementById("curtask3").style.opacity = "0";
				document.getElementById("curtask4").style.opacity = "1";
				document.getElementById("curtask4").style.top = "20px";
				document.getElementById("curtask5").style.opacity = "1";
				document.getElementById("curtask5").style.top = "200px";
				document.getElementById("proyek").style.color = "#ff5645";
				document.getElementById("kuliah").style.color = "#6FBEC6";
				document.getElementById("tugas").style.color = "#6FBEC6";
				document.getElementById("lomba").style.color = "#6FBEC6";
			}
			else if (catnum == 4) {
				document.getElementById("curtask1").style.opacity = "0";
				document.getElementById("curtask2").style.opacity = "0";
				document.getElementById("curtask4").style.opacity = "0";
				document.getElementById("curtask5").style.opacity = "0";
				document.getElementById("curtask3").style.opacity = "1";
				document.getElementById("curtask3").style.top = "20px";
				document.getElementById("lomba").style.color = "#ff5645";
				document.getElementById("proyek").style.color = "#6FBEC6";
				document.getElementById("kuliah").style.color = "#6FBEC6";
				document.getElementById("tugas").style.color = "#6FBEC6";
				}
			else {
				document.getElementById("curtask1").style.opacity = "0";
				document.getElementById("curtask2").style.opacity = "0";
				document.getElementById("curtask3").style.opacity = "0";
				document.getElementById("curtask4").style.opacity = "0";
				document.getElementById("curtask5").style.opacity = "0";
				document.getElementById("tugas").style.color = "#ff5645";
				document.getElementById("proyek").style.color = "#6FBEC6";
				document.getElementById("lomba").style.color = "#6FBEC6";
				document.getElementById("kuliah").style.color = "#6FBEC6";
				}
			}
		}
    }
	
function add_category2() {
	add_category();
	var taskcatlist = document.getElementsByClassName("taskcat");
	var selc = document.getElementById("catselector");
	var newcat = document.getElementById("add_category_name").value;
	selc.innerHTML += '<option value="' + taskcatlist.length + '">' + newcat +'</option>';
	
	var content = document.getElementById("dynamic_content");
	var curcatnum = taskcatlist.length+1;
	content.innerHTML += '<div class="taskcat" id="taskcat' + curcatnum + '">Task di Category ' + curcatnum + '</div>';
	catchange(curcatnum);
	}