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
	this.categ = newcateg;
}

function createcateg(){
	//categ={cat:cat, task:task, deadline:deadline, assignee:assignee, comment:comment, tag:tag};//var task1 = new Array("Buy a Ferrari","Rent an apartment","Build a startup","Fail in life");
	categ1={task:["Buy a Ferrari","Rent an apartment","Build a startup","Buy some milk"], deadline:(2013,02,22), assignee:"Atika", comment:"it works", tag:"#work"};
	
	//var task2 = new Array("Buy a Lexus","Rent an apartment","Build a startup","Fail in life");
	categ2={task:["Buy a Lexus","Sleep beside a hobo","Build a startup","Fail in life"], deadline:(2013,02,22), assignee:"Atika", comment:"it works", tag:"#work"};
	
	//var task3 = new Array("Buy a Volkswagen","Rent an apartment","Build a startup","Fail in life");
	categ3={task:["Buy a Volkswagen","Buy a house","Build a startup","Make a sandwich"], deadline:(2013,02,22), assignee:"Atika", comment:"it works", tag:"#work"};
}



function mOver(categ)
{
	
	createcateg();
	
	for (var i in categ.task)
	{
		
		document.getElementById("tasklist").innerHTML=myfunc(categ,i);
			
	}
	
}

function mOut(obj)
{
	//document.getElementById("tasklist").innerHTML=mclk(tasklist);
	
	
}

function mclk(){
	//document.getElementById("tasklist").onclick=function(){document.getElementById("tasklist").innerHTML="<a href="newtask.html"></a>"};
}