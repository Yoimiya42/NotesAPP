<%--<%@ page import="ucl.ac.uk.notesapp.model.entity.Note" %>--%>
<%--<%@ page import="java.util.List" %>--%>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title>All Notes</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--    <ul>--%>
<%--        <%  List<Note> notes = (List<Note>) request.getAttribute("notes");--%>
<%--            for(Note note : notes) {--%>
<%--        %>--%>
<%--        <li><a href="/note/loadPage?new=f&subject=<%=note.getSubject()%>&id=<%=note.getCreatedTime()%>">--%>
<%--            <%=note.getTitle()%></a></li>--%>
<%--        <% } %>--%>
<%--    </ul>--%>

<%--</body>--%>
<%--</html>--%>
<%@ page import="ucl.ac.uk.notesapp.model.entity.Note" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All Notes</title>
    <style>
        .note-block {
            display: inline-block;
            width: 200px;
            height: 100px;
            background-color: #f0f0f0;
            margin: 10px;
            text-align: center;
            line-height: 100px;
            text-decoration: none;
            color: #333;
            border: 1px solid #ccc;
            cursor: pointer;
        }
        .note-block:hover {
            background-color: #e0e0e0;
        }
        form {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div>
    <% List<Note> notes = (List<Note>) request.getAttribute("notes");
        for (Note note : notes) { %>
    <form action="/note/loadPage" method="post">
        <input type="hidden" name="subject" value="<%=note.getSubject()%>">
        <input type="hidden" name="id" value="<%=note.getCreatedTime()%>">
        <input type="hidden" name="new" value="f">
        <button type="submit" class="note-block"><%=note.getTitle()%></button>
    </form>
    <p>Modified:<%= note.showModifiedTime()%></p>
    <p>Created:<%= note.showCreatedTime()%></p>

    <% } %>
</div>
</body>
</html>