<%@ page import="ucl.ac.uk.notesapp.model.entity.Note" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Notes</title>
    <link rel="stylesheet" href="/noteList.css">
    <meta name="sortBy" content="<%=request.getAttribute("sortBy") != null ? request.getAttribute("sortBy") : "modifiedTime_desc" %>">
    <meta name="currentChoice" content="<%=request.getAttribute("currentChoice") != null ? request.getAttribute("currentChoice") : "all" %>">
</head>
<body>
<div class="container">
    <!-- 侧边栏 -->
    <div class="sidebar">
        <div id="subjects-container">
            <div>
                <form action="/load/loadNoteList" method="get">
                    <button type="submit" class="subject-btn" data-choice="all">All Notes</button>
                </form>
                <div class="sidebar-divider"></div>

                <% List<String> subjects = (List<String>) request.getAttribute("subjects");
                    if (subjects != null) {
                        for (String subject : subjects) { %>
                <form action="/load/loadNoteList" method="get">
                    <input type="hidden" name="choice" value="<%= subject %>">
                    <button type="submit" class="subject-btn" data-choice="<%= subject %>"><%= subject %></button>
                </form>
                <% }} %>
            </div>
            <div>
                <div class="sidebar-divider"></div>
                <form action="/load/loadNoteList" method="get">
                    <input type="hidden" name="choice" value="trash">
                    <button type="submit" class="subject-btn" data-choice="trash">Trash</button>
                </form>
            </div>
        </div>
    </div>

    <!-- 主内容区域 -->
    <div class="main-content">
        <div class="sort-container">
            <label for="sortSelect">Sort By: </label>
            <form action="/task/sortNotes" method="post">
                <select id="sortSelect" name="sortBy" onchange="this.form.submit()">
                    <option value="modifiedTime_desc">Modified Time (Desc)</option>
                    <option value="modifiedTime_asc">Modified Time (Asc)</option>
                    <option value="createdTime_desc">Created Time (Desc)</option>
                    <option value="createdTime_asc">Created Time (Asc)</option>
                    <option value="title_desc">Title (Desc)</option>
                    <option value="title_asc">Title (Asc)</option>
                </select>
            </form>
        </div>

        <div class="note-container" id="noteList">
            <% List<Note> notes = (List<Note>) request.getAttribute("notes");
                if (notes == null || notes.isEmpty()) { %>
            <div class="empty-message">No notes available</div>
            <% } else {
                String currentChoice = request.getAttribute("currentChoice") != null ? request.getAttribute("currentChoice").toString() : "all";
                for (Note note : notes) {
                    if ("trash".equals(currentChoice) ? note.isInTrash() : !note.isInTrash()) { %>
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
                    <% if ("trash".equals(currentChoice)) { %>
                    <form action="/crud/permanentDelete" method="post" class="delete-form" onsubmit="return handlePermanentDelete(event, '<%=note.getCreatedTime()%>')">
                        <input type="hidden" name="id" value="<%=note.getId()%>">
                        <button type="submit" class="action-btn delete-btn">Permanently Delete</button>
                    </form>
                    <form action="/crud/recoverNote" method="post" class="recover-form" onsubmit="return handleRecover(event, '<%=note.getId()%>')">
                        <input type="hidden" name="id" value="<%=note.getId()%>">
                        <button type="submit" class="action-btn recover-btn">Recover</button>
                    </form>
                    <% } else { %>
                    <form action="/crud/deleteNote" method="post" class="delete-form" onsubmit="return handleDelete(event, '<%=note.getCreatedTime()%>')">
                        <input type="hidden" name="id" value="<%=note.getId()%>">
                        <button type="submit" class="action-btn delete-btn">Delete</button>
                    </form>
                    <% } %>
                </div>
            </div>
            <div class="divider"></div>
            <% }}} %>
        </div>
    </div>
</div>
<script src="/noteList.js"></script>
</body>
</html>