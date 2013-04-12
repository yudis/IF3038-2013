
var kategori = [
	{ID: "Kategori 0", task: {Name:["task01","task02"],Deadline: ["01-01-2013","01-02-2013"], Deskripsi: ["deskripsi tugas 1","deskripsi tugas 2"]}, tag: ["tag01", "tag02"]},
	{ID: "Kategori 1", task: {Name:["task03","task04"],Deadline: ["02-01-2013","02-02-2013"], Deskripsi: ["deskripsi tugas 3","deskripsi tugas 4"]}, tag: ["tag11", "tag12"]},
	{ID: "Kategori 2", task: {Name:["task05","task06"],Deadline: ["03-01-2013","03-02-2013"], Deskripsi: ["deskripsi tugas 5","deskripsi tugas 6"]}, tag: ["tag21", "tag22"]},
];			


function loadCategory()
{
	var xx = document.getElementById("dashboard-listcategory");
	xx.innerHTML = xx.innerHTML + "<a class=\"dash-category\" href=\"#\" onclick=\"loadContentCategory(-1)\">all</a><br>";
	
	for (var b = 0; b < kategori.length; b++)
	{
		xx.innerHTML = xx.innerHTML + ("<a id=\"dash-category" + b + "\" href=\"#\" onclick=\"loadContentCategory(" + b + ")\">" + kategori[b].ID + "</a><br>");
	}	
	
	xx.innerHTML = xx.innerHTML + "<a class=\"dash-category\" href=\"#join_form\" id=\"join_pop\">add category</a>";
}
 
function loadTask(var1){
	var a = Math.floor(var1/10);
	var b = var1 % 10;
	var xx = document.getElementById("dashboard-tugas");
	xx.innerHTML = "";	
	xx.innerHTML += "<h3>" + kategori[a].task.Name[b] + "</h3><br><h4>" + kategori[a].task.Deskripsi[b] + "</h4>";

}

function makeTask(var1)
{
	var xx = document.getElementById("dashboard-tugas");
	xx.innerHTML = "";
	xx.innerHTML += "<h4>halaman pembuatan tugas</h4>";
}

function loadContentCategory(var1)
{
	var yy = document.getElementById("dashboard-tugas");
	yy.innerHTML = "";
	
	if (var1 != -1){
		var zz = "";
		zz += "<div class=\"category-content\"><h3>" + kategori[var1].ID + "</h3><hr><h4>Task:</h4><ol>";
		for (c = 0; c < kategori[var1].task.Name.length; c++){
			zz += "<li><a id=\"dash-task" + var1 + "" + c + "\" href=\"#\" onclick=\"location.href=\'rincian_tugas.html\'\">"+ kategori[var1].task.Name[c] + " deadline: " + kategori[var1].task.Deadline[c] + "</a></li>";
		}
		zz += "</ol><h4>tag :</h4>";
		
		for (d = 0; d < kategori[var1].tag.length; d++){
			zz+= "<a class=\"tag\" href=\"#\">" + kategori[var1].tag[d] + " </a>";
		}
		//zz += "<br></div><button onclick=\"makeTask(" + var1 + ")\">Add Task</button>";
		zz += "<br></div><button onclick=\"location.href=\'tambah_tugas.html\'\">Add Task</button>";
		yy.innerHTML += zz;	
	} else {
		for (var a = 0; a < kategori.length; a++)
		{
			var zz = "";
			zz += "<div class=\"category-content\"><h3>" + kategori[a].ID + "</h3><hr><h4>Task:</h4><ol>";
			for (c = 0; c < kategori[a].task.Name.length; c++){
				zz += "<li><a id=\"dash-task" + a + "" + c + "\" href=\"#\" onclick=\"location.href=\'rincian_tugas.html\'\">"+ kategori[a].task.Name[c] + " deadline: " + kategori[a].task.Deadline[c] + "</a></li>";
			}
			zz += "</ol><h4>tag :</h4>";
			
			for (d = 0; d < kategori[a].tag.length; d++){
				zz+= "<a class=\"tag\" href=\"#\">" + kategori[a].tag[d] + " </a>";
			}
			zz += "<br></div>";
			yy.innerHTML += zz;
		}
	}
}
