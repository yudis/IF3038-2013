<%-- 
    Document   : dashboard
    Created on : Apr 13, 2013, 12:29:07 AM
    Author     : Sonny Theo Thumbur
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SharedToDoList | dashboard</title>
        <link rel="stylesheet" type="text/css" href="style/style.css"></link>
        <script LANGUAGE="Javascript" src="script/script.js"></script>
    </head>
    <body>
        
        <%
//        $curUser = "smanurung"; //hanya dummy
//        if (isset($_SESSION["username"])) { //hanya untuk memastikan
//            $curUser = $_SESSION["username"];
//        } else {
//            header("Location:index.php"); //mengembalikan ke halaman utama untuk user yang belum login
//        }
            if (session.getAttribute("username") == null) {
                response.sendRedirect("index.jsp");
            } else {
                String curUser = (String) session.getAttribute("username");
            }
        %>
        
        <form action="Suggestion" method="get">
    	<div id="header">
            <a href="dashboard.jsp"><img id="logo" src="res/logo1.png" alt="to-do list"></img></a>
            <a id="dashboardLink" href="dashboard.jsp">dashboard</a>
            <input id="searchForm" type="text" name="keyword" onkeyup="showHint(this.value)" placeholder="search"></input>
            <select id="filter" name="filter">
                <!--<option selected>Select Filter ...</option>-->
                <option>all</option>
                <option>user</option>
                <option>category</option>
                <option>task</option>
            </select>
            <input id="submitForm" type="submit" name="search" value="search" onclick="toSearchResult(searchForm.value,filter.value)">
	    <a href="profile.jsp"><img id="profile" src="res/profileLogo.png" onclick="keProfil()"/></a>
	    <a id="logout" href="logout.php">Log Out</a>
	    <div class="suggest">Suggestion : <span id="textHint"></span></div>
        </div>
	</form>
        
        <div id="spasi">
        </div>
        
        <div id="staticContainer">
            <div id="kategoriTitle">
            	<p>KATEGORI</p>
            </div>
            
            <%
//                membuat koneksi
                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver).newInstance();

                Connection con = null;
                ResultSet rs = null;
                Statement stmt = null;

                String url = "jdbc:mysql://localhost:3306/progin_405_13510027?user=progin&password=progin";
                con = DriverManager.getConnection(url);
                stmt = con.createStatement();
                String sqlKategoriName = "SELECT namaKategori from kategori";
                rs = stmt.executeQuery(sqlKategoriName);
                
                while  (rs.next()) {
            %>
            <div id="kategoriContent">
                <div id="<%= rs.getString("namaKategori") %>" class="kategoriElmt" onclick="showKategori('<%= rs.getString("namaKategori") %>')">
                    <p><%= rs.getString("namaKategori") %></p>
                </div>
            <%
                }
            %>
                <div id="semuaTugas" class="kategoriElmt" onclick="showAllKategori()">
                    <p>Semua Kategori Tugas</p>
                </div>
                <div id="addKategori" onclick="showPopUp()">
                        <button name="addKategori">tambah kategori</button>
                </div>
            </div>  
        </div>
        
        <hr/><hr/><br/><br/>
                
        <div id="dynamicContainer">
            <div id="dynamic_1" class="dynamicElmt">
                <div class="rincianTitleDiv">
                        <h2 class="rincianText">Rincian Tugas</h2>
                    <hr/>
                </div>
                <div id="dynamicSpace" class="dyn_elmt">
                     <%   
                        Statement stmtDef = con.createStatement();
                        String sqlDef = "SELECT * FROM task";
                        ResultSet rsDef = stmt.executeQuery(sqlDef);
                        while (rsDef.next()) {
                            String namaTask = rsDef.getString("namaTask");
                            String deadline = rsDef.getString("deadline");
                            String status = rsDef.getString("status");
                            String idStatus = namaTask + "status";
                    
                    %>
                        <div id='<%= namaTask %>space'>
                            <div id="taskTitle1" class="taskElmtLeft" onclick="toHalamanRincianTugas('<%= namaTask %>')">
                                <p></strong><%= namaTask %></strong></p>
                            </div>
                            <div class="taskElmtRight">
                            </div>
                            <div class="taskElmtLeft">
                                <p>Deadline :</p>
                            </div>
                            <div class="taskElmtRight">
                                <p><%= deadline %></p>
                            </div>
                            <div class="taskElmtLeft">
                                <p>Tag :</p>
                                </div>
                                <div class="taskElmtRight">

                                    <%
                                        Statement stmtTag = con.createStatement();
                                        String sqlTag = "SELECT tag FROM tagging WHERE namaTask='" + namaTask + "'";
                                        ResultSet rsTag = stmtTag.executeQuery(sqlTag);
                                        
                                        String tagString = "";
                                        while (rsTag.next()) {
                                            tagString += rsTag.getString("tag") + " | ";
                                        }
                                    %>
                                <p><%= tagString %></p>
                            </div>
                            <div class="taskElmtLeft">
                                <p>Status :</p>
                            </div>
                            <div class="taskElmtRight" id='<%= idStatus%>'>
                                <p><%= status %></p>
                            </div>
                            <div class="taskElmtLeft">
                            </div>
                            <div class="taskElmtRight">
                                <button class="ubahStatusTask" onclick="changeTaskStatus('<%= namaTask %>','<%= idStatus %>')">Ubah Status</button>
                            </div>
                            <div class="taskElmtLeft">
                            </div>
                            <div class="taskElmtRight">
                                <button class="ubahStatusTask" onclick="deleteTask('<%= namaTask%>')">Hapus Tugas</button>
                            </div>
                        </div>
                       <%
                           }
                       %>
                </div>
            </div>
        </div>
        
        <div id="popUp">
            <div id="newTugasTitle">
                <h2>Form Tambah Kategori</h2>
            </div>

            <div id="newTugasForm">
                    <div class="Task">Nama Kategori</div> <div class="TaskContent">: <input id="kategoriName" type="text" name="namaTask"/></div>
                    <div class="Task">Pengguna Terpercaya</div> <div class="TaskContent">: <input id="userList" type="text"/></div>

                    <div id="submitNewKategori" onclick="closeKategoriForm('popUp');">
                        <button>cancel</button>
                    </div>
                    <div id="submitNewKategori" onclick="closeKategoriForm('popUp');addKategori(kategoriName.value,userList.value);">
                        <button>submit</button>
                    </div>
            </div>
        </div>
    </body>
</html>
