<%-- 
    Document   : newcat
    Created on : Apr 13, 2013, 6:49:33 AM
    Author     : gilang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard - New Category</title>
        <link rel="stylesheet" type="text/css" href="dashboard.css">
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
        <link rel="icon" href="favicon.ico" type="image/x-icon">
    </head>
    <body>
        <div id="newcatcontent">
		<div id="atribut">
                        <br><br>
			<p>Nama Kategori</p>
			<p>Siapa yang bisa melakukan edit:</p>	
		</div>
		
		<div id="inputatribut">
                    <br><br>
			<form  action="addCat" method="POST">
			<input type="text" name="catname" value=""><br>
                            
                            <input type="checkbox" name="user" value="Fadhil">Fadhil Muhtadin<br>
                            <input type="checkbox" name="user" value="Ari">Arie Tando<br>
                            <input type="checkbox" name="user" value="Wachid">M Wachid Kusuma 
		
				<input type="submit" id="tombolsubmit" name="submit" value="submit">
			</form>
		</div>
	</div>
    </body>
</html>
