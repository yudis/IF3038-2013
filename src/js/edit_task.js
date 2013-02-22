window.onload = function() {
	$id("rinciantaskform-input-asigneetext").style.visibility = "hidden";
	$id("rinciantaskform-input-deadline").style.visibility = "hidden";
	$id("rinciantaskform-input-tag").style.visibility = "hidden";
	$id("rinciantaskform-input-asigneeadd").style.visibility = "hidden";
	$id("rinciantaskform-OK").style.visibility = "hidden";
	
	$id("profilename").innerHTML = JSON.parse(window.localStorage['login_user']).fullname;
}

function edit_field() {
	if($id("rinciantaskform-input-asigneetext").style.visibility == "hidden") {
		// EDIT
		$id("rinciantaskform-input-asigneetext").style.visibility = "visible";
		$id("rinciantaskform-input-deadline").style.visibility = "visible";
		$id("rinciantaskform-input-tag").style.visibility = "visible";
		$id("rinciantaskform-input-asigneeadd").style.visibility = "visible";
		
		$id("rinciantaskform-tag").style.visibility = "hidden";
		$id("rinciantaskform-deadline").style.visibility = "hidden";
		$id("rinciantaskform-edit").value = "OK";
	} else {
	$id("rinciantaskform-input-asigneetext").style.visibility = "hidden";
		// OK
		$id("rinciantaskform-input-deadline").style.visibility = "hidden";
		$id("rinciantaskform-input-tag").style.visibility = "hidden";
		$id("rinciantaskform-input-asigneeadd").style.visibility = "hidden";
		
		$id("rinciantaskform-tag").style.visibility = "visible";
		$id("rinciantaskform-deadline").style.visibility = "visible";
		$id("rinciantaskform-edit").value = "Edit";
	}
}

function komentar_send() {
	$id("rinciantaskform-komentar");
}

function activate_komentar_send() {
	var send = $id("rinciantaskform-komentartext");
	var comment = $id("rinciantaskform-komentarsend");
	if(send.value.length > 0) {
		comment.disabled = false;
	} else {
		comment.disabled = true;
	}
}

function activate_asignee_add() {
	var add = $id("rinciantaskform-input-asigneetext");
	var asignee = $id("rinciantaskform-input-asigneeadd");
	if(add.value.length > 0){
		asignee.disabled = false;
	} else{
		asignee.disabled = true;
	}
}
