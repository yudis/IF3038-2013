<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.sql.SQLException"%>
<%
    Connection conn = null;
        int IDTask = Integer.parseInt(request.getParameter("IDTask"));
        int pages = Integer.parseInt(request.getParameter("page"));
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
            
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM comment WHERE IDTask=?");
            ps.setInt(1, IDTask);

            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            int pagecount = count / 10;
            if (count % 10 > 0) {
                pagecount++;
            }
            out.print("<br/>Comment (" + count + ") :<br/>");
            out.print("<div class=\"komentar\" id=\"isikomentar\">");
            out.print("<hr>");

            ps = conn.prepareStatement(
                    "SELECT * FROM comment WHERE IDTask=?");
            ps.setInt(1, IDTask);
            rs = ps.executeQuery();

            int countpage = pages;
            if (countpage > 1) {
                countpage--;
                for (int x = 0; x < 10; x++) {
                    rs.next();
                }
            }

            int x = 0;  
            while (rs.next()) {
                if (x < 10) {
                    x++;
                    out.print("<div class=\"commentyeah\">");
                    out.print("<div class=\"commentcontent\">" + rs.getString("Content") + "</div>");

                    ps = conn.prepareStatement(
                            "SELECT * FROM user WHERE username=?");
                    ps.setString(1, rs.getString("Username"));
                    ResultSet res = ps.executeQuery();
                    res.next();
                    String[] ArrayTime = (rs.getString("Timestamp")).split(" ");
                    String[] ArrayDate = ArrayTime[0].split("-");
                    String[] ArrayTimeHour = ArrayTime[1].split(":");
                    
                    String time = ArrayTimeHour[0]+":"+ArrayTimeHour[1]+" - "+ArrayDate[2]+"/"+ArrayDate[1];
                    out.print("<div class=\"commentinfo\"> <img width=30px height=30px src=\""+res.getString("Avatar")+"\" /> <a href=\"profile.php?user=\""+rs.getString("Username")+"\">"+rs.getString("Username")+"</a> at "+time);
                   // HttpSession session = request.getSession(true);
                    if(res.getString("Username").compareTo((String)session.getAttribute("username"))==0){
                        out.print("<br/><input type=\"button\" class=\"remove\" onclick=\"removeComment("+rs.getString("IDComment")+")\" value=\"remove\">");
                    }
                    out.print("</div></div><hr>");
                }
            }
            out.print("</div><br/>");
            out.print("<div id=\"commentpage\" >");
            x=1;
            for(x=1;x<=pagecount;x++){
                if(x==pagecount){
                    out.print("<a class=\"commentselected\" onclick=\"setPage("+x+")\">["+x+"]</a>");
                }else{
                    out.print("<a onclick=\"setPage("+x+")\">["+x+"]</a>");
                }
            }
            out.print("</div></div>");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Can not close connection");
            }
        }
    %>
