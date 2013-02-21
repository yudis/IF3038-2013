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