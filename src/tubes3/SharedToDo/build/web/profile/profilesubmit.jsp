<%@page import="java.sql.ResultSet"%>
<%@include file="../ConnectDB.jsp" %>
<%
	String username = request.getParameter("u");
	String password = request.getParameter("password");
	String email = request.getParameter("email");
	String fullname = request.getParameter("fullname");
	String dob = request.getParameter("dob");
	String photo = "";
        
	/*
	if ($_FILES['files']['name'])
	{
		$photo = $_FILES['files']['name'];
		move_uploaded_file($_FILES['files']['tmp_name'], '../images/'.$_FILES['files']['name']);
	}
	else
	{
		$query = "SELECT photo FROM login WHERE username = '$username'";
		$result = mysql_query($query);
		if ($result)
		{
			$row = mysql_fetch_array($result);
			$photo = $row['photo'];
		}
	}
	*/
        
	String sqlQuery = "UPDATE login SET username = '$username', password = '$password', email = '$email', fullname = '$fullname', dob = '$dob', photo = '$photo' WHERE username = '$username'";
	ResultSet resultSet = ConnectDB.mysql_query(sqlQuery);
	if (resultSet.next())
	{
		//header("Location: index.php?u=".$_GET['u']."&e=".$_GET['e']);
	}
	
	ConnectDB.closeDB();
%>