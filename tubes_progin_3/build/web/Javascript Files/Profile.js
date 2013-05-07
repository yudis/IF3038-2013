function showForm() {
    document.getElementById("popoutovl").style.display = "block";
    document.getElementById("popoutbg").style.display = "block";
}

function hideForm() {
    document.getElementById("popoutovl").style.display = "none";
    document.getElementById("popoutbg").style.display = "none";
}

function bioFill() {
    //name
    if (document.getElementById("bioname").value === "") {
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
    if (document.getElementById("biopassword1").value === "") {
        document.getElementById("errpassword1").style.display = "none";
        password1Valid = false;
    } else if (document.getElementById("biopassword1").value.length < 8) {
        document.getElementById("errpassword1").style.display = "inline";
        password1Valid = false;
    } else {
        document.getElementById("errpassword1").style.display = "none";
        password1Valid = true;
    }
    //pasword2
    if (document.getElementById("biopassword2").value === "") {
        document.getElementById("errpassword2").style.display = "none";
        password2Valid = false;
    } else if ((document.getElementById("biopassword1").value !== document.getElementById("biopassword2").value) && (document.getElementById("biopassword2").value !== "") && (document.getElementById("biopassword2").value !== "")) {
        document.getElementById("errpassword2").style.display = "inline";
        password2Valid = false;
    } else {
        document.getElementById("errpassword2").style.display = "none";
        password2Valid = true;
    }
    //date
    if (document.getElementById("biodate").value === "") {
        dateValid = false;
    } else {
        dateValid = true;
    }
    if (password1Valid && password2Valid && nameValid && dateValid) {
        document.getElementById("biobutton").style.color = "black";
        document.getElementById("biobutton").disabled = false;
    } else {
        document.getElementById("biobutton").style.color = "#777777";
        document.getElementById("biobutton").disabled = true;
    }
}