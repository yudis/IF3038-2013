function addEvent(node, type, callback) {
    if (node.addEventListener) {
        node.addEventListener(type, function(e) {
            callback(e, e.target);

        }, false);
    }
    else if (node.attachEvent) {
        node.attachEvent('on' + type, function(e) {
            callback(e, e.srcElement);
        });
    }
}


function shouldBeValidated(field) {
    return (
            !(field.getAttribute('readonly') || field.readonly) &&
            !(field.getAttribute('disabled') || field.disabled) &&
            (field.getAttribute('pattern') || field.getAttribute('required'))
            );
}


function instantValidation(field) {
    if (shouldBeValidated(field)) {
        var invalid = (
                (field.getAttribute('required') && !field.value) ||
                (field.getAttribute('pattern') && field.value && !new RegExp(field.getAttribute('pattern')).test(field.value))
                );

        if (!invalid && field.getAttribute('aria-invalid')) {
            field.removeAttribute('aria-invalid');
        }
        else if (invalid && !field.getAttribute('aria-invalid')) {
            field.setAttribute('aria-invalid', 'true');
        }
    }
}

var fields = [document.getElementsByTagName('input'), document.getElementsByTagName('textarea')];
for (var a = fields.length, i = 0; i < a; i++) {
    for (var b = fields[i].length, j = 0; j < b; j++) {
        addEvent(fields[i][j], 'change', function(e, target) {
            instantValidation(target);
        });
    }
}

function displayResult()
{
    var x = document.getElementById("fileupld").name;
    alert(x);
}

function CheckFiles()
{
    var value = document.getElementById('filename').value.split('.');
    var lenghtValue = value.length;
    if (lenghtValue <= 1)
        alert("file undefined");
    else {
        if (value[lenghtValue - 1].toLowerCase() == "jpeg" ||
                value[lenghtValue - 1].toLowerCase() == "jpg" ||
                value[lenghtValue - 1].toLowerCase() == "png" ||
                value[lenghtValue - 1].toLowerCase() == "bmp")
            alert("file benar");
        else
            alert("file salah");
    }
}

function playPause()
{
    var myVideo = document.getElementById("video1");
    if (myVideo.paused)
        myVideo.play();
    else
        myVideo.pause();
}

function checkUsername() {
    if (document.getElementById('username').value == document.getElementById('password').value) {
        document.getElementById('register').setAttribute('disabled', 'true');
    }
    else {
        document.getElementById('register').removeAttribute('disabled');
    }
}

function checkPassword() {
    if (document.getElementById('password').value == document.getElementById('username').value ||
            document.getElementById('password').value == document.getElementById('email').value) {
        document.getElementById('register').setAttribute('disabled', 'true');
    }
    else {
        document.getElementById('register').removeAttribute('disabled');
    }
}

function checkConfirmPassword() {
    if (document.getElementById('password').value != document.getElementById('confirmpassword').value) {
        document.getElementById('register').setAttribute('disabled', 'true');
    }
    else {
        document.getElementById('register').removeAttribute('disabled');
    }
}

function checkDOB() {
    if (!document.getElementById('dob').getAttribute('aria-invalid')) {
        document.getElementById('register').setAttribute('disabled', 'true');
    }
    else {
        document.getElementById('register').removeAttribute('disabled');
    }
}

//======================= DELETE SELECTED COMMENT
function deleteComment(element) {
    var id = element.id;
    var classname = element.className;
    var parentid = document.getElementById(classname);

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            alert('Comment berhasil di delete');
            parentid.parentNode.removeChild(parentid);
        }
    };

    xmlhttp.open("GET", id, true);
    xmlhttp.send();
}

//======================= LOAD TASKS DETAILS
function loadTaskDetails() {
    var taskname = document.getElementsByName("taskname");
    var tasknameid = taskname[0].getAttribute("id");
    var category = document.getElementsByName("category");
    var categoryid = category[0].getAttribute("id");
    var object = JSON.parse(localStorage.getItem("key"));
    var username = object.username;

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("details").innerHTML = xmlhttp.responseText;
        }
    };

    xmlhttp.open("GET", "loadtaskdetails.jsp?taskname=" + tasknameid + "&category=" + categoryid + "&username=" + username, true);
    xmlhttp.send();
}

//======================= SHOW TASKS FOR SELECTED CATEGORY
function search() {
    var term = document.getElementById("searchterm").value;
    var type = document.getElementById("searchtype").value;

    window.location = "../search/?term=" + term + "&type=" + type;
}
//======================= SHOW TASKS FOR SELECTED CATEGORY
var category = "";
function showTasks(element) {
    var id = element.id;
    category = id;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("plustask").style.display = 'inline';
            document.getElementById("task").innerHTML = xmlhttp.responseText;
            alert('Tasks berhasil ditampilkan');
        }
    };

    xmlhttp.open("GET", "loadtask.jsp?category=" + id, true);
    xmlhttp.send();
}

