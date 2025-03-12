package ucl.ac.uk.notesapp.model.manager;

import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.entity.TextNote;
import ucl.ac.uk.notesapp.util.JsonUtil;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Manager {

	private static final String JSON_DIR = "data/";
	private final Map<String, Note> noteBook = new HashMap<>();
	private final List<String> subjects = new ArrayList<>();
	private final List<Note> trash = new ArrayList<>();

	public Manager()
	{
		subjects.add("Unarchived");
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
				createSubject(subject);
				noteBook.put(note.getId(), note);
				if(note.isInTrash())
					trash.add(note);
			}
		}
	}


	public List<String> getAllSubjects()
	{return subjects;}

	public void createSubject(String subject)
	{
		if(!subjects.contains(subject))
			subjects.add(subject);
	}

	public List<Note> getSubjectNotes(String subject)
	{
		return getAllNotes().stream()
				.filter(n -> n.getSubject().equals(subject))
				.collect(Collectors.toList());
	}

	public List<Note> getAllNotes()
	{return new ArrayList<>(noteBook.values());}

	public void createNote(Note note) {
		noteBook.put(note.getId(), note);
		updateDB(note);
	}

	public Note findNoteById(String id)
	{return noteBook.get(id);}

	public void updateDB(Note note) {
		JsonUtil.writeJsonFile(JSON_DIR+note.getId()+".json", note);
	}

	public void deleteNote(Note note)
	{
		note.setInTrash(true);
		note.setModifiedTime();
		trash.add(note);

		updateDB(note);
	}

	public void permanentDelete(Note note) {
		noteBook.remove(note.getId());
		trash.remove(note);
		File file = new File(JSON_DIR + note.getId() + ".json");
		if (file.exists()) {
			file.delete();
		}
	}

	public List<Note> getTrash()
	{
		return trash;
	}

	public List<Note> getAllNotesSorted(String sortBy)
	{
		List<Note> allNotes = getAllNotes();

		switch(sortBy)
		{
			case "createdTime_asc":
				Collections.sort(allNotes, Comparator.comparing(Note::getCreatedTime));
				break;
			case "createdTime_desc":
				Collections.sort(allNotes, Comparator.comparing(Note::getCreatedTime).reversed());
				break;
			case "modifiedTime_asc":
				Collections.sort(allNotes, Comparator.comparing(Note::getModifiedTime));
				break;
			case "modifiedTime_desc":
				Collections.sort(allNotes, Comparator.comparing(Note::getModifiedTime).reversed());
				break;
			case "title_asc":
				Collections.sort(allNotes, (n1, n2) -> n1.getTitle().compareToIgnoreCase(n2.getTitle()));
				break;
			case "title_desc":
				Collections.sort(allNotes, (n1, n2) -> n2.getTitle().compareToIgnoreCase(n1.getTitle()));
				break;
			default:
				Collections.sort(allNotes, Comparator.comparing(Note::getModifiedTime).reversed()); // 默认按修改时间降序
				break;
		}

		return allNotes;
	}

	public List<Note> searchNotes(String searchStr, String searchOption) {
		List<Note> allNotes = getAllNotes();
		if (searchStr == null || searchStr.isEmpty()) {
			return allNotes;
		}

		return allNotes.stream()
				.filter(note -> !note.isInTrash())  // Exclude notes in trash
				.filter(note -> {
					if ("title".equals(searchOption)) {
						return note.getTitle().toLowerCase().contains(searchStr.toLowerCase());
					} else if ("content".equals(searchOption)) {
						return note instanceof TextNote &&
								((TextNote) note).getContent() != null &&
								((TextNote) note).getContent().toLowerCase().contains(searchStr.toLowerCase());
					}
					return false;
				})
				.collect(Collectors.toList());
	}

}
