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
	coordinatorIndex++;
    
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

function NewKategori() {
    var q = document.getElementById('txtNewKategori').value;
    ajax_get("./ajax/createkategori?q="+q+"&Arr="+coordinatorArr, function(xhr) {
        document.getElementById("nama_k").innerHTML=xhr.responseText;
    });
    return false;
}

function showCoordinator()
{
    ajax_get("./ajax/updateStatus?q="+str+"&n="+n, function(xhr) {
        document.getElementById("user").innerHTML=xhr.responseText;
    });
}

function updateStatus(n,str) {
    ajax_get("./ajax/assigneeList", function(xhr) {
        document.getElementById("stats").innerHTML=xhr.responseText;
    });
    return false;
}

function NewTask() {
    if(chosen!=0)
    {
            window.location = "createtugas.php?id_kat="+chosen;
    }
}

function deleteCategory()
{
	if(chosen!=0)
	{
		window.location = "deleteCat.php?q="+chosen;
	}
}

function deleteTask()
{
	if(chosenTask!=0)
	{
		window.location = "deleteTask.php?q="+chosenTask;
	}
}

function loadtugas(str)
{
    ajax_get("./ajax/tugas?q="+str, function(xhr) {
        document.getElementById("tugasT").innerHTML=xhr.responseText;
    });
	
    return false;
}