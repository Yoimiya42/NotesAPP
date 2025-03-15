/**
 * Auxiliary service class in the Model layer of the MVC architecture, responsible
 * for managing trashed notes.
 *
 * @author Luan Fangming
 * @version 2.1
 * @since 2025-03-09
 */

package ucl.ac.uk.notesapp.model.service.auxmodel;

import ucl.ac.uk.notesapp.model.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class TrashModel {
	private final List<Note> trash = new ArrayList<>();

	public List<Note> getTrash() {
		return trash;
	}

	public void moveToTrash(Note note) {
		note.setInTrash(true);
		note.setModifiedTime();
		trash.add(note);
	}

	public void removeFromTrash(Note note) {
		trash.remove(note);
	}

}
