package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class searchresult_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/header.jsp", out, false);
      out.write('\n');
      out.write('\n');

if ((request.getParameter("filter") != null) && 
            (request.getParameter("keyword") != null) && 
            (!request.getParameter("keyword").equals("")) && 
            (!request.getParameter("keyword").equals("Enter search query"))) {
        String filter = request.getParameter("filter");
        String keyword = request.getParameter("keyword");
        out.print("<input type='text' id='filter' class='hidden' value='"+filter+"'/> <input type='text' class='hidden' id='keyword' value='"+keyword+"'/><input type='text' class='hidden' id='user' value='RAYMOND'/>");
        int i = 1;
        out.print("<script type='text/javascript' language='javascript'> var i = 1; window.onload = doSearch('"+filter+"', '"+keyword+"', '"+i+"', 'RAYMOND'); </script>");

      out.write("\t\n");
      out.write("\n");
      out.write("<div id=\"isi\">\t\t\t\t\n");
      out.write("    <div id=\"rightsidebar1\">\n");
      out.write("        <ul class=\"user\" id=\"searchAll\">\n");
      out.write("        </ul>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<div id=\"footer\" class=\"home\">\n");
      out.write("    <p>&copy Copyright 2013. All rights reserved<br>\n");
      out.write("        Chalkz Team<br>\n");
      out.write("        Yulianti - Adriel - Amelia</p>\t\t\t\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");

    } else {
        //echo "Anauthorized Access!!";
    }

      out.write("\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" language=\"javascript\">\t\t\n");
      out.write("    document.onscroll = function(){\n");
      out.write("        //alert(\"+: \"+(window.pageYOffset + window.innerHeight)+\"hegi:\"+document.height);\n");
      out.write("        if ((window.pageYOffset + window.innerHeight) > document.height){\n");
      out.write("            doSearch(document.getElementById('filter').value, document.getElementById('keyword').value, i++, document.getElementById('user').value);\n");
      out.write("        }\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("    }\n");
      out.write("</script>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
