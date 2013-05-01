<?php include './includes/header.inc.php' ?>
                <div class="sidebar">
				<ul id="Kategori" class="nav">     
						<li><a href="#"  onclick="loadtugas('');setChosen(0); return false;">All</button></a></li>
						<div id="nama_k"></div>
					</ul>
                    <ul class="nav">
                        <li><a href="#" onclick="popup('popUpDiv','blanket',300,600)">Tambah Kategori...</a></li>
                    </ul>
                </div>
                <div id="listTugas" class="main">
                    <h1 class="inlineblock">Dashboard</h1> <button id="addTask" onclick="NewTask()">add new task...</button> <button id="deleteCat" onclick="deleteCategory()">Delete Category</button>
                    <div id="tugasT" ></div>
                </div>
            <?php include './includes/footer.inc.php' ?>