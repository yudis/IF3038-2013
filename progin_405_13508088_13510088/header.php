<html>
	<script type="text/javascript" src="Header.js"></script>
	<link rel="stylesheet" href="style.css" type="text/css"/>
	        <div class ="header" id="headerother">
                <img src="images/LogoPNG.png" class="logosmall">
                <input type="text" name="search" class="sfield" value="" align = "right" style="margin-right :-7px; float:none" onclick ="javascript:ShowHide('filterbox')" href="javascript:;" >
					<select name="mydropdown" style="position:fixed; float : none; margin-top:5px; width: 105px; height: 24px; border-radius:3px; font-family:calibri; font-style:italic; border : 0px">
						<option value="All">All Search</option>
						<option value="Username">by Username</option>
						<option value="Title">by Title</option>
						<option value="Email">by Email</option>
						<option value="FullName">by FullName</option>
						<option value="Birthday">by Birthday</option>
					</select>
                <a href="dashboard.php" style = "margin-left : 350px">Dashboard</a>
                <a href="halamanProfil.php"><?php include "coba.php" ?></a> 
                 <a href="index.php">Logout</a>
                </form>

        </div>
</html>