/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat (TomEE)/7.0.55 (1.7.1)
 * Generated at: 2016-04-19 16:25:03 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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

      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t<meta charset=\"UTF-8\"/>\n");
      out.write("\t<title>Bibliotheque</title>\n");
      out.write("\t<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<h1 class=\"center\">Ajouter un livre</h1>\n");
      out.write("\t<!-- formulaire -->\n");
      out.write("\t<form class=\"container\" action=\"index.jsp\">\n");
      out.write("\t\t<input type=\"text\" name=\"auteur\" placeholder=\"Auteur\" value='");
      out.print( request.getParameter("auteur") );
      out.write("'>\n");
      out.write("\t\t<br/>\n");
      out.write("\t\t<input type=\"text\" name=\"titre\" placeholder=\"Titre\" value='");
      out.print( request.getParameter("titre") );
      out.write("'>\n");
      out.write("\t\t<br/>\n");
      out.write("\t\t<label for=\"annee\">Annee</label>\n");
      out.write("\t\t<input type=\"number\" name=\"annee\" min=\"0\" max=\"2100\" value='");
      out.print( request.getParameter("annee") );
      out.write("'>\n");
      out.write("\t\t<br/>\n");
      out.write("\t\t<button class=\"btn\" type=\"submit\">Send</button>\n");
      out.write("\t</form>\n");
      out.write("\t<h1 class=\"center\">Livre</h1>\n");
      out.write("\t<!--rÃ©sultat obtenu lors du clic sur Send -->\n");
      out.write("\t<div class=\"container center\">\n");
      out.write("\t\t<div class=\"section\">\n");
      out.write("\t\t<p>Auteur :</p>\n");
      out.write("\t\t");
      out.print( request.getParameter("auteur") );
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"divider\"></div>\n");
      out.write("\t\t<div class=\"section\">\n");
      out.write("\t\t<p>Titre :</p>\n");
      out.write("\t\t");
      out.print( request.getParameter("titre") );
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"divider\"></div>\n");
      out.write("\t\t<div class=\"section\">\n");
      out.write("\t\t<p>Annee :</p>\n");
      out.write("\t\t");
      out.print( request.getParameter("annee") );
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
