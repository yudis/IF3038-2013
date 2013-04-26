var before;
var after;
var username;

function onload(uname) {
    username = uname;
    
    ajax_get("ajax/profilecategory?id=" + username,function(xhr)
    {
                    document.getElementById("tasklist").innerHTML=xhr.responseText;
    });
}

function saveProfile() {
	var str = "";
	if (document.getElementById("oldpass").value != ""){
		if (document.getElementById("Oldpwd").value != calcMD5(document.getElementById("oldpass").value)){
			alert("Old Password different from Current Password");
			return false;
		} else if (document.getElementById("pwd").value == ""){
			alert("New Password is blank");
			return false;
		} else if (document.getElementById("pwd_confirm").value == ""){
			alert("rePassword is blank");
			return false;
		} else if (document.getElementById("pwd_confirm").value != document.getElementById("pwd").value){
			alert("New Password is different from rePassword");
			return false;
		}
	}

	after = new Array();
	after[0] = document.getElementById("fname").value;
	after[1] = document.getElementById("Bday").value;
	after[2] = document.getElementById("pwd_confirm").value;
	
	
	if (after[0] == before[0]){
		str += "- Full name did not changed \n";
	}
	if (after[1] == before[1]){
		str += "- Birthday did not changed \n";
	}
	if (after[2] == before[2]){
		str += "- Password did not changed \n";
	}

	if (str != ""){
		alert(str);
                        
	}

    document.getElementById("nameEditDiv").style.display = "none";
    document.getElementById("birthEditDiv").style.display = "none";
    document.getElementById("passEditLabel").style.display = "none";
    document.getElementById("passEditDetail").style.display = "none";
    document.getElementById("repassEditDetail").style.display = "none";
    document.getElementById("changeAvatarLabel").style.display = "none";
    document.getElementById("changeAvatarDetail").style.display = "none";
    document.getElementById("oldpassEditDetail").style.display = "none";
    document.getElementById("oldpassEditLabel").style.display = "none";
    document.getElementById("doneButton").style.display = "none";
    document.getElementById("nameDisplayDiv").style.display = "block";
    document.getElementById("birthDisplayDiv").style.display = "block";
    document.getElementById("editButton").style.display = "block";

    var birthDisplay = document.getElementById("birthDay");
    var birthTextBox = document.getElementById("Bday");
    birthDisplay.innerHTML = birthTextBox.value;
    var nameDisplay = document.getElementById("Fullnama");
    var nameTextBox = document.getElementById("fname");
    nameDisplay.innerHTML = nameTextBox.value;

    return true;
	// document.getElementById("oldpass").value = "";
	// document.getElementById("pwd").value = "";
	// document.getElementById("pwd_confirm").value = "";
}

function editProfile() {
	before = new Array();
	before[0] = document.getElementById("Fullnama").innerHTML;
	before[1] = document.getElementById("birthDay").innerHTML;
	before[2] = document.getElementById("oldpass").value;

    document.getElementById("nameEditDiv").style.display = "block";
	document.getElementById("birthEditDiv").style.display = "block";
	document.getElementById("passEditLabel").style.display = "block";
	document.getElementById("passEditDetail").style.display = "block";
	document.getElementById("repassEditDetail").style.display = "block";	
	document.getElementById("changeAvatarLabel").style.display = "block";
	document.getElementById("changeAvatarDetail").style.display = "block";
	document.getElementById("oldpassEditDetail").style.display = "block";
	document.getElementById("oldpassEditLabel").style.display = "block";
    document.getElementById("doneButton").style.display = "block";
    document.getElementById("nameDisplayDiv").style.display = "none";
	document.getElementById("birthDisplayDiv").style.display = "none";
    document.getElementById("editButton").style.display = "none";
    
    return false;
}

