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
    xmlhttp.open('get','checkuser.php?username='+usersend);
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
    xmlhttp.open('get','checkemail.php?email='+emailsend);
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
    document.getElementById("buattugas").style.visibility = "hidden";
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

function showRinci() {
    document.getElementById("listtugas3").style.visibility = "hidden";
    document.getElementById("listtugas2").style.visibility = "hidden";
    document.getElementById("listtugas").style.visibility = "hidden";
    document.getElementById("rincitugas").style.visibility = "visible";
    document.getElementById("edittugas").style.visibility = "hidden";
    document.getElementById("buattugas").style.visibility = "hidden";
    document.getElementById("wanted").style.visibility = "visible";
    self.focus();
}

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
   overlay.setAttribute("id","overlay");
   overlay.setAttribute("class", "overlay");
   document.body.appendChild(overlay);
   document.getElementById('edit').style.display='block';
}


function restore() {
    document.body.removeChild(document.getElementById("overlay"));
    document.getElementById('add').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
}

function restoreP() {
   document.body.removeChild(document.getElementById("overlay"));
   document.getElementById('edit').style.display='none';
   document.getElementById('overlay').style.display='none';
}

var clickable = false;

function Redirect() {
    window.location = "index.php";
}

function Login() {
    if (document.getElementById("logusername").value !== "admin") {
        alert("Wrong username!");
    } else if (document.getElementById("logpassword").value !== "admincool") {
        alert("Wrong password!");
    } else {
        window.location = "Dashboard.php";
        localStorage.username = document.getElementById("logusername").value;
        localStorage.name = "Billy The Kid";
        localStorage.date = "1968-09-3";
        localStorage.email = "coolKid@yahoo.com";
        document.getElementById("foto").src = "img/foto.png";
    }
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