function myfunc(categ,i){				
		return categ.task[i];
}

function myfunc2(categ){
	return categ.assignee;
}

function myfunc3(categ){
	return categ.deadline;
}

function myfunc4(categ){
	return categ.comment;
}

function myfunc5(categ){
	return categ.tag;
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
	this.categ = newcateg;
}

function createcateg(){
	var d1 = new Date("October 13, 2013 10:13:00")
	var d2 = new Date("July 12, 2014 07:13:00")
	var d3 = new Date("September 29, 1955 11:13:00")
	//categ={cat:cat, task:task, deadline:deadline, assignee:assignee, comment:comment, tag:tag};//var task1 = new Array("Buy a Ferrari","Rent an apartment","Build a startup","Fail in life");
	categ1={task:["Buy a Ferrari","Rent an apartment","Build a startup","Buy some milk"], deadline:d1, assignee:"Cow", comment:"hurry", tag:"#misc #food"};
	
	//var task2 = new Array("Buy a Lexus","Rent an apartment","Build a startup","Fail in life");
	categ2={task:["Buy a Lexus","Sleep beside a hobo","Fail in life","Build a startup"], deadline:d2, assignee:"Atika", comment:"start small", tag:"#work #money"};
	
	//var task3 = new Array("Buy a Volkswagen","Rent an apartment","Build a startup","Fail in life");
	categ3={task:["Buy a Volkswagen","Buy a house","Build a startup","Go back to the future"], deadline:d3, assignee:"Marty", comment:"urgent matters", tag:"#project #time"};
}

var categ1, categ2, categ3;

function mOver1(categ)
{
	
	createcateg();
	
	for (var i in categ.task)
	{
		
		document.getElementById("tasklist").innerHTML='<b><a href="task.html">'+myfunc(categ,i)+'<br>'+myfunc3(categ).toUTCString()+'</br>'+'<br>'+'<br>'+myfunc4(categ)+'</br>'+'<br>'+myfunc2(categ)+'</br>'+'<br>'+myfunc5(categ)+'</br>'+'</a></b><br><a href="addtask.html">&laquo; Add more tasks..</a></br>';
			
	}
	
		
	
}

function mOver2(categ)
{
	
	createcateg();
	
	for (var i in categ.task)
	{
		
		document.getElementById("tasklist").innerHTML='<b><a href="task.html">'+myfunc(categ,i)+'<br>'+myfunc3(categ).toUTCString()+'</br>'+'<br>'+'<br>'+myfunc4(categ)+'</br>'+'<br>'+myfunc2(categ)+'</br>'+'<br>'+myfunc5(categ)+'</br>'+'</a></b><br><a href="addtask.html">&laquo; Add more tasks..</a></br>';
					
	}
	
}

function mOver3(categ)
{
	
	createcateg();
	
	for (var i in categ.task)
	{
		
		document.getElementById("tasklist").innerHTML='<b><a href="task.html">'+myfunc(categ,i)+'<br>'+myfunc3(categ).toUTCString()+'</br>'+'<br>'+'<br>'+myfunc4(categ)+'</br>'+'<br>'+myfunc2(categ)+'</br>'+'<br>'+myfunc5(categ)+'</br>'+'</a></b><br><a href="addtask.html">&laquo; Add more tasks..</a></br>';
					
	}
	
}

function mOut(obj)
{
	//document.getElementById("tasklist").innerHTML=mclk(tasklist);
}