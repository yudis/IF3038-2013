<html>

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
                    if (xmlhttp.readyState==4 && xmlhttp.status == 200)	{
                        //alert(xmlhttp.responseText);
                        if (xmlhttp.responseText == 1) {
                            document.getElementById("deadicon").src="pict/centang.png";
                        } else {
                            document.getElementById("deadicon").src="pict/canceled.png";                        
                        }
                    }
                }
                
                params = "tanggal=";
                params += escape(form.year.value+"-"+form.month.value+"-"+form.day.value+" "+form.hour.value+":"+form.minute.value+":00");                
                //alert(params);
                xmlhttp.open("POST","validasi_date.php",true);
                xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlhttp.send(params);
            }
			
			function tag_validating()
            {
                var tagid = document.buatugas.tag.value;
                
                if(tagid.match(/([ \t\r\n\v\f])/)){
                    document.getElementById("tagicon").src="pict/canceled.png";
                }
                    else
                {
                    document.getElementById("tagicon").src="pict/centang.png";
                }
            }

            function validasi_file(place) {
                var ext=place.value.substring(place.value.indexOf(".")+1);
                if (ext=="jpeg" || ext == "avi" || ext=="pdf" || ext == "jpg") {
                    document.getElementById("attach_upload").innerHTML += place.value+";";
                    place.value = "";
                } else {
                    alert("Ekstensi file tidak didukung web");
                    place.value = "";
                }
                //alert(place.value);
            }
			
			function prepare() {
                for (var i=1;i<=12;++i) {
                    document.getElementById("month").innerHTML += "<option>"+i+"</option>";
                }
                
                for (var i=1;i<=31;++i) {
                    document.getElementById("day").innerHTML += "<option>"+i+"</option>";
                }

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
            
            function auto_complete(form) {
                var text = form.value.substring(form.value.lastIndexOf("/")+1);
            
                var xmlhttp;
                if (text == "") {
                    document.getElementById("autobox").value = "";
                    document.getElementById("asicon").src="pict/centang.png";
                } else {
                    if (window.XMLHttpRequest){
                        xmlhttp = new XMLHttpRequest();				
                    } else {
                        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
                    }
                    
                    xmlhttp.onreadystatechange = function(){
                        if (xmlhttp.readyState==4 && xmlhttp.status == 200)	{
                            //alert(xmlhttp.responseText);
                            var s = xmlhttp.responseText;
                            var n = s.indexOf("\n");
                            document.getElementById("autobox").value = "";
                            
                            if (n == -1) {
                                document.getElementById("autobox").value = "Tidak ada user dengan nama itu";
                                document.getElementById("asicon").src="pict/canceled.png";
                            } else {                       
                                while (n != -1) {
                                    //Ambil satu data komentar
                                    var username = s.substring(0,n);
                                    s = s.substring(n+1);
                                    n = s.indexOf("\n");
         
                                    //Tampilkan datanya
                                    var tambah = username+" ";
                                    document.getElementById("autobox").value += tambah+"\n";
                                }
                                document.getElementById("asicon").src = "pict/blank.png";
                            }
                        }
                    }
                    
                    params = "assignee="+escape(text);
                    //alert(params);
                    xmlhttp.open("POST","auto_complete_user.php",true);
                    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                    xmlhttp.send(params);
                }
            }
            
            function add_tugas(form) {
                var centang = window.location.href.substring(0,window.location.href.lastIndexOf("/")) + "/pict/centang.png";
                //alert(centang);
                if (document.getElementById("namaicon").src != centang 
                    || document.getElementById("deadicon").src != centang
                    || document.getElementById("asicon").src != centang) {
                    //alert(document.getElementById("namaicon").src);
                    alert("Masukan ada yang belum benar");
                } else {        
                    var xmlhttp;
                    if (window.XMLHttpRequest){
                        xmlhttp = new XMLHttpRequest();				
                    } else {
                        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
                    }
                    
                    xmlhttp.onreadystatechange = function(){
                        if (xmlhttp.readyState==4) {
                            //alert(xmlhttp.status);
                            if (xmlhttp.status == 200)	{
                                //alert(xmlhttp.responseText);
                                if (xmlhttp.responseText == 1) {
                                    alert("Berhasil Membuat tugas baru");
                                } else {
                                    alert("Gagal membuat tugas baru");
                                }
                            }
                        }
                    }

                    var id_kategori = "-1";
                    if ((c = window.location.search.indexOf("id_kategori=")) != -1) {
                        id_kategori = window.location.search.substring(c+12);
                    }     
                    
                    //alert(id_kategori);
                    params = "assignee="+escape(form.assignee.value)+"&tag="+escape(form.catname.value)+"&nama="+form.task_name.value;
                    params += "&id_kategori="+id_kategori+"&deadline=";
                    params += escape(form.year.value+"-"+form.month.value+"-"+form.day.value+" "+form.hour.value+":"+form.minute.value+":00");
                    params += "&attachment="+document.getElementById("attach_upload").innerHTML;
                    
                    //alert(params);
                    xmlhttp.open("POST","buat_tugas.php",true);
                    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                    xmlhttp.send(params);
                }
            }            
		</script>
        
	<?php include 'header.php'; ?>
    
    <body onLoad="prepare();showUserLogin();">
    
        
        <!-----------------------------BODY FILE-------------------------------->
        <div class="main">
            <div id="addtask">
            </div>
            <div>
                <form>
                    <input type="button" id="back" value="" onClick="location.href='dashboard.html'">
                </form>
            </div>
            
            <div id="formtask">
                <div id = "judulform">
                </div>
                <form id="formtask2">
                    <label>NAMA TUGAS</label>
                    <input name="task_name" type="text" placeholder="task_name" onKeyUp="name_valid(this.value)" />
                    <img src="pict/blank.png" alt="icon1" id="namaicon"  />                    
                    
                    <label>DEADLINE</label>
                    <input type="textarea" name="year" id="yearbox" value="" onChange="dead_validating(this.parentNode)">-
                    <select name="month" id="month" onChange="dead_validating(this.parentNode)">
                    </select>-

                    <select name="day" id="day" onChange="dead_validating(this.parentNode)">
                    </select> Jam: 
                    
                    <select name="hour" id="hour" onChange="dead_validating(this.parentNode)">
                    </select>-

                    <select name="minute" id="minute" onChange="dead_validating(this.parentNode)">
                    </select>                        
					<img src="pict/blank.png" alt="icon3" id="deadicon" />
                    
                    <label>ASSIGNEE</label>
                    <input type="textarea" name="assignee" placeholder="assignee"
                      title="Akhiri nama user dengan tanda /, jangan dipisah spasi"
                      onkeyup="auto_complete(this)" value="">
                    <img src="pict/blank.png" alt="icon4" id="asicon" />
                      
                    <input id="autobox" disabled></input>
                    
                    <label>TAG</label>
                    <input type="textarea" name="catname" placeholder="tag" value="">
                    
                    <label>ATTACHMENT</label>
                    <div id="attach_upload">                            
                    </div>                            
					<img src="pict/blank.png" alt="icon2" id="attaicon"  />                    
                    
                    <input type="file" name="file" id="file" onChange="validasi_file(this)">
                    <br>
                    <input class= "submitreg" name="submit" type="button" value="Submit"
                     onclick="add_tugas(this.form)">
                </form>
            </div>
        </div>
        
        <!---------------------------------FOOTER FILE------------------------------------>
        <div class="footer">
            Copyright © Ahmad Faiz - Fandi Pradana - Sigit Aji
        </div>
        
    </body>
</html>