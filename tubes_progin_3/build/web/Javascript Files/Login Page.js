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

/* ================= RELATED TO LOG IN ================= */

function logindeh() {
    var querystring = "";
    var xmlhttp = getXmlHttpRequest();
    var username = document.getElementById('logusername').value;
    var password = document.getElementById('logpassword').value;
    querystring = "?u=" + username + "&p=" + password;
    xmlhttp.open("GET", "login.jsp" + querystring, true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var queryresult = xmlhttp.responseText;
            if (queryresult.indexOf('1') !== -1) {
                window.location = "dashboard.jsp";
            } else {
                window.location = "index.jsp";
                window.alert("Wrong username or password!!");
            }
        }
    };
    xmlhttp.send();
}

/* ================= RELATED TO REGISTER ================= */
var usernameValid;
var password1Valid;
var password2Valid;
var nameValid;
var emailValid;
var dateValid;
var fileValid;
var usernameUnique = true;
var emailUnique = true;
function regFill() {
    var atPos = document.getElementById("regemail").value.indexOf("@");
    var dotPos = document.getElementById("regemail").value.indexOf(".");
    //username
    if (document.getElementById("regusername").value === "") {
        document.getElementById("errusernamea").style.display = "none";
        document.getElementById("errusernameb").style.display = "none";
        document.getElementById("errusernamec").style.display = "none";
        usernameValid = false;
    } else if (document.getElementById("regusername").value.length < 5) {
        document.getElementById("errusernamea").style.display = "inline";
        document.getElementById("errusernameb").style.display = "none";
        document.getElementById("errusernamec").style.display = "none";
        usernameValid = false;
    } else if ((document.getElementById("regusername").value === document.getElementById("regpassword1").value) && (document.getElementById("regusername").value !== "") && (document.getElementById("regpassword1").value !== "")) {
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
        document.getElementById("erremaild").style.display = "none";
        emailValid = false;
    } else if ((document.getElementById("regemail").value !== "") && (atPos < 1)) {
        document.getElementById("erremaila").style.display = "inline";
        document.getElementById("erremailb").style.display = "none";
        document.getElementById("erremailc").style.display = "none";
        document.getElementById("erremaild").style.display = "none";
        emailValid = false;
    } else if ((document.getElementById("regemail").value !== "") && (dotPos - atPos < 2)) {
        document.getElementById("erremaila").style.display = "none";
        document.getElementById("erremailb").style.display = "inline";
        document.getElementById("erremailc").style.display = "none";
        document.getElementById("erremaild").style.display = "none";
        emailValid = false;
    } else if (document.getElementById("regemail").value.length - dotPos < 3) {
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

    if (usernameValid) {
        var querystring = "";
        var xmlhttp = getXmlHttpRequest();
        var username = document.getElementById("regusername").value;
        //var email = document.getElementById("regemail").value;
        querystring = "?u=" + username;
        xmlhttp.open("GET", "registerauth.jsp" + querystring, true);
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                var queryresult = xmlhttp.responseText;
                if (queryresult.indexOf('1') === -1) {
                    usernameUnique = false;
                    document.getElementById("errusernamea").style.display = "none";
                    document.getElementById("errusernameb").style.display = "none";
                    document.getElementById("errusernamec").style.display = "inline";
                } else {
                    usernameUnique = true;
                    document.getElementById("errusernamea").style.display = "none";
                    document.getElementById("errusernameb").style.display = "none";
                    document.getElementById("errusernamec").style.display = "none";
                }
            }
        };
        xmlhttp.send(null);
    }
    if (emailValid) {
        var querystring = "";
        var xmlhttp = getXmlHttpRequest();
        var email = document.getElementById("regemail").value;
        querystring = "?e=" + email;
        xmlhttp.open("GET", "registerauth.jsp" + querystring, true);
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                var queryresult = xmlhttp.responseText;
                if (queryresult.indexOf('1') === -1) {
                    emailUnique = false;
                    document.getElementById("erremaila").style.display = "none";
                    document.getElementById("erremailb").style.display = "none";
                    document.getElementById("erremailc").style.display = "none";
                    document.getElementById("erremaild").style.display = "inline";
                } else {
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
    if (usernameValid && password1Valid && password2Valid && nameValid && emailValid && dateValid && fileValid) {
        if (usernameUnique && emailUnique) {
            document.getElementById("regbutton").style.color = "black";
            document.getElementById("regbutton").disabled = false;
        }
    } else {
        document.getElementById("regbutton").style.color = "#777777";
        document.getElementById("regbutton").disabled = true;

    }
}

