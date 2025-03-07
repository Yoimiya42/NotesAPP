package ucl.ac.uk.notesapp.controller.impl;

import ucl.ac.uk.notesapp.controller.NoteService;
import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.util.JsonUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class NoteServiceImpl<T extends Note> implements NoteService<T>
{
	private static final String NOTES_PATH = "notes/";

	@Override
	public  List<T> allNotes(Class<T> noteType)
	{
		try {
			return JsonUtil.readJsonFile("data/note.json", noteType);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public void addNote(Note note)
	{
		try{
			JsonUtil.writeJsonFile("data/testWrite.json", note);
		}catch (IOException e){
			throw new RuntimeException("Error writing note", e);
		}
	}


}
