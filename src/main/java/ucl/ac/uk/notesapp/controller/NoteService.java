package ucl.ac.uk.notesapp.controller;

import ucl.ac.uk.notesapp.model.entity.Note;

import java.util.List;

public interface NoteService<T extends Note>{

	List<Note> allNotes();

	void addNote(T note);

	List<String> loadSubjects();

	void createSubject(String subject);
}
