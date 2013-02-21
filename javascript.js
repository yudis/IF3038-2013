function validateLogin() {
    var x = document.forms["formLogin"]["username"].value;
    var y = document.forms["formLogin"]["pass"].value;
    if (x === "admin" && y === "admin123") {
        //pindah ke halaman dashboard
        window.location = "dashboard.html";
    } else {
        alert("Invalid Login ID or Password");
    }
}

function validate() {
    var check = true;
    var formReg = document.getElementById("formRegister");
    //formReg["subreg"].disabled = false;
    var UsernamRegEx = /^.{5,}$/;
    var NameRegEx = /^.+ .+$/;
    var EmailRegEx = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/;
	
    if (UsernamRegEx.test(formReg["regusername"].value) && formReg["regusername"].value != "") {
        //kasi contreng
    } else {
        check = false;
    }

    if (NameRegEx.test(formReg["nama"].value) && formReg["nama"].value != "") {
        //contreng
    } else {
        check = false;
    }

    if (EmailRegEx.test(formReg["regemail"].value) && formReg["regemail"].value != "") {
        //contreng
    } else {
        check = false;
    }

    if (formReg["regusername"].value == formReg["regpass"].value || formReg["regpass"].value == formReg["regemail"].value) {
        check = false;
    } else {
        //kasi contreng
    }

    if (formReg["regpass"].value != formReg["passconf"].value) {
        check = false;
    } else {
        //kasi contreng
    }

    if (formReg["ttl"].value != "") {
        //contreng
    } else {
        check = false;
    }
	
    if (formReg["avatar"].value != "") {
        //contreng
        var avatarvalue = document.getElementById("avatar").value;
        var extension = avatarvalue.split(".");
        if (extension[extension.length - 1] !== "jpg" || extension[extension.length - 1] !== "jpeg") {
            alert("Sorry please attach jpg/jpeg format");
        } else {
            check = false;
        }
    } 

    if (check) {
        formReg["subreg"].disabled = false;
    } else {
        formReg["subreg"].disabled = true;
    }
}


function validateReg() {
	window.location = "dashboard.html";
    //pindah halaman
}

function reg(){
    var alpha = document.getElementById("alpha");
    var reg = document.getElementById("form_register");
    
    alpha.style.display = "block";
    reg.style.display = "block";
}

function cancel(){
    var alpha = document.getElementById("alpha");
    var reg = document.getElementById("form_register");
    
    alpha.style.display = "none";
    reg.style.display = "none";
}