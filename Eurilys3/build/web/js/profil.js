function autoComplete(str){
				// User Name
				if(document.getElementById("userName").checked){
					str = str+"&userName=1";	
				}
				else{
					str = str+"&userName=0";
				}
				// Judul Kategori
				if(document.getElementById("judulKategori").checked){
					str = str+"&judulKategori=1";	
				}
				else{
					str = str+"&judulKategori=0";
				}
				// Task Tag
				if(document.getElementById("taskTag").checked){
					str = str+"&taskTag=1";	
				}
				else{
					str = str+"&tasTag=0";
				}
				var hintList = document.getElementById("sought");
				if (str.length <= 0){
					hintList.innerHTML = "";
				}
				else{
					if (window.XMLHttpRequest)
					  {// code for IE7+, Firefox, Chrome, Opera, Safari
					  xmlhttp=new XMLHttpRequest();
					  }
					else
					  {// code for IE6, IE5
					  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
					  }	
					xmlhttp.onreadystatechange=function()
					  {
					  if (xmlhttp.readyState==4 && xmlhttp.status==200)
						{
							hintList.innerHTML = xmlhttp.responseText;
						}
					  }  
					xmlhttp.open("GET","../src/autoComplete.php?val="+str,true);
					xmlhttp.send();
				}
			}

function updateProfile(){
	var profile_picture = document.getElementById("profile_picture");
	var profile_picture2 = document.getElementById("profile_picture2");
	var profile_name = document.getElementById("profile_info");
	var profile_names = document.getElementById("user_name");
	var profile_fullname = document.getElementById("full_name");
	var profile_name2 = document.getElementById("namauser");
	var profile_email = document.getElementById("user_email");
	var profile_bday = document.getElementById("user_bday");
	var profile_content = "";
	var array_profile_content = "";
	var button = document.getElementById("edit_button");
	var profile_button = document.getElementById("user_button");
	var profile_image = document.getElementById("user_image");
	profile_image.innerHTML = "";
	profile_button.innerHTML = "";
	button.innerHTML = "Edit Profile";
	var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				profile_content = xmlhttp.responseText;
				array_profile_content = profile_content.split("|");
				profile_name.innerHTML = array_profile_content[4];
				profile_names.innerHTML = array_profile_content[4];
				profile_name2.innerHTML = array_profile_content[0];
				profile_fullname.innerHTML = array_profile_content[0];
				profile_picture.src = "../"+array_profile_content[1];
				profile_picture2.src = "../"+array_profile_content[1];
				profile_email.innerHTML = array_profile_content[2];
				profile_bday.innerHTML = array_profile_content[3];
				
			}
		  }  
		xmlhttp.open("GET","../src/getProfileConten.php",true);
		xmlhttp.send();
}

function updateProfileTask(){
	var currentTaskBox = document.getElementById("currentTask");
	var finishedTaskBox = document.getElementById("finishedTask");
	var penampung = "";
	var arrayTask;
	var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				penampung = xmlhttp.responseText;
				arrayTask = penampung.split("|@|");
				currentTaskBox.innerHTML = arrayTask[0];
				finishedTaskBox.innerHTML = arrayTask[1];
			}
		  }  
		xmlhttp.open("GET","../src/getProfileTask.php",true);
		xmlhttp.send();						
}

function editProfile(){
	var profile_picture = document.getElementById("profile_picture"); // gambar kecil
	var profile_name = document.getElementById("profile_info"); // user name kecil
	var profile_name2 = document.getElementById("namauser"); // full name kecil
	var profile_picture2 = document.getElementById("profile_picture2"); // gambar utama
	var profile_names = document.getElementById("user_name"); // user name utama
	var profile_fullname = document.getElementById("full_name"); // full name utama
	var profile_email = document.getElementById("user_email");
	var profile_bday = document.getElementById("user_bday");
	var profile_image = document.getElementById("user_image");
	var profile_button = document.getElementById("user_button");
	var button = document.getElementById("edit_button");
	var currentTaskBox = document.getElementById("currentTask");
	
	// initial value
	fullname = profile_fullname.innerHTML;
	email = profile_email.innerHTML;
	bday = profile_bday.innerHTML;
	
	if(button.innerHTML =="Edit Profile"){	
	// Edit Mode
		button.innerHTML = "";
		clearInterval(UpdateProfile);
		clearInterval(UpdateTask);
		profile_fullname.innerHTML = '<input type="text" name="fullname" id="fullname" value="'+fullname+'"  required >';
		profile_email.innerHTML = '<input type="text" name="email" id="email" value="'+email+'"  required >';
		profile_bday.innerHTML = '<input type="date" name="bday" id="bday" value="'+bday+'"  required ><br>';
		profile_image.innerHTML = 'Avatar : <input type="file" name="avatar_upload" id="avatar_upload">';
		profile_button.innerHTML ='<div id="updateButtonSubmit" onclick="submitUpdate();" class="right link_blue_big top10 bold" title="Semua elemen form harus diisi dengan benar dahulu."> SAVE </div>'
	}
	
}

function submitUpdate(){
	
	var new_fullname = document.getElementById("fullname").value;
	var new_email = document.getElementById("email").value;
	var new_bday = document.getElementById("bday").value;
	
	if( new_fullname == fullname && new_email == email  && new_bday == bday){
		alert("Isi Field Tidak Berubah");	
		UpdateProfile = setInterval(function(){updateProfile()},300);
		UpdateTask = setInterval(function(){updateProfileTask()},300);
	}
	else{
		document.getElementById("updateProfileForm").submit();
		UpdateProfile = setInterval(function(){updateProfile()},300);
		UpdateTask = setInterval(function(){updateProfileTask()},300);
	}
}




