<%-- 
    Document   : searchresult
    Created on : Mar 31, 2013, 4:30:56 PM
    Author     : Raymond
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%
if ((request.getParameter("filter") != null) && 
            (request.getParameter("keyword") != null) && 
            (!request.getParameter("keyword").equals("")) && 
            (!request.getParameter("keyword").equals("Enter search query"))) {
        String filter = request.getParameter("filter");
        String keyword = request.getParameter("keyword");
        out.print("<input type='text' id='filter1' class='hidden' value='"+filter+"'/> <input type='text' class='hidden' id='keyword1' value='"+keyword+"'/>");
        out.print("<script type='text/javascript' language='javascript'> var i = 1; window.onload = doSearch('"+filter+"', '"+keyword+"','"+username+"', 'false'); </script>");
%>	

<div id="isi">				
    <div id="rightsidebar1">
        <ul class="user" id="searchAll">
        </ul>
    </div>
</div>



<div id="footer" class="home">
    <p>&copy Copyright 2013. All rights reserved<br>
        Chalkz Team<br>
        Devin - Felix - Kevin</p>			
</div>
</div>


</body>
</html>
<%
    } else {
        //echo "Anauthorized Access!!";
    }
%>

<script type="text/javascript" language="javascript">		
    document.onscroll = function(){
        //alert("+: "+(window.pageYOffset + window.innerHeight)+"hegi:"+document.body.offsetHeight);
        if ((window.scrollY + window.innerHeight) >= document.body.offsetHeight){
            doSearch(document.getElementById('filter1').value, document.getElementById('keyword1').value, document.getElementById('user').value, 'true');
        }
		
		
    }
</script>