<%-- 
    Document   : rinciantugas
    Created on : Apr 5, 2013, 4:56:10 PM
    Author     : Christianto
--%>

<%@page import="RincianTugas.DataAwal"%>
<%@page import="javax.xml.crypto.Data"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%DataAwal data = new DataAwal(Integer.parseInt(request.getParameter("id_tugas")));%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/buattask.css">
        <link rel="stylesheet" href="css/calendar.css">
        <title>Next | Rincian Tugas : <%out.println(data.getNama());%> </title>
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

                    params = "komentar="+escape(form.comment.value)+"&user=<%out.print(session.getAttribute("userLoginSession"));%>&id_tugas="+id_tugas;
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
                
                params = 'tanggal='+tanggal+'&user=<%out.print(session.getAttribute("userLoginSession"));%>&id_tugas='+id_tugas;
                xmlhttp.open("POST","hapus_komentar",true);                
                xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlhttp.send(params);
                //alert(params);
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
                            
                            if (username === "<%out.print(session.getAttribute("userLoginSession"));%>") {
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
                        if (xmlhttp.responseText != "0") ambil_komentar();
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
        
            function showEdit() {
                if (document.getElementById("detailedit").innerHTML !== "" && (<%
                    String[] assignee = data.getAssignee();
                    out.println('"'+(String)session.getAttribute("userLoginSession")+"\" === \""+data.getCreator()+'"');
                    for (int i=0;i<assignee.length;++i) {
                        out.println("|| \""+(String)session.getAttribute("userLoginSession")+"\" === \""+assignee[i]+'"');
                    }
                %>)) {
                    document.getElementById("detail").innerHTML=document.getElementById("detailedit").innerHTML;
                    document.getElementById("detailedit").innerHTML = "";
                    calendar.set("date");
                }
            } 
        </script>        
        
    </head>
    <body onload="jumlah_komentar();">
        <%-- Mulai daerah header buatan Jo--%>
        <%@ include file="header.jsp" %>
        
        <!-------------------------------BODY HALAMAN------------------------------------->
        <div class="main">
            <div id="edit" onclick="showEdit();">
            </div>
            <div>
                <form>
                    <input type="button" id="back" value="" onclick="location.href='dashboard.jsp';">
                </form>
            </div>
            <div>
                <%
                    if (data.getCreator().equals((String)session.getAttribute("userLoginSession"))) {
                        out.println("<form method=\"GET\" action=\"hapus_tugas\">");
                        out.println("<input type=\"submit\" name=\"hapus\" id=\"hapus\" value=\"\">");                        
                        out.println("<input type=\"text\" name=\"id_tugas\" id=\"id_tugas\" value=\""+request.getParameter("id_tugas")+"\">");
                        out.println("</form>");
                    }
                %>
            </div>

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
                            out.println("<a href=\""+attachment[i]+"\">"
                                    +attachment[i].substring(attachment[i].lastIndexOf('/')+1)+"</a>");
                            String ext = attachment[i].substring(attachment[i].lastIndexOf('.')+1);
                            
                            if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png")) {
                                out.println("<div class=\"attach_image\"><img src=\""+attachment[i]+"\"></div>");
                            } else if (ext.equals("mp4")) {
                                out.println("<div class=\"attach_image\"><video width=200 height=200 controls>"
                                        + "<source src=\""+attachment[i]+"\" type=\"video/"+ext+"\"></div>");
                            }
                            out.println("<br>");
                        }
                    %>
                    </div>
                </div>
                <div id ="detailedit">
                    <label>NAMA KATEGORI</label>
                    <a id="kategori"><%out.print(data.getKategori());%></a>
                    
                    <form action="edit_detail_tugas" method="POST" enctype="multipart/form-data">
                        <label>STATUS TUGAS
                        <input type="checkbox" name="status" value="done"<%if (data.getStatus()==1) out.print("checked");%>> 
                        </label>
                        
                        <label>DEADLINE</label>
                        <input type="text" name="date" id="date" value ="<%out.print(data.getDeadline().substring(0,10));%>"> 
                        <select name="hour" id="hour">
                        <%
                        for (int i=0;i<24;++i) {
                            if (Integer.parseInt(data.getDeadline().substring(11,13)) == i) {
                                out.println("<option selected>"+i+"</option>");
                            } else {
                                out.println("<option>"+i+"</option>");
                            }
                        }
                        %>    
                        </select>-
                        <select name="minute" id="minute">
                        <%
                        for (int i=0;i<59;++i) {
                            String sem; 
                            if (i < 10) sem = "0"+String.valueOf(i);
                            else sem = String.valueOf(i);
                            
                            if (data.getDeadline().substring(14,16) == sem) {
                                out.println("<option selected>"+sem+"</option>");
                            } else {
                                out.println("<option>"+sem+"</option>");
                            }
                        }
                        %>    
                        </select>
                        
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
                        <input type="textarea" name="tag" placeholder="tag" value="<%out.println(data.getTag());%>">
                        
                        <label>ATTACHMENT</label>
                        <input type="file" name="file" id="file" onchange="validasi_file(this);">

                        <input type="text" name="id_tugas" value="<%out.print(request.getParameter("id_tugas"));%>"
                               id="id_tugas" \>

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
            <div id="calendar" class="calendar-box"></div>
        </div>
        
        <!-------------------------------FOOTER HALAMAN------------------------------------->        
        <div class="footer">
            Copyright © Christianto Handojo - Johannes Ridho - Ahmad Faiz - Fandi Pradana - Sigit Aji
        </div>        
    </body>
</html>
