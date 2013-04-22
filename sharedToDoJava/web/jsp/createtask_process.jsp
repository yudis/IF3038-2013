<%@page import="com.mysql.jdbc.Driver"%>
<%@page import="com.mysql.jdbc.exceptions.jdbc4.CommunicationsException" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>

<%    
    String task_name = request.getParameter("task_name");
	String deadline = request.getParameter("deadline");
	//String category = request.getParameter("kategori");
	String category = "html";
	String creator = session.getAttribute("username").toString();
    
	new Driver();
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/progin_405_13510027","progin","progin");
	
	Statement st = con.createStatement();
	st.executeUpdate("INSERT INTO task (namaTask, namaKategori, deadline, creatorTaskName, status) VALUES ('"+task_name+"', '"+category+"', '"+deadline+"','"+creator+"', 'belum')");	
       
    /*
    $i = 0;    
    foreach($_FILES["attachment"]["name"] as $filename) {        
        move_uploaded_file($_FILES["attachment"]["tmp_name"][$i], "../server/" . $filename);
        mysqli_query($con, "INSERT INTO attach (namaTask, attachment) VALUES ('$task_name', '$filename')");
        $i++;
    }
    */
    /*
    $tag_input = $_POST["tag"];
    $tags = explode(",", $tag_input);
    $j = 0;
    foreach($tags as $tag) {
        mysqli_query($con, "INSERT INTO tagging (namaTask, tag) VALUES ('$task_name', '$tag')");
        $j++;
    }
    */
    
    /*
    
    $assigneeList = $_POST['assignee'];
    $assignee = explode(",",$assigneeList);
    
    for ($i=0; $i<count($assignee); $i++) {
        mysqli_query($con,"INSERT INTO tasktoasignee VALUES ('$task_name','$assignee[$i]')");
    }
    */
    response.sendRedirect("../halamanRincianTugas.jsp");
   	
   
%>

