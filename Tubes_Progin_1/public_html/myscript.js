var clickable = false;

function Redirect(){
    window.location = "index.html";
}

function Login(){
    if (document.getElementById("logusername").value !== "admin"){
        alert("Wrong username!");
    } else if (document.getElementById("logpassword").value !== "admincool"){
        alert("Wrong password!");
    }else{
        window.location = "Dashboard.html";
    }
}

function Register(){
    var atPos = document.getElementById("regemail").value.indexOf("@");
    var dotPos = document.getElementById("regemail").value.indexOf(".");
    if ((document.getElementById("regusername").value.length < 5) && (document.getElementById("regusername").value !== "")){
        alert("Username should be at least 5 characters long.");
    } else if ((document.getElementById("regpassword1").value.length < 8) && (document.getElementById("regpassword1").value !== "")){
        alert("Password should be at least 8 characters long.");
    } else if ((document.getElementById("regusername").value === document.getElementById("regpassword1").value) && (document.getElementById("regusername").value !== "") && (document.getElementById("regpassword1").value !== "")){
        alert("Username and password cannot be identical.");
    } else if ((document.getElementById("regpassword1").value !== document.getElementById("regpassword2").value) && (document.getElementById("regpassword2").value !== "") && (document.getElementById("regpassword2").value !== "")){
        alert("Confirmed password and password are not the same.");
    } else if ((document.getElementById("regname").value.indexOf(" ") < 0) && (document.getElementById("regname").value !== "")) {
        alert("Name should be constructed by two or more words separated by space.");
    } else if ((document.getElementById("regemail").value !== "") && (atPos < 1)){
            alert("There should be at least one character before '@' character.");
    } else if ((document.getElementById("regemail").value !== "") && (dotPos - atPos < 2)){
            alert("There should be at least one character between '@' and '.' character.");
    } else if ((document.getElementById("regemail").value !== "")&&(document.getElementById("regemail").value.length - dotPos < 3)){
            alert("There should be at least two characters after '.' character");
    } else if ((document.getElementById("regusername").value !== "")&&(document.getElementById("regpassword1").value !== "")&&
        (document.getElementById("regpassword2").value !== "")&&(document.getElementById("regname").value !== "")&&
        (document.getElementById("regemail").value !== "")){
            document.getElementById("regbutton").style.color = "black";
            document.getElementById("regbutton").style.fontWeight = "bold";
            clickable = true;
    } else {
        document.getElementById("regbutton").style.color = "#777777";
        document.getElementById("regbutton").style.fontWeight = "normal";
        clickable = false;
    }
}

function Submit(){
    if (clickable){
        window.location = "Dashboard.html";
    }
}