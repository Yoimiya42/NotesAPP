
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
<%
    boolean isEdit = "edit".equals(request.getAttribute("action"));
    Note note = isEdit ? (Note) request.getAttribute("note") : null;
%>

<div class="form-container">
    <p><%= request.getAttribute("action") != null ? request.getAttribute("action") : "create" %></p>
    <form id="noteForm" action="<%= isEdit ?
        "${pageContext.request.contextPath}/crud/updateNote?id=" + note.getId() :
        "${pageContext.request.contextPath}/crud/createNote" %>" method="post">
        <input type="hidden" name="noteType" value="text">
        <% if (isEdit) { %>
        <input type="hidden" name="id" value="<%= note.getId() %>">
        <% } %>

        <!-- Note Title -->
        <input type="text" name="title" id="noteTitle" placeholder="Input title..." required
               value="<%= isEdit ? note.getTitle().replace("\"", "&quot;") : "" %>">

        <!-- Note Subject -->
        <select name="subject" id="noteSubject">
            <% List<String> subjects = (List<String>) request.getAttribute("subjects");
                for (String subject : subjects) { %>
            <option value="<%= subject %>" <%= isEdit && subject.equals(note.getSubject()) ? "selected" : "" %>><%= subject %></option>
            <% } %>
        </select>

        <hr>
        <br>

        <!-- Note Tags -->
        <div class="tags-section">
            <% List<String> noteTags = isEdit ? note.getTags() : null; %>
            <input type="checkbox" name="tags" value="Star" <%= (noteTags != null && noteTags.contains("Star")) ? "checked" : "" %> id="tagStar"><label for="tagStar">Star</label>
            <input type="checkbox" name="tags" value="Urgent" <%= (noteTags != null && noteTags.contains("Urgent")) ? "checked" : "" %> id="tagUrgent"><label for="tagUrgent">Urgent</label>
            <input type="checkbox" name="tags" value="Pending" <%= (noteTags != null && noteTags.contains("Pending")) ? "checked" : "" %> id="tagPending"><label for="tagPending">Pending</label>
            <input type="checkbox" name="tags" value="Review" <%= (noteTags != null && noteTags.contains("Review")) ? "checked" : "" %> id="tagReview"><label for="tagReview">Review</label>
            <input type="checkbox" name="tags" value="Idea" <%= (noteTags != null && noteTags.contains("Idea")) ? "checked" : "" %> id="tagIdea"><label for="tagIdea">Idea</label>
        </div>

        <!-- Note Content -->
        <textarea rows="14" cols="42" name="content" id="noteContent" placeholder="Enter...">
            <%= isEdit && note instanceof TextNote ? ((TextNote) note).getContent(): "" %></textarea>
    </form>

    <button type="button" id="deleteButton" style="display: none" onclick="deleteNote()">Delete</button>
    <a href="/load/loadNoteList" class="back-link" id="backLink">Back to All Notes</a>
    <span class="save-notice" id="saveNotice">Saved!</span>
</div>

<script>
    let noteId = "${action}" === "edit" ? "${note.id}" : null;

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