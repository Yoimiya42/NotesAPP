package ucl.ac.uk.notesapp.controller.impl;

import ucl.ac.uk.notesapp.controller.NoteService;
import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.manager.Manager;
import ucl.ac.uk.notesapp.model.manager.ManagerFactory;
import ucl.ac.uk.notesapp.util.JsonUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class NoteServiceImpl<T extends Note> implements NoteService<T>
{
	private static final String NOTES_PATH = "notes/";
	private Manager manager = ManagerFactory.getManager();

	@Override
	public List<Note> allNotes()
	{
		return manager.getAllNotes();
	}

	@Override
	public void addNote(Note note)
	{

		try{
			String fileName = note.getCreatedTime() + ".json";
			JsonUtil.writeJsonFile("data/"+ fileName, note);
			manager.addNote(note, note.getSubject());
			System.out.println(manager.getAllNotes().size());
		}catch (IOException e){
			throw new RuntimeException("Error writing note", e);
		}
	}

	@Override
	public List<String> loadSubjects()
	{
		return manager.getAllSubject();
	}

	@Override
	public Note findNote(String subject, String id) {
		List<Note> notebook = manager.getNoteBook(subject);
		for(Note note : notebook)
		{
			if(note.getCreatedTime().equals(id))
			{
				return note;
			}
		}

		return null;
	}

	public void deleteNote(String subject, String id) {
		Note note = findNote(subject, id);
		moveNote(subject, id, "RecycleBin");

	}

	public void moveNote(String oldSubject, String id, String newSubject) {
		List<Note> notebook = manager.getNoteBook(oldSubject);
		for(Note note : notebook)
		{
			if(note.getCreatedTime().equals(id))
			{
				notebook.remove(note);
				manager.addNote(note, newSubject);
			}
		}

	}

	public void createSubject(String subject)
	{
		manager.createSubject(subject);
	}



}
