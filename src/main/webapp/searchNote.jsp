<%@ page import="ucl.ac.uk.notesapp.model.entity.Note,java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Search Notes</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/searchNote.css">
</head>
<body>
<div class="container">
  <div class="main-content">
    <div class="search-container">
      <form action="/task/searchNote" method="post">
        <input type="text" name="searchStr" value="${searchStr != null ? searchStr : ''}" required placeholder="Enter search term">
        <label><input type="radio" name="option" value="title" ${"title".equals(searchOption) ? "checked" : ""}> Title</label>
        <label><input type="radio" name="option" value="content" ${"content".equals(searchOption) ? "checked" : ""}> Content</label>
        <button type="submit">Search</button>
        <a href="/load/loadNoteList" class="back-link">Back to All Notes</a>
      </form>
    </div>

    <div class="note-container" id="noteList">
      <%
        List<Note> searchResults = (List<Note>) request.getAttribute("searchResults");
        if (searchResults == null || searchResults.isEmpty()) {
          String searchStr = (String) request.getAttribute("searchStr");
          out.print("<div class='empty-message'>" + (searchStr != null ?
                  "No notes found matching \"" + searchStr + "\" in " + request.getAttribute("searchOption") :
                  "Please enter a search term") + "</div>");
        } else {
          for (Note note : searchResults) {
      %>
      <div class="note-block" data-note-id="<%=note.getId()%>">
        <form action="/load/loadForm" method="post">
          <input type="hidden" name="id" value="<%=note.getId()%>">
          <input type="hidden" name="mode" value="edit">
          <button type="submit" class="note-button">
            <span class="note-title"><%=note.getTitle()%></span>
            <div class="note-times">
              <p>Modified: <%=note.showModifiedTime()%></p>
              <p>Created: <%=note.showCreatedTime()%></p>
            </div>
          </button>
        </form>
        <div class="note-actions">
          <button class="action-btn rename-btn" data-note-id="<%=note.getId()%>"
                  onclick="const newTitle = prompt('Enter new title:');
                          if (newTitle) {
                          this.closest('.note-block').querySelector('.note-title').textContent = newTitle;
                          fetch('/crud/renameTitle', {
                          method: 'POST',
                          headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                          body: 'id=<%=note.getId()%>&title=' + encodeURIComponent(newTitle)
                          });
                          }">Rename</button>
          <form action="/crud/deleteNote" method="post" onsubmit="return handleDelete(event, '<%=note.getId()%>')">
            <input type="hidden" name="id" value="<%=note.getId()%>">
            <button type="submit" class="action-btn delete-btn">Delete</button>
          </form>
        </div>
      </div>
      <% }} %>
    </div>
  </div>
</div>
<script src="/js/noteList.js"></script>
</body>
</html>