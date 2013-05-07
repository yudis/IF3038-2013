function fileallowed(){
    //	alert("masuk");
    var file = document.getElementById('avatar');
    var ext = file.value.match(/\.([^\.]+)$/)[1];
	
    switch(ext)
    {
        case 'jpg':
        case 'jpeg':
        case 'png':
        case 'ogg':
            break;
        default:
            file.value='';
            alert('Tipe file tidak diizinkan.\nSilahkan ulangi masukan');
           
    }
    
}

function ambildata(ID,cnt){

    var xmlhttp;
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
        if(xmlhttp.readyState == 4){
            var ss = document.getElementById('Komentar');
            var str =xmlhttp.responseText;
            ss.innerHTML += str;
        }
    }
    xmlhttp.open("GET", 'Task?ID=' + ID+ '&continue=' + cnt, true);
    xmlhttp.send();
}

function removeComment(id,idcomment,jumcom){

    var xmlhttp;
    var jum;
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
        if(xmlhttp.readyState == 4){
            document.getElementById("Komentar").innerHTML="";                  
            scroll();
            ambildata(id,'false');
              
          
        }
    }

    var queryString = "idcomment="+idcomment;
    xmlhttp.open("POST", 'removeComment', true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send(queryString);
}

function scroll(){
    document.onscroll = function(){
        var data=document.getElementById("ID").innerHTML;
        //alert("+: "+(window.pageYOffset + window.innerHeight)+"hegi:"+document.body.offsetHeight);
        if ((window.scrollY + window.innerHeight) >= document.body.offsetHeight){
            ambildata(data, 'true');
        }
		
		
    }
}

