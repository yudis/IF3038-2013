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

/* ================== From here on is Timotius' functions!! \('_'\) ================== */
function getXmlHttpRequest( ) {
    var xmlHttpObj;
    if (window.XMLHttpRequest) {
        xmlHttpObj = new XMLHttpRequest( );
    } else {
        try {
            xmlHttpObj = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xmlHttpObj = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                xmlHttpObj = false;
            }
        }
    }
    return xmlHttpObj;
}
function logindeh(){
    var querystring = "";
    var xmlhttp = getXmlHttpRequest();
    var username = document.getElementById('logusername').value;
    var password = document.getElementById('logpassword').value;
    querystring = "?u=" + username + "&p=" + password;
    xmlhttp.open("GET", "login.php" + querystring, true);
    xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                var queryresult = xmlhttp.responseText;
                if(queryresult===1){
                        window.location = "dashboard.php";							
                }else{
                        window.location = "index.php";							
                }
            }
    };
    xmlhttp.send();
}

var usernameValid;
var password1Valid;
var password2Valid;
var nameValid;
var emailValid;
var dateValid;
var fileValid;
var usernameUnique = true;
var emailUnique = true;
function regFill(){
    var atPos = document.getElementById("regemail").value.indexOf("@");
    var dotPos = document.getElementById("regemail").value.indexOf(".");
    //username
    if (document.getElementById("regusername").value === ""){
        document.getElementById("errusernamea").style.display = "none";
        document.getElementById("errusernameb").style.display = "none";
        document.getElementById("errusernamec").style.display = "none";
        usernameValid = false;
    }else if (document.getElementById("regusername").value.length < 5){
        document.getElementById("errusernamea").style.display = "inline";
        document.getElementById("errusernameb").style.display = "none";
        document.getElementById("errusernamec").style.display = "none";
        usernameValid = false;
    } else if ((document.getElementById("regusername").value === document.getElementById("regpassword1").value) && (document.getElementById("regusername").value !== "") && (document.getElementById("regpassword1").value !== "")){
        document.getElementById("errusernamea").style.display = "none";
        document.getElementById("errusernameb").style.display = "inline";
        document.getElementById("errusernamec").style.display = "none";
        usernameValid = false;
    } else {
        document.getElementById("errusernamea").style.display = "none";
        document.getElementById("errusernameb").style.display = "none";
        document.getElementById("errusernamec").style.display = "none";
        usernameValid = true;
    }
    //password1
    if (document.getElementById("regpassword1").value === ""){
        document.getElementById("errpassword1a").style.display = "none";
        document.getElementById("errpassword1b").style.display = "none";
        password1Valid = false;
    } else if (document.getElementById("regpassword1").value.length < 8){
        document.getElementById("errpassword1a").style.display = "inline";
        document.getElementById("errpassword1b").style.display = "none";
        password1Valid = false;
    } else if ((document.getElementById("regusername").value === document.getElementById("regpassword1").value) && (document.getElementById("regusername").value !== "") && (document.getElementById("regpassword1").value !== "")){
        document.getElementById("errpassword1a").style.display = "none";
        document.getElementById("errpassword1b").style.display = "inline";
        password1Valid = false;
    } else {
        document.getElementById("errpassword1a").style.display = "none";
        document.getElementById("errpassword1b").style.display = "none";
        password1Valid = true;
    }
    //pasword2
    if (document.getElementById("regpassword2").value === ""){
        document.getElementById("errpassword2").style.display = "none";
        password2Valid = false;
    } else if ((document.getElementById("regpassword1").value !== document.getElementById("regpassword2").value) && (document.getElementById("regpassword2").value !== "") && (document.getElementById("regpassword2").value !== "")){
        document.getElementById("errpassword2").style.display = "inline";
        password2Valid = false;
    } else {
        document.getElementById("errpassword2").style.display = "none";
        password2Valid = true;
    }
    //name
    if (document.getElementById("regname").value === ""){
        document.getElementsByClassName("errname").style.display = "none";
        nameValid = false;
    } else if (document.getElementById("regname").value.indexOf(" ") < 0) {
        document.getElementsByClassName("errname").style.display = "inline";
        nameValid = false;
    } else {
        document.getElementsByClassName("errname").style.display = "none";
        nameValid = true;
    }
    //email
    if (document.getElementById("regemail").value === ""){
        document.getElementById("erremaila").style.display = "none";
        document.getElementById("erremailb").style.display = "none";
        document.getElementById("erremailc").style.display = "none";
        document.getElementById("erremaild").style.display = "none";
        emailValid = false;
    }else if ((document.getElementById("regemail").value !== "") && (atPos < 1)){
        document.getElementById("erremaila").style.display = "inline";
        document.getElementById("erremailb").style.display = "none";
        document.getElementById("erremailc").style.display = "none";
        document.getElementById("erremaild").style.display = "none";
        emailValid = false;
    } else if ((document.getElementById("regemail").value !== "") && (dotPos - atPos < 2)){
        document.getElementById("erremaila").style.display = "none";
        document.getElementById("erremailb").style.display = "inline";
        document.getElementById("erremailc").style.display = "none";
        document.getElementById("erremaild").style.display = "none";
        emailValid = false;
    } else if (document.getElementById("regemail").value.length - dotPos < 3){
        document.getElementById("erremaila").style.display = "none";
        document.getElementById("erremailb").style.display = "none";
        document.getElementById("erremailc").style.display = "inline";
        document.getElementById("erremaild").style.display = "none";
        emailValid = false;
    } else {
        document.getElementById("erremaila").style.display = "none";
        document.getElementById("erremailb").style.display = "none";
        document.getElementById("erremailc").style.display = "none";
        document.getElementById("erremaild").style.display = "none";
        emailValid = true;
    }
    //date
    if (document.getElementById("regdate").value === ""){
        dateValid = false;
    } else{
        dateValid = true;
    }
    //avatar
    if (document.getElementById("regfile").value === ""){
        fileValid = false;
    }else{
        fileValid = true;
    }
    
    if (usernameValid){
        var querystring = "";
        var xmlhttp = getXmlHttpRequest();
        var username = document.getElementById("regusername").value;
        //var email = document.getElementById("regemail").value;
        querystring = "?u=" + username;
        xmlhttp.open("GET", "register.php" + querystring, true);
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                var queryresult = xmlhttp.responseText;
                if ((queryresult == 1) || (queryresult == 2)){
                        usernameUnique = false;
                        document.getElementById("errusernamea").style.display = "none";
                        document.getElementById("errusernameb").style.display = "none";
                        document.getElementById("errusernamec").style.display = "inline";    
                }else {
                        usernameUnique = true;
                        document.getElementById("errusernamea").style.display = "none";
                        document.getElementById("errusernameb").style.display = "none";
                        document.getElementById("errusernamec").style.display = "none";    
                }
            }
        };
        xmlhttp.send(null);
    }
    if (emailValid){
        var querystring = "";
        var xmlhttp = getXmlHttpRequest();
        var email = document.getElementById("regemail").value;
        querystring = "?e=" + email;
        xmlhttp.open("GET", "register.php" + querystring, true);
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                var queryresult = xmlhttp.responseText;
                if ((queryresult == 1) || (queryresult == 3)){
                        emailUnique = false;
                        document.getElementById("erremaila").style.display = "none";
                        document.getElementById("erremailb").style.display = "none";
                        document.getElementById("erremailc").style.display = "none";
                        document.getElementById("erremaild").style.display = "inline";
                }else {
                        emailUnique = true;
                        document.getElementById("erremaila").style.display = "none";
                        document.getElementById("erremailb").style.display = "none";
                        document.getElementById("erremailc").style.display = "none";
                        document.getElementById("erremaild").style.display = "none";
                }
            }
        };
        xmlhttp.send(null);
    }
    if (usernameValid && password1Valid && password2Valid && nameValid && emailValid && dateValid && fileValid){
        if (usernameUnique && emailUnique){
            document.getElementById("regbutton").style.color = "black";
            document.getElementById("regbutton").disabled = false;
        }
    } else {
        document.getElementById("regbutton").style.color = "#777777";
        document.getElementById("regbutton").disabled = true;
        
    }
}

