<%@include file="header.jsp"%>

<!-- Web Content -->
<section>
   <%@include file="navbar.jsp"%>
   <div id="dynamic_content">
   </div>
</section>

<%@include file="footer.jsp"%>

<script>
   allCategory("<% out.print(session.getAttribute("username"));%>");
   updateNavbar("<% out.print(session.getAttribute("username"));%>");
   setInterval(function(){updateNavbar("<% out.print(session.getAttribute("username"));%>")}, 1000);
</script>
</body>
</html>