function addcomment(username,id){
    var xmlhttp;
    var komen=document.getElementById("commentfield").value;
    document.getElementById("commentfield").value="";
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
        if(xmlhttp.readyState == 4){
            document.getElementById("Komentar").innerHTML="";
            scroll();
            ambildata(xmlhttp.responseText,'false');
              
        }
    }
	  
    var queryString = "comment="+komen+"&usernamecur="+username+"&id="+id;
    xmlhttp.open("POST", 'addComment', true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send(queryString);

}
function editTask(jumlahAssignee,ID)
{
    document.getElementById("edit").innerHTML="<b>Save</b>";
    document.getElementById("edit").setAttribute('onclick','save('+jumlahAssignee+','+ID+')');
    document.getElementById("tanggal").setAttribute('onclick', '');
    var i=0;
        
    for(i;i<jumlahAssignee;i++) {
        if(document.getElementById("r"+i)!=null)
        {
            document.getElementById("r"+i).setAttribute("style","visibility:visible; position:relative; left:3px;");
        }
    }
    document.getElementById("inputtag").setAttribute("style","visibility:visible;position:absolute; top:15px; left:154px;");	
    document.getElementById("assignee").innerHTML+="<br><input id=\"asignee\" type=\"text\" placeholder=\"assignee\" onkeyup='searchSuggest(this.id)'></input>";
}
function save(jumlahA,ID){
    
    document.getElementById("edit").innerHTML="<b>Edit</b>";	
    document.getElementById("tanggal").setAttribute('onclick', 'return false');
    document.getElementById("edit").setAttribute('onclick','editTask('+jumlahA+','+ID+')');
    document.getElementById("inputtag").setAttribute("style","visibility:hidden;position:absolute; top:15px; left:154px;");
    document.getElementById("asignee").setAttribute("style","visibility:hidden;");
    var assignee=document.getElementById("asignee").value;
    
    if(assignee!="")
    {
                
        var jumlah=document.getElementById("jumlahA").innerHTML;
        jumlah++;
        document.getElementById("jumlahA").innerHTML=jumlah;
        document.getElementById("edit").setAttribute('onclick','editTask('+jumlah+')');
    }
    var i=0;
    document.getElementById("asignee").value="";
    document.getElementById("assignee").innerHTML="";
    //document.getElementById("anggota").innerHTML+="<div id=\""+assignee+"\"><a  href=\"profile.php?username="+assignee+"\">"+assignee+"</a><a id=\"r"+(jumlahA++)+"\" href=\"#\" style=\"visibility:hidden\" onclick=\"removeA('"+assignee+"')\">(remove)</a><br></div>";
    for(i;i<jumlahA;i++) {
        if(document.getElementById("r"+i)!=null)
        {
            document.getElementById("r"+i).setAttribute("style","visibility:hidden; position:relative; left:3px;");
        }
            
    }
    var tag=document.getElementById("inputtag").value;
    var deadline=document.getElementById("deadline").value;
    
    var n=deadline.split("-"); 
    deadline=n[2]+"-"+n[1]+"-"+n[0];
    document.getElementById("inputtag").value="";
    if(tag=="" && deadline=="" && assignee=="")
    {
    }
    else
    {
        var xmlhttp;
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
		  
            if(xmlhttp.readyState == 4){
                if(deadline!="" && tag!="" && assignee!="")
                {
                    var total=xmlhttp.responseText.split(",");
                    var cal=total[0].split("-");
                    cal=cal[2]+"-"+cal[1]+"-"+cal[0];
                              
                    document.getElementById("deadline").setAttribute('value', cal);
                    document.getElementById("data").innerHTML = total[1];
                    document.getElementById("anggota").innerHTML+="<div id=\""+total[2] +"\"><a  href=\"profile.jsp?username="+total[2]+"\">"+total[2]+"</a><a id=\"r"+jumlahA+"\" href=\"#\" style=\"visibility:hidden\" onclick=\"removeA('"+total[2]+"')\">(remove)</a><br></div>";                              
                }
                else if(deadline!="" && tag!="")
                {
                    var total=xmlhttp.responseText.split(",");
                    var cal=total[0].split("-");
                    cal=cal[2]+"-"+cal[1]+"-"+cal[0];
                    document.getElementById("deadline").setAttribute('value', cal);
                    document.getElementById("data").innerHTML = total[1];
                }
                else if(deadline!="" && assignee!="")
                {
                    var total=xmlhttp.responseText.split(",");
                    var cal=total[0].split("-");
                    cal=cal[2]+"-"+cal[1]+"-"+cal[0];
                    document.getElementById("deadline").setAttribute('value', cal);
                    document.getElementById("anggota").innerHTML+="<div id=\""+total[1] +"\"><a  href=\"profile.jsp?username="+total[1]+"\">"+total[1]+"</a><a id=\"r"+jumlahA+"\" href=\"#\" style=\"visibility:hidden\" onclick=\"removeA('"+total[1]+"')\">(remove)</a><br></div>";			  
                }
                else if(deadline!="")
                {
                    var total=xmlhttp.responseText;
                    var cal=total.split("-");
                    cal=cal[2]+"-"+cal[1]+"-"+cal[0];
                    document.getElementById("deadline").setAttribute('value', cal);
                                  
                }
                          
            //window.location.reload();
            }

        }
        var queryString = "tag="+tag+"&deadline="+deadline+"&assignee="+assignee+"&ID="+ID;
        
        xmlhttp.open("POST", 'edittask', true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send(queryString);
    }
}
function removeA(username)
{
    var xmlhttp;
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
  
        if(xmlhttp.readyState == 4){
            if(xmlhttp.responseText=="sukses")
            {
                document.getElementById(username).innerHTML="";
            //parseInt(document.getElementById("jumkom").innerHTML, radix);
            }
	
        }

    }
    var queryString = "username="+username;
    xmlhttp.open("POST", 'removeAssignee', true)
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    xmlhttp.send(queryString);
}

function updateedit(id){
    var xmlhttp;
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
  
        if(xmlhttp.readyState == 4 && xmlhttp.status==200){
            if(xmlhttp.responseText=="true")
            {
                document.getElementById("edit").setAttribute("style","visibility:visible;");
            //parseInt(document.getElementById("jumkom").innerHTML, radix);
            }
	
        }

    }
    var queryString = "id="+id;
    xmlhttp.open("POST", 'editbutton', true)
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    xmlhttp.send(queryString);
}