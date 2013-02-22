function initButton() {
	document.getElementById('addtask').disabled = true;
}

function submitComment() {
	var comment = document.getElementById('changecomment1').value;
	if(comment != "")
		document.getElementById('comment1').innerHTML = comment;
	return false;
}