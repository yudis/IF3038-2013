if (typeof(Storage)!== "undefined") 
{
	// Web storage is supported
	if (sessionStorage.MOA_userSession == null)
	{
		window.location = "index.html";
	}
}
else 
{
	// Web storage is NOT supported
}