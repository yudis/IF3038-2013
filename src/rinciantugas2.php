<?php include 'header.php';?>

<div id="main">
	<div id="konten">
		<div class="atas">
		</div>
		<div class="tengah">
			<div class="judul">
				Pemrograman Internet
			</div>
                                <div id ="dashboardimage"><img src="images/Prog_In.png" alt="dashboardimgprogin"/></div>
			<div class="isi">
			</div>
            
			<<div class="detail">
				<div class="byon">
					Posted by <strong><span class="by">Enjella</span></strong> on <strong>February 20, 2013</strong>
				</div>
				
				<div class="byon">
					Deadline : <input type="text" name="inputdeadline" />
				</div>
				
				<div class="byon">
					Assignee : <input type="text" name="inputasignee" list="user"/>
					<datalist id ="user" />
					<option value = "enjella" />
					<option value = "kevin" />
					<option value = "vincentius" />
					</datalist>
				</div>
				
                                            <div class="byon">
					Tag : <input type="text" name="inputtag" autocomplete="on"/>
				</div>
                                        
				<div class="likedislike">
					<ul class="ldbuttons">
						<li class="like" id="blike"><a href="javascript:like();void(0);"></a></li>
						<li class="dislike" id="bdislike"><a href="javascript:dislike();void(0);"></a></li>
					</ul>
				</div>
				<div class="count">
					<strong><span id="jmllike"></span></strong> likes
				</div>
                
                <div class="count"><input type="button" name="edit" onclick="editTask2()" value="Submit"/></div>
                
			</div><br><br><br><div class="videomode" align="center"> <br> Attachment : 
				<a href="images/Up.png" target="images/Up.png">Up.png</a><br><img src="images/gajah.png"></img><br><video width="320" height="240" controls src="images/movie.ogg"></video></div>
			<div class="komen">
				<div class="komenjudul">Comments</div>
				<div id="konten-commenter">
					<strong><span id="jmlkomen">3</span></strong> comments
				</div>
				<div class="line-konten"></div>
				<div id="lkomen">
					<div class="komen-nama">Vincentius Martin</div>
					<div class="komen-tgl">| February 20, 2013 at 12:51</div>
					<div class="komen-isi">Waaah...IMBA!!</div>
					<div class="line-konten"></div>
					<div class="komen-nama">Kevin Jangtjik</div>
					<div class="komen-tgl">| February 20, 2013 at 20:01</div>
					<div class="komen-isi">Kyaya...kyaaaa...</div>
					<div class="line-konten"></div>
					<div class="komen-nama">Enjella</div>
					<div class="komen-tgl">| February 20, 2013 at 23:59</div>
					<div class="komen-isi">Finally, it's work !</div>
					<div class="line-konten"></div>
				</div>
				<form action="" method="get">
					<div id="komen-tulis"><strong>Tulis Komentar</strong></div>
					<div class="clear"></div>
					<div class="komen-label btg-mrh" id="komen-btg">*) wajib diisi</div>
					<div class="clear"></div>
					<div class="komen-label">Nama <span class="btg-mrh">*</span></div><div class="register-td">:</div><div class="register-input"><input class="register-input-input" type="text" name="nama" id="form-nama" /></div>
					<div class="clear error" id="error-username"></div>
					<div class="komen-label">Komentar <span class="btg-mrh">*</span></div><div class="register-td">:</div><div class="register-input"><textarea name="komentar" rows="3" cols="50" id="form-komen"></textarea></div>
					<div class="clear"></div>
					<div class="komen-submit"><input type="button" name="submit" onclick="commenting()" value="Submit" /></div>
				</form>
			</div>
		</div>
		<div class="bawah">
		</div>
	</div>
	</div>

<?php include 'footer.php';?>