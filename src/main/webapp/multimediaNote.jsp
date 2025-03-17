<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ucl.ac.uk.notesapp.model.entity.*, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Multimedia Note</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/multimediaNote.css">
</head>
<body>
<%
    boolean isEdit = "edit".equals(request.getAttribute("action"));
    Note note = isEdit ? (Note) request.getAttribute("note") : null;
    MultimediaNote multimediaNote = isEdit ? (MultimediaNote) note : null;
%>
<div class="form-container">
    <h2><%= isEdit ? "Edit Multimedia Note" : "New Multimedia Note" %></h2>
    <form id="multimediaForm" action="<%= isEdit ? "/crud/updateNote?id=" + note.getId() : "/crud/createNote" %>"
          method="post" enctype="multipart/form-data">
        <input type="hidden" name="noteType" value="multimedia">
        <% if (isEdit) { %>
        <input type="hidden" name="id" value="<%= note.getId() %>">
        <% } %>

        <input type="text" name="title" id="noteTitle" placeholder="Input title..." required
               value="<%= isEdit ? note.getTitle() : "" %>">

        <select name="subject" id="noteSubject">
            <% List<String> subjects = (List<String>) request.getAttribute("subjects");
                for (String subject : subjects) { %>
            <option value="<%= subject %>" <%= isEdit && subject.equals(note.getSubject()) ? "selected" : "" %>>
                <%= subject %>
            </option>
            <% } %>
        </select>

        <div class="tags-section">
            <% List<String> noteTags = isEdit ? note.getTags() : null; %>
            <input type="checkbox" name="tags" value="Star" <%= (noteTags != null && noteTags.contains("Star")) ?
                    "checked" : "" %> id="tagStar"><label for="tagStar">Star</label>
            <input type="checkbox" name="tags" value="Urgent" <%= (noteTags != null && noteTags.contains("Urgent")) ?
                    "checked" : "" %> id="tagUrgent"><label for="tagUrgent">Urgent</label>
            <input type="checkbox" name="tags" value="Pending" <%= (noteTags != null && noteTags.contains("Pending")) ?
                    "checked" : "" %> id="tagPending"><label for="tagPending">Pending</label>
            <input type="checkbox" name="tags" value="Review" <%= (noteTags != null && noteTags.contains("Review")) ?
                    "checked" : "" %> id="tagReview"><label for="tagReview">Review</label>
            <input type="checkbox" name="tags" value="Idea" <%= (noteTags != null && noteTags.contains("Idea")) ?
                    "checked" : "" %> id="tagIdea"><label for="tagIdea">Idea</label>
        </div>

        <!-- Container for media items (images, links, descriptions) -->
        <div id="mediaItems">
            <% if (isEdit && multimediaNote.getMediaItems() != null && !multimediaNote.getMediaItems().isEmpty()) {
                int i = 1;
                for (MediaItem item : multimediaNote.getMediaItems()) { %>
            <div class="media-item">
                <% if (item.getImageUrl() != null) { %>
                <p>Current Image: <img src="<%= item.getImageUrl() %>" alt="Current Image"></p>
                <input type="hidden" name="existingImageUrl<%= i %>" value="<%= item.getImageUrl() %>">
                <% } %>
                <input type="file" name="imageFile<%= i %>" accept="image/*">
                <input type="text" name="hyperLink<%= i %>" placeholder="Hyperlink (optional)" value="<%= item.getHyperLink() != null ? item.getHyperLink() : "" %>">
                <textarea name="description<%= i %>" placeholder="Description (optional)"><%= item.getDescription() != null ? item.getDescription() : "" %></textarea>
            </div>
            <% i++; }
            } else { %>
            <!-- Default media item for new note -->
            <div class="media-item">
                <input type="file" name="imageFile1" accept="image/*">
                <input type="text" name="hyperLink1" placeholder="Hyperlink (optional)">
                <textarea name="description1" placeholder="Description (optional)"></textarea>
            </div>
            <% } %>
        </div>

        <button type="button" onclick="addMediaItem()">Add Media</button>
        <button type="button" onclick="previewMedia()">Preview</button>
        <button type="submit">Save</button>
        <a href="/load/loadNoteList">Back to All Notes</a>
    </form>
</div>

<div class="preview-container">
    <h2>Preview</h2>
    <div id="previewArea"></div>
</div>

<script>
    let mediaCount = <%= isEdit && multimediaNote.getMediaItems() != null ? multimediaNote.getMediaItems().size() : 1 %>;

    //Adds a new media item section to the form dynamically.
    function addMediaItem() {
        mediaCount++;
        const container = document.getElementById("mediaItems");
        const newItem = document.createElement("div");
        newItem.className = "media-item";
        newItem.innerHTML = `
            <input type="file" name="imageFile${mediaCount}" accept="image/*">
            <input type="text" name="hyperLink${mediaCount}" placeholder="Hyperlink (optional)">
            <textarea name="description${mediaCount}" placeholder="Description (optional)"></textarea>
        `;
        container.appendChild(newItem);
    }

    function previewMedia() {
        const previewArea = document.getElementById("previewArea");
        previewArea.innerHTML = "";

        const mediaItems = document.querySelectorAll(".media-item");
        mediaItems.forEach((item, index) => {
            const i = index + 1;
            const fileInput = item.querySelector("input[name='imageFile" + i + "']");
            const hyperLinkInput = item.querySelector("input[name='hyperLink"+ i + "']");
            const descriptionInput = item.querySelector("textarea[name='description" + i +"']");
            const existingImageUrl = item.querySelector("input[name='existingImageUrl"+ i + "']")?.value;

            const hyperLink = hyperLinkInput?.value || "";
            const description = descriptionInput?.value || "";
            const mediaDiv = document.createElement("div");


            if (fileInput?.files?.[0]) {
                const img = document.createElement("img");
                img.src = URL.createObjectURL(fileInput.files[0]);
                mediaDiv.appendChild(img);
            }

            else if (existingImageUrl) {
                const img = document.createElement("img");
                img.src = existingImageUrl;
                mediaDiv.appendChild(img);
            }

            if (hyperLink) {
                const link = document.createElement("a");
                link.href = hyperLink;
                link.textContent = hyperLink;
                link.target = "_blank";
                mediaDiv.appendChild(link);
            }

            if (description) {
                const desc = document.createElement("p");
                desc.textContent = description;
                mediaDiv.appendChild(desc);
            }

            if (mediaDiv.children.length > 0) {
                previewArea.appendChild(mediaDiv);
            }
        });
    }
</script>
</body>
</html>