<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% response.sendRedirect(request.getContextPath() + "/home"); %>--%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
</head>

<body>
    <a href="/load/loadNoteList">All Notes</a>
    <a href="/load/loadForm?mode=create">Create Note</a>

    <hr>
    <button onclick="showBox()">New Subject</button>
    <div id="inputBox" style="display:none">
        <input type="text" id="subjectInput" maxlength="20" placeholder="new subject">
        <button onclick="submitData()">Submit</button>
        <button onclick="hideBox()">Cancel</button>
    </div>

    <hr>

    <a href="searchNote.jsp">Search</a>
    <script>

        function showBox(){
            document.getElementById("inputBox").style.display="block";
        }
        function hideBox(){
            document.getElementById("inputBox").style.display="none";
        }
        function submitData()
        {
            fetch("/note/addSubject",
            {
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
                })

        }


    </script>


</body>
</html>
