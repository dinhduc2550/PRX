package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "setUpPage.jsp", out, false);
      out.write("\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta charset=\"UTF-8\">\r\n");
      out.write("        <title>Login</title>\r\n");
      out.write("        <link rel=\"stylesheet\"  href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/css/style.css\" >\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css\">\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("       \r\n");
      out.write("        <h2 style=\"color: red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${mess2}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</h2>\r\n");
      out.write("        <div class=\"container\" id=\"container\">\r\n");
      out.write("            <div class=\"form-container sign-up-container\">\r\n");
      out.write("                <form action=\"signup\" method=\"get\">\r\n");
      out.write("                    <h1>Create Account</h1>\r\n");
      out.write("                    <p class=\"text-danger\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${mess2}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\r\n");
      out.write("                    <input name=\"rUser\" type=\"text\" placeholder=\"Username\" />\r\n");
      out.write("                    <input name=\"rPass\" id=\"password\" type=\"password\" onkeyup='check();' placeholder=\"Password\" />\r\n");
      out.write("                    <input name=\"rRePass\" id=\"confirm_password\"  onkeyup='check();' type=\"password\" placeholder=\"Confirm Password\" />\r\n");
      out.write("\r\n");
      out.write("                    <span id='message'></span>\r\n");
      out.write("                    <button>Sign Up</button>\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"form-container sign-in-container\">\r\n");
      out.write("                <form action=\"login\" method=\"post\">\r\n");
      out.write("                    <h1>Sign in</h1>\r\n");
      out.write("                    <p class=\"text-danger\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${mess}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\r\n");
      out.write("                    <input value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${u}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" type=\"text\" name=\"uname\" placeholder=\"Username\" />\r\n");
      out.write("                    <input value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${p}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" type=\"password\" name=\"psw\" placeholder=\"Password\" />\r\n");
      out.write("                    <div class=\"form-check\">\r\n");
      out.write("                        <input name=\"remember\" type=\"checkbox\" class=\"form-check-input\" id=\"exampleCheck1\">\r\n");
      out.write("                        <label class=\"form-check-label\" for=\"exampleCheck1\">Remember Me</label>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <a href=\"#\"></a>\r\n");
      out.write("                    <button>Sign In</button>\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"overlay-container\">\r\n");
      out.write("                <div class=\"overlay\">\r\n");
      out.write("                    <div class=\"overlay-panel overlay-left\">\r\n");
      out.write("                        <h1>Welcome Back!</h1>\r\n");
      out.write("                        <p>To keep connected with us please login with your personal info</p>\r\n");
      out.write("                        <button class=\"ghost\" id=\"signIn\">Sign In</button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"overlay-panel overlay-right\">\r\n");
      out.write("                        <h1>Hello, Friend!</h1>\r\n");
      out.write("                        <p>Enter your personal details and start journey with us</p>\r\n");
      out.write("                        <button class=\"ghost\" id=\"signUp\">Sign Up</button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <!--        <footer>\r\n");
      out.write("                    <p>\r\n");
      out.write("                        Created with <i class=\"fa fa-heart\"></i> by\r\n");
      out.write("                        <a target=\"_blank\" href=\"https://florin-pop.com\">Florin Pop</a>\r\n");
      out.write("                        - Read how I created this and how you can join the challenge\r\n");
      out.write("                        <a target=\"_blank\" href=\"https://www.florin-pop.com/blog/2019/03/double-slider-sign-in-up-form/\">here</a>.\r\n");
      out.write("                    </p>\r\n");
      out.write("                </footer>-->\r\n");
      out.write("        <script src=\"js/script.js\"></script>\r\n");
      out.write("    </body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
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
