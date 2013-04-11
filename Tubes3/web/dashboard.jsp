<%-- 
    Document   : dashboard
    Created on : Apr 11, 2013, 9:47:46 PM
    Author     : Christianto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Next | Dashboard </title>
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/dash.css">
        <script type="text/javascript" src="js/popup.js"></script>
        <script type="text/javascript">
        function showAddTask(){
            document.getElementById("addtask").style.visibility="visible";
            document.getElementById("addtask").disabled = false;
        }

        function hideAddTask(){
            document.getElementById("addtask").style.visibility="hidden";
            document.getElementById("addtask").disabled = true;
        }
			
        function ubahStatus(nomor) {
            var xmlhttp;
            if (window.XMLHttpRequest){
                xmlhttp = new XMLHttpRequest();				
            }else{
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
            }

            xmlhttp.onreadystatechange = function(){
                //alert(xmlhttp.readyState+" "+xmlhttp.status);

                if (xmlhttp.readyState==4 && xmlhttp.status==200){				
                    //alert(xmlhttp.responseText);
                    //document.getElementById("category").innerHTML=xmlhttp.responseText;
                }
            };

            xmlhttp.open("GET","ubahstatus?id_tugas="+nomor,true);
            xmlhttp.send();				
        }

        function getCat() {
            var xmlhttp;
            if (window.XMLHttpRequest){
                xmlhttp = new XMLHttpRequest();				
            }else{
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
            }

            xmlhttp.onreadystatechange = function(){
                //alert(xmlhttp.readyState+" "+xmlhttp.status);

                if (xmlhttp.readyState==4 && xmlhttp.status==200){				
                    //alert(xmlhttp.responseText);
                    document.getElementById("category").innerHTML=xmlhttp.responseText;					
                }
            };

            xmlhttp.open("GET","getCat",true);
            xmlhttp.send();
            //alert(xmlhttp.responseText);
            //alert(xmlhttp.status);
        }

        function getTask() {
            var xmlhttp;
            if (window.XMLHttpRequest){
                xmlhttp = new XMLHttpRequest();				
            } else {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
            }

            xmlhttp.onreadystatechange = function(){
                //alert(xmlhttp.readyState+" "+xmlhttp.status);

                if (xmlhttp.readyState==4 && xmlhttp.status==200){				
                    //alert(xmlhttp.responseText);
                    document.getElementById("category2").innerHTML=xmlhttp.responseText;					
                }
            };

            xmlhttp.open("GET","getTask",true);
            xmlhttp.send();
            //alert(xmlhttp.responseText);
            //alert(xmlhttp.status);
        }
        
        function catTask(n){<!--SIGIT-->
            var xmlhttp;
            if (window.XMLHttpRequest){
                xmlhttp = new XMLHttpRequest();				
            }else{
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
            }

            xmlhttp.onreadystatechange = function(){
                //alert(xmlhttp.readyState+" "+xmlhttp.status);

                if (xmlhttp.readyState==4 && xmlhttp.status==200){				
                    //alert(xmlhttp.responseText);
                    document.getElementById("category2").innerHTML=xmlhttp.responseText;					
                }
            };

            xmlhttp.open("GET","catTask?id_kategori="+n,true);
            xmlhttp.send();
            document.getElementById("kirim").action = "buattask.jsp?id_kategori="+n;
            //alert(xmlhttp.responseText);
            //alert(xmlhttp.status);
        }
			
        </script>
    </head>
	
<!------------------------------------------------------------------------->	
    <body onload="getTask();getCat();">
        <jsp:include page="header.jsp" flush="true"/>
        
        <div class="main">
            <div id="addcat" onclick="popup('popUpDiv');" disabled>
            </div>
            <form id="kirim" action="buattask.html" method="POST">
                <input type="Submit" name="submit" id="addtask" value="">
            </form>
            <div id="category"></div>
            <div id="category2"></div>
            
            <div id="task">
                <a href="rinciantugas.html">
                <div id="div1">
                    
                </div>
                </a>
            </div>
            
            <div id="blanket"></div>
            <div id="popUpDiv">
                <div id="newcategory">
                    <div id="closecat">
                        <a href="#" onclick="popup('popUpDiv');" >CLOSE</a>
                    </div>
                    <div id="cattitle">ADD CATEGORY</div>
                    <div id="elcategory">
                        <form action="addCategory">
                            <label>category name</label>
                            <input name="catname" placeholder="category name">
                            <label>assignee</label>
                            <input name="catass" placeholder="assignee"></br></br>
                            <input class= "submitreg" name="submit" type="submit" value="Submit">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
