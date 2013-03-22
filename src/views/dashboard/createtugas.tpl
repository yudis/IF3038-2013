<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Todolist: Create Tugas</title>
        <link rel="stylesheet" type="text/css" href="styles/default.css" />
        <link rel="stylesheet" type="text/css" href="styles/mediaqueries.css" />
        <script src="scripts/formtugas.js" type="application/javascript"></script>
        <script src="scripts/tugas.js" type="application/javascript"></script>
        <script src="scripts/createtugas.js" type="application/javascript"></script>
		
    </head>
    <body onload="showKategori()">
        <div class="page">
            <header class="content">
                <nav>
                    <div class="logo"><a href="dashboard.html"><img alt="Home" src="images/logo.png" /></a></div>
                    <ul>
                        <li><div><a href="dashboard.html">Dashboard</a></div></li><li><div><a href="profile.html">Profile</a></div></li><li><div><a href="index.html">Logout</a></div></li>
                    </ul>
                    <div class="search">
                        <div id="searchwrapper">
                            <form action="#">
                                <input type="text" class="searchbox" name="q" value="" placeholder="Enter task name here.." />
                                <input type="image" src="images/search.png" name="sumbit" class="searchbox_submit" alt="search..."/>
                            </form>
                        </div>
                    </div>
                </nav>
            </header>
            <div class ="content">
                <h1>Buat Tugas Baru</h1>
                <div class="formtugas">
                    <form name="formTugas1"  method="post" enctype="multipart/form-data" action="create.php">
                        <ul class="item">
							<li id="folil0">
                                <div>
                                    <input id="namakategori" name="namakategori" type="text" maxlength="25" tabindex="1" required
                                           title="pilih salah satu kategori" value="<?php echo $_GET["id_kat"]?>" hidden>
                                </div>
                            </li>
							
                            <li id="folil1">
                                <label id="title1">Nama task:</label>
                                <div>
                                    <input id="namatask" name="namatask" type="text" maxlength="25" tabindex="1" pattern="[A-Za-z0-9 ]{2,25}" required 
                                           title="Nama task tidak diperbolehkan menggunakan karakter spesial"/>
                                </div>
                            </li>

                            <li id="folil2">
                                <label id="title2">Attachment:</label>
                                <div>
                                    <input id="attachment" name="attachments[]" type="file" tabindex="2" accept="application/pdf,application/msword,image/*" multiple />
                                </div>
                            </li>

                            <li id="folil3">
                                <label id="title3">Deadline:</label>
                                <div>
                                    <input id="deadline" name="deadline" type="date" tabindex="3" required/>
                                </div>
                            </li>

                            <li id="folil4">
                                <label id="title4">Assignee:</label>
                                <div>
									<ul id="assigneesList" class="tag"></ul>
									<br>
                                    <input id="assignee" name="assignee" onfocus="showAssignee()" type="text" tabindex="4" list="user" />
                                    
                                    <datalist id="user">
                                    </datalist>
									<button onclick="addAssignees(); return false">Add</button>
									<input id="assigneeI" name="assigneeI" type="text" value="" tabindex="4" hidden />
								</div>
								
                            </li>

                            <li id="folil5">
                                <label id="title5">Tag:</label>
                                <div>
                                    <input id="tag" name="tag" type="text" tabindex="5" pattern="[A-Za-z0-9, ]{1,}"/>
                                </div>
                            </li>

                            <li id="btn">
                                <input type="submit" value="Submit" class="button"/>
                            </li>
                        </ul>
                    </form>
                </div>
            </div>
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.<br />
                IF3094 - Pemrograman Internet.
            </footer>
        </div>
    </body>
</html>