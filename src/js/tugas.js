var Day = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];
var Mon = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Des"];

var id_task = document.getElementById("id_task").value;

Rp(function() 
{
	function buildcomment(comment)
	{
		var commentList = document.getElementById("commentsList");
		var string = '<article id="comment_'+comment.id_komentar+'" class="comment">';
			string += '<a href="profile?id='+comment.id_user+'">';
				string += '<img src="upload/user_profile_pict/'+comment.avatar+'" alt="'+comment.fullname+'" class="icon_pict" >';
			string += '</a>';
			string += '<div class="right">';
				date = new Date(comment.timestamp);
				var temphour = (date.getHours()>=10)? "" : "0";
				var tempmin = (date.getMinutes()>=10)? "" : "0";
				string += temphour + date.getHours()+":"+tempmin+date.getMinutes()+" – "+Day[date.getDay()]+"/"+Mon[date.getMonth()];
				if (comment.id_user==id_user)
					string += ' <a href="javascript:delete_comment('+comment.id_komentar+')">DELETE</a>';
			string += '</div>';
			string += '<header>';
				string += '<a href="profile?id='+comment.id_user+'">';
					string += '<h4>'+comment.username+'</h4>';
				string += '</a>';
			string += '</header>';
			string += '<p>';
				string += comment.komentar;
			string += '</p>';
			string += '<div class="clear"></div>';
		string += '</article>';		
		commentList.innerHTML += string;
		total_comment++;
		current_total_comment++;
		var tempstring = (total_comment>1)? "s":"";
		document.getElementById("total_comment").innerHTML = total_comment + " Comment"+tempstring;
	}
	
	function retrievecomment()
	{
		var req = Rp.ajaxRequest();
		req.onreadystatechange = function() {
			switch (req.readyState) {
				case 1:
				case 2:
				case 3:
					// Rp('#commentForm').addClass('loading');
					break;
				case 4:
					// Rp('#commentForm').removeClass('loading');
					try {
						response = Rp.parseJSON(req.responseText);
						for (var i in response)
						{
							buildcomment(response[i]);
							document.getElementById("show_status").innerHTML = "Showing "+current_total_comment+" of "+total_comment;
							timestamp = response[i].timestamp;
						}
					}
					catch (e) {

					}
					break;
			}
		}
		req.get('api/retrieve_comments?id_task=' + id_task+"&timestamp="+timestamp);
	}
	
	var delete_task = document.getElementById("removeTaskLink");
	if (delete_task != undefined) delete_task.onclick = function()
	{
		var serialized = "task_id="+id_task;
		var req = Rp.ajaxRequest('api/delete_task');
		req.onreadystatechange = function() {
			switch (req.readyState) {
				case 1:
				case 2:
				case 3:
					break;
				case 4:
					try {
						response = Rp.parseJSON(req.responseText);
						if (response.success)
						{
							window.location = "dashboard";
						}
					}
					catch (e) {

					}
					break;
			}
		}
		req.post(serialized);
		return false;
	}
	
	Rp('#commentForm').on('submit', function(e) 
	{
		e.preventDefault();
		
		var serialized = Rp(this).serialize();

		var req = Rp.ajaxRequest('api/comment');
		req.onreadystatechange = function() {
			switch (req.readyState) {
				case 1:
				case 2:
				case 3:
					Rp('#commentForm').addClass('loading');
					break;
				case 4:
					Rp('#commentForm').removeClass('loading');
					try {
						response = Rp.parseJSON(req.responseText);
						if (response == "success")
						{
							document.getElementById("commentBody").value = "";
							retrievecomment();
						}
					}
					catch (e) {

					}
					break;
			}
		}
		req.post(serialized);
	});
	
	window.onload = function()
	{
		setInterval(function(){retrievecomment();},7000);
		datePicker.init(document.getElementById("calendar"), document.getElementById("new_tugas"), "deadline");
	}
});

function delete_comment(id)
{
	var serialized = "id="+id;
	var req = Rp.ajaxRequest('api/remove_comment');
	req.onreadystatechange = function() {
		switch (req.readyState) {
			case 1:
			case 2:
			case 3:
				Rp('#commentForm').addClass('loading');
				break;
			case 4:
				Rp('#commentForm').removeClass('loading');
				try {
					response = Rp.parseJSON(req.responseText);
					if (response == "success")
					{
						var element = document.getElementById("comment_"+id);
						element.parentNode.removeChild(element);
						total_comment--;
						current_total_comment--;
						var tempstring = (total_comment>1)? "s":"";
						document.getElementById("total_comment").innerHTML = total_comment + " Comment"+tempstring;
						document.getElementById("show_status").innerHTML = "Showing "+current_total_comment+" of "+total_comment;
					}
				}
				catch (e) {

				}
				break;
		}
	}
	req.post(serialized);
}

