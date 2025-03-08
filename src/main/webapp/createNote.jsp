<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>

    <form action="/note/addNote" method="post">
        <input type="text" name="title" placeholder="Input title...", required>

        <select name="subject">
        <% List<String> subjects = (List<String>) request.getAttribute("subjects");
            for(String subject : subjects)
            { %>
            <option value="<%=subject%>"><%= subject%></option>
            <%}%>
        </select>
        <hr>
        <br>
        <input type="checkbox" name="labels" value="label1">LABEL1
        <input type="checkbox" name="labels" value="label2">LABEL2
        <input type="checkbox" name="labels" value="label3">LABEL3
        <br>
        <textarea rows="7" cols="42" name="content" placeholder="Enter..."></textarea>
        <button type="submit" >Submit!</button>
    </form>

</body>
</html>