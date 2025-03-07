package ucl.ac.uk.notesapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ucl.ac.uk.notesapp.controller.NoteService;
import ucl.ac.uk.notesapp.controller.impl.NoteServiceImpl;
import ucl.ac.uk.notesapp.model.entity.PlainTextNote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


@WebServlet("/createNote")
public class CreateNoteServlet extends HttpServlet {

	private NoteService noteService = new NoteServiceImpl();

	public CreateNoteServlet() {System.out.println("Initialized Note Servlet.");}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.getWriter().write("<p>doGet()...</p>");
		System.out.println("doGet()");
		System.out.println(req.getContextPath().toString());
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("doPost()");

		String title = req.getParameter("title");
		ArrayList<String> labels = new ArrayList<>(Arrays.asList(req.getParameter("labels")));
		String content = req.getParameter("content");

		PlainTextNote note = new PlainTextNote();
		note.setTitle(title);
		note.setTags(labels);
		note.setContent(content);

		noteService.addNote(note);

	}


}
