/**
 * Servlet responsible for handling CRUD(Create, Read, Update, Delete) operations for notes
 * in the application.
 *
 * Extends BaseServlet to leverage dynamic URI-based method invocation, acting as the Controller layer
 * in the MVC architecture.
 * This class processes HTTP requests from the View (JSP pages), interacts with
 * Model layer for data operations.
 *
 * @author Luan Fangming
 * @version 9.4.2
 * @since 2025-03-09
 */

package ucl.ac.uk.notesapp.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ucl.ac.uk.notesapp.model.entity.MultimediaNote;
import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.entity.TextNote;
import ucl.ac.uk.notesapp.model.entity.TodoItem;
import ucl.ac.uk.notesapp.model.entity.TodoListNote;
import ucl.ac.uk.notesapp.model.service.Model;
import ucl.ac.uk.notesapp.model.service.ModelFactory;
import ucl.ac.uk.notesapp.util.ImageUtil;

@WebServlet("/crud/*")
@MultipartConfig
public class NoteCrudServlet extends BaseServlet {

	private Model model = ModelFactory.getModel();

	// Creates a new note based on the specified note type
	public void createNote(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String noteType = req.getParameter("noteType");
		System.out.println(noteType);

		Note note;
		switch (noteType != null ? noteType : "text")
		{
			case "multimedia":
				note = new MultimediaNote();
				break;
			case "todolist":
				note = new TodoListNote();
				break;
			case "text":
			default:
				note = new TextNote();
				resp.setContentType("text/plain");
				resp.getWriter().write(note.getId());
				break;
		}

		buildNoteFromRequest(note, req);
		model.createNote(note);

		// Response based on note type
		if("text".equals(noteType)){
			resp.setContentType("text/plain");
			resp.getWriter().write(note.getId());
		}else{
			resp.setContentType("text/html");
			resp.getWriter().write("<script>window.location.href = '/load/loadNoteList';</script>");
		}

	}

	// Updates an existing note identified by its ID with new request parameters
	public void updateNote(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String id =req.getParameter("id");

		Note note = model.findNoteById(id);
		buildNoteFromRequest(note, req);
		model.updateNote(note);

		// Redirect to NoteList.jsp when save button is clicked
		if (note instanceof TodoListNote || note instanceof MultimediaNote) {
			resp.setContentType("text/html");
			resp.getWriter().write("<script>window.location.href = '/load/loadNoteList';</script>");
		}
	}

	public void buildNoteFromRequest(Note note, HttpServletRequest req) throws ServletException, IOException {
		note.setTitle(req.getParameter("title").trim());
		note.setSubject(req.getParameter("subject"));
		note.setTags(req.getParameterValues("tags") != null
				? Arrays.asList(req.getParameterValues("tags"))
				: new ArrayList<>());

		if (note instanceof TextNote) {
			((TextNote) note).setContent(req.getParameter("content"));
		}else if (note instanceof TodoListNote) {
			buildTodoListNote((TodoListNote)note, req);
		} else if (note instanceof MultimediaNote) {
			buildMultimediaNote((MultimediaNote)note, req);
		}
	}

	private void buildTodoListNote(TodoListNote note, HttpServletRequest req) {
		note.getTodoItems().clear();// Clear existing items for update
		String[] tasks = req.getParameterValues("tasks");
		String[] completedValues = req.getParameterValues("completed");

		if (tasks != null) {
			for (int i = 0; i < tasks.length; i++) {
				if (tasks[i].trim().isEmpty()) continue;
				TodoItem todoItem = new TodoItem(tasks[i].trim());

				boolean isCompleted = (completedValues != null &&
									i < completedValues.length &&
									"true".equals(completedValues[i]));
				todoItem.setCompleted(isCompleted);
				note.getTodoItems().add(todoItem);
			}
		}
	}

	private void buildMultimediaNote(MultimediaNote note, HttpServletRequest req) throws ServletException, IOException {
		note.getMediaItems().clear();

		int i = 1;
		while (true) {
			Part filePart = req.getPart("imageFile" + i);
			String hyperLink = req.getParameter("hyperLink" + i);
			String description = req.getParameter("description" + i);
			String existingImageUrl = req.getParameter("existingImageUrl" + i);

			if (filePart == null &&
				hyperLink == null &&
				description == null &&
				existingImageUrl == null) {
				break;
			}

			String imageUrl = existingImageUrl != null ? existingImageUrl : "";
			if (filePart != null && filePart.getSize() > 0) {
				String uploadDir = getServletContext().getRealPath("") + File.separator + "images";
				File dir = new File(uploadDir);
				if (!dir.exists() && !dir.mkdirs()) {
					throw new IOException("Failed to create upload directory: " + uploadDir);
				}
				imageUrl = ImageUtil.saveImage(filePart, uploadDir);
			}

			if (!imageUrl.isEmpty() || (hyperLink != null && !hyperLink.trim().isEmpty()) ||
					(description != null && !description.trim().isEmpty())) {
				note.addMediaItem(
						imageUrl,
						hyperLink != null ? hyperLink.trim() : "",
						description != null ? description.trim() : "");
			}
			i++;
		}
	}

	public void addSubject(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String subject = req.getParameter("newSubject");
		model.createSubject(subject);
	}

	public void renameTitle(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		Note note = model.findNoteById(id);

		if  (title != null && !title.trim().isEmpty())
		{
			note.setTitle(title.trim());
			model.updateNote(note);
		}
	}



	// Move a note to the trash based on its ID.
	public void deleteNote(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String id = req.getParameter("id");
		Note note = model.findNoteById(id);
		model.deleteNote(note);
	}

	public void permanentDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String id = req.getParameter("id");
		Note note = model.findNoteById(id);
		model.permanentDelete(note);
	}

	// Recovers a note from the trash.
	public void recoverNote(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		Note note = model.findNoteById(id);
		model.recoverNote(note);
	}

}
