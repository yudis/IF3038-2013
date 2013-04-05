ajax_get("./ajax/KategoriN", function(xhr) {
        document.getElementById("nama_k").innerHTML=xhr.responseText;
});