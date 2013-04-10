function $(type, name)
{
    if (type == "name")
    {
        return document.getElementsByName(name);
    }
    else if(type == "id")
    {
        return document.getElementById(name);
    }
    else if(type == "tagname")
    {
        return document.getElementsByTagName(name);
    }
    return null;
}

function changeClassName(element, className)
{
    element.className = className;
}