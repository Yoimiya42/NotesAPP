package ucl.ac.uk.notesapp.model.manager;

import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.entity.TextNote;
import ucl.ac.uk.notesapp.util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Manager {

	private static final String JSON_DIR = "data/";
	private final Map<String, List<Note>> noteBook = new HashMap<>();

	public Manager() {
		noteBook.put("Unarchived", new ArrayList<>());
		noteBook.put("RecycleBin", new ArrayList<>());
		loadAllNotes();
	}

	public void loadAllNotes()
	{
		File dataDir = new File(JSON_DIR);
		File[] files = dataDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
		if (files != null)
		{
			for (File file : files)
			{
				Note note = JsonUtil.readJsonFile(file.getAbsolutePath(), TextNote.class);
				String subject = note.getSubject();
				if (!noteBook.containsKey(subject))
					createSubject(subject);
				noteBook.get(subject).add(note);
			}
		}
	}

	public void createSubject(String subject)
	{
		if (noteBook.get(subject) == null)
			noteBook.put(subject, new ArrayList<>());
	}

	public List<String> getAllSubject() {return new ArrayList<>(noteBook.keySet());}



	public List<Note> getSubjectNotes(String subject) {return noteBook.get(subject);}

	public List<Note> getAllNotes() {
		List<Note> allNotes = new ArrayList<>();
		for (List<Note> subjectNotes : noteBook.values())
			allNotes.addAll(subjectNotes);

		return allNotes;
	}



	public Note findNoteBySubjectAndId(String subject, String id) {
		List<Note> subjectNotes = getSubjectNotes(subject);
		for (Note note : subjectNotes) {
			if (note.getId().equals(id))
				return note;
		}

		return null;
	}

	public Note findNoteById(String id) {
		List<Note> allNotes = getAllNotes();
		for (Note note : allNotes) {
			if (note.getId().equals(id))
				return note;
		}

		return null;
	}


	public void createNote(Note note, String subject) {
		noteBook.get(subject).add(note);
		updateDB(note);
	}

	public void updateDB(Note note) {
		JsonUtil.writeJsonFile(JSON_DIR+note.getId()+".json", note);
	}

	public void moveNote(String oldSubject, String id, String newSubject) {

		List<Note> oldSubjectNotes = getSubjectNotes(oldSubject);
		for (Note note : oldSubjectNotes)
		{
			if (note.getId().equals(id))
			{
				oldSubjectNotes.remove(note);
				createNote(note, newSubject);
				return;
			}
		}
	}





}