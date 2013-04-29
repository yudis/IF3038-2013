<jsp:include page="/WEB-INF/includes/header.inc.jsp" />
                <h1>Buat Tugas Baru</h1>
                
                <div class="formtugas">
                    <form name="formTugas1" method="post" enctype="multipart/form-data" action="Create">
                        <ul class="item">
							<li id="folil0">
                                <div>
                                    <input id="namakategori" name="namakategori" type="text" maxlength="25" tabindex="1" required
                                           title="pilih salah satu kategori" value="<%= request.getParameter("id_kat")%>" hidden>
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
                                    <input id="attachment" name="attachments" type="file" tabindex="2" accept="application/pdf,application/msword,image/*" multiple="multiple" />
                                </div>
                            </li>

                            <li id="folil3">
                                <label id="title3">Deadline:</label>
                                <div>
								    <input type="text" class="regbox" id="deadline" name="deadline" required="required" /><a href="#" onclick="NewCssCal('deadline', 'yyyyMMdd', 'dropdown', false, 24, false); return false;"><img src="./images/cal/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
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
                                <input type="submit" class="button" />
                            </li>
                        </ul>
                    </form>
                </div>
<jsp:include page="/WEB-INF/includes/footer.inc.jsp" />
