
<%@ page import="ucl.ac.uk.notesapp.model.entity.Note" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All Notes</title>
    <style>
        .sort-container {
            margin: 20px;
        }
        #sortSelect {
            padding: 5px;
            font-size: 16px;
        }
        .note-container {
            width: 90%; /* 控制整体宽度 */
            margin: 0 auto; /* 居中 */
        }
        .note-block {
            display: flex; /* 使用 flex 布局 */
            justify-content: space-between; /* 左右分布 */
            align-items: center; /* 垂直居中 */
            width: 100%; /* 横向细长 */
            height: 60px; /* 高度稍矮一些 */
            background-color: #f9f9f9; /* 浅灰背景 */
            margin: 10px 0; /* 上下间距 */
            padding: 0 20px; /* 左右内边距 */
            border: 1px solid #ddd; /* 边框 */
            border-radius: 5px; /* 圆角 */
            text-decoration: none;
            color: #333;
            cursor: pointer;
            transition: background-color 0.3s; /* 悬停动画 */
        }
        .note-block:hover {
            background-color: #e8e8e8; /* 悬停时颜色变化 */
        }
        .note-title {
            flex: 1; /* 标题占主要空间 */
            text-align: left;
            font-size: 16px;
        }
        .note-times {
            flex: 0 0 300px; /* 时间区域固定宽度 */
            text-align: right;
            font-size: 14px;
            color: #666; /* 时间颜色稍浅 */
        }
        .note-times p {
            margin: 5px 0; /* 时间间距 */
        }
        .divider {
            border-bottom: 1px solid #ccc; /* 水平分割线 */
            margin: 5px 0; /* 分割线间距 */
        }
        form {
            width: 100%; /* 表单占满容器 */
            margin: 0;
            padding: 0;
        }
        button.note-block {
            background: none; /* 移除按钮默认背景 */
            border: none; /* 移除按钮默认边框 */
            padding: 0; /* 重置内边距 */
        }
    </style>
</head>

<body>
    <div class="sort-container">
        <label for="sortSelect">Sort By: </label>
        <form action="/note/sortNotes" method="post" >
        <select id="sortSelect" name="sortBy" onchange="this.form.submit()">
            <option value="modifiedTime_desc">Modified Time(Desc) </option>
            <option value="modifiedTime_asc">Modified Time(Asc)</option>
            <option value="createdTime_desc">Created Time(Desc)</option>
            <option value="createdTime_asc">Created Time(Asc)</option>
            <option value="title_desc">Title(Desc)</option>
            <option value="title_asc">Title(Asc)</option>
        </select>
        </form>
    </div>


    <div class="note-container" id="noteList">
        <% List<Note> notes = (List<Note>) request.getAttribute("notes");
            for (Note note : notes) {
				if(!note.getSubject().equals("RecycleBin")){%>
        <form action="/note/loadForm" method="post">
            <input type="hidden" name="subject" value="<%=note.getSubject()%>">
            <input type="hidden" name="id" value="<%=note.getCreatedTime()%>">
            <input type="hidden" name="mode" value="edit">
            <button type="submit" class="note-block">
                <span class="note-title"><%=note.getTitle()%></span>
                <div class="note-times">
                    <p>Modified: <%=note.showModifiedTime()%></p>
                    <p>Created: <%=note.showCreatedTime()%></p>
                </div>

            </button>
        </form>
        <div class="divider"></div>
        <% }} %>
    </div>


    <script>

        document.addEventListener("DOMContentLoaded", function() {
            const sortSelect = document.getElementById("sortSelect");
            const currentSortBy = '<%=request.getAttribute("sortBy") %>';

            sortSelect.value = currentSortBy;

            sortSelect.addEventListener("change", function() {
                const sortBy = this.value;
                console.log("Selected sortBy: " + sortBy); //
                window.location.href = "/note/sortNotes?sortBy=" + encodeURIComponent(sortBy);
            });
        });

        <%--document.getElementById("sortSelect").addEventListener("change", function(){--%>
        <%--    const sortOption = this.value;--%>
        <%--    fetch("note/sortNotes", {--%>
        <%--        method : 'post',--%>
        <%--        headers:  {"Content-Type": "application/x-www-form-urlencoded"},--%>
        <%--        body:"sortBy=${sortOption}"--%>
        <%--    })--%>
        <%--});--%>


    </script>
</body>





</html>