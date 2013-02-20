function setActiveStyleSheet(title) {
	var i, a, main;
	for(i = 0; (a = document.getElementsByTagName("link")[i]); i++) {
		if(a.getAttribute("rel").indexOf("style") != -1 && a.getAttribute("title")) {
			a.disabled = true;
			if(a.getAttribute("title") == title) a.disabled = false;
		}
	}
}

function getActiveStyleSheet() {
  var i, a;
  for(i=0; (a = document.getElementsByTagName("link")[i]); i++) {
	if(a.getAttribute("rel").indexOf("style") != -1 && a.getAttribute("title") && !a.disabled) return a.getAttribute("title");
  }
  return null;
}

function changeStyleSheet() {
	if (getActiveStyleSheet() == 'style1') {
		setActiveStyleSheet('style2');
	} else {
		setActiveStyleSheet('style1');
	}
}

//Vincentius Martin
function loginMenu(){
	var elmt = document.getElementById('droplogin');
        if(elmt.style.visibility=="hidden"){
            elmt.style.visibility = "visible";
        }else{
            elmt.style.visibility = "hidden";
        }
}