<?php
	$session_time = 30*24*60*60;
	ini_set('session.gc-maxlifetime', $session_time);

	session_start();
	if ((!ISSET($_SESSION['base_url'])) || (!ISSET($_SESSION['full_url'])) || (!ISSET($_SESSION['full_path'])))
	{
		$folder = substr($_SERVER['SCRIPT_NAME'], 0, strrpos($_SERVER['SCRIPT_NAME'], "/")+1);  
		$protocol = (ISSET($_SERVER['HTTPS']) && $_SERVER['HTTPS'] != "off") ? "https" : "http";
		$_SESSION['base_url'] = $folder;
		$_SESSION['full_url'] = $protocol . "://" . $_SERVER['HTTP_HOST'] . $folder;
		$_SESSION['full_path'] = $_SERVER['DOCUMENT_ROOT'].$folder;
	}
?>
<?php include $_SESSION['full_path']."template/is_login.php"; ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="Description" content="" />
		<link rel="shortcut icon" type="image/x-icon" href="<?php echo $_SESSION['base_url']; ?>images/favicon.ico" />
		<title>MOA - Dashboard</title>
		<link rel="stylesheet" href="<?php echo $_SESSION['base_url']; ?>css/style.css" />
		<link rel="stylesheet" href="<?php echo $_SESSION['base_url']; ?>css/dashboard.css" />
	</head>
	<body>
		<?php
			$menu = array();
			$menu["Dashboard"] =  array("href" => "dashboard.php", "class" => "active");
			$menu["Profil"] = array("href" => "profil.php");
		?>
		<?php include $_SESSION['full_path']."template/header.php";?>
		<section>
			<div id="content_wrap" class="wrap">
                <div id="dashboard_category">
                    <nav>
                        <ul id="categories">
                            <li class="dashboard_category_element_aktif"> <a href="javascript:check(1);">Semua Kategori</a></li>
                        </ul>
                    </nav>
					<div id="new_category" class="dashboard_category_element">
						<a href="javascript:new_category();">Tambah Kategori Baru</a>
					</div>
                </div>
                <div id="dashboard_tugas">
                    <nav>
                        <ul id="tasks">                           
                        </ul>       
                    </nav>
					<a id="new_task_link" href="newwork.html">
						<div class="dashboard_tugas_element">
							<div class="dashboard_tugas_judul">New Task</div>
						</div>
					</a>
                </div>
			</div>
		</section>
		<?php
			$breadcrumbs = array();
			$breadcrumbs["Dashboard"] = array("href" => "dashboard.php", "class" => "active");
		?>
		<?php include $_SESSION['full_path']."template/footer.php";?>
		
		<div id="black_trans">
			<div id="frame_new_category">
				<div id="close_button">
					<a href="javascript:close();">X</a>
				</div>
				<div id="new_category_area">
					<div id="new_category_form_wrap">
						<div id="new_category_head">
							<h2>Tambah Kategori Baru</h2>
						</div>
						<form id="new_category_form">
							<div class="row">
								<label for="new_category_name">Nama Kategori</label>
								<input id="new_category_name" name="new_category_name" type="text" title="Nama kategori harus diisi." required />
							</div>
							<div class="row">
								<label for="new_category_username">Username</label>
								<input id="new_category_username" name="new_category_username" pattern="^[^;]{5,}(;[^;]{5,})*$" type="text" title="Username harus terdaftar dan dipisahkan tanda titik-koma. Kosongkan jika private." />
							</div>
							<input id="new_category_submit" type="submit" value="Tambah" disabled="disabled" title="Semua elemen form harus diisi dengan benar dahulu."/>
						</form>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript" src="<?php echo $_SESSION['base_url']; ?>js/search.js"></script>
		<script type="text/javascript" src="<?php echo $_SESSION['base_url']; ?>js/logout.js"></script>
		<script type="text/javascript">
            function check(n)
            {
                var categories = document.getElementById("categories");               
				var count = 0;
				var j = 0;
				
				while (j<categories.childNodes.length)
				{
					if (categories.childNodes[j].nodeName!="#text")
					{
						count++;
						if (count==n)
							categories.childNodes[j].className = "dashboard_category_element_aktif";
						else
							categories.childNodes[j].className = "dashboard_category_element";
					}
					j++;
				}
				
				var tasks = document.getElementById("tasks");
				var listtask = JSON.parse(localStorage.MOA_taskList);
				tasks.innerHTML = "";
				for (i=0;i<listtask.length;++i)
				{
					if ((n==1)||((n-2)==listtask[i].category))
					{
						var tempstring = "";
						tempstring += "<li>";
							tempstring += "<a id='task_link' href='tugas.html?taskId="+i+"'>";
								tempstring += '<div class="dashboard_tugas_element">';
									tempstring += '<div class="dashboard_tugas_judul">'+listtask[i].name+'</div>';
									tempstring += '<div class="dashboard_tugas_tanggal">Deadline: '+listtask[i].deadline+'</div>';
									tempstring += '<div class="dashboard_tugas_tag_area">';
										tempstring += "<ul>";										
											var tags = listtask[i].tag;
											for (j=0;j<tags.length;++j)
											{
												tempstring += "<li>";
												tempstring += tags[j];
												tempstring += "</li>";
											}
										tempstring += "</ul>";
									tempstring += "</div>";
								tempstring += "</div>";	
							tempstring += "</a>";			
						tempstring += "</li>";		
						tasks.innerHTML += tempstring;
					}
				}
				
				var new_task_link = document.getElementById("new_task_link");
				new_task_link.href = "newwork.html";
				if (n>1)
					new_task_link.href += "?category="+(n-2);
				
		   }
            
            /*Bagian menampilkan pembuatan new_category dan new_task*/
			function new_category()
			{
				document.getElementById("black_trans").className = "appear";
				document.getElementById("new_category_area").className = "appear";
				document.getElementById("close_button").className = "appear";
				setTimeout(function()
				{
					document.getElementById("frame_new_category").className = "frame_new_category_enter";
				}, 100);
			}
			
			function close()
			{
				document.getElementById("frame_new_category").className = "";
				setTimeout(function()
				{
					document.getElementById("black_trans").className = "";
					document.getElementById("new_category_area").className = "";
					document.getElementById("close_button").className = "";
				}, 400);
			}
            
            /*--- Bagian Validasi Pembuatan Kategori Baru ---*/
			var new_category_form = document.getElementById("new_category_form");
			new_category_form.onsubmit = function()
			{
				if (new_category_name.checkValidity() && new_category_username.checkValidity() && 
					check_username(new_category_username.value) && check_category(this.value))
				{
					var listcategory = JSON.parse(localStorage.MOA_categoryList);
					var userlist = JSON.parse(localStorage.MOA_userList);
					var check = true;
					var users = new Array();
					if (new_category_username.value!="")
					{
						var names = new_category_username.value.split(";");
						for (i=0;i<names.length;++i)
						{
							if (names[i]==userlist[sessionStorage.MOA_userId].userName)
								check = false;
							if (names[i]!='')
								users[i] = names[i];
						}
					}
					if (check)
					{
						users[users.length] = userlist[sessionStorage.MOA_userId].userName;
					}
					
					var category = new Object();
					category.users = users;
					category.name = new_category_name.value;
					listcategory[listcategory.length] = category;
					
					localStorage.MOA_categoryList = JSON.stringify(listcategory);
					
					append_category(category.name);					
					close();
					new_category_form.reset();
					new_category_name.backgroundImage = "";
					new_category_username.backgroundImage = "";
				}
				else
				{
					alert("Ada kesalahan pada data yang dimasukkan");
				}
				return false;
			}
			
			var new_category_name = document.getElementById("new_category_name");
			var new_category_username = document.getElementById("new_category_username");
			
			new_category_name.onkeyup = function()
			{
				if ((this.checkValidity())&&(check_category(this.value)))
					this.style.backgroundImage = "url('images/valid.png')";
				else
					this.style.backgroundImage = "url('images/warning.png')";
				check_submit_category();
			}
						
			new_category_username.onkeyup = function()
			{
				if ((this.checkValidity())&&(check_username(this.value)))
					this.style.backgroundImage = "url('images/valid.png')";
				else
					this.style.backgroundImage = "url('images/warning.png')";
				check_submit_category();
			}
			
			function check_category(categoryname)
			{
				var categorylist = JSON.parse(localStorage.MOA_categoryList);
				var check = true;
				var i = 0;
				while ((check)&&(i<categorylist.length))
				{
					if (categoryname==categorylist[i].name)
						check = false;
					i++;
				}
				return check;
			}
			
			function check_username(username)
			{
				if (username!="")
				{
					var userlist = JSON.parse(localStorage.MOA_userList);
					var names = username.split(";");
					var check1 = true;
					var i=0;
					while ((check1)&&(i<names.length))
					{
						if (names[i]!='')
						{
							var check2 = true;
							var j=0;
							while ((check2)&&(j<userlist.length))
							{
								if (names[i]==userlist[j].userName)
									check2 = false;
								j++;
							}
							if (check2)
								check1 = false;
						}
						i++;
					}
					return check1;
				}
				return true;
			}
						
			function check_submit_category()
			{
				if (new_category_name.checkValidity() && new_category_username.checkValidity() && 
					check_username(new_category_username.value) && check_category(this.value))
				{
					document.getElementById("new_category_submit").disabled="";
				}
				else
				{
					document.getElementById("new_category_submit").disabled="disabled";
				}
			}
		</script>
		<script>
			function append_category(category_name)
			{
				var categories = document.getElementById("categories");
				var count = 1;
				for (j=0;j<categories.childNodes.length;++j)
					if (categories.childNodes[j].nodeName!="#text")
						count++;
				categories.innerHTML += "<li class='dashboard_category_element'><a href='javascript:check("+count+");'>"+category_name+"</a></li>";
			}
			window.onload = function()
			{
				var categories = document.getElementById("categories");
				var listcategory = JSON.parse(localStorage.MOA_categoryList);
				var userlist = JSON.parse(localStorage.MOA_userList);
				
				for (i=0;i<listcategory.length;++i)
				{
					var j = 0;
					var check = true;
					while ((check)&&(j<listcategory[i].users.length))
					{
						if (listcategory[i].users[j] == userlist[sessionStorage.MOA_userId].userName)
							check = false;
						++j;
					}
					if (!check)
						append_category(listcategory[i].name);
				}
				
				var tasks = document.getElementById("tasks");
				var listtask = JSON.parse(localStorage.MOA_taskList);
				for (i=0;i<listtask.length;++i)
				{
					var tempstring = "";
					tempstring += "<li>";
						tempstring += "<a id='task_link' href='tugas.html?taskId="+i+"'>";
							tempstring += '<div class="dashboard_tugas_element">';
								tempstring += '<div class="dashboard_tugas_judul">'+listtask[i].name+'</div>';
								tempstring += '<div class="dashboard_tugas_tanggal">Deadline: '+listtask[i].deadline+'</div>';
								tempstring += '<div class="dashboard_tugas_tag_area">';
									tempstring += "<ul>";										
										var tags = listtask[i].tag;
										for (j=0;j<tags.length;++j)
										{
											tempstring += "<li>";
											tempstring += tags[j];
											tempstring += "</li>";
										}
									tempstring += "</ul>";
								tempstring += "</div>";
							tempstring += "</div>";	
						tempstring += "</a>";			
					tempstring += "</li>";		
					tasks.innerHTML += tempstring;
				}
			}
		</script>
	</body>
</html>