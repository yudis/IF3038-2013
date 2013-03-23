function addEvent(node, type, callback){
	if(node.addEventListener){
		node.addEventListener(type, function(e){
			callback(e, e.target);
			
		}, false);
	}
	else if(node.attachEvent){
		node.attachEvent('on' + type, function(e){
			callback(e, e.srcElement);
		});
	}
}


function shouldBeValidated(field){
	return (
		!(field.getAttribute('readonly') || field.readonly)	&&
		!(field.getAttribute('disabled') || field.disabled)	&&
		(field.getAttribute('pattern') || field.getAttribute('required'))
	); 
}


function instantValidation(field){
	if(shouldBeValidated(field)){
		var invalid = (
			(field.getAttribute('required') && !field.value) ||
			(field.getAttribute('pattern') && field.value && !new RegExp(field.getAttribute('pattern')).test(field.value))
		);

		if(!invalid && field.getAttribute('aria-invalid')){
			field.removeAttribute('aria-invalid');
		}
		else if(invalid && !field.getAttribute('aria-invalid')){
			field.setAttribute('aria-invalid', 'true');
		}
	}
}

var fields = [document.getElementsByTagName('input'), document.getElementsByTagName('textarea')];
for(var a = fields.length, i = 0; i < a; i ++){
	for(var b = fields[i].length, j = 0; j < b; j ++){
		addEvent(fields[i][j], 'change', function(e, target){
			instantValidation(target);
		});
	}
}

function displayResult()
{
	var x=document.getElementById("fileupld").name;
	alert(x);
}

function CheckFiles()
{
	var value = document.getElementById('attachment').value.split('.');
	var lenghtValue = value.length;
	if(lenghtValue <= 1) 
	{
		//alert("file undefined");		
		return false;
	}
	else 
	{
		//alert("image: " + value);
		if(value[lenghtValue-1].toLowerCase() == "jpeg"||
			value[lenghtValue-1].toLowerCase() == "jpg"||
			value[lenghtValue-1].toLowerCase() == "bmp") 			
		{
			//alert("file benar");
			return true;
		}
		else 
		{
			//alert("file salah");
			return false;
		}
	}
}

function playPause()
{ 
	var myVideo=document.getElementById("video1"); 
	if (myVideo.paused) 
		myVideo.play(); 
	else 
		myVideo.pause(); 
} 

// ======================= REGISTER =======================
function checkUsername(){
	if(document.getElementById('username').value == document.getElementById('password').value){
		document.getElementById('register').setAttribute('disabled', 'true');
	}
	else{
		document.getElementById('register').removeAttribute('disabled');
	}
}

function checkPassword(){
	if(document.getElementById('password').value == document.getElementById('username').value ||
		document.getElementById('password').value == document.getElementById('email').value){
		document.getElementById('register').setAttribute('disabled', 'true');
	}
	else{
		document.getElementById('register').removeAttribute('disabled');
	}
}

function checkConfirmPassword(){
	if(document.getElementById('password').value != document.getElementById('confirmpassword').value){
		document.getElementById('register').setAttribute('disabled', 'true');
	}
	else{
		document.getElementById('register').removeAttribute('disabled');
	}
}

function checkDOB(){
	if(!document.getElementById('dob').getAttribute('aria-invalid')){
		document.getElementById('register').setAttribute('disabled', 'true');
	}
	else{
		document.getElementById('register').removeAttribute('disabled');
	}
}

//======================= LOGIN =======================
function checkLogin(){
	var u = document.getElementById("usernamelogin").value;
	var p = document.getElementById("passwordlogin").value;
	
	if(window.XMLHttpRequest){
		xmlhttp = new XMLHttpRequest();
	}
	else{
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			if(xmlhttp.responseText == u){
				//alert(':)');
				window.location = "dashboard.php";
			}
			else{
				alert(':(');
			}
		}
	}

	xmlhttp.open("GET", "login.php?u=" + u + "&p=" + p, true);
	xmlhttp.send();
}

