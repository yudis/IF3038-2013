/**
 * @author Dimas
 */
var popupWindow=null;
function fitur() {
	myWindow=window.open("KategoriBaru.php","Nama",'width=400,height=300');
	myWindow.focus();

}

function addKategori(){
	myWindow=window.open("KategoriBaru.php","Nama",'width=400,height=300');
	myWindow.focus();
}

function checkAddress(checkbox)
{
    if (checkbox.checked){
        alert("Tugas Selesai. Yiey! (y)");
    } else {
		alert("Tugas Belum Selesai. Berjuang!");
	}
}

function parent_disable() {
if(popupWindow && !popupWindow.closed)
popupWindow.focus();
}

function delWarn()
{
	  var x = confirm("Are you sure you want to delete?");
	if (x)
      return ture;
  else
    return false;
}

function handleChange(cb) {
  display("Changed, new value = " + cb.checked);
}

function tutup() {
	alert("kategori dibuat namun belum disimpan");
	window.close();
}

function addTask(){
	myWindow=window.open("buattugas.php","Nama",'width=400,height=300');
	myWindow.focus();
}

function showDapur() {
	document.getElementById("alltask").style.visibility="hidden";
	document.getElementById("dapurtask").style.visibility="visible";
	document.getElementById("sekolahtask").style.visibility="hidden";
	document.getElementById("personaltask").style.visibility="hidden";
}

function showSekolah() {
	document.getElementById("alltask").style.visibility="hidden";
	document.getElementById("dapurtask").style.visibility="hidden";
	document.getElementById("sekolahtask").style.visibility="visible";
	document.getElementById("personaltask").style.visibility="hidden";
}

function showPersonal() {
	document.getElementById("alltask").style.visibility="hidden";
	document.getElementById("dapurtask").style.visibility="hidden";
	document.getElementById("sekolahtask").style.visibility="hidden";
	document.getElementById("personaltask").style.visibility="visible";
}



