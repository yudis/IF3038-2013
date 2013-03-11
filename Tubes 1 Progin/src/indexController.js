var formdata = new Array("username", "password", "confirmpassword", "fullname", "dateofbirth", "email", "mothername", "avatar");
var isValid = new Array(false,false,false,false,false,false,false,false);
var usernamevalue;
var passwordvalue;
var emailvalue;

function doValid(index, regexp, value, validElement)
{
    var new_class = "";
    var validstatus = false;
    if (regexp.test(value))
    {
        new_class = "inline";
        validstatus = true;
    }
    else
    {
        new_class = "hidden";
        validstatus = false;
    }
    
    changeClassName($("id", validElement), new_class);
    isValid[index] = validstatus;
}

function checkUsername(value)
{
    usernamevalue = value;
    var index = 0;
    var regularExpresion = /^([a-zA-Z0-9]{5,})+$/;
    if (usernamevalue != passwordvalue)
    {
        doValid(index, regularExpresion, value, "valid_username");
    }
}

function checkPassword(value)
{
    passwordvalue = value;
    var index = 2;
    var regularExpresion = /^([a-zA-Z0-9]{8,})+$/;
    doValid(index, regularExpresion, value, "valid_password");    
}

function checkConfirmPassword(value)
{
    emailvalue = value;
    var index = 2;
    var validstatus = false;
    var new_status = "hidden";
    if (passwordvalue == value)
    {
        new_status = "inline";
        validstatus = true;
    }
    
    changeClassName($("id", "valid_confirmpassword"), new_status);
    isValid[index] = validstatus;
}
function checkFullName(value)
{
    var index = 3;
    var regularExpresion = /^([a-zA-Z0-9])+ +([a-zA-Z0-9 ])+$/;
    doValid(index, regularExpresion, value, "valid_fullname");
}

function checkDateOfBirth(value)
{
    var index = 4;
    var new_class;
    var validstatus;

    new_class = "inline";
    validstatus = true;
    
    changeClassName($("id", "valid_dateofbirth"), new_class);
    isValid[index] = validstatus;
}

function checkEmail(value)
{
    emailvalue = value;
    var index = 5;
    var regularExpresion = /^([a-zA-Z0-9]{1,})+\@(([a-zA-Z0-9]{1,})+\.)+([a-zA-Z0-9]{2,})+$/;
    if (emailvalue != passwordvalue)
    {
        doValid(index, regularExpresion, value, "valid_email");
    }
}

function checkMotherName(value)
{
    var index = 6;
    var regularExpresion = /^([a-zA-Z0-9])+ +([a-zA-Z0-9 ])+$/;
    doValid(index, regularExpresion, value, "valid_mothername");
}

function checkAvatar(value)
{
    var index = 7;
    var new_class;
    var validstatus;

    new_class = "inline";
    validstatus = true;
    
    changeClassName($("id", "valid_avatar"), new_class);
    isValid[index] = validstatus;
}

function validate(element)
{
    var elmentType = element.getAttribute("name");
    var value = $("name", elmentType)[0].value;
    //alert(value);
    if (elmentType == "username")
    {
        checkUsername(value);
    }
    else if (elmentType == "password")
    {
        checkPassword(value);
    }
    else if (elmentType == "confirmpassword")
    {
        checkConfirmPassword(value);
    }
    else if (elmentType == "fullname")
    {
        checkFullName(value);
    }
    else if (elmentType == "dateofbirth")
    {
        checkDateOfBirth(value);
    }
    else if (elmentType == "email")
    {
        checkEmail(value);
    }
    else if (elmentType == "mothername")
    {
        checkMotherName(value);
    }
    else if (elmentType == "avatar")
    {
        checkAvatar(value);
    }
    
    
}

function doRegistration()
{
    var valid = true;
    var new_class = "inline";
    var validstatus;
    var i = 0;
    while((valid) && (i < isValid.length))
    {
        if (!isValid[i])
        {
            valid = false;
            new_class = "hidden";
        }
        
        ++i;
    }
    
    //validstatus = valid;
    //alert(validstatus);
    //changeClassName($("id", "submitbutton"), new_class);
    if (valid)
    {
        alert("Successfully register to this website.");
    }
    else
    {
        alert("Unsuccessfully register to this website.");
    }

}