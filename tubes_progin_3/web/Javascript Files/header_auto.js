function h_autocomp(input, namescript) {
	var idname = "hasil_" + input.id;
	var elmt = document.getElementById(input.id);
	var opt_a = document.getElementById('option_all');
	var opt_u = document.getElementById('option_user');
	var opt_c = document.getElementById('option_category');
	var opt_t = document.getElementById('option_task');
	var usr = document.getElementById('usr').value;
	if (elmt.value.length > 0) {
		document.body.setAttribute("onClick", "h_autocompHandleClick();");
		h_autocompClear(input);
		var div = document.createElement("div");
		div.setAttribute("id", idname);
		div.setAttribute("class", "h_autocomplete_container");
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
				var parent_div = document.getElementById(idname);
				var j = 0;
				var i = 0;
				//Get user starts here
				if (opt_a.checked || opt_u.checked) {
					var query_result = xmlHttp.responseXML.documentElement.getElementsByTagName("User");
					if (query_result.length > 0) {
						var divisor = document.createElement("div");
						divisor.setAttribute("class", "h_autocomplete_div");
						divisor.style.width = elmt.offsetWidth;
						divisor.innerHTML = "User";
						parent_div.appendChild(divisor);
					}
					for ( i = 0; i < query_result.length; i++) {
						var inner_result_id = query_result[i].getElementsByTagName("ID");
						var inner_result_name = query_result[i].getElementsByTagName("String");
						var sug_id = idname + i;
                                                var anchor = document.createElement("a");
                                                anchor.href = "profile.jsp?userprofile="+inner_result_id[0].firstChild.nodeValue;
                                                anchor.innerHTML = inner_result_name[0].firstChild.nodeValue;
						var child_div = document.createElement("div");
						child_div.setAttribute("id", sug_id);
						child_div.setAttribute("class", "h_autocomplete");
						child_div.setAttribute("onClick", "h_autocompGetResult(this);");
						child_div.style.width = elmt.offsetWidth;
                                                child_div.appendChild(anchor);
                                                parent_div.appendChild(child_div);
					}
				}
				//Get category starts here
				if (opt_a.checked || opt_c.checked) {
					var query_result = xmlHttp.responseXML.documentElement.getElementsByTagName("Category");
					if (query_result.length > 0) {
						var divisor = document.createElement("div");
						divisor.setAttribute("class", "h_autocomplete_div");
						divisor.style.width = elmt.offsetWidth;
						divisor.innerHTML = "Category";
						parent_div.appendChild(divisor);
					}
					j = i;
					for ( i = 0; i < query_result.length; i++) {
						var inner_result_id = query_result[i].getElementsByTagName("ID");
						var inner_result_name = query_result[i].getElementsByTagName("String");
						var sug_id = idname + (i + j);
						var child_div = document.createElement("div");
						child_div.setAttribute("id", sug_id);
						child_div.setAttribute("class", "h_autocomplete");
						child_div.setAttribute("onClick", "h_autocompGetResult(this);");
						child_div.style.width = elmt.offsetWidth;
						child_div.innerHTML = inner_result_name[0].firstChild.nodeValue;
						var hidden_input = document.createElement("div");
						hidden_input.style.display = "none";
						hidden_input.setAttribute("id", sug_id + "h");
						hidden_input.innerHTML = inner_result_id[0].firstChild.nodeValue;
						child_div.appendChild(hidden_input);
						parent_div.appendChild(child_div);
					}
				}
				//Get task starts here
				if (opt_a.checked || opt_t.checked) {
					var query_result = xmlHttp.responseXML.documentElement.getElementsByTagName("Task");
					if (query_result.length > 0) {
						var divisor = document.createElement("div");
						divisor.setAttribute("class", "h_autocomplete_div");
						divisor.style.width = elmt.offsetWidth;
						divisor.innerHTML = "Task";
						parent_div.appendChild(divisor);
					}
					j = i + j;
					for ( i = 0; i < query_result.length; i++) {
						var inner_result_id = query_result[i].getElementsByTagName("ID");
						var inner_result_name = query_result[i].getElementsByTagName("String");
						var sug_id = idname + (i+j);
                                                var anchor = document.createElement("a");
                                                anchor.href = "dashboard.jsp?seektask="+inner_result_id[0].firstChild.nodeValue;
                                                anchor.innerHTML = inner_result_name[0].firstChild.nodeValue;
						var child_div = document.createElement("div");
						child_div.setAttribute("id", sug_id);
						child_div.setAttribute("class", "h_autocomplete");
						child_div.setAttribute("onClick", "h_autocompGetResult(this);");
						child_div.style.width = elmt.offsetWidth;
                                                child_div.appendChild(anchor);
                                                parent_div.appendChild(child_div);
					}
				}
			}
		}
		var q = document.getElementById(input.id).value;

		var opt;
		if (opt_a.checked) {
			opt = 'a';
		} else if (opt_u.checked) {
			opt = 'u';
		} else if (opt_c.checked) {
			opt = 'c';
		} else if (opt_t.checked) {
			opt = 't';
		}
		console.log(opt);
		xmlHttp.open("GET", namescript + "?q=" + q + "&opt=" + opt + "&usr=" + usr, true);
		xmlHttp.send(null);

	} else {
		h_autocompClear(input);
		document.body.removeAttribute("onClick");
	}
}

function h_autocompClear(input) {
	console.log("Cleared");
	var idname = "hasil_" + input.id;
	while (document.getElementById(idname) != null) {
		document.body.removeChild(document.getElementById(idname));
	}
}

function h_autocompClearAll() {
	var elmt = document.getElementsByClassName("h_autocomplete");
	for (var i = 0; i < elmt.length; i++) {
		document.body.removeChild(document.getElementById(elmt[i].id));
	}
}

function h_autocompGetResult(input) {
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

function h_autocompHandleClick() {
	var parentdiv = document.getElementsByClassName("h_autocomplete_container");
	for (var i = 0; i < parentdiv.length; i++) {
		document.body.removeChild(document.getElementById(parentdiv[i].id));
	}
}