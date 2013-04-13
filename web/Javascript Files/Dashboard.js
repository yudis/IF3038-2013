window.onload = InitDashboard();

var myVar = setInterval(function() {
    InitDashboard();
}, 1000);

function InitDashboard() {
    ShowCategory();
    showTaskList(0);
    console.log("ajax-reloaded");
}

/* ================= RELATED TO CREATING TASK ================= */

function showBuat() {
    document.getElementById("popoutovl").style.display = "block";
    document.getElementById("add2").style.display = "block";

    //document.getElementById("listwall").style.display = "none";
    document.getElementById("rincitugas").style.display = "none";
    document.getElementById("edittugas").style.display = "none";
    document.getElementById("buattugas").style.display = "block";
    document.getElementById("wanted").style.display = "block";
}

function createTask() {
    var regex = /^[a-zA-Z0-9]{5,25}$/;

    if ((regex.test(document.getElementById("namaTask").value))) {
        var k = document.getElementById("listwall");
        k.innerHTML = "<a class='listTugas' onclick='showRinci();'><a class='listTugas' onclick='showRinci();'></a> <a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a onclick='showBuat()' class='addTask'></a>";
        document.getElementById("namaTask").value = 0;
        showList();
    } else {
        alert("task name must be 5-25 long");
    }
}

/* ================= RELATED TO CATEGORY MANIPULATION ================= */
function ShowCategory() {
    var xmlhttp;
    var url = "BangServlet?action=showCategory";
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            document.getElementById("category").innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function addCategory() {
    //var overlay = document.createElement("div");
    //overlay.setAttribute("id", "overlay");
    //overlay.setAttribute("class", "overlay");
    //document.body.appendChild(overlay);

    document.getElementById('popoutovl').style.display = 'block';
    document.getElementById('popoutbg').style.display = 'block';
}

function addCat() {
    console.log("Adding Category");
    var name = document.getElementById("newCategoryName").value;
    var authUsers = document.getElementById("authUsers").value;
    var xmlhttp;
    var url = "BangServlet?action=addCategory&name=" + name + "&authUsers=" +
            authUsers;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            restore();
            ShowCategory();
            showTaskList(0);
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send(null);
    return false;
}

function restore() {
    //document.body.removeChild(document.getElementById("overlay"));
    document.getElementById('popoutovl').style.display = 'none';
    document.getElementById('popoutbg').style.display = 'none';
    //document.getElementById('overlay').style.display = 'none';
    //return false;
}

function restore2() {
    document.getElementById('popoutovl').style.display = 'none';
    document.getElementById('rincitugas').style.display = 'none';
}

function restore3() {
    document.getElementById('popoutovl').style.display = 'none';
    document.getElementById('add2').style.display = 'none';
}
function restore4() {
    document.getElementById('popoutovl').style.display = 'none';
    document.getElementById('edittugas').style.display = 'none';
    document.getElementById('rincitugas').style.display = 'block';
}


function removeCategory(code) {
    if (confirm("Are you sure you want to delete this category?")) {
        var xmlhttp;
        var url = "BangServlet?action=removeCategory&code=" + code;
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                document.getElementById("category").innerHTML = xmlhttp.responseText;
            }
        };
        xmlhttp.open("POST", url, true);
        xmlhttp.send();
        showTaskList(0);
        ShowCategory();
    }
}

/* ================= RELATED TO  TASK DISPLAY ================= */
function showTaskList(code) {
    var xmlhttp;
    var url = "BangServlet?action=showTaskList&code=" + code;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            document.getElementById("listwall").innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    document.getElementById("listwall").style.display = "block";
    document.getElementById("rincitugas").style.display = "none";
    document.getElementById("edittugas").style.display = "none";
    document.getElementById("buattugas").style.display = "none";
    document.getElementById("wanted").style.display = "none";
    document.getElementById('add').style.display = 'none';
}

function showRinci(code) {
    document.getElementById('popoutovl').style.display = "block";

    var xmlhttp;
    var url = "BangServlet?action=showTaskDetail&code=" + code;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            document.getElementById("rincitugas").innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    //document.getElementById("listwall").style.display = "none";
    document.getElementById("rincitugas").style.display = "block";
    document.getElementById("edittugas").style.display = "none";
    document.getElementById("buattugas").style.display = "none";
    //document.getElementById("wanted").style.display = "block";
    self.focus();
}

