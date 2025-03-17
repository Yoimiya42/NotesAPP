/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/11.0.4
 * Generated at: 2025-03-17 13:37:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import ucl.ac.uk.notesapp.model.entity.Note;
import java.util.List;

public final class searchNote_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(3);
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("ucl.ac.uk.notesapp.model.entity.Note");
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("  <meta charset=\"UTF-8\">\r\n");
      out.write("  <title>Search Notes</title>\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/css/searchNote.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("  <div class=\"main-content\">\r\n");
      out.write("    <div class=\"search-container\">\r\n");
      out.write("      <form action=\"/task/searchNote\" method=\"post\">\r\n");
      out.write("        <input type=\"text\" name=\"searchStr\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${searchStr != null ? searchStr : ''}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" required placeholder=\"Enter search term\">\r\n");
      out.write("        <label><input type=\"radio\" name=\"option\" value=\"title\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${\"title\".equals(searchOption) ? \"checked\" : \"\"}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("> Title</label>\r\n");
      out.write("        <label><input type=\"radio\" name=\"option\" value=\"content\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${\"content\".equals(searchOption) ? \"checked\" : \"\"}", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("> Content</label>\r\n");
      out.write("        <button type=\"submit\">Search</button>\r\n");
      out.write("        <a href=\"/load/loadNoteList\" class=\"back-link\">Back to All Notes</a>\r\n");
      out.write("      </form>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"note-container\" id=\"noteList\">\r\n");
      out.write("      ");

        List<Note> searchResults = (List<Note>) request.getAttribute("searchResults");
        if (searchResults == null || searchResults.isEmpty()) {
          String searchStr = (String) request.getAttribute("searchStr");
          out.print("<div class='empty-message'>" + (searchStr != null ?
                  "No notes found matching \"" + searchStr + "\" in " + request.getAttribute("searchOption") :
                  "Please enter a search term") + "</div>");
        } else {
          for (Note note : searchResults) {
      
      out.write("\r\n");
      out.write("      <div class=\"note-block\" data-note-id=\"");
      out.print(note.getId());
      out.write("\">\r\n");
      out.write("        <form action=\"/load/loadForm\" method=\"post\">\r\n");
      out.write("          <input type=\"hidden\" name=\"id\" value=\"");
      out.print(note.getId());
      out.write("\">\r\n");
      out.write("          <input type=\"hidden\" name=\"mode\" value=\"edit\">\r\n");
      out.write("          <button type=\"submit\" class=\"note-button\">\r\n");
      out.write("            <span class=\"note-title\">");
      out.print(note.getTitle());
      out.write("</span>\r\n");
      out.write("            <div class=\"note-times\">\r\n");
      out.write("              <p>Modified: ");
      out.print(note.showModifiedTime());
      out.write("</p>\r\n");
      out.write("              <p>Created: ");
      out.print(note.showCreatedTime());
      out.write("</p>\r\n");
      out.write("            </div>\r\n");
      out.write("          </button>\r\n");
      out.write("        </form>\r\n");
      out.write("        <div class=\"note-actions\">\r\n");
      out.write("          <button class=\"action-btn rename-btn\" data-note-id=\"");
      out.print(note.getId());
      out.write("\"\r\n");
      out.write("                  onclick=\"const newTitle = prompt('Enter new title:');\r\n");
      out.write("                          if (newTitle) {\r\n");
      out.write("                          this.closest('.note-block').querySelector('.note-title').textContent = newTitle;\r\n");
      out.write("                          fetch('/crud/renameTitle', {\r\n");
      out.write("                          method: 'POST',\r\n");
      out.write("                          headers: {'Content-Type': 'application/x-www-form-urlencoded'},\r\n");
      out.write("                          body: 'id=");
      out.print(note.getId());
      out.write("&title=' + encodeURIComponent(newTitle)\r\n");
      out.write("                          });\r\n");
      out.write("                          }\">Rename</button>\r\n");
      out.write("          <form action=\"/crud/deleteNote\" method=\"post\" onsubmit=\"return handleDelete(event, '");
      out.print(note.getId());
      out.write("')\">\r\n");
      out.write("            <input type=\"hidden\" name=\"id\" value=\"");
      out.print(note.getId());
      out.write("\">\r\n");
      out.write("            <button type=\"submit\" class=\"action-btn delete-btn\">Delete</button>\r\n");
      out.write("          </form>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("      ");
 }} 
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
      out.write("<script src=\"/js/noteList.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
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
