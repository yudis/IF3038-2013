/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/* ---------------------------- */
/* XMLHTTPRequest Enable */
/* ---------------------------- */
function createObject() {
    var request_type;
    var browser = navigator.appName;
    if (browser == "Microsoft Internet Explorer") {
        request_type = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        request_type = new XMLHttpRequest();
    }
    return request_type;
}

var ab = 0;
function logins() {

    var http = createObject();

    http.onreadystatechange = function() {
        if (http.readyState == 4) {
            var response = http.responseText;
            //alert(response);
            if (response == 0) {
                alert("username dan password salah");
            } else {
                // alert(response+"login berhasil");
                window.location = "dashboard.jsp";
            }
        }
    };

    var username = encodeURI(document.getElementById('logusername').value);
    var pass = encodeURI(document.getElementById('logpassword').value);
    http.open('get', 'login?username=' + username + '&pass=' + pass);
    http.send(null);
}

function showcat() {

    var cat = createObject();

    cat.onreadystatechange = function() {
        if (cat.readyState == 4)
        {
            var response = cat.responseText;
            if (cat.responseText != "")
            {
                document.getElementById("category").innerHTML = "";
                document.getElementById("category").innerHTML = response;
            }
        }

    };
    cat.open('get', 'listcat.php');
    cat.send(null);

}

function showTask(a) {
    ab++;
    var task = createObject();

    task.onreadystatechange = function() {
        if (task.readyState == 4)
        {
            var response = task.responseText;
            document.getElementById("listtugas").innerHTML = "";
            document.getElementById("listtugas").innerHTML = response;
        }

    };
    task.open('get', 'listtask.php?category=' + a);
    task.send(null);

}
function changeStatus(a, b, c) {
    var checkbox = createObject();
    checkbox.onreadystatechange = function() {
        if (checkbox.readyState == 4)
        {
            var response = checkbox.responseText;

            if (checkbox.responseText != "")
            {
                    if (response == "done") {   
                    document.getElementById("checkedvalue" + c).innerHTML = "";
                    document.getElementById("checkedvalue" + c).innerHTML = "undone";
                }
                else {
                    document.getElementById("checkedvalue" + c).innerHTML = "";
                    document.getElementById("checkedvalue" + c).innerHTML = "done";
                }
            }
        }

    };
    

    if (a.checked)
    {
        checkbox.open('get', 'changeStatus.php?IDTask=' + b + '&Status=undone');
    }
    else
    {
        checkbox.open('get', 'changeStatus.php?IDTask=' + b + '&Status=done');
    }
    checkbox.send(null);

}

function update() {
    showcat();
    if (ab > 0)
    {
        showTask();
    }
}

function showRinciTugas(idtask){
    window.location="RinciTugas.php?IDTask="+idtask;
}

function showBuatTugas(cat,user){
    window.location="addtask.php?categoryID="+cat+"&userID="+user;
}

function delCate(idCat){
    var deletecat = createObject();
    deletecat.onreadystatechange = function() {
        if (deletecat.readyState == 4)
        {
            var response = deletecat.responseText;
            alert(response);
        }

    };
    deletecat.open('get', 'deletecategory.php?IDCategory=' +idCat);
    deletecat.send(null);
    update();
}

function delTask(idTask){
    var deletetask = createObject();
    deletetask.onreadystatechange = function() {
        if (deletetask.readyState == 4)
        {
            var response = deletetask.responseText;

        }

    };
    deletetask.open('get', 'deleteTask.php?IDTask=' + idTask );
    deletetask.send(null);
    update();
}

setInterval(update(), 5000);