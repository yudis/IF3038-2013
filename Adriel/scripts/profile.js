function editProfil()
{
	document.getElementById("nameEditDiv").style.display = "inline";
	document.getElementById("emailEditDiv").style.display = "inline";
	document.getElementById("dateEditDiv").style.display = "inline";
	document.getElementById("majorEditDiv").style.display = "inline";
	document.getElementById("doneProfileButton").style.display = "inline";
	document.getElementById("changeProfilePictureButton").style.display = "inline";
	
	document.getElementById("nameDisplayDiv").style.display = "none";
	document.getElementById("emailDisplayDiv").style.display = "none";
	document.getElementById("dateDisplayDiv").style.display = "none";
	document.getElementById("majorDisplayDiv").style.display = "none";
	document.getElementById("editProfileButton").style.display = "none";
	
	return false;
}

function doneProfil()
{
	document.getElementById("nameEditDiv").style.display = "none";
	document.getElementById("emailEditDiv").style.display = "none";
	document.getElementById("dateEditDiv").style.display = "none";
	document.getElementById("majorEditDiv").style.display = "none";
	document.getElementById("doneProfileButton").style.display = "none";
	document.getElementById("changeProfilePictureButton").style.display = "none";
	
	document.getElementById("nameDisplayDiv").style.display = "inline";
	document.getElementById("emailDisplayDiv").style.display = "inline";
	document.getElementById("dateDisplayDiv").style.display = "inline";
	document.getElementById("majorDisplayDiv").style.display = "inline";
	document.getElementById("editProfileButton").style.display = "inline";
	
	return false;
}