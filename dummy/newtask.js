function validateTaskName(){
	var elmt = document.getElementById("taskname");
	var val = elmt.value;
	var flag = "true";
	if((val.length>25)||(val.length<1)){
		changeTaskValid("false");
		flag = "false";
	}else{
		for(var i=0;i<val.length;i++){
			var current_char = val.charCodeAt(i);
			if(!(((current_char>=48)&&(current_char<=57))||((current_char>=65)&&(current_char<=90))||((current_char>=97)&&(current_char<=122)))){
				changeTaskValid("false");
				flag = "false";
			}
		}
		if(flag=="true"){
			changeTaskValid("true");
		}
	}
}

function changeTaskValid(message){
	var elmt = document.getElementById("taskvalid");
	if(message=="false"){
		elmt.style.display = "block";
	}else{
		elmt.style.display = "none";
	}
}

function category_click(){
	window.location = "mytask.html";
}

function task_click(){
	window.location = "mytask.html";
}