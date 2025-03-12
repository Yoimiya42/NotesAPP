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


@WebServlet("/task/*")
public class NoteTaskServlet extends BaseServlet {

	private Manager manager = ManagerFactory.getManager();

	public void sortNotes(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{

		String sortBy = req.getParameter("sortBy");
		List<Note> notes = manager.getAllNotesSorted(sortBy);

		req.setAttribute("notes", notes);
		req.setAttribute("sortBy", sortBy);
		req.setAttribute("subjects", manager.getAllSubjects());
		req.getRequestDispatcher("/NoteList.jsp").forward(req, resp);
	}



	public void searchNote(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String searchStr = req.getParameter("searchStr").toLowerCase().trim();
		System.out.println(searchStr); // 没有输出
		String searchOption = req.getParameter("option");
		System.out.println(searchOption); //没有输出

		List<Note> searchResults = manager.searchNotes(searchStr, searchOption);
		req.setAttribute("searchResults", searchResults);
		req.setAttribute("searchStr", searchStr);
		req.setAttribute("searchOption", searchOption);

		req.getRequestDispatcher("/searchNote.jsp").forward(req, resp);

	}
}


