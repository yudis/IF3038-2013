<?php include './includes/header.inc.php' ?>
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
									<button type="button" onclick="addAssignees(); return false">Add</button>
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
            <?php include './includes/footer.inc.php' ?>