function editTaskDetail(code) {
    var xmlhttp;
    var url = "BangServlet?action=editTaskDetail&code=" + code;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            document.getElementById("edittugas").innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();

    //document.getElementById("listwall").style.display = "none";
    document.getElementById("rincitugas").style.display = "none";
    document.getElementById("edittugas").style.display = "block";
    document.getElementById("buattugas").style.display = "none";
    //document.getElementById("wanted").style.display = "block";
}

/* ================= RELATED TO TASK MANIPULATION ================= */
function changeTaskStatus(code, chk) {
    var xmlhttp;
    chk = (chk === true ? "1" : "0");
    var url = "BangServlet?action=changeTaskStatus&code=" +
            code + "&chkYesNo=" + chk;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            document.getElementById("statusTask" + code).innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function saveTaskDetail(code) {
    var newDeadline = document.getElementById('editDeadline').value;
    var newAssignees = document.getElementById('editAssignee').value;
    var newTags = document.getElementById('editTag').value;
    var xmlhttp;
    var url = "BangServlet?action=saveTaskDetail&code=" + code +
            "&newDeadline=" + newDeadline + "&newAssignees=" + newAssignees + "&newTags=" +
            newTags;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            showRinci(code);
        }
    };
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}

function removeTask(origin, code) {
    if (confirm("Are you sure you want to delete this task?")) {
        console.log("code" + code);
        var xmlhttp;
        var url = "BangServlet?action=removeTask&code=" + code;
        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                restore4();
                showTaskList(origin);
            }
        };
        xmlhttp.open("POST", url, true);
        xmlhttp.send();
    }
}

/* ================= RELATED TO COMMENTS ================= */
function postComment(taskId) {
    var xmlHttp = new XMLHttpRequest();
    var content = document.getElementById('ccontent').value;
    var url = "BangServlet?action=addComment&content=" + content +
            "&taskId=" + taskId;
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var queryresult = xmlHttp.responseText;
            var pieces = queryresult.split(';');
            var parentTag = document.getElementById('comment_nonform'); //ini harusnya getelement id si task nya
            var childContainer = document.createElement('div');
            childContainer.setAttribute('id', 'comment' + pieces[7]);
            var childImg = document.createElement('img');
            childImg.setAttribute('src', pieces[0]);
            childImg.setAttribute('width', '30px');
            childImg.setAttribute('height', '30px');
            childImg.setAttribute('padding', '5px');
            var childNameDate = document.createElement('div');
            childNameDate.innerHTML = pieces[1] + ', ' + pieces[2] + '/' + pieces[3] + ' -- ' + pieces[4] + ':' + pieces[5];
            childNameDate.style.color = 'black';
            var childContent = document.createElement('div');
            childContent.innerHTML = pieces[6];
            childContent.style.textAlign = 'justify';
            childContent.style.fontSize = '8pt';
            childContent.style.color = 'black';
            var childDelete = document.createElement('a');
            childDelete.innerHTML = 'Delete';
            childDelete.setAttribute('id', 'deletecomment' + pieces[7]);
            childDelete.setAttribute('href', 'javascript:deleteComment(' + pieces[7] + ');');
            childDelete.style.fontSize = '10pt';
            childDelete.style.color = 'black';
            childContainer.appendChild(childImg);
            childContainer.appendChild(childNameDate);
            childContainer.appendChild(childContent);
            childContainer.appendChild(childDelete);
            parentTag.appendChild(childContainer);
        }
    };
    xmlHttp.send();
}

function deleteComment(code) {
    var xmlHttp = new XMLHttpRequest();
    var url = "BangServlet?action=deleteComment&code=" + code;
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var queryresult = xmlHttp.responseText;
            var removed = document.getElementById('comment' + code);
            if (removed !== null)
            {
                while (removed.firstChild) {
                    removed.removeChild(removed.firstChild);
                }
            }
        }
    };
    xmlHttp.send();
}