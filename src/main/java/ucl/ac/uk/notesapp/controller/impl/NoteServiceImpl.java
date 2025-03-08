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
//		try {
//			return JsonUtil.readJsonFile("data/note.json", noteType);
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//			return Collections.emptyList();
//		}
		return manager.getAllNotes();
	}

	@Override
	public void addNote(Note note)
	{

		try{
			String fileName = note.getCreatedTime() + ".json";
			JsonUtil.writeJsonFile("data/"+ fileName, note);
			manager.addNote(note, note.getSubject());
		}catch (IOException e){
			throw new RuntimeException("Error writing note", e);
		}
	}

	@Override
	public List<String> loadSubjects()
	{
		return manager.getAllSubject();
	}

	public void createSubject(String subject)
	{
		manager.createSubject(subject);
	}


}
