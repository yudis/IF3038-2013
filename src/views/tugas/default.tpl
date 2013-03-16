<?php include './includes/header.inc.php' ?>
                <h1>Rincian Tugas</h1>
                <div class="padding12px">
                    <form action="#">
                        <div class="rincianLabel">Nama:</div><div class="rincianDetail"><input type="hidden" id="idTugas" name="idTugas" value="<?php echo $id; ?>" /><div id="namaTugas"><?php echo $namaTugas ?></div></div>
                        <div class="rincianLabel">Attachment:</div><div class="rincianDetail"><div class="inlineblock"><a href="./files/test.zip" target="_blank">test.zip</a></div></div>
                        <div class="rincianLabel">Deadline:</div><div class="rincianDetail">
                            <div id="deadlineDisplayDiv" class="inlineblock"><?php echo $deadline; ?></div>
                            <div id="deadlineEditDiv">
                                <!-- <input type="date" id="deadline" name="deadline" value="2013-02-22" /> -->
                                <input type="text" id="deadline" name="deadline" placeholder="yyyy-mm-dd" value="<?php echo $deadline; ?>" /><a href="#" onclick="NewCal('deadline', 'YYYYMMDD'); return false;"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
                            </div>
                        </div>
                        <div class="rincianLabel">Assignee:</div><div class="rincianDetail">
                            <ul id="assigneesList" class="tag"></ul>
                            <div id="assigneeEditDiv">
                                <input type="text" id="assignee" name="assignee" list="user" />
                                <datalist id="user">
                                    <option value="Abraham Krisnanda Santoso">
                                    <option value="Edward Samuel Pasaribu">
                                    <option value="Stefanus Thobi Sinaga">
                                </datalist>
                                <button onclick="return addAssignees();">Add</button>
                            </div>
                        </div>
                        <div class="rincianLabel">Tags:</div><div class="rincianDetail">
                            <div id="tagsDisplayDiv">
                                <ul id="tagsList" class="tag">
									<?php 
										if ($tags):
										foreach ($tags as $t): 
									?>
										<li><?php echo $t; ?></li>
									<?php 
										endforeach;
										endif; 
									?>
                                </ul>
                            </div>
                            <div id="tagsEditDiv">
                                <input type="text" id="tags" name="tags" value="<?php echo $tagsEdit; ?>" />
                            </div>
                        </div>
                        <br /><br />
                        <button id="editButton" onclick="return editTugas();">Edit</button><button id="doneButton" onclick="return saveTugas();">Done</button>
                        <br /><br /><br />
                        <div class="rincianLabel">Komentar:</div><div class="rincianDetail">
                            <div id="komentar"><b>Abraham Krisnanda Santoso</b> - 20 Februari 2013 23:06<hr />Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer tortor nunc, posuere sit amet facilisis quis, ullamcorper nec sapien. Donec placerat nisi in dui auctor posuere. Etiam quis urna tortor. Mauris aliquet porta tellus, a tristique arcu congue vel. Ut scelerisque, lacus vel sodales tristique, neque leo facilisis metus, quis posuere dui eros vel sem. Aliquam turpis quam, vehicula quis malesuada vel, tincidunt sit amet nunc. Vivamus orci augue, volutpat sed egestas eget, luctus eget lacus. Curabitur ut quam non dolor pellentesque dapibus. Quisque ut semper ante. Donec lobortis rutrum elit, eget consequat leo ultrices non. Nam mauris magna, fermentum vel pulvinar ac, varius condimentum nulla. Maecenas ac odio non ligula vestibulum congue. Ut nunc risus, vestibulum sit amet consectetur molestie, porttitor nec.<br /><br /><b>Stefanus Thobi Sinaga</b> - 20 Februari 2013 23:06<hr />Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer tortor nunc, posuere sit amet facilisis quis, ullamcorper nec sapien. Donec placerat nisi in dui auctor posuere. Etiam quis urna tortor. Mauris aliquet porta tellus, a tristique arcu congue vel. Ut scelerisque, lacus vel sodales tristique, neque leo facilisis metus, quis posuere dui eros vel sem. Aliquam turpis quam, vehicula quis malesuada vel, tincidunt sit amet nunc. Vivamus orci augue, volutpat sed egestas eget, luctus eget lacus. Curabitur ut quam non dolor pellentesque dapibus. Quisque ut semper ante. Donec lobortis rutrum elit, eget consequat leo ultrices non. Nam mauris magna, fermentum vel pulvinar ac, varius condimentum nulla. Maecenas ac odio non ligula vestibulum congue. Ut nunc risus, vestibulum sit amet consectetur molestie, porttitor nec.<br /><br /><b>Edward Samuel Pasaribu</b> - 20 Februari 2013 23:06<hr />Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer tortor nunc, posuere sit amet facilisis quis, ullamcorper nec sapien. Donec placerat nisi in dui auctor posuere. Etiam quis urna tortor. Mauris aliquet porta tellus, a tristique arcu congue vel. Ut scelerisque, lacus vel sodales tristique, neque leo facilisis metus, quis posuere dui eros vel sem. Aliquam turpis quam, vehicula quis malesuada vel, tincidunt sit amet nunc. Vivamus orci augue, volutpat sed egestas eget, luctus eget lacus. Curabitur ut quam non dolor pellentesque dapibus. Quisque ut semper ante. Donec lobortis rutrum elit, eget consequat leo ultrices non. Nam mauris magna, fermentum vel pulvinar ac, varius condimentum nulla. Maecenas ac odio non ligula vestibulum congue. Ut nunc risus, vestibulum sit amet consectetur molestie, porttitor nec.<br /><br /></div><textarea id="txtKomentar" rows="4" cols="80" placeholder="Your comment here..."></textarea><br /><button onclick="return addKomentar();">Submit</button>
                        </div>
                    </form>
                </div>
                <br />
<?php include './includes/footer.inc.php' ?>