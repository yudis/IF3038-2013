function myfunc(categ,i){				
		return categ.task[i];
}

function myfunc2(categ){
	return categ.assignee;
}


function addtask(task, deadline, assignee, comment, tag){
	this.task = task;
	//this.att = att;
	this.deadline = deadline;
	this.assignee = assignee;
	this.comment = comment;
	this.tag = tag;
}

function addcateg(newcateg){
	var ni = document.getElementById("catlist");
	//var numi = document.getElementById('theValue');
	//var num = (document.getElementById('theValue').value -1)+2;
	//numi.value = num;
	var newdiv = document.createElement("catlist");
	var divIdName = newcateg;
	newdiv.setAttribute('id',divIdName);
	newdiv.innerHTML = '<b>'+divIdName+'</b>';
	ni.appendChild(newdiv);
	//this.categ = newcateg;
}

function removeElement(divNum) {

  var d = document.getElementById('myDiv');

  var olddiv = document.getElementById(divNum);

  d.removeChild(olddiv);

}

function createcateg(){
	//categ={cat:cat, task:task, deadline:deadline, assignee:assignee, comment:comment, tag:tag};//var task1 = new Array("Buy a Ferrari","Rent an apartment","Build a startup","Fail in life");
	categ1={task:["Buy a Ferrari","Rent an apartment","Build a startup","Buy some milk"], deadline:(2013,02,22), assignee:"Atika", comment:"it works", tag:"#work"};
	
	//var task2 = new Array("Buy a Lexus","Rent an apartment","Build a startup","Fail in life");
	categ2={task:["Buy a Lexus","Sleep beside a hobo","Build a startup","Fail in life"], deadline:(2013,02,22), assignee:"Atika", comment:"it works", tag:"#work"};
	
	//var task3 = new Array("Buy a Volkswagen","Rent an apartment","Build a startup","Fail in life");
	categ3={task:["Buy a Volkswagen","Buy a house","Build a startup","Make a sandwich"], deadline:(2013,02,22), assignee:"Atika", comment:"it works", tag:"#work"};
}


function gettask(categ){
	//createcateg();
	return categ.assignee;
}



function mOver(categ)
{
	
	createcateg();
	 
	//var ni = document.getElementById("tasklist");
	//var newdiv = document.createElement("tasklist");
	
	
	
	for (var i in categ.task)
	{
		//newdiv.setAttribute('id',myfunc(categ, i));
		//newdiv.innerHTML = '<b>'+myfunc(categ,i)+'</b>';
		//ni.appendChild(newdiv);
		document.getElementById("tasklist").innerHTML='<b><a href="task.html">'+myfunc(categ,i)+'</b></a>';
	}
	
	//document.getElementById("tasklist").onclick	= "window.open('task.html')";
	
}

function mOut(categ)
{
	//document.getElementById("tasklist").innerHTML=mclk(tasklist);
	//var d = document.getElementById("tasklist");

  //var olddiv = document.getElementById(categ);

  //d.removeChild(olddiv);
	
}

function mclk(){
	//document.getElementById("tasklist").onclick=function(){document.getElementById("tasklist").innerHTML="<a href="newtask.html"></a>"};
}