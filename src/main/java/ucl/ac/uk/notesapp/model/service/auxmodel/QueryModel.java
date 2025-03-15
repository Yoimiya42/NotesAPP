/**
 * Auxiliary class in the Model layer of the MVC architecture, responsible for querying and sorting notes.
 * @author Luan Fangming
 * @version 3.1
 * @since 2025-03-08
 */


package ucl.ac.uk.notesapp.model.service.auxmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ucl.ac.uk.notesapp.model.entity.MultimediaNote;
import ucl.ac.uk.notesapp.model.entity.Note;
import ucl.ac.uk.notesapp.model.entity.TextNote;
import ucl.ac.uk.notesapp.model.entity.TodoListNote;

public class QueryModel {
	public List<Note> sortNotes(List<Note> notes, String sortBy) {
		List<Note> sortedNotes = notes;

		switch (sortBy) {
			case "createdTime_asc":
				Collections.sort(sortedNotes, Comparator.comparing(Note::getCreatedTime));
				break;
			case "createdTime_desc":
				Collections.sort(sortedNotes, Comparator.comparing(Note::getCreatedTime).reversed());
				break;
			case "modifiedTime_asc":
				Collections.sort(sortedNotes, Comparator.comparing(Note::getModifiedTime));
				break;
			case "modifiedTime_desc":
				Collections.sort(sortedNotes, Comparator.comparing(Note::getModifiedTime).reversed());
				break;
			case "title_asc":
				Collections.sort(sortedNotes, (n1, n2) -> n1.getTitle().compareToIgnoreCase(n2.getTitle()));
				break;
			case "title_desc":
				Collections.sort(sortedNotes, (n1, n2) -> n2.getTitle().compareToIgnoreCase(n1.getTitle()));
				break;
			default:
				Collections.sort(sortedNotes, Comparator.comparing(Note::getModifiedTime).reversed());
				break;
		}

		return sortedNotes;
	}

	public List<Note> searchNotes(List<Note> notes, String searchStr, String searchOption) {
		if (searchStr == null || searchStr.isEmpty()) {
			return new ArrayList<>(notes);
		}

		String searchLower = searchStr.toLowerCase();
		return notes.stream()
				.filter(note -> !note.isInTrash())
				.filter(note -> matchesSearch(note, searchLower, searchOption))
				.collect(Collectors.toList());
	}

	/**
	 * Determines if a note matches the search criteria based on the specified option.
	 *
	 * @param note         the note to check
	 * @param searchLower  the lowercase search string
	 * @param searchOption the field to search in ("title" or "content")
	 * @return true if the note matches the search criteria, false otherwise
	 */
	private boolean matchesSearch(Note note, String searchLower, String searchOption) {
		if ("title".equals(searchOption)) {
			return note.getTitle().toLowerCase().contains(searchLower);
		} else if ("content".equals(searchOption)) {
			return matchesContent(note, searchLower);
		}
		return false;
	}


	/**
	 * Checks if the content of a note matches the search string,
	 * based on its specific type.
	 *
	 * @param note        the note to check
	 * @param searchLower the lowercase search string
	 * @return true if the content matches, false otherwise
	 */
	private boolean matchesContent(Note note, String searchLower) {
		if (note instanceof TextNote) {
			String content = ((TextNote) note).getContent();
			return content != null && content.toLowerCase().contains(searchLower);
		} else if (note instanceof MultimediaNote) {
			return ((MultimediaNote) note).getMediaItems().stream()
					.anyMatch(item -> (item.getDescription() != null
							&& item.getDescription().toLowerCase().contains(searchLower)));
		} else if (note instanceof TodoListNote) {
			return ((TodoListNote) note).getTodoItems().stream()
					.anyMatch(item -> item.getTask() != null
							&& item.getTask().toLowerCase().contains(searchLower));
		}
		return false;
	}
}