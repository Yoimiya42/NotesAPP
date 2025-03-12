
<%--<%@ page import="java.util.List" %>--%>
<%--<%@ page import="ucl.ac.uk.notesapp.model.entity.Note" %>--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>New Note</title>--%>
<%--    <style>--%>
<%--        body {--%>
<%--            margin: 0;--%>
<%--            font-family: 'Arial', sans-serif;--%>
<%--            background-color: #000;--%>
<%--            color: #fff;--%>
<%--            display: flex;--%>
<%--            justify-content: center;--%>
<%--            align-items: center;--%>
<%--            height: 100vh;--%>
<%--            padding: 20px 0;--%>
<%--        }--%>
<%--        .form-container {--%>
<%--            background-color: #1a1a1a;--%>
<%--            border: 4px solid #333;--%>
<%--            border-radius: 12px;--%>
<%--            padding: 20px;--%>
<%--            box-shadow: 10px 10px 15px rgba(0, 0, 0, 0.5);--%>
<%--            width: 500px;--%>
<%--            display: flex;--%>
<%--            flex-direction: column;--%>
<%--            gap: 15px;--%>
<%--            position: relative; /* 用于定位保存提示 */--%>
<%--        }--%>
<%--        .form-container input[type="text"],--%>
<%--        .form-container select,--%>
<%--        .form-container textarea {--%>
<%--            width: 100%;--%>
<%--            padding: 10px;--%>
<%--            margin-bottom: 10px;--%>
<%--            background-color: #333;--%>
<%--            border: 1px solid #444;--%>
<%--            border-radius: 5px;--%>
<%--            color: #fff;--%>
<%--            font-size: 14px;--%>
<%--            box-sizing: border-box;--%>
<%--        }--%>
<%--        .form-container textarea {--%>
<%--            resize: vertical;--%>
<%--            min-height: 150px;--%>
<%--        }--%>
<%--        .form-container input[type="checkbox"] {--%>
<%--            margin-right: 10px;--%>
<%--        }--%>
<%--        hr {--%>
<%--            border: 0;--%>
<%--            border-top: 1px solid #444;--%>
<%--            margin: 15px 0;--%>
<%--        }--%>
<%--        .save-notice {--%>
<%--            position: absolute;--%>
<%--            right: 20px;--%>
<%--            top: 10px;--%>
<%--            color: #0f0;--%>
<%--            font-size: 12px;--%>
<%--            display: none; /* 默认隐藏 */--%>
<%--        }--%>
<%--        .save-notice.show {--%>
<%--            display: block; /* 保存时显示 */--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="form-container">--%>
<%--    <p>${action}</p>--%>
<%--    <form id="noteForm" action="/crud/createNote" method="post">--%>
<%--        <input type="text" name="title" id="noteTitle" placeholder="Input title..." required>--%>
<%--        <select name="subject" id="noteSubject">--%>
<%--            <% List<String> subjects = (List<String>) request.getAttribute("subjects");--%>
<%--                for(String subject : subjects) {--%>
<%--                    if(!subject.equals("RecycleBin")) { %>--%>
<%--            <option value="<%=subject%>"><%= subject %></option>--%>
<%--            <%  } } %>--%>
<%--        </select>--%>
<%--        <hr>--%>
<%--        <br>--%>
<%--        <input type="checkbox" name="labels" value="label1">LABEL1--%>
<%--        <input type="checkbox" name="labels" value="label2">LABEL2--%>
<%--        <input type="checkbox" name="labels" value="label3">LABEL3--%>
<%--        <br>--%>
<%--        <textarea rows="7" cols="42" name="content" id="noteContent" placeholder="Enter..."></textarea>--%>
<%--    </form>--%>

<%--    <button type="button" id="deleteButton" style="display: none" onclick="deleteNote()">Delete</button>--%>
<%--    <a href="/load/loadNoteList" class="back-link" id="backLink">Back to All Notes</a>--%>
<%--    <span class="save-notice" id="saveNotice">Saved!</span>--%>
<%--</div>--%>

<%--<script>--%>
<%--    let currentNoteId = "${action}" === "edit" ? "${note.id}" : null;--%>

<%--    function saveNote() {--%>
<%--        const title = document.getElementById("noteTitle").value.trim();--%>
<%--        if (!title) return;--%>

<%--        const params = new URLSearchParams();--%>
<%--        const formData = new FormData(document.getElementById("noteForm"));--%>
<%--        params.append("title", formData.get("title") || "");--%>
<%--        params.append("subject", formData.get("subject") || "");--%>
<%--        params.append("content", formData.get("content") || "");--%>
<%--        const labels = formData.getAll("labels");--%>
<%--        labels.forEach(label => params.append("labels", label));--%>

<%--        const url = currentNoteId ?--%>
<%--            `/crud/updateNote?id=${currentNoteId}` :--%>
<%--            "/crud/createNote";--%>

<%--        fetch(url, {--%>
<%--            method: "POST",--%>
<%--            headers: {--%>
<%--                "Content-Type": "application/x-www-form-urlencoded"--%>
<%--            },--%>
<%--            body: params.toString()--%>
<%--        })--%>
<%--            .then(response => response.text())--%>
<%--            .then(data => {--%>
<%--                if (!currentNoteId && data && data !== "OK") {--%>
<%--                    currentNoteId = data;--%>
<%--                    document.getElementById("noteForm").action = `/crud/updateNote?id=${currentNoteId}`;--%>
<%--                    document.getElementById("deleteButton").style.display = "inline-block";--%>
<%--                }--%>
<%--                document.getElementById("saveNotice").classList.add("show");--%>
<%--            });--%>
<%--    }--%>

<%--    function hideSaveNotice() {--%>
<%--        document.getElementById("saveNotice").classList.remove("show");--%>
<%--    }--%>

<%--    function fillForm(title, subject, content) {--%>
<%--        document.getElementById("noteTitle").value = title || "";--%>
<%--        document.getElementById("noteSubject").value = subject || "";--%>
<%--        document.getElementById("noteContent").value = content || "";--%>
<%--    }--%>

<%--    function deleteNote() {--%>
<%--        fetch(`/crud/deleteNote?id=${currentNoteId}`, { method: "post" });--%>
<%--    }--%>

<%--    if ("${action}" === "edit" && "${note}" !== "") {--%>
<%--        fillForm("${note != null ? note.title : ''}",--%>
<%--            "${note != null ? note.subject : ''}",--%>
<%--            "${note != null ? note.content : ''}");--%>
<%--        document.getElementById("noteForm").action = `/crud/updateNote?id=${note != null ? note.id : ''}`;--%>
<%--        document.getElementById("deleteButton").style.display = "inline-block";--%>
<%--    }--%>

<%--    const formContainer = document.querySelector('.form-container');--%>
<%--    const titleInput = document.getElementById("noteTitle");--%>
<%--    const contentInput = document.getElementById("noteContent");--%>
<%--    let timeoutId = null;--%>

<%--    function debounce(func, wait) {--%>
<%--        return function() {--%>
<%--            clearTimeout(timeoutId);--%>
<%--            timeoutId = setTimeout(func, wait);--%>
<%--        };--%>
<%--    }--%>

<%--    function isInputFocused() {--%>
<%--        return document.activeElement === titleInput ||--%>
<%--            document.activeElement === contentInput;--%>
<%--    }--%>

<%--    formContainer.addEventListener('mouseleave', debounce(() => {--%>
<%--        if (!isInputFocused()) saveNote();--%>
<%--    }, 1000));--%>

<%--    [titleInput, contentInput].forEach(input => {--%>
<%--        input.addEventListener('blur', debounce(saveNote, 1000));--%>
<%--        input.addEventListener('mouseenter', hideSaveNotice);--%>
<%--        input.addEventListener('focus', hideSaveNotice);--%>
<%--    });--%>

<%--    document.getElementById('backLink').addEventListener('click', function(e) {--%>
<%--        e.preventDefault();--%>
<%--        saveNote();--%>
<%--        setTimeout(() => window.location.href = this.href, 500);--%>
<%--    });--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page import="java.util.List" %>
<%@ page import="ucl.ac.uk.notesapp.model.entity.Note" %>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>New Note</title>
    <style>
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            background-color: #000;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            padding: 20px 0;
        }
        .form-container {
            background-color: #1a1a1a;
            border: 4px solid #333;
            border-radius: 12px;
            padding: 20px;
            box-shadow: 10px 10px 15px rgba(0, 0, 0, 0.5);
            width: 500px;
            display: flex;
            flex-direction: column;
            gap: 15px;
            position: relative;
        }
        .form-container input[type="text"],
        .form-container select,
        .form-container textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            background-color: #333;
            border: 1px solid #444;
            border-radius: 5px;
            color: #fff;
            font-size: 14px;
            box-sizing: border-box;
        }
        .form-container textarea {
            resize: vertical;
            min-height: 150px;
        }
        .form-container input[type="checkbox"] {
            margin-right: 10px;
        }
        hr {
            border: 0;
            border-top: 1px solid #444;
            margin: 15px 0;
        }
        .save-notice {
            position: absolute;
            right: 20px;
            top: 10px;
            color: #0f0;
            font-size: 12px;
            display: none;
        }
        .save-notice.show {
            display: block;
        }
    </style>
