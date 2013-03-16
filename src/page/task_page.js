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
function finish_assignee() {
	document.getElementById("assignee_edit").style.display = 'none';
	document.getElementById("assignee_done").style.display = 'block';
}
function edit_tag() {
	document.getElementById("tag_edit").style.display = 'block';
	document.getElementById("tag_done").style.display = 'none';
}
function finish_tag() {
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