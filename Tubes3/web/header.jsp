<%--<head>--%>			<!------memanggil showUserLogin saat load body-------->	
        <%@ page import ="java.sql.*" %>
        <%@ page import ="javax.sql.*" %>   
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/dash.css">
        <script type="text/javascript" src="js/popup.js"></script>
        <script>

	function auto_complete_search(text) {
                var xmlhttp;
                if (text == "") {
                    document.getElementById("autosearch").value = "";                    
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
                            document.getElementById("autosearch").value = "";
                            
                            while (n != -1) {
                                //Ambil satu data komentar
                                var username = s.substring(0,n);
                                s = s.substring(n+1);
                                n = s.indexOf("\n");
     
                                //Tampilkan datanya
                                var tambah = username+" ";
                                document.getElementById("autosearch").value += tambah+"\n";
                            }
                        }
                    }
                    
                    params = "assignee="+escape(text);
                    //alert(params);
                    xmlhttp.open("POST","auto_complete_search",true);
                    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                    xmlhttp.send(params);
                }
            }//end autocomplete
			
        </script>			
            
        <%
        //HttpSession session = request.getSession(false);// don't create if it doesn't exist
        if((String)session.getAttribute("userLoginSession") == null) {            
            response.sendRedirect("index.jsp");
            //out.print("asdasda");
        }      
        %>      
        
        <div class="header">
            
            <div id="logo">
                <a href="dashboard.jsp">
                    <img src="pict/logo.png">
                </a>
            </div>
            
            <div id="border">

            </div>
<!--            
            <div id="dashboard">
                <a href="dashboard.jsp">DASHBOARD</a>
            </div>
            
            <div id="profile">
                <a href="profile.jsp">PROFILE</a>
            </div>
-->            
            <div id="search">
            
                <form action="searchresult.jsp" method="post">
                    <section class="searchform cf">
                            <input onkeyup="auto_complete_search(this.value.substring(this.value.lastIndexOf('/')+1))" id="cari" class="searchbox" type="search" name="search" placeholder="Search.." required>                           
                    </section>                        
                    <select name="searchFilter" class="dropdownJo" id="searchFilter"> 	<!--dropdown filter-->
                        <option value="semua">Semua</option>
                        <option value="username">Username</option>
                        <option value="email">Email</option>
                        <option value="namalengkap">Nama Lengkap</option>
                        <option value="birthdate">Birthdate</option>
                        <option value="kategori">Kategori</option>
                        <option value="tugas">Tugas</option>
                        <option value="tag">Tag</option>                            
                        <option value="komentar">Komentar</option>                            
                    </select>
                    <input type="submit" value="search" class="searchbuttonbox cf">
                </form>					
            </div>	<!--end div search-->
                                                                                 
            <div id="showLoginHeader">
                <div id="showAvatarHeader">
                <%
                    String ses = (String)session.getAttribute("userLoginSession");                

                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection conLogin11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510003","root","");

                    Statement stLogin11= conLogin11.createStatement(); 
                    ResultSet rsLogin11=stLogin11.executeQuery("select * from pengguna where username='"+ses+"'");     

                    if(rsLogin11.next()) { 
                        out.print("<div>");
                        out.print("<img width=\"60px\" height=\"60px\" src=\"");
                        out.print(rsLogin11.getString("avatar"));
                        out.print("\">" );
                        out.println("</div>");
                    }
                %>
            </div>	
            
                <a href="profile.jsp">
                    <%
                        out.println(session.getAttribute("userLoginSession"));       
                    %>
                </a>
                
            </div>	
            
            
            				    
            <div id="logout">		<!--hapus user login dan waktunya ketika logout -->
			    <a href="logout.jsp">LOGOUT</a>              
    	    </div>
                    		
    </div>
          
        <input id="autosearch" disabled></input>  <!--autocomplete box-->
        
        <%--    </head>--%>


