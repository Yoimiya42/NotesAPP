<%--
  Created by IntelliJ IDEA.
  User: 14604
  Date: 2025/3/7
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="/note/allNotes">All Notes</a>
    <a href="/note/loadPage?new=t">Create Note</a>

    <button onclick="showBox()">New Subject</button>
    <div id="inputBox" style="display:none">
        <input type="text" id="subjectInput" maxlength="20" placeholder="new subject">
        <button onclick="submitData()" >Submit</button>
        <button onclick="hideBox()">Cancel</button>
    </div>

    <script>
        function showBox(){
            document.getElementById("inputBox").style.display="block";
        }
        function hideBox(){
            document.getElementById("inputBox").style.display="none";
        }
        function submitData()
        {
            fetch("/note/addSubject", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
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
