<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Todolist</title>
        <link rel="stylesheet" type="text/css" href="styles/default.css" />
        <link rel="stylesheet" type="text/css" href="styles/mediaqueries.css" />
        <script src="scripts/helper.js" type="application/javascript"></script>
        <script src="scripts/popup.js" type="application/javascript"></script>
        <script src="scripts/dashboard.js" type="application/javascript"></script>
        <script src="scripts/kategori.js" type="application/javascript"></script>
    </head>
    <body onload="updateAddButtonVisibility();">
        <div id="blanket"></div>
        <div id="popUpDiv">
            <h1>Create new category</h1>
            <div class="padding12px"><label for="txtNewKategori">Name</label>:<br />
                <input id="txtNewKategori" type="text" placeholder="eg: IF40XX" /></div>
            <br />
            <div class="padding12px">
                Priviledge users:<br />
                <ul class="tag">
                    <li>Abraham Krisnanda Santoso</li>
                    <li>Edward Samuel Pasaribu</li>
                    <li>Stefanus Thobi Sinaga</li>
                </ul>
            </div>
            <br />
            <div class="rightalign padding12px"><button onclick="popup('popUpDiv','blanket',300,600); NewKategori()">OK</button> <button onclick="popup('popUpDiv','blanket',300,600)">Cancel</button></div>
            <br />
        </div>
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
            <div class="content">
                <div class="sidebar">
				<ul id="Kategori" class="nav">     
						<li><a href="#"  onclick="loadtugas(''); return false;">All</button></a></li>
						<div id="nama_k"></div>
					</ul>
                    <ul class="nav">
                        <li><a href="#" onclick="popup('popUpDiv','blanket',300,600)">Tambah Kategori...</a></li>
                    </ul>
                </div>
                <div id="listTugas" class="main">
                    <h1 class="inlineblock">Dashboard</h1> <button id="addTask" onclick="NewTask()">add new task...</button>
                    <div id="tugasT" ></div>
                </div>
            </div>
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.<br />
                IF3094 - Pemrograman Internet.
            </footer>
        </div>
    </body> 
</html>
