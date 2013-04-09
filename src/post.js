document.getElementById("ap-youtube").innerHTML = "<iframe id=\"video-container\"></iframe>";
document.getElementById("video-container").setAttribute('width','461');
document.getElementById("video-container").setAttribute('height','346');
document.getElementById("video-container").setAttribute('frameborder','0');

var title;
var artikel;
var desk;
var youtube;

document.getElementById("artikel").style.display="none";
document.getElementById("avatar").style.display="none";
document.getElementById("youtube").style.display="none";
document.getElementById("afterpost").style.display="none";
									
document.getElementById("sex-link").checked=false;
document.getElementById("sex-gambar").checked=false;
document.getElementById("sex-video").checked=false;
						
function linkClicked() {
	document.getElementById("artikel").style.display="block";
	document.getElementById("avatar").style.display="none";
	document.getElementById("youtube").style.display="none";
}

function gambarClicked() {
	document.getElementById("artikel").style.display="none";
	document.getElementById("avatar").style.display="block";
	document.getElementById("youtube").style.display="none";
}

function videoClicked() {
	document.getElementById("artikel").style.display="none";
	document.getElementById("avatar").style.display="none";
	document.getElementById("youtube").style.display="block";
}


function postContent() {
	
	if (document.getElementById("judul").value == "") {
		alert('Please give a title to your post');	
	} else {
		title = document.getElementById("judul").value;
		if (document.getElementById("sex-link").checked == true) {
			if (document.getElementById("kontenartikel").value == "") {
				alert('Do not leave the box empty');
			} else {
				artikel = document.getElementById("kontenartikel").value;
				cekurl = new RegExp("^(http:\/\/www.|https:\/\/www.|ftp:\/\/www.|www.){1}([0-9A-Za-z]+\.)");
				if (!cekurl.test(artikel)) {
					alert('Your URL link does not valid');
				} else {
					desk = document.getElementById("kontendeskripsi").value;
					nextStep(1);
				}
			}
		} else if (document.getElementById("sex-gambar").checked == true) {
			if (document.getElementById("kontenavatar").value == "") {
				alert('Please choose a picture to post');
			} else {
				youtube = document.getElementById("kontenyoutube").value;
				nextStep(2);
			}

		} else if (document.getElementById("sex-video").checked == true) {
			if (document.getElementById("kontenyoutube").value == "") {
				alert('Please insert a Youtube link to post');
			} else {
				nextStep(3);
				
			}

		} else {
			alert('Please choose a type before posting');
		}
	}
}

function nextStep(opt) {
	document.getElementById("headerpostkonten").style.display="none";
	document.getElementById("postkonten").style.display="none";
	document.getElementById("afterpost").style.display="block";
	document.getElementById("apjudul").innerHTML = title;
					
	document.getElementById("ap-artikel").style.display="none";
	document.getElementById("ap-avatar").style.display="none";
	document.getElementById("ap-youtube").style.display="none";
	
	if (opt==1) {
		document.getElementById("ap-kontenartikel").innerHTML = "<a href=\"" + artikel + "\">" + artikel + "</a>";
		document.getElementById("ap-kontendeskripsi").innerHTML = desk;
		document.getElementById("ap-artikel").style.display="block";
		document.getElementById("ap-kontenartikel").style.display="block";
	} else if (opt==2) {
		document.getElementById("ap-avatar").style.display="block";
	} else if (opt==3) {
		var lutube = document.getElementById("kontenyoutube").value;
		var jlube = "";
		for (i = 31; i < lutube.length; i++) {
			jlube += lutube.charAt(i);
		}
		document.getElementById("video-container").setAttribute('src','http://www.youtube.com/embed/'+jlube+'?rel=0');
		document.getElementById("ap-youtube").style.display="block";
	}			
}

function refreshPage() {
	document.getElementById("headerpostkonten").style.display="block";
	document.getElementById("afterpost").style.display="none";
	document.getElementById("postkonten").style.display="block";	
}