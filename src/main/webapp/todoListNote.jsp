<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ucl.ac.uk.notesapp.model.entity.*, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Todo List Note</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/todoListNote.css">
</head>
<body>
<% boolean isEdit = "edit".equals(request.getAttribute("action"));
    Note note = isEdit ? (Note)request.getAttribute("note") : null;%>

<div class="form-container">
    <!-- Form Title -->
    <h2><%= isEdit ? "Edit Todo List Note" : "New Todo List Note" %></h2>

    <form id="todoForm" action="<%= isEdit ?
        "/crud/updateNote?id=" + note.getId() :
        "/crud/createNote" %>" method="post">
        <input type="hidden" name="noteType" value="todolist">
        <% if (isEdit) { %>
        <input type="hidden" name="id" value="<%= note.getId() %>">
        <% } %>

        <!-- Note Title -->
        <input type="text" name="title" id="noteTitle" placeholder="Input title..." required
               value="<%= isEdit ? note.getTitle() : "" %>">

        <!-- Note Subject -->
        <select name="subject" id="noteSubject">
            <% List<String> subjects = (List<String>) request.getAttribute("subjects");
                for(String subject : subjects) { %>
            <option value="<%=subject%>" <%= isEdit && subject.equals(note.getSubject()) ?
                    "selected" : "" %>><%= subject %></option>
            <% } %>
        </select>

        <!-- Note tags -->
        <div class="tags-section">
            <% List<String> noteTags = isEdit ? note.getTags() : null; %>
            <input type="checkbox" name="tags" value="Star" <%= (noteTags != null && noteTags.contains("Star")) ?
                    "checked" : "" %> id="tagStar"><label for="tagStar">Star</label>
            <input type="checkbox" name="tags" value="Urgent" <%= (noteTags != null && noteTags.contains("Urgent")) ?
                    "checked" : "" %> id="tagUrgent"><label for="tagUrgent">Urgent</label>
            <input type="checkbox" name="tags" value="Pending" <%= (noteTags != null && noteTags.contains("Pending")) ?
                    "checked" : "" %> id="tagPending"><label for="tagPending">Pending</label>
            <input type="checkbox" name="tags" value="Review" <%= (noteTags != null && noteTags.contains("Review")) ?
                    "checked" : "" %> id="tagReview"><label for="tagReview">Review</label>
            <input type="checkbox" name="tags" value="Idea" <%= (noteTags != null && noteTags.contains("Idea")) ?
                    "checked" : "" %> id="tagIdea"><label for="tagIdea">Idea</label>
        </div>

        <!-- Todo Items  -->
        <div id="todoItems">
            <% if (isEdit) {
                List<TodoItem> todoItems = ((TodoListNote)request.getAttribute("note")).getTodoItems();
                for (TodoItem item : todoItems) { %>
            <div class="todo-item <%= item.isCompleted() ? "completed" : "" %>">
                <input type="text" name="tasks" value="<%= item.getTask() %>" placeholder="Enter task...">
                <input type="checkbox" name="completed" value="true" <%= item.isCompleted() ? "checked" : "" %>
                       onchange="toggleCompleted(this)">
            </div>
            <% } } else { %>
            <div class="todo-item">
                <input type="text" name="tasks" placeholder="Enter task...">
                <input type="checkbox" name="completed" value="true" onchange="toggleCompleted(this)">
            </div>
            <% } %>
        </div>

        <button type="button" onclick="addTodoItem()">Add Task</button>
        <button type="submit">Save</button>
        <a href="/load/loadNoteList">Back to All Notes</a>

    </form>
</div>

<script>

    function addTodoItem() {
        const container = document.getElementById("todoItems");
        const newItem = document.createElement("div");
        newItem.className = "todo-item";
        newItem.innerHTML = `
            <input type="text" name="tasks" placeholder="Enter task...">
            <input type="checkbox" name="completed" value="true" onchange="toggleCompleted(this)">
        `;
        container.appendChild(newItem);
    }

    function toggleCompleted(checkbox) {
        const todoItem = checkbox.parentElement;
        if (checkbox.checked) {
            todoItem.classList.add("completed");
        } else {
            todoItem.classList.remove("completed");
        }
    }

</script>

</body>
</html>