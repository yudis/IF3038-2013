/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var username = document.getElementById("regusername");
var fullname = document.getElementById("regname");
var pass1 = document.getElementById("regpassword1");
var pass2 = document.getElementById("regpassword2");
var email = document.getElementById("regemail");
var file = document.getElementById("regfile");
var submit = document.getElementById("regbutton");
var regForm = document.getElementById("regForm");
var valid1bool;
var valid2bool;
var valid3bool;
var valid4bool;
var valid5bool;
var valid6bool;
var valid7bool;

username.onkeyup = function()
{
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4)
        {
            if (xmlhttp.responseText > 0) {
                valid1.src = "img/salah.png";
                valid1bool = false;
            } else {
                if (username.checkValidity()) {
                    valid1.src = "img/benar.png";
                    valid1bool = true;
                }
                else
                {
                    valid1.src = "img/salah.png";
                    valid1bool = false;
                }
                if (username.value == pass1.value)
                {
                    valid1.src = "img/salah.png";
                    valid1bool = false;
                }
                cekvalid();
            }
        }
    };

    var usersend = encodeURI(username.value);
    xmlhttp.open('get', 'checkuser.php?username=' + usersend);
    xmlhttp.send(null);
};

fullname.onkeyup = function()
{
    if (fullname.checkValidity()) {
        valid2.src = "img/benar.png";
        valid2bool = true;
    }
    else
    {
        valid2.src = "img/salah.png";
        valid2bool = false;
    }
    cekvalid();
}

pass1.onkeyup = function()
{

    if (pass1.checkValidity()) {
        valid3.src = "img/benar.png";
        valid3bool = true;
    }
    else
    {
        valid3.src = "img/salah.png";
        valid3bool = false;
    }

    if (username.value == pass1.value)
    {
        valid3bool = false;
        valid3.src = "img/salah.png";
    }
    else if (pass1.value == email.value)
    {
        valid3.src = "img/salah.png";
        valid3bool = false;
    }
    if (pass2.checkValidity() && (pass1.value == pass2.value)) {
        valid4.src = "img/benar.png";
        valid4bool = true;
    }
    else
    {
        valid4.src = "img/salah.png";
        valid4bool = false;
    }
    cekvalid();
}

pass2.onkeyup = function()
{
    if (pass2.checkValidity() && (pass1.value == pass2.value)) {
        valid4.src = "img/benar.png";
        valid4bool = true;
    }
    else
    {
        valid4.src = "img/salah.png";
        valid4bool = false;
    }

    cekvalid();
}

email.onkeyup = function()
{
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4)
        {
            // document.getElementById("txtHint").innerHTML=xmlhttp.responseText;
            if (xmlhttp.responseText > 0) {
                valid5.src = "img/salah.png";
                valid5bool = false;
            } else {
                if (email.checkValidity()) {
                    valid5.src = "img/benar.png";
                    valid5bool = true;
                }
                else
                {
                    valid5.src = "img/salah.png";
                    valid5bool = false;
                }
                if (pass1.value == email.value)
                {
                    valid5.src = "img/salah.png";
                    valid5bool = false;
                }
                cekvalid();
            }
        }
    };

    var emailsend = encodeURI(email.value);
    xmlhttp.open('get', 'checkemail.php?email=' + emailsend);
    xmlhttp.send(null);
}

function checkImage()
{
    var extensi = file.value.match("^.+\.(jpe?g|JPE?G)$");
    if (extensi) {
        valid6.src = "img/benar.png";
        valid6bool = true;
    } else {
        valid6.src = "img/salah.png";
        valid6bool = false;
    }
    cekvalid();
}

function dateChange() {
    valid7.src = "img/benar.png";
    valid7bool = true;
    cekvalid();
}

function cekvalid() {
    if (valid1bool == true && valid2bool == true && valid3bool == true && valid4bool == true && valid5bool == true && valid6bool == true && valid7bool == true)
    {
        submit.disabled = false;
    }
    else
    {
        submit.disabled = "disabled";
    }
}


function showList() {
    document.getElementById("listtugas3").style.visibility = "hidden";
    document.getElementById("listtugas2").style.visibility = "hidden";
    document.getElementById("listtugas").style.visibility = "visible";
    document.getElementById("rincitugas").style.visibility = "hidden";
    document.getElementById("edittugas").style.visibility = "hidden";
    //document.getElementById("buattugas").style.visibility = "hidden";
    document.getElementById("wanted").style.visibility = "hidden";
}

