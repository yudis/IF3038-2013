
<jsp:include page="/WEB-INF/includes/header.inc.jsp" />
<div id="blanket"></div><div id="popUpDiv"><h1>Create new category</h1><div class="padding12px"><label for="txtNewKategori">Name</label>:<br /><input id="txtNewKategori" type="text" placeholder="eg: IF40XX" /></div><br /><div class="padding12px">Priviledge users:<br /><ul id="userList" class="tag"></ul><br><input id="userL" name="userL" onfocus="showCoordinator()" type="text" tabindex="4" list="user" /><datalist id="user" ></datalist><button onclick="return addCoordinator();">Add</button></div><br /><div class="rightalign padding12px"><button onclick="popup('popUpDiv', 'blanket', 300, 600);
        NewKategori();">OK</button> <button onclick="popup('popUpDiv', 'blanket', 300, 600);">Cancel</button></div><br /></div>
<div class="sidebar">
    <ul id="Kategori" class="nav">     
        <li><a href="#"  onclick="loadtugas('');
        setChosen(0);
        return false;">All</button></a></li>
        <div id="nama_k"></div>
    </ul>
    <ul class="nav">
        <li><a href="#" onclick="popup('popUpDiv', 'blanket', 300, 600)">Tambah Kategori...</a></li>
    </ul>
</div>
<div id="listTugas" class="main">
    <h1 class="inlineblock">Dashboard</h1> <button id="addTask" onclick="NewTask()">add new task...</button> <button id="deleteCat" onclick="deleteCategory()">Delete Category</button>
    <div id="tugasT" ></div>
</div>
<jsp:include page="/WEB-INF/includes/footer.inc.jsp" />
