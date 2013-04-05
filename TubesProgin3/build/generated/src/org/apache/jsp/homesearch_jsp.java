package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class homesearch_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\t\n");
      out.write("    <head>\n");
      out.write("        <title> Banana Board</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n");
      out.write("        <script src=\"Dashboard.js\" type=\"text/javascript\" language=\"javascript\"> </script>\n");
      out.write("        <script src=\"Raymond.js\" type=\"text/javascript\" language=\"javascript\"> </script>\n");
      out.write("        <script src=\"datetimepicker_css.js\" type=\"text/javascript\" language=\"javascript\"> </script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"content\">\t\n");
      out.write("            <div id=\"header\">\n");
      out.write("                <div id=\"logo\">\n");
      out.write("                    <img src=\"./image/logo.png\"/>\n");
      out.write("                </div>\n");
      out.write("                <div id=\"menu\">\n");
      out.write("                    <ul>\n");
      out.write("                        <li> <a href=\"home.php\"> DASHBOARD </a> </li>\n");
      out.write("                        <li> <a href=\"profile.php\"> PROFILE </a> </li>\n");
      out.write("                        <li> <a href=\"logout.php\"> LOGOUT </a> </li>\n");
      out.write("                    </ul>\n");
      out.write("                    <form method=\"post\" action=\"searchresult.jsp\">\n");
      out.write("\n");
      out.write("                        <img src=\"image/avatar.jpg\" id=\"profPic\"></img>\n");
      out.write("                        <select name=\"filter\">\n");
      out.write("                            <option value=\"semua\">Semua</option>\n");
      out.write("                            <option value=\"username\">User Name</option>\n");
      out.write("                            <option value=\"judul\">Judul Kategori</option>\n");
      out.write("                            <option value=\"task\">Task</option>\n");
      out.write("                        </select>\n");
      out.write("                        <input name=\"keyword\" id=\"keyword\" class=\"box\" type=\"text\" onclick=\"this.value='';\" onfocus=\"this.select()\" onblur=\"this.value=!this.value?'Enter search query':this.value;\" value=\"Enter search query\" onKeyUp=\"searchSuggestKeyword()\">\n");
      out.write("                        <input id=\"searchbutton\" type=\"submit\" value=\"\">\n");
      out.write("                    </form>\n");
      out.write("                    <div id=\"layer\"></div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