function showList2() {
    document.getElementById("listtugas").style.visibility = "hidden";
    document.getElementById("listtugas2").style.visibility = "visible";
    document.getElementById("listtugas3").style.visibility = "hidden";
    document.getElementById("rincitugas").style.visibility = "hidden";
    document.getElementById("edittugas").style.visibility = "hidden";
    document.getElementById("buattugas").style.visibility = "hidden";
    document.getElementById("wanted").style.visibility = "hidden";
    self.focus;
}

function showList3() {
    document.getElementById("listtugas").style.visibility = "hidden";
    document.getElementById("listtugas2").style.visibility = "hidden";
    document.getElementById("listtugas3").style.visibility = "visible";
    document.getElementById("rincitugas").style.visibility = "hidden";
    document.getElementById("edittugas").style.visibility = "hidden";
    document.getElementById("buattugas").style.visibility = "hidden";
    document.getElementById("wanted").style.visibility = "hidden";
    self.focus();
}

function showEdit() {
    document.getElementById("rincitugas").style.visibility = "hidden";
    document.getElementById("edittugas").style.visibility = "visible";
    self.focus();
}
function showBuat() {
    document.getElementById("listtugas3").style.visibility = "hidden";
    document.getElementById("listtugas2").style.visibility = "hidden";
    document.getElementById("listtugas").style.visibility = "hidden";
    document.getElementById("rincitugas").style.visibility = "hidden";
    document.getElementById("edittugas").style.visibility = "hidden";
    document.getElementById("buattugas").style.visibility = "visible";
    document.getElementById("wanted").style.visibility = "visible";
}

var valid = false;

function createTask() {
    var regex = /^[a-zA-Z0-9]{5,25}$/;

    if ((regex.test(document.getElementById("namaTask").value))) {
        var k = document.getElementById("listtugas");
        k.innerHTML = "<a class='listTugas' onclick='showRinci();'><a class='listTugas' onclick='showRinci();'></a> <a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a class='listTugas' onclick='showRinci();'></a><a onclick='showBuat()' class='addTask'></a>";
        document.getElementById("namaTask").value = 0;
        showList();
    } else {
        alert("task name must be 5-25 long");
    }
}

function addCat() {
    var k = document.getElementById("category");
    var l = document.getElementById("cate").value;
    if (l !== "") {
        k.innerHTML += "<div class='kategori' onclick='showList2();'>" + l + "</div>";
        restore();
        showList();
    } else {
        alert("Input category name");
    }
}

function addCategory() {
    var overlay = document.createElement("div");
    overlay.setAttribute("id", "overlay");
    overlay.setAttribute("class", "overlay");
    document.body.appendChild(overlay);

    document.getElementById('add').style.display = 'block';
}

function editProfile() {
    var overlay = document.createElement("div");
    overlay.setAttribute("id", "overlay");
    overlay.setAttribute("class", "overlay");
    document.body.appendChild(overlay);
    document.getElementById('edit').style.display = 'block';
}


function restore() {
    document.body.removeChild(document.getElementById("overlay"));
    document.getElementById('add').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
}

function restoreP() {
    document.body.removeChild(document.getElementById("overlay"));
    document.getElementById('edit').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
}

var clickable = false;

function Redirect() {
    window.location = "index.php";
}

function Register() {
    var atPos = document.getElementById("regemail").value.indexOf("@");
    var dotPos = document.getElementById("regemail").value.indexOf(".");
    if ((document.getElementById("regusername").value.length < 5) && (document.getElementById("regusername").value !== "")) {
        alert("Username should be at least 5 characters long.");
    } else if ((document.getElementById("regpassword1").value.length < 8) && (document.getElementById("regpassword1").value !== "")) {
        alert("Password should be at least 8 characters long.");
    } else if ((document.getElementById("regusername").value === document.getElementById("regpassword1").value) && (document.getElementById("regusername").value !== "") && (document.getElementById("regpassword1").value !== "")) {
        alert("Username and password cannot be identical.");
    } else if ((document.getElementById("regpassword1").value !== document.getElementById("regpassword2").value) && (document.getElementById("regpassword2").value !== "") && (document.getElementById("regpassword2").value !== "")) {
        alert("Confirmed password and password are not the same.");
    } else if ((document.getElementById("regname").value.indexOf(" ") < 0) && (document.getElementById("regname").value !== "")) {
        alert("Name should be constructed by two or more words separated by space.");
    } else if ((document.getElementById("regemail").value !== "") && (atPos < 1)) {
        alert("There should be at least one character before '@' character.");
    } else if ((document.getElementById("regemail").value !== "") && (dotPos - atPos < 2)) {
        alert("There should be at least one character between '@' and '.' character.");
    } else if ((document.getElementById("regemail").value !== "") && (document.getElementById("regemail").value.length - dotPos < 3)) {
        alert("There should be at least two characters after '.' character");
    } else if ((document.getElementById("regusername").value !== "") && (document.getElementById("regpassword1").value !== "") &&
            (document.getElementById("regpassword2").value !== "") && (document.getElementById("regname").value !== "") &&
            (document.getElementById("regemail").value !== "")) {
        document.getElementById("regbutton").style.color = "black";
        document.getElementById("regbutton").style.fontWeight = "bold";
        clickable = true;
    } else {
        document.getElementById("regbutton").style.color = "#777777";
        document.getElementById("regbutton").style.fontWeight = "normal";
        clickable = false;
    }
}

