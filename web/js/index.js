/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


                    function makeTgl()
                        {
				for(var i=1; i<=31; i++)
                                {
					var isi=document.createTextNode(i);
					var opsi = document.createElement("option");
					opsi.setAttribute("value",i);
					opsi.appendChild(isi);
					document.getElementById("tgl").appendChild(opsi);
				}
			}

			function makeThn(){
				for(var i=1955; i<=2013; i++){
					var isi=document.createTextNode(i);
					var opsi = document.createElement("option");
					opsi.setAttribute("value",i);
					opsi.appendChild(isi);
					document.getElementById("thn").appendChild(opsi);
				}
			}

			function enableRegister(){
				document.getElementById("register_button").disabled = false;
			}

			function checkEmail(email){	
				var pattern=/^([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
				if(pattern.test(email)){         
					document.getElementById('v_email').innerHTML='<font color="green">Benar</font>'; 
					return true;
				}else{   
					document.getElementById('v_email').innerHTML='<font color="red">Salah</font>';
					return false;
				}
			}			

			function checkUserName(uname){	
				var pattern=/^([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+/;
				if(pattern.test(uname)){         
					document.getElementById('v_uname').innerHTML='<font color="green">Benar</font>';
					return true;
				}else{   
					document.getElementById('v_uname').innerHTML='<font color="red">Salah</font>';
					return false;
				}
			}			

			function checkCPass(cpass, pass){
				if(cpass == pass){
					document.getElementById('v_cpass').innerHTML='<font color="green">Benar</font>';
					return true;					
				}else{
					document.getElementById('v_cpass').innerHTML='<font color="red">Salah</font>'; 
					return false;
				}
			}

			function checkPass(pass, uname, email){
				if((pass != uname) && (pass != email)){
					document.getElementById('v_pass').innerHTML='<font color="green">Benar</font>';
					return true;					
				}else if(pass == uname){
					document.getElementById('v_pass').innerHTML='<font color="red">Password tidak boleh sama dengan username</font>'; 
					return false;
				}
				else if(pass == email){
					document.getElementById('v_pass').innerHTML='<font color="red">Password tidak boleh sama dengan email</font>'; 
					return false;
				}
				else{
					document.getElementById('v_pass').innerHTML='<font color="red">Password tidak boleh sama dengan username/email</font>'; 
					return false;
				}

		function validateLogin() {
			var flag = 0;
			var username = document.login.login_email.value;
			var password = document.login.login_pass.value;

			if (username == "admin" && password == "admin") {
				window.location="dashboard.html";
				flag = 1;
			}

			if (flag == 0) {
				alert("Invalid login!");
			}
		}

		function checkSubmit(e) {
		   if (e && e.keyCode == 13) {
			  validateLogin();
		   }
		}
