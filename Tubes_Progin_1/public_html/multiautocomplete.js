function multiAutocomp(input, phpscript) {
	var idname = "hasil_" + input.id;
	var elmt = document.getElementById(input.id);
	if (elmt.value.length > 0) {
		document.body.setAttribute("onClick", "multiAutocompHandleClick();");
		multiAutocompClear(input);
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
					child_div.setAttribute("onClick", "multiAutocompGetResult(this);");
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
		if(q.length<1){
			xmlHttp.open("GET", phpscript + "?q=" + q, true);
		}else{
			q = q.split(",");
			xmlHttp.open("GET", phpscript + "?q=" + q[q.length-1], true);			
		}
		xmlHttp.send(null);

	} else {
		multiAutocompClear(input);
		document.body.removeAttribute("onClick");
	}
}

function multiAutocompClear(input) {
	console.log("Cleared");
	var idname = "hasil_" + input.id;
	while (document.getElementById(idname) != null) {
		document.body.removeChild(document.getElementById(idname));
	}
}

function multiAutocompClearAll(){
	var elmt = document.getElementsByClassName("autocomplete");
	for (var i=0;i<elmt.length;i++){
		document.body.removeChild(document.getElementById(elmt[i].id));		
	}
}

function multiAutocompGetResult(input) {
	var idname = input.id + "h";
	var parentname = idname.substr(6, idname.length - 8);
	var parentdiv = idname.substr(0, idname.length - 2);
	var elmt = document.getElementById(parentname);
	if(elmt.value.indexOf(",")==-1){
		elmt.value = document.getElementById(idname).innerHTML + ",";
	}else{
		var temp_string = elmt.value;
		temp_string = temp_string.split(",");
		var result_string = "";
		for(var i=0;i<temp_string.length - 1;i++){
			result_string += temp_string[i] + ",";			
		}
		elmt.value = result_string + document.getElementById(idname).innerHTML + ",";
	}
	document.body.removeChild(document.getElementById(parentdiv));
}

function multiAutocompHandleClick() {
	var parentdiv = document.getElementsByClassName("autocomplete_container");
	for (var i = 0; i < parentdiv.length; i++) {
		document.body.removeChild(document.getElementById(parentdiv[i].id));
	}
}