//======================= DELETE SELECTED TASK
function deleteTask(element) {
    var id = element.id;
    var classname = element.className;
    var parentid = document.getElementById(classname);

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            alert('Task berhasil di delete');
            parentid.parentNode.removeChild(parentid);
        }
    };

    xmlhttp.open("GET", id, true);
    xmlhttp.send();
}

//======================= CHANGE STATUS OF SELECTED TASK
function changeStatus(element) {
    var status = element.checked;
    var id = element.id;
    var classname = element.className;

    if (status == true) {
        status = 0;
    }
    else {
        status = 1;
    }

    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var statusstring;
            if (status !== 1) {
                statusstring = "Selesai";

            }
            else {
                statusstring = "Belum Selesai";
            }

            alert('Status changed to "' + statusstring + '"');
            document.getElementById('status' + classname).innerHTML = statusstring;
        }
    };

    xmlhttp.open("GET", id + '&newvalue=' + status, true);
    xmlhttp.send();
}

//======================= CHECK IF LOGGED IN
function checkLogged() {
    var object = JSON.parse(localStorage.getItem("key"));
    if (object == null) {
        window.location = "../index.jsp";
    }
    else {
        // dateString = object.timestamp;
        // now = new Date().getTime().toString();
        login = object.username;

        if (login == null) {
            window.location = "../index.jsp";
        }
        else {
            if (window.XMLHttpRequest) {
                xmlhttp = new XMLHttpRequest();
            }
            else {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }

            xmlhttp.onreadystatechange = function() {
                
               //alert(xmlhttp.readyState + " " + xmlhttp.status);
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    document.getElementById("navsearch").innerHTML = xmlhttp.responseText;
                }
            };

            xmlhttp.open("GET", "../login/loadloggedin.jsp?username=" + login, true);
            xmlhttp.send();
        }
    }
}

//======================= LOGIN
function checkLogin()
{
    var u = document.getElementById("usernamelogin").value;
    var p = document.getElementById("passwordlogin").value;

    if (window.XMLHttpRequest)
    {
        xmlhttp = new XMLHttpRequest();
    }
    else
    {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function()
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
            if (xmlhttp.responseText !== u) // jika sama
            {
                //alert(u);
                var object = {username: u, timestamp: new Date().getTime()};
                localStorage.setItem("key", JSON.stringify(object));
                window.location = "dashboard/";
            }
            else
            {
                alert('Username/password error!');
            }
        }
    };

    xmlhttp.open("GET", "login/?u=" + u + "&p=" + p, true);
    xmlhttp.send();
}

function logout()
{
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            window.location = "../index.jsp";
        }
    };

    xmlhttp.open("GET", "../logout/", true);
    xmlhttp.send();
}

function toggleSearch() {
    //alert(getStyle(document.getElementById('search'), 'display'));
    
    if (getStyle(document.getElementById('search'), 'display') == 'none') 
    {
        document.getElementById('search').style.display = 'inline';
        //alert("none");
    }
    else 
    {
        //alert("inline");
        document.getElementById('search').style.display = 'none';
    }
}

function getStyle(oElm, strCssRule) {
    var strValue = "";
    if (document.defaultView && document.defaultView.getComputedStyle) {
        strValue = document.defaultView.getComputedStyle(oElm, "").getPropertyValue(strCssRule);
    }
    else if (oElm.currentStyle) {
        strCssRule = strCssRule.replace(/\-(\w)/g, function(strMatch, p1) {
            return p1.toUpperCase();
        });
        strValue = oElm.currentStyle[strCssRule];
    }
    return strValue;
}

function redirDetails() {
    window.location = "../taskdetails/";
}

function redirAdd() {
    window.location = "../addtask/?category=" + category;
}

function handleFileSelect(evt) {
    var files = evt.target.files;

    for (var i = 0, f; f = files[i]; i++) {

        if (!f.type.match('image.*')) {
            continue;
        }

        var reader = new FileReader();

        reader.onload = (function(theFile) {
            return function(e) {
                var span = document.createElement('span');
                span.innerHTML = ['<img class="thumb" src="', e.target.result,
                    '" title="', escape(theFile.name), '"/>'].join('');
                document.getElementById('list').insertBefore(span, null);
            };
        })(f);

        // Read in the image file as a data URL.
        reader.readAsDataURL(f);
    }
}

document.getElementById('files').addEventListener('change', handleFileSelect, false);

function popupcat() {
    window.open("../addcat/");
}