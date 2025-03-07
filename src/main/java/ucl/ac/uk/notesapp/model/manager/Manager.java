package ucl.ac.uk.notesapp.model.manager;

import ucl.ac.uk.notesapp.model.entity.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Manager{

	private static final Map<String, List<Note>> ALL_NOTES = new HashMap<>();

	public Manager()
	{
		ALL_NOTES.put("Unarchived", new ArrayList<>());
		ALL_NOTES.put("RecycleBin", new ArrayList<>());
	}

	public void newNoteBook(String subject)
	{
		if(ALL_NOTES.get(subject) == null)
			ALL_NOTES.put(subject, new ArrayList<>());
	}
}