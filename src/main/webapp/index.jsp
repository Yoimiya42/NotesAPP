<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<div class="content">
    <!-- Logo -->
    <div class="logo">NotesApp</div>

    <!-- All Notes button -->
    <div class="button-group">
        <a href="${pageContext.request.contextPath}/load/loadNoteList">
            <button>All Notes</button></a>
    </div>

    <!-- Create note buttons -->
    <div class="button-group">
        <a href="${pageContext.request.contextPath}/load/loadForm?type=todolist&mode=create">
            <button>Create TodoList Note</button></a>
        <a href="${pageContext.request.contextPath}/load/loadForm?type=multimedia&mode=create">
            <button>Create Multimedia Note</button></a>
        <a href="${pageContext.request.contextPath}/load/loadForm?type=text&mode=create">
            <button>Create Text Note</button></a>
    </div>

    <hr><!-- New subject section -->
    <div class="button-group">
        <button onclick="showBox()">New Subject</button>
        <div id="inputBox">
            <input type="text" id="subjectInput" maxlength="20" placeholder="new subject">
            <button onclick="submitData()">Submit</button>
            <button onclick="hideBox()">Cancel</button>
        </div>
    </div>

    <hr><!-- Search button -->
    <div class="button-group">
        <a href="searchNote.jsp"><button>Search</button></a>
    </div>
</div>

<!-- Footer with name and GitHub link -->
<footer>
    <b>Created by: </b> UCL CS, Luan Fangming (Kevin) | <a href="https://github.com/Yoimiya42" target="_blank">GitHub</a>
</footer>

<script>
    function showBox() {
        document.getElementById("inputBox").style.display = "block";
    }
    function hideBox() {
        document.getElementById("inputBox").style.display = "none";
    }
    function submitData() {
        fetch("/note/addSubject", {
            method: "POST",
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            body: "newSubject=" + encodeURIComponent(document.getElementById("subjectInput").value)
        })
            .then(response => {
                if (response.ok) {
                    document.getElementById("subjectInput").value = "";
                    hideBox();
                    window.alert("Submit successful");
                } else {
                    throw new Error("Error");
                }
            });
    }
</script>
</body>
</html>