function validateFullName() {
    var name = document.getElementById("fname");
    var regex = /^[\w]+ [\w ]+$/g;
    
    isValidFullName = true;
    if (!regex.test(name.value)) {
        isValidFullName = false;
    }
    
    if (isValidFullName) {
        name.style.border = '2px #5fae53 solid';
    } else {
        name.style.border = '2px red solid';
    }
    
}

function validateBday() {
    var bday = document.getElementById("bday");
    var regex = /^[0-9]{4,4}-[0-9]{1,2}-[0-9]{1,2}$/g;
    
    isValidBday = true;
    if (!regex.test(bday.value)) {
        isValidBday = false;
    }
    
    if (isValidBday) {
        if (bday.value.substr(0, 4) < 1955) {
            isValidBday = false;
        }
    }
    
    if (isValidBday) {
        bday.style.border = '2px #5fae53 solid';
    } else {
        bday.style.border = '2px red solid';
    }
    
}

function validatePassword() {
    var pwd = document.getElementById("pwd");
    var regex = /^[\w\W]{8,}$/g;
    
    isValidPassword = true;
    if (!regex.test(pwd.value)) {
        isValidPassword = false;
    }
    
    var uname = document.getElementById("usernameDisplayDiv");
    var email = document.getElementById("emailValue");
    if (uname.innerHTML == pwd.value || email.innerHTML == pwd.value) {
        isValidPassword = false;
    }
    
    if (isValidPassword) {
        pwd.style.border = '2px #5fae53 solid';
    } else {
        pwd.style.border = '2px red solid';
    }
    
}

function validateOldPassword() {
    var oldpass = document.getElementById("oldpass");
    var regex = /^[\w\W]{8,}$/g;
    
    isValidPassword = true;
    if (!regex.test(oldpass.value)) {
        isValidPassword = false;
    }
    
    var uname = document.getElementById("usernameDisplayDiv");
    var email = document.getElementById("emailValue");
    if (uname.innerHTML == oldpass.value || email.innerHTML == oldpass.value) {
        isValidPassword = false;
    }
    
    if (isValidPassword) {
        oldpass.style.border = '2px #5fae53 solid';
    } else {
        oldpass.style.border = '2px red solid';
    }
    
}

function validateRePassword() {
    var pwd = document.getElementById("pwd");
    var pwd_confirm = document.getElementById("pwd_confirm");
    
    isValidRePassword= true;
    if (pwd.value != pwd_confirm.value) {
        isValidRePassword = false;
    }
    
    if (isValidRePassword) {
        pwd_confirm.style.border = '2px #5fae53 solid';
    } else {
        pwd_confirm.style.border = '2px red solid';
    }
    
}




function validateAvatar() {
    var xAvatar = document.getElementById("ava");
    var str1=/\.jpg/g;
    var str2=/\.jpeg/g;
    
    isValidPhoto = true;

    if (!str1.test(xAvatar.value) && !str2.test(xAvatar.value)) {
        isValidPhoto = false;
    }
    
    if (isValidPhoto) {
        xAvatar.style.border = '2px #5fae53 solid';
    } else {
        xAvatar.style.border = '2px red solid';
    }
	
}
		
function updateStatus(n,str) {
    if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		document.getElementById("stats").innerHTML=xmlhttp.responseText;
		}
	  }
          var x;
          if (n == "true"){
              x = 1;
          } else if (n == "false"){
              x = 0;
          }
	xmlhttp.open("GET","ajax/UpdateStatus?q="+str+"&n="+x,true);
	xmlhttp.send();
	
	return false;
}
	
/*
 * A JavaScript implementation of the RSA Data Security, Inc. MD5 Message
 * Digest Algorithm, as defined in RFC 1321.
 * Copyright (C) Paul Johnston 1999 - 2000.
 * Updated by Greg Holt 2000 - 2001.
 * See http://pajhome.org.uk/site/legal.html for details.
 */

/*
 * Convert a 32-bit number to a hex string with ls-byte first
 */
