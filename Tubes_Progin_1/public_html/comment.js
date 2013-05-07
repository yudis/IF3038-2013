function postComment(idtask){
    try {
        var xmlHttp = new XMLHttpRequest();
    } catch(e) {
        try {
            var xmlHttp = new ActiveXObject(Msxml2.XMLHTTP);
        } catch(e) {
            console.log("Browser doesn't support AJAX");
        }
    }
    var xmlhttp = getXmlHttpRequest();
    var content = document.getElementById('ccontent').value;
    xmlHttp.open("GET", "comment.php?c="+content+'&i='+idtask, true);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var queryresult = xmlHttp.responseText;
            var pieces = queryresult.split(';');
            var parentTag = document.getElementById('comment_nonform'); //ini harusnya getelement id si task nya
            var childContainer = document.createElement('div');
            childContainer.setAttribute('id','comment'+ pieces[7]);
            var childImg = document.createElement('img');
            childImg.setAttribute('src', pieces[0]);
            childImg.setAttribute('width','30px');
            childImg.setAttribute('height','30px');
            childImg.setAttribute('padding','5px');
            var childNameDate = document.createElement('div');
            childNameDate.innerHTML = pieces[1]+', '+pieces[2]+'/'+pieces[3]+' -- '+pieces[4]+':'+pieces[5];
            childNameDate.style.color='black';
            var childContent = document.createElement('div');
            childContent.innerHTML = pieces[6];
            childContent.style.textAlign='justify';
            childContent.style.fontSize='8pt';
            childContent.style.color='black';
            var childDelete = document.createElement('a');
            childDelete.innerHTML = 'Delete';
            childDelete.setAttribute('id', 'deletecomment'+pieces[7]);
            childDelete.setAttribute('href', 'javascript:deleteComment('+pieces[7]+');');
            childDelete.style.fontSize='10pt';
            childDelete.style.color='black';
            childContainer.appendChild(childImg);
            childContainer.appendChild(childNameDate);
            childContainer.appendChild(childContent);
            childContainer.appendChild(childDelete);
            parentTag.appendChild(childContainer);
        }
    };
    xmlHttp.send(null);
}

function deleteComment(input){
    try {
        var xmlHttp = new XMLHttpRequest();
    } catch(e) {
        try {
            var xmlHttp = new ActiveXObject(Msxml2.XMLHTTP);
        } catch(e) {
            console.log("Browser doesn't support AJAX");
        }
    }
    var xmlhttp = getXmlHttpRequest();
    xmlHttp.open("GET", "deletecomment.php?id=" + input, true);
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var queryresult = xmlHttp.responseText;
            var removed = document.getElementById('comment'+queryresult);
                if ( removed !== null )
                {
                    while (removed.firstChild) {
                        removed.removeChild(removed.firstChild);
                    }
                }
        }
    };
    xmlHttp.send(null);
}