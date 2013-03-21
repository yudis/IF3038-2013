<?php include './includes/header.inc.php' ?>
                <h1>Rincian Tugas</h1>
                <div class="padding12px">
                    <div class="rincianLabel">Nama:</div><div class="rincianDetail"><input type="hidden" id="idTugas" name="idTugas" value="<?php echo $id; ?>" /><div id="namaTugas">Loading...</div></div>
                    <div class="rincianLabel">Status:</div><div class="rincianDetail"><span id="statusTugas">Loading...</span> <button class="button" onclick="return false;">Toggle</button></div>
                    <div class="rincianLabel">Attachment:</div><div class="rincianDetail" id="attachmentTugas">Loading...</div>
                    <div class="rincianLabel">Deadline:</div><div class="rincianDetail">
                        <div id="deadlineDisplayDiv" class="inlineblock">Loading...</div>
                        <div id="deadlineEditDiv">
                            <!-- <input type="date" id="deadline" name="deadline" value="2013-02-22" /> -->
                            <input type="text" id="deadline" name="deadline" placeholder="yyyy-mm-dd" /> <a href="#" onclick="NewCal('deadline', 'YYYYMMDD'); return false;"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
                        </div>
                    </div>
                    <div class="rincianLabel">Assignees:</div><div class="rincianDetail">
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
                            <ul id="tagsList" class="tag"></ul>
                        </div>
                        <div id="tagsEditDiv">
                            <input type="text" id="tags" name="tags" />
                        </div>
                    </div>
                    <br /><br />
                    <button id="editButton" onclick="return editTugas();">Edit</button><button id="doneButton" onclick="return saveTugas();">Done</button>
                    <br /><br /><br />
                    <div class="rincianLabel">Komentar:</div><div class="rincianDetail">
                        <div id="komentar"></div>
                        <textarea id="txtKomentar" rows="4" cols="80" placeholder="Your comment here..."></textarea><br /><button onclick="addComment(); return false;">Submit</button>
                    </div>
                </div>
                <br />
<?php include './includes/footer.inc.php' ?>