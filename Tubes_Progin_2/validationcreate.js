//registration form variabel
var taskName = document.getElementById("namaTask");
var valid1bool;
	
	taskName.onkeyup = function()
	{
		if (taskName.checkValidity()){
			valid1.src = "img/benar.png";
			valid1bool=true;
		}
		else
		{
			valid1.src = "img/salah.png";
			valid1bool=false;
		}
		cekvalid();
	}