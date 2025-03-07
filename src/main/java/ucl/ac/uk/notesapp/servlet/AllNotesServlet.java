package ucl.ac.uk.notesapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ucl.ac.uk.notesapp.controller.NoteService;
import ucl.ac.uk.notesapp.controller.impl.NoteServiceImpl;
import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.entity.PlainTextNote;

import java.io.IOException;
import java.util.List;


@WebServlet("/allNotes")
public class AllNotesServlet extends HttpServlet {

	private NoteService noteService = new NoteServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("test");
		List<Note> notes = noteService.allNotes(PlainTextNote.class);
		System.out.println(notes.get(0).getClass());//没有输出

		req.setAttribute("notes", notes);
		req.getRequestDispatcher("allNotes.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


	}
}
