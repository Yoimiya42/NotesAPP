package ucl.ac.uk.notesapp.controller;

import ucl.ac.uk.notesapp.model.entity.Note;

import java.util.List;

public interface NoteService<T extends Note>{

	List<T> allNotes(Class<T> noteType);


	void addNote(T note);
}
