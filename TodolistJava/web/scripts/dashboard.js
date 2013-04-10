var chosen=0;
var chosenTask=0;
var coordinatorArr="";

function addCoordinator() {
    var newcoordinator = document.getElementById("userL");
    var coordinatorList = document.getElementById("userList");
    
    if (newcoordinator.value == "") {
        alert("Nama harus dimasukkan");
        return false;
    }
	coordinatorArr+=newcoordinator.value+",";
    coordinatorList.innerHTML += "<li>" + newcoordinator.value + "</li> ";
    newcoordinator.value = "";
    
    return false;
}

function updateAddButtonVisibility() {
	if(chosen!=0)
	{
		var elmt = document.getElementById('addTask');
		elmt.style.display = 'inline-block';
	}
	else if(chosen==0)
	{
		elmt = document.getElementById('addTask');
		elmt.style.display = 'none';
	}
}

function updateDelButtonVisibility() {
	if(chosen!=0)
	{
		var elmt = document.getElementById('deleteCat');
		elmt.style.display = 'inline-block';
	}
	else if(chosen==0)
	{
		elmt = document.getElementById('deleteCat');
		elmt.style.display = 'none';
	}
}

function setChosen(str)
{
	chosen=str;
	updateAddButtonVisibility();
	updateDelButtonVisibility();
}

function setChosenT(str)
{
	chosenTask=str;
}

function loadKategori()
{
    ajax_get("./ajax/KategoriN", function(xhr) {
    document.getElementById("nama_k").innerHTML=xhr.responseText;
    });
}

function NewKategori() {
    var q = document.getElementById('txtNewKategori').value;
    ajax_get("./ajax/CreateKategori?q="+q+"&Arr="+coordinatorArr, function(xhr) {
        loadKategori();
    });
    
    return false;
}

function showCoordinator()
{
    ajax_get("./ajax/AssigneeList", function(xhr) {
        document.getElementById("user").innerHTML=xhr.responseText;
    });
}

function updateStatus(n,str) {
    ajax_get("./ajax/UpdateStatus?q="+str+"&n="+n, function(xhr) {
        document.getElementById("stats").innerHTML=xhr.responseText;
    });
    ajax_get("./ajax/TugasA?q="+chosen, function(xhr) {
        document.getElementById("tugasT").innerHTML=xhr.responseText;
    });
    return false;
}

function NewTask() {
    if(chosen!=0)
    {
            window.location = "./CreateTugas?id_kat="+chosen;
    }
}

function deleteCategory()
{
    if(chosen!=0)
    {
        ajax_get("./ajax/DeleteCat?q="+chosen, function(xhr) {
            window.location = "./dashboard.jsp";
        });
           
    }
}

function deleteTask()
{
    if(chosenTask!=0)
    {
        ajax_get("./ajax/DeleteTask?q="+chosenTask, function(xhr) {
            window.location = "./dashboard.jsp";
        });
    }
}

function loadtugas(str)
{
    ajax_get("./ajax/TugasA?q="+str, function(xhr) {
        document.getElementById("tugasT").innerHTML=xhr.responseText;
    });
	
    return false;
}