function logout()
{
	if(window.XMLHttpRequest){
		xmlhttp = new XMLHttpRequest();
	}
	else{
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			window.location = "index.php";
		}
	}

	xmlhttp.open("GET", "logout.php", true);
	xmlhttp.send();
}

function toggleSearch(){
	if(getStyle(document.getElementById('search'), 'display') == 'none'){
		document.getElementById('search').style.display = 'inline';
	}
	else{
		document.getElementById('search').style.display = 'none';
	}
}

function getStyle(oElm, strCssRule){
	var strValue = "";
	if(document.defaultView && document.defaultView.getComputedStyle){
		strValue = document.defaultView.getComputedStyle(oElm, "").getPropertyValue(strCssRule);
	}
	else if(oElm.currentStyle){
		strCssRule = strCssRule.replace(/\-(\w)/g, function (strMatch, p1){
			return p1.toUpperCase();
		});
		strValue = oElm.currentStyle[strCssRule];
	}
	return strValue;
}

function redirDetails(){
	window.location="taskdetails.html";
}

function toggleSelectProgin(){
	if(getStyle(document.getElementById('proginrow'), 'background-color') != '#94DBFF'){
		var a = document.getElementById('proginrow').getAttribute('class').split(' ');
		a.splice(1,1,'selected');
		var s = a.join(' ');		
		document.getElementById('proginrow').setAttribute('class', s);

		var a = document.getElementById('kriptorow').getAttribute('class').split(' ');
		a.splice(1,1,'even');
		var s = a.join(' ');
		document.getElementById('kriptorow').setAttribute('class', s);
		
		document.getElementById('plustask').style.display = 'inline';
		document.getElementById('progin').style.display = 'table-row';
		document.getElementById('kripto').style.display = 'none';
	}
	else{
		var a = document.getElementById('proginrow').getAttribute('class').split(' ');
		a.pop();
		var s = a.join(' ');
		document.getElementById('proginrow').setAttribute('class', s);

		document.getElementById('plustask').style.display = 'none';
		document.getElementById('progin').style.display = 'table-row';
		document.getElementById('kripto').style.display = 'table-row';
	}
}

function toggleSelectKripto(){
	if(getStyle(document.getElementById('kriptorow'), 'background-color') != '#94DBFF'){
		var a = document.getElementById('kriptorow').getAttribute('class').split(' ');
		a.splice(1,1,'selected');
		var s = a.join(' ');		
		document.getElementById('kriptorow').setAttribute('class', s);
		
		var a = document.getElementById('proginrow').getAttribute('class').split(' ');
		a.pop();
		var s = a.join(' ');
		document.getElementById('proginrow').setAttribute('class', s);
		
		document.getElementById('plustask').style.display = 'inline';
		document.getElementById('kripto').style.display = 'table-row';
		document.getElementById('progin').style.display = 'none';
	}
	else{
		var a = document.getElementById('kriptorow').getAttribute('class').split(' ');
		a.splice(1,1,'even');
		var s = a.join(' ');
		document.getElementById('kriptorow').setAttribute('class', s);
		
		document.getElementById('plustask').style.display = 'none';
		document.getElementById('progin').style.display = 'table-row';
		document.getElementById('kripto').style.display = 'table-row';
	}
}

function redirAdd(){
	window.location="addtask.html";
}

function handleFileSelect(evt) {
	var files = evt.target.files;

	for (var i = 0, f; f = files[i]; i++) {

	  if (!f.type.match('image.*')) {
		continue;
	  }

	  var reader = new FileReader();

	  reader.onload = (function(theFile) {
		return function(e) {
		  var span = document.createElement('span');
		  span.innerHTML = ['<img class="thumb" src="', e.target.result,
							'" title="', escape(theFile.name), '"/>'].join('');
		  document.getElementById('list').insertBefore(span, null);
		};
	  })(f);

	  // Read in the image file as a data URL.
	  reader.readAsDataURL(f);
	}
}

document.getElementById('files').addEventListener('change', handleFileSelect, false);

function popupcat(){
	window.open( "addcat/" )
}