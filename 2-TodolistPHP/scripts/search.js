var searchResult;
var curX = 0;
var curN = 0;
var curQ = "";
var curF = "";

function saatload(q, filter, x, n) {
	curQ = q;
	curF = filter;
	
	ajax_get("ajax/searchresult.php?q=" + q + "&filter=" + filter + "&x=" + x + "&n=" + n,function(xhr)
	{
			searchResult = JSON.parse(xhr.responseText);
					// var contentAdded = document.getElementById("SearchResultContent");
					// contentAdded.innerHTML += xhr.responseText;
			updateContent();
	});
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
	xmlhttp.open("GET","ajax/updateStatus.php?q="+str+"&n="+n,true);
	xmlhttp.send();
	
	return false;
}

function updateContent(){
		var contentAdded = document.getElementById("SearchResultContent");
		var tempStr = "";
		curX = searchResult.x;
		curN = searchResult.n;
		if ((searchResult.category != null) && (searchResult.category != false)){
			tempStr += "	<h2> Category </h2>";
			var i = 0;
			do {
					tempStr += "	<div class=\"tugas\">";
					tempStr += "		<div><a href=\"#\">" + searchResult.category[i].nama + "</a></div>";
					tempStr += "	</div>";
					i++;
			} while(i < searchResult.category.length);
		}

		if ((searchResult.tugas != null) && (searchResult.tugas != false)){
			tempStr += "<h2> Task </h2>";
			var i = 0;

			do {
					tempStr += "	<div class=\"tugas\">";
					tempStr += "		<div><a href=\"tugas.php?id="+ searchResult.tugas[i].id +"\">" + searchResult.tugas[i].nama + "</a></div>";
					tempStr += "		<div>Submission: <strong>" + searchResult.tugas[i].tgl_deadline + "</strong></div>";
					tempStr += "			Tags: ";
					tempStr += "			<ul class=\"tag\">";			 
												 
					var lastTN = searchResult.tugas[i].id;
					
					do {
						if (searchResult.tugas[i].tag != null){
							tempStr += 			"	<li>" + searchResult.tugas[i].tag + " </li>";
						}
						i++;
						if  (i == searchResult.tugas.length){
							break;
						}
					} while(lastTN == searchResult.tugas[i].id);
					i--;
					
					tempStr += "			</ul>";
					if (searchResult.tugas[i].status == 0){
						tempStr += "<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value," + searchResult.tugas[i].id +")\" value=\"" + searchResult.tugas[i].status + "\"></div>";
					} else {
						tempStr += "<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value," + searchResult.tugas[i].id + ")\" value=\"" + searchResult.tugas[i].status + "\" checked></div>";
					}
					
					tempStr += "		</div>";
					tempStr += "	</div>";					
					
					// tempStr += "			</ul>";
					// tempStr += "			<div>Status " + searchResult.tugas[i].status + "</div>";
					// tempStr += "		</div>";
					// tempStr += "	</div>";
					i++;
			} while(i < searchResult.tugas.length);
		}

				
		if ((searchResult.user != null) && (searchResult.user != false)){
			tempStr += "	<h2> User </h2>";			
			var i = 0;

			do {
					tempStr += "	<div class=\"tugas\">";
					tempStr += "		<div> 		<img src=\"images/avatars/" + searchResult.user[i].avatar + "\" alt=\"" + searchResult.user[i].full_name + "\" width=\"32\" height=\"32\" /> <strong>" + searchResult.user[i].full_name + "</strong> (<a href=\"profileM.php?username=" + searchResult.user[i].username + "\">" + searchResult.user[i].username + "</a>)</div>";
					tempStr += "	</div>";
					i++;
			} while(i < searchResult.user.length);
		}
		
		contentAdded.innerHTML += tempStr;		
}

// alert("abc");

// document.getElementById('footerpage').addEventListener(
    // 'scroll',
    // function()
    // {
        // var scrollTop = document.getElementById('footerpage').scrollTop;
        // var scrollHeight = document.getElementById('footerpage').scrollHeight; // added
        // var offsetHeight = document.getElementById('footerpage').offsetHeight;
        //// var clientHeight = document.getElementById('box').clientHeight;
        // var contentHeight = scrollHeight - offsetHeight; // added
        // if (contentHeight <= scrollTop) // modified
        // {
			// alert("aaa");
            // Now this is called when scroll end!
        // }
    // },
     // false
// )



document.onscroll = function(){
	if (curX != null && curN != null){
		if ((window.pageYOffset + window.innerHeight) == document.body.scrollHeight){
			saatload(curQ, curF, curX, curN);
		}
	}
};