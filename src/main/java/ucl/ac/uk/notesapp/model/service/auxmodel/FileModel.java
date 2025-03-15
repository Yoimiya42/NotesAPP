/**
 *  Auxiliary class in the Model layer of the MVC architecture, responsible for
 *  file operations related to notes.
 *  Handles loading, persisting, and deleting note data in JSON format within the application.
 *
 * @author Luan Fangming
 * @version 3.1
 * @since 2025-03-08
 */


package ucl.ac.uk.notesapp.model.service.auxmodel;

import java.io.File;
import java.util.List;
import java.util.Map;

import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.entity.NoteType;
import ucl.ac.uk.notesapp.util.JsonUtil;


public class FileModel {
	private static final String JSON_DIR = "data/";

	/**
	 * Constructs the file path of a note based on its ID and type suffix
	 * @param note
	 * @param noteType  the type of the note
	 * @return  the file path as a string (e.g., "data/20250308214548_T.json")
	 */
	private String getFilePath(Note note, NoteType noteType){
		return JSON_DIR + note.getId() + "_" + noteType.getSuffix() + ".json";
	}

	/**
	 * Loads all notes from the JSON directory into the noteBook and trash list, and builds subjects list.
	 * @param noteBook    the map to store notes (key: note ID, value: Note object)
	 * @param trash       the list to store trashed notes
	 * @param subjects    the list to record subjects added
	 */
	public void loadAllNotes(Map<String, Note> noteBook, List<Note> trash,
	                         List<String> subjects) {
		File filesDir = new File(JSON_DIR);
		File[] files = filesDir.listFiles((dir, name) -> name.endsWith(".json"));
		if(files != null)
		{
			for(File file : files)
			{
				String fileName = file.getName();
				String suffix = fileName.substring(fileName.lastIndexOf("_") + 1,
													fileName.lastIndexOf("."));
				NoteType noteType = NoteType.fromSuffix(suffix);
				Note note = JsonUtil.readJsonFile(file.getAbsolutePath(), noteType.getNoteClass());
				noteBook.put(note.getId(), note);

				String subject = note.getSubject();
				if (!subjects.contains(subject))
					subjects.add(subject);
				if(note.isInTrash())
					trash.add(note);
			}
		}
	}

	/**
	 * Persists a note to the JSON directory.
	 * @param note
	 */
	public void saveNote(Note note)
	{
		NoteType noteType = NoteType.fromNote(note);
		String filePath = getFilePath(note, noteType);
		JsonUtil.writeJsonFile(filePath, note);
	}

	/**
	 * Permanently deletes the JSON file of a note.
	 * @param note
	 */
	public void deleteNoteFile(Note note)
	{
		NoteType noteType = NoteType.fromNote(note);
		String filePath = getFilePath(note, noteType);
		File file = new File(filePath);
		if(file.exists())
			file.delete();
	}

}
