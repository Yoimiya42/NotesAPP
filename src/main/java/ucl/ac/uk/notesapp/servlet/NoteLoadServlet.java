/**
 * Servlet responsible for loading notes list and notes form pages.
 *
 * Extends BaseServlet to leverage dynamic URI-based method invocation,
 * acting as the Controller layer in the MVC architecture.
 *
 * Handles requests for note creation/editing forms and note list displays.
 *
 * @author Luan Fangming
 * @version 6.2.2
 * @since 2025-03-09
 */

package ucl.ac.uk.notesapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.entity.NoteType;
import ucl.ac.uk.notesapp.model.service.Model;
import ucl.ac.uk.notesapp.model.service.ModelFactory;

import java.io.IOException;
import java.util.List;


@WebServlet("/load/*")
public class NoteLoadServlet extends BaseServlet {

	private Model model = ModelFactory.getModel();

	public void loadForm(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		String mode = req.getParameter("mode");
		String noteType = req.getParameter("type");

		if ("create".equals(mode)) {
			req.setAttribute("action", "create");
		} else {
			Note note = model.findNoteById(req.getParameter("id"));
			noteType = String.valueOf(NoteType.fromNote(note));
			req.setAttribute("note", note);
			req.setAttribute("action", "edit");
		}

		req.setAttribute("subjects", model.getAllSubjects());
		req.setAttribute("noteType", noteType);

		String jspPath;
		switch (noteType.toLowerCase()) {
			case "todolist":
				jspPath = "/todoListNote.jsp";
				break;
			case "multimedia":
				jspPath = "/multimediaNote.jsp";
				break;
			case "text":
			default:
				jspPath = "/TextNote.jsp";
				break;
		}
		req.getRequestDispatcher(jspPath).forward(req, resp);
	}

	public void loadNoteList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String choice = req.getParameter("choice");

		List<Note> notes;
		if (choice == null)
		{
			// Sort by default in chronological order of modification
			notes = model.getSortedNotes("modifiedTime_desc");
			req.setAttribute("currentChoice", "all");
		} else if (choice.equals("trash")) {
			notes = model.getTrash();
			req.setAttribute("currentChoice", "trash");
		} else {
			notes = model.getSubjectNotes(choice);
			req.setAttribute("currentChoice", choice);
		}

		req.setAttribute("notes", notes);
		req.setAttribute("subjects", model.getAllSubjects());
		req.getRequestDispatcher("/NoteList.jsp").forward(req, resp);

	}
}
