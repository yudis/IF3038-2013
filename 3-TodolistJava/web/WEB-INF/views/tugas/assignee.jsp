<jsp:include page="/WEB-INF/includes/header.inc.jsp" />
                <h1>Rincian Tugas</h1>
                <div class="padding12px">
                    <h2>Detail</h2>
                    <div class="rincianLabel">Nama:</div><div class="rincianDetail"><span id="namaTugas">Loading...</span></div>
                    <div class="rincianLabel">Status:</div><div class="rincianDetail"><input type="checkbox" id="cbStatus" onclick="toggleStatus();"/> <span id="statusTugas">Loading...</span></div>
                    <div class="rincianLabel">Deadline:</div><div class="rincianDetail">
                        <div id="deadlineDisplayDiv" class="inlineblock">Loading...</div>
                        <div id="deadlineEditDiv">
                            <!-- <input type="date" id="deadline" name="deadline" value="2013-02-22" /> -->
                            <input type="text" id="deadline" name="deadline" placeholder="yyyy-mm-dd" /> <a href="#" onclick="NewCssCal('deadline', 'yyyyMMdd'); return false;"><img src="./images/cal/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
                        </div>
                    </div>
                    <div class="rincianLabel">Assignees:</div><div class="rincianDetail">
                        <ul id="assigneesList" class="tag"></ul>
                        <div id="assigneeEditDiv">
                            <input type="text" id="assignee" name="assignee" autocomplete="off" onkeyup="suggestAssignees();" list="assigneesSug" /><datalist id="assigneesSug"></datalist>
                            <button onclick="return addAssignee();">Add</button>
                        </div>
                    </div>
                    <div class="rincianLabel">Tags:</div><div class="rincianDetail">
                        <div id="tagsDisplayDiv">
                            <ul id="tagsList" class="tag"></ul> <span id="tagsEditDiv"><input type="text" id="tags" name="tags" autocomplete="off" onkeyup="return tagsSave(this);" list="tagsSug" /><datalist id="tagsSug"></datalist></span>
                        </div>
                    </div>
                    <br /><br />
                    <button id="editButton" onclick="return editTugas();">Edit</button> <button id="doneButton" onclick="saveTugas(); return false;">Done</button>
                    <br />
                    <h2>Attachment</h2>
                    <div id="attachmentTugas">Loading...</div>
                    <br />
                    <h2>Komentar</h2>
                    <div>
                        <div id="komentar"></div>
                        <br />
                        <div id="komentarControl" class="padding12px">Seen/Total: <strong id="komentarStatistic">Loading...</strong> | Page: <select id="komentarPage" onchange="changeKomentarPage(this);"></select></div>
                        <br />
                        <textarea id="txtKomentar" placeholder="Your comment here..." rows="8"></textarea><br /><button onclick="addComment(); return false;">Submit</button>
                    </div>
                </div>
                <br />
<jsp:include page="/WEB-INF/includes/footer.inc.jsp" />