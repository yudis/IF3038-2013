<%-- 
    Document   : rinciantugas
    Created on : Apr 5, 2013, 4:56:10 PM
    Author     : Christianto
--%>

<%@page import="RincianTugas.DataAwal"%>
<%@page import="javax.xml.crypto.Data"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%DataAwal data = new DataAwal(Integer.parseInt(request.getParameter("id_tugas")));%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/buattask.css">
        <link rel="stylesheet" href="css/calendar.css">
        <title>Rincian Tugas : <%out.println(data.getNama());%> </title>
        <script type="text/JavaScript" src="js/calendar.js"></script>
        <script type="text/javascript">
            function submit_comment(form) {
                if (form.comment.value !== "") {
                    var xmlhttp;
                    if (window.XMLHttpRequest){
                        xmlhttp = new XMLHttpRequest();
                    } else {
                        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
                    }
                    
                    xmlhttp.onreadystatechange = function(){
                        if (xmlhttp.readyState===4 && xmlhttp.status===200) {
                            //alert(xmlhttp.responseText);
                            jumlah_komentar();
                        }
                    };

                    var id_tugas = "-1";
                    var c = window.location.search.indexOf("id_tugas=");
                    if (c !== -1) {
                        id_tugas = window.location.search.substring(c+9);
                    } 

                    params = "komentar="+escape(form.comment.value)+"&user="+localStorage.userLogin+"&id_tugas="+id_tugas;
                    xmlhttp.open("POST","tambah_komentar",true);                
                    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                    xmlhttp.send(params);
                    form.comment.value = "";
                    //alert("OK");
                }
            }
        
            function hapus_komentar(comment) {
                var xmlhttp;
                if (window.XMLHttpRequest){
                    xmlhttp = new XMLHttpRequest();
                } else {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
                }
                
                xmlhttp.onreadystatechange = function(){
                    if (xmlhttp.readyState===4 && xmlhttp.status===200)	{
                        alert(xmlhttp.responseText);
                        jumlah_komentar();
                    }
                };

                var id_tugas = "-1";
                var c = window.location.search.indexOf("id_tugas=");
                if (c !== -1) {
                    id_tugas = window.location.search.substring(c+9);
                } 

                var n = comment.innerHTML.indexOf(" ");
                var tanggal = comment.innerHTML.substring(n+1,n+20);
                
                params = 'tanggal='+tanggal+'&user='+localStorage.userLogin+'&id_tugas='+id_tugas;
                xmlhttp.open("POST","hapus_komentar",true);                
                xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlhttp.send(params);
                //alert(params);
            }
        
            function validasi_file(place) {
                var ext=place.value.substring(place.value.indexOf(".")+1);
                if (ext==="jpeg" || ext === "avi" || ext==="pdf" || ext === "jpg") {
                    document.getElementById("attach_upload").innerHTML += place.value+";";
                    place.value = "";
                } else {
                    alert("Ekstensi file tidak didukung web");
                    place.value = "";
                }
                //alert(place.value);
            }
                
            function ambil_komentar() {
                var xmlhttp2;
                if (window.XMLHttpRequest) {
                    xmlhttp2 = new XMLHttpRequest();                
                } else {
                    xmlhttp2 = new ActiveXObject("Microsoft.XMLHTTP"); 
                }

                var id_tugas = "-1";                
                xmlhttp2.onreadystatechange = function() {
                    if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                        var s = xmlhttp2.responseText;
                        var n = s.indexOf("\n");
                        daftar_komentar = document.getElementById("tempat_komentar");
                        daftar_komentar.innerHTML = "";
                        //alert(localStorage.userLogin);
                        
                        while (n !== -1) {
                            //Ambil satu data komentar
                            var username = s.substring(0,n);
                            s = s.substring(n+1);
                            n = s.indexOf("\n");
                            
                            var tanggal = s.substring(0,n);
                            s = s.substring(n+1);
                            n = s.indexOf("\n");

                            var komentar = unescape(s.substring(0,n));
                            s = s.substring(n+1);
                            n = s.indexOf("\n");

                            var avatar = unescape(s.substring(0,n));
                            s = s.substring(n+1);
                            n = s.indexOf("\n");
                            
                            //Tampilkan datanya
                            var tambah = '<div id = "daftarkomen">';
                            tambah += '<div class="gambar_user"><img src="'+avatar+'"></div>';
                            tambah += '<div class="data_komentar">';
                            tambah += username + " " + tanggal + " :";
                            
                            if (username === localStorage.userLogin) {
                                tambah += '<input type="button" class="hapus_comment" value="delete" onclick="hapus_komentar(this.parentNode)">';
                            }
                            
                            tambah += "<br>"+komentar;
                            tambah += '</div>';
                            tambah += "</div>";
                            daftar_komentar.innerHTML += tambah+"\n";
                        }
                    }
                };
            
                //Untuk mengambil daftar komentar
                var c = window.location.search.indexOf("id_tugas=");
                if (c  !== -1) {
                    id_tugas = window.location.search.substring(c+9);
                }                 
                var params = "id_tugas="+id_tugas+"&start="+document.getElementById("halaman_komentar").value;
                xmlhttp2.open("POST","ambil_komentar",true);
                xmlhttp2.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlhttp2.send(params);
            }
        
            function jumlah_komentar() {
                var xmlhttp;
                if (window.XMLHttpRequest){
                    xmlhttp = new XMLHttpRequest();				
                } else {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
                }
                
                xmlhttp.onreadystatechange = function(){
                    if (xmlhttp.readyState===4 && xmlhttp.status === 200)	{
                        //alert(xmlhttp.responseText);
                        document.getElementById("max_komentar").value=xmlhttp.responseText;
                        document.getElementById("halaman_komentar").value=xmlhttp.responseText;                        
                        ambil_komentar();
                    }
                };
                
                var id_tugas = "-1";
                var c = window.location.search.indexOf("id_tugas=");
                if (c !== -1) {
                    id_tugas = window.location.search.substring(c+9);
                }                 
                params = "id_tugas="+id_tugas;
                xmlhttp.open("POST","jumlah_komentar",true);
                xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlhttp.send(params);
                //alert("HOHOHO");
            }
        
            function ubah_hal(i) {
                var n = parseInt(document.getElementById("halaman_komentar").value)+i;
                if (n > 0 && n <= document.getElementById("max_komentar").value) {
                    document.getElementById("halaman_komentar").value = n;
                    ambil_komentar();
                }
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
        
            function showEdit(){
                if (document.getElementById("detailedit").innerHTML !== "") {
                    document.getElementById("detail").innerHTML=document.getElementById("detailedit").innerHTML;
                    document.getElementById("detailedit").innerHTML = "";
                    calendar.set("date");
                }
            }
                        
            function hapus_task() {
                if (localStorage.userLogin !== <%out.print(data.getCreator());%>) {
                    alert("Anda bukan pembuat tugas");
                } else {
                    var xmlhttp;
                    if (window.XMLHttpRequest){
                        xmlhttp = new XMLHttpRequest();				
                    } else {
                        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
                    }

                    xmlhttp.onreadystatechange = function(){
                        if (xmlhttp.readyState===4)	{
                            //alert(xmlhttp.responseText);
                        }
                    };

                    var id_tugas = "-1";
                    var c = window.location.search.indexOf("id_tugas=");
                    if (c !== -1) {
                        id_tugas = window.location.search.substring(c+9);
                    }     

                    var params = "id_tugas="+id_tugas;

                    //alert(params);
                    xmlhttp.open("POST","edit_detail_tugas.php",true);
                    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                    xmlhttp.send(params);                    
                }
            }
        </script>        
        
    </head>
    <body onload="jumlah_komentar();">
        <%-- Mulai daerah header buatan Jo--%>
        <%--<jsp:include page="header.jsp" flush="true" />--%>
        
        <!-------------------------------BODY HALAMAN------------------------------------->
        <div class="main">
            <div id="edit" onclick="showEdit();">
            </div>
            <div>
                <form>
                    <input type="button" id="back" value="" onclick="location.href='dashboard.jsp';">
                </form>
            </div>
            <div id="hapus" onclick="hapus_task();"></div>

            <div id="rinciantugas">
                <div id="judultugas"><%out.println(data.getNama());%></div>
                <div id="detail">
                    <label>NAMA KATEGORI</label>
                    <a id="kategori"><%out.println(data.getKategori());%></a>
                    
                    <label>STATUS TUGAS</label>
                    <a id="status"><%
                        if (data.getStatus() == 1) {
                            out.println("DONE");
                        } else {
                            out.println("IN THE PROCESS");
                        }
                    %></a>

                    <label>DEADLINE</label>
                    <a id="deadline"><%out.println(data.getDeadline());%></a>
                    
                    <label>TASK CREATOR</label>
                    <a id="creator"><%out.println(data.getCreator());%></a>
                    
                    <label>ASSIGNEE</label>
                    <div id="assignee">
                    <%
                        String[] assignee = data.getAssignee();
                        for (int i=0;i<assignee.length;++i) {
                            out.println("<a href='profile.jsp?username="+assignee[i]+"'>"+assignee[i]+"</a><br>");
                        }
                    %>
                    </div>
                    
                    <label>TAG</label>
                    <div id="tag"><%out.println(data.getTag());%></div>
  
                    <label>ATTACHMENT</label>
                    <div id="attach">
                    <%
                        String[] attachment = data.getAttachment();
                        for (int i=0;i<attachment.length;++i) {
                            out.println("<a href="+attachment[i]+">"
                                    +attachment[i].substring(attachment[i].lastIndexOf('/')+1)+"</a>");
                            String ext = attachment[i].substring(attachment[i].lastIndexOf('.')+1);
                            
                            if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png")) {
                                out.println("<div class=\"attach_image\"><img src=\""+attachment[i]+"\"></div>");
                            } else if (ext.equals("mp4")) {
                                out.println("<div class=\"attach_image\"></div>");
                            }
                            out.println("<br>");
                        }
                    %>
                    </div>
                    <!--           
                            tambah2 += "<label>ATTACHMENT</label>";
                            tambah2 += '<div id="attach_upload">';                            
                            
                            var sem1 = attachment.indexOf(";");
                            while (sem1 != -1) {
                                var file = attachment.substring(0,sem1);
                                var ext = file.substring(file.lastIndexOf(".")+1);
                                
                                tambah += '<a href="'+file+'">'+file.substring(file.lastIndexOf("/")+1)+'</a>';                            
                                if (ext == 'jpeg' || ext == 'jpg') {
                                    tambah += '<div class="attach_image"><img src="'+file+'"></div>';
                                } else if () {

                                }

                                tambah2 += '<a href="'+file+'">'+file.substring(file.lastIndexOf("/")+1)+'</a>';
                                
                                attachment = attachment.substring(sem1+1);
                                sem1 = attachment.indexOf(";");
                            }
                            tambah += '</div>';

                            tambah2 += '</div>';
                    -->
                </div>
                <div id ="detailedit">
                    <label>NAMA KATEGORI</label>
                    <a id="kategori"><%out.println(data.getKategori());%></a>
                    
                    <form action="edit_detail_tugas" method="POST" enctype="multipart/form-data">
                        <label>STATUS TUGAS</label>
                        <input type="checkbox" name="status" value="done"<%if (data.getStatus()==1) out.println("checked");%>> 
                        
                        <label>DEADLINE</label>
                        <input type="text" name="date" id="date" value ="<%out.println(data.getDeadline().substring(0,10));%>"> 

                        <label>ASSIGNEE</label>
                        <input type="textarea" name="assignee" placeholder="assignee"
                         title="Akhiri nama user dengan tanda /, jangan dipisah spasi"
                         onkeyup="auto_complete(this.value.substring(this.value.lastIndexOf('/')+1));"
                         value="<%
                            for (int i=0;i<assignee.length;++i) {
                                out.print(assignee[i] + "/");
                            }
                         %>">
                        <input id="autobox" disabled>
                        
                        <label>TAG</label>
                        <input type="textarea" name="catname" placeholder="tag" value="<%out.println(data.getTag());%>">
                        
                        <label>ATTACHMENT</label>
                        <input type="file" name="file" id="file" onchange="validasi_file(this);" multiple>
                        
                        <input class="submitreg" name="submit" type="submit" value="submit">
                    </form>
                </div>
            </div>
            <div id="komen">
                <div id="tempat_komentar">
                  
                </div>
                <div id="paginasi_komentar">
                    <form name="form_paginasi">
                        <input class="submitreg" name="prev" type="button" onclick="ubah_hal(-1);" value=" < prev">
                        <input type="text" id="halaman_komentar" disabled></input>
                        /
                        <input type="text" id="max_komentar" disabled></input>
                        <input class="submitreg" name="next" type="button" onclick="ubah_hal(1);" value="next > ">
                    </form>
                </div>
                <div id="submitkomen">                
                    <form name="form_comment">
                        <label>submit comment</label>
                        <textarea name="comment" type="comment" placeholder="comment" class="isikomen" 
                        onkeyup="if (event.keyCode == 13) {
                                this.value = this.value.replace('\n','');
                                submit_comment(this.form);
                            }"></textarea>
                        <input class= "submitreg" name="submit" type="button" onclick="submit_comment(this.form);" value="Submit">
                    </form>
                </div>
            </div>
            <div id="calendar" class="calendar-box">
        </div>
        
        <!-------------------------------FOOTER HALAMAN------------------------------------->        
        <div class="footer">
            Copyright © Christianto Handojo - Johannes Ridho - Ahmad Faiz - Fandi Pradana - Sigit Aji
        </div>        
    </body>
</html>
