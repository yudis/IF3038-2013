function createAJAX()
{
    var xmlhttp;
    if (window.XMLHttpRequest) 
    {
        xmlhttp = new XMLHttpRequest();
    }
    else 
    {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    return xmlhttp;
}

function postAJAX(xmlhttp, url, parameters)
{
    xmlhttp.open("POST", url + "?" + parameters, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send();
    //xmlhttp.send(parameters);
}

function postAJAX2(xmlhttp, url, header, parameters)
{
    xmlhttp.open("POST", url + "?" + parameters, true);
    xmlhttp.setRequestHeader("Content-type", header);
    xmlhttp.send();
    //xmlhttp.send(parameters);
}