package ucl.ac.uk.notesapp.model.manager;

import ucl.ac.uk.notesapp.model.entity.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Manager{

	private  final Map<String, List<Note>> ALL_NOTEBOOKS = new HashMap<>();

	public Manager()
	{
		ALL_NOTEBOOKS.put("Unarchived", new ArrayList<>());
		ALL_NOTEBOOKS.put("RecycleBin", new ArrayList<>());
	}

	public void createSubject(String archive)
	{
		if(ALL_NOTEBOOKS.get(archive) == null)
			ALL_NOTEBOOKS.put(archive, new ArrayList<>());
	}
	

	public List<String> getAllSubject()
	{return new ArrayList<>(ALL_NOTEBOOKS.keySet());}


	public  List<Note> getAllNotes()
	{
		List<Note> allNotes = new ArrayList<>();
		for(List<Note> subjectNotes : ALL_NOTEBOOKS.values())
			allNotes.addAll(subjectNotes);

		return allNotes;
	}

	public void addNote(Note note, String subject)
	{
		ALL_NOTEBOOKS.get(subject).add(note);
	}


	public static void loadAllNotes()
	{

	}


}