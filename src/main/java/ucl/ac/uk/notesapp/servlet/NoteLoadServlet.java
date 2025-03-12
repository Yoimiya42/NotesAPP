package ucl.ac.uk.notesapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.manager.Manager;
import ucl.ac.uk.notesapp.model.manager.ManagerFactory;

import java.io.IOException;
import java.util.List;


@WebServlet("/load/*")
public class NoteLoadServlet extends BaseServlet {

	private Manager manager = ManagerFactory.getManager();

	public void loadForm(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		String mode = req.getParameter("mode");

		if ("create".equals(mode)) {
			req.setAttribute("action", "create");
		} else {
			Note note = manager.findNoteById(req.getParameter("id"));
			req.setAttribute("note", note);
			req.setAttribute("action", "edit");
		}

		req.setAttribute("subjects", manager.getAllSubjects());
		req.getRequestDispatcher("/form.jsp").forward(req, resp);
	}

	public void loadNoteList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String choice = req.getParameter("choice");

		List<Note> notes;
		if (choice == null)
		{
			notes = manager.getAllNotes();
			req.setAttribute("currentChoice", "all");
		} else if (choice.equals("trash")) {
			notes = manager.getTrash();
			req.setAttribute("currentChoice", "trash");
		} else {
			notes = manager.getSubjectNotes(choice);
			req.setAttribute("currentChoice", choice);
		}

		req.setAttribute("notes", notes);
		req.setAttribute("subjects", manager.getAllSubjects());
		req.getRequestDispatcher("/NoteList.jsp").forward(req, resp);

	}
}
