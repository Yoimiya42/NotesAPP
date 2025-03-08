<%@ page import="ucl.ac.uk.notesapp.model.entity.Note" %>
<%@ page import="java.util.List" %>


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
