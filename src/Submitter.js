function initButton() {
	document.getElementById('addtask').disabled = true;
}

function submitComment1() {
	var comment = document.getElementById('changecomment1').value;
	if(comment != "")
		document.getElementById('comment1').innerHTML = comment;
	return false;
}

function submitComment2() {
	var comment = document.getElementById('changecomment2').value;
	if(comment != "")
		document.getElementById('comment2').innerHTML = comment;
	return false;
}

function submitComment3() {
	var comment = document.getElementById('changecomment3').value;
	if(comment != "")
		document.getElementById('comment3').innerHTML = comment;
	return false;
}

function submitComment4() {
	var comment = document.getElementById('changecomment4').value;
	if(comment != "")
		document.getElementById('comment4').innerHTML = comment;
	return false;
}

function submitComment5() {
	var comment = document.getElementById('changecomment5').value;
	if(comment != "")
		document.getElementById('comment5').innerHTML = comment;
	return false;
}