function Submit() {
    if (clickable) {
        window.location = "Dashboard.php";
        localStorage.username = document.getElementById("regusername").value;
        localStorage.name = document.getElementById("regname").value;
        localStorage.date = document.getElementById("regdate").value;
        localStorage.email = document.getElementById("regemail").value;
        document.getElementById("foto").src = "img/foto_anonim.png";
    }
}

function multiAutocomp(input, phpscript, text) {
    var idname = "hasil_" + input.id;
    var elmt = document.getElementById(input.id);
    if (elmt.value.length > 0) {

        document.body.setAttribute("onClick", "multiAutocompHandleClick('" + text + "');");
        multiAutocompClear(input);
        var div = document.createElement("div");
        div.setAttribute("id", idname);
        div.setAttribute("class", "autocomplete_container");
        div.style.top = elmt.offsetTop + elmt.offsetHeight;
        div.style.left = elmt.offsetLeft;
        div.style.width = elmt.offsetWidth;
        var divadd = document.getElementById(text);
        //document.body.appendChild(div);
        divadd.appendChild(div);

        try {
            var xmlHttp = new XMLHttpRequest();
        } catch (e) {
            try {
                var xmlHttp = new ActiveXObject(Msxml2.XMLHTTP);
            } catch (e) {
                console.log("Browser doesn't support AJAX");
            }
        }
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var query_result = xmlHttp.responseXML.documentElement.getElementsByTagName("Data");
                for (i = 0; i < query_result.length; i++) {
                    var inner_result_id = query_result[i].getElementsByTagName("ID");
                    var inner_result_name = query_result[i].getElementsByTagName("String");
                    var sug_id = idname + i;
                    var child_div = document.createElement("div");
                    child_div.setAttribute("id", sug_id);
                    child_div.setAttribute("class", "autocomplete");
                    child_div.setAttribute("onClick", "multiAutocompGetResult(this,'" + text + "');");
                    child_div.style.width = elmt.offsetWidth;
                    child_div.innerHTML = inner_result_name[0].firstChild.nodeValue;
                    var hidden_input = document.createElement("div");
                    hidden_input.style.display = "none";
                    hidden_input.setAttribute("id", sug_id + "h");
                    hidden_input.innerHTML = inner_result_id[0].firstChild.nodeValue;
                    child_div.appendChild(hidden_input);
                    var parent_div = document.getElementById(idname);
                    parent_div.appendChild(child_div);
                }
            }
        }
        var q = document.getElementById(input.id).value;
        if (q.length < 1) {
            xmlHttp.open("GET", phpscript + "?q=" + q, true);
        } else {
            q = q.split(";");
            xmlHttp.open("GET", phpscript + "?q=" + q[q.length - 1], true);
        }
        xmlHttp.send(null);

    } else {
        multiAutocompClear(input, text);
        document.body.removeAttribute("onClick");
    }
}

function multiAutocompClear(input, text) {
    console.log("Cleared");
    var idname = "hasil_" + input.id;
    while (document.getElementById(idname) != null) {
        var divadd = document.getElementById(text);
        divadd.removeChild(document.getElementById(idname));
        //document.body.removeChild(document.getElementById(idname));
    }
}

function multiAutocompClearAll(text) {
    var elmt = document.getElementsByClassName("autocomplete");
    for (var i = 0; i < elmt.length; i++) {
        var divadd = document.getElementById(text);
        divadd.removeChild(document.getElementById(elmt[i].id));
        //document.body.removeChild(document.getElementById(elmt[i].id));		
    }
}

