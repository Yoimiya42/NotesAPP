<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>
    <form action="/createNote" method="post">
        <input type="text" name="title" placeholder="Input title...", required>

        <input type="checkbox" name="labels" value="label1">LABEL1
        <input type="checkbox" name="labels" value="label2">LABEL2
        <input type="checkbox" name="labels" value="label3">LABEL3
        <br>
        <textarea rows="7" cols="42" name="content" placeholder="Enter..."></textarea>
        <button type="submit" onclick="submit()">Submit!</button>
    </form>

<%--    <script>--%>
<%--        function submit()--%>
<%--        {--%>
<%--            document.write("Submitted");--%>
<%--        }--%>
<%--    </script>--%>
</body>
</html>