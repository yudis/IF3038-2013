function autocomp(input, phpscript) {
	var idname = "hasil_" + input.id;
	var elmt = document.getElementById(input.id);
	if (elmt.value.length > 0) {
		document.body.setAttribute("onClick", "autocompHandleClick();");
		autocompClear(input);
		var div = document.createElement("div");
		div.setAttribute("id", idname);
		div.setAttribute("class", "autocomplete_container");
		var boundary = elmt.getBoundingClientRect();
		div.style.top = boundary.bottom + "px";
		div.style.left = boundary.left + "px";
		div.style.width = (boundary.right - boundary.left) + "px";
		document.body.appendChild(div);

		try {
			var xmlHttp = new XMLHttpRequest();
		} catch(e) {
			try {
				var xmlHttp = new ActiveXObject(Msxml2.XMLHTTP);
			} catch(e) {
				console.log("Browser doesn't support AJAX");
			}
		}
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var query_result = xmlHttp.responseXML.documentElement.getElementsByTagName("Data");
				for ( i = 0; i < query_result.length; i++) {
					var inner_result_id = query_result[i].getElementsByTagName("ID");
					var inner_result_name = query_result[i].getElementsByTagName("String");
					var sug_id = idname + i;
					var child_div = document.createElement("div");
					child_div.setAttribute("id", sug_id);
					child_div.setAttribute("class", "autocomplete");
					child_div.setAttribute("onClick", "autocompGetResult(this);");
					child_div.style.width = elmt.offsetWidth;
					child_div.innerHTML = inner_result_name[0].firstChild.nodeValue;
					var hidden_input = document.createElement("div");
					hidden_input.style.display="none";
					hidden_input.setAttribute("id", sug_id + "h");
					hidden_input.innerHTML = inner_result_id[0].firstChild.nodeValue;
					child_div.appendChild(hidden_input);
					var parent_div = document.getElementById(idname);
					parent_div.appendChild(child_div);
				}
			}
		}
		var q = document.getElementById(input.id).value;
		xmlHttp.open("GET", phpscript + "?q=" + q, true);
		xmlHttp.send(null);

	} else {
		autocompClear(input);
		document.body.removeAttribute("onClick");
	}
}

function autocompClear(input) {
	console.log("Cleared");
	var idname = "hasil_" + input.id;
	while (document.getElementById(idname) != null) {
		document.body.removeChild(document.getElementById(idname));
	}
}

function autocompClearAll(){
	var elmt = document.getElementsByClassName("autocomplete");
	for (var i=0;i<elmt.length;i++){
		document.body.removeChild(document.getElementById(elmt[i].id));		
	}
}

function autocompGetResult(input) {
	var idname = input.id + "h";
	console.log(idname);
	var parentname = idname.substr(6, idname.length - 8);
	console.log(parentname);
	var parentdiv = idname.substr(0, idname.length - 2);
	console.log(parentdiv);
	var elmt = document.getElementById(parentname);
	elmt.value = document.getElementById(idname).innerHTML;
	document.body.removeChild(document.getElementById(parentdiv));
}

function autocompHandleClick() {
	var parentdiv = document.getElementsByClassName("autocomplete_container");
	for (var i = 0; i < parentdiv.length; i++) {
		document.body.removeChild(document.getElementById(parentdiv[i].id));
	}
}