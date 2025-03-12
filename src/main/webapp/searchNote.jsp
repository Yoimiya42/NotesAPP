<%@ page import="ucl.ac.uk.notesapp.model.entity.Note" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Search Notes</title>
  <link rel="stylesheet" href="/noteList.css">
  <style>
    .search-container {
      margin: 20px;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="main-content">
    <div class="search-container">
      <form action="/task/searchNote" method="post">
        <input type="text" name="searchStr" value="<%= request.getAttribute("searchStr") != null ? request.getAttribute("searchStr") : "" %>"
               required placeholder="Enter search term">
        <label>
          <input type="radio" name="option" value="title"
            <%= "title".equals(request.getAttribute("searchOption")) ? "checked" : "" %>> Title
        </label>
        <label>
          <input type="radio" name="option" value="content"
            <%= "content".equals(request.getAttribute("searchOption")) ? "checked" : "" %>> Content
        </label>
        <button type="submit">Search</button>
      </form>
      <a href="/load/loadNoteList" class="back-link">Back to All Notes</a>
    </div>

    <div class="note-container" id="noteList">
      <%
        List<Note> searchResults = (List<Note>) request.getAttribute("searchResults");
        if (searchResults == null || searchResults.isEmpty()) {
      %>
      <div class="empty-message">
        <% if (request.getAttribute("searchStr") != null) { %>
        No notes found matching "<%=request.getAttribute("searchStr")%>" in <%=request.getAttribute("searchOption")%>
        <% } else { %>
        Please enter a search term
        <% } %>
      </div>
      <%
      } else {
        for (Note note : searchResults) {
      %>
      <div class="note-block" data-note-id="<%=note.getId()%>">
        <form action="/load/loadForm" method="post" class="note-form">
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
          <button class="action-btn rename-btn" data-note-id="<%=note.getId()%>">Rename</button>
          <form action="/crud/deleteNote" method="post" class="delete-form"
                onsubmit="return handleDelete(event, '<%=note.getCreatedTime()%>')">
            <input type="hidden" name="id" value="<%=note.getId()%>">
            <button type="submit" class="action-btn delete-btn">Delete</button>
          </form>
        </div>
      </div>
      <div class="divider"></div>
      <%
          }
        }
      %>
    </div>
  </div>
</div>
<%--<script src="/noteList.js"></script>--%>
</body>
</html>