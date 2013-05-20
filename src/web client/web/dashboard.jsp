
<%@page import="org.json.JSONArray"%>
<%@page import="database.Checker"%>
<!DOCTYPE HTML>
<%@page language="java" import="database.MyDatabase,java.sql.ResultSet;" errorPage="" %>
<%
    if(session.getAttribute("sUsername")==null)
        response.sendRedirect("index.jsp");
%>
<html>
    <head>
        <title>S.Y.N. Dashboard </title>
        <link rel="stylesheet" type="text/css" href="dashboard.css" media="screen">
        <link rel="stylesheet" href="loginpage.css" type="text/css" media="screen">
        <script src="dashboard.js"></script>
        <script src="myscript.js"></script>
        <script type="text/javascript">
            function handleSelectCateg(elm){
                window.location = "dashboard.jsp?category=" + elm.value;
            }
            function timedRefresh(timeoutPeriod) {
                setTimeout("location.reload(true);",timeoutPeriod);
            }
        </script>
    </head>
    <jsp:include page="header.jsp"/>
    <body onload="javascript:timedRefresh(5000)">

        <!---
        --->
        &nbsp
        <br>
        &nbsp
        <br>
        &nbsp
        <br>
        &nbsp
        <br>
        <table id="Content_Table">
            <tr>
                <td width="200" valign="top">
                    <h3>Select Category : </h3>
                    <select name="categchoice" onchange="javascript:handleSelectCateg(this)">
                        <option>-------</option>
                        <%
                            JSONArray tResult0 = MyDatabase.getSingleton().selectDBREST("select * from kategori");
                            int i=0;
                            while (!tResult0.isNull(i)) {
                                out.println("<option>" + tResult0.getJSONObject(i).getString("namaKategori") + "</option>");
                                i++;
                            }
                        %>
                        <option>all</option>
                    </select>
                    <div id="secondary" class="widget-area">
                        <aside class="widget">	
                            <div class="nav-previous"><a href="#openModal">+ Add category</a>
                                <div id="openModal" class="modalDialog">
                                    <div>
                                        <a href="#close" title="Close" class="close"></a>
                                        <form action="newcategory.jsp" method="POST">
                                            <font color="black"> New Category
                                            <br>
                                            <br>
                                            Nama Kategori :
                                            <br>
                                            <br>
                                            <input name="newcateg" size="60" type="text">
                                            <br>
                                            <br>
                                            User yang berhak memodifikasi :
                                            <br>
                                            <br>
                                            Pisahkan dengan tanda koma (",")
                                            <br>
                                            <br>
                                            <input name="userlist" size="60" type="text"></font>
                                            <br>
                                            <br>
                                            <input type="submit" value="Add">
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </aside>
                    </div>
                    <br>
                    <%
                        if (request.getParameter("category") != null && !request.getParameter("category").toString().equals("all")) {
                            String catcreator = Checker.getCategoryCreator(request.getParameter("category"));
                            out.println("Category Creator : " + catcreator + "<br><br>");
                            if (catcreator.equals(session.getAttribute("sUsername"))) {
                                out.println("<a href=\"deletecategory.jsp?namaKategori=" + request.getParameter("category") + "\"><input type=\"button\" value=\"Delete Category\"></a>");
                            }
                            if (Checker.isContributor(session.getAttribute("sUsername").toString(), request.getParameter("category").toString())) {
                                out.println("<a href=\"addtask.jsp?namaKategori=" + request.getParameter("category") + "\"><input type=\"button\" value=\"Add Task\"></a>");
                            }
                        }
                    %>
                    <br>
                    <br>
                    <br>
                    ! Only Category Creator can delete category
                    <br>
                    <br>
                </td>
                <td width="800">
                    <%
                        String categ;
                        if (request.getParameter("category") == null || request.getParameter("category").equals("all")) {
                            categ = "1";
                        } else {
                            categ = "namaKategori = \"" + request.getParameter("category") + "\"";
                        }
                        String tQuery = "Select * from task where " + categ + ";";
                    %>
                    <h1>
                        Dashboard
                        <br>
                        category : 
                        <%
                            if (request.getParameter("category") == null || request.getParameter("category").equals("all")) {
                                out.println("all");
                            } else {
                                out.println(request.getParameter("category"));
                            }
                        %>
                    </h1>
                    <%
                        MyDatabase myDB = new MyDatabase();
                        JSONArray tResult = myDB.selectDBREST(tQuery);
                        i = 0;
                        while (!tResult.isNull(i)) {
                            if (Checker.isAssignee(session.getAttribute("sUsername").toString(), tResult.getJSONObject(i).getString("namaTask"))) {
                                out.println("<h2>");
                                out.println("<a href=\"viewtask.jsp?namaTask=" + tResult.getJSONObject(i).getString("namaTask") + "\">");
                                out.println(tResult.getJSONObject(i).getString("namaTask") + "</a></h2>");
                                out.println("Deadline : " + tResult.getJSONObject(i).getString("deadline"));
                                out.println("<br>Tag : ");
                                String tQuery2 = "select * from tagging where namaTask=\"" + tResult.getJSONObject(i).getString("namaTask") + "\";";
                                MyDatabase myDB2 = new MyDatabase();
                                int j=0;
                                JSONArray tResult2 = myDB2.selectDBREST(tQuery2);
                                while (!tResult2.isNull(j)) {
                                    out.println(tResult2.getJSONObject(j).getString("tag") + ", ");
                                    j++;
                                }
                                out.println("<br>");
                                out.println("Status : " + tResult.getJSONObject(i).getString("status") + "<br>");
                                if (Checker.isAssignee(session.getAttribute("sUsername").toString(), tResult.getJSONObject(i).getString("namaTask"))) {
                                    out.println("<a href=\"ubahstatus.jsp?target=dashboard&namaTask=" + tResult.getJSONObject(i).getString("namaTask") + "\"><input type=\"button\" value=\"Ubah Status\"></a>");
                                }
                                if (Checker.isCreator(session.getAttribute("sUsername").toString(), tResult.getJSONObject(i).getString("namaTask"))) {
                                    out.println("<a href=\"deletetask.jsp?namaTask=" + tResult.getJSONObject(i).getString("namaTask") + "\"><input type=\"button\" value=\"Delete Task\"></a>");
                                }
                                out.println("<br>");
                                out.println("===========<br>");
                            }
                            i++;
                        }
                    %>

                </td>
            </tr>
        </table>
    </body>
</html>