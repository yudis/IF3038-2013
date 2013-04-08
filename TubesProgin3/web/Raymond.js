function getXmlHttpRequestObject() {
	if (window.XMLHttpRequest) {// Mozilla/Safari
		return new XMLHttpRequest();
	} else if(window.ActiveXObject) {// IE
		return new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		alert("Your Browser Sucks!");
	}
}
var xmlHttpReq = getXmlHttpRequestObject();
var lanjut = 1;
function checkFile(id) {
	var fileUrl = document.getElementById(id).value.split("\\");
	fileUrl = fileUrl[fileUrl.length - 1];
	xmlHttpReq.open('HEAD', "uploadedFiles//"+fileUrl, true);
	xmlHttpReq.onreadystatechange = function() {
		if (xmlHttpReq.readyState == 4) {
			if (xmlHttpReq.status == 200) {
				alert('File sudah ada di server!');
			}else if (self.xmlHttpReq.status == 404) {
		checkpostal(id);
	}
		}
	}
	xmlHttpReq.send();
}

function checkNamaTugas(){
	var NamaTugas = document.getElementById("tugas").value;
	if (NamaTugas.match(/^[a-zA-Z0-9 ]+$/) && NamaTugas.length <= 25)
	{
		<!-- benar -->
		document.getElementById("nlengkappic").src="image/true.png";
		lanjut = 0;
	} else{
		document.getElementById("nlengkappic").src="image/false.png";
		lanjut = 1;
	}
}

function checkpostal(id){
	var jpg=/\.jpg$/
	var png=/\.png$/
	var ogg=/\.ogg$/
	var mp4=/\.mp4$/
	if ((document.getElementById(id).value.search(jpg)!=-1) || (document.getElementById(id).value.search(png)!=-1))
	{
		alert("File bertipe gambar!");
	}
	else if ((document.getElementById(id).value.search(ogg)!=-1) || (document.getElementById(id).value.search(mp4)!=-1)){	
		alert("File bertipe video!");
	}
	else{
	alert("File bertipe file!");
	}
}
function checkSubmission(e, evt)
{
	if (lanjut == 0)
	{
		e.submit
	}else{
	evt.preventDefault();
	alert("Nama masih salah!")}
}

function searchSuggest() {
	if (xmlHttpReq.readyState == 4 || xmlHttpReq.readyState == 0) {
		var str = escape(document.getElementById('asignee').value);
		xmlHttpReq.open("GET", 'GetUsers.php?nama=' + str, true);
		xmlHttpReq.onreadystatechange = handleSearchSuggest; 
		xmlHttpReq.send(null);
	}		
}
function handleSearchSuggest() {
	if (xmlHttpReq.readyState == 4) {
		var ss = document.getElementById('layer1');
		var str =xmlHttpReq.responseText.split("\n");
		if(str.length==1)
		{
			document.getElementById('layer1').className = "hidden";
		}
		else
		ss.className = 'suggestBox';
		ss.innerHTML = '';
		for(i=0; i < str.length - 1; i++) {
			//Build our element string.  This is cleaner using the DOM, but
			//IE doesn't support dynamically added attributes.
			var suggest = '<div onmouseover="javascript:suggestOver(this);" ';
			suggest += 'onmouseout="javascript:suggestOut(this);" ';
			suggest += 'onclick="javascript:setSearch(this.innerHTML);" ';
			suggest += 'class="small">' + str[i] + '</div>';
			ss.innerHTML += suggest;
		}
	}
}

//Mouse over function
function suggestOver(div_value) {
	div_value.className = 'suggest_link_over';
}
//Mouse out function
function suggestOut(div_value) {
	div_value.className = 'suggest_link';
}
//Click function
function setSearch(value) {
	document.getElementById('asignee').value = value;
	document.getElementById('layer1').innerHTML = '';
	document.getElementById('layer1').className = "hidden";
}

function searchSuggestKeyword() {
	if (xmlHttpReq.readyState == 4 || xmlHttpReq.readyState == 0) {
		var str = escape(document.getElementById('keyword').value);
		xmlHttpReq.open("GET", 'GetAll.php?nama=' + str, true);
		xmlHttpReq.onreadystatechange = handleSearchSuggestKeyword; 
		xmlHttpReq.send(null);
	}		
}
function handleSearchSuggestKeyword() {
	if (xmlHttpReq.readyState == 4) {
		var ss = document.getElementById('layer');
		var str =xmlHttpReq.responseText.split("\n");
		if(str.length==1)
		{
			document.getElementById('layer').className = "hidden";
		}
		else
		ss.className = 'suggestBox';
		ss.innerHTML = '';
		for(i=0; i < str.length - 1; i++) {
			//Build our element string.  This is cleaner using the DOM, but
			//IE doesn't support dynamically added attributes.
			var suggest = '<div onmouseover="javascript:suggestOver(this);" ';
			suggest += 'onmouseout="javascript:suggestOut(this);" ';
			suggest += 'onclick="javascript:setSearchKeyword(this.innerHTML);" ';
			suggest += 'class="small">' + str[i] + '</div>';
			ss.innerHTML += suggest;
		}
	}
}
//Click function
function setSearchKeyword(value) {
	document.getElementById('keyword').value = value;
	document.getElementById('layer').innerHTML = '';
	document.getElementById('layer').className = "hidden";
}

/* SEARCH*/
function doSearch(filter, keyword, i, user) {
	if (xmlHttpReq.readyState == 4 || xmlHttpReq.readyState == 0) {
		//var str = escape(document.getElementById('asignee').value);
		xmlHttpReq.open("GET", 'DoSearch?filter=' + filter + '&keyword=' + escape(keyword)+ '&id=' + i+ '&user=' + user, true);
		//alert("memee");
                xmlHttpReq.onreadystatechange = handleDoSearch; 
		xmlHttpReq.send(null);
	}		
}
function handleDoSearch() {
	if (xmlHttpReq.readyState == 4) {
		var ss = document.getElementById('searchAll');
		var str =xmlHttpReq.responseText;
		ss.innerHTML += str;
		alert(str);
	}
}

var ids = null;
//Starts the AJAX request.
function changevalues(id) {
	ids = id;
	if (xmlHttpReq.readyState == 4 || xmlHttpReq.readyState == 0) {
		xmlHttpReq.open("GET", 'ChangeTaskStatus.php?id='+id, true);
		xmlHttpReq.onreadystatechange = handleStatusChange; 
		xmlHttpReq.send(null);
	}		
}

//Called when the AJAX response is returned.
function handleStatusChange() {
	if (xmlHttpReq.readyState == 4) {
	}
}