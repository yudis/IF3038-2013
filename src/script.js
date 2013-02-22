/* Rincian Tugas.js */

function saveEdits() {
	var editElem = document.getElementById("edit");

	var userVersion = editElem.innerHTML;

	localStorage.userEdits = userVersion;

	document.getElementById("update").innerHTML="Edits saved!";
	document.getElementById("update2").innerHTML="Edits saved!";
	document.getElementById("update3").innerHTML="Edits saved!";
}

function checkEdits() {
	if(localStorage.userEdits!=null)
	document.getElementById("edit").innerHTML = localStorage.userEdits;
}