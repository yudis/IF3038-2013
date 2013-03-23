var ajaxRequest;

function getAjax() //a function to get AJAX from browser
{
	try
	{
		ajaxRequest = new XMLHttpRequest();
	}
	catch (e)
	{
		try
		{
			ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e) 
		{
			try
			{
				ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e)
			{
				alert("Can't get AJAX, browser error");
				return false;
			}
		}
	}
}

function addComment(taskid){
	getAjax();
	var comment = document.getElementById("textarea-comment").value;
	
	if(comment!=""){
		ajaxRequest.open("GET","../php/updatecomment.php?comment="+comment+"&taskid="+taskid,false);
	
		ajaxRequest.onreadystatechange = function()
		{
			document.getElementById("user-comment").innerHTML =  ajaxRequest.responseText;
		}
		
		ajaxRequest.send();
	}
}

function edit_deadline() {
	document.getElementById("deadline_edit").style.display = 'block';
	document.getElementById("deadline_done").style.display = 'none';
}
function finish_deadline() {
	document.getElementById("deadline_edit").style.display = 'none';
	document.getElementById("deadline_done").style.display = 'block';
}
function edit_assignee() {
	document.getElementById("assignee_edit").style.display = 'block';
	document.getElementById("assignee_done").style.display = 'none';
}
function finish_assignee(taskid) {
	getAjax();
	var tag = document.getElementById("task-assignee").value;
	if(tag!=""){
		ajaxRequest.open("GET","../php/updateSharedWithDB.php?tag="+tag+"&taskid="+taskid,false);

		ajaxRequest.onreadystatechange = function()
		{
			document.getElementById("left-main-body2").innerHTML = ajaxRequest.responseText;
		}
		ajaxRequest.send();
	}
	document.getElementById("assignee_edit").style.display = 'none';
	document.getElementById("assignee_done").style.display = 'block';
}
function edit_tag() {
	document.getElementById("tag_edit").style.display = 'block';
	document.getElementById("tag_done").style.display = 'none';
}
function finish_tag(taskid) {
	getAjax();
	var tag = document.getElementById("tag-edit").value;
	if(tag!=""){
		ajaxRequest.open("GET","../php/updateTagDB.php?tag="+tag+"&taskid="+taskid,false);

		ajaxRequest.onreadystatechange = function()
		{
			document.getElementById("left-main-body3").innerHTML = ajaxRequest.responseText;
		}
		ajaxRequest.send();
	}
	document.getElementById("tag_edit").style.display = 'none';
	document.getElementById("tag_done").style.display = 'block';
}
function check_html5() {
	if (navigator.userAgent.indexOf('Chrome') != -1 || navigator.userAgent.indexOf('Opera') != -1){
		document.getElementById("date_html5").style.display = 'block';
		document.getElementById("date_html").style.display = 'none';
	} else {
		document.getElementById("date_html5").style.display = 'none';
		document.getElementById("date_html").style.display = 'block';
	}
}
function myFunction(e)
{
	e.preventDefault();
	var x;
	var name = document.getElementById("textarea-comment").value;
	if (name!=null)
	{
		x="<div id=comment><div id=user-info><div id=left-comment-body><img src=../image/ecky.jpg width=50px height=50px"+
			"/></div><div id=right-comment-body><b id=komentator>meckyr</b><br><b id=post-date"+
			">Post at 4.30 AM, February 4th, 2013</b></div></div><div id=comment-box><p>"+name+"</p></div></div>";
		document.getElementById("new-comment").innerHTML=x;
	}
}

function autoCompleteAsignee() {
	getAjax();

	var asignee = document.getElementById("task-assignee").value;
	var suggestion = "";
	var suggestionarray;
	
	var index = asignee.length;
	
	if(asignee!="")
	{
		if (asignee.charAt(index - 1) == ',')
		{
			konkat = asignee.substr(0,index);
		}
		
		ajaxRequest.open("GET","../php/autocompleteasignee.php?asignee="+document.getElementById("task-assignee").value,false);

		ajaxRequest.onreadystatechange = function()
		{
			suggestion =  ajaxRequest.responseText;
			suggestion = suggestion.substr(0,suggestion.length-1);
			suggestionarray = suggestion.split("|");
			//alert(suggestionarray);
			
			var x;
			x="<datalist id=\"assignee-task\">";
			for (var i = 0; i < suggestionarray.length; i++) {
				x += "<option value=\""+konkat+suggestionarray[i]+"\">";
			}
			x += "</datalist>";
			document.getElementById("shared-with").innerHTML=x;
		}
		
		ajaxRequest.send();
	}
	else
	{
		konkat = "";
	}
	
	//ajaxRequest.open("GET","php/checkavailid.php?idinput="+document.getElementById("username").value,false);
}

function setCompleteStatus(taskid){
	getAjax();
	var status = document.getElementById("left-main-body4").innerHTML;
	status = status.substring(9,status.length);
	//alert("aaaaaaaaa "+document.getElementById("red-text"+idx).innerHTML);
	if(status!=""){
		ajaxRequest.open("GET","../php/updatecompletestatus.php?status="+status+"&taskid="+taskid,false);
	
		ajaxRequest.onreadystatechange = function()
		{
			document.getElementById("left-main-body4").innerHTML =  "Status : "+ajaxRequest.responseText;
		}
		
		ajaxRequest.send();
	}
}