var hex_chr = "0123456789abcdef";
function rhex(num)
{
  str = "";
  for(j = 0; j <= 3; j++)
    str += hex_chr.charAt((num >> (j * 8 + 4)) & 0x0F) +
           hex_chr.charAt((num >> (j * 8)) & 0x0F);
  return str;
}

/*
 * Convert a string to a sequence of 16-word blocks, stored as an array.
 * Append padding bits and the length, as described in the MD5 standard.
 */
function str2blks_MD5(str)
{
  nblk = ((str.length + 8) >> 6) + 1;
  blks = new Array(nblk * 16);
  for(i = 0; i < nblk * 16; i++) blks[i] = 0;
  for(i = 0; i < str.length; i++)
    blks[i >> 2] |= str.charCodeAt(i) << ((i % 4) * 8);
  blks[i >> 2] |= 0x80 << ((i % 4) * 8);
  blks[nblk * 16 - 2] = str.length * 8;
  return blks;
}

/*
 * Add integers, wrapping at 2^32. This uses 16-bit operations internally 
 * to work around bugs in some JS interpreters.
 */
function add(x, y)
{
  var lsw = (x & 0xFFFF) + (y & 0xFFFF);
  var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
  return (msw << 16) | (lsw & 0xFFFF);
}

/*
 * Bitwise rotate a 32-bit number to the left
 */
function rol(num, cnt)
{
  return (num << cnt) | (num >>> (32 - cnt));
}

/*
 * These functions implement the basic operation for each round of the
 * algorithm.
 */
function cmn(q, a, b, x, s, t)
{
  return add(rol(add(add(a, q), add(x, t)), s), b);
}
function ff(a, b, c, d, x, s, t)
{
  return cmn((b & c) | ((~b) & d), a, b, x, s, t);
}
function gg(a, b, c, d, x, s, t)
{
  return cmn((b & d) | (c & (~d)), a, b, x, s, t);
}
function hh(a, b, c, d, x, s, t)
{
  return cmn(b ^ c ^ d, a, b, x, s, t);
}
function ii(a, b, c, d, x, s, t)
{
  return cmn(c ^ (b | (~d)), a, b, x, s, t);
}

/*
 * Take a string and return the hex representation of its MD5.
 */
