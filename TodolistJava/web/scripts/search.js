var searchResult;
var curX = 0;
var curN = 0;
var curQ = "";
var curF = "";
var isFirstTime = true;
var isUser = false;
var isCategory = false;
var isTugas = false;
var curUsername = "";


function saatload(q, filter, x, n, uname) {
	curQ = q;
	curF = filter;
        curUsername = uname;
        
	ajax_get("ajax/searchresult?q=" + q + "&filter=" + filter + "&x=" + x + "&n=" + n,function(xhr)
	{
			searchResult = JSON.parse(xhr.responseText); 
			updateContent();
	});
}

function isAssignee(Tugas, Username){
    isIt = false;
    for (var a = 0; a < Tugas.assignees.length; a++){
        if (Tugas.assignees[a].username == Username){
            isIt = true;
            break;
        }
    }
    return isIt;
}

function updateStatus(n,str) {
    if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		document.getElementById("stats").innerHTML=xmlhttp.responseText;
		}
	  }
          var x;
          if (n == "true"){
              x = 1;
          } else if (n == "false"){
              x = 0;
          }
	xmlhttp.open("GET","ajax/UpdateStatus?q="+str+"&n="+x,true);
	xmlhttp.send();
	
	return false;
}
    
function updateContent(){
                isFirstTime = false;
		var contentAdded = document.getElementById("SearchResultContent");
		var tempStr = "";
		curX = searchResult.x;
		curN = searchResult.n;
        
		if (searchResult.user.length != 0){
                        if (!isUser){
                            tempStr += "	<h2> User </h2>";	
                            isUser = true;
                        }
			var i = 0;

			do {
					tempStr += "	<div class=\"tugas\">";
					tempStr += "		<div> 		<img src=\"images/avatars/" + searchResult.user[i].avatar + "\" alt=\"" + searchResult.user[i].nama + "\" width=\"32\" height=\"32\" /> <strong>" + searchResult.user[i].fullName + "</strong> (<a href=\"profile.jsp?id=" + searchResult.user[i].username + "\">" + searchResult.user[i].username + "</a>)</div>";
					tempStr += "	</div>";
					i++;
			} while(i < searchResult.user.length);
		}
                
		if ((searchResult.category.length != 0)){
                        if (!isCategory){
                            tempStr += "	<h2> Category </h2>";
                            isCategory = true;
                        }
			var i = 0;
			do {
					tempStr += "	<div class=\"tugas\">";
					tempStr += "		<div><a href=\"#\">" + searchResult.category[i].nama + "</a></div>";
					tempStr += "	</div>";
					i++;
			} while(i < searchResult.category.length);
		}

		if (searchResult.tugas.length != 0){
                        if (!isTugas){
                            tempStr += "<h2> Task </h2>";
                            isTugas = true;
                        }
			var i = 0;

			do {
					tempStr += "	<div class=\"tugas\">";
					tempStr += "		<div><a href=\"tugas.jsp?id="+ searchResult.tugas[i].id +"\">" + searchResult.tugas[i].nama + "</a></div>";
					tempStr += "		<div>Submission: <strong>" + searchResult.tugas[i].tglDeadline + "</strong></div>";
					tempStr += "			Tags: ";
					tempStr += "			<ul class=\"tag\">";			 
												 
					var lastTN = searchResult.tugas[i].id;
					var j = 0;
					do {
						if (searchResult.tugas[i].tags != null){
							tempStr += 			"	<li>" + searchResult.tugas[i].tags[j] + " </li>";
						}
						j++;
					} while(j < searchResult.tugas[i].tags.length);
					
					tempStr += "			</ul>";
                                        if ((searchResult.tugas[i].pemilik.username == curUsername) || isAssignee(searchResult.tugas[i], curUsername)){
                                            if (searchResult.tugas[i].status == 0){
                                                    tempStr += "<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value," + searchResult.tugas[i].id +")\" value=\"" + searchResult.tugas[i].status + "\"></div>";
                                            } else {
                                                    tempStr += "<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value," + searchResult.tugas[i].id + ")\" value=\"" + searchResult.tugas[i].status + "\" checked></div>";
                                            }
                                        } else {
                                            if (searchResult.tugas[i].status == 0){
                                                    tempStr += "<div>Status : Belum Selesai</div>";
                                            } else {
                                                    tempStr += "<div>Status : Selesai</div>";
                                            }                                            
                                        }
					tempStr += "		</div>";
					tempStr += "	</div>";					

					i++;
			} while(i < searchResult.tugas.length);
		}
		contentAdded.innerHTML += tempStr;		
}    



document.onscroll = function(){
	if (curX != null && curN != null){
		if ((window.pageYOffset + window.innerHeight) == document.body.scrollHeight){
			saatload(curQ, curF, curX, curN, curUsername);
		}
	}
};