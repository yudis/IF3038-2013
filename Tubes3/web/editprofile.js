function notifikasi(){
	alert("Tidak berubah");
}

function notifikasiquery(){
	alert("Data berhasil ditambahkan");
}
function notifikasiquery2(){
	alert("Data tidak berhasil ditambahkan");
}
function notifikasigagalupload(){
	alert("Avatar tidak berhasil diupload, silahkan ulangi lagi");	
}

function fileallowed(){
//	alert("masuk");
	var file = document.getElementById('avatar');
   var ext = file.value.match(/\.([^\.]+)$/)[1];
	
    switch(ext)
    {
        case 'jpg':
        case 'jpeg':
            break;
        default:
		 file.value='';
            alert('Tipe file yang diizinkan hanya jpg atau jpeg.\nSilahkan ulangi masukan');
           
    }
};

function checkNamaLengkap(){
	var nama=document.getElementById("namalengkap");
	var spasi=/ /;
	if (spasi.test(namalengkap.value)) {
		var a1 = new Array();
		a1=namalengkap.value.split(' ');
		if(a1[0]=="" || a1[1]=="")
		{
			alert("Nama lengkap minimal terdiri dari dua kata");
			nama.value="";
			nama.focus();
			
		}
	}
	else
	{
		alert("Nama lengkap minimal terdiri dari dua kata dipisahkan spasi");
		nama.value="";
		nama.focus();
	}
}

function checkPassword(username){
	var pass=document.getElementById("pass");
	if(pass.value.length<8)
	{
		alert("password minimal 8 karakter");	
		pass.value="";
		pass.focus();
	}
	else if(username==pass.value)
	{
		alert("password sama dengan username");
		pass.value="";
		pass.focus();
	}
}

function checkConfirmedPass(){
	var pass=document.getElementById("pass");
	var passconfirmed=document.getElementById("passconfirmed");
	if(pass.value!=passconfirmed.value)
	{
		alert("confirmed password harus sama dengan password");
		passconfirmed.value="";
		passconfirmed.focus();
	}
}