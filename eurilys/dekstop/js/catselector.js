function catchange(catnum){
	var taskcatlist = document.getElementsByClassName("taskcat");
	if (catnum != 0){	
		for(i = 0; i < taskcatlist.length;i++){
				taskcatlist[i].style.display = 'none';
				}
		taskcatlist[catnum-1].style.display = 'block';		
		}
	else {
			for(i = 0; i < taskcatlist.length;i++){
				taskcatlist[i].style.display = 'block';
				}
		}
    }
	
function add_category2() {
	
	var taskcatlist = document.getElementsByClassName("taskcat");
	var selc = document.getElementById("catselector");
	var newcat = document.getElementById("add_category_name").value;
	selc.innerHTML += '<option value="' + taskcatlist.length + '">' + newcat +'</option>';
	
	var content = document.getElementById("dynamic_content");
	var curcatnum = taskcatlist.length+1;
	content.innerHTML += '<div class="taskcat" id="taskcat' + curcatnum + '">Task di Category ' + curcatnum + '</div>';
	catchange(curcatnum);
	}