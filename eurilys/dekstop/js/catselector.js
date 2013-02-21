function catchange(catnum){
		if (catnum == 0) {
			document.getElementById("curtask1").style.display= "block";
			document.getElementById("curtask2").style.display= "block";
			document.getElementById("curtask3").style.display= "block";
			document.getElementById("curtask4").style.display= "block";
			document.getElementById("curtask5").style.display= "block";
		}
		
		else {if (catnum == 1){
				document.getElementById("curtask3").style.display= "none";
				document.getElementById("curtask4").style.display= "none";
				document.getElementById("curtask5").style.display= "none";
				document.getElementById("curtask1").style.display= "block";
				document.getElementById("curtask2").style.display= "block";
			}
		else {
			if (catnum==2) {
				document.getElementById("curtask1").style.display= "none";
				document.getElementById("curtask2").style.display= "none";
				document.getElementById("curtask3").style.display= "none";
				document.getElementById("curtask4").style.display= "block";
				document.getElementById("curtask5").style.display= "block";
			}
			else if (catnum == 4) {
				document.getElementById("curtask1").style.display= "none";
				document.getElementById("curtask2").style.display= "none";
				document.getElementById("curtask4").style.display= "none";
				document.getElementById("curtask5").style.display= "none";
				document.getElementById("curtask3").style.display= "block";
				}
			else {
				document.getElementById("curtask1").style.display= "none";
				document.getElementById("curtask2").style.display= "none";
				document.getElementById("curtask3").style.display= "none";
				document.getElementById("curtask4").style.display= "none";
				document.getElementById("curtask5").style.display= "none";
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