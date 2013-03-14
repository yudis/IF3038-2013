<?php include './includes/header.inc.php' ?>
<h1>Buat Tugas Baru</h1>
<div class="formtugas">
<form onsubmit="return validate (this);" name="form">
	<ul class="item">
		<li id="folil1">
			<label id="title1">Nama task:</label>
			<div>
				<input id="namatask" name="namatask" type="text" maxlength="25" tabindex="1" pattern="[A-Za-z0-9 ]{1,25}"
					   title="Nama task tidak diperbolehkan menggunakan karakter spesial"/>
			</div>
		</li>

		<li id="folil2">
			<label id="title2">Attachment:</label>
			<div>
				<input id="attachment" name="attachment" type="file" tabindex="2" accept="application/pdf,application/msword,image/*"/>
			</div>
		</li>

		<li id="folil3">
			<label id="title3">Deadline:</label>
			<div>
				<input id="deadline" name="deadline" type="date" tabindex="3"/>
			</div>
		</li>

		<li id="folil4">
			<label id="title4">Assignee:</label>
			<div>
				<input id="assignee" name="assignee" type="text" tabindex="4" pattern="[A-Za-z0-9 ]{1,}"  list="user" />
				<datalist id="user">
					<option value="Abraham Krisnanda Santoso">
					<option value="Edward Samuel Pasaribu">
					<option value="Stefanus Thobi Sinaga">
				</datalist>
			</div>
		</li>

		<li id="folil5">
			<label id="title5">Tag:</label>
			<div>
				<input id="tag" name="tag" type="text" tabindex="5" pattern="[A-Za-z0-9 ]{1,}"/>
			</div>
		</li>

		<li id="btn">
			<input type="submit" value="Submit" class="button"/>
		</li>
	</ul>
</form>                
</div>
<?php include './includes/footer.inc.php' ?>