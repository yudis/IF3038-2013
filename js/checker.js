Rp(function() {
	Rp('#email').on('keyup', function() {
		//javascript email regex from regularexpressionsrightnow.com
		match = this.value.match(/(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|”(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*”)@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/);
		if (match&&(this.value!=Rp('#password').val())) {
			// bener
			Rp(this).removeClass('invalid');
			Rp('#submitButton').removeAttr('disabled');
		}
		else {
			// ga valid
			console.log('Invalid');
			Rp(this).addClass('invalid');
			Rp('#submitButton').attr('disabled', 'disabled');
		}
	});
	Rp('#username').on('keyup', function() {
		match = this.value.match(/.{5,}/);
		if ((this.value != Rp('#email').val())&&match){
			//valid
			Rp(this).removeClass('invalid');
			Rp('#submitButton').removeAttr('disabled');
		}else{
			//error
			console.log('Invalid');
			Rp(this).addClass('invalid');
			Rp('#submitButton').attr('disabled', 'disabled');
		}
	});
	Rp('#password').on('keyup',function(){
		match = this.value.match(/.{8,}/);
		if( (this.value!=Rp('#username').val())&&(this.value!=Rp('#email').val())&&match ){
			//valid
			Rp(this).removeClass('invalid');
			Rp('#submitButton').removeAttr('disabled');
		}else{
			//error
			console.log('Invalid');
			Rp(this).addClass('invalid');
			Rp('#submitButton').attr('disabled', 'disabled');
		}
	});
	Rp('#password_k').on('keyup',function(){
		if(this.value==Rp('#password').val()){
			//valid
			Rp(this).removeClass('invalid');
			Rp('#submitButton').removeAttr('disabled');
		}else{
			//error
			console.log('Invalid');
			Rp(this).addClass('invalid');
			Rp('#submitButton').attr('disabled', 'disabled');
		}
	});
	Rp('#nama').on('keyup', function() {
		match = this.value.match(/\W/g);
		console.log(match);
		if (match){
			//error
			console.log('Invalid');
			Rp(this).addClass('invalid');
			Rp('#submitButton').attr('disabled', 'disabled');
		}else{
			//valid
			Rp(this).removeClass('invalid');
			Rp('#submitButton').removeAttr('disabled');
		}
	});
});

// settings
// var FILE_ENCODING = 'utf-8',
    // EOL = '\n';
 

// function
// var error = false;
// var usr = document.getElementById("username").value;
// var pwd = document.getElementById("password").value;
// var email = document.getElementById("email").value;
// var kpwd = document.getElementById("password_k").value;
// function init(){
	// usr = document.getElementById("username").value;
	// pwd = document.getElementById("password").value;
	// email = document.getElementById("email").value;
	// kpwd = document.getElementById("password_k").value;
// }
// function change1(){
	// if (pwd != null && usr == pwd){
		// alert("Username tidak boleh sama dengan password");
		// document.getElementById("errMsg1").innerHTML="Username tidak boleh sama dengan password\n";
		// error = true;
	// }
// }
// function check2(){
	// if (usr != null && usr == pwd){
		// document.getElementById("errMsg1").innerHTML="Username tidak boleh sama dengan password";
		// error = true;
	// }
	// if (email != null && email == pwd){
		// document.getElementById("errMsg2").innerHTML="Email tidak boleh sama dengan password";
		// error = true;
	// }
	// if (kpwd != null && kpwd == pwd){
		// document.getElementById("errMsg3").innerHTML="Password harus sama";
		// error = true;
	// }
// }
// function check3(){
	// var patt=/.+@.+\.{2,}/
	// if(patt.match!=null){
		// document.getElementById("errMsg2").innerHTML="Email error";
		// error = true;
	// }
	// if (email != null && email == pwd){
		// document.getElementById("errMsg2").innerHTML="Email tidak boleh sama dengan password";
		// error = true;
	// }
// }
