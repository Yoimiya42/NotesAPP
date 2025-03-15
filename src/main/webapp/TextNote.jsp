
<%@ page import="java.util.List" %>
<%@ page import="ucl.ac.uk.notesapp.model.entity.Note" %>
<%@ page import="ucl.ac.uk.notesapp.model.entity.TextNote" %>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New Note</title>
    <link rel="stylesheet" href="/css/TextNote.css">
</head>
<body>
<div class="form-container">
    <p>${action}</p>
    <form id="noteForm" action="/crud/createNote" method="post">
        <input type="hidden", name="noteType", value="text">
        <input type="text" name="title" id="noteTitle" placeholder="Input title..." required>
        <select name="subject" id="noteSubject">
            <% List<String> subjects = (List<String>) request.getAttribute("subjects");
                for(String subject : subjects) {%>
            <option value="<%=subject%>"><%= subject %></option>
            <%   } %>
        </select>
        <hr>
        <br>
        <input type="checkbox" name="tags" value="Star">Star
        <input type="checkbox" name="tags" value="Urgent">Urgent
        <input type="checkbox" name="tags" value="Pending">Pending
        <input type="checkbox" name="tags" value="Review">Review
        <input type="checkbox" name="tags" value="Idea">Idea
        <br>
        <textarea rows="14" cols="42" name="content" id="noteContent" placeholder="Enter..."></textarea>
    </form>

    <button type="button" id="deleteButton" style="display: none" onclick="deleteNote()">Delete</button>
    <a href="/load/loadNoteList" class="back-link" id="backLink">Back to All Notes</a>
    <span class="save-notice" id="saveNotice">Saved!</span>
</div>

<script>
    let noteId = "${action}" === "edit" ? "${note.id}" : null;
    console.log(noteId)
    window.onload = function() {
        <% if ("edit".equals(request.getAttribute("action"))) {
            Note note = (Note) request.getAttribute("note");
        %>
        console.log("EDIT");
        document.getElementById("noteTitle").value = "<%= note.getTitle() %>";
        document.getElementById("noteSubject").value = "<%= note.getSubject() %>";
        <% if (note instanceof TextNote) { %>
        document.getElementById("noteContent").value = "<%= ((TextNote) note).getContent() %>";
        <% } %>
        <% for (String tag : note.getTags()) { %>
        document.querySelector("input[name='tags'][value='<%= tag %>']").checked = true;
        <% } %>
        document.getElementById("noteForm").action = "/crud/updateNote?id=<%= note.getId() %>";
        <% } %>
    };

    const form = document.getElementById("noteForm");
    const titleInput = document.getElementById("noteTitle");
    const contentInput = document.getElementById("noteContent");
    const formContainer = document.querySelector(".form-container");


    function saveNote() {
        const title = titleInput.value.trim();
        if (!title) {
            console.log("Title is empty, skipping save");
            return;
        }

        const params = new URLSearchParams();
        const formData = new FormData(form);
        params.append("title", formData.get("title"));
        params.append("subject", formData.get("subject"));
        params.append("content", formData.get("content"));
        const tags = formData.getAll("tags");
        tags.forEach(tag => params.append("tags", tag));

        console.log("Saving, noteId:", noteId);
        let url = noteId ? "/crud/updateNote?id=" + encodeURIComponent(noteId) : "/crud/createNote";
        console.log(url)
        fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: params.toString()
        }).then(response => {
            if (!response.ok) throw new Error("Request failed with status: " + response.status);
            if (!noteId) {
                return response.text().then(id => {
                    noteId = id;
                    form.action = "/crud/updateNote?id=" + id;
                    console.log("Created note with ID:", noteId);
                });
            }
        }).then(() => {
            const notice = document.getElementById("saveNotice");
            notice.classList.add("show");
            setTimeout(() => notice.classList.remove("show"), 1000);
        }).catch(err => console.error("Save failed:", err));
    }

    function debounce(func, wait) {
        let timeout;
        return function() {
            clearTimeout(timeout);
            timeout = setTimeout(func, wait);
        };
    }

    titleInput.addEventListener("blur", debounce(saveNote, 500));
    contentInput.addEventListener("blur", debounce(saveNote, 500));
    formContainer.addEventListener("mouseleave", debounce(saveNote, 500));

    // Save when returning back
    document.getElementById("backLink").addEventListener("click", function(e) {
        e.preventDefault();
        saveNote();
        setTimeout(() => window.location.href = this.href, 500);
    });

</script>
</body>
</html>