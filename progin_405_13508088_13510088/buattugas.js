
function validateForm(){
	if (document.getElementById("namatugas").value==="") {
	 	alert("Input masih salah!");

	} 
	else {
		window.location='rinciantugas.html';
	}
}

function cancel() {
	window.location='rinciantugas.html';
}

function seperateTags(){
	var tag = document.getElementById("inputtags");
	var ArrayofTags = tag.split(',');
}

function checkFile() {
        var fileElement = document.getElementById("inputattachment");
        var fileExtension = "";
        if (fileElement.value.lastIndexOf(".") > 0) {
            fileExtension = fileElement.value.substring(fileElement.value.lastIndexOf(".") + 1, fileElement.value.length);
        }
        if (fileExtension == "gif" || fileExtension == "jpg" || fileExtension=="mp3" || fileExtension=="txt" || fileExtension=="mp4") {
            return true;
        }
        else {
            alert("Unsupported file type! Please select mp3, gif, jpg, and txt files only");
            return false;
        }
}