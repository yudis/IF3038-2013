function Comment() {

	var curTime = new Date();
	this.name_ = "null"; 
	this.time_ = curTime.getHours() + ":" + curTime.getMinutes() + ":" + curTime.getSeconds(); 
	this.date_ = curTime.getDate() + "-" + (curTime.getMonth()+1) + "-" + curTime.getFullYear(); 
	this.content_ = "null content";

	this.getName = function() 
	{
		return this.name_;
	}
	
	this.getTime = function()
	{
		return this.time_
	}

	this.getDate = function() 
	{
		return this.date_;
	}
	
	this.getContent = function()
	{
		return this.content_;
	}
	
	this.setName = function(_newName) 
	{
		this.name_ = _newName;
	}
	
	this.setTime = function(_newTime)
	{
		this.time_ = _newTime;
	}

	this.setDate = function(_newDate) 
	{
		this.date_ = _newDate;
	}
	
	this.setContent = function(_newContent)
	{
		this.content_ = _newContent;
	}

	this.display = function(varStr) 
	{
		
		var preventHTML = /</g;
		document.write("<div style=\"margin-bottom:10px; border:solid; width:300px; border-width:1px; \" id=\"comment"+varStr+"\">")
		document.write("<div style=\"font-weight:bold; \" >"+this.name_.replace(preventHTML,'&lt;')+"</div><hr />");
		document.write("<div style=\"font-size:12px; text-align:right; \" >Posted at "+this.time_+", "+this.date_+"</div>");
		document.write("<div>"+ this.content_.replace(preventHTML,'&lt;')+"</div>");
		document.write("</div>");
	}

}