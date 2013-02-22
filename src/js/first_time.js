if (localStorage.MOA_userList == null)
{
	// pendefinisian user default
	var user = new Object();
	user.userName = "admin";
	user.passWord = "admin123";
	user.fullName = "Administrator Jordan";
	user.birthDate = "20/11/1992";
	user.emailAddress = "fernandojordan.92@gmail.com";
	user.Avatar = "images/tes.jpg";
	
	var listuser = new Array();
	listuser[0] = user;
	
	localStorage.MOA_userList = JSON.stringify(listuser);
	
	var users = new Array();
	users[0] = "admin";
	
	var category = new Object();
	category.users = users;
	
	category.name = "Pemrograman Internet";	
	var listcategory = new Array();
	listcategory[0] = category;
	
	category = new Object();
	category.users = users;
	category.name = "Kriptografi";	
	listcategory[1] = category;
	
	category = new Object();
	category.users = users;
	category.name = "Metode Numerik";	
	listcategory[2] = category;
	
	localStorage.MOA_categoryList = JSON.stringify(listcategory);
}