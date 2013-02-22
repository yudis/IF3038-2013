if (localStorage.userList == null)
{
	var user = new Object();
	user.userName = "admin";
	user.passWord = "admin123";
	user.fullName = "Administrator Jordan";
	user.birthDate = "20/11/1992";
	user.emailAddress = "fernandojordan.92@gmail.com";
	user.Avatar = "images/tes.jpg";
	
	var listuser = new Array();
	listuser[0] = user;
	
	localStorage.userList = JSON.stringify(listuser);
}