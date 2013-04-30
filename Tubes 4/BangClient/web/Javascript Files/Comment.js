function addComment(idtask){
    try {
        var xmlHttp = new XMLHttpRequest();
    } catch(e) {
        try {
            var xmlHttp = new ActiveXObject(Msxml2.XMLHTTP);
        } catch(e) {
            console.log("Browser doesn't support AJAX");
        }
    }
    var content = document.getElementById('cbox').value;
    xmlHttp.open("GET", "BangServlet?action=addComment&c="+content+'&it='+idtask, true);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var message = xmlHttp.responseText;
            var msplit = message.split(':');
            var parentTag = document.getElementById('commentlist');
            var childContainer = document.createElement('div');
				childContainer.setAttribute('id','comment'+ msplit[7]);
            var childImg = document.createElement('img');
				childImg.setAttribute('class', 'cavatar');
				childImg.setAttribute('src', msplit[0]);
				childImg.setAttribute('width','30px');
				childImg.setAttribute('height','30px');
            var childDate = document.createElement('div');
				childDate.setAttribute('class', 'ctimestamp');
				childDate.innerHTML = msplit[4]+':'+msplit[5]+' - '+msplit[3]+'/'+msplit[2];
            var childContent = document.createElement('div');
				childContent.setAttribute('class', 'ccontent');
				childContent.innerHTML = msplit[6];
            var childDelete = document.createElement('input');
                                childDelete.setAttribute('type', 'button');
				childDelete.setAttribute('class', 'cdelete');
				childDelete.setAttribute('value', 'Delete');
				childDelete.setAttribute('onclick', 'delComment('+msplit[7]+');');
            childContainer.appendChild(childImg);
            childContainer.appendChild(childDate);
            childContainer.appendChild(childContent);
            childContainer.appendChild(childDelete);
            parentTag.appendChild(childContainer);
            document.getElementById('cbox').value = "";
        }
    };
    xmlHttp.send(null);
}
function delComment(idcomment){
    try {
        var xmlHttp = new XMLHttpRequest();
    } catch(e) {
        try {
            var xmlHttp = new ActiveXObject(Msxml2.XMLHTTP);
        } catch(e) {
            console.log("Browser doesn't support AJAX");
        }
    }
    xmlHttp.open("GET", "BangServlet?action=delComment&ic=" + idcomment, true);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            var queryresult = xmlHttp.responseText;
            var removed = document.getElementById('comment'+queryresult);
            removed.innerHTML = "";
        }
    };
    xmlHttp.send(null);
}