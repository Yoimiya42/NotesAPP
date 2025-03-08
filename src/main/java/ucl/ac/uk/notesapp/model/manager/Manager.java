package ucl.ac.uk.notesapp.model.manager;

import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.entity.PlainTextNote;
import ucl.ac.uk.notesapp.util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Manager{

	private final Map<String, List<Note>> ALL_NOTEBOOKS = new HashMap<>();

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


	public void loadAllNotes()
	{
		try{
			File dataDir = new File("data");
			File[] files = dataDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
			if(files != null)
			{
				for(File file:files)
				{
					Note note = JsonUtil.readJsonFile(file.getAbsolutePath(), PlainTextNote.class);
					String subject = note.getSubject();
					if (!ALL_NOTEBOOKS.containsKey(subject))
						createSubject(subject);
					ALL_NOTEBOOKS.get(subject).add(note);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args){
		Manager manager = new Manager();
		List<Note> notes = manager.getAllNotes();
		for(Note note: notes)
		{
			System.out.println(note.getTitle());
		}
	}
}