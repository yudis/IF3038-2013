<%-- 
    Document   : searchresult
    Created on : Mar 31, 2013, 4:30:56 PM
    Author     : Raymond
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />

<%
if ((request.getParameter("filter") != null) && 
            (request.getParameter("keyword") != null) && 
            (!request.getParameter("keyword").equals("")) && 
            (!request.getParameter("keyword").equals("Enter search query"))) {
        String filter = request.getParameter("filter");
        String keyword = request.getParameter("keyword");
        out.print("<input type='text' id='filter' class='hidden' value='"+filter+"'/> <input type='text' class='hidden' id='keyword' value='"+keyword+"'/><input type='text' class='hidden' id='user' value='RAYMOND'/>");
        int i = 1;
        out.print("<script type='text/javascript' language='javascript'> var i = 1; window.onload = doSearch('"+filter+"', '"+keyword+"', '"+i+"', 'RAYMOND'); </script>");
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
        Yulianti - Adriel - Amelia</p>			
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
        //alert("+: "+(window.pageYOffset + window.innerHeight)+"hegi:"+document.height);
        if ((window.pageYOffset + window.innerHeight) > document.height){
            doSearch(document.getElementById('filter').value, document.getElementById('keyword').value, i++, document.getElementById('user').value);
        }
		
		
    }
</script>