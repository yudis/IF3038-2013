<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<html>
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="autocomplete.css" media="screen" />
    </head>
    <body onload="loadcomment(); loadpagevar()">
        <%@include file="header.jsp"%>
        <%
            int IDTask = Integer.parseInt(request.getParameter("IDTask"));
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
            
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM task WHERE IDTask=?");
            ps.setInt(1, IDTask);

            String TaskName = "";
            String deadline = "";
            String Status = "";
            String Creator = "";
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaskName = rs.getString("TaskName");
                deadline = rs.getString("Deadline");
                Status = rs.getString("Status");
                Creator = rs.getString("Creator");
            }

            ps = conn.prepareStatement(
                    "SELECT * FROM attachment WHERE IDTask=?");
            ps.setInt(1, IDTask);

            ArrayList<String> attachment = new ArrayList<String>();

            rs = ps.executeQuery();
                System.out.print("Waw");
            while (rs.next()) {
                attachment.add(rs.getString("PathFile"));
            }

            ps = conn.prepareStatement(
                    "SELECT * FROM assignment WHERE IDTask=?");
            ps.setInt(1, IDTask);

            ArrayList<String> assignee = new ArrayList<String>();

            rs = ps.executeQuery();
            while (rs.next()) {
                assignee.add(rs.getString("Username"));
            }

            ps = conn.prepareStatement(
                    "SELECT * FROM tasktag,tag WHERE IDTask=? AND tasktag.IDTag=tag.IDTag");
            ps.setInt(1, IDTask);

            ArrayList<String> tag = new ArrayList<String>();

            rs = ps.executeQuery();
            while (rs.next()) {
                tag.add(rs.getString("TagName"));
            }
            /*
             <?php
             $IDTask = $_GET['IDTask'];

             if (connectDB()) {
             $TaskQuery = "SELECT * FROM task WHERE IDTask=" . $IDTask . ";";
             $TaskResult = mysql_query($TaskQuery);
             $result = mysql_fetch_array($TaskResult);

             $TaskName = $result[2];
             $deadline = $result[4];
             $Status = $result[3];

             $AttachmentQueryText = "SELECT * FROM attachment WHERE IDTask=" . $IDTask . ";";
             $AttachmentQuery = mysql_query($AttachmentQueryText);

             $AssigneeQueryText = "SELECT * FROM assignment WHERE IDTask=" . $IDTask . ";";
             $AssigneeQuery = mysql_query($AssigneeQueryText);

             $TagQueryText = "SELECT * FROM tasktag,tag WHERE IDTask=" . $IDTask . " AND tasktag.IDTag=tag.IDTag";
             $TagQuery = mysql_query($TagQueryText);

             $CommentQueryText = "SELECT * FROM comment WHERE IDTask=" . $IDTask . ";";
             $CommentQuery = mysql_query($CommentQueryText);
             ?>//*/
        %>    
        <div id="category">      
            <div class = "kategori"><a title="Go to Dashboard" href="dashboard.php">Back to Dashboard</a></div>
            <div>
                <%
                    out.print("<input value=\"" + IDTask + "\" hidden=\"true\" id=\"HiddenIDTask\">");
                %>
            </div>
        </div>
        <div id ="listtugas" class="list">
            <div class="tugasyeah" id="rincitugas">
                Name: <% out.print(TaskName + "");%><br/>
                Status : 
                <div id="checkstatus">
                    <%
                        if (Status.compareTo("done") == 0) {
                            out.print("DONE <input type=\"checkbox\" id=\"checkboxstatus\" checked onclick=\"changestatus(" + IDTask + " )\" >");
                        } else {
                            out.print("DONE <input type=\"checkbox\" id=\"checkboxstatus\" onclick=\"changestatus(" + IDTask + " )\" >");
                        }
                    %>
                </div>
                Attachment:
                <div class="attachment" >
                    <%
                        for (int i = 0; i < attachment.size(); i++) {
                            String s = attachment.get(i);
                            String[] attachmentpart = s.split("\\.");
                            out.print("attachment " + i + "<br/>");
                            int idx = attachmentpart.length - 1;
                            if (attachmentpart[idx].compareTo("jpg") == 0 || attachmentpart[idx].compareTo("jpeg") == 0 || attachmentpart[idx].compareTo("png") == 0) {
                                out.print("<a href=\"" + attachment.get(i) + "\"><img width = \"150px\" height = \"150px\" src=\"" + attachment.get(i) + "\"></a><br/>");
                            } else if (attachmentpart[idx].compareTo("ogg") == 0) {
                                out.print("<video width=\"320\" height=\"240\" controls autoplay><source src=\"" + attachment.get(i) + "\" type=\"video/\"" + attachmentpart[idx] + "\"\"></video><br>");
                            } else {
                                out.print("<a href=\"" + attachment.get(i) + "\" target=\"_blank\">" + attachment.get(i) + "</a><br/>");
                            }
                            out.print("<br/>");   //*/
                        }
                    %>
                </div><br/>
                Deadline: <% out.print(deadline + "");%><br/>
                Assignee: 
                <%
                if(session.getAttribute("username")!=null){
                    for (int i = 0; i < assignee.size(); i++) {
                        if (assignee.get(i).compareTo((String) session.getAttribute("username")) != 0) {
                            out.print("<a href=\"profile.jsp?user=" + assignee.get(i) + "\" class=\"asignee\">" + assignee.get(i) + "</a>,");
                        }
                    }
                }
                %>
                <br/>
                Tag: 
                <%
                    for(int i=0;i<tag.size();i++){
                        out.print("<a href=\"\" class=\"tag\">"+tag.get(i)+"</a>, ");
                    }
                %>
                <br/>
                <div id="komentaryey"></div>
                <form>
                    AddComment <br/>
                    <input type="text" id="addCommentText"  >
                    <%
                    //<input type="button" name="submit" onclick="addComment('<?php echo $_COOKIE['UserLogin']; ?>', '<?php echo $IDTask; ?>')" value="submit">
                    %>
                </form>
                <br/><br/>
                <a onclick="showEdit();" class="button">edit</a><br/>
            </div>
            <div class="tugasyeahh" id ="edittugas">
                EDIT TASK<br/><br/>
                <form>
                    Deadline: <input type="date" id="editdeadline"><br/>
                    Assignee: <div class="assignee">
                        <div id="ListEditAssignee">
                           
                        </div>
                        <input type="text" id="addnewassignee" list="assignee" onkeyup="multiAutocomp(this, 'assignee.php', 'edittugas')"></div><br/>
                    Tag: 
                    <div id="ListEditTag">
                        
                    </div>
                    <div class="tag"> <input id="edittag" type="text"></div> <br/>
                </form> <br/>
                <a onclick="editTask('<?php echo$IDTask; ?>')" class="button">OK</a><br/><br/><br/>
                
                <a onclick="deleteTaskYey('<?php echo$IDTask; ?>')" class="button">DELETE TASK</a>
                
            </div>
        </div>
    </body>
    <script type="text/javascript" src="script.js"></script>
  
</html>