function prepend_comment(comment)
{
	var commentList = document.getElementById("commentsList");
	var string = '<article id="comment_'+comment.id_komentar+'" class="comment">';
		string += '<a href="profile?id='+comment.id_user+'">';
			string += '<img src="upload/user_profile_pict/'+comment.avatar+'" alt="'+comment.fullname+'" class="icon_pict" >';
		string += '</a>';
		string += '<div class="right">';
			date = new Date(comment.timestamp);
			var temphour = (date.getHours()>=10)? "" : "0";
			var tempmin = (date.getMinutes()>=10)? "" : "0";
			var tempstring = temphour + date.getHours()+":"+tempmin+date.getMinutes()+" – "+Day[date.getDay()]+"/"+Mon[date.getMonth()];
			string += tempstring;
			if (comment.id_user==id_user)
				string += ' <a href="javascript:delete_comment('+comment.id_komentar+')">DELETE</a>';
		string += '</div>';
		string += '<header>';
			string += '<a href="profile?id='+comment.id_user+'">';
				string += '<h4>'+comment.username+'</h4>';
			string += '</a>';
		string += '</header>';
		string += '<p>';
			string += comment.komentar;
		string += '</p>';
		string += '<div class="clear"></div>';
	string += '</article>';
	commentList.innerHTML = string + commentList.innerHTML;
}

function more_comment()
{
	var req = Rp.ajaxRequest();
	req.onreadystatechange = function() {
		switch (req.readyState) {
			case 1:
			case 2:
			case 3:
				Rp('#commentList').addClass('loading');
				break;
			case 4:
				Rp('#commentList').removeClass('loading');
				try {
					response = Rp.parseJSON(req.responseText);
					for (var i in response)
					{
						prepend_comment(response[i]);
						first_timestamp = response[i].timestamp;
						current_total_comment++;
					}
					var tempstr = (total_comment-current_total_comment>1) ? "s" : "";
					if (total_comment==current_total_comment)
					{
						var element = document.getElementById("more_link");
						element.parentNode.removeChild(element);
					}
					else
					{
						document.getElementById("more_link").innerHTML = "Previous Comment"+tempstr;
					}
					document.getElementById("show_status").innerHTML = "Showing "+current_total_comment+" of "+total_comment;
				}
				catch (e) {

				}
				break;
		}
	}
	req.get('api/get_previous_comments?id_task=' + id_task+"&timestamp="+first_timestamp);
}

var asignee = document.getElementById("assignee");
asignee.onkeyup = function()
{
	var value = asignee.value;
	var req = Rp.ajaxRequest();
	req.onreadystatechange = function() {
		switch (req.readyState) {
			case 1:
			case 2:
			case 3:
				Rp('#assignee').addClass('loading');
				break;
			case 4:
				Rp('#assignee').removeClass('loading');
				try {
					response = Rp.parseJSON(req.responseText);
					var elm = document.getElementById("auto_comp_assignee");
					var inflate = document.getElementById("auto_comp_inflate_assignee");
					inflate.innerHTML = "";
					var temp = 0;
					for (var i in response)
					{
						temp++;
						var newLi = document.createElement("li");
						newLi.innerHTML = "<a href='javascript:choose_assignee(\""+response[i].data.username+"\")'>"+
											response[i].data.username+"</a>";
						inflate.insertBefore(newLi, inflate.firstChild);
					}
					
					if (temp!=0)
						elm.style.display = "block";
				}
				catch (e) {

				}
				break;
		}
	}
	req.get('api/get_username?username=' + value);
}

function choose_assignee(username)
{
	var elm = document.getElementById("auto_comp_assignee");
	var value = asignee.value;
	value = value.substr(0, value.lastIndexOf(",")+1);
	value += username;
	asignee.value = value;
	elm.style.display = "none";
}

var tag = document.getElementById("tag");
tag.onkeyup = function()
{
	var value = tag.value;
	var req = Rp.ajaxRequest();
	req.onreadystatechange = function() {
		switch (req.readyState) {
			case 1:
			case 2:
			case 3:
				Rp('#tag').addClass('loading');
				break;
			case 4:
				Rp('#tag').removeClass('loading');
				try {
					response = Rp.parseJSON(req.responseText);
					var elm = document.getElementById("auto_comp_tag");
					var inflate = document.getElementById("auto_comp_inflate_tag");
					inflate.innerHTML = "";
					var temp = 0;
					for (var i in response)
					{
						temp ++;
						var newLi = document.createElement("li");
						newLi.innerHTML = "<a href='javascript:choose_tag(\""+response[i].data.tag_name+"\")'>"+
											response[i].data.tag_name+"</a>";
						inflate.insertBefore(newLi, inflate.firstChild);
					}
					
					if (temp!=0)
						elm.style.display = "block";
				}
				catch (e) {

				}
				break;
		}
	}
	req.get('api/get_tag?tag=' + value);
}

function choose_tag(temptag)
{
	var elm = document.getElementById("auto_comp_tag");
	var value = tag.value;
	value = value.substr(0, value.lastIndexOf(",")+1);
	value += temptag;
	tag.value = value;
	elm.style.display = "none";
}
