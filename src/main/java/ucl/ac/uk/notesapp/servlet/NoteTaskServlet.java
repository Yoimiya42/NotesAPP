/**
 * Servlet responsible for handling note-related tasks such as sorting and searching.
 *
 * Extends BaseServlet to leverage dynamic URI-based method invocation, serving as the
 * Controller layer in the MVC architecture.
 *
 * Processes requests, interacts with the Model layer, and forwards results to the
 * View layer (JSP).
 *
 */



package ucl.ac.uk.notesapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.service.Model;
import ucl.ac.uk.notesapp.model.service.ModelFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/task/*")
public class NoteTaskServlet extends BaseServlet {

	private Model model = ModelFactory.getModel();

	public void sortNotes(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{

		String sortBy = req.getParameter("sortBy");
		List<Note> notes = model.getSortedNotes(sortBy);

		req.setAttribute("notes", notes);
		req.setAttribute("sortBy", sortBy);
		req.setAttribute("subjects", model.getAllSubjects());
		req.getRequestDispatcher("/NoteList.jsp").forward(req, resp);
	}


	public void searchNote(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String searchStr = req.getParameter("searchStr").toLowerCase().trim();
		String searchOption = req.getParameter("option");

		List<Note> searchResults = model.searchNotes(searchStr, searchOption);
		req.setAttribute("searchResults", searchResults);
		req.setAttribute("searchStr", searchStr);
		req.setAttribute("searchOption", searchOption);

		req.getRequestDispatcher("/searchNote.jsp").forward(req, resp);

	}
}


