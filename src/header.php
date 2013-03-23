<head >			<!------memanggil showUserLogin saat load body-------->	

        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/dash.css">
        <script type="text/javascript" src="js/popup.js"></script>
        <script>

/*            var itotal=5;
            var ipartin=0;
            var ipartout=0;
            var itulis=0;*/
            
			
			function showUserLogin(){							/*-----------menampilkan user pada local storage-------------- */
				var waktuLogin = Math.round(localStorage.tglLogin/(1000*60*60*24)); 
				var waktuSkrg = Math.round(new Date().getTime()/(1000*60*60*24));
				var selisihHari = waktuSkrg-waktuLogin;	 	//new Date().getTime() akan menampilkan selisih waktu milisecond antara midnight 1 januari 1970 dengan saat method dipanggil 
//				alert(selisihHari);				
				
				if(selisihHari<30){							
					document.getElementById("showLoginHeader").innerHTML="Welcome "+localStorage.userLogin;	
														
				}else{										//jika sudah 30 hari, user harus login lagi
					window.location="index.php";	
				}
			}
			
			function hapusUserLogin(){							/*-----------menghapus user dan waktu pada local storage-------*/
				localStorage.clear();
			}
/*			
            function taskawal(itotal){
                for(var i=0;i<itotal;i++){
                var para=document.createElement("p");
                if(i===0){
                    var node=document.createTextNode("TUBES 1" + " " + (11+(i*4)) + " AGUSTUS2013"  +" KAP");   
                }else if(i===1){
                    var node=document.createTextNode("TUBES 2" + " " + ((i*6)-1) + "OKTOBER2013"  +" MSDI");   
                }else if(i===2){
                    var node=document.createTextNode("TUBES 3" + " " + ((i*6)-1) + "OKTOBER2013"  +" MSDI");   
                }else if(i===3){
                    var node=document.createTextNode("TUBES 4" + " " + ((i*6)-1) + "OKTOBER2013"  +" MSDI");   
                }else if(i===4){
                    var node=document.createTextNode("TUBES 5" + " " + ((i*4)-4) + "APRIL2013"  +" PROGIN" );   
                }else if(i===5){
                    var node=document.createTextNode("TUBES 6" + " " + ((i*4)-4) + "APRIL2013"  +" PROGIN" );   
                }
                para.appendChild(node);
                para.id="listtask";
                
                var element=document.getElementById("div1");
                element.appendChild(para);
                ipartout = itotal;
                }
            }
            function addTask(ipartin){                
                for(var i=0;i<ipartin;i++){
                var para=document.createElement("p");
                if(ipartin === 1){
                    var node=document.createTextNode("TUBES " + (i+1) + " " + (11+(i*4)) + "AGUSTUS2013"  +" KAP");
                } else if(ipartin === 2){
                    var node=document.createTextNode("TUBES " + (i+5) + " " + (12+(i*4)) + "APRIL2013"  +" PROGIN" );
                } else if(ipartin === 3){    
                    var node=document.createTextNode("TUBES " + (i+2) + " " + (5+(i*6)) + "OKTOBER2013"  +" MSDI");
                }
                
                para.appendChild(node);
                para.id="listtask";
                
                var element=document.getElementById("div1");
                element.appendChild(para);
                }
                
                ipartout=ipartin;
                itulis=ipartin;
            }
            
            function removeTask(){
                for(var i=0;i<ipartout;i++){
                var parent=document.getElementById("div1");
                var child=document.getElementById("listtask");
                parent.removeChild(child);
                }
            }
            
            
            function showTask(){
                document.getElementById("addtask").style.visibility="visible";
            }
            
            function hideAddTask(){
                document.getElementById("addtask").style.visibility="hidden";
            }
*/			
			
<!------------------------------------------------ buat search------------------------------------------>			
	    function tampilSearch(){
//			if(	window.location==window.location.href.substring(0,window.location.href.lastIndexOf("/")) + "/searchresult.php"){							
			    var xyz = document.getElementById("cari").value;
				var xyz2 = document.getElementById("searchFilter").value;
				if (window.XMLHttpRequest){
					xmlhttp = new XMLHttpRequest();				
				}else{
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
				}

				xmlhttp.onreadystatechange = function(){
					if (xmlhttp.readyState==4 && xmlhttp.status==200)	{				

						document.getElementById("divTugas").innerHTML=xmlhttp.responseText;
					}
				}
					
				xmlhttp.open("GET","search.php?tugas="+xyz+"&filter="+xyz2,true);
				xmlhttp.send();
//			}//end if searchresult.php
//			else{

//				window.location="searchresult.php";
				
//			}// end else
		}
			
        </script>			
            
        </script>
                     <div class="header">
			<div id="logo">
			    <a hreg="dashboard.php">
			    <img src="pict/logo.png">
			    </a>
			</div>
			<div id="border">
			    
			</div>
			<div id="dashboard">
			    <a href="dashboard.php">DASHBOARD</a>
			</div>
			<div id="profile">
			    <a href="profile.php">PROFILE</a>
			</div>
			<div id="search">
            
	            	<form action="searchresult.php" method="post">
					    <section class="searchform cf">
						    <input id="cari" class="searchbox" type="search" name="search" placeholder="Search.." required>						
					    </section>
                        
                            <select name="searchFilter" class="dropdownJo" id="searchFilter"> 	<!-------dropdown filter-->
                            <option value="semua">Semua</option>
                            <option value="username">Username</option>
                            <option value="email">Email</option>
                            <option value="namalengkap">Nama Lengkap</option>
                            <option value="birthdate">Birthdate</option>
                            <option value="kategori">Kategori</option>
                            <option value="tugas">Tugas</option>
                            <option value="tag">Tag</option>                            
                            <option value="komentar">Komentar</option>                            
                            </select>




                        <input type="submit" class="searchbuttonbox cf" >
                        
                    </form>
				</div>	<!--end div search-->
			<div id="showLoginHeader">

			</div>	
            
            <div id="showAvatarHeader">

			</div>	
            				    
            
            	<div id="logout" onClick="hapusUserLogin()">		<!---------hapus user login dan waktunya ketika logout ---------->
			    <a href="index.php">LOGOUT</a>

                
				    </div>
                    

		
		  </div>


        
    </head>