</head>
<body>
<div class="form-container">
    <p>${action}</p>
    <form id="noteForm" action="/crud/createNote" method="post">
        <input type="text" name="title" id="noteTitle" placeholder="Input title..." required>
        <select name="subject" id="noteSubject">
            <% List<String> subjects = (List<String>) request.getAttribute("subjects");
                for(String subject : subjects) {
                    if(!subject.equals("RecycleBin")) { %>
            <option value="<%=subject%>"><%= subject %></option>
            <%  } } %>
        </select>
        <hr>
        <br>
        <input type="checkbox" name="labels" value="label1">LABEL1
        <input type="checkbox" name="labels" value="label2">LABEL2
        <input type="checkbox" name="labels" value="label3">LABEL3
        <br>
        <textarea rows="7" cols="42" name="content" id="noteContent" placeholder="Enter..."></textarea>
    </form>

    <button type="button" id="deleteButton" style="display: none" onclick="deleteNote()">Delete</button>
    <a href="/load/loadNoteList" class="back-link" id="backLink">Back to All Notes</a>
    <span class="save-notice" id="saveNotice">Saved!</span>
</div>

<script>
    let currentNoteId = "${action}" === "edit" ? "${note.id}" : null;

    function saveNote() {
        const title = document.getElementById("noteTitle").value.trim();
        if (!title) return; // 标题必须存在

        const params = new URLSearchParams();
        const formData = new FormData(document.getElementById("noteForm"));
        params.append("title", formData.get("title"));
        params.append("subject", formData.get("subject"));
        params.append("content", formData.get("content"));
        const labels = formData.getAll("labels");
        labels.forEach(label => params.append("labels", label));

        const url = currentNoteId ?
            "/crud/updateNote?id=" + encodeURIComponent(currentNoteId) :
            "/crud/createNote";


        fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: params.toString()
        })
            .then(response => {
                if (!currentNoteId) { // 创建模式下获取 ID
                    return response.text().then(id => {
                        currentNoteId = id; // 保存 ID
                        document.getElementById("noteForm").action = `/crud/updateNote?id=${id}`;
                        document.getElementById("deleteButton").style.display = "inline-block";
                    });
                }
            })
            .then(() => {
                document.getElementById("saveNotice").classList.add("show");
                setTimeout(hideSaveNotice, 1000); // 1秒后隐藏提示
            });
    }

    function hideSaveNotice() {
        document.getElementById("saveNotice").classList.remove("show");
    }

    function fillForm(title, subject, content) {
        document.getElementById("noteTitle").value = title;
        document.getElementById("noteSubject").value = subject;
        document.getElementById("noteContent").value = content;

        const labelCheckboxes = document.querySelectorAll('input[name="labels"]');

        labelCheckboxes.forEach(checkbox => {
            checkbox.checked = labels.includes(checkbox.value);
        });
    }

    function deleteNote() {
        fetch(`/crud/deleteNote?id=${currentNoteId}`, { method: "post" });
    }

    if ("${action}" === "edit") {
        const tagsString = "${note.tags}";
        const labels = tagsString
            .replace(/^\[|\]$/g, '')
            .split(',')
            .map(label => label.trim());

        fillForm("${note.title}", "${note.subject}", "${note.content}", labels);
        document.getElementById("noteForm").action = `/crud/updateNote?id=${note.id}`;
        document.getElementById("deleteButton").style.display = "inline-block";

    }

    const formContainer = document.querySelector('.form-container');
    const titleInput = document.getElementById("noteTitle");
    const contentInput = document.getElementById("noteContent");

    // 防抖函数，避免频繁保存
    function debounce(func, wait) {
        let timeoutId;
        return function() {
            clearTimeout(timeoutId);
            timeoutId = setTimeout(func, wait);
        };
    }

    // 检查输入框是否聚焦
    function isInputFocused() {
        return document.activeElement === titleInput ||
            document.activeElement === contentInput;
    }

    // 监听鼠标离开 form-container
    formContainer.addEventListener('mouseleave', debounce(() => {
        if (!isInputFocused()) saveNote();
    }, 500));

    // 监听输入框失去焦点
    [titleInput, contentInput].forEach(input => {
        input.addEventListener('blur', debounce(saveNote, 500));
        input.addEventListener('focus', hideSaveNotice);
    });

    // 点击返回时保存并跳转
    document.getElementById('backLink').addEventListener('click', function(e) {
        e.preventDefault();
        saveNote();
        setTimeout(() => window.location.href = this.href, 500); // 等待保存完成
    });
</script>
</body>
</html>