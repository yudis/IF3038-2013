ajax_get("./ajax/kategori", function(xhr) {
        document.getElementById("nama_k").innerHTML=xhr.responseText;
    });