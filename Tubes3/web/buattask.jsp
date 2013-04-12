<%-- 
    Document   : buattask
    Created on : Apr 11, 2013, 10:27:47 AM
    Author     : Christianto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<html>
    <head>
        <title> Next | ADD TASK </title>
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/buattask.css">
        <link rel="stylesheet" href="css/calendar.css">
        <script src="js/calendar.js" > </script>
        <script>
            function name_valid(namaid) {
                if ((namaid.length > "25" ) || (namaid.match(/[!"#$%&'90*+,.:;<=>?@\^_`{|}~]/))) {
                    document.getElementById("namaicon").src="pict/canceled.png";
                } else {
                    document.getElementById("namaicon").src="pict/centang.png";
                }
            }
				
            function dead_validating(form) {
                var xmlhttp;
                if (window.XMLHttpRequest) {
                    xmlhttp = new XMLHttpRequest();				
                } else {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
                }
                
                xmlhttp.onreadystatechange = function(){
                    if (xmlhttp.readyState===4 && xmlhttp.status === 200)	{
                        //alert(xmlhttp.responseText);
                        if (xmlhttp.responseText === 1) {
                            document.getElementById("deadicon").src="pict/centang.png";
                        } else {
                            document.getElementById("deadicon").src="pict/canceled.png";                        
                        }
                    }
                };
                
                params = "tanggal=";
                params += escape(form.year.value+"-"+form.month.value+"-"+form.day.value+" "+form.hour.value+":"+form.minute.value+":00");                
                //alert(params);
                xmlhttp.open("POST","validasi_date",true);
                xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlhttp.send(params);
            }
			
            function tag_validating()
            {
                var tagid = document.buatugas.tag.value;
                
                if(tagid.match(/([ \t\r\n\v\f])/)){
                    document.getElementById("tagicon").src="pict/canceled.png";
                } else {
                    document.getElementById("tagicon").src="pict/centang.png";
                }
            }

            function validasi_file(place) {
                var ext=place.value.substring(place.value.indexOf(".")+1);
                if (ext==="jpeg" || ext === "avi" || ext==="pdf" || ext === "jpg" || ext === "mp4" || ext==="ogg") {
                    document.getElementById("attach_upload").innerHTML += place.value+";";
                    if (document.getElementById("file_upload").innerHTML !== "") {
                        document.getElementById("file_upload").innerHTML += ", "+place.value;
                    } else {
                        document.getElementById("file_upload").innerHTML += place.value;
                    }
                    place.value = "";
                } else {
                    alert("Ekstensi file tidak didukung web");
                    place.value = "";
                }
                //alert(place.value);
            }
			
            function prepare() {
                calendar.set("date");
                for (var i=0;i<=23;++i) {
                    document.getElementById("hour").innerHTML += "<option>"+i+"</option>";
                }
                
                for (var i=0;i<=59;++i) {
                    var sem = "";
                    if (i < 10) sem = "0";
                    sem += i;
                    document.getElementById("minute").innerHTML += "<option>"+sem+"</option>";
                }
                
                document.getElementById("username").value = localStorage.userLogin;
            }
            
            function auto_complete(text) {
                var xmlhttp;
                if (text === "") {
                    document.getElementById("autobox").value = "";                    
                } else {
                    if (window.XMLHttpRequest){
                        xmlhttp = new XMLHttpRequest();				
                    } else {
                        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
                    }
                    
                    xmlhttp.onreadystatechange = function(){
                        if (xmlhttp.readyState===4 && xmlhttp.status === 200)	{
                            //alert(xmlhttp.responseText);
                            var s = xmlhttp.responseText;
                            var n = s.indexOf("\n");
                            document.getElementById("autobox").value = "";
                            
                            while (n !== -1) {
                                //Ambil satu data komentar
                                var username = s.substring(0,n);
                                s = s.substring(n+1);
                                n = s.indexOf("\n");
     
                                //Tampilkan datanya
                                var tambah = username+" ";
                                document.getElementById("autobox").value += tambah+"\n";
                            }
                        }
                    };
                    
                    params = "assignee="+escape(text);
                    //alert(params);
                    xmlhttp.open("POST","auto_complete_user",true);
                    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                    xmlhttp.send(params);
                }
            }
	</script>
    </head>
    <body onload="prepare();">
        <!----------------------------HEADER FILE---------------------------->
        <%@ include file="header.jsp" %>
        
        <!-----------------------------BODY FILE-------------------------------->
        <div class="main">
            <div id="back" onclick="location.href='dashboard.jsp';"></div>            
            
            <div id="formtask">
                <div id = "judulform">
                </div>
                <form id="formtask2" enctype="multipart/form-data" method="POST" action="addTugas">
                    <label>NAMA TUGAS</label>
                    <input name="task_name" type="text" placeholder="task_name" onkeyup="name_valid(this.value);" />
                    <img src="pict/blank.png" alt="icon1" id="namaicon"  />                    
                    
                    <label>DEADLINE</label>
                    <input type="text" id="date" name="date" placeholder="2000-12-20">
                    <select name="hour" id="hour" onchange="dead_validating(this.parentNode);">
                    </select>-

                    <select name="minute" id="minute" onchange="dead_validating(this.parentNode);">
                    </select>  
                    <img src="pict/blank.png" alt="icon3" id="deadicon" />
                    
                    <label>ASSIGNEE</label>
                    <input type="textarea" name="assignee" placeholder="assignee"
                      title="Akhiri nama user dengan tanda /, jangan dipisah spasi"
                      onkeyup="auto_complete(this);" value="">
                    <img src="pict/blank.png" alt="icon4" id="asicon" />
                      
                    <input id="autobox" disabled></input>
                    
                    <label>TAG</label>
                    <input type="textarea" name="catname" placeholder="tag" value="">
                    
                    <label>ATTACHMENT</label>
                    <div id="attach_upload">                            
                    </div>                            
		    <img src="pict/blank.png" alt="icon2" id="attaicon"  />                    
                    
                    Upload File: <input type="file" name="file" id="file" onchange="validasi_file(this);">
                    <input type="file" name="file_upload" id="file_upload" multiple>
                    <br>
                    <input class= "submitreg" name="submit" type="submit" value="Submit">
                    <input type="text" name="id_kategori" id="id_kategori" 
                           value="<%out.print(request.getParameter("id_kategori"));%>">
                    <input type="text" name="username" id="username"
                           value="">
                </form>
            </div>
            <div id="calendar" class="calendar-box"></div>
        </div>
        
        <!---------------------------------FOOTER FILE------------------------------------>
        <div class="footer">
            Copyright © Ahmad Faiz - Fandi Pradana - Sigit Aji
        </div>
        
    </body>
</html>