function calcMD5(str)
{
  x = str2blks_MD5(str);
  a =  1732584193;
  b = -271733879;
  c = -1732584194;
  d =  271733878;

  for(i = 0; i < x.length; i += 16)
  {
    olda = a;
    oldb = b;
    oldc = c;
    oldd = d;

    a = ff(a, b, c, d, x[i+ 0], 7 , -680876936);
    d = ff(d, a, b, c, x[i+ 1], 12, -389564586);
    c = ff(c, d, a, b, x[i+ 2], 17,  606105819);
    b = ff(b, c, d, a, x[i+ 3], 22, -1044525330);
    a = ff(a, b, c, d, x[i+ 4], 7 , -176418897);
    d = ff(d, a, b, c, x[i+ 5], 12,  1200080426);
    c = ff(c, d, a, b, x[i+ 6], 17, -1473231341);
    b = ff(b, c, d, a, x[i+ 7], 22, -45705983);
    a = ff(a, b, c, d, x[i+ 8], 7 ,  1770035416);
    d = ff(d, a, b, c, x[i+ 9], 12, -1958414417);
    c = ff(c, d, a, b, x[i+10], 17, -42063);
    b = ff(b, c, d, a, x[i+11], 22, -1990404162);
    a = ff(a, b, c, d, x[i+12], 7 ,  1804603682);
    d = ff(d, a, b, c, x[i+13], 12, -40341101);
    c = ff(c, d, a, b, x[i+14], 17, -1502002290);
    b = ff(b, c, d, a, x[i+15], 22,  1236535329);    

    a = gg(a, b, c, d, x[i+ 1], 5 , -165796510);
    d = gg(d, a, b, c, x[i+ 6], 9 , -1069501632);
    c = gg(c, d, a, b, x[i+11], 14,  643717713);
    b = gg(b, c, d, a, x[i+ 0], 20, -373897302);
    a = gg(a, b, c, d, x[i+ 5], 5 , -701558691);
    d = gg(d, a, b, c, x[i+10], 9 ,  38016083);
    c = gg(c, d, a, b, x[i+15], 14, -660478335);
    b = gg(b, c, d, a, x[i+ 4], 20, -405537848);
    a = gg(a, b, c, d, x[i+ 9], 5 ,  568446438);
    d = gg(d, a, b, c, x[i+14], 9 , -1019803690);
    c = gg(c, d, a, b, x[i+ 3], 14, -187363961);
    b = gg(b, c, d, a, x[i+ 8], 20,  1163531501);
    a = gg(a, b, c, d, x[i+13], 5 , -1444681467);
    d = gg(d, a, b, c, x[i+ 2], 9 , -51403784);
    c = gg(c, d, a, b, x[i+ 7], 14,  1735328473);
    b = gg(b, c, d, a, x[i+12], 20, -1926607734);
    
    a = hh(a, b, c, d, x[i+ 5], 4 , -378558);
    d = hh(d, a, b, c, x[i+ 8], 11, -2022574463);
    c = hh(c, d, a, b, x[i+11], 16,  1839030562);
    b = hh(b, c, d, a, x[i+14], 23, -35309556);
    a = hh(a, b, c, d, x[i+ 1], 4 , -1530992060);
    d = hh(d, a, b, c, x[i+ 4], 11,  1272893353);
    c = hh(c, d, a, b, x[i+ 7], 16, -155497632);
    b = hh(b, c, d, a, x[i+10], 23, -1094730640);
    a = hh(a, b, c, d, x[i+13], 4 ,  681279174);
    d = hh(d, a, b, c, x[i+ 0], 11, -358537222);
    c = hh(c, d, a, b, x[i+ 3], 16, -722521979);
    b = hh(b, c, d, a, x[i+ 6], 23,  76029189);
    a = hh(a, b, c, d, x[i+ 9], 4 , -640364487);
    d = hh(d, a, b, c, x[i+12], 11, -421815835);
    c = hh(c, d, a, b, x[i+15], 16,  530742520);
    b = hh(b, c, d, a, x[i+ 2], 23, -995338651);

    a = ii(a, b, c, d, x[i+ 0], 6 , -198630844);
    d = ii(d, a, b, c, x[i+ 7], 10,  1126891415);
    c = ii(c, d, a, b, x[i+14], 15, -1416354905);
    b = ii(b, c, d, a, x[i+ 5], 21, -57434055);
    a = ii(a, b, c, d, x[i+12], 6 ,  1700485571);
    d = ii(d, a, b, c, x[i+ 3], 10, -1894986606);
    c = ii(c, d, a, b, x[i+10], 15, -1051523);
    b = ii(b, c, d, a, x[i+ 1], 21, -2054922799);
    a = ii(a, b, c, d, x[i+ 8], 6 ,  1873313359);
    d = ii(d, a, b, c, x[i+15], 10, -30611744);
    c = ii(c, d, a, b, x[i+ 6], 15, -1560198380);
    b = ii(b, c, d, a, x[i+13], 21,  1309151649);
    a = ii(a, b, c, d, x[i+ 4], 6 , -145523070);
    d = ii(d, a, b, c, x[i+11], 10, -1120210379);
    c = ii(c, d, a, b, x[i+ 2], 15,  718787259);
    b = ii(b, c, d, a, x[i+ 9], 21, -343485551);

    a = add(a, olda);
    b = add(b, oldb);
    c = add(c, oldc);
    d = add(d, oldd);
  }
  return rhex(a) + rhex(b) + rhex(c) + rhex(d);
}
	
	