/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//function showRinci(){
//document.getElementById("listtugas3").style.visibility="hidden";
//document.getElementById("listtugas2").style.visibility="hidden";
//document.getElementById("listtugas").style.visibility="hidden";
//document.getElementById("rincitugas").style.visibility="visible";
//document.getElementById("edittugas").style.visibility="hidden";
//document.getElementById("buattugas").style.visibility="hidden";
//document.getElementById("wanted").style.visibility="visible";
//self.focus();
//}

function showEdit() {
    document.getElementById("listtugas3").style.visibility = "hidden";
    document.getElementById("listtugas2").style.visibility = "hidden";
    document.getElementById("listtugas").style.visibility = "hidden";
    document.getElementById("rincitugas").style.visibility = "hidden";
    document.getElementById("edittugas").style.visibility = "visible";
    document.getElementById("buattugas").style.visibility = "hidden";
    document.getElementById("wanted").style.visibility = "visible";
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

function addCategory() {
    var overlay = document.createElement("div");
    overlay.setAttribute("id", "overlay");
    overlay.setAttribute("class", "overlay");
    document.body.appendChild(overlay);

    document.getElementById("add").style.display = 'block';
}


function restore() {
    document.body.removeChild(document.getElementById("overlay"));
    document.getElementById('add').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
}

function Redirect() {
    window.location = "index.html";
}

function Login() {
}

function checkFile(fileID, validExtension) {
    var fileElement = document.getElementById(fileID);
    var fileExtension;
    if (fileElement.value.lastIndexOf(".") > 0) {
        fileExtension = fileElement.value.substring(fileElement.value.lastIndexOf(".") + 1, fileElement.value.length);
    }
    if (fileExtension === validExtension) {
        return true;
    }
    else {
        return false;
    }
}

var usernameValid;
var password1Valid;
var password2Valid;
var nameValid;
var emailValid;
var dateValid;
var fileValid;
function regFill() {
    var atPos = document.getElementById("regemail").value.indexOf("@");
    var dotPos = document.getElementById("regemail").value.indexOf(".");
    //username
    if (document.getElementById("regusername").value === "") {
        document.getElementById("errusernamea").style.display = "none";
        document.getElementById("errusernameb").style.display = "none";
        usernameValid = false;
    } else if (document.getElementById("regusername").value.length < 5) {
        document.getElementById("errusernamea").style.display = "inline";
        document.getElementById("errusernameb").style.display = "none";
        usernameValid = false;
    } else if ((document.getElementById("regusername").value === document.getElementById("regpassword1").value) && (document.getElementById("regusername").value !== "") && (document.getElementById("regpassword1").value !== "")) {
        document.getElementById("errusernamea").style.display = "none";
        document.getElementById("errusernameb").style.display = "inline";
        usernameValid = false;
    } else {
        document.getElementById("errusernamea").style.display = "none";
        document.getElementById("errusernameb").style.display = "none";
        usernameValid = true;
    }
    //password1
    if (document.getElementById("regpassword1").value === "") {
        document.getElementById("errpassword1a").style.display = "none";
        document.getElementById("errpassword1b").style.display = "none";
        password1Valid = false;
    } else if (document.getElementById("regpassword1").value.length < 8) {
        document.getElementById("errpassword1a").style.display = "inline";
        document.getElementById("errpassword1b").style.display = "none";
        password1Valid = false;
    } else if ((document.getElementById("regusername").value === document.getElementById("regpassword1").value) && (document.getElementById("regusername").value !== "") && (document.getElementById("regpassword1").value !== "")) {
        document.getElementById("errpassword1a").style.display = "none";
        document.getElementById("errpassword1b").style.display = "inline";
        password1Valid = false;
    } else {
        document.getElementById("errpassword1a").style.display = "none";
        document.getElementById("errpassword1b").style.display = "none";
        password1Valid = true;
    }
    //pasword2
    if (document.getElementById("regpassword2").value === "") {
        document.getElementById("errpassword2").style.display = "none";
        password2Valid = false;
    } else if ((document.getElementById("regpassword1").value !== document.getElementById("regpassword2").value) && (document.getElementById("regpassword2").value !== "") && (document.getElementById("regpassword2").value !== "")) {
        document.getElementById("errpassword2").style.display = "inline";
        password2Valid = false;
    } else {
        document.getElementById("errpassword2").style.display = "none";
        password2Valid = true;
    }
    //name
    if (document.getElementById("regname").value === "") {
        document.getElementById("errname").style.display = "none";
        nameValid = false;
    } else if (document.getElementById("regname").value.indexOf(" ") < 0) {
        document.getElementById("errname").style.display = "inline";
        nameValid = false;
    } else {
        document.getElementById("errname").style.display = "none";
        nameValid = true;
    }
    //email
    if (document.getElementById("regemail").value === "") {
        document.getElementById("erremaila").style.display = "none";
        document.getElementById("erremailb").style.display = "none";
        document.getElementById("erremailc").style.display = "none";
        emailValid = false;
    } else if ((document.getElementById("regemail").value !== "") && (atPos < 1)) {
        document.getElementById("erremaila").style.display = "inline";
        document.getElementById("erremailb").style.display = "none";
        document.getElementById("erremailc").style.display = "none";
        emailValid = false;
    } else if ((document.getElementById("regemail").value !== "") && (dotPos - atPos < 2)) {
        document.getElementById("erremaila").style.display = "none";
        document.getElementById("erremailb").style.display = "inline";
        document.getElementById("erremailc").style.display = "none";
        emailValid = false;
    } else if (document.getElementById("regemail").value.length - dotPos < 3) {
        document.getElementById("erremaila").style.display = "none";
        document.getElementById("erremailb").style.display = "none";
        document.getElementById("erremailc").style.display = "inline";
        emailValid = false;
    } else {
        document.getElementById("erremaila").style.display = "none";
        document.getElementById("erremailb").style.display = "none";
        document.getElementById("erremailc").style.display = "none";
        emailValid = true;
    }
    //date
    if (document.getElementById("regdate").value === "") {
        dateValid = false;
    } else {
        dateValid = true;
    }
    //avatar
    if (document.getElementById("regfile").value === "") {
        fileValid = false;
    } else {
        fileValid = true;
    }

    if (usernameValid && password1Valid && password2Valid && nameValid && emailValid && dateValid && fileValid) {
        document.getElementById("regbutton").style.color = "black";

    } else {
        document.getElementById("regbutton").style.color = "#777777";

    }
}

function regSubmit() {
    if (usernameValid && password1Valid && password2Valid && nameValid && emailValid && dateValid && fileValid) {
        return true;
    } else {
        return false;
    }
}

/* ================== From here on is Jeremy's functions!! \('>_<)/ ================== */
function showTaskList(code) {
    var xmlhttp;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            document.getElementById("listtugas").innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", "GetTaskList.php?q=" + code, true);
    xmlhttp.send();
}

function changeTaskStatus(code, chk) {
    var xmlhttp;
    chk = (chk === true ? "1" : "0");
    console.log(chk);
    var url = "ChangeTaskStatus.php?code=" + code + "&chkYesNo=" + chk;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            document.getElementById("statusTask" + code).innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function removeTask(code) {
    var xmlhttp;
    var url = "RemoveTask.php?code=" + code;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            document.getElementById("listtugas").innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function addCat() {
    var name = document.getElementById("newCategoryName").value;
    var authUsers = document.getElementById("authUsers").value;
    if (name !== "") {
        var xmlhttp;
        var url = "AddCategory.php?name=" + name + "&authUsers=" + authUsers;
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                document.getElementById("category").innerHTML = xmlhttp.responseText;
            }
        };
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
        restore();
        showList();
    } else {
        alert("Input category name");
    }
}

function showRinci(code) {
    document.getElementById("listtugas").style.visibility = "hidden";
    document.getElementById("rincitugas").style.visibility = "visible";
    document.getElementById("edittugas").style.visibility = "hidden";
    document.getElementById("buattugas").style.visibility = "hidden";
    document.getElementById("wanted").style.visibility = "visible";
    self.focus();

    var xmlhttp;
    var url = "ShowTaskDetail.php?code=" + code;
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            document.getElementById("rincitugas").innerHTML = xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}