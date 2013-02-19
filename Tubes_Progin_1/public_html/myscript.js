function Login(){
    if (document.getElementById("liusername").value !== "admin"){
        alert("Wrong username!");
    } else if (document.getElementById("lipassword").value !== "admincool"){
        alert("Wrong password!");
    }else{
        window.location = "Dashboard.html";
        }
}