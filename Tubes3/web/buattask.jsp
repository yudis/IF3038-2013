<%-- 
    Document   : buattask
    Created on : Apr 11, 2013, 10:27:47 AM
    Author     : Christianto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <title> Next | ADD TASK </title>
        <!--<link rel="stylesheet" href="css/css.css">-->
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
	
            function isKabisat(x) {
                if (x % 100 != 0) {
                    return (x % 4 == 0);
                } else {
                    return (x % 400 == 0);
                }
            }
        
            function dead_validating(date) {
                var s = date.value;
                var regex = new RegExp("^[0-9]{4}-((0[1-9])|(1[0-2]))-([0-2][0-9]|3[0-1])$");
                alert(s);
                if (regex.test(x)) {
                    var d = s.substring(5,7);
                    var m = s.substring(8,10);
                    var y = parseInt(s.substring(0,4));
                    
                    if (((m=="02"||m=="04"||m=="06"||m=="09"||m=="11")&&(d == "31"))||(m=="02" && d=="30")||
                            (m=="02" && d=="29" && !isKabisat(y))) {
                        document.getElementById("deadicon").src = "pict/canceled.png";
                    } else {
                        document.getElementById("namaicon").src="pict/centang.png";                        
                    }
                } else {
                    document.getElementById("deadicon").src = "pict/canceled.png";
                }
            }
			
            function tag_validating(haha)
            {
                var tagid = haha.value;
                
                if(tagid.length == 0){
                    document.getElementById("tagicon").src="pict/canceled.png";
                } else {
                    document.getElementById("tagicon").src="pict/centang.png";
                }
            }

            function validasi_file(place) {
                var ext=place.value.substring(place.value.indexOf(".")+1);
                if (ext === "" || ext==="jpeg" || ext === "avi" || ext==="pdf" || ext === "jpg" || ext === "mp4" || ext==="ogg") {
                    document.getElementById("attaicon").src = "pict/centang.png";
                } else {
                    alert("Ekstensi file tidak didukung web");
                    document.getElementById("attaicon").src = "pict/canceled.png";                    
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
            }
            
            function auto_complete(text) {
                var xmlhttp;
                if (text === ""){ 
                    if (document.getElementById("autobox").value !== "Tidak ada user dengan nama itu") {
                        document.getElementById("autobox").value = "";
                        document.getElementById("asicon").src="pict/centang.png"; 
                    }
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
                                var tambah = username;
                                document.getElementById("autobox").value += tambah+"\n";
                            }
                            
                            if (document.getElementById("autobox").value === "") {
                                document.getElementById("asicon").src="pict/canceled.png";
                                document.getElementById("autobox").value = "Tidak ada user dengan nama itu";
                            } else {
                                document.getElementById("asicon").src="pict/blank.png"; 
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
                    <input type="text" id="date" name="date" placeholder="2000-12-20" onchange="dead_validating(this);">
                    <select name="hour" id="hour">
                    </select>-

                    <select name="minute" id="minute">
                    </select>  
                    <img src="pict/blank.png" alt="icon3" id="deadicon" />
                    
                    <label>ASSIGNEE</label>
                    <input type="textarea" name="assignee" placeholder="assignee"
                      title="Akhiri nama user dengan tanda /, jangan dipisah spasi"
                      onkeyup="auto_complete(this.value.substring(this.value.lastIndexOf('/')+1));" value=""
                      autocomplete="off">
                    <img src="pict/blank.png" alt="icon4" id="asicon" />
                      
                    <input id="autobox" disabled></input>
                    
                    <label>TAG</label>
                    <input type="textarea" name="tag" id="tag" placeholder="tag" value="" onkeyup="tag_validating(this);">
		    <img src="pict/blank.png" alt="icon2" id="tagicon"  />                     
                    
                    <label>ATTACHMENT</label>                          
                    Upload File: <input type="file" name="file" id="file" onchange="validasi_file(this);">
		    <img src="pict/blank.png" alt="icon2" id="attaicon"  />  
                    
                    <input type="text" name="id_kategori" id="id_kategori"
                           value="<%out.print(request.getParameter("id_kategori"));%>">
                    <input type="text" name="username" id="username"
                           value="<%out.print(session.getAttribute("userLoginSession"));%>">
                    <input class= "submitreg" name="submit" type="submit" value="Submit">
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
