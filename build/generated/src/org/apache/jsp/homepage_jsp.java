package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import entity.News;
import DAO.DAONews;
import java.util.Vector;

public final class homepage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
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
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"vn\">\n");
      out.write("\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "setUpPage.jsp", out, false);
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <style>\n");
      out.write("               \n");
      out.write("                .img-fluid {\n");
      out.write("                    max-width: 100%;\n");
      out.write("                    height: 200px !important;\n");
      out.write("                }\n");
      out.write("            </style>\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "nav.jsp", out, false);
      out.write("\n");
      out.write("        </header>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("        <section class=\"sectionFilter\">\n");
      out.write("            <!--<form id=\"myForm\" action=\"\" method=\"get\">-->\n");
      out.write("            <label class=\"filter\">Filter:</label>\n");
      out.write("            <input onclick=\"clickNews()\" class=\"inputF\" type=\"radio\" name=\"filter\" id=\"news\" value=\"NewsF\">\n");
      out.write("            <label class=\"inputF1\" for=\"news\">News</label>\n");
      out.write("            <input onclick=\"clickNews()\" class=\"inputF\" type=\"radio\" name=\"filter\" id=\"product\" value=\"ProductsF\">\n");
      out.write("            <label class=\"inputF1\" for=\"product\">Product</label>\n");
      out.write("            <input onclick=\"clickNews()\" class=\"inputF\" type=\"radio\" name=\"filter\" id=\"show\" value=\"showF\" checked=\"\">\n");
      out.write("            <label class=\"inputF1\" for=\"show\">Show All</label>\n");
      out.write("            <!--</form>-->\n");
      out.write("            <input id=\"txtS\" oninput=\"autoSearch(this)\" class=\"form-control me-2\" name=\"txt\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\n");
      out.write("        </section>\n");
      out.write("\n");
      out.write("        <section class=\"containerLast\">\n");
      out.write("            <div class=\"left-half\">\n");
      out.write("                <!--<div class=\"div-left\">-->\n");
      out.write("                <article>\n");
      out.write("                    <h1 class=\"most\"><i class=\"fas fa-fire\"></i>Most View<i class=\"fas fa-fire\"></i></h1>\n");
      out.write("                </article>\n");
      out.write("                <div class=\"row rowleft row-cols-1\">\n");
      out.write("                    ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                    <a href=\"\"></a>\n");
      out.write("                </div>\n");
      out.write("                <!--</div>-->\n");
      out.write("            </div>\n");
      out.write("            <div class=\"right-half\">\n");
      out.write("                <div id=\"content2\"  class=\"row row-cols-1 row-cols-sm-2 row-cols-md-3\">\n");
      out.write("                    ");
      if (_jspx_meth_c_forEach_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <button style=\"float: right; margin-top: 20px;\" onclick=\"loadMore()\" type=\"button\" class=\"btn btn-success\">Load More</button>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </section>\n");
      out.write("\n");
      out.write("        <footer>\n");
      out.write("            <div>\n");
      out.write("                <script src=\"https://apps.elfsight.com/p/platform.js\" defer></script>\n");
      out.write("                <div class=\"elfsight-app-959efa1d-6a33-411f-8e32-959a0864ffa9\"></div>\n");
      out.write("                <div>\n");
      out.write("                    <p>Contact Me</p>\n");
      out.write("                    <a class=\"a1\" href=\"https://www.facebook.com/\" aria-label=\"facebook\"><i\n");
      out.write("                            class=\"fab fa-facebook-square fa-5x\"></i></a>\n");
      out.write("                    <a class=\"a2\" href=\"https://www.facebook.com/\" aria-label=\"facebook\"><i\n");
      out.write("                            class=\"fab fa-instagram-square fa-5x\"></i></a>\n");
      out.write("                    <a class=\"a3\" href=\"https://www.facebook.com/\" aria-label=\"facebook\"><i\n");
      out.write("                            class=\"fab fa-youtube-square fa-5x\"></i></a>\n");
      out.write("                </div>\n");
      out.write("        </footer>\n");
      out.write("        <!--<script src=\"js/homeJS.js\"></script>-->\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n");
      out.write("        <script>\n");
      out.write("\n");
      out.write("                    function loadMore() {\n");
      out.write("                        var num = document.getElementsByClassName(\"numNews\").length;\n");
      out.write("//                        var check = document.getElementsByClassName(\"pro\").innerHTML;\n");
      out.write("                        var x = document.querySelector('input[name=\"filter\"]:checked').value;\n");
      out.write("                        var txt = document.getElementById(\"txtS\").value;\n");
      out.write("                        $.ajax({\n");
      out.write("                            url: \"/MyProject/loadNews\",\n");
      out.write("                            type: \"get\", //send it through get method\n");
      out.write("                            data: {\n");
      out.write("                                exists: num,\n");
      out.write("                                typeF1: x,\n");
      out.write("                                searchT: txt\n");
      out.write("                            },\n");
      out.write("                            success: function (data) {\n");
      out.write("                                var row = document.getElementById(\"content2\");\n");
      out.write("                                row.innerHTML += data;\n");
      out.write("                            },\n");
      out.write("                            error: function (xhr) {\n");
      out.write("                                //Do Something to handle error\n");
      out.write("                            }\n");
      out.write("                        });\n");
      out.write("                    }\n");
      out.write("                    function clickNews() {\n");
      out.write("                        var x = document.querySelector('input[name=\"filter\"]:checked').value;\n");
      out.write("                        $.ajax({\n");
      out.write("                            url: \"/MyProject/loadByFilter\",\n");
      out.write("                            type: \"get\", //send it through get method\n");
      out.write("                            data: {\n");
      out.write("                                exists1: x\n");
      out.write("                            },\n");
      out.write("                            success: function (data) {\n");
      out.write("                                var row = document.getElementById(\"content2\");\n");
      out.write("                                row.innerHTML = data;\n");
      out.write("                            },\n");
      out.write("                            error: function (xhr) {\n");
      out.write("                                //Do Something to handle error\n");
      out.write("                            }\n");
      out.write("                        });\n");
      out.write("                    }\n");
      out.write("                    function autoSearch(text) {\n");
      out.write("                        var txtSearch = text.value;\n");
      out.write("                        var x = document.querySelector('input[name=\"filter\"]:checked').value;\n");
      out.write("                        $.ajax({\n");
      out.write("                            url: \"/MyProject/search\",\n");
      out.write("                            type: \"get\", //send it through get method\n");
      out.write("                            data: {\n");
      out.write("                                txt: txtSearch,\n");
      out.write("                                typeF1: x\n");
      out.write("                            },\n");
      out.write("                            success: function (data) {\n");
      out.write("                                var row = document.getElementById(\"content2\");\n");
      out.write("                                row.innerHTML = data;\n");
      out.write("                            },\n");
      out.write("                            error: function (xhr) {\n");
      out.write("                                //Do Something to handle error\n");
      out.write("                            }\n");
      out.write("                        });\n");
      out.write("                    }\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("\n");
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

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setVar("o1");
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${news}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                        <section class=\"container1 containerLast-Left\">\n");
          out.write("                            <div class=\"left-half1 divLeft\">\n");
          out.write("                                 <a  href=\"detailControl?newID=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${o3.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">\n");
          out.write("                                <img width=\"100%\" height=\"100%\" src=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${o1.urlImage}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" class=\"imgLeft\" alt=\"\">\n");
          out.write("                                 </a>\n");
          out.write("                            </div>\n");
          out.write("                            <div class=\"right-half1 divLeft\">\n");
          out.write("                                <a class=\"text\" href=\"detailControl?newID=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${o1.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">\n");
          out.write("                                    ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${o1.title}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\n");
          out.write("                                </a> \n");
          out.write("                            </div>\n");
          out.write("                        </section>\n");
          out.write("                    ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }

  private boolean _jspx_meth_c_forEach_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_1.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_1.setParent(null);
    _jspx_th_c_forEach_1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${newsRight}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_1.setVar("o3");
    int[] _jspx_push_body_count_c_forEach_1 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_1 = _jspx_th_c_forEach_1.doStartTag();
      if (_jspx_eval_c_forEach_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                        <div class=\"numNews text\" class=\"col\">\n");
          out.write("                            <a  href=\"detailControl?newID=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${o3.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">\n");
          out.write("                                 <img src=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${o3.urlImage}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" class=\"img-fluid newsImg\" alt=\"...\">\n");
          out.write("                            \n");
          out.write("                            </a>\n");
          out.write("                           <div class=\"text-text\">\n");
          out.write("                                <a class=\" text\"\n");
          out.write("                                   href=\"detailControl?newID=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${o3.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">\n");
          out.write("                                    ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${o3.title}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\n");
          out.write("                                </a> \n");
          out.write("\n");
          out.write("                            </div>\n");
          out.write("\n");
          out.write("                        </div>\n");
          out.write("                    ");
          int evalDoAfterBody = _jspx_th_c_forEach_1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_1.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_1);
    }
    return false;
  }
}
