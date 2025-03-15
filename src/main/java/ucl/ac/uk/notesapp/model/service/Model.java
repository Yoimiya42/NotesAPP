/**
 * Model class representing the Model layer in the MVC architecture of the application.
 * Manages note storage, retrieval, subject categorisation, and delegates file, trash and query operations to auxiliary models.
 * 
 * This class is provided as a singleton instance vis ModelFactory.
 * 
 * @author Luan Fangming
 * @version 5.0.3
 * @since 2025-03-07
 */

package ucl.ac.uk.notesapp.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.service.auxmodel.FileModel;
import ucl.ac.uk.notesapp.model.service.auxmodel.TrashModel;
import ucl.ac.uk.notesapp.model.service.auxmodel.QueryModel;
import ucl.ac.uk.notesapp.model.service.auxmodel.FileModel;

public class Model {

	private final Map<String, Note> noteBook = new HashMap<>(); //(key: note ID, value: Note object)
	private final List<String> subjectList = new ArrayList<>();

	private final FileModel fileModel = new FileModel();
	private final TrashModel trashModel = new TrashModel();
	private final QueryModel queryModel = new QueryModel();


	public Model(){
		subjectList.add("Unarchived");
		fileModel.loadAllNotes(noteBook, trashModel.getTrash(), subjectList);
	}

	/**
	 * Creates a new note in the Model and persists it.
	 * @param note
	 */
	public void createNote(Note note)
	{
		noteBook.put(note.getId(), note);
		updateNote(note);
	}

	/**
	 * Updates an existing note in the Model and persists it.
	 * @param note
	 */
	public void updateNote(Note note) {
		fileModel.saveNote(note);
		note.setModifiedTime();
	}

	public Note findNoteById(String id){
		return noteBook.get(id);
	}

	public List<Note> getAllNotes(){
		return new ArrayList<>(noteBook.values());
	}

	public List<String> getAllSubjects(){   
		return new ArrayList<>(subjectList);    
	}


	//Create a new subject or categories of notes
	public void createSubject(String subject){
		if (!subjectList.contains(subject) && subject != null)
			subjectList.add(subject);
	}

	/**
	 * Retrieve all notes belonging to the specified subject 
	 * @param subject	the subject filter to apply 
	 * @return a list of notes belonging to the specified subject
	 */
	public List<Note> getSubjectNotes(String subject){
		return getAllNotes().stream()
				.filter(n -> n.getSubject().equals(subject))
				.collect(Collectors.toList());
	}


	// -------------------------- Deletion ----------------------------
	/**
	 * Moves a note to the trash and updates its status of being trashed.
	 * @param note
	 */
	public void deleteNote(Note note){
		trashModel.moveToTrash(note);
		fileModel.saveNote(note);
	}

	public void recoverNote(Note note){
		note.setInTrash(false);
		updateNote(note);
		trashModel.removeFromTrash(note);
	}

	/**
	 * Permanently deletes from the Model, trash and local directory
	 * @param note
	 */
	public void permanentDelete(Note note)
	{
		noteBook.remove(note.getId());
		trashModel.removeFromTrash(note);
		fileModel.deleteNoteFile(note);
	}

	public List<Note> getTrash(){   
		return trashModel.getTrash();   
	}

	// ----------------------------- Query ---------------------------------
	/**
	 * Returns a sorted list of notes based on the specified criterion (e.g., title_desc, modifiedTime_asc)
	 * @param sortBy the criterion to sort the notes by
	 * @return	a sorted list of notes
	 */
	public List<Note> getSortedNotes(String sortBy){   
		return queryModel.sortNotes(getAllNotes(), sortBy);    
	}

	public List<Note> searchNotes(String searchStr, String searchOption){   
		return queryModel.searchNotes(getAllNotes(), searchStr, searchOption);  
	}
}


