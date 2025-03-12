package ucl.ac.uk.notesapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.entity.TextNote;
import ucl.ac.uk.notesapp.model.manager.Manager;
import ucl.ac.uk.notesapp.model.manager.ManagerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/crud/*")
public class NoteCrudServlet extends BaseServlet {

	private Manager manager = ManagerFactory.getManager();

	public void createNote(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		Note note = new TextNote();
		buildNoteFromRequest(note, req);
		manager.createNote(note);

		resp.setContentType("text/plain");
		resp.getWriter().write(note.getId());
	}

	public void updateNote(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String id =req.getParameter("id");
		System.out.println(id);
		Note note = manager.findNoteById(req.getParameter("id"));
		buildNoteFromRequest(note, req);
		manager.updateDB(note);

	}

	public void buildNoteFromRequest(Note note, HttpServletRequest req)
	{
		note.setTitle(req.getParameter("title").trim());
		note.setSubject(req.getParameter("subject"));
		note.setContent(req.getParameter("content"));
		note.setTags(req.getParameterValues("labels") != null
				? Arrays.asList(req.getParameterValues("labels"))
				: new ArrayList<>());

		note.setModifiedTime();
	}

	public void deleteNote(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		Note note = manager.findNoteById(req.getParameter("id"));
		manager.deleteNote(note);
		manager.updateDB(note);

		resp.sendRedirect("/index.jsp");
	}


	public void addSubject(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		String subject = req.getParameter("newSubject");
		manager.createSubject(subject);
	}

	public void renameTitle(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		Note note = manager.findNoteById(id);

		if  (title != null && !title.trim().isEmpty())
		{
			note.setTitle(title.trim());
			note.setModifiedTime();
			manager.updateDB(note);
		}
	}

	public void permanentDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Note note = manager.findNoteById(id);

		manager.permanentDelete(note);

		resp.setStatus(HttpServletResponse.SC_OK);

		resp.sendRedirect("/index.jsp");
	}

	public void recoverNote(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Note note = manager.findNoteById(id);

		note.setInTrash(false);
		note.setModifiedTime();
		manager.updateDB(note);
		manager.getTrash().remove(note);

		resp.sendRedirect("/index.jsp");
	}


}
