/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-03-05 14:02:58 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
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

      out.write("<!DOCTYPE HTML>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("  <title>Login Page</title>\r\n");
      out.write("  <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"><title>W3.CSS Template</title>\r\n");
      out.write("  <meta charset=\"UTF-8\">\r\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"../styles/w3.css\">\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"../styles/css.css\">\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"../styles/font-awesome.css\">\r\n");
      out.write("  <script src=\"../js/jquery/jquery_3.4.1.js\"></script>\r\n");
      out.write("  <script src=\"../js/jquery_login_validator.js\"></script>\r\n");
      out.write("  <style>\r\n");
      out.write("    body,h1,h2,h3,h4,h5,h6 {font-family: \"Karma\", sans-serif}\r\n");
      out.write("    .w3-bar-block .w3-bar-item {padding:20px}\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<!-- Sidebar (hidden by default) -->\r\n");
      out.write("<nav class=\"w3-sidebar w3-bar-block w3-card w3-top w3-xlarge w3-animate-left\" style=\"display: none; z-index: 2; width: 40%; min-width: 300px;\" id=\"mySidebar\">\r\n");
      out.write("  <a href=\"javascript:void(0)\" onclick=\"w3_close()\" class=\"w3-bar-item w3-button\">Close Menu</a>\r\n");
      out.write("  <a href=\"../pages/main_page.jsp\" onclick=\"w3_close()\" class=\"w3-bar-item w3-button\">Main page</a>\r\n");
      out.write("  <a href=\"../index.jsp\" onclick=\"w3_close()\" class=\"w3-bar-item w3-button\">Auto sale</a><a href=\"/registerUser\" onclick=\"w3_close()\" class=\"w3-bar-item w3-button\">Register</a>\r\n");
      out.write("</nav>\r\n");
      out.write("\r\n");
      out.write("<!-- Top menu -->\r\n");
      out.write("<div class=\"w3-top\">\r\n");
      out.write("  <div class=\"w3-white w3-xlarge\" style=\"max-width:1200px;margin:auto\">\r\n");
      out.write("    <div class=\"w3-button w3-padding-16 w3-left\" onclick=\"w3_open()\">\r\n");
      out.write("      <div style=\"width: 20px; height: 3px; background-color: black; margin: 4px 0;\"></div>\r\n");
      out.write("      <div style=\"width: 20px; height: 3px; background-color: black; margin: 4px 0;\"></div>\r\n");
      out.write("      <div style=\"width: 20px; height: 3px; background-color: black; margin: 4px 0;\"></div>\r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    <div class=\"w3-center w3-padding-16\">Car sale</div>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
      out.write("    <div class=\"w3-main w3-content w3-padding\" style=\"max-width:1200px;margin-top:100px\">\r\n");
      out.write("      <div style=\"color:#c70039;position: fixed;margin-left: 28%;\">");
      if (_jspx_meth_c_005fout_005f0(_jspx_page_context))
        return;
      out.write("</div>\r\n");
      out.write("      <div style=\"margin-top: 4%;\">\r\n");
      out.write("        <!-- insert the page content here -->\r\n");
      out.write("        <form name=\"login\" method=\"post\" action=\"../loginUser\" style=\"text-align: center;\" onsubmit=\"return loginValidation();\">\r\n");
      out.write("        <table style=\"margin-left: auto;margin-right: auto; width: 100%\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td style=\"text-align: right;width: 15%;\">Login</td>\r\n");
      out.write("            <td style=\"width: 10%;\"><input class=\"reg\" type=\"text\" name=\"login\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['login']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("            <td style=\"width: 15%; color:#c70039;\" id=\"login_error\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope[\"log_error\"]}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td style=\"text-align: right;\">Password</td>\r\n");
      out.write("            <td><input class=\"reg\" type=\"password\" name=\"password\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${password}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("            <td style=\"width: 15%; color:#c70039;\" id=\"password_error\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope[\"pas_error\"]}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("        <input type=\"submit\" value=\"Login\"/>\r\n");
      out.write("        </form>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  <div><hr id=\"about\">\r\n");
      out.write("\r\n");
      out.write("  <!-- About Section -->\r\n");
      out.write("  \r\n");
      out.write("  <hr></div>\r\n");
      out.write("  \r\n");
      out.write("  <!-- Footer -->\r\n");
      out.write("  <footer class=\"w3-row-padding w3-padding-32\">\r\n");
      out.write("    <div class=\"w3-third\">\r\n");
      out.write("      <p>Powered by <a href=\"https://www.w3schools.com/w3css/default.asp\" target=\"_blank\">w3.css</a></p>\r\n");
      out.write("    </div>\r\n");
      out.write("  \r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("  </footer>\r\n");
      out.write("\r\n");
      out.write("<!-- End page content -->\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("// Script to open and close sidebar\r\n");
      out.write("function w3_open() {\r\n");
      out.write("  document.getElementById(\"mySidebar\").style.display = \"block\";\r\n");
      out.write("}\r\n");
      out.write(" \r\n");
      out.write("function w3_close() {\r\n");
      out.write("  document.getElementById(\"mySidebar\").style.display = \"none\";\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent(null);
    // /pages/login.jsp(43,67) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['user_error']}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }
}
