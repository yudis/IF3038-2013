function validateTaskName(){
	var elmt = document.getElementById("taskname");
	var val = elmt.value;
	var flag = "true";
	if((val.length>25)||(val.length<1)){
		changeTaskValid("false");
		flag = "false";
		return false;
	}else{
		for(var i=0;i<val.length;i++){
			var current_char = val.charCodeAt(i);
			if(!((current_char==32)||((current_char>=48)&&(current_char<=57))||((current_char>=65)&&(current_char<=90))||((current_char>=97)&&(current_char<=122)))){
				changeTaskValid("false");
				flag = "false";
				return false;
			}
		}
		if(flag=="true"){
			changeTaskValid("true");
			return true;
		}
	}
}

function validateFormRegister(){
	var uname = document.getElementById("regusername"); //variabel username
	var valun = uname.value;
	var pwd = document.getElementById("password"); //variabel password
	var vp = pwd.value;
	var cpwd = document.getElementById("cpassword"); //variabel confirm password
	var vcp = cpwd.value;
	var fname = document.getElementById("fullname"); //variabel fullname
	var vfn = fname.value;
	var email = document.getElementById("regemail"); //variabel email
	var ve = email.value;
	vfn = vfn.toLowerCase();
	var regex = new RegExp("([a-z]) ([a-z])");
	var regex2 = new RegExp("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+$");
	if(valun.length>4 && vp.length>7){
		if (valun==vp || vp==ve || vp!=vcp){
			document.getElementById("att_valid2").innerHTML = "<BR>Password jangan sama dengan email ataupun username";
		} //validasi nilai username, email, dan konfirm password
			//return false;
		else{
			if(regex.test(vfn) && regex2.test(ve)){
				login_click();
				//return true;
			}
		}
	}
	else{
		document.getElementById("att_valid2").innerHTML = "Username harus lebih dari 5 huruf";
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

function avatarValid(){
	var elmt = document.getElementById("attachment").value;
	var temp = elmt.split(".");
	if(temp[temp.length-1]!="jpg"&&temp[temp.length-1]!="jpeg"){
		document.getElementById("att_valid").innerHTML = "Invalid File Type!!";
		return false;
	}else{
		document.getElementById("att_valid").innerHTML = "";
		return true;
	}
}

function edittasksasignee(){
	var elmt = document.getElementById("taskasignee");
	elmt.innerHTML = "Asignee : <input type='text' id='editasignee' name='editasignee'><br>";
}

function edittaskdeadline(){
	var elmt = document.getElementById("taskdeadline");
	elmt.innerHTML = "Deadline : <input type='text' name='deadline' id='deadline' class='calendarSelectDate'><p id='deadline_valid'></p><br>";
}

function edittasktag(){
	var elmt = document.getElementById("tasktag");
	elmt.innerHTML = "Tag : <input type='text' id='edittag' name='edittag'><br>";
}

function category_click(){
	window.location = "mytask.html";
}

function login_click(){
	window.location = "mytask.html#all";
}

function task_click(){
	window.location = "mytask.html";
}