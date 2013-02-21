function enable(element)
{
    $("name", element)[0].disabled = false;
}

function submit(element)
{
    var elm = $("name", element)[0];
    alert("Submitted");
}