function showForm(){
    document.getElementById("bioform").style.display = "inline";
    
}
function hideForm(){
    document.getElementById("biodata").style.display = "inline";
    document.getElementById("bioform").style.display = "none";
}
function bioFill(){
    //name
    if (document.getElementById("bioname").value === ""){
        document.getElementById("errname").style.display = "none";
        nameValid = false;
    } else if (document.getElementById("bioname").value.indexOf(" ") < 0) {
        document.getElementById("errname").style.display = "inline";
        nameValid = false;
    } else {
        document.getElementById("errname").style.display = "none";
        nameValid = true;
    }
    //password1
    if (document.getElementById("biopassword1").value === ""){
        document.getElementById("errpassword1").style.display = "none";
        password1Valid = false;
    } else if (document.getElementById("biopassword1").value.length < 8){
        document.getElementById("errpassword1").style.display = "inline";
        password1Valid = false;
    } else {
        document.getElementById("errpassword1").style.display = "none";
        password1Valid = true;
    }
    //pasword2
    if (document.getElementById("biopassword2").value === ""){
        document.getElementById("errpassword2").style.display = "none";
        password2Valid = false;
    } else if ((document.getElementById("biopassword1").value !== document.getElementById("biopassword2").value) && (document.getElementById("biopassword2").value !== "") && (document.getElementById("biopassword2").value !== "")){
        document.getElementById("errpassword2").style.display = "inline";
        password2Valid = false;
    } else {
        document.getElementById("errpassword2").style.display = "none";
        password2Valid = true;
    }
    //date
    if (document.getElementById("biodate").value === ""){
        dateValid = false;
    } else{
        dateValid = true;
    }
    if (password1Valid && password2Valid && nameValid && dateValid){
            document.getElementById("biobutton").style.color = "black";
            document.getElementById("biobutton").disabled = false;
    } else {
        document.getElementById("biobutton").style.color = "#777777";
        document.getElementById("biobutton").disabled = true;
    }
}