var isValid = new Array();
var tasknamevalue;
var attachmentvalue;
var deadlinevalue;
var assigneevalue;
var tagvalue;

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

function checkTaskname(value)
{
    tasknamevalue = value;
    var index = 0;
    var regularExpresion = /^([a-zA-Z0-9]{1,25})$/;
    doValid(index, regularExpresion, value, "valid_taskname");
}

function checkAttachment(value)
{    
    attachmentvalue = value;
    var index = 1;
    var regularExpresion = /^([a-zA-Z0-9]{1,25})$/;
    doValid(index, regularExpresion, value, "valid_attachment");
}

function checkDeadline(value)
{
    deadlinevaluee = value;
    var index = 2;
    var regularExpresion = /^([a-zA-Z0-9]{1,25})$/;
    doValid(index, regularExpresion, value, "valid_deadline");
}   

function checkAssignee(value)
{
    assigneevalue = value;
    var index = 3;
    var regularExpresion = /^([a-zA-Z0-9]{1,25})$/;
    doValid(index, regularExpresion, value, "valid_assignee");
}

function checkTag(value)
{
    tagvalue = value;
    var index = 4;
    var regularExpresion = /^([a-zA-Z0-9]{1,25})$/;
    doValid(index, regularExpresion, value, "valid_tag");
}

function validate(element)
{
    var elmentType = element.getAttribute("name");
    var value = $("name", elmentType)[0].value;
    
    if (elmentType == "taskname")
    {
        checkTaskname(value);
    }
    else if (elmentType == "attachment")
    {
        checkAttachment(value);
    }
    else if (elmentType == "deadline")
    {
        checkDeadline(value);
    }
    else if (elmentType == "assignee")
    {
        checkAssignee(value);
    }
    else if (elmentType == "tag")
    {
        checkTag(value);
    }
}

function doCreatingTask()
{
    
}