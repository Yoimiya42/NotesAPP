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
import java.util.Arrays;
import java.util.List;

@WebServlet("/note/*")
public class NoteServlet extends BaseServlet {

	private Manager manager = ManagerFactory.getManager();

//	public void saveNote(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException
//	{
//
//
//		String oldSubject = req.getParameter("oldSub");
//		String subject = req.getParameter("subject");
//		String id = req.getParameter("id");
//
//
//		Note note = null;
//		if (oldSubject != null && !oldSubject.trim().isEmpty())
//		{
//
//			note = noteService.findNote(oldSubject, id);
//			note.setModifiedTime();
//			if (!oldSubject.equals(subject))
//				noteService.moveNote(oldSubject, id, subject);
//		}else{
//
//			note = new TextNote();
//		}
//
//
//		note.setTitle(req.getParameter("title"));
//		note.setTags(new ArrayList<>(Arrays.asList(req.getParameterValues("labels"))));
//		note.setContent(req.getParameter("content"));
//		note.setSubject(req.getParameter("subject"));
//
//		noteService.addNote(note);
//		resp.sendRedirect("/index.jsp");
//	}

	public void createNote(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		Note note = new TextNote();

		String title = req.getParameter("title");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		List<String> tags = Arrays.asList(req.getParameterValues("labels"));

		note.setTitle(title);
		note.setSubject(subject);
		note.setContent(content);
		note.setTags(tags);

		manager.createNote(note);

		resp.sendRedirect("/index.jsp");
	}

	public void updateNote(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		String id = req.getParameter("id");
		Note note = manager.findNoteById(id);

		note.setContent(req.getParameter("content"));
		note.setSubject(req.getParameter("subject"));
		note.setTitle(req.getParameter("title"));
		note.setModifiedTime();

		manager.updateDB(note);
		resp.sendRedirect("/index.jsp");
	}

	public void noteList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		String subject = req.getParameter("subject");
		System.out.println(subject);
		List<Note> notes;
		if(subject == null)
		{
			notes = manager.getAllNotes();
			req.setAttribute("currentChoice", "all");
		}else if(subject.equals("recycleBin"))
		{
			notes = manager.getTrash();
			req.setAttribute("currentChoice", "recycleBin");
		}
		 else{
			notes = manager.getSubjectNotes(subject);
			req.setAttribute("currentChoice", subject);
		}


		req.setAttribute("notes", notes);
		req.setAttribute("subjects", manager.getAllSubjects());
		req.getRequestDispatcher("/NoteList.jsp").forward(req, resp);
	}

	public void loadForm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		// Create
		if(req.getParameter("mode").equals("create"))
		{
			req.setAttribute("action", "create");
		}else{ // Edit
			String id = req.getParameter("id");
			Note note = manager.findNoteById(id);

			req.setAttribute("note", note);
			req.setAttribute("action", "edit");
		}
		req.setAttribute("subjects", manager.getAllSubjects());
		req.getRequestDispatcher("/form.jsp").forward(req, resp);
	}

	public void addSubject(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		String subject = req.getParameter("newSubject");
		manager.createSubject(subject);
	}


	public void sortNotes(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		String sortBy = req.getParameter("sortBy");

		List<Note> notes = manager.getAllNotesSorted(sortBy);

		req.setAttribute("notes", notes);
		req.setAttribute("sortBy", sortBy);

		req.getRequestDispatcher("/NoteList.jsp").forward(req, resp);
	}


	public void deleteNote(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{

		String id = req.getParameter("id");
		Note note = manager.findNoteById(id);
		manager.deleteNote(note);

		manager.updateDB(note);
		resp.sendRedirect("/index.jsp");
	}

	public void search(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String searchStr = req.getParameter("searchStr");
		String searchZone = req.getParameter("searchZone");
		String timeInterval = req.getParameter("timeInterval");

//		List<Note> notes = manager.


	}
}
