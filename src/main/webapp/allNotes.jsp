<%@ page import="ucl.ac.uk.notesapp.model.entity.Note" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 14604
  Date: 2025/3/7
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All Notes</title>
</head>
<body>

    <ul>
    <%  List<Note> notes = (List<Note>) request.getAttribute("notes");
        for(Note note : notes) {
         String title = note.getTitle();
    %>
        <li><a href="createNote.jsp"><%=title%></a></li><% } %>
    </ul>

</body>
</html>
