<%-- 
    Document   : dashboard
    Created on : Apr 6, 2013, 8:14:09 AM
    Author     : VAIO
--%>

<%@page import="Class.*"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <%
	    String userAgent = request.getHeader("user-agent").toLowerCase();
	    if (userAgent.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*") || userAgent.substring(0, 4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")) {
		out.println("<link href=\"css/style-mobile.css\" rel=\"stylesheet\" type=\"text/css\" />");
	    } else {
		out.println("<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" />");
	    }
	%>
        <link href="css/modal.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="javascript/dashboard.js"></script>
    </head>
    <body>
        <%
        /*session management  */
        String userActive = "";
        if(request.getSession().getAttribute("userlistapp")==null){
            response.sendRedirect("index.jsp");
        }else{
            userActive = request.getSession().getAttribute("userlistapp").toString();
        }
        %>
        <div id="main-body-general">
            <!--Header-->
            <div id="header">
            <jsp:include page="header.jsp" />
            </div>
            <div><hr id="border"></div>
            <!--Body-->
            <div id="dashboard-body">
                    <div id="profile-pic">
                        <%
                        HashMap<String,String> userActiveData = (new Function()).GetUser(userActive);
                        %>
                        <a href="profile.jsp?username=<%=userActive %>"><img alt="" id="photo" src="avatar/<%=userActiveData.get("avatar") %>" width="120" height="150"/>
                            <br />
                            <b><%=userActive %></b></a>
                    </div>
                    <div id="main-dashboard">
                            <div id="dashboard-title"><b>MY TASK<br /></b><br /></div>
                            <div id="add-category"><a href="#add_task"><button>+ New Category</button></a>&nbsp;</div>

                            <!-- popup form #1 -->
                            <a href="#x" class="overlay" id="add_task"></a>
                            <div class="popup">
                                    <h2>Add New Category</h2>
                                    <br>
                            <form action="insertcategory" method="post">
                                    <div>
                                            <label for="login">Name:</label>
                                            <input type="text" id="login" value="" name="newCategory"/>
                                    </div>
                                    <div>
                                            <label for="asignee">Assignee:</label>
                                            <input type="text" id="asignee" value="" name="listAssignee" onKeyUp="autoCompleteAsignee()" list="assignee" placeholder="Assignee1,Assignee2,Assignee3"/>
                                            <div id="assignee-suggest"></div>
                                    </div>
                                    <div align="right"><input type="submit" value="Finish"/></div>
                                    </form>

                                    <a class="close" href="#close"></a>
                            </div>

                            <div id="sort">	
                                    Sort by :
                                    <select name="Sort by">
                                            <option value="Auto">Auto</option>
                                            <option value="Name">Name</option>
                                            <option value="Date">Date</option>
                                    </select>&nbsp;			
                            </div>
                            <!--Category list (static)-->
                            <div id="category">
                                <jsp:include page="listCategory.jsp" />
                            </div>

                            <div id="new-category"></div>
                            <!--New category button ==> popup-->
                                    <!--Name-->
                                    <!--List of priveleged users-->
                            <!--New task button ==> new_task.html (this button only appears if a category is selected)-->
                    </div>
                    <div id="dashboard-empty">
                    </div>
            </div>
        </div>
    </body>
</html>
