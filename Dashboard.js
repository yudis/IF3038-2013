var i;

function ShowTask(s,jum){
	var obj=document.getElementById("Task2");
	obj.style.display="none";
//	obj.style.visibility="hidden";
	//obj.hidden();
	for(var i=1;i<=jum;i++)
	{
	var str="Task2";
	var n=str.replace("2",i);
	if(i==s)
	{
		var obj=document.getElementById(n);
		obj.style.display="block";
	}
	else{
		var obj=document.getElementById(n);
		obj.style.display="none";
	}
	}
	
}
function FormAddKategori(){
		var form=document.getElementById("popupform");
	form.style.display="block";

	var obj= document.getElementById("popup");
	obj.style.display="block";
}

function AddKategori(){
	var d1 = document.getElementById('namakategori').value;
//	var para=document.createElement("a");
	//var node=document.createTextNode(d1);
	//para.appendChild(node);
	//var element=document.getElementById("kategori");
//	element.appendChild(para);
  var div = document.getElementById ("kategori");
  div.innerHTML = "<a onClick=\"ShowTask('2','5')\">"+d1+"</a>";
  
		var form=document.getElementById("popupform");
	form.style.display="none";

	var obj= document.getElementById("popup");
	obj.style.display="none";
//	alert(d1);

}