function multiAutocompGetResult(input, text) {
    var idname = input.id + "h";
    var parentname = idname.substr(6, idname.length - 8);
    var parentdiv = idname.substr(0, idname.length - 2);
    var elmt = document.getElementById(parentname);
    if (elmt.value.indexOf(";") == -1) {
        elmt.value = document.getElementById(idname).innerHTML + ";";
    } else {
        var temp_string = elmt.value;
        temp_string = temp_string.split(";");
        var result_string = "";
        for (var i = 0; i < temp_string.length - 1; i++) {
            result_string += temp_string[i] + ";";
        }
        elmt.value = result_string + document.getElementById(idname).innerHTML + ";";
    }
    var divadd = document.getElementById(text);
    divadd.removeChild(document.getElementById(parentdiv));
    //document.body.removeChild(document.getElementById(parentdiv));
}

function multiAutocompHandleClick(text) {
    var parentdiv = document.getElementsByClassName("autocomplete_container");
    for (var i = 0; i < parentdiv.length; i++) {
        var divadd = document.getElementById(text);
        divadd.removeChild(document.getElementById(parentdiv[i].id));
        //document.body.removeChild(document.getElementById(parentdiv[i].id));
    }
}

function addComment(user, IDTask) {
    var comment = document.getElementById('addCommentText').value;
    if (comment != "") {
        var xmlhttp;
        if (window.XMLHttpRequest)
        {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else
        {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function()
        {
            if (xmlhttp.readyState == 4)
            {
                var response = xmlhttp.responseText;
                comment = "www";
                //document.getElementById("isikomentar").innerHTML+=response;
            }
        }
        xmlhttp.open('get', 'addcomment.php?comment=' + encodeURI(comment) + '&user=' + encodeURI(user) + '&task=' + encodeURI(IDTask));
        xmlhttp.send(null);
    }
}

function loadcomment() {
    var IDTask = document.getElementById("HiddenIDTask").value;
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4)
        {
            var response = xmlhttp.responseText;
            document.getElementById("komentaryey").innerHTML = response;
        }
    }
    xmlhttp.open('get', 'generatecomment.php?IDTask=' + encodeURI(IDTask+"&page="+page));
    xmlhttp.send(null);

    setTimeout('loadcomment()', 500);

}

function removeComment(IDComment) {
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4)
        {
        }
    }
    xmlhttp.open('get', 'deletecomment.php?IDComment=' + encodeURI(IDComment));
    xmlhttp.send(null);
}

function editTask(IDTask) {
    var deadline = document.getElementById("editdeadline").value;
    var assignee = document.getElementById("addnewassignee").value;
    var tag = document.getElementById(id = "edittag").value;

    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4)
        {
            var response = xmlhttp.responseText;
            window.location = "RinciTugas.php?IDTask=" + IDTask;
        }
    }
    xmlhttp.open('get', 'edittask.php?deadline=' + encodeURI(deadline) + "&IDTask=" + encodeURI(IDTask) + "&assignee=" + encodeURI(assignee) + "&tag=" + encodeURI(tag));
    xmlhttp.send(null);
}

function deleteassignee(IDAssignment, IDTask) {
    var deadline = document.getElementById("editdeadline").value;
    var assignee = document.getElementById("addnewassignee").value;
    var tag = document.getElementById(id = "edittag").value;

    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4)
        {
            var response = xmlhttp.responseText;
            document.getElementById("ListEditAssignee").innerHTML = response;
        }
    }
    xmlhttp.open('get', 'deleteassignment.php?IDAssignment=' + encodeURI(IDAssignment) + '&IDTask=' + encodeURI(IDTask));
    xmlhttp.send(null);
}

function deletetag(IDTag, IDTask) {
    var deadline = document.getElementById("editdeadline").value;
    var assignee = document.getElementById("addnewassignee").value;
    var tag = document.getElementById(id = "edittag").value;

    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4)
        {
            var response = xmlhttp.responseText;
            document.getElementById("ListEditTag").innerHTML = response;
        }
    }
    xmlhttp.open('get', 'deletetag.php?IDTag=' + encodeURI(IDTag) + '&IDTask=' + encodeURI(IDTask));
    xmlhttp.send(null);
}

function changestatus(IDTask) {
    var status = document.getElementById("checkboxstatus").checked;
    
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4)
        {
            var response = xmlhttp.responseText;
            document.getElementById("checkstatus").innerHTML = response;
        }
    }
    xmlhttp.open('get', 'updatestatus.php?IDTask=' + encodeURI(IDTask)+"&status="+encodeURI(status));
    xmlhttp.send(null);
    setTimeout('changestatus('+IDTask+')', 500);

}

var page;

function loadpagevar(){
    page=1;
}

function setPage(_page){
    page=_page;
}