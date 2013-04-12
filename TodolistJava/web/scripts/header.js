function searchAutoComplete(e){
        if (e.)
        var q = document.getElementById("q").value;
        var filter = document.getElementById("filter").value;
        ajax_get("./ajax/AutoCompleteSearch?q=" + q + "&filter=" + filter, function(xhr) {
            document.getElementById("autoC").innerHTML=xhr.responseText;
        });
}