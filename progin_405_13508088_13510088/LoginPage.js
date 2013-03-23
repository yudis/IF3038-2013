/**
 * @author Dimas
 */
function validate(){
	var email = document.getElementById("usermail").value;
	var pass = document.getElementById("password").value;
	getpages('coba.php','nama');
	if ((pass=="admin") && (email=="nunu@syafira.dimas")){
		window.location='dashboard.html';	
	} else {
		alert("ID tidak terdaftar!");
	}
	
	
}


function regsukses() {
	var name = form.nama.value;
	var pass = form.password.value;
	
	if ((name == pass)){
		alert("kesalahan pengisian");
	}
	
	else 
		
		alert("Registrasi Berhasil. Namun mohon maaf situs belum mampu menyimpan data anda");
	}
} 


/*
	function passid_validation()
	{
		var passid = form.password.value;
		var passid_len = passid.length;
		if (passid_len == 0 )
		{
			alert("Password should not be empty !");
			passid.focus();
			return false;
		}
		return true;
	}


function conf_validation()
	{
		//var passid_len = passid.value.length;
		if (passid != conf )
		{
			alert("Password not match !");
			passid.focus();
			return false;
		}
		return true;
	}

	function allLetter()
	{ 
		var letters = /^[A-Za-z]+$/;
		if(uname.value.match(letters))
		{
			alert('Form Succesfully Submitted');
				//window.location.reload()
			return true;
		}
		else
		{
			alert('Jangan alay dech namanya');
			uname.focus();
			return false;
		}
	}

	

	

	
	function ValidateEmail()
	{
		var uemail = form.usermail.value;
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(uemail.value.match(mailformat))
			{
				alert("halo");
				return true;
			}
		else
			{
				alert("You have entered an invalid email address!");
				//uemail.focus();
				return false;
			}
	} 
 */
/*	function validsex(umsex,ufsex)
	{
		x=0;

		if(umsex.checked) 
			{
				x++;
			} 
		if(ufsex.checked)
			{
				x++; 
			}
		if(x==0)
			{
				alert('Select Male/Female');
				umsex.focus();
				return false;
			}
		else
			{
				alert('Form Succesfully Submitted');
				window.location.reload()
				return true;
			}
		} */
