package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class maketask_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/header.jsp", out, false);
      out.write('\r');
      out.write('\n');

    String kategori;
    if (request.getParameter("kategori") != null) {
        kategori = request.getParameter("kategori");
    } else {
        kategori = "1";
    }


      out.write("\r\n");
      out.write("<div id=\"isi\">\r\n");
      out.write("    <div id=\"leftsidebar\">\r\n");
      out.write("        <b>CREATE NEW TASK</b>\r\n");
      out.write("        <img src=\"image/leftmenu.png\"/>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div id=\"rightsidebar\">\r\n");
      out.write("        <div id=\"wrapper-left\">\r\n");
      out.write("            <form class=\"task\" name=\"MakeForm\" method=\"post\" onSubmit=\"checkSubmission(this, event)\" enctype=\"multipart/form-data\" action=\"AddTask\">\r\n");
      out.write("                <h1>Fill Details</h1>\r\n");
      out.write("                <ul>\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <label for=\"tugas\" >Nama Tugas</label>\r\n");
      out.write("                        <input id=\"tugas\" name=\"tugas\" onkeyup=\"checkNamaTugas()\" type=\"text\" maxlength=\"25\"/><img src=\"image/false.png\" alt=\"False04\" id=\"nlengkappic\"/><br>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <label for=\"filebutton1\">Attachment 1</label>\r\n");
      out.write("                        <input id=\"filebutton1\" name=\"filebutton1\" type=\"file\" onChange=\"checkFile('filebutton1')\"/>\r\n");
      out.write("                    </li>\t\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <label for=\"filebutton2\">Attachment 2</label>\r\n");
      out.write("                        <input id=\"filebutton2\" name=\"filebutton2\" type=\"file\" onChange=\"checkFile('filebutton2')\"/>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <label for=\"filebutton3\">Attachment 3</label>\r\n");
      out.write("                        <input id=\"filebutton3\" name=\"filebutton3\" type=\"file\" onChange=\"checkFile('filebutton3')\"/>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <label for=\"asignee\">Assignee</label>\r\n");
      out.write("                        <input id=\"asignee\" name=\"asignee\" type=\"text\" onKeyUp=\"searchSuggest()\"/>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li id=\"layer1\">\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <label for=\"tag\">Tag</label>\r\n");
      out.write("                        <input id=\"tag\" name=\"tag\" type=\"text\" size=\"20\"/><br>\r\n");
      out.write("                        <span>*dipisahkan dengan \",\"</span>\r\n");
      out.write("                    </li>\t\t\t\t\t\t\t\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <label for=\"deadline\">Deadline</label>\r\n");
      out.write("                        <input id=\"demo1\" name=\"deadline\" type=\"text\" size=\"25\"/>\r\n");
      out.write("                        <a href=\"javascript:NewCssCal('demo1','ddmmyyyy')\"><img src=\"image/cal.gif\" alt=\"Pick a date\"/></a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <input type=\"text\" name=\"kategori\" class=\"hidden\" value=\"");
      out.print( kategori );
      out.write("\"/>\r\n");
      out.write("                        <input type=\"text\" name=\"user\" class=\"hidden\" value=\"RAYMOND\"><!-- $_SESSION['bananauser'];%>\"/> TODO!!-->\r\n");
      out.write("                        <button class=\"task\" name=\"submitbutton\" type=\"submit\"><b>Submit</b></button>\r\n");
      out.write("                    </li>\r\n");
      out.write("                </ul>\r\n");
      out.write("            </form>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"footer\" class=\"home\">\r\n");
      out.write("    <p>&copy; Copyright 2013. All rights reserved<br>\r\n");
      out.write("        Chalkz Team<br>\r\n");
      out.write("        Yulianti - Adriel - Amelia</p>\t